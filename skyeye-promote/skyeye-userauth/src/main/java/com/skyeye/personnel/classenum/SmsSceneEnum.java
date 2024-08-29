/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.classenum;

import cn.hutool.core.util.ArrayUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SmsSceneEnum
 * @Description: 短信场景枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/28 20:28
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SmsSceneEnum implements SkyeyeEnumClass {

    LOGIN(1, "user-sms-login", "用户 - 手机号登陆", true, false),
    UPDATE_MOBILE(2, "user-update-mobile", "用户 - 修改绑定的手机", true, false),
    UPDATE_PASSWORD(3, "user-update-password", "用户 - 修改密码", true, false),
    RESET_PASSWORD(4, "user-reset-password", "用户 - 忘记密码", true, false);

    private Integer key;

    /**
     * 模版编码
     */
    private String value;

    private String remark;

    private Boolean show;

    private Boolean isDefault;

    public static SmsSceneEnum getCodeByScene(Integer scene) {
        return ArrayUtil.firstMatch(sceneEnum -> sceneEnum.getKey().equals(scene),
            values());
    }

}
