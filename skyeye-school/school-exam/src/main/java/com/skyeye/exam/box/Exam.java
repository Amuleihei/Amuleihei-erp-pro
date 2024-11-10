package com.skyeye.exam.box;


import com.baomidou.mybatisplus.annotation.TableField;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

@Data
@ApiModel("题目公共实体类")
public class Exam extends OperatorUserInfo {

    @TableField("qu_id")
    @ApiModelProperty(value = "所属题")
    private String quId;

    @TableField("belong_id")
    @ApiModelProperty(value = "所属问卷或题库", required = "required")
    private String belongId;

    @TableField("qu_title")
    @ApiModelProperty(value = "题目名称")
    private String quTitle;

    @TableField("order_by_id")
    @ApiModelProperty(value = "排序ID", required = "required")
    private Integer orderById;

    @TableField("tag")
    @ApiModelProperty(value = "标记     1题库中的题   2试卷中的题")
    private Integer tag;

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
//    private Integer logic;

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

}
