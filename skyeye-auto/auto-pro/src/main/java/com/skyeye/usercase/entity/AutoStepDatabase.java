/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.database.entity.AutoDataBase;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: AutoStepDatabase
 * @Description: 用例步骤关联的数据库实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/28 12:54
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@TableName(value = "auto_step_database")
@ApiModel(value = "用例步骤关联的数据库实体类")
public class AutoStepDatabase extends CommonInfo {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("step_id")
    @Property(value = "步骤id")
    private String stepId;

    @TableField("database_id")
    @ApiModelProperty(value = "数据库id", required = "required")
    private String databaseId;

    @TableField(exist = false)
    @Property("数据库信息")
    private AutoDataBase databaseMation;

    @TableField("sql_content")
    @ApiModelProperty(value = "sql脚本", required = "required")
    private String sqlContent;

    @TableField(exist = false)
    @ApiModelProperty(value = "数据库取值", required = "json")
    private List<AutoStepDatabaseValue> stepDatabaseValueList;

    @TableField("case_id")
    @Property(value = "所属用例")
    private String caseId;

}
