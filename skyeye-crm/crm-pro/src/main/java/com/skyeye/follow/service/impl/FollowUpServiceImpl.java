/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.follow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeTeamAuthServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.contract.service.CrmContractService;
import com.skyeye.eve.contacts.service.IContactsService;
import com.skyeye.follow.classenum.CrmFollowUpAuthEnum;
import com.skyeye.follow.dao.FollowUpDao;
import com.skyeye.follow.entity.FollowUp;
import com.skyeye.follow.service.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FollowUpServiceImpl
 * @Description: 客户回访服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/2 10:43
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "回访管理", groupName = "回访管理", teamAuth = true)
public class FollowUpServiceImpl extends SkyeyeTeamAuthServiceImpl<FollowUpDao, FollowUp> implements FollowUpService {

    @Autowired
    private IContactsService iContactsService;

    @Autowired
    private CrmContractService crmContractService;

    @Override
    public Class getAuthEnumClass() {
        return CrmFollowUpAuthEnum.class;
    }

    @Override
    public List<String> getAuthPermissionKeyList() {
        return Arrays.asList(CrmFollowUpAuthEnum.ADD.getKey(), CrmFollowUpAuthEnum.EDIT.getKey(), CrmFollowUpAuthEnum.DELETE.getKey());
    }

    @Override
    public void createPrepose(FollowUp entity) {
        Map<String, Object> business = BeanUtil.beanToMap(entity);
        String oddNumber = iCodeRuleService.getNextCodeByClassName(this.getClass().getName(), business);
        entity.setOddNumber(oddNumber);
    }

    @Override
    public QueryWrapper<FollowUp> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<FollowUp> queryWrapper = super.getQueryWrapper(commonPageInfo);
        queryWrapper.eq(MybatisPlusUtil.toColumns(FollowUp::getObjectId), commonPageInfo.getObjectId());
        return queryWrapper;
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        iContactsService.setMationForMap(beans, "contacts", "contactsMation");
        crmContractService.setMationForMap(beans, "contractId", "contractMation");
        iAuthUserService.setMationForMap(beans, "followUserId", "followUserMation");
        return beans;
    }

    @Override
    public FollowUp selectById(String id) {
        FollowUp followUp = super.selectById(id);
        // 回访人
        iAuthUserService.setDataMation(followUp, FollowUp::getFollowUserId);
        // 联系人信息
        iContactsService.setDataMation(followUp, FollowUp::getContacts);
        // 合同信息
        crmContractService.setDataMation(followUp, FollowUp::getContractId);
        return followUp;
    }

}
