/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.assets.dao.AssetPurchaseLinkCodeDao;
import com.skyeye.eve.assets.entity.AssetPurchaseLinkCode;
import com.skyeye.eve.assets.service.AssetPurchaseLinkCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: AssetPurchaseLinkCodeServiceImpl
 * @Description: 资产采购子表关联的条形码编号服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/20 10:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AssetPurchaseLinkCodeServiceImpl extends SkyeyeBusinessServiceImpl<AssetPurchaseLinkCodeDao, AssetPurchaseLinkCode> implements AssetPurchaseLinkCodeService {

    @Override
    public void saveList(String parentId, List<AssetPurchaseLinkCode> beans) {
        deleteByParentId(parentId);
        if (CollectionUtil.isNotEmpty(beans)) {
            for (AssetPurchaseLinkCode assetPurchaseLinkCode : beans) {
                assetPurchaseLinkCode.setParentId(parentId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }

    @Override
    public void deleteByParentId(String parentId) {
        QueryWrapper<AssetPurchaseLinkCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AssetPurchaseLinkCode::getParentId), parentId);
        remove(queryWrapper);
    }

    @Override
    public List<AssetPurchaseLinkCode> selectByParentId(String parentId) {
        QueryWrapper<AssetPurchaseLinkCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AssetPurchaseLinkCode::getParentId), parentId);
        List<AssetPurchaseLinkCode> list = list(queryWrapper);
        return list;
    }
}
