/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.driver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.tms.driver.dao.TmsDriverDao;
import com.skyeye.tms.driver.entity.TmsDriver;
import com.skyeye.tms.driver.service.TmsDriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TmsDriverServiceImpl
 * @Description: 司机管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "司机管理", groupName = "司机管理")
public class TmsDriverServiceImpl extends SkyeyeBusinessServiceImpl<TmsDriverDao, TmsDriver> implements TmsDriverService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        iAuthUserService.setMationForMap(beans, "userId", "userMation");
        return beans;
    }

    @Override
    public TmsDriver selectById(String id) {
        TmsDriver tmsDriver = super.selectById(id);
        iAuthUserService.setDataMation(tmsDriver, TmsDriver::getUserId);
        return tmsDriver;
    }

    @Override
    public List<TmsDriver> selectByIds(String... ids) {
        List<TmsDriver> tmsDriverList = super.selectByIds(ids);
        iAuthUserService.setDataMation(tmsDriverList, TmsDriver::getUserId);
        return tmsDriverList;
    }

    @Override
    public void queryEnabledTmsDriverList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<TmsDriver> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TmsDriver::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<TmsDriver> tmsDriverList = list(queryWrapper);
        iAuthUserService.setDataMation(tmsDriverList, TmsDriver::getUserId);
        tmsDriverList.forEach(tmsCarType -> {
            tmsCarType.setId(tmsCarType.getUserId());
            Map<String, Object> userMation = tmsCarType.getUserMation();
            tmsCarType.setName(userMation.get("name").toString());
        });
        outputObject.setBeans(tmsDriverList);
        outputObject.settotal(tmsDriverList.size());
    }
}

