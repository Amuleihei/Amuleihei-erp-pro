/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tenant.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.tenant.entity.TenantAppBuyOrderYear;

import java.util.List;

/**
 * @ClassName: TenantAppBuyOrderYearService
 * @Description: 订单-购买应用年限管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/29 22:13
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TenantAppBuyOrderYearService extends SkyeyeBusinessService<TenantAppBuyOrderYear> {

    void saveList(String parentId, List<TenantAppBuyOrderYear> beans);

    void deleteByParentId(String parentId);

    List<TenantAppBuyOrderYear> selectByParentId(String parentId);

}
