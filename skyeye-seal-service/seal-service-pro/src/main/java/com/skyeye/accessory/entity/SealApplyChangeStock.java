/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.accessory.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: SealApplyChangeStock
 * @Description: 配件申请单修改数量实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/20 10:30
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("配件申请单修改数量实体类")
public class SealApplyChangeStock extends CommonInfo {

    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑", required = "required")
    private String id;

    @ApiModelProperty(value = "创建人id", required = "required")
    private String createId;

    @ApiModelProperty(value = "配件申领明细", required = "required,json")
    private List<ApplyLink> applyLinkList;

}
