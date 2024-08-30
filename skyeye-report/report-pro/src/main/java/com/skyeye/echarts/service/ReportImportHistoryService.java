/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.echarts.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.echarts.entity.ImportHistory;

import java.util.Map;

/**
 * @ClassName: ReportImportHistoryService
 * @Description: Echarts导入历史服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/20 14:05
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ReportImportHistoryService extends SkyeyeBusinessService<ImportHistory> {

    void importReportImportModel(InputObject inputObject, OutputObject outputObject);

    void queryAllMaxVersionReportModel(InputObject inputObject, OutputObject outputObject);
}
