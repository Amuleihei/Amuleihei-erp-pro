/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.userprocess.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: ActUserProcess
 * @Description: 用户启动的流程实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/18 10:34
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "act_user_process", autoResultMap = true)
@ApiModel("用户启动的流程实体类")
public class ActUserProcess extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("process_instance_id")
    @ApiModelProperty(value = "流程id", required = "required")
    private String processInstanceId;

    @TableField("act_flow_id")
    @ApiModelProperty(value = "工作流模型id", required = "required")
    private String actFlowId;

    @TableField(exist = false)
    @Property("工作流模型名称")
    private String title;

    @TableField(exist = false)
    @Property("流程配置页面类型")
    private Integer pageTypes;

    @TableField("object_id")
    @ApiModelProperty(value = "业务数据的id", required = "required")
    private String objectId;

    @TableField("object_key")
    @ApiModelProperty(value = "业务数据的serviceClassName", required = "required")
    private String objectKey;

    @TableField("app_id")
    @ApiModelProperty(value = "业务数据的id", required = "required")
    private String appId;

    @TableField(exist = false)
    @Property("任务信息")
    private Map<String, Object> task;

}
