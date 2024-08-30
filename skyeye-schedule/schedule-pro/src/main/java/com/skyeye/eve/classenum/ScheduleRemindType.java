/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: ScheduleRemindType
 * @Description: 日程提醒时间所属类型
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/24 17:47
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ScheduleRemindType implements SkyeyeEnumClass {

    NO_REMINDER(0, "无需提醒", false, false),
    START(1, "日程开始时", true, true),
    FIVE_MINUTE(2, "5分钟前", true, false),
    FIFTEEN_MINUTE(3, "15分钟前", true, false),
    THIRTY_MINUTE(4, "30分钟前", true, false),
    ONE_HOUR(5, "1小时前", true, false),
    TWO_HOUR(6, "2小时前", true, false),
    ONE_DAY(7, "1天前", true, false),
    TWO_DAY(8, "2天前", true, false),
    ONE_WEEK(9, "一周前", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getName(Integer state) {
        for (ScheduleRemindType bean : ScheduleRemindType.values()) {
            if (state.equals(bean.getKey())) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
