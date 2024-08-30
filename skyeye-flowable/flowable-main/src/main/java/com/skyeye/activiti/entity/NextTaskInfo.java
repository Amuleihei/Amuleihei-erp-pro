/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.entity;

import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;
import org.flowable.bpmn.model.UserTask;

import java.io.Serializable;

/**
 * @ClassName: NextTaskInfo
 * @Description: 流程中下一个UserTask的信息
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/23 23:08
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class NextTaskInfo implements Serializable {

    @ApiModelProperty("用户任务节点信息")
    private UserTask userTask;

}
