/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.voucher.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: VoucherState
 * @Description: 凭证状态的枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/12 22:10
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum VoucherState implements SkyeyeEnumClass {

    UN_CLUTTERED(1, "未整理", true, true),
    CLUTTERED(2, "已整理", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
