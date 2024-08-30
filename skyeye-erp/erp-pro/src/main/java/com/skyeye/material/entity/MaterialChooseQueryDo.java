/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.search.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: MaterialChooseQueryDo
 * @Description: 列表信息展示为选择性表格列表查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/21 21:19
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("列表信息展示为选择性表格列表查询条件实体类")
public class MaterialChooseQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "所属分类id")
    private String categoryId;

    @ApiModelProperty(value = "仓库id")
    private String depotId;

}
