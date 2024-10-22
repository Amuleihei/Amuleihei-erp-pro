package com.skyeye.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import com.skyeye.coupon.enums.PromotionMaterialScope;
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
        // 新增/编辑优惠券的适用商品对象
        if (CollectionUtil.isNotEmpty(coupon.getCouponMaterialList())) {
            couponMaterialService.insertCouponMaterial(coupon.getId(), coupon.getCouponMaterialList(), userId);
        }
    }

    @Override
    public void getQueryWrapper(InputObject inputObject, QueryWrapper<Coupon> wrapper) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        String type = commonPageInfo.getType();
        String typeKey = MybatisPlusUtil.toColumns(Coupon::getTemplateId);
        if (type.equals(CommonNumConstants.NUM_ZERO.toString())) {
            wrapper.isNull(typeKey).or().eq(type, StrUtil.EMPTY);
        }
        if (type.equals(CommonNumConstants.NUM_ONE.toString())) {
            wrapper.isNotNull(typeKey).or().ne(typeKey, StrUtil.EMPTY);
        }
        super.getQueryWrapper(inputObject, wrapper);
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        String type = commonPageInfo.getType();
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        String typeKey = MybatisPlusUtil.toColumns(Coupon::getTemplateId);
        if (type.equals(CommonNumConstants.NUM_ZERO.toString())) {
            queryWrapper.isNull(typeKey).or().eq(type, StrUtil.EMPTY);
        }
        if (type.equals(CommonNumConstants.NUM_ONE.toString())) {
            queryWrapper.isNotNull(typeKey).or().ne(typeKey, StrUtil.EMPTY);
        }
        List<Coupon> list = list(queryWrapper);
        // 分页查询时获取数据
        return JSONUtil.toList(JSONUtil.toJsonStr(list), null);
    }

    @Override
    public void queryCouponListByState(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Coupon::getStoreId), params.get("storeId"));
        String typeKey = MybatisPlusUtil.toColumns(Coupon::getTemplateId);
        if (params.containsKey("type") && Objects.equals(params.get("type"), CommonNumConstants.NUM_ZERO)) {
            queryWrapper.isNull(typeKey).or().eq(typeKey, StrUtil.EMPTY);
        }
        if (params.containsKey("type") && Objects.equals(params.get("type"), CommonNumConstants.NUM_ONE)) {
            queryWrapper.isNotNull(typeKey).or().ne(typeKey, StrUtil.EMPTY);
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

    @Override
    public void deletePostpose(List<String> ids) {
        couponMaterialService.deleteByCouponId(ids);
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
