/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.grade.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.school.grade.entity.YearSystem;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: YearSystemService
 * @Description: 年制服务接口层
 * @author: xqz
 * @date: 2023/8/18 17:48
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface YearSystemService extends SkyeyeBusinessService<YearSystem> {

    void deleteLinkListByClassId(String classId);

    List<YearSystem> queryLinkListByClassId(String classId);

    Map<String, List<YearSystem>> queryLinkListByClassId(List<String> classIdList);

}
