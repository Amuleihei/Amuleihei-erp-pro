/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.service.impl;


import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.eve.question.dao.DwQuestionDao;
import com.skyeye.eve.question.entity.DwQuestion;
import com.skyeye.eve.question.service.DwQuestionService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DwQuestionServiceImpl
 * @Description: 问题表服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "问题表", groupName = "问题表", manageShow = false)
public class DwQuestionServiceImpl extends SkyeyeBusinessServiceImpl<DwQuestionDao, DwQuestion> implements DwQuestionService {

}

