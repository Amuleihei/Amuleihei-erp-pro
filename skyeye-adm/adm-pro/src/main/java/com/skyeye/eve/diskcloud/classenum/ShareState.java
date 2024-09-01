/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.diskcloud.classenum;

import cn.hutool.core.util.StrUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @ClassName: ShareState
 * @Description: 文件分享状态
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/26 22:37
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ShareState implements SkyeyeEnumClass {

    NORMAL(1, "正常", true, false),
    LOSE_EFFICACY(2, "失效", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static String getTypeName(Integer key) {
        ShareState item = Arrays.stream(ShareState.values())
            .filter(bean -> key.equals(bean.getKey())).findFirst().orElse(null);
        if (item == null) {
            return StrUtil.EMPTY;
        }
        return item.getValue();
    }

}
