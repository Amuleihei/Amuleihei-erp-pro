/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.classenum;

import cn.hutool.core.util.StrUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import com.skyeye.common.constans.CommonNumConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @ClassName: FileType
 * @Description: 文件类型
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/26 22:37
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FileType implements SkyeyeEnumClass {

    IMAGE_IS_PNG("png", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_JPG("jpg", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_XBM("xbm", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_BMP("bmp", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_WEBP("webp", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_JPEG("jpeg", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_SVGZ("svgz", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_GIT("git", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_ICO("ico", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_TIFF("tiff", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_SVG("svg", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_JIFF("jiff", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_PJPEG("pjpeg", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_PJP("pjp", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),
    IMAGE_IS_TIF("tif", CommonNumConstants.NUM_ONE, StrUtil.EMPTY, false, false),

    OFFICE_IS_DOCX("docx", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/doc.png", false, false),
    OFFICE_IS_DOC("doc", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/doc.png", false, false),
    OFFICE_IS_XLS("xls", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/xls.png", false, false),
    OFFICE_IS_XLSX("xlsx", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/xls.png", false, false),
    OFFICE_IS_PPT("ppt", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/ppt.png", false, false),
    OFFICE_IS_PPTX("pptx", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/pptx.png", false, false),
    OFFICE_IS_WPS("wps", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/wps-icon.png", false, false),
    OFFICE_IS_ET("et", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/ppt.png", false, false),
    OFFICE_IS_DPS("dps", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/xls.png", false, false),
    OFFICE_IS_CSV("csv", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/csv.png", false, false),
    OFFICE_IS_PDF("pdf", CommonNumConstants.NUM_TWO, "../../assets/images/cloud/pdf.png", false, false),

    VEDIO_IS_MP4("mp4", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),
    VEDIO_IS_RM("rm", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),
    VEDIO_IS_RMVB("rmvb", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),
    VEDIO_IS_WMV("wmv", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),
    VEDIO_IS_AVI("avi", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),
    VEDIO_IS3GP("3gp", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),
    VEDIO_IS_MKV("mkv", CommonNumConstants.NUM_THREE, StrUtil.EMPTY, false, false),

    PACKAGE_IS_ZIP("zip", CommonNumConstants.NUM_FOUR, StrUtil.EMPTY, false, false),
    PACKAGE_IS_RAR("rar", CommonNumConstants.NUM_FOUR, StrUtil.EMPTY, false, false),

    ACE_IS_TXT("txt", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/txt.png", false, false),
    ACE_IS_SQL("sql", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/sql-icon.png", false, false),
    ACE_IS_JAVA("java", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/java-icon.png", false, false),
    ACE_IS_CSS("css", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/css-icon.png", false, false),
    ACE_IS_HTML("html", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/html.png", false, false),
    ACE_IS_HTM("htm", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/html.png", false, false),
    ACE_IS_JSON("json", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/json.png", false, false),
    ACE_IS_JS("js", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/js.png", false, false),
    ACE_IS_TPL("tpl", CommonNumConstants.NUM_FIVE, "../../assets/images/cloud/tpl.png", false, false),

    EPUB_IS("epub", CommonNumConstants.NUM_SIX, StrUtil.EMPTY, false, false);

    private String key;

    // 1.图片  2.办公文件  3.视频  4.压缩包  5.普通文件  6.电子书
    private int type;

    private String defaultLogoIcon;

    private Boolean show;

    private Boolean isDefault;

    /**
     * 根据文件后缀获取封面logo
     *
     * @param fileExt 文件后缀
     * @return 封面logo
     */
    public static String getIconByFileExt(String fileExt) {
        FileType item = Arrays.stream(FileType.values())
            .filter(bean -> fileExt.equalsIgnoreCase(bean.getKey())).findFirst().orElse(null);
        if (item == null) {
            return StrUtil.EMPTY;
        }
        return item.getDefaultLogoIcon();
    }

    /**
     * 判断是否是文件系统允许的文件后缀类型
     *
     * @param fileExt 文件后缀
     * @param type    文件类型
     * @return false:系统不允许的文件类型；true:系统允许的文件类型
     */
    public static Boolean judgeIsAllowedFileType(String fileExt, int type) {
        FileType item = Arrays.stream(FileType.values())
            .filter(bean -> fileExt.equalsIgnoreCase(bean.getKey()) && type == bean.getType()).findFirst().orElse(null);
        if (item == null) {
            return false;
        }
        return true;
    }

}
