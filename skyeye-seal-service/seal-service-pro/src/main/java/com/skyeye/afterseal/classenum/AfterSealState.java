/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: AfterSealState
 * @Description: 售后工单状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/10 13:23
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AfterSealState implements SkyeyeEnumClass {

    BE_DISPATCHED("beDispatched", "待派工", true, true),
    PENDING_ORDERS("pendingOrders", "待接单", true, false),
    BE_SIGNED("beSigned", "待签到", true, false),
    BE_COMPLETED("beCompleted", "待完工", true, false),
    BE_EVALUATED("beEvaluated", "待评价", true, false),
    AUDIT("audit", "待审核", true, false),
    COMPLATE("complate", "已完工", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
