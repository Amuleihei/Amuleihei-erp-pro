/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contacts.service.impl;

import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.enumeration.IsDefaultEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.contacts.dao.ContactsDao;
import com.skyeye.contacts.service.ContactsService;
import com.skyeye.contacts.entity.ContactsMation;
import com.skyeye.eve.entity.object.query.BaseServerQueryDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ContactsServiceImpl
 * @Description: 客户联系人管理
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ContactsServiceImpl extends SkyeyeBusinessServiceImpl<ContactsDao, ContactsMation> implements ContactsService {

    @Autowired
    private ContactsDao contactsDao;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        BaseServerQueryDo baseServerQuery = inputObject.getParams(BaseServerQueryDo.class);
        baseServerQuery.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        if (ToolUtil.isBlank(baseServerQuery.getObjectId()) && ToolUtil.isBlank(baseServerQuery.getObjectKey())) {
            baseServerQuery.setCreateId(inputObject.getLogParams().get("id").toString());
        }
        List<Map<String, Object>> beans = contactsDao.queryContactsList(baseServerQuery);
        return beans;
    }

    @Override
    public void writePostpose(ContactsMation entity, String userId) {
        if (entity.getIsDefault() == IsDefaultEnum.IS_DEFAULT.getKey()) {
            // 如果设置为默认联系人，则修改之前的联系人为非默认
            contactsDao.setContactsIsNotDefault(entity.getObjectId(), IsDefaultEnum.NOT_DEFAULT.getKey());
            contactsDao.setContactsIsDefault(entity.getId(), IsDefaultEnum.IS_DEFAULT.getKey());
        }
    }

}
