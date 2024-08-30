/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.datafrom.entity.ReportDataFromSQL;

/**
 * @ClassName: ReportDataFromSQLService
 * @Description: SQL格式的数据来源服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ReportDataFromSQLService extends SkyeyeBusinessService<ReportDataFromSQL> {

    void deleteByFromId(String fromId);

    ReportDataFromSQL getByFromId(String fromId);


}
