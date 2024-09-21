package com.skyeye.exam.examqumultfillblank.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "exam:multifillblank")
@TableName(value = "exam_qu_multi_fillblank")
@ApiModel("多行填空题实体类")
public class ExamQuMultiFillblank extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("qu_id")
    @ApiModelProperty(value = "所属题", required = "required")
    private String quId;

    @TableField("option_name")
    @ApiModelProperty(value = "选项内容", required = "required")
    private Long optionName;

    @TableField("option_title")
    @ApiModelProperty(value = "选项标题")
    private String optionTitle;

    @TableField("check_type")
    @ApiModelProperty(value = "说明的验证方式")
    private Integer checkType;

    @TableField("order_by_id")
    @ApiModelProperty(value = "排序ID", required = "required")
    private Integer orderById;

    @TableField("visibility")
    @ApiModelProperty(value = "是否显示  0不显示  1显示", required = "required")
    private Integer visibility;

    @TableField("create_id")
    @ApiModelProperty(value = "创建人", required = "required")
    private String createId;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = "required")
    private Data createTime;

    @TableField("last_update_id")
    @ApiModelProperty(value = "最后更新人", required = "required")
    private String lastUpdateId;

    @TableField("last_update_time")
    @ApiModelProperty(value = "最后更新时间", required = "required")
    private Data lastUpdateTime;

    @TableField("is_default_answer")
    @ApiModelProperty(value = "是否是默认答案  1.是  2.否")
    private Long isDefaultAnswer;
}