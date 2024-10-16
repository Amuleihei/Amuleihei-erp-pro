package com.skyeye.exam.examQuestion.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import javax.xml.soap.Text;

/**
 * @ClassName: ExamQuestion
 * @Description: 问题表实体类
 * @author: skyeye云系列--lqy
 * @date: 2024/7/16 11:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "exam:question")
@TableName(value = "exam_question")
@ApiModel("问题表实体类")
public class ExamQuestion extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("qu_title")
    @ApiModelProperty(value = "题目名称", required = "required")
    private String quTitle;

    @TableField("qu_tag")
    @ApiModelProperty(value = "是否是大小题    1默认题  2大题  3大题下面的小题", required = "required")
    private Integer quTag;

    @TableField("qu_type")
    @ApiModelProperty(value = "题目类型", required = "required")
    private Integer quType;

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

    @TableField("rand_order")
    @ApiModelProperty(value = "选项随机排列  1随机排列 0不随机排列", required = "required")
    private Integer randOrder;

    @TableField("tag")
    @ApiModelProperty(value = "标记     1题库中的题   2试卷中的题")
    private Integer tag;

    @TableField("visibility")
    @ApiModelProperty(value = "是否显示 0不显示   1显示 ", required = "required")
    private Integer visibility;

    @TableField("yesno_option")
    @ApiModelProperty(value = "是非题的选项")
    private Integer yesnoOption;

    @TableField("fraction")
    @ApiModelProperty(value = "每道题的分数，不能小于1")
    private Integer fraction;

    @TableField("knowledge_ids")
    @ApiModelProperty(value = "知识点id字符串，逗号隔开")
    private String knowledgeIds;

    @TableField("file_url")
    @ApiModelProperty(value = "该题目包含的视频，音频，图片等的文件所在路径")
    private String fileUrl;

    @TableField("file_type")
    @ApiModelProperty(value = "试题类型，0.默认没有，1.视频，2.音频，3.图片", required = "required")
    private Integer fileType;

    @TableField("whether_upload")
    @ApiModelProperty(value = "是否允许拍照/上传图片选中，1.是，2.否", required = "required")
    private Integer whetherUpload;

    @TableField("is_default_answer")
    @ApiModelProperty(value = "填空题默认答案")
    private String isDefaultAnswer;

    @TableField("is_delete")
    @ApiModelProperty(value = "0表示问题已经删除，1.表示未删除，默认为1")
    private Integer isDelete;
}