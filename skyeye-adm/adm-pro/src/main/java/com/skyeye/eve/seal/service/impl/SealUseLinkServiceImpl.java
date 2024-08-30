/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.seal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.eve.seal.dao.SealUseLinkDao;
import com.skyeye.eve.seal.entity.Seal;
import com.skyeye.eve.seal.entity.SealUseLink;
import com.skyeye.eve.seal.service.SealService;
import com.skyeye.eve.seal.service.SealUseLinkService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SealUseLinkServiceImpl
 * @Description: 印章借用申请关联的印章信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 19:54
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "印章借用申请-印章Link", groupName = "印章模块", manageShow = false)
public class SealUseLinkServiceImpl extends SkyeyeLinkDataServiceImpl<SealUseLinkDao, SealUseLink> implements SealUseLinkService {

    @Autowired
    private SealService sealService;

    @Override
    public void checkLinkList(String pId, List<SealUseLink> beans) {
        if (CollectionUtil.isEmpty(beans)) {
            throw new CustomException("印章信息不能为空.");
        }
        List<String> sealIds = beans.stream().map(SealUseLink::getSealId).distinct().collect(Collectors.toList());
        Map<String, Seal> sealMap = sealService.selectMapByIds(sealIds);
        beans.forEach(sealUseLink -> {
            Seal seal = sealMap.get(sealUseLink.getSealId());
            if (seal == null) {
                throw new CustomException("数据中包含不存在的印章信息.");
            }
        });
    }

}
