/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: NoticeUser
 * @Description: 公告群发对象表
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/30 23:11
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "sys_notice_user", autoResultMap = true)
@ApiModel("公告群发对象表")
public class NoticeUser extends CommonInfo {

    @TableField(value = "user_id")
    @Property(value = "用户id")
    private String userId;

    @TableField(value = "notice_id")
    @Property(value = "公告id")
    private String noticeId;

}
