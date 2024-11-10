/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.level.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.level.entity.ShopMemberLevel;

/**
 * @ClassName: ShopMemberLevelService
 * @Description: 会员等级服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ShopMemberLevelService extends SkyeyeBusinessService<ShopMemberLevel> {

    ShopMemberLevel getMinLevel();

    ShopMemberLevel getSimpleLevelByLevel(Integer level);

}
