/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.echarts.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: ReportModel
 * @Description: 模型版本实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 9:51
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "report_model", autoResultMap = true)
@ApiModel("Echarts模型实体类")
@ExcelTarget("ReportModel")
public class ReportModel extends CommonInfo {

    @TableId("id")
    @Property("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("history_id")
    @Property(value = "历史导入关联id")
    private String historyId;

    @TableField("model_code")
    @Property(value = "模型code")
    private String modelCode;

    @TableField("default_width")
    @Property(value = "默认宽度")
    @Excel(name = "默认宽度", width = 10, isImportField = "true_st", orderNum = "2")
    private String defaultWidth;

    @TableField("default_height")
    @Property(value = "默认高度")
    @Excel(name = "默认高度", width = 10, isImportField = "true_st", orderNum = "3")
    private String defaultHeight;

    @TableField("min_width")
    @Property(value = "最小宽度")
    @Excel(name = "最小宽度", width = 10, isImportField = "true_st", orderNum = "4")
    private String minWidth;

    @TableField("min_height")
    @Property(value = "最小高度")
    @Excel(name = "最小高度", width = 10, isImportField = "true_st", orderNum = "5")
    private String minHeight;

    @TableField("default_bg_color")
    @Property(value = "默认背景色")
    @Excel(name = "默认背景色", width = 10, isImportField = "true_st", orderNum = "6")
    private String defaultBgColor;

    @TableField("bg_transparency")
    @Property(value = "透明度")
    @Excel(name = "透明度", width = 10, isImportField = "true_st", orderNum = "7")
    private String bgTransparency;

    @TableField("logo_path")
    @Property(value = "logo地址")
    @Excel(name = "LOGO", type = 2, width = 10, isImportField = "true_st", imageType = 1)
    private String logoPath;

    @TableField("software_version")
    @Property(value = "版本")
    private Integer softwareVersion;

    @TableField("state")
    @Property(value = "状态，参考#ReportModelState")
    private Integer state;


}
