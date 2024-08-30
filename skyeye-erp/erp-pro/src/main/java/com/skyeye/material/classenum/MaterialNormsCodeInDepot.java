/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: MaterialNormsCodeInDepot
 * @Description: 商品规格一物一码库存状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/25 11:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MaterialNormsCodeInDepot implements SkyeyeEnumClass {

    NOT_IN_STOCK(1, "未入库", true, false),
    WAREHOUSING(2, "入库", true, false),
    OUTBOUND(3, "出库", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getName(Integer state) {
        for (MaterialNormsCodeInDepot bean : MaterialNormsCodeInDepot.values()) {
            if (state == bean.getKey()) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }
}
