/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.echarts.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.echarts.entity.ReportModel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReportModelService
 * @Description: 模型版本服务接口类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/20 15:01
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ReportModelService extends SkyeyeBusinessService<ReportModel> {

    Integer queryNewMaxVersionByModelCode(String modelCode);

    List<ReportModel> queryAllMaxVersionReportModel();

}
