/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: SysEveUserStaffTeacher
 * @Description: 员工所属学校关系实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 11:24
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_eve_user_staff_teacher")
@ApiModel("员工所属学校关系实体类")
public class SysEveUserStaffTeacher extends CommonInfo {

    @TableId("id")
    @Property(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("staff_id")
    @ApiModelProperty(value = "员工id", required = "required")
    private String staffId;

    @TableField("school_id")
    @ApiModelProperty(value = "学校id", required = "required")
    private String schoolId;

    @TableField("faculty_id")
    @ApiModelProperty(value = "院系id")
    private String facultyId;

}
