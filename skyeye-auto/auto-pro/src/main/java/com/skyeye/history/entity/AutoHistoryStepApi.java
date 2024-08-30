/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.history.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: AutoHistoryStepApi
 * @Description: API执行历史实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/28 12:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "auto_history_step_api")
@ApiModel(value = "API执行历史实体类")
public class AutoHistoryStepApi extends CommonInfo {

    @TableId("id")
    @Property(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "history_step_id")
    @Property(value = "历史步骤id")
    private String historyStepId;

    @TableField(value = "url")
    @Property(value = "接口请求url地址")
    private String url;

    @TableField(value = "method")
    @Property(value = "请求方式")
    private String method;

    @TableField(value = "header")
    @Property(value = "请求头")
    private String header;

    @TableField("input_value")
    @Property(value = "入参")
    private String inputValue;

    @TableField("output_value")
    @Property(value = "出参")
    private String outputValue;

    @TableField("execute_start_time")
    @Property(value = "请求开始时间")
    private String executeStartTime;

    @TableField("execute_end_time")
    @Property(value = "请求结束时间")
    private String executeEndTime;

    @TableField("execute_time")
    @Property(value = "请求耗时   毫秒")
    private String executeTime;

    @TableField("history_case_id")
    @Property(value = "用例id")
    private String historyCaseId;

}
