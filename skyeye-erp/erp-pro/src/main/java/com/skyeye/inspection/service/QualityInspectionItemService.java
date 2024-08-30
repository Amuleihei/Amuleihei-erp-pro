/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.inspection.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.inspection.entity.QualityInspectionItem;

import java.util.List;

/**
 * @ClassName: QualityInspectionItemService
 * @Description: 质检单子单据服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/22 9:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface QualityInspectionItemService extends SkyeyeBusinessService<QualityInspectionItem> {

    void saveList(String parentId, List<QualityInspectionItem> beans);

    void deleteByParentId(String parentId);

    List<QualityInspectionItem> selectByParentId(String parentId);

    List<QualityInspectionItem> selectByParentId(List<String> parentId);

}
