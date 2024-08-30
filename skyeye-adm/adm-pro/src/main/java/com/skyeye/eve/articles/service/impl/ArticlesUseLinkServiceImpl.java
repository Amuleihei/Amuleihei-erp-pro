/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.articles.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.articles.dao.ArticlesUseLinkDao;
import com.skyeye.eve.articles.entity.Articles;
import com.skyeye.eve.articles.entity.ArticlesUseLink;
import com.skyeye.eve.articles.service.ArticlesService;
import com.skyeye.eve.articles.service.ArticlesUseLinkService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ArticlesUseLinkServiceImpl
 * @Description: 用品领用申请关联的用品信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 19:54
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "用品领用单-用品Link", groupName = "用品模块", manageShow = false)
public class ArticlesUseLinkServiceImpl extends SkyeyeLinkDataServiceImpl<ArticlesUseLinkDao, ArticlesUseLink> implements ArticlesUseLinkService {

    @Autowired
    private ArticlesService articlesService;

    @Override
    public void checkLinkList(String pId, List<ArticlesUseLink> beans) {
        if (CollectionUtil.isEmpty(beans)) {
            throw new CustomException("用品信息不能为空.");
        }
        List<String> articleIds = beans.stream().map(ArticlesUseLink::getArticleId).distinct().collect(Collectors.toList());
        Map<String, Articles> articlesMap = articlesService.selectMapByIds(articleIds);
        beans.forEach(assetArticlesApplyUseChild -> {
            Articles article = articlesMap.get(assetArticlesApplyUseChild.getArticleId());
            if (article == null) {
                throw new CustomException("数据中包含不存在的用品信息.");
            }
            if (article.getResidualNum() < assetArticlesApplyUseChild.getApplyUseNum()) {
                throw new CustomException("用品【" + article.getName() + "】库存余量不足。");
            }
        });
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editActualUseNumById(String id, String state, Integer actualUseNum) {
        UpdateWrapper<ArticlesUseLink> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(ArticlesUseLink::getState), state);
        updateWrapper.set(MybatisPlusUtil.toColumns(ArticlesUseLink::getActualUseNum), actualUseNum);
        update(updateWrapper);
    }
}
