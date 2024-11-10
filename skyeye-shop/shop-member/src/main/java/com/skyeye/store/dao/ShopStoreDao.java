/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.dao;

import com.skyeye.store.entity.ShopStore;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: StoreDao
 * @Description: 门店管理数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 12:34
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopStoreDao extends SkyeyeBaseMapper<ShopStore> {

    List<Map<String, Object>> queryOnlineAppointmentMation(@Param("onlineDay") String onlineDay, @Param("list") List<String> onlineTime);

}
