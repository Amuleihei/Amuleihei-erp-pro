/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye-report
 ******************************************************************************/

package com.skyeye.database.service;

import com.skyeye.base.business.service.SkyeyeTeamAuthService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.database.entity.AutoDataBase;
import com.skyeye.sql.entity.AutoDataSource;

/**
 * @ClassName: AutoDataBaseService
 * @Description: 数据库管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
public interface AutoDataBaseService extends SkyeyeTeamAuthService<AutoDataBase> {

    /**
     * 获取数据库对象
     *
     * @param dataBaseId 数据库id
     * @return
     */
    AutoDataSource getReportDataSource(String dataBaseId);

    void queryAllAutoDataBaseList(InputObject inputObject, OutputObject outputObject);

    void testAutoDbConnection(InputObject inputObject, OutputObject outputObject);

    void queryAutoDataBaseTypeList(InputObject inputObject, OutputObject outputObject);

    void queryAutoPoolTypeList(InputObject inputObject, OutputObject outputObject);
}
