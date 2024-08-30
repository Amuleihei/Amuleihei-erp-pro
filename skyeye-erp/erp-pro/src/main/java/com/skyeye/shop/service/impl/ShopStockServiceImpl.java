/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.depot.classenum.DepotPutOutType;
import com.skyeye.exception.CustomException;
import com.skyeye.material.service.MaterialNormsService;
import com.skyeye.material.service.MaterialService;
import com.skyeye.rest.shop.service.IShopStoreService;
import com.skyeye.shop.dao.ShopStockDao;
import com.skyeye.shop.entity.ShopStock;
import com.skyeye.shop.service.ShopStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopStockServiceImpl
 * @Description: 门店物料库存信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/31 16:58
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "门店物料库存信息", groupName = "门店物料库存", manageShow = false)
public class ShopStockServiceImpl extends SkyeyeBusinessServiceImpl<ShopStockDao, ShopStock> implements ShopStockService {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialNormsService materialNormsService;

    @Autowired
    private IShopStoreService iShopStoreService;

    @Override
    public QueryWrapper<ShopStock> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopStock> queryWrapper = super.getQueryWrapper(commonPageInfo);
        if (StrUtil.equals(commonPageInfo.getType(), "store")) {
            // 门店id
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStock::getStoreId), commonPageInfo.getHolderId());
        }
        return queryWrapper;
    }

    @Override
    protected List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        materialService.setMationForMap(beans, "materialId", "materialMation");
        materialNormsService.setMationForMap(beans, "normsId", "normsMation");
        iShopStoreService.setMationForMap(beans, "storeId", "storeMation");
        return beans;
    }

    @Override
    public void updateShopStock(String storeId, String materialId, String normsId, Integer operNumber, int type) {
        ShopStock shopStock = queryShopStock(storeId, normsId);
        // 如果该规格在指定门店中已经有存储数据，则直接做修改
        if (ObjectUtil.isNotEmpty(shopStock)) {
            int stock = shopStock.getStock();
            if (type == DepotPutOutType.PUT.getKey()) {
                // 入库
                stock = stock + operNumber;
            } else if (type == DepotPutOutType.OUT.getKey()) {
                // 出库
                stock = stock - operNumber;
            }
            editShopStock(storeId, normsId, stock);
        } else {
            int stockNum = 0;
            if (type == DepotPutOutType.PUT.getKey()) {
                // 入库
                stockNum = operNumber;
            } else if (type == DepotPutOutType.OUT.getKey()) {
                // 出库
                stockNum = stockNum - operNumber;
            }
            if (stockNum < 0) {
                throw new CustomException("门店库存存量不足.");
            }
            saveShopStock(storeId, materialId, normsId, stockNum);
        }
    }

    @Override
    public ShopStock queryShopStock(String storeId, String normsId) {
        QueryWrapper<ShopStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStock::getStoreId), storeId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStock::getNormsId), normsId);
        return getOne(queryWrapper);
    }

    private void editShopStock(String storeId, String normsId, int stock) {
        UpdateWrapper<ShopStock> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(MybatisPlusUtil.toColumns(ShopStock::getStoreId), storeId);
        updateWrapper.eq(MybatisPlusUtil.toColumns(ShopStock::getNormsId), normsId);
        updateWrapper.set(MybatisPlusUtil.toColumns(ShopStock::getStock), stock);
        update(updateWrapper);
    }

    private void saveShopStock(String storeId, String materialId, String normsId, int stock) {
        ShopStock departmentStock = new ShopStock();
        departmentStock.setStoreId(storeId);
        departmentStock.setMaterialId(materialId);
        departmentStock.setNormsId(normsId);
        departmentStock.setStock(stock);
        save(departmentStock);
    }

    @Override
    public Map<String, Integer> queryNormsShopStock(String storeId, List<String> normsIds) {
        QueryWrapper<ShopStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopStock::getStoreId), storeId);
        queryWrapper.in(MybatisPlusUtil.toColumns(ShopStock::getNormsId), normsIds);
        List<ShopStock> departmentStockList = list(queryWrapper);

        Map<String, Integer> stockMap = departmentStockList.stream()
            .collect(Collectors.toMap(ShopStock::getNormsId, ShopStock::getStock));
        normsIds.forEach(normsId -> {
            if (!stockMap.containsKey(normsId)) {
                stockMap.put(normsId, 0);
            }
        });
        return stockMap;
    }

}
