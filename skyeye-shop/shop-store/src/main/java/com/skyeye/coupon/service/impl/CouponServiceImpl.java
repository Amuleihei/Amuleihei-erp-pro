/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.coupon.dao.CouponDao;
import com.skyeye.coupon.entity.Coupon;
import com.skyeye.coupon.enums.CouponValidityType;
import com.skyeye.coupon.enums.PromotionDiscountType;
import com.skyeye.coupon.enums.PromotionMaterialScope;
import com.skyeye.coupon.service.CouponMaterialService;
import com.skyeye.coupon.service.CouponService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: CouponServiceImpl
 * @Description: 优惠券/模版信息管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/10/23 10:07
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "优惠券/模版信息管理", groupName = "优惠券/模版信息管理")
public class CouponServiceImpl extends SkyeyeBusinessServiceImpl<CouponDao, Coupon> implements CouponService {

    @Autowired
    private CouponMaterialService couponMaterialService;

    @Override
    public void validatorEntity(Coupon coupon) {
        if (StrUtil.isNotEmpty(coupon.getId()) && StrUtil.isNotEmpty(coupon.getTemplateId())) {
            throw new CustomException("更新操作不可上传模板id(templateId)");
        }
        // 模板新增
        if (StrUtil.isEmpty(coupon.getId()) && StrUtil.isEmpty(coupon.getTemplateId()) && // 主键和模板id为空时，即为模板
            coupon.getProductScope() != PromotionMaterialScope.ALL.getKey() && // 判断适用商品类型
            CollectionUtil.isEmpty(coupon.getCouponMaterialList()))  // 不适用全部商品时，适用对象不能为空。
        {
            throw new CustomException("需要指定优惠券适用的商品范围，适用全部商品时可为空");
        }
        if (Objects.equals(coupon.getValidityType(), CouponValidityType.DATE.getKey())) {
            if (StrUtil.isEmpty(coupon.getValidStartTime()) || StrUtil.isEmpty(coupon.getValidEndTime())) {
                throw new CustomException("固定日期类型优惠券，有效期不能为空");
            }
        } else {
            if (coupon.getFixedStartTerm() == null || coupon.getFixedEndTerm() == null) {
                throw new CustomException("固定周期类型优惠券，有效期不能为空");
            }
        }

        if (Objects.equals(coupon.getDiscountType(), PromotionDiscountType.PRICE.getKey())) {
            if (coupon.getDiscountPrice() == null) {
                throw new CustomException("价格折扣类型优惠券，折扣金额不能为空");
            }
        } else {
            if (coupon.getDiscountPercent() == null) {
                throw new CustomException("折扣率类型优惠券，折扣率不能为空");
            }
        }
    }

    @Override
    public void writePostpose(Coupon coupon, String userId) {
        // 新增/编辑优惠券的适用商品对象
        if (CollectionUtil.isNotEmpty(coupon.getCouponMaterialList())) {
            couponMaterialService.insertCouponMaterial(coupon.getId(), coupon.getCouponMaterialList(), userId);
        }
    }

    @Override
    public QueryWrapper<Coupon> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<Coupon> queryWrapper = super.getQueryWrapper(commonPageInfo);
        String type = commonPageInfo.getType();
        if (StrUtil.isEmpty(type)) {
            throw new CustomException("暂不支持该类型查询");
        }
        String typeKey = MybatisPlusUtil.toColumns(Coupon::getTemplateId);
        if (type.equals(CommonNumConstants.NUM_ZERO.toString())) {
            queryWrapper.isNull(typeKey).or().eq(typeKey, StrUtil.EMPTY);
        }
        if (type.equals(CommonNumConstants.NUM_ONE.toString())) {
            queryWrapper.isNotNull(typeKey).ne(typeKey, StrUtil.EMPTY);
        }
        return queryWrapper;
    }

    @Override
    public void queryCouponListByState(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Coupon::getStoreId), params.get("storeId").toString());
        String typeKey = MybatisPlusUtil.toColumns(Coupon::getTemplateId);
        if (params.containsKey("type") && Objects.equals(params.get("type"), CommonNumConstants.NUM_ZERO)) {
            queryWrapper.isNull(typeKey).or().eq(typeKey, StrUtil.EMPTY);
        }
        if (params.containsKey("type") && Objects.equals(params.get("type"), CommonNumConstants.NUM_ONE)) {
            queryWrapper.isNotNull(typeKey).ne(typeKey, StrUtil.EMPTY);
        }
        List<Coupon> list = list(queryWrapper);
        outputObject.setBean(list);
        outputObject.settotal(list.size());
    }

    @Override
    public void updateTakeCount(String couponId, Integer takeCount) {
        UpdateWrapper<Coupon> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, couponId);
        updateWrapper.set(MybatisPlusUtil.toColumns(Coupon::getTakeCount), takeCount);
        update(updateWrapper);
    }

    @Override
    public void deletePostpose(List<String> ids) {
        couponMaterialService.deleteByCouponId(ids);
    }

    @Override
    public void setStateByCoupon() {
        UpdateWrapper<Coupon> updateWrapper = new UpdateWrapper<>();
        // 取优惠券
        String typeKey = MybatisPlusUtil.toColumns(Coupon::getTemplateId);
        updateWrapper.isNotNull(typeKey).ne(typeKey, StrUtil.EMPTY);
        // 固定日期类型的优惠券
        updateWrapper.lt(MybatisPlusUtil.toColumns(Coupon::getValidEndTime),
            DateUtil.getTimeAndToString());
        updateWrapper.or()
            // 非固定日期的优惠券
            .lt(MybatisPlusUtil.toColumns(Coupon::getFixedEndTerm),
                DateUtil.getTimeAndToString());
        updateWrapper.set(MybatisPlusUtil.toColumns(Coupon::getEnabled), WhetherEnum.DISABLE_USING.getKey());
        update(updateWrapper);
    }
}
