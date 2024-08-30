/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.car.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CarAttribute
 * @Description: 车辆属性枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/9 12:12
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CarAttribute implements SkyeyeEnumClass {

    FIXED(1, "固定", true, true),
    SELF_OPERATED(2, "自营", true, false),
    FIXED_LEASE_IN(3, "固定租入", true, false),
    TEMPORARY_RENTAL(4, "临时租入", true, false),
    AFFILIATION(5, "挂靠", true, false),
    FIXED_LEASE_OUT(6, "固定租出", true, false),
    TEMPORARY_LEASE_OUT(7, "临时租出", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
