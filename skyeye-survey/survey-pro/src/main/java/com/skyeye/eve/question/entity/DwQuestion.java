/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: DwQuestion
 * @Description:问题表实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/8 14:35
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@TableName(value = "dw_question")
@ApiModel(value = "问题表实体类")
public class DwQuestion extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("answer_input_row")
    @ApiModelProperty(value = "填空的input行")
    private Integer answerInputRow;

    @TableField("answer_input_width")
    @ApiModelProperty(value = "填空的input宽度")
    private Integer answerInputWidth;

    @TableField("belong_id")
    @ApiModelProperty(value = "所属问卷或题库", required = "required")
    private String belongId;

    @TableField("cell_count")
    @ApiModelProperty(value = "按列显示时，列数", required = "required")
    private Integer cellCount;

    @TableField("check_type")
    @ApiModelProperty(value = "说明的验证方式")
    private Integer checkType;

    @TableField("contacts_attr")
    @ApiModelProperty(value = "1关联到联系人属性  0不关联到联系人属性")
    private Integer contactsAttr;


    @TableField("contacts_field")
    @ApiModelProperty(value = "关联的联系人字段")
    private String contactsField;

    @TableField("copy_from_id")
    @ApiModelProperty(value = "如果是复制的题，则有复制于那一题")
    private String copyFromId;

    @TableField("hv")
    @ApiModelProperty(value = "1水平显示 2垂直显示", required = "required")
    private Integer hv;

    @TableField("is_required")
    @ApiModelProperty(value = "是否必答 0非必答 1必答", required = "required")
    private Integer isRequired;

    @TableField("keywords")
    @ApiModelProperty(value = "关键字")
    private String keywords;

    @TableField("order_by_id")
    @ApiModelProperty(value = "排序ID")
    private Integer orderById;

    @TableField("param_int01")
    @ApiModelProperty(value = "枚举题 枚举项数目 ,评分题起始分值")
    private Integer paramInt01;

    @TableField("param_int02")
    @ApiModelProperty(value = "评分题，最大分值")
    private Integer paramInt02;

    @TableField("parent_qu_id")
    @ApiModelProperty(value = "所属大题  只有小题才有此属性 即quTag=3的题")
    private String parentQuId;

    @TableField("qu_name")
    @ApiModelProperty(value = "题目名称")
    private String quName;

    @TableField("qu_note")
    @ApiModelProperty(value = "题目说明")
    private String quNote;

    @TableField("qu_tag")
    @ApiModelProperty(value = "是否是大小题    1默认题  2大题  3大题下面的小题", required = "required")
    private Integer quTag;

    @TableField("qu_title")
    @ApiModelProperty(value = "题干")
    private String quTitle;

    @TableField("qu_type")
    @ApiModelProperty(value = "题目类型", required = "required")
    private Integer quType;

    @TableField("rand_order")
    @ApiModelProperty(value = "选项随机排列  1随机排列 0不随机排列", required = "required")
    private Integer randOrder;

    @TableField("tag")
    @ApiModelProperty(value = "标记     1题库中的题   2问卷中的题")
    private Integer tag;

    @TableField("visibility")
    @ApiModelProperty(value = "是否显示 0不显示   1显示", required = "required")
    private Integer visibility;

    @TableField("yesno_option")
    @ApiModelProperty(value = "是非题的选项 ")
    private Integer yesno_option;

    @TableField("datetime")
    @ApiModelProperty(value = "创建时间")
    private String datetime;


}
