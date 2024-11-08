/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.delivery.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.delivery.dao.ShopDeliveryTemplateDao;
import com.skyeye.delivery.entity.ShopDeliveryTemplate;
import com.skyeye.delivery.service.ShopDeliveryTemplateService;
import com.skyeye.exception.CustomException;
import com.skyeye.store.entity.ShopStore;
import com.skyeye.store.service.ShopStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopDeliveryTemplateServiceImpl
 * @Description: 快递运费模版服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "快递运费模版", groupName = "快递运费模版")
public class ShopDeliveryTemplateServiceImpl extends SkyeyeBusinessServiceImpl<ShopDeliveryTemplateDao, ShopDeliveryTemplate> implements ShopDeliveryTemplateService {

    @Autowired
    private ShopStoreService shopStoreService;

    /**
     * 分页查询-快递运费模版
     *
     * @param commonPageInfo
     * @return
     */
    @Override
    public QueryWrapper<ShopDeliveryTemplate> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopDeliveryTemplate> queryWrapper = super.getQueryWrapper(commonPageInfo);
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopDeliveryTemplate::getStoreId), commonPageInfo.getObjectId());
        return queryWrapper;
    }

    /**
     * 获取全部快递运费模版信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     */
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        QueryWrapper<ShopDeliveryTemplate> queryWrapper = new QueryWrapper<>();
        List<ShopDeliveryTemplate> beans = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(beans), null);
    }

    /**
     * 重写新增编辑前置条件快递运费模版信息
     */
    @Override
    public void validatorEntity(ShopDeliveryTemplate shopDeliveryTemplate) {
        super.validatorEntity(shopDeliveryTemplate);

        if (StrUtil.isNotEmpty(shopDeliveryTemplate.getName()) && shopDeliveryTemplate.getName().length() > 100) {
            throw new CustomException("运费模板名称过长");
        }
        if (!(shopDeliveryTemplate.getOrderBy() instanceof Integer)){
            throw new CustomException("排序值类型错误,请输入整数数字");
        }
        //判断StoreId是否存在
        if (StrUtil.isNotEmpty(shopDeliveryTemplate.getStoreId())) {
            ShopStore shopStore = shopStoreService.selectById(shopDeliveryTemplate.getStoreId());
            //判断shopStore是否为空，如果为空，则抛出异常
            if (StrUtil.isNotEmpty(shopStore.getId())) {
                throw new CustomException("门店不存在: " + shopStore);
            }
        }
    }
}
