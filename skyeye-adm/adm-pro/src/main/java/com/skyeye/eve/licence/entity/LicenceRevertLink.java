/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.licence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.features.SkyeyeLinkData;
import lombok.Data;

/**
 * @ClassName: LicenceRevertLink
 * @Description: 证照归还申请关联的证照信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/3 18:16
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "licence_revert_goods")
@ApiModel("证照归还申请关联的证照信息实体类")
public class LicenceRevertLink extends SkyeyeLinkData {

    @TableField("licence_id")
    @ApiModelProperty(value = "证照id", required = "required")
    private String licenceId;

    @TableField(exist = false)
    @Property("证照信息")
    private Licence licenceMation;

}
