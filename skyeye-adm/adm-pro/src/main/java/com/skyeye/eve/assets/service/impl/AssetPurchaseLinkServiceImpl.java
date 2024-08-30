/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.assets.dao.AssetPurchaseLinkDao;
import com.skyeye.eve.assets.entity.Asset;
import com.skyeye.eve.assets.entity.AssetPurchaseLink;
import com.skyeye.eve.assets.entity.AssetPurchaseLinkCode;
import com.skyeye.eve.assets.service.AssetPurchaseLinkCodeService;
import com.skyeye.eve.assets.service.AssetPurchaseLinkService;
import com.skyeye.eve.assets.service.AssetService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AssetPurchaseLinkServiceImpl
 * @Description: 资产采购关联的资产信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 19:54
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "资产采购-资产Link", groupName = "资产模块", manageShow = false)
public class AssetPurchaseLinkServiceImpl extends SkyeyeLinkDataServiceImpl<AssetPurchaseLinkDao, AssetPurchaseLink> implements AssetPurchaseLinkService {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetPurchaseLinkCodeService assetPurchaseLinkCodeService;

    @Override
    public void saveLinkList(String pId, List<AssetPurchaseLink> beans) {
        super.saveLinkList(pId, beans);
        // 保存关联的条形码
        List<AssetPurchaseLinkCode> assetPurchaseLinkCodeList = new ArrayList<>();
        beans.forEach(bean -> {
            if (CollectionUtil.isNotEmpty(bean.getNormsCodeList())) {
                bean.getNormsCodeList().forEach(normsCode -> {
                    AssetPurchaseLinkCode assetPurchaseLinkCode = new AssetPurchaseLinkCode();
                    assetPurchaseLinkCode.setNormsCode(normsCode);
                    assetPurchaseLinkCode.setAssetId(bean.getAssetId());
                    assetPurchaseLinkCodeList.add(assetPurchaseLinkCode);
                });
            }
        });
        if (CollectionUtil.isNotEmpty(assetPurchaseLinkCodeList)) {
            assetPurchaseLinkCodeService.saveList(pId, assetPurchaseLinkCodeList);
        }
    }

    @Override
    public void deleteByPId(String pId) {
        super.deleteByPId(pId);
        assetPurchaseLinkCodeService.deleteByParentId(pId);
    }

    @Override
    public List<AssetPurchaseLink> selectByPId(String pId) {
        List<AssetPurchaseLink> assetPurchaseLinkList = super.selectByPId(pId);
        // 查询资产条形码
        List<AssetPurchaseLinkCode> assetPurchaseLinkCodeList = assetPurchaseLinkCodeService.selectByParentId(pId);
        if (CollectionUtil.isNotEmpty(assetPurchaseLinkCodeList)) {
            Map<String, List<AssetPurchaseLinkCode>> collect = assetPurchaseLinkCodeList.stream()
                .collect(Collectors.groupingBy(AssetPurchaseLinkCode::getAssetId));
            // 进行条形码赋值
            assetPurchaseLinkList.forEach(assetPurchaseLink -> {
                List<AssetPurchaseLinkCode> assetPurchaseLinkCodes = collect.get(assetPurchaseLink.getAssetId());
                if (CollectionUtil.isNotEmpty(assetPurchaseLinkCodes)) {
                    List<String> list = assetPurchaseLinkCodes.stream().map(AssetPurchaseLinkCode::getNormsCode).collect(Collectors.toList());
                    assetPurchaseLink.setNormsCodeList(list);
                    assetPurchaseLink.setNormsCode(Joiner.on("\n").join(list));
                }
            });
        }

        return assetPurchaseLinkList;
    }

    @Override
    public void checkLinkList(String pId, List<AssetPurchaseLink> beans) {
        if (CollectionUtil.isEmpty(beans)) {
            throw new CustomException("资产信息不能为空.");
        }
        List<String> articleIds = beans.stream().map(AssetPurchaseLink::getAssetId).distinct().collect(Collectors.toList());
        Map<String, Asset> articlesMap = assetService.selectMapByIds(articleIds);
        beans.forEach(assetPurchaseLink -> {
            Asset asset = articlesMap.get(assetPurchaseLink.getAssetId());
            if (asset == null) {
                throw new CustomException("数据中包含不存在的资产信息.");
            }
        });
    }

    @Override
    public List<AssetPurchaseLink> queryAssetPurchaseLinkByPIds(List<String> pIds) {
        QueryWrapper<AssetPurchaseLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(AssetPurchaseLink::getParentId), pIds);
        return list(queryWrapper);
    }
}
