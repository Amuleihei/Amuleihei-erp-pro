/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.order.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ShopOrderTerminal
 * @Description: 订单来源
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/8 10:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ShopOrderTerminal implements SkyeyeEnumClass {

    UNKNOWN(0, "未知", true, false),
    WECHAT_MINI_PROGRAM(1, "微信小程序", true, false),
    WECHAT_WAP(2, "微信公众号", true, false),
    H5(3, "H5 网页", true, false),
    APP(4, "手机 App", true, false),
    PC(5, "PC端", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
