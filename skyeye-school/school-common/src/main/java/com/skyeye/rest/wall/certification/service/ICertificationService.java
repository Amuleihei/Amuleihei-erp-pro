/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.wall.certification.service;

import com.skyeye.base.rest.service.IService;

import java.util.Map;

/**
 * @ClassName: ICertificationService
 * @Description: 学生认证信息
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/12 8:29
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ICertificationService extends IService {

    Map<String, Object> queryCertificationById(String id);
}
