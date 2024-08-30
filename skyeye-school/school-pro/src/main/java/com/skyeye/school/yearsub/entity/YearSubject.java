/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.yearsub.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.school.major.entity.Major;
import com.skyeye.school.subject.entity.Subject;
import lombok.Data;

/**
 * @ClassName: YearSubject
 * @Description: 学年科目实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/29 16:15
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "school:yearSubject", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "school_year_subject")
@ApiModel(value = "学年科目实体类")
public class YearSubject extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("semester")
    @ApiModelProperty(value = "学年", required = "required")
    private String semester;

    @TableField("year")
    @Property(value = "所属年份(哪一届)")
    private Integer year;

    @TableField("major_id")
    @Property(value = "所属专业")
    private String majorId;

    @TableField(exist = false)
    @Property(value = "所属专业信息")
    private Major majorMation;

    @TableField("subject_id")
    @ApiModelProperty(value = "科目Id", required = "required")
    private String subjectId;

    @TableField(exist = false)
    @Property(value = "科目Id")
    private Subject subjectMation;

}
