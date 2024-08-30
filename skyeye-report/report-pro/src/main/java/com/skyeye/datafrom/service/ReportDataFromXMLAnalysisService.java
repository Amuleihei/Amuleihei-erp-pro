/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.datafrom.entity.ReportDataFromXMLAnalysis;

import java.util.List;

/**
 * @ClassName: ReportDataFromXMLAnalysisService
 * @Description: XML数据对应的解析信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:52
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ReportDataFromXMLAnalysisService extends SkyeyeBusinessService<ReportDataFromXMLAnalysis> {

    void saveList(String objectId, List<ReportDataFromXMLAnalysis> beans);

    void deleteByObjectId(String objectId);

    List<ReportDataFromXMLAnalysis> selectByObjectId(String objectId);

}
