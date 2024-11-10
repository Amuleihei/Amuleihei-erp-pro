/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coupon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.coupon.dao.CouponUseDao;
import com.skyeye.coupon.entity.Coupon;
import com.skyeye.coupon.entity.CouponMaterial;
import com.skyeye.coupon.entity.CouponUse;
import com.skyeye.coupon.entity.CouponUseMaterial;
import com.skyeye.coupon.enums.CouponUseState;
import com.skyeye.coupon.enums.CouponValidityType;
import com.skyeye.coupon.enums.PromotionDiscountType;
import com.skyeye.coupon.service.CouponService;
import com.skyeye.coupon.service.CouponUseMaterialService;
import com.skyeye.coupon.service.CouponUseService;
import com.skyeye.exception.CustomException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: CouponUseServiceImpl
 * @Description: 优惠券领取信息管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/23 10:43
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "优惠券领取信息管理", groupName = "优惠券领取信息管理")
public class CouponUseServiceImpl extends SkyeyeBusinessServiceImpl<CouponUseDao, CouponUse> implements CouponUseService {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponUseMaterialService couponUseMaterialService;

    private void check(Coupon coupon) {
        if (ObjectUtil.isEmpty(coupon)) {
            throw new CustomException("优惠券不存在");
        }
        if (Objects.equals(coupon.getEnabled(), WhetherEnum.DISABLE_USING.getKey())) {
            throw new CustomException("优惠券已过期");
        }
        if (coupon.getTotalCount() != -1 && coupon.getTotalCount() <= CommonNumConstants.NUM_ZERO) {
            throw new CustomException("数量不足,领取失败.");
        }
        if (coupon.getTakeLimitCount() == -1) {
            return;
        }
        QueryWrapper<CouponUse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getCouponId), coupon.getId());
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getCreateId), InputObject.getLogParamsStatic().get("id").toString());
        if (count(queryWrapper) >= coupon.getTakeLimitCount()) {
            throw new CustomException("超出领取数量限制");
        }
    }

    @Override
    public void createPrepose(CouponUse couponUse) {
        Coupon coupon = couponService.selectById(couponUse.getCouponId());
        check(coupon);
        // 设置适用对象
        List<CouponUseMaterial> couponUseMaterialList = couponUse.getCouponUseMaterialList();
        for (CouponMaterial couponMaterial : coupon.getCouponMaterialList()) {
            CouponUseMaterial couponUseMaterial = new CouponUseMaterial();
            couponUseMaterial.setCouponId(coupon.getId());
            couponUseMaterial.setMaterialId(couponMaterial.getMaterialId());
            couponUseMaterialList.add(couponUseMaterial);
        }
        if (StrUtil.isNotEmpty(couponUse.getCouponId())) {
            // 状态
            couponUse.setState(CouponUseState.UNUSED.getKey());
            //满减
            couponUse.setUsePrice(coupon.getUsePrice());
            //使用范围
            couponUse.setProductScope(coupon.getProductScope());
            //生效时间
            if (Objects.equals(CouponValidityType.DATE.getKey(), coupon.getValidityType())) {
                couponUse.setValidStartTime(coupon.getValidStartTime());
                couponUse.setValidEndTime(coupon.getValidEndTime());
            } else {
                couponUse.setValidStartTime(DateUtil.getAfDate(LocalDate.now().toDate(), coupon.getFixedStartTime(), "d").toString());
                couponUse.setValidEndTime(DateUtil.getAfDate(LocalDate.now().toDate(), coupon.getFixedEndTerm(), "d").toString());
            }
            //折扣类型
            couponUse.setDiscountType(coupon.getDiscountType());
            //折扣值
            if (Objects.equals(PromotionDiscountType.PERCENT.getKey(), coupon.getDiscountType())) {
                couponUse.setDiscountPercent(coupon.getDiscountPercent());
            } else {
                couponUse.setDiscountPrice(coupon.getDiscountPrice());
            }
            //折扣上限
            couponUse.setDiscountLimitPrice(coupon.getDiscountLimitPrice());
        }
    }

    @Override
    public void createPostpose(CouponUse couponUse, String userId) {
        QueryWrapper<CouponUse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getCouponId), couponUse.getCouponId());
        couponService.updateTakeCount(couponUse.getCouponId(), (int) count(queryWrapper));
        couponUseMaterialService.createEntity(couponUse.getCouponUseMaterialList(), userId);
    }

    @Override
    public void writePostpose(CouponUse couponUse, String userId) {
        if (ObjectUtil.isNotEmpty(couponUse.getCouponUseMaterialList())) {
            couponUse.getCouponUseMaterialList().forEach(couponMaterial -> couponMaterial.setCouponId(couponUse.getId()));
            couponUseMaterialService.createEntity(couponUse.getCouponUseMaterialList(), userId);
        }
    }

    @Override
    public void updatePrepose(CouponUse couponUse) {
        if (StrUtil.isNotEmpty(couponUse.getUseOrderId())) {
            couponUse.setUseTime(DateUtil.getTimeAndToString());
            couponUse.setState(CouponUseState.USED.getKey());
        }
    }

    @Override
    public void updatePostpose(CouponUse couponUse, String userId) {
        QueryWrapper<CouponUse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getCouponId), couponUse.getCouponId());
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getState), CouponUseState.USED.getKey());
    }

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<CouponUse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getCreateId), inputObject.getLogParams().get("id").toString());
        if (params.containsKey("state")) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getState), params.get("state").toString());
        }
        // 查询时获取数据
        List<CouponUse> list = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(list), null);
    }

    /**
     * xxlJob任务管理器定时修改过期优惠券的状态
     */
    @Override
    public void setStateByCouponUse() {
        UpdateWrapper<CouponUse> updateWrapper = new UpdateWrapper<>();
        // 取优未使用的优惠券
        updateWrapper.eq(MybatisPlusUtil.toColumns(CouponUse::getState), CouponUseState.UNUSED.getKey());
        // 固定日期类型的优惠券
        updateWrapper.lt(MybatisPlusUtil.toColumns(CouponUse::getValidEndTime), DateUtil.getTimeAndToString());
        updateWrapper.set(MybatisPlusUtil.toColumns(CouponUse::getState), CouponUseState.EXPIRE.getKey());
        update(updateWrapper);
    }
}