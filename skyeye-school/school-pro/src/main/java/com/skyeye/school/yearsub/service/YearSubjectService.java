/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.yearsub.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.subject.entity.Subject;
import com.skyeye.school.yearsub.entity.YearSubject;
import com.skyeye.school.yearsub.entity.YearSubjectBox;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: YearSubjectService
 * @Description: 学年科目服务接口层
 * @author: xqz
 * @date: 2023/8/29 16:30
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface YearSubjectService extends SkyeyeBusinessService<YearSubject> {

    /**
     * 根据专业id和学年id批量查询科目
     *
     * @param majorId      专业id
     * @param semesterList 学年id
     * @return
     */
    Map<String, List<Subject>> querySubjectList(String majorId, List<String> semesterList);

    Map<String, List<YearSubject>> queryYearSubjectList(String majorId, Integer year);

    void writeYearSubject(InputObject inputObject, OutputObject outputObject);

}
