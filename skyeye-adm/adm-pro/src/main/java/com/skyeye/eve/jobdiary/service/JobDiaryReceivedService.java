/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.jobdiary.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.jobdiary.entity.JobDiaryReceived;

import java.util.List;

/**
 * @ClassName: JobDiaryReceivedService
 * @Description: 工作日志接收人服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/24 13:40
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface JobDiaryReceivedService extends SkyeyeBusinessService<JobDiaryReceived> {

    void deleteByDiaryId(String diaryId);

    void save(String diaryId, List<String> receivedIdList);

    List<String> queryByDiaryId(String diaryId);

}
