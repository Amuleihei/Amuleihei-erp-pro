package com.skyeye.eve.myquestionbank.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;



/**
 * @ClassName: MatrixMultipleChoice
 * @Description: 矩阵单选题信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/5 15:18
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@TableName(value = "MatrixMultipleChoice")
@ApiModel(value = "矩阵单选实体类")
public class MatrixMultipleChoice extends CommonInfo {

    @TableField("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "belong_answer_id")
    @ApiModelProperty(value = "归属回答id")
    private String belong_answer_id;

    @TableField(value = "belong_id")
    @ApiModelProperty(value = "所属id")
    private String belong_id;

    @TableField(value = "qu_id")
    @ApiModelProperty(value = "所属题")
    private String qu_id;

    @TableField(value = "visibility")
    @ApiModelProperty(value = "是否显示 0不显示 1显示")
    private Integer visibility;

    @TableField(value = "qu_col_id")
    @ApiModelProperty(value = "所属题列")
    private String qu_col_id;

    @TableField(value = "qu_row_id")
    @ApiModelProperty(value = "所属题行")
    private String qu_row_id;
}