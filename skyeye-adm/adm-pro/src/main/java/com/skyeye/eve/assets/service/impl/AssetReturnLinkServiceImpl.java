/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.eve.assets.dao.AssetReturnLinkDao;
import com.skyeye.eve.assets.entity.Asset;
import com.skyeye.eve.assets.entity.AssetReturnLink;
import com.skyeye.eve.assets.service.AssetReturnLinkService;
import com.skyeye.eve.assets.service.AssetService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AssetReturnLinkServiceImpl
 * @Description: 资产归还申请关联的资产信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 19:54
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "资产归还单-资产Link", groupName = "资产模块", manageShow = false)
public class AssetReturnLinkServiceImpl extends SkyeyeLinkDataServiceImpl<AssetReturnLinkDao, AssetReturnLink> implements AssetReturnLinkService {

    @Autowired
    private AssetService assetService;

    @Override
    public void checkLinkList(String pId, List<AssetReturnLink> beans) {
        if (CollectionUtil.isEmpty(beans)) {
            throw new CustomException("资产信息不能为空.");
        }
        List<String> articleIds = beans.stream().map(AssetReturnLink::getAssetId).distinct().collect(Collectors.toList());
        Map<String, Asset> articlesMap = assetService.selectMapByIds(articleIds);
        beans.forEach(assetReturnLink -> {
            Asset asset = articlesMap.get(assetReturnLink.getAssetId());
            if (asset == null) {
                throw new CustomException("数据中包含不存在的资产信息.");
            }
        });
    }

}
