/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.student.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

/**
 * @ClassName: SchoolStudentParents
 * @Description: 学生父母实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/11 20:43
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "school_student_parents")
@ApiModel(value = "学生父母实体类")
public class StudentParents extends BaseGeneralInfo {

    @TableField("student_id")
    @ApiModelProperty(value = "学生id", required = "required")
    private String studentId;

    @TableField("idcard")
    @ApiModelProperty(value = "身份证号码", required = "required")
    private String idcard;

    @TableField("unit")
    @ApiModelProperty(value = "单位", required = "required")
    private String unit;

    @TableField("phone")
    @ApiModelProperty(value = "手机号", required = "required")
    private String phone;

    @TableField("nation")
    @ApiModelProperty(value = "民族", required = "required")
    private String nation;

    @TableField("residence_no")
    @ApiModelProperty(value = "户口所在地编码", required = "required")
    private String residenceNo;

    @TableField("call_name")
    @ApiModelProperty(value = "称呼", required = "required")
    private String callName;

}
