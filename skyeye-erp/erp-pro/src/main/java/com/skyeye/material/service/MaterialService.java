/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.material.entity.Material;

/**
 * @ClassName: MaterialService
 * @Description: 商品信息管理服务接口类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:44
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MaterialService extends SkyeyeBusinessService<Material> {

    void queryMaterialListToTable(InputObject inputObject, OutputObject outputObject);

    void queryMaterialTockByNormsId(InputObject inputObject, OutputObject outputObject);

    void queryMaterialReserveList(InputObject inputObject, OutputObject outputObject);

    void queryMaterialInventoryWarningList(InputObject inputObject, OutputObject outputObject);

    void queryAllMaterialList(InputObject inputObject, OutputObject outputObject);

    /**
     * 设置为使用中
     *
     * @param id
     */
    void setUsed(String id);
}
