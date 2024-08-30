/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.circle.entity.Circle;
import com.skyeye.circle.service.CircleService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.material.dao.MaterialDao;
import com.skyeye.material.entity.Material;
import com.skyeye.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MaterialServiceImpl
 * @Description: 资料服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "资料管理", groupName = "资料管理")
public class MaterialServiceImpl extends SkyeyeBusinessServiceImpl<MaterialDao, Material> implements MaterialService {

    @Autowired
    private CircleService circleService;

    @Override
    public Material selectById(String id) {
        Material material = super.selectById(id);
        circleService.setDataMation(material, Material::getCircleId);
        return material;
    }

    @Override
    public void validatorEntity(Material material) {
        //修改时的校验
        if (ObjectUtil.isNotEmpty(material.getId())) {
            Circle circle = circleService.selectById(material.getCircleId());
            if (StrUtil.isEmpty(circle.getId())) {
                throw new CustomException("圈子不存在");
            }
            String userId = InputObject.getLogParamsStatic().get("id").toString();
            if (!userId.equals(circle.getCreateId())) {
                throw new CustomException("无权限");
            }
        } else {
            //新增校验
            QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(Material::getCircleId), material.getCircleId());
            Material flagMaterial = getOne(queryWrapper);
            if (ObjectUtil.isNotEmpty(flagMaterial)) {
                throw new CustomException("不可重复新增");
            }
        }
    }

    @Override
    public void deletePreExecution(Material material) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        if (!userId.equals(material.getCreateId())) {
            throw new CustomException("无权限");
        }
    }

    @Override
    public void deleteByCircleId(String circleId) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Material::getCircleId), circleId);
        remove(queryWrapper);
    }
}
