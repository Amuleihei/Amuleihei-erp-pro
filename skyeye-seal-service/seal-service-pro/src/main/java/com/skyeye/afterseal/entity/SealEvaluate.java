/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.base.handler.enclosure.bean.Enclosure;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: SealEvaluate
 * @Description: 工单服务评价实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 14:58
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@RedisCacheField(name = "seal:server:evaluate", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "crm_service_evaluate")
@ApiModel("工单服务评价实体类")
public class SealEvaluate extends OperatorUserInfo implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "评价类型，参考数据字典", required = "required")
    private String typeId;

    @TableField(value = "object_id")
    @ApiModelProperty(value = "工单id", required = "required")
    private String objectId;

    @TableField(value = "object_key")
    @ApiModelProperty(value = "工单的key", required = "required")
    private String objectKey;

    @TableField(value = "content")
    @ApiModelProperty(value = "评价内容", required = "required")
    private String content;

    @TableField(value = "type")
    @ApiModelProperty(value = "类型  1.系统自动好评  2.人工评价", required = "num", defaultValue = "2")
    private Integer type;

    @TableField(value = "open_id")
    @ApiModelProperty(value = "微信用户的id")
    private String openId;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件", required = "json")
    private Enclosure enclosureInfo;

}
