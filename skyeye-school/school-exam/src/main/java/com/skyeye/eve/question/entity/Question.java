/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.exam.examQuRadio.entity.ExamQuRadio;
import com.skyeye.exam.examQuScore.entity.ExamQuScore;
import com.skyeye.exam.examquchckbox.entity.ExamQuCheckbox;
import com.skyeye.exam.examquchencolumn.entity.ExamQuChenColumn;
import com.skyeye.exam.examquchenrow.entity.ExamQuChenRow;
import com.skyeye.exam.examqumultfillblank.entity.ExamQuMultiFillblank;
import com.skyeye.exam.examquorderby.entity.ExamQuOrderby;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: Question
 * @Description: 问题
 * @author: skyeye云系列--卫志强
 * @date: 2023/10/11 19:17
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "bank_question")
@ApiModel(value = "题目")
public class Question extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

//    @TableField("belong_id")
//    @ApiModelProperty(value = "所属问卷或题库", required = "required")
//    private String belongId;

    @TableField("qu_title")
    @ApiModelProperty(value = "题目名称")
    private String quTitle;

    @TableField("qu_type")
    @ApiModelProperty(value = "题目类型", required = "required")
    private Integer quType;

    @TableField("school_id")
    @ApiModelProperty(value = "学校Id", required = "required")
    private String schoolId;

    @TableField("grade_id")
    @ApiModelProperty(value = "年级Id", required = "required")
    private String gradeId;

    @TableField("order_by_id")
    @ApiModelProperty(value = "排序ID", required = "required")
    private Integer orderById;

    @TableField("qu_tag")
    @ApiModelProperty(value = "标记     1题库中的题   2试卷中的题")
    private Integer quTag;

    @TableField("contacts_attr")
    @ApiModelProperty(value = "1关联到联系人属性  0不关联到联系人属性")
    private Integer contactsAttr;

    @TableField("contacts_field")
    @ApiModelProperty(value = "关联的联系人字段")
    private String contactsField;

    @TableField("hv")
    @ApiModelProperty(value = "1水平显示 2垂直显示", required = "required")
    private Integer hv;

    @TableField("rand_order")
    @ApiModelProperty(value = "选项随机排列  1随机排列 0不随机排列")
    private Integer randOrder;

    @TableField("cell_count")
    @ApiModelProperty(value = "按列显示时，列数", required = "required")
    private Integer cellCount;

//    @TableField("logic")
//    @ApiModelProperty(value = "逻辑设置json串")
//    private String logic;

    @TableField("fraction")
    @ApiModelProperty(value = "每道题的分数，不能小于1")
    private Integer fraction;

//    @TableField("knowledge_ids")
//    @ApiModelProperty(value = "知识点id字符串，逗号隔开")
//    private String knowledgeIds;

    @TableField("file_url")
    @ApiModelProperty(value = "该题目包含的视频，音频，图片等的文件所在路径")
    private String fileUrl;

    @TableField("file_type")
    @Property(value = "试题类型，0.默认没有，1.视频，2.音频，3.图片 ")
    private Integer fileType;

    @TableField("whether_upload")
    @ApiModelProperty(value = "是否允许拍照/上传图片选中，1.是，2.否", required = "required")
    private Integer whetherUpload;

    @TableField("visibility")
    @ApiModelProperty(value = "是否显示  0不显示  1显示", required = "required")
    private Integer visibility;

    @TableField(exist = false)
    @ApiModelProperty(value = "单选题选项信息", required = "json")
    private List<ExamQuRadio> radioTd;

    @TableField(exist = false)
    @ApiModelProperty(value = "评分题选项信息", required = "json")
    private List<ExamQuScore> scoreTd;

    @TableField(exist = false)
    @ApiModelProperty(value = "多选题选项信息", required = "json")
    private List<ExamQuCheckbox> checkboxTd;

    @TableField(exist = false)
    @ApiModelProperty(value = "矩阵题-列选项信息", required = "json")
    private List<ExamQuChenColumn> columnTd;

    @TableField(exist = false)
    @ApiModelProperty(value = "矩阵题-行选项信息", required = "json")
    private List<ExamQuChenRow> rowTd;

//    @TableField(exist = false)
//    @ApiModelProperty(value = "矩阵题-列选项信息", required = "json")
//    private List<ExamQuChenOption> chenoptionTd;

    @TableField(exist = false)
    @ApiModelProperty(value = "多行填空题选项信息", required = "json")
    private List<ExamQuMultiFillblank> multifillblankTd;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序题选项信息", required = "json")
    private List<ExamQuOrderby> orderbyTd;

}
