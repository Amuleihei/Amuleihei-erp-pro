/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PayRefundStatusResp
 * @Description: 渠道的退款状态枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 8:40
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PayRefundStatusResp implements SkyeyeEnumClass {

    WAITING(0, "等待退款", true, false),
    SUCCESS(1, "退款成功", true, false),
    FAILURE(2, "退款失败", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
