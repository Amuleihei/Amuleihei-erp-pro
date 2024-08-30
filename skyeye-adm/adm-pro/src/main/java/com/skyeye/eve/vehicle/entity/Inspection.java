/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.vehicle.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.BaseGeneralInfo;
import lombok.Data;

/**
 * @ClassName: Inspection
 * @Description: 车辆年检实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/5/3 18:16
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "assistant:vehicle:inspection", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "vehicle_inspection")
@ApiModel("车辆年检实体类")
public class Inspection extends BaseGeneralInfo {

    @TableField("vehicle_id")
    @ApiModelProperty(value = "车辆id", required = "required")
    private String vehicleId;

    @TableField(exist = false)
    @Property(value = "车辆信息")
    private Vehicle vehicleMation;

    @TableField("inspection_area")
    @ApiModelProperty(value = "年检地点")
    private String inspectionArea;

    @TableField("contact_information")
    @ApiModelProperty(value = "联系电话", required = "phone")
    private String contactInformation;

    @TableField("inspection_price")
    @ApiModelProperty(value = "年检费用", required = "double", defaultValue = "0")
    private String inspectionPrice;

    @TableField("this_inspection_time")
    @ApiModelProperty(value = "本次年检时间", required = "required")
    private String thisInspectionTime;

    @TableField("next_inspection_time")
    @ApiModelProperty(value = "下次年检时间", required = "required")
    private String nextInspectionTime;

}
