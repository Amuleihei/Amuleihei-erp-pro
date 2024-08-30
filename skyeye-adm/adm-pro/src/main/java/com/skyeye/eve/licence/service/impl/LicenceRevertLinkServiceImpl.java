/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.licence.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeLinkDataServiceImpl;
import com.skyeye.eve.licence.dao.LicenceRevertLinkDao;
import com.skyeye.eve.licence.entity.Licence;
import com.skyeye.eve.licence.entity.LicenceRevertLink;
import com.skyeye.eve.licence.service.LicenceRevertLinkService;
import com.skyeye.eve.licence.service.LicenceService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: LicenceRevertLinkServiceImpl
 * @Description: 证照归还申请关联的证照信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 19:54
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "证照归还申请-证照Link", groupName = "证照模块", manageShow = false)
public class LicenceRevertLinkServiceImpl extends SkyeyeLinkDataServiceImpl<LicenceRevertLinkDao, LicenceRevertLink> implements LicenceRevertLinkService {

    @Autowired
    private LicenceService licenceService;

    @Override
    public void checkLinkList(String pId, List<LicenceRevertLink> beans) {
        if (CollectionUtil.isEmpty(beans)) {
            throw new CustomException("证照信息不能为空.");
        }
        List<String> licenceIds = beans.stream().map(LicenceRevertLink::getLicenceId).distinct().collect(Collectors.toList());
        Map<String, Licence> licenceMap = licenceService.selectMapByIds(licenceIds);
        beans.forEach(licenceUseLink -> {
            Licence licence = licenceMap.get(licenceUseLink.getLicenceId());
            if (licence == null) {
                throw new CustomException("数据中包含不存在的证照信息.");
            }
        });
    }

}
