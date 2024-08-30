/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.cartype.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.tms.cartype.dao.TmsCarTypeDao;
import com.skyeye.tms.cartype.entity.TmsCarType;
import com.skyeye.tms.cartype.service.TmsCarTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: TmsCarTypeServiceImpl
 * @Description: 车辆类型服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "车辆类型", groupName = "车辆类型")
public class TmsCarTypeServiceImpl extends SkyeyeBusinessServiceImpl<TmsCarTypeDao, TmsCarType> implements TmsCarTypeService {

    @Override
    public void createPrepose(TmsCarType tmsCarType) {
        Map<String, Object> business = BeanUtil.beanToMap(tmsCarType);
        String oddNumber = iCodeRuleService.getNextCodeByClassName(getServiceClassName(), business);
        tmsCarType.setOddNumber(oddNumber);
    }

    @Override
    public void queryEnabledTmsCarType(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<TmsCarType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TmsCarType::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<TmsCarType> tmsCarTypeList = list(queryWrapper);
        tmsCarTypeList.forEach(tmsCarType -> {
            tmsCarType.setName(String.format(Locale.ROOT, "%s_%s", tmsCarType.getOddNumber(), tmsCarType.getName()));
        });
        outputObject.setBeans(tmsCarTypeList);
        outputObject.settotal(tmsCarTypeList.size());
    }

}

