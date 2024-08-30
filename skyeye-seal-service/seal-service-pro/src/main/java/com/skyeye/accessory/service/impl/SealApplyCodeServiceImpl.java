/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.accessory.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.accessory.dao.SealApplyCodeDao;
import com.skyeye.accessory.entity.SealApplyCode;
import com.skyeye.accessory.service.SealApplyCodeService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SealApplyCodeServiceImpl
 * @Description: 单据关联的条形码编号服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/20 10:44
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "单据关联的条形码编号", groupName = "配件申领单管理")
public class SealApplyCodeServiceImpl extends SkyeyeBusinessServiceImpl<SealApplyCodeDao, SealApplyCode> implements SealApplyCodeService {

    @Override
    public void saveList(String parentId, List<SealApplyCode> beans) {
        if (CollectionUtil.isNotEmpty(beans)) {
            for (SealApplyCode sealApplyCode : beans) {
                sealApplyCode.setParentId(parentId);
            }
            createEntity(beans, StrUtil.EMPTY);
        }
    }
}
