/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.student.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.school.student.entity.StudentParents;

import java.util.List;

/**
 * @ClassName: StudentParentsService
 * @Description: 学生父母管理服务接口层
 * @author: xqz
 * @date: 2023/8/11 20:51
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface StudentParentsService extends SkyeyeBusinessService<StudentParents> {

    void saveLinkList(String studentId, List<StudentParents> beans);

    void deleteLinkListByStudentId(String studentId);

    List<StudentParents> queryLinkListByStudentId(String studentId);
}
