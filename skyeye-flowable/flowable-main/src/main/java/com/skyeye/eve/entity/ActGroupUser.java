/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: ActGroupUser
 * @Description: 用户组关联用户的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/11 11:11
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"groupId", "userId"})
@TableName(value = "act_group_user")
@ApiModel("用户组关联用户的实体类")
public class ActGroupUser extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "group_id")
    @ApiModelProperty(value = "用户组id", required = "required")
    private String groupId;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id", required = "required")
    private String userId;

    @TableField(exist = false)
    @Property(value = "用户信息")
    private Map<String, Object> userMation;

}
