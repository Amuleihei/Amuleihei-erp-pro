/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.articles.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeFlowableServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.FlowableChildStateEnum;
import com.skyeye.common.enumeration.FlowableStateEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.eve.articles.dao.ArticlesPurchaseDao;
import com.skyeye.eve.articles.entity.ArticlesPurchase;
import com.skyeye.eve.articles.entity.ArticlesPurchaseLink;
import com.skyeye.eve.articles.service.ArticlesPurchaseLinkService;
import com.skyeye.eve.articles.service.ArticlesPurchaseService;
import com.skyeye.eve.articles.service.ArticlesService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ArticlesPurchaseServiceImpl
 * @Description: 用品采购申请服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/24 11:41
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "用品采购单", groupName = "用品模块", flowable = true)
public class ArticlesPurchaseServiceImpl extends SkyeyeFlowableServiceImpl<ArticlesPurchaseDao, ArticlesPurchase> implements ArticlesPurchaseService {

    @Autowired
    private ArticlesPurchaseLinkService articlesPurchaseLinkService;

    @Autowired
    private ArticlesService articlesService;

    @Override
    public List<Map<String, Object>> queryPageData(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        pageInfo.setCreateId(inputObject.getLogParams().get("id").toString());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryMyPurchaseArticlesList(pageInfo);
        return beans;
    }

    @Override
    public void validatorEntity(ArticlesPurchase entity) {
        chectOrderItem(entity.getPurchaseLink());
        getTotalPrice(entity);
    }

    @Override
    public void writeChild(ArticlesPurchase entity, String userId) {
        articlesPurchaseLinkService.saveLinkList(entity.getId(), entity.getPurchaseLink());
        super.writeChild(entity, userId);
    }

    private void chectOrderItem(List<ArticlesPurchaseLink> articlesPurchaseLinks) {
        if (CollectionUtil.isEmpty(articlesPurchaseLinks)) {
            throw new CustomException("请最少选择一条用品信息");
        }
        List<String> articleIds = articlesPurchaseLinks.stream()
            .map(ArticlesPurchaseLink::getArticleId).distinct()
            .collect(Collectors.toList());
        if (articlesPurchaseLinks.size() != articleIds.size()) {
            throw new CustomException("单据中不允许出现同一用品信息");
        }
    }

    private void getTotalPrice(ArticlesPurchase entity) {
        String totalPrice = "0";
        // 计算关联的用品总价
        for (ArticlesPurchaseLink purchaseLink : entity.getPurchaseLink()) {
            String amountOfMoney = CalculationUtil.multiply(CommonNumConstants.NUM_TWO, String.valueOf(purchaseLink.getApplyPurchaseNum()), purchaseLink.getUnitPrice());
            purchaseLink.setAmountOfMoney(amountOfMoney);
            totalPrice = CalculationUtil.add(totalPrice, amountOfMoney);
        }
        entity.setAllPrice(totalPrice);
    }

    @Override
    public void submitToApprovalPostpose(String id, String processInstanceId) {
        super.submitToApprovalPostpose(id, processInstanceId);
        articlesPurchaseLinkService.editStateByPId(id, FlowableChildStateEnum.IN_EXAMINE.getKey());
    }

    @Override
    public ArticlesPurchase getDataFromDb(String id) {
        ArticlesPurchase articlesPurchase = super.getDataFromDb(id);
        List<ArticlesPurchaseLink> articlesPurchaseLinks = articlesPurchaseLinkService.selectByPId(articlesPurchase.getId());
        articlesPurchase.setPurchaseLink(articlesPurchaseLinks);
        return articlesPurchase;
    }

    @Override
    public ArticlesPurchase selectById(String id) {
        ArticlesPurchase articlesPurchase = super.selectById(id);
        // 获取用品信息
        articlesService.setDataMation(articlesPurchase.getPurchaseLink(), ArticlesPurchaseLink::getArticleId);
        articlesPurchase.getPurchaseLink().forEach(bean -> {
            bean.setStateName(FlowableChildStateEnum.getStateName(bean.getState()));
        });
        articlesPurchase.setStateName(FlowableStateEnum.getStateName(articlesPurchase.getState()));
        iAuthUserService.setName(articlesPurchase, "createId", "createName");
        return articlesPurchase;
    }

    @Override
    public void revokePostpose(ArticlesPurchase entity) {
        super.revokePostpose(entity);
        articlesPurchaseLinkService.editStateByPId(entity.getId(), FlowableChildStateEnum.DRAFT.getKey());
    }

    @Override
    public void approvalEndIsSuccess(ArticlesPurchase entity) {
        ArticlesPurchase purchase = selectById(entity.getId());
        for (ArticlesPurchaseLink bean : purchase.getPurchaseLink()) {
            // 当前用品剩余的数量
            int residualNum = bean.getArticleMation().getResidualNum();
            // 重置库存剩余数量
            residualNum = residualNum + bean.getApplyPurchaseNum();
            // 修改库存
            articlesService.editResidualNum(bean.getArticleId(), residualNum);
        }
        // 修改用品采购状态
        articlesPurchaseLinkService.editStateByPId(entity.getId(), FlowableChildStateEnum.ADEQUATE.getKey());
    }

    @Override
    protected void approvalEndIsFailed(ArticlesPurchase entity) {
        articlesPurchaseLinkService.editStateByPId(entity.getId(), FlowableChildStateEnum.REJECT.getKey());
    }
}
