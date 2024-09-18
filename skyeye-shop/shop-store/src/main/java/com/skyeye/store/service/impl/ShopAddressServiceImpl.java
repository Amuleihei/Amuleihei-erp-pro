/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.WhetherEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.store.dao.ShopAddressDao;
import com.skyeye.store.entity.ShopAddress;
import com.skyeye.store.service.ShopAddressService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopAddressServiceImpl
 * @Description: 收件地址管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/4 10:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "收件地址管理", groupName = "收件地址管理")
public class ShopAddressServiceImpl extends SkyeyeBusinessServiceImpl<ShopAddressDao, ShopAddress> implements ShopAddressService {

    @Override
    public void writePostpose(ShopAddress shopAddress, String userId) {
        if (WhetherEnum.ENABLE_USING.getKey().equals(shopAddress.getIsDefault())) {
            UpdateWrapper<ShopAddress> updateWrapper = new UpdateWrapper<>();
            updateWrapper.ne(CommonConstants.ID, shopAddress.getId());
            updateWrapper.eq(MybatisPlusUtil.toColumns(ShopAddress::getCreateId), userId);
            updateWrapper.eq(MybatisPlusUtil.toColumns(ShopAddress::getIsDefault), WhetherEnum.ENABLE_USING.getKey());
            ShopAddress one = getOne(updateWrapper);
            if (ObjectUtil.isEmpty(one)) {
                return;
            }
            updateWrapper.set(MybatisPlusUtil.toColumns(ShopAddress::getIsDefault), WhetherEnum.DISABLE_USING.getKey());
            update(updateWrapper);
            refreshCache(one.getId());
        }
    }

    @Override
    public void deleteById(InputObject inputObject, OutputObject outputObject) {
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(CommonCharConstants.COMMA_MARK));
        super.deleteById(idList);
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        QueryWrapper<ShopAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopAddress::getCreateId), userId);
        List<ShopAddress> list = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(list), null);
    }

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject){
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        QueryWrapper<ShopAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopAddress::getCreateId), userId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopAddress::getIsDefault), CommonNumConstants.NUM_ONE);
        List<ShopAddress> list = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(list), null);
    }

    @Override
    public void queryShopAddressByCreateId(InputObject inputObject, OutputObject outputObject) {
        String userId = inputObject.getParams().get("userId").toString();
        QueryWrapper<ShopAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopAddress::getCreateId), userId);
        List<ShopAddress> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }
}
