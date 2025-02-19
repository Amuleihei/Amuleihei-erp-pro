/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye-report
 ******************************************************************************/

package com.skyeye.page.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.page.entity.ReportPage;

/**
 * @ClassName: ReportPageService
 * @Description: 报表页面信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/26 17:43
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
public interface ReportPageService extends SkyeyeBusinessService<ReportPage> {

    void editReportPageContentById(InputObject inputObject, OutputObject outputObject);
}
