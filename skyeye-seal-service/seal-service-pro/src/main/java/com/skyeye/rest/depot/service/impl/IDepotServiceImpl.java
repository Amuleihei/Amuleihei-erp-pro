/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.depot.service.impl;

import com.skyeye.base.rest.service.impl.IServiceImpl;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.rest.depot.rest.IDepotRest;
import com.skyeye.rest.depot.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: IDepotServiceImpl
 * @Description: 仓库信息管理公共的一些操作
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 10:32
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
public class IDepotServiceImpl extends IServiceImpl implements IDepotService {

    @Autowired
    private IDepotRest iDepotRest;

    @Override
    public Map<String, Object> queryEntityMationById(String id) {
        return queryEntityMationByIds(id).stream().findFirst().orElse(new HashMap<>());
    }

    @Override
    public List<Map<String, Object>> queryEntityMationByIds(String ids) {
        return ExecuteFeignClient.get(() -> iDepotRest.queryDepotByIds(ids)).getRows();
    }

    @Override
    public String queryCacheKeyById(String id) {
        return String.format(Locale.ROOT, "%s:%s", CacheConstants.ERP_DEPOT_CACHE_KEY, id);
    }

}
