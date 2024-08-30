package com.skyeye.eve.radio.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.eve.radio.dao.DwQuRadioDao;
import com.skyeye.eve.radio.entity.DwQuRadio;
import com.skyeye.eve.radio.service.DwQuRadioService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DwQuRadioServiceImpl
 * @Description: 单选题选项服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "单选题选项", groupName = "单选题选项", manageShow = false)
public class DwQuRadioServiceImpl extends SkyeyeBusinessServiceImpl<DwQuRadioDao, DwQuRadio> implements DwQuRadioService {
}
