/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.request.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: PurchaseRequestChildInquiry
 * @Description: 采购申请子单据是否询价的枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/21 16:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PurchaseRequestChildInquiry implements SkyeyeEnumClass {

    NOT_INQUIRY(1, "无需询价", true, true),
    INQUIRY(2, "询价", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getName(Integer type) {
        for (PurchaseRequestChildInquiry bean : PurchaseRequestChildInquiry.values()) {
            if (type == bean.getKey()) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
