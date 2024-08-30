/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.service.impl;


import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.eve.question.dao.DwAnYesnoDao;
import com.skyeye.eve.question.entity.DwAnYesno;
import com.skyeye.eve.question.service.DwAnYesnoService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DwAnYesnoServiceImpl
 * @Description: 答卷判断题服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "答卷判断题", groupName = "答卷判断题", manageShow = false)
public class DwAnYesnoServiceImpl extends SkyeyeBusinessServiceImpl<DwAnYesnoDao, DwAnYesno> implements DwAnYesnoService {

}

