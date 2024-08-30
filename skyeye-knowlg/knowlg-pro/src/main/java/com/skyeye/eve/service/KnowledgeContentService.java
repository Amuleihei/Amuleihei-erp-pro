/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.KnowledgeContent;

/**
 * @ClassName: KnowledgeContentService
 * @Description: 知识库管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/21 15:30
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface KnowledgeContentService extends SkyeyeBusinessService<KnowledgeContent> {

    void queryAllKnowledgeContentList(InputObject inputObject, OutputObject outputObject);

    void editKnowledgeContentToCheck(InputObject inputObject, OutputObject outputObject);

    void queryAllPassKnowledgeContentList(InputObject inputObject, OutputObject outputObject);

    void queryEightPassKnowlgList(InputObject inputObject, OutputObject outputObject);
}
