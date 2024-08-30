/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.datafrom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: ReportDataFromXMLAnalysis
 * @Description: XML数据对应的解析信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/4 8:51
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "report_data_from_xml_analysis", autoResultMap = true)
@ApiModel("XML数据对应的解析信息")
public class ReportDataFromXMLAnalysis extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "xml_id")
    @Property(value = "xml数据源的id")
    private String xmlId;

    @TableField(value = "`key`")
    @ApiModelProperty(value = "xml解析后的key", required = "required")
    private String key;

    @TableField("`name`")
    @ApiModelProperty(value = "名称")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

}
