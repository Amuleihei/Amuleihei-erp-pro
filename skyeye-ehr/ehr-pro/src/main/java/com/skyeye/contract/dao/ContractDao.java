/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contract.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.contract.entity.Contract;
import com.skyeye.eve.dao.SkyeyeBaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ContractDao
 * @Description: 员工合同管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 20:55
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ContractDao extends SkyeyeBaseMapper<Contract> {

    List<Map<String, Object>> queryContractList(CommonPageInfo commonPageInfo);

}
