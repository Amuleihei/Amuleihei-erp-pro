/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dom.dao.DomModelDao;
import com.skyeye.dom.entity.DomModel;
import com.skyeye.dom.service.DomModelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DomModelServiceImpl
 * @Description: DOM模型服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/4 10:08
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "DOM模型管理", groupName = "DOM模型管理")
public class DomModelServiceImpl extends SkyeyeBusinessServiceImpl<DomModelDao, DomModel> implements DomModelService {

    @Override
    public void queryAllEnabledDomModelList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<DomModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(DomModel::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<DomModel> domModelList = list(queryWrapper);
        outputObject.setBeans(domModelList);
        outputObject.settotal(domModelList.size());
    }
}
