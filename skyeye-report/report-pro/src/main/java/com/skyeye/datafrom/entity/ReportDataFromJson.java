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

import java.util.List;

/**
 * @ClassName: ReportDataFromJson
 * @Description: JSON格式的数据来源
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/3 21:39
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "report_data_from_json", autoResultMap = true)
@ApiModel("JSON格式的数据来源")
public class ReportDataFromJson extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "from_id")
    @Property(value = "来源id")
    private String fromId;

    @TableField(value = "json_content")
    @ApiModelProperty(value = "json串", required = "required")
    private String jsonContent;

    @TableField(exist = false)
    @ApiModelProperty(value = "解析的数据")
    private List<ReportDataFromJsonAnalysis> analysisList;

}
