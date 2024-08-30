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
 * @ClassName: JobDiaryState
 * @Description: 日志状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/24 11:48
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum JobDiaryState implements SkyeyeEnumClass {

    NORMAL(1, "正常", true, true),
    REVOKE(2, "撤销", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getName(Integer state) {
        for (JobDiaryState bean : JobDiaryState.values()) {
            if (state.equals(bean.getKey())) {
                return bean.getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
