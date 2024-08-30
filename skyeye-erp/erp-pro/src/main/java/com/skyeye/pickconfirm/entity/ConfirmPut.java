/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pickconfirm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.entity.ErpOrderHead;
import lombok.Data;

/**
 * @ClassName: ConfirmPut
 * @Description: 物料接收单
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/27 10:01
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "erp:order:confirmPut", cacheTime = RedisConstants.TOW_MONTH_SECONDS)
@TableName(value = "erp_depothead", autoResultMap = true)
@ApiModel("物料接收单实体类")
public class ConfirmPut extends ErpOrderHead {

}
