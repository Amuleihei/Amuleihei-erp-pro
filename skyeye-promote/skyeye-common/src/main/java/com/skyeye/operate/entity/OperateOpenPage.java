/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.OperatorUserInfo;
import com.skyeye.dsform.entity.DsFormPage;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: OperateOpenPage
 * @Description: 操作信息对应的新开页面信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_operate_page", autoResultMap = true)
@ApiModel("操作信息对应的新开页面信息实体类")
public class OperateOpenPage extends OperatorUserInfo {

    @TableId("id")
    @Property(value = "主键id")
    private String id;

    @TableField("operate_id")
    @Property(value = "操作信息的id")
    private String operateId;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("`type`")
    @ApiModelProperty(value = "页面类型，参考#MenuPageType", required = "required")
    private Integer type;

    @TableField("page_url")
    @ApiModelProperty(value = "自定义页面的地址/表单布局的id", required = "required")
    private String pageUrl;

    @TableField(exist = false)
    @Property(value = "当 type 为表单布局时，存储的表单布局信息")
    private DsFormPage dsFormPage;

    @TableField(exist = false)
    @Property(value = "当 type 为视图页面时，存储的视图页面信息")
    private Map<String, Object> reportPage;

    @TableField(value = "params", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "请求参数，数据格式：{入参key: 需要解析的属性key}", required = "json")
    private Map<String, Object> params;

}
