package com.skyeye.delivery.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.delivery.dao.ShopDeliveryCompanyDao;
import com.skyeye.delivery.entity.ShopDeliveryCompany;
import com.skyeye.delivery.service.ShopDeliveryCompanyService;
import com.skyeye.exception.CustomException;
import com.skyeye.store.entity.ShopStore;
import com.skyeye.store.service.ShopStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SkyeyeService(name = "快递公司管理", groupName = "快递公司管理")
public class ShopDeliveryCompanyServiceImpl extends SkyeyeBusinessServiceImpl<ShopDeliveryCompanyDao, ShopDeliveryCompany> implements ShopDeliveryCompanyService {

    /**
     * 禁用
     */
    public static final Integer STATUS_FALSE = 0;
    /**
     * 启用
     */
    public static final Integer STATUS_TURE = 1;

    @Autowired
    private ShopStoreService shopStoreService;

    /**
     * 分页查询-快递公司
     * @param commonPageInfo
     * @return
     */
    @Override
    public QueryWrapper<ShopDeliveryCompany> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopDeliveryCompany> queryWrapper = super.getQueryWrapper(commonPageInfo);
        String objectKey =  commonPageInfo.getObjectKey();
        if (StrUtil.isNotEmpty(objectKey)) {
            queryWrapper.like(MybatisPlusUtil.toColumns(ShopDeliveryCompany::getStoreId), objectKey);
        }
        return queryWrapper;
    }

    /**
     * 查询状态启用的快递公司管理信息
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void streamlineDeliveryList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<ShopDeliveryCompany> queryWrapper = new QueryWrapper<>();
        // 添加查询
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopDeliveryCompany::getEnabled), STATUS_TURE);
        List<ShopDeliveryCompany> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }

    /**
     * 重写新增编辑前置条件
     */
    @Override
    public void createPrepose(ShopDeliveryCompany shopDeliveryCompany) {
        checkStoreExists(shopDeliveryCompany);
    }

    @Override
    public void updatePrepose(ShopDeliveryCompany shopDeliveryCompany) {
        checkStoreExists(shopDeliveryCompany);
    }

    private void checkStoreExists(ShopDeliveryCompany shopDeliveryCompany) {
        //判断StoreId是否存在
        if (ObjectUtil.isNotNull(shopDeliveryCompany.getStoreId())) {
            ShopStore shopStore = shopStoreService.selectById(shopDeliveryCompany.getStoreId());
            //判断shopStore是否为空，如果为空，则抛出异常
            if (shopStore.getId() ==null) {
                throw new CustomException("门店不存在: " + shopDeliveryCompany.getStoreId());
            }
        }
    }
}
