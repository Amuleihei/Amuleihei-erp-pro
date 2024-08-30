package com.skyeye.exam.examQuChencolumn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

@Data
@RedisCacheField(name = "exam:chencolumn")
@TableName(value = "exam_qu_chen_column")
@ApiModel("矩陈题-列选项实体类")
public class ExamQuChenColumn extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("qu_id")
    @ApiModelProperty(value = "所属题", required = "required")
    private String quId;

    @TableField("option_name")
    @ApiModelProperty(value = "选项内容", required = "required")
    private Long optionName;

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
}