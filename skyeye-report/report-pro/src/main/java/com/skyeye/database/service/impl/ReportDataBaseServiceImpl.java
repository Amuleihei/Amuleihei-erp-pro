/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye-report
 ******************************************************************************/

package com.skyeye.database.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.ReportConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.database.dao.ReportDataBaseDao;
import com.skyeye.database.entity.DataBase;
import com.skyeye.database.service.ReportDataBaseService;
import com.skyeye.eve.entity.ReportDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReportDataBaseServiceImpl
 * @Description: 数据库管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "数据库管理", groupName = "数据库管理")
public class ReportDataBaseServiceImpl extends SkyeyeBusinessServiceImpl<ReportDataBaseDao, DataBase> implements ReportDataBaseService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.getReportDataBaseList(commonPageInfo);
        beans.forEach(bean -> {
            String driverClass = bean.get("driverClass").toString();
            String poolClass = bean.get("poolClass").toString();
            bean.put("dataType", ReportConstants.DataBaseMation.getTypeByDricerClass(driverClass));
            bean.put("poolClassName", ReportConstants.PoolMation.getTitleByPoolClass(poolClass));
        });
        return beans;
    }

    @Override
    public void validatorEntity(DataBase entity) {
        super.validatorEntity(entity);
        entity.setDriverClass(ReportConstants.DataBaseMation.getDricerClassByType(entity.getDataType()));
        entity.setQueryerClass(ReportConstants.DataBaseMation.getQueryerClassByType(entity.getDataType()));
        entity.setPoolClass(ReportConstants.PoolMation.getPoolClassByType(entity.getPoolClassType()));
    }

    @Override
    public DataBase selectById(String id) {
        DataBase dataBase = super.selectById(id);
        dataBase.setDataType(ReportConstants.DataBaseMation.getTypeByDricerClass(dataBase.getDriverClass()));
        dataBase.setPoolClassType(ReportConstants.PoolMation.getTypeByPoolClass(dataBase.getPoolClass()));
        return dataBase;
    }

    /**
     * 获取数据库对象
     *
     * @param dataBaseId 数据库id
     * @return
     */
    @Override
    public ReportDataSource getReportDataSource(String dataBaseId) {
        // 获取数据源信息
        DataBase dataBase = selectById(dataBaseId);
        Map<String, Object> options = new HashMap<>();
        if (CollectionUtil.isNotEmpty(dataBase.getOptions())) {
            dataBase.getOptions().stream().forEach(bean -> {
                options.put(bean.get("configKey").toString(), bean.get("configValue").toString());
            });
        }
        return new ReportDataSource(
            dataBaseId,
            dataBase.getDriverClass(),
            dataBase.getJdbcUrl(), dataBase.getUser(), dataBase.getPassword(),
            dataBase.getQueryerClass(),
            dataBase.getPoolClass(),
            options);
    }

    @Override
    public void queryAllDataBaseList(InputObject inputObject, OutputObject outputObject) {
        List<DataBase> dataBaseList = queryAllData();
        outputObject.setBeans(dataBaseList);
        outputObject.settotal(dataBaseList.size());
    }
}
