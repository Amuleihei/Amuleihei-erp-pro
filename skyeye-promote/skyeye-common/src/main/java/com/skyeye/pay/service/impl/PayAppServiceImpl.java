/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
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
import com.skyeye.pay.dao.PayAppDao;
import com.skyeye.pay.entity.PayApp;
import com.skyeye.pay.service.PayAppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PayAppServiceImpl
 * @Description: 支付应用服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "支付应用管理", groupName = "支付应用管理")
public class PayAppServiceImpl extends SkyeyeBusinessServiceImpl<PayAppDao, PayApp> implements PayAppService {

    @Override
    public void updatePrepose(PayApp payApp) {
        verify(payApp.getId());
    }

<<<<<<< HEAD
=======
    @Override
    public void updateEnabled(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        String enabled = params.get("enabled").toString();
        verify(id);
        UpdateWrapper<PayApp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(MybatisPlusUtil.toColumns(PayApp::getEnabled), enabled)
            .eq(CommonConstants.ID, id);
        update(updateWrapper);
        refreshCache(id);
    }

>>>>>>> 6ea701f278e38aa5a863728a89295f400d79faff
    private void verify(String id){
        QueryWrapper<PayApp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstants.ID, id);
        PayApp one = getOne(queryWrapper);
        if (ObjectUtil.isEmpty(one)) {
            throw new CustomException("该支付应用信息不存在");
        }
    }

    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        QueryWrapper<PayApp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(PayApp::getEnabled), CommonNumConstants.NUM_ONE);
        List<PayApp> list = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(list), null);
    }
}
