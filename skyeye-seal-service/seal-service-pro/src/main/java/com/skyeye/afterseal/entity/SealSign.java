/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: SealSign
 * @Description: 工人签到信息
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 13:15
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@RedisCacheField(name = "seal:server:sign", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "crm_service_sign")
@ApiModel("工人签到信息实体类")
public class SealSign extends CommonInfo {

    @TableId("id")
    @Property(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "object_id")
    @ApiModelProperty(value = "工单id", required = "required")
    private String objectId;

    @TableField(value = "object_key")
    @ApiModelProperty(value = "工单的key", required = "required")
    private String objectKey;

    @TableField(value = "longitude")
    @ApiModelProperty(value = "经度")
    private String longitude;

    @TableField(value = "latitude")
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @TableField(value = "address")
    @ApiModelProperty(value = "签到地址")
    private String address;

    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "sign_id")
    @Property(value = "签到人id")
    private String signId;

    @TableField(value = "sign_time")
    @Property(value = "签到日期")
    private String signTime;

}
