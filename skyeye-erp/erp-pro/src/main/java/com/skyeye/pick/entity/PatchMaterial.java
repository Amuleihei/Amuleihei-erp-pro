/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pick.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.pick.entity.common.Pick;
import lombok.Data;

/**
 * @ClassName: PatchMaterial
 * @Description: 补料单
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/30 8:49
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = CacheConstants.MES_PATCH_CACHE_KEY, cacheTime = RedisConstants.TOW_MONTH_SECONDS)
@TableName(value = "erp_pick_header", autoResultMap = true)
@ApiModel("补料单实体类")
public class PatchMaterial extends Pick {

}
