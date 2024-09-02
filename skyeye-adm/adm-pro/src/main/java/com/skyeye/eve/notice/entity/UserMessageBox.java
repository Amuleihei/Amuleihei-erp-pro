/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.notice.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserNoticeMationBox
 * @Description: 用户内部消息的实体类的盒子，支持集合使用
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/7 14:49
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("用户内部消息的实体类的盒子，支持集合使用")
public class UserMessageBox implements Serializable {

    @ApiModelProperty(value = "用户内部消息", required = "required")
    private List<UserMessage> userNoticeMationList;

}
