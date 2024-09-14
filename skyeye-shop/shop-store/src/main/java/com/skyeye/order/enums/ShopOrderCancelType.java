/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.order.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ShopOrderCancelType
 * @Description: 订单取消类型
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/8 10:39
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ShopOrderCancelType implements SkyeyeEnumClass {

    PAY_TIMEOUT(1, "超时未支付", true, false),
    AFTER_SALE_CLOSE(2, "退款关闭", true, false),
    MEMBER_CANCEL(3, "买家取消", true, false),
    COMBINATION_CLOSE(4, "拼团关闭", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
