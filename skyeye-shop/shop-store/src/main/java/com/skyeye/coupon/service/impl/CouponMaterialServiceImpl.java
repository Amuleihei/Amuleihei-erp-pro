package com.skyeye.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.coupon.dao.CouponMaterialDao;
import com.skyeye.coupon.entity.CouponMaterial;
import com.skyeye.coupon.service.CouponMaterialService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "优惠券/模版适用商品对象管理", groupName = "优惠券/模版适用商品对象管理", manageShow = false)
public class CouponMaterialServiceImpl extends SkyeyeBusinessServiceImpl<CouponMaterialDao, CouponMaterial> implements CouponMaterialService {

    @Autowired
    private CouponMaterialService couponMaterialService;

    @Override
    public List<CouponMaterial> queryListByCouponId(String couponId) {
        QueryWrapper<CouponMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponMaterial::getCouponId), couponId);
        return list(queryWrapper);
    }

    @Override
    public void deleteByCouponId(String id) {
        QueryWrapper<CouponMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponMaterial::getCouponId), id);
        remove(queryWrapper);
    }

    @Override
    public void deleteByCouponId(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        QueryWrapper<CouponMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(CouponMaterial::getCouponId), ids);
        remove(queryWrapper);
    }

    @Override
    public void insertCouponMaterial(String couponId, List<CouponMaterial> couponMaterialList, String userId) {
        List<CouponMaterial> materialList = couponMaterialList.stream()
            .filter(couponMaterial -> {
                // 过滤空的适用对象，同时设置优惠券id
                couponMaterial.setCouponId(couponId);
                return StrUtil.isEmpty(couponMaterial.getMaterialId());
            })
            .distinct().collect(Collectors.toList());
        if (materialList.size() != couponMaterialList.size()) {
            throw new CustomException("存在空的或者相同的适用商品对象");
        }
        // 删除原本的适用商品信息
        couponMaterialService.deleteByCouponId(couponId);
        // 批量新增
        couponMaterialService.createEntity(couponMaterialList, userId);
    }
}
