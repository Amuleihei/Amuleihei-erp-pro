/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.radio.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.question.QuType;
import com.skyeye.eve.question.entity.Question;
import com.skyeye.eve.question.service.QuestionService;
import com.skyeye.eve.radio.dao.RadioDao;
import com.skyeye.eve.radio.entity.Radio;
import com.skyeye.eve.radio.entity.RadioBox;
import com.skyeye.eve.radio.service.RadioService;
import com.skyeye.exam.box.Exam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RadioServiceImpl
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/15 15:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "单选题", groupName = "题库管理")
public class RadioServiceImpl extends SkyeyeBusinessServiceImpl<RadioDao, Radio> implements RadioService {


}
