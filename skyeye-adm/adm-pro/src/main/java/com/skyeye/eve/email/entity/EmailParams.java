/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.email.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: EmailParams
 * @Description: 邮件发送等操作的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/9 13:07
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("邮件发送等操作的实体类")
public class EmailParams extends CommonInfo {

    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @ApiModelProperty(value = "标题", required = "required")
    private String title;

    @ApiModelProperty(value = "邮件内容", required = "required")
    private String content;

    @ApiModelProperty(value = "收件人", required = "required")
    private String toPeople;

    @ApiModelProperty(value = "抄送人")
    private String toCc;

    @ApiModelProperty(value = "暗送人")
    private String toBcc;

    @ApiModelProperty(value = "邮件附件id串")
    private String emailEnclosure;

    @ApiModelProperty(value = "用户绑定的邮箱id", required = "required")
    private String emailUserId;

}
