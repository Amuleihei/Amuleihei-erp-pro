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
 * @ClassName: DwSurveyDirectory
 * @Description:问卷目录及问卷实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/8 14:35
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@TableName(value = "dw_survey_directory")
@ApiModel(value = "问卷目录及问卷实体类")
public class DwSurveyDirectory extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("survey_name")
    @ApiModelProperty(value = "问卷名称", required = "required")
    private String surveyName;

    @TableField("survey_note")
    @ApiModelProperty(value = "问卷说明")
    private String surveyNote;

    @TableField("survey_qu_num")
    @ApiModelProperty(value = "问卷下面有多少题目数", required = "required")
    private Integer surveyQuNum;

    @TableField("survey_state")
    @ApiModelProperty(value = "问卷状态  0默认设计状态  1执行中 2结束 ", required = "required")
    private Integer surveyState;

    @TableField("real_start_time")
    @ApiModelProperty(value = "回答的题项目数 ---- 表示有些题下面会有多重回答")
    private String realStartTime;

    @TableField("real_end_time")
    @ApiModelProperty(value = "实际结束时间")
    private String realEndTime;

    @TableField("survey_model")
    @ApiModelProperty(value = "问卷所属的问卷模块   1问卷模块  2试题模块", required = "required")
    private Integer surveyModel;

    @TableField("an_item_least_num")
    @ApiModelProperty(value = "可以回答的最少选项数目", required = "required")
    private Integer anItemLeastNum;

    @TableField("an_item_most_num")
    @ApiModelProperty(value = "可以回答的最多选项数目", required = "required")
    private Integer anItemMostNum;

    @TableField("effective")
    @ApiModelProperty(value = "问卷有效性限制 ---1不限制,2使用Cookie技术,3使用来源IP检测,4 每台电脑或手机只能答一次 ", required = "required")
    private Integer ceffective;

    @TableField("effective_ip")
    @ApiModelProperty(value = "每个IP只能答一次 1是 0否", required = "required")
    private Integer effectiveIp;

    @TableField("effective_time")
    @ApiModelProperty(value = "如果每个IP能答多次，每隔多长时间可以答一次(分钟)", required = "required")
    private Integer effectiveTime;

    @TableField("yn_end_num")
    @ApiModelProperty(value = "是否依据收到的份数结束   1是   0否", required = "required")
    private Integer ynEndNum;

    @TableField("end_num")
    @ApiModelProperty(value = "依据收到的份数", required = "required")
    private Integer endNum;

    @TableField("yn_end_time")
    @ApiModelProperty(value = "是否依据时间结束  1是   0否", required = "required")
    private Integer ynEndTime;

    @TableField("end_time")
    @ApiModelProperty(value = "手动设置的结束时间")
    private String endTime;

    @TableField("rule")
    @ApiModelProperty(value = "调查规则 1公开, 2私有, 3令牌(表示启用访问密码)", required = "required")
    private Integer rule;

    @TableField("rule_code")
    @ApiModelProperty(value = "rule为3时的令牌密码")
    private String ruleCode;

    @TableField("answer_num")
    @ApiModelProperty(value = "回答次数", required = "required")
    private Integer answerNum;

    @TableField("refresh")
    @ApiModelProperty(value = "有重复回答启用验证码   1是  0否", required = "required")
    private Integer refresh;

    @TableField("excerpt_num")
    @ApiModelProperty(value = "引用次数")
    private Integer excerptNum;

    @TableField("html_path")
    @ApiModelProperty(value = "静态HTML保存路径")
    private String htmlPath;

    @TableField("is_share")
    @ApiModelProperty(value = "是否共享问卷  0不共享 1共享", required = "required")
    private Integer isShare;

    @TableField("sid")
    @ApiModelProperty(value = "用于短链接的ID")
    private String sid;

    @TableField("dir_type")
    @ApiModelProperty(value = "1目录   2问卷", required = "required")
    private Integer dirType;

    @TableField("end_type")
    @ApiModelProperty(value = "结束方式   1手动结束,2依据结束时间,3依据收到的份数")
    private Integer endType;

    @TableField("mail_only")
    @ApiModelProperty(value = "只有邮件邀请唯一链接的受访者可回答  1启用 0不启用", required = "required")
    private Integer mailOnly;

    @TableField("survey_tag")
    @ApiModelProperty(value = "问卷标识 默认 0待审核  1审核通过  2审核未通过  3审核中", required = "required")
    private Integer surveyTag;

    @TableField("view_answer")
    @ApiModelProperty(value = "是否公开结果  0不  1公开", required = "required")
    private Integer viewAnswer;

    @TableField("visibility")
    @ApiModelProperty(value = "是否被删除  1.未删除  0.已删除", required = "required")
    private Integer visibility;

    @TableField("create_id")
    @ApiModelProperty(value = "创建人", required = "required")
    private String createId;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = "required")
    private String createTime;
}

