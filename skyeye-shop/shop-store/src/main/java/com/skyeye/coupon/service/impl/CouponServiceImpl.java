package com.skyeye.coupon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
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
import com.skyeye.coupon.entity.CouponMaterial;
import com.skyeye.coupon.enums.CouponValidityType;
import com.skyeye.coupon.enums.PromotionDiscountType;
import com.skyeye.coupon.service.CouponMaterialService;
import com.skyeye.coupon.service.CouponService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@SkyeyeService(name = "优惠券/模版信息管理", groupName = "优惠券/模版信息管理")
public class CouponServiceImpl extends SkyeyeBusinessServiceImpl<CouponDao, Coupon> implements CouponService {

    @Autowired
    private CouponMaterialService couponMaterialService;

    @Override
    public void validatorEntity(Coupon coupon) {
        if(StrUtil.isNotEmpty(coupon.getId()) && StrUtil.isNotEmpty(coupon.getTemplateId())){
            throw new CustomException("更新操作不可上传模板id(templateId)");
        }
    }

    @Override
    public void createPrepose(Coupon coupon) {
        if (StrUtil.isNotEmpty(coupon.getTemplateId())) {
            Coupon couponTemplate = selectById(coupon.getTemplateId());
            coupon.setEnabled(couponTemplate.getEnabled());
            coupon.setTotalCount(couponTemplate.getTotalCount());
            coupon.setTakeLimitCount(couponTemplate.getTakeLimitCount());
            coupon.setTakeType(couponTemplate.getTakeType());
            coupon.setUsePrice(couponTemplate.getUsePrice());
            coupon.setProductScope(couponTemplate.getProductScope());
            coupon.setValidityType(couponTemplate.getValidityType());
            if (Objects.equals(couponTemplate.getValidityType(), CouponValidityType.DATE.getKey())) {
                coupon.setValidStartTime(couponTemplate.getValidStartTime());
                coupon.setValidEndTime(couponTemplate.getValidEndTime());
            } else {
                coupon.setFixedStartTerm(couponTemplate.getFixedStartTerm());
                coupon.setFixedEndTerm(couponTemplate.getFixedEndTerm());
            }
            coupon.setDiscountType(couponTemplate.getDiscountType());
            if (Objects.equals(couponTemplate.getDiscountType(), PromotionDiscountType.PRICE.getKey())) {
                coupon.setDiscountPrice(couponTemplate.getDiscountPrice());
            } else {
                coupon.setDiscountPercent(couponTemplate.getDiscountPercent());
            }
            coupon.setDiscountLimitPrice(couponTemplate.getDiscountLimitPrice());
            coupon.setTakeCount(couponTemplate.getTakeCount());
            coupon.setUseCount(couponTemplate.getUseCount());
        }
    }

    @Override
    public void writePostpose(Coupon coupon, String userId) {
        if (ObjectUtil.isNotEmpty(coupon.getCouponMaterialList())) {
            // 删除原本的适用商品信息
            couponMaterialService.deleteByCouponId(coupon.getId());
            // 设置优惠券id
            coupon.getCouponMaterialList().forEach(couponMaterial -> couponMaterial.setCouponId(coupon.getId()));
            // 批量新增
            couponMaterialService.createEntity(coupon.getCouponMaterialList(), userId);
        }
    }

    @Override
    public Coupon selectById(String id) {
        Coupon coupon = super.selectById(id);
        List<CouponMaterial> couponMaterialList = couponMaterialService.queryListByCouponId(coupon.getId());
        coupon.setCouponMaterialList(couponMaterialList);
        return coupon;
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        String type = commonPageInfo.getType();
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        if (type.equals(CommonNumConstants.NUM_ONE.toString())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(Coupon::getTemplateId), "");
        }
        if (type.equals(CommonNumConstants.NUM_TWO.toString())) {
            queryWrapper.ne(MybatisPlusUtil.toColumns(Coupon::getTemplateId), "");
        }
        List<Coupon> list = list(queryWrapper);
        List<String> parentIdList = list.stream().map(Coupon::getId).collect(Collectors.toList());
        Map<String, List<CouponMaterial>> couponMapMaterialList =couponMaterialService.queryListByCouponId(parentIdList);
        for (Coupon coupon : list) {
            coupon.setCouponMaterialList(couponMapMaterialList.get(coupon.getId()));
        }
        // 分页查询时获取数据
        return JSONUtil.toList(JSONUtil.toJsonStr(list), null);
    }

    @Override
    public void queryCouponListByState(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Coupon::getEnabled), params.get("enabled"));
        if (params.containsKey("type") && Objects.equals(params.get("type"), CommonNumConstants.NUM_ZERO)) {
            queryWrapper.ne(MybatisPlusUtil.toColumns(Coupon::getTemplateId), "");
        }
        if(params.containsKey("type") && Objects.equals(params.get("type"), CommonNumConstants.NUM_ONE)){
            queryWrapper.eq(MybatisPlusUtil.toColumns(Coupon::getTemplateId), "");
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
    public void updateUseCount(String couponId, int useCount) {
        UpdateWrapper<Coupon> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, couponId);
        updateWrapper.set(MybatisPlusUtil.toColumns(Coupon::getUseCount), useCount);
        update(updateWrapper);
    }

    /**
     * xxlJob任务管理器定时修改过期优惠券的状态
     */
    @Override
    public void setStateByCoupon() {
        UpdateWrapper<Coupon> updateWrapper = new UpdateWrapper<>();
        // 取优惠券
        updateWrapper.ne(MybatisPlusUtil.toColumns(Coupon::getTemplateId), "");
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
