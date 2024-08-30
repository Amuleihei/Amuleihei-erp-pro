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
 * @ClassName: AutoHistoryStepDatabase
 * @Description: 数据库执行历史实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/28 12:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "auto_history_database")
@ApiModel(value = "数据库执行历史实体类")
public class AutoHistoryStepDatabase extends CommonInfo {

    @TableId("id")
    @Property(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "history_step_id")
    @Property(value = "历史步骤id")
    private String historyStepId;

    @TableField(value = "database_id")
    @Property(value = "数据库id")
    private String databaseId;

    @TableField(value = "sql_content")
    @Property(value = "sql脚本")
    private String sqlContent;

    @TableField("output_value")
    @Property(value = "出参")
    private String outputValue;

    @TableField("history_case_id")
    @Property(value = "用例id")
    private String historyCaseId;

}
