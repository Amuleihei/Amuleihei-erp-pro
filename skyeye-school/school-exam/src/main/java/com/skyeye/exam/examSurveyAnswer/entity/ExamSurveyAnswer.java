package com.skyeye.exam.examSurveyAnswer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "exam:answer")
@TableName(value = "exam_survey_answer")
@ApiModel("试卷回答信息表实体类")
public class ExamSurveyAnswer extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("survey_id")
    @ApiModelProperty(value = "问卷ID", required = "required")
    private String surveyId;

    @TableField("bg_an_date")
    @ApiModelProperty(value = "回答开始时间", required = "required")
    private Data bg_an_date;

    @TableField("end_an_date")
    @ApiModelProperty(value = "回答结束时间", required = "required")
    private Data end_an_date;

    @TableField("complete_num")
    @ApiModelProperty(value = "回答的题数")
    private Integer complete_num;

    @TableField("complete_item_num")
    @ApiModelProperty(value = "回答的题项目数 ---- 表示有些题下面会有多重回答")
    private Integer complete_item_num;

    @TableField("data_source")
    @ApiModelProperty(value = "数据来源  0网调  1录入数据 2移动数据 3导入数据", required = "required")
    private Integer data_source;

    @TableField("handle_state")
    @ApiModelProperty(value = "审核状态  0未处理 1通过 2不通过", required = "required")
    private Integer handle_state;

    @TableField("ip_addr")
    @ApiModelProperty(value = "回答者IP")
    private String ip_addr;

    @TableField("addr")
    @ApiModelProperty(value = "回答者是详细地址")
    private String addr;

    @TableField("city")
    @ApiModelProperty(value = "回答者城市 ")
    private String city;

    @TableField("is_complete")
    @ApiModelProperty(value = "是否完成  1完成 0未完成", required = "required")
    private Integer is_complete;

    @TableField("is_effective")
    @ApiModelProperty(value = "是否是有效数据  1有效  0无效", required = "required")
    private Integer is_effective;

    @TableField("qu_num")
    @ApiModelProperty(value = "回答的题数", required = "required")
    private Integer qu_num;

    @TableField("total_time")
    @ApiModelProperty(value = "用时", required = "required")
    private Float total_time;

    @TableField("create_id")
    @ApiModelProperty(value = "学生ID", required = "required")
    private String create_id;

    @TableField("state")
    @ApiModelProperty(value = "教师是否阅卷  1.否  2.是", required = "required")
    private Integer state;

    @TableField("mark_fraction")
    @ApiModelProperty(value = "最后得分")
    private Integer mark_fraction;

    @TableField("mark_people")
    @ApiModelProperty(value = "阅卷人")
    private String mark_people;

    @TableField("mark_start_time")
    @ApiModelProperty(value = "开始阅卷时间")
    private Data mark_start_time;

    @TableField("mark_end_time")
    @ApiModelProperty(value = "结束阅卷时间")
    private Data mark_end_time;
}