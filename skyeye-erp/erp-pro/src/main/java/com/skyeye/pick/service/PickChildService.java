/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pick.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.pick.entity.PickChild;

import java.util.List;

/**
 * @ClassName: PickChildService
 * @Description: 物料单子单据服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/30 9:01
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface PickChildService extends SkyeyeBusinessService<PickChild> {

    void deleteByParentId(String pickId);

    List<PickChild> selectByParentId(String pickId);

    void saveList(String pickId, List<PickChild> pickChildList, String userId);

    List<PickChild> queryPickChildListByParentIds(List<String> ids);
}
