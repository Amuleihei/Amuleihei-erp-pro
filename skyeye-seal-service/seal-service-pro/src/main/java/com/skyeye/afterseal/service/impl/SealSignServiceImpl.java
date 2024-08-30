/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.service.impl;

import cn.hutool.core.util.StrUtil;
import com.skyeye.afterseal.classenum.AfterSealState;
import com.skyeye.afterseal.dao.SealSignDao;
import com.skyeye.afterseal.entity.AfterSeal;
import com.skyeye.afterseal.entity.SealSign;
import com.skyeye.afterseal.service.AfterSealService;
import com.skyeye.afterseal.service.SealSignService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SealSignServiceImpl
 * @Description: 工人签到信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 13:26
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "工人签到信息", groupName = "售后工单")
public class SealSignServiceImpl extends SkyeyeBusinessServiceImpl<SealSignDao, SealSign> implements SealSignService {

    @Autowired
    private AfterSealService afterSealService;

    @Override
    protected List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.querySealSignList(commonPageInfo);
        return beans;
    }

    @Override
    protected void validatorEntity(SealSign entity) {
        AfterSeal afterSeal = afterSealService.selectById(entity.getObjectId());
        if (StrUtil.equals(afterSeal.getState(), AfterSealState.BE_SIGNED.getKey())) {
            // 待签到可以进行签到
        } else {
            throw new CustomException("该工单已经签到。");
        }
    }

    @Override
    protected void createPrepose(SealSign entity) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        entity.setSignId(userId);
        entity.setSignTime(DateUtil.getTimeAndToString());
    }

    @Override
    protected void createPostpose(SealSign entity, String userId) {
        // 修改工单信息为【待完成】
        afterSealService.updateStateById(entity.getObjectId(), AfterSealState.BE_COMPLETED.getKey());
    }
}
