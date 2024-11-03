package com.skyeye.order.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderCommentType implements SkyeyeEnumClass {
    CUSTOMERFiRST(0, "客户评价", true, false),
    CUSTOMERLATER(1, "客户追评", true, false),
    MERCHANT(2, "商家回复", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
