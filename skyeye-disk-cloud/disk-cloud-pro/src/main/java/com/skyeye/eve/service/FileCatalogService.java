/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.entity.FileCatalog;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FileCatalogService
 * @Description: 文件夹管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 11:28
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface FileCatalogService extends SkyeyeBusinessService<FileCatalog> {

    void deleteByParentId(String parentId);

    void editNameById(String id, String name, String userId);

    String setParentId(String id);

    List<Map<String, Object>> queryFolderAndChildList(List<String> ids);

}
