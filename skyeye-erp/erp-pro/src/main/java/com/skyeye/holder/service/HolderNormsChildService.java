/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.holder.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.holder.entity.HolderNormsChild;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: HolderNormsChildService
 * @Description: 关联的客户/供应商/会员购买或者出售的商品子信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/5 11:18
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface HolderNormsChildService extends SkyeyeBusinessService<HolderNormsChild> {

    void editHolderNormsChildState(String holderId, List<String> normsCode, Integer state);

    List<Map<String, Object>> queryHolderMaterialNormsCodeListByHolder(String holderId, String holderKey, String normsId);
}
