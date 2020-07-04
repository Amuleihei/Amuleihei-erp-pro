/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface MaterialService {

	public void queryMaterialListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialUnitListToSelectByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertMaterialMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialEnabledToDisablesById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialEnabledToEnablesById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteMaterialMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialMationDetailsById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialTockByNormsIdAndDepotId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialDepotItemByNormsId(InputObject inputObject, OutputObject outputObject) throws Exception;

}
