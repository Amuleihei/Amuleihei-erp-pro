/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.machinprocedure.classenum;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MachinProcedureFarmState
 * @Description: 车间任务状态枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/24 14:51
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MachinProcedureFarmState implements SkyeyeEnumClass {

    WAIT_RECEIVE("waitReceive", "待接收", "red", true, false),
    WAIT_EXECUTED("waitExecuted", "待执行", "blue", true, false),
    PARTIAL_COMPLETION("partialCompletion", "部分完成", "orange", true, false),
    ALL_COMPLETED("allCompleted", "全部完成", "green", true, false),
    EXCESS_COMPLETED("excessCompleted", "超额完成", "lightgreen", true, false);

    private String key;

    private String value;

    private String color;

    private Boolean show;

    private Boolean isDefault;

    public static Map<String, Object> getMation(String type) {
        for (MachinProcedureFarmState bean : MachinProcedureFarmState.values()) {
            if (StrUtil.equals(bean.getKey(), type)) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", bean.getKey());
                result.put("name", bean.getValue());
                return result;
            }
        }
        return MapUtil.newHashMap();
    }

}
