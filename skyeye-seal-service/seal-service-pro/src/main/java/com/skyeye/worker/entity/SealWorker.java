/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.worker.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.AreaInfo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: SealWorker
 * @Description: 工人管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 19:04
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@RedisCacheField(name = "seal:worker", cacheTime = RedisConstants.TOW_MONTH_SECONDS)
@TableName(value = "crm_service_worker", autoResultMap = true)
@ApiModel("工人管理实体类")
public class SealWorker extends AreaInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(exist = false)
    @Property(value = "名称")
    private String name;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id", required = "required")
    private String userId;

    @TableField(exist = false)
    @Property(value = "用户信息")
    private Map<String, Object> userMation;

    @TableField(value = "longitude")
    @ApiModelProperty(value = "经度")
    private String longitude;

    @TableField(value = "latitude")
    @ApiModelProperty(value = "纬度")
    private String latitude;

}
