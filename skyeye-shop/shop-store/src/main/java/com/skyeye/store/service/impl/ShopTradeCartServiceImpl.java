/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.rest.shopmaterialnorms.sevice.IShopMaterialNormsService;
import com.skyeye.store.dao.ShopTradeCartDao;
import com.skyeye.store.entity.ShopTradeCart;
import com.skyeye.store.service.ShopTradeCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: ShopTradeCartServiceImpl
 * @Description: 购物车管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:07
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "购物车管理", groupName = "购物车管理")
public class ShopTradeCartServiceImpl extends SkyeyeBusinessServiceImpl<ShopTradeCartDao, ShopTradeCart> implements ShopTradeCartService {

    @Autowired
    private ShopTradeCartService shopTradeCartService;

    @Autowired
    private IShopMaterialNormsService iShopMaterialNormsService;

    @Override
    public void validatorEntity(ShopTradeCart shopTradeCart) {
        if (shopTradeCart.getCount() <= CommonNumConstants.NUM_ZERO) {
            throw new CustomException("商品数量不能小于1");
        }
    }

    @Override
    public void queryMyShopTradeCartList(InputObject inputObject, OutputObject outputObject) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        QueryWrapper<ShopTradeCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopTradeCart::getCreateId), userId);
        List<Map<String, Object>> mapList = listMaps(queryWrapper);
        if (CollectionUtil.isEmpty(mapList)) {
            return;
        }
        List<String> normsIds = mapList.stream()
            .map(map -> map.get("norms_id").toString()).collect(Collectors.toList());
        List<Map<String, Object>> materiaList = iShopMaterialNormsService.
            queryShopMaterialByNormsIdList(normsIds.toString().replaceAll("\\[|]", ""));
        Map<String, Object> materialMap = materiaList.stream().collect(Collectors.
            toMap(key -> key.get("materialId").toString(), value -> value.get("materialMation")));
        Map<String, Object> normsMap = materiaList.stream().collect(Collectors.
            toMap(key -> key.get("normsId").toString(), value -> value.get("normsMation")));
        Map<String, Long> salePriceMap = materiaList.stream().collect(Collectors.
            toMap(key -> key.get("materialId").toString(), value -> Long.parseLong(value.get("salePrice").toString())));

        Map<String, Object> priceAndNum = new HashMap<>();
        final Long[] sum = {0L};
        final int[] allCount = {0};
        mapList.forEach(map -> {
            String materialId = map.get("material_id").toString();
            String normsId = map.get("norms_id").toString();
            if (CollectionUtil.isNotEmpty(materialMap)) {
                map.put("materialMaton", materialMap.get(materialId));
            }
            if (CollectionUtil.isNotEmpty(normsMap)) {
                map.put("normsMation", normsMap.get(normsId));
            }
            if (CollectionUtil.isNotEmpty(salePriceMap)) {
                int count = (int) map.get("count");
                Long price = count * salePriceMap.get(materialId);
                sum[0] = sum[0] + price;
                allCount[0] = allCount[0] + count;
            }
        });
        priceAndNum.put("price", sum[0]);
        priceAndNum.put("allCount", allCount[0]);
        mapList.add(priceAndNum);
        outputObject.setBeans(mapList);
        outputObject.settotal(mapList.size() - CommonNumConstants.NUM_ONE);
    }

    @Override
    public void changeSelected(InputObject inputObject, OutputObject outputObject) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        String id = inputObject.getParams().get("id").toString();
        UpdateWrapper<ShopTradeCart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        ShopTradeCart one = getOne(updateWrapper);
        if (!userId.equals(one.getCreateId())) {
            throw new CustomException("无权限!");
        }
        if (Objects.equals(one.getSelected(), WhetherEnum.ENABLE_USING.getKey())) {
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopTradeCart::getSelected), WhetherEnum.DISABLE_USING.getKey());
        } else {
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopTradeCart::getSelected), WhetherEnum.ENABLE_USING.getKey());
        }
        update(updateWrapper);
        outputObject.setBean(getOne(updateWrapper));
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void changeCount(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        Integer sign = Integer.parseInt(params.get("sign").toString());
        UpdateWrapper<ShopTradeCart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        ShopTradeCart one = getOne(updateWrapper);
        Integer count = one.getCount();
        if (Objects.equals(sign, CommonNumConstants.NUM_ONE)) {
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopTradeCart::getCount), count + CommonNumConstants.NUM_ONE);
        } else {
            if (count <= CommonNumConstants.NUM_ONE) {
                throw new CustomException("商品数量不能小于1");
            }
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopTradeCart::getCount), count - CommonNumConstants.NUM_ONE);
        }
        update(updateWrapper);
        outputObject.setBean(getOne(updateWrapper));
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void resetShopTradeCart(InputObject inputObject, OutputObject outputObject) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        QueryWrapper<ShopTradeCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopTradeCart::getCreateId), userId);
        List<ShopTradeCart> list = list(queryWrapper);
        remove(queryWrapper);
        List<String> idCollect = list.stream()
            .map(ShopTradeCart::getId).collect(Collectors.toList());
        outputObject.setBeans(idCollect);
        outputObject.settotal(idCollect.size());
    }

    @Override
    public void deleteByIds(InputObject inputObject, OutputObject outputObject) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(","));
        QueryWrapper<ShopTradeCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(CommonConstants.ID, idList);
        List<ShopTradeCart> list = list(queryWrapper);
        List<ShopTradeCart> MyCartIds = list.stream().filter(
            shopTradeCart -> shopTradeCart.getCreateId().equals(userId)
        ).collect(Collectors.toList());
        if (MyCartIds.size() == list.size()) {
            shopTradeCartService.deleteById(idList);
        } else {
            throw new CustomException("存在无权限信息");
        }
    }
}
