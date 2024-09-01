/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.note.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * @ClassName: NoteType
 * @Description: 笔记类型
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/26 22:31
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum NoteType implements SkyeyeEnumClass {

    EDITOR(1, "富文本编辑器", "note-1.png", true, false),
    MD(2, "markdown笔记", "note-2.png", true, false),
    WORD(3, "word笔记", "note-3.png", true, false),
    EXCEL(4, "ecxel笔记", "note-4.png", true, false);

    private Integer key;

    private String value;

    private String iconLogo;

    private Boolean show;

    private Boolean isDefault;

    public static String getIconPathByType(Integer type) {
        String iconLogo = "../../assets/images/%s";
        for (NoteType bean : NoteType.values()) {
            if (type.equals(bean.getKey())) {
                return String.format(Locale.ROOT, iconLogo, bean.getIconLogo());
            }
        }
        return StringUtils.EMPTY;
    }

}