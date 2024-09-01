/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.checkbox.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.eve.checkbox.dao.DwQuCheckboxDao;
import com.skyeye.eve.checkbox.entity.DwQuCheckbox;
import com.skyeye.eve.checkbox.service.DwQuCheckboxService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DwQuCheckboxServiceImpl
 * @Description: 多选题选项服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/16 23:20
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye-report Inc. All rights reserved.
 * 注意：本内容具体规则请参照readme执行，地址：https://gitee.com/doc_wei01/skyeye-report/blob/master/README.md
 */
@Service
@SkyeyeService(name = "答卷单选题选项", groupName = "答卷单选题选项", manageShow = false)
public class DwQuCheckboxServiceImpl extends SkyeyeBusinessServiceImpl<DwQuCheckboxDao, DwQuCheckbox> implements DwQuCheckboxService {

}
