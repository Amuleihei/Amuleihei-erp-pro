/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.tenant.entity.TenantAppBuyOrderNum;

import java.util.List;

/**
 * @ClassName: TenantAppBuyOrderNumService
 * @Description: 订单-购买租户数量管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/30 16:17
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TenantAppBuyOrderNumService extends SkyeyeBusinessService<TenantAppBuyOrderNum> {

    void saveList(String parentId, List<TenantAppBuyOrderNum> beans);

    void deleteByParentId(String parentId);

    List<TenantAppBuyOrderNum> selectByParentId(String parentId);

}
