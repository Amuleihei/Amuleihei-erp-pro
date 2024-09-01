/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.email.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.email.entity.EmailEnclosure;

import java.util.List;

/**
 * @ClassName: EmailEnclosureService
 * @Description: 邮件附件服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/8 9:35
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface EmailEnclosureService extends SkyeyeBusinessService<EmailEnclosure> {

    void deleteByObjectId(String objectId);

    List<EmailEnclosure> queryByObjectId(String objectId);

}
