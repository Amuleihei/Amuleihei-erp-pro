/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.purchase.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OrderArrivalState
 * @Description: 采购订单/整单委外单到货状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/31 11:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderArrivalState implements SkyeyeEnumClass {

    NOT_NEED_ARRIVAL(1, "无需到货", "purple", true, true),
    NEED_ARRIVAL(2, "待到货", "blue", true, false),
    PARTIAL_ARRIVAL(3, "部分到货", "orange", true, false),
    COMPLATE_ARRIVAL(4, "全部到货", "green", true, false);


    private Integer key;

    private String value;

    private String color;

    private Boolean show;

    private Boolean isDefault;

}
