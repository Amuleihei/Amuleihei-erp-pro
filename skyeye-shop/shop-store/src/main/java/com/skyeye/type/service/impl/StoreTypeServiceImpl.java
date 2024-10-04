/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.type.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.store.service.ShopStoreService;
import com.skyeye.type.dao.StoreTypeDao;
import com.skyeye.type.entity.StoreType;
import com.skyeye.type.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StoreTypeServiceImpl
 * @Description: 门店商品分类管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "门店商品分类管理", groupName = "门店商品分类管理")
public class StoreTypeServiceImpl extends SkyeyeBusinessServiceImpl<StoreTypeDao, StoreType> implements StoreTypeService {

    @Autowired
    private ShopStoreService shopStoreService;

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<StoreType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(StoreType::getStoreId), params.get("storeId").toString());
        queryWrapper.eq(MybatisPlusUtil.toColumns(StoreType::getEnabled), WhetherEnum.ENABLE_USING.getKey());
        List<StoreType> beans = list(queryWrapper);
        shopStoreService.setDataMation(beans, StoreType::getStoreId);
        return JSONUtil.toList(JSONUtil.toJsonStr(beans), null);
    }

    @Override
    public QueryWrapper<StoreType> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<StoreType> queryWrapper = super.getQueryWrapper(commonPageInfo);
        String objectId = commonPageInfo.getObjectId();
        if (StrUtil.isNotEmpty(objectId)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(StoreType::getStoreId), objectId);
        }
        return queryWrapper;
    }

    @Override
    public void deleteById(InputObject inputObject, OutputObject outputObject) {
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(CommonCharConstants.COMMA_MARK));
        super.deleteById(idList);
    }
}