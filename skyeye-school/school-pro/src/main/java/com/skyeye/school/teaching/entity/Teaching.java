/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.teaching.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.school.building.entity.Classroom;
import com.skyeye.school.grade.entity.Classes;
import com.skyeye.school.yearsub.entity.YearSubject;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: Teaching
 * @Description: 授课实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 17:41
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:teaching", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_teaching")
@ApiModel(value = "授课实体类")
public class Teaching extends OperatorUserInfo {

    @TableField("teacher_id")
    @ApiModelProperty(value = "授课教师", required = "required")
    private Map<String,Object> teacherId;

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("year_subject_id")
    @ApiModelProperty(value = "学年科目", required = "required")
    private String yearSubjectId;

    @TableField(exist = false)
    @Property(value = "学年科目")
    private YearSubject yearSubjectMation;

    @TableField("class_id")
    @ApiModelProperty(value = "所属班级信息", required = "required")
    private String classId;

    @TableField(exist = false)
    @Property(value = "所属班级信息")
    private Classes classMation;

    @TableField("classroom_id")
    @ApiModelProperty(value = "上课地点", required = "required")
    private String classroomId;

    @TableField(exist = false)
    @Property(value = "上课地点")
    private Classroom classroomMation;

}
