/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.grade.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.school.subject.entity.Subject;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: YearSystem
 * @Description: 年制实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 17:36
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:yearSystem")
@TableName(value = "school_year_system")
@ApiModel(value = "年制实体类")
public class YearSystem extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("class_id")
    @ApiModelProperty(value = "所属班级", required = "required")
    private String classId;

    @TableField("semester")
    @ApiModelProperty(value = "学年", required = "required")
    private String semester;

    @TableField("order_by")
    @ApiModelProperty(value = "排序", required = "required")
    private Integer orderBy;

    @TableField("year")
    @Property(value = "所属年份(哪一届)")
    private Integer year;

    @TableField(exist = false)
    @Property("学年下的课程信息")
    private List<Subject> subjectList;

}
