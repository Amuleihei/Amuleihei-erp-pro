/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.enums;

import cn.hutool.core.util.ArrayUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PayTransferType
 * @Description: 转账类型枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 8:50
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PayTransferType implements SkyeyeEnumClass {

    ALIPAY_BALANCE(1, "支付宝余额", true, false),
    WX_BALANCE(2, "微信余额", true, false),
    BANK_CARD(3, "银行卡", true, false),
    WALLET_BALANCE(4, "钱包余额", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static PayTransferType typeOf(Integer type) {
        return ArrayUtil.firstMatch(item -> item.getKey().equals(type), values());
    }

}
