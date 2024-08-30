/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.tms.car.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CarState
 * @Description: 车辆当前状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/9 12:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CarState implements SkyeyeEnumClass {

    AVAILABLE(1, "可用", true, true),
    DISPATCHED(2, "已调度", true, false),
    IN_TRANSIT(3, "在途", true, false),
    MAINTENANCE_UPKEEP(4, "维修保养", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
