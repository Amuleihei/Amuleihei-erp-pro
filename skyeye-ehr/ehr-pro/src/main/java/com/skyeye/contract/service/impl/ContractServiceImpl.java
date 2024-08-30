/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contract.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.contract.dao.ContractDao;
import com.skyeye.contract.entity.Contract;
import com.skyeye.contract.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ContractServiceImpl
 * @Description: 员工合同管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "员工合同信息", groupName = "员工合同信息", teamAuth = true)
public class ContractServiceImpl extends SkyeyeBusinessServiceImpl<ContractDao, Contract> implements ContractService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryContractList(commonPageInfo);
        return beans;
    }

    @Override
    public Contract selectById(String id) {
        Contract contract = super.selectById(id);
        iSysDictDataService.setDataMation(contract, Contract::getTypeId);
        iSysDictDataService.setDataMation(contract, Contract::getMoldId);
        return contract;
    }

}
