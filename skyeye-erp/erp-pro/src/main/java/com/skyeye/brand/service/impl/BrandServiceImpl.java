/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.brand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.brand.dao.BrandDao;
import com.skyeye.brand.entity.Brand;
import com.skyeye.brand.service.BrandService;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BrandServiceImpl
 * @Description: 品牌管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/17 21:15
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "品牌管理", groupName = "品牌管理")
public class BrandServiceImpl extends SkyeyeBusinessServiceImpl<BrandDao, Brand> implements BrandService {

    @Override
    public void queryEnabledBrandList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Brand::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<Brand> brandList = list(queryWrapper);
        outputObject.setBeans(brandList);
        outputObject.settotal(brandList.size());
    }
}