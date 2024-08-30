/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.family.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.family.dao.FamilyDao;
import com.skyeye.family.entity.Family;
import com.skyeye.family.service.FamilyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FamilyServiceImpl
 * @Description: 员工家庭情况管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "员工家庭成员", groupName = "员工家庭成员", teamAuth = true)
public class FamilyServiceImpl extends SkyeyeBusinessServiceImpl<FamilyDao, Family> implements FamilyService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryFamilyList(commonPageInfo);
        return beans;
    }

    @Override
    public Family selectById(String id) {
        Family family = super.selectById(id);
        iSysDictDataService.setDataMation(family, Family::getCardType);
        iSysDictDataService.setDataMation(family, Family::getPoliticId);
        iSysDictDataService.setDataMation(family, Family::getRelationshipId);
        return family;
    }

}
