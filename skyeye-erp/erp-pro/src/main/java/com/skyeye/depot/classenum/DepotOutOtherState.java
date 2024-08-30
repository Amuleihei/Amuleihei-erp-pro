/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.depot.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DepotOutOtherState
 * @Description: 仓库出库单，领料/补料确认状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/31 11:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DepotOutOtherState implements SkyeyeEnumClass {

    NEED_CONFIRM(1, "待确认", "blue", true, false),
    PARTIAL_CONFIRM(2, "部分确认", "orange", true, false),
    COMPLATE_CONFIRM(3, "全部确认", "green", true, false);

    private Integer key;

    private String value;

    private String color;

    private Boolean show;

    private Boolean isDefault;

}
