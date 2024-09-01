/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.service.impl;


import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.eve.question.dao.DwSurveyMailInviteDao;
import com.skyeye.eve.question.entity.DwSurveyMailInvite;
import com.skyeye.eve.question.service.DwSurveyMailInviteService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DwSurveyMailInviteServiceImpl
 * @Description: 问卷选择发送邮件调查时的邮件服务服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "问卷选择发送邮件调查时的邮件服务", groupName = "问卷选择发送邮件调查时的邮件服务", manageShow = false)
public class DwSurveyMailInviteServiceImpl extends SkyeyeBusinessServiceImpl<DwSurveyMailInviteDao, DwSurveyMailInvite> implements DwSurveyMailInviteService {

}
