/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.email.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.email.dao.EmailEnclosureDao;
import com.skyeye.eve.email.entity.EmailEnclosure;
import com.skyeye.eve.email.service.EmailEnclosureService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: EmailEnclosureServiceImpl
 * @Description: 邮件附件服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/8 9:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "邮件附件管理", groupName = "邮件附件管理", manageShow = false)
public class EmailEnclosureServiceImpl extends SkyeyeBusinessServiceImpl<EmailEnclosureDao, EmailEnclosure> implements EmailEnclosureService {

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<EmailEnclosure> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(EmailEnclosure::getParentId), objectId);
        remove(queryWrapper);
    }

    @Override
    public List<EmailEnclosure> queryByObjectId(String objectId) {
        QueryWrapper<EmailEnclosure> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(EmailEnclosure::getParentId), objectId);
        List<EmailEnclosure> emailEnclosureList = list(queryWrapper);
        return emailEnclosureList;
    }

}
