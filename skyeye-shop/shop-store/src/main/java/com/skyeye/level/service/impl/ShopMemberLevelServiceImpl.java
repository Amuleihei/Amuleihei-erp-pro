/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.level.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.delivery.entity.ShopDeliveryTemplate;
import com.skyeye.level.dao.ShopMemberLevelDao;
import com.skyeye.level.entity.ShopMemberLevel;
import com.skyeye.level.service.ShopMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopMemberLevelServiceImpl
 * @Description: 会员等级服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "会员等级", groupName = "会员等级")
public class ShopMemberLevelServiceImpl extends SkyeyeBusinessServiceImpl<ShopMemberLevelDao, ShopMemberLevel> implements ShopMemberLevelService {

    /**
     * 分页查询-会员等级
     *
     * @param commonPageInfo
     * @return
     */
    @Override
    public QueryWrapper<ShopMemberLevel> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopMemberLevel> queryWrapper = super.getQueryWrapper(commonPageInfo);
        queryWrapper.like(MybatisPlusUtil.toColumns(ShopMemberLevel::getName), commonPageInfo.getObjectId());
        return queryWrapper;
    }

    /**
     * 获取会员等级列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        QueryWrapper<ShopMemberLevel> queryWrapper = new QueryWrapper<>();
        List<ShopMemberLevel> beans = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(beans), null);
    }
}
