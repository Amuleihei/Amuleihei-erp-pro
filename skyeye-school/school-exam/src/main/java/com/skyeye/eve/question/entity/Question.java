/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.question.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: Question
 * @Description: 问题
 * @author: skyeye云系列--卫志强
 * @date: 2023/10/11 19:17
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "bank:question", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "bank_question")
@ApiModel(value = "问题")
public class Question extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("qu_title")
    @ApiModelProperty(value = "题目名称")
    private String quTitle;

    @TableField("qu_tag")
    @ApiModelProperty(value = "是否是大小题    1默认题  2大题  3大题下面的小题   ", required = "required,num")
    private Integer quTag;

    @TableField("qu_type")
    @ApiModelProperty(value = "题目类型", required = "required,num")
    private Integer quType;

    @TableField("answer_input_row")
    @ApiModelProperty(value = "填空的input行")
    private Integer answerInputRow;

    @TableField("answer_input_width")
    @ApiModelProperty(value = "填空的input宽度")
    private Integer answerInputWidth;

    @TableField("visibility")
    @ApiModelProperty(value = "是否显示 0不显示   1显示 ")
    private Integer visibility;

}
