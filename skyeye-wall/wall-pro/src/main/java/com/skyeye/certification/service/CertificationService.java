/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.certification.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.certification.entity.Certification;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;

/**
 * @ClassName: CertificationService
 * @Description: 学生认证信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/24 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CertificationService extends SkyeyeBusinessService<Certification> {
    void reviewInformation(InputObject inputObject, OutputObject outputObject);

    void queryByUserId(InputObject inputObject, OutputObject outputObject);

    List<Certification> getCertificationListByIds(List<String> userIds);

    void queryUserByStudentNumber(InputObject inputObject, OutputObject outputObject);
}