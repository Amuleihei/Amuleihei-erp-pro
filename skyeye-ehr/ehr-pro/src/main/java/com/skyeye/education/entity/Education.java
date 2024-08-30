/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.education.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.base.handler.enclosure.bean.Enclosure;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: Education
 * @Description: 员工教育背景实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/11/15 11:45
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "ehr:education", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "sys_staff_education")
@ApiModel("员工教育背景实体类")
public class Education extends OperatorUserInfo implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "education_id")
    @ApiModelProperty(value = "学历id，参考数据字典", required = "required")
    private String educationId;

    @TableField(exist = false)
    @Property(value = "学历信息")
    private Map<String, Object> educationMation;

    @TableField(value = "start_time")
    @ApiModelProperty(value = "入校时间", required = "required")
    private String startTime;

    @TableField(value = "end_time")
    @ApiModelProperty(value = "毕业时间", required = "required")
    private String endTime;

    @TableField(value = "graduction_school")
    @ApiModelProperty(value = "毕业学校", required = "required")
    private String graductionSchool;

    @TableField(value = "major")
    @ApiModelProperty(value = "专业")
    private String major;

    @TableField(value = "learning_modality_id")
    @ApiModelProperty(value = "学习形式，参考数据字典", required = "required")
    private String learningModalityId;

    @TableField(exist = false)
    @Property(value = "学习形式信息")
    private Map<String, Object> learningModalityMation;

    @TableField(value = "school_nature")
    @ApiModelProperty(value = "学校性质，参考数据字典", required = "required")
    private String schoolNature;

    @TableField(exist = false)
    @Property(value = "学校性质信息")
    private Map<String, Object> schoolNatureMation;

    @TableField(value = "object_id", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "所属第三方业务数据id(员工id)", required = "required")
    private String objectId;

    @TableField(value = "object_key", updateStrategy = FieldStrategy.NEVER)
    @ApiModelProperty(value = "所属第三方业务数据的key(员工key)", required = "required")
    private String objectKey;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件", required = "json")
    private Enclosure enclosure;

}
