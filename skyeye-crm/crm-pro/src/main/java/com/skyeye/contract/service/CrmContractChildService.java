/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contract.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.contract.entity.CrmContractChild;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CrmContractChildService
 * @Description: 客户合同-商品明细服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/12 14:50
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CrmContractChildService extends SkyeyeBusinessService<CrmContractChild> {

    void saveList(String parentId, List<CrmContractChild> beans);

    void deleteByParentId(String parentId);

    List<CrmContractChild> selectByParentId(String parentId);

    Map<String, List<CrmContractChild>> selectByParentId(List<String> parentIds);

    List<CrmContractChild> getCrmContractChildList(List<String> parentIds);

    String calcOrderAllTotalPrice(List<CrmContractChild> supplierContractChildList);

}
