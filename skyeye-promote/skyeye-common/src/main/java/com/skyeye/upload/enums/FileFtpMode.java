/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.enums;

import cn.hutool.extra.ftp.FtpMode;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FileFtpMode
 * @Description: FTP上传模式枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2024/11/10 10:03
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FileFtpMode implements SkyeyeEnumClass {

    ACTIVE(FtpMode.Active.toString(), "主动模式", true, false),
    PASSIVE(FtpMode.Passive.toString(), "被动模式", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static FileFtpMode getByKey(String key) {
        for (FileFtpMode fileFtpMode : FileFtpMode.values()) {
            if (fileFtpMode.getKey().equals(key)) {
                return fileFtpMode;
            }
        }
        return null;
    }

    public static FtpMode getFtpModeByKey(String key) {
        for (FileFtpMode fileFtpMode : FileFtpMode.values()) {
            if (fileFtpMode.getKey().equals(key)) {
                return FtpMode.valueOf(fileFtpMode.getKey());
            }
        }
        return null;
    }

}
