/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.accessory.service;

import com.skyeye.accessory.entity.ApplyLink;
import com.skyeye.base.business.service.SkyeyeLinkDataService;

import java.util.List;

/**
 * @ClassName: ApplyLinkService
 * @Description: 配件申请单配件信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/17 17:01
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface ApplyLinkService extends SkyeyeLinkDataService<ApplyLink> {

    /**
     * 计算单据信息的总价
     *
     * @param applyLinkList
     * @return
     */
    String calcOrderAllTotalPrice(List<ApplyLink> applyLinkList);

}
