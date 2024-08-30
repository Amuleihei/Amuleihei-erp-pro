/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.usercase.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.search.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AutoUserCaseQueryDo
 * @Description: 用例列表查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/24 16:23
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("用例列表查询条件实体类")
public class AutoUserCaseQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "模块id")
    private String moduleId;

}
