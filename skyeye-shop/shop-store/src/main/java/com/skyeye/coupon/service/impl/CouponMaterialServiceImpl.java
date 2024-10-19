package com.skyeye.coupon.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.coupon.dao.CouponMaterialDao;
import com.skyeye.coupon.entity.CouponMaterial;
import com.skyeye.coupon.service.CouponMaterialService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "优惠券/模版适用商品对象管理", groupName = "优惠券/模版适用商品对象管理", manageShow = false)
public class CouponMaterialServiceImpl extends SkyeyeBusinessServiceImpl<CouponMaterialDao, CouponMaterial> implements CouponMaterialService {
    @Override
    public List<CouponMaterial> queryListByCouponId(String couponId) {
        QueryWrapper<CouponMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponMaterial::getCouponId), couponId);
        return list(queryWrapper);
    }

    @Override
    public Map<String, List<CouponMaterial>> queryListByCouponId(List<String> couponIdList) {
        QueryWrapper<CouponMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(CouponMaterial::getCouponId), couponIdList);
        List<CouponMaterial> list = list(queryWrapper);
        Map<String, List<CouponMaterial>> collect = list.stream().collect(Collectors.groupingBy(CouponMaterial::getCouponId));
        return collect;
    }

    @Override
    public void deleteByCouponId(String id) {
        QueryWrapper<CouponMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponMaterial::getCouponId), id);
        remove(queryWrapper);
    }
}
