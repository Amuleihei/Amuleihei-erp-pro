/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.car.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.erp.service.ISupplierService;
import com.skyeye.exception.CustomException;
import com.skyeye.ifs.service.IAccountService;
import com.skyeye.tms.car.dao.CarDao;
import com.skyeye.tms.car.entity.Car;
import com.skyeye.tms.car.service.CarService;
import com.skyeye.tms.cartype.service.TmsCarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: CarServiceImpl
 * @Description: 车辆管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/9 12:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "车辆管理", groupName = "车辆管理")
public class CarServiceImpl extends SkyeyeBusinessServiceImpl<CarDao, Car> implements CarService {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private ISupplierService iSupplierService;

    @Autowired
    private TmsCarTypeService tmsCarTypeService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        iAuthUserService.setMationForMap(beans, "commonDriverId", "commonDriverMation");
        iAuthUserService.setMationForMap(beans, "auxiliaryDriverId", "auxiliaryDriverMation");
        iAccountService.setMationForMap(beans, "settlementMethod", "settlementMethodMation");
        iSupplierService.setMationForMap(beans, "objectId", "objectMation");
        tmsCarTypeService.setMationForMap(beans, "typeId", "typeMation");
        return beans;
    }

    @Override
    protected void validatorEntity(Car entity) {
        super.validatorEntity(entity);
        if (StrUtil.isNotEmpty(entity.getCommonDriverId()) && StrUtil.equals(entity.getCommonDriverId(), entity.getAuxiliaryDriverId())) {
            throw new CustomException("常用司机和辅助司机不能一致，请确认");
        }
    }

    @Override
    public void createPrepose(Car entity) {
        Map<String, Object> business = BeanUtil.beanToMap(entity);
        String oddNumber = iCodeRuleService.getNextCodeByClassName(getServiceClassName(), business);
        entity.setOddNumber(oddNumber);
    }

    @Override
    public Car selectById(String id) {
        Car car = super.selectById(id);
        iSupplierService.setDataMation(car, Car::getObjectId);
        tmsCarTypeService.setDataMation(car, Car::getTypeId);
        iAuthUserService.setDataMation(car, Car::getCommonDriverId);
        iAuthUserService.setDataMation(car, Car::getAuxiliaryDriverId);
        iAccountService.setDataMation(car, Car::getSettlementMethod);
        return car;
    }

    @Override
    public List<Car> selectByIds(String... ids) {
        List<Car> carList = super.selectByIds(ids);
        iSupplierService.setDataMation(carList, Car::getObjectId);
        tmsCarTypeService.setDataMation(carList, Car::getTypeId);
        iAuthUserService.setDataMation(carList, Car::getCommonDriverId);
        iAuthUserService.setDataMation(carList, Car::getAuxiliaryDriverId);
        iAccountService.setDataMation(carList, Car::getSettlementMethod);
        return carList;
    }

    @Override
    public void queryEnabledCarList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Car::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<Car> carList = list(queryWrapper);
        carList.forEach(tmsCarType -> {
            tmsCarType.setName(String.format(Locale.ROOT, "%s_%s", tmsCarType.getOddNumber(), tmsCarType.getPlate()));
        });
        outputObject.setBeans(carList);
        outputObject.settotal(carList.size());
    }

}
