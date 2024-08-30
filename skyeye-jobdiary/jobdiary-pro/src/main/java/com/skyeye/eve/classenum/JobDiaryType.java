/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import com.skyeye.common.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * @ClassName: JobDiaryType
 * @Description: 日志类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/24 11:48
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum JobDiaryType implements SkyeyeEnumClass {

    DIARY_DAY(1, "日报", true, true),
    DIARY_WEEK(2, "周报", true, false),
    DIARY_MONTH(3, "月报", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getTitle(Integer type) {
        if (type.equals(DIARY_DAY.getKey())) {
            return String.format(Locale.ROOT, "%s日 %s", DateUtil.getYmdTimeAndToString(), DIARY_DAY.getValue());
        } else if (type.equals(DIARY_WEEK.getKey())) {
            return String.format(Locale.ROOT, "%s年第%s周 %s", DateUtil.getYTimeAndToString(), DateUtil.getWeekOfYear(), DIARY_WEEK.getValue());
        } else if (type.equals(DIARY_MONTH.getKey())) {
            return String.format(Locale.ROOT, "%s月 %s", DateUtil.getYmTimeAndToString(), DIARY_MONTH.getValue());
        }
        return StringUtils.EMPTY;
    }

    public static String getName(Integer state) {
        for (JobDiaryType bean : JobDiaryType.values()) {
            if (state.equals(bean.getKey())) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
