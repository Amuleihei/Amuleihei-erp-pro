/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface StatisticsDao {

	public List<Map<String, Object>> queryWarehousingDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryOutgoingDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryInComimgDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> querySalesDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryCustomerReconciliationDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> querySupplierReconciliationDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryInComimgAllDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> querySalesAllDetails(Map<String, Object> params, PageBounds pageBounds) throws Exception;

}
