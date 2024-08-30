/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeTeamAuth;
import com.skyeye.environment.entity.AutoEnvironment;
import lombok.Data;

/**
 * @ClassName: AutoServer
 * @Description: 服务器管理实体层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/26 9:00
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField({"objectId", "ip"})
@RedisCacheField(name = "auto:server", cacheTime = RedisConstants.TOW_MONTH_SECONDS)
@TableName(value = "auto_server", autoResultMap = true)
@ApiModel(value = "服务器实体类")
public class AutoServer extends SkyeyeTeamAuth implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty("相关描述")
    private String remark;

    @TableField("ip")
    @ApiModelProperty(value = "服务器ip", required = "required")
    private String ip;

    @TableField("cpu")
    @ApiModelProperty(value = "服务器cpu")
    private String cpu;

    @TableField("disk")
    @ApiModelProperty(value = "服务器磁盘")
    private String disk;

    @TableField("mem")
    @ApiModelProperty(value = "服务器分配信息")
    private String mem;

    @TableField("environment_id")
    @ApiModelProperty(value = "环境id", required = "required")
    private String environmentId;

    @TableField(exist = false)
    @Property(value = "环境信息")
    private AutoEnvironment environmentMation;

}
