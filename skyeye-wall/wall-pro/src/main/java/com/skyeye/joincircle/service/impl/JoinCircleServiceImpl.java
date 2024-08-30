/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.joincircle.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.circle.service.CircleService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.joincircle.dao.JoinCircleDao;
import com.skyeye.joincircle.entity.JoinCircle;
import com.skyeye.joincircle.service.JoinCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: JoinCircleServiceImpl
 * @Description: 加入圈子服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "加入圈子管理", groupName = "加入圈子管理")
public class JoinCircleServiceImpl extends SkyeyeBusinessServiceImpl<JoinCircleDao, JoinCircle> implements JoinCircleService {

    @Autowired
    private CircleService circleService;

    @Override
    public void validatorEntity(JoinCircle joinCircle) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        joinCircle.setCreateId(userId);
        joinCircle.setCreateTime(DateUtil.getTimeAndToString());
    }

    @Override
    public void createPostpose(JoinCircle joinCircle, String userId) {
        QueryWrapper<JoinCircle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(JoinCircle::getCreateId), joinCircle.getCircleId());
        long count = count(queryWrapper);
        circleService.updateJoinNum(joinCircle.getCircleId(), (int) count);
    }

    @Override
    public void deletePreExecution(JoinCircle joinCircle) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        if (!userId.equals(joinCircle.getCreateId())) {
            throw new CustomException("无权限!");
        }
    }

    @Override
    public void deletePostpose(JoinCircle joinCircle) {
        QueryWrapper<JoinCircle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(JoinCircle::getCreateId), joinCircle.getCircleId());
        long count = count(queryWrapper);
        circleService.updateJoinNum(joinCircle.getCircleId(), (int) count);
    }

    @Override
    public JoinCircle selectByCircleId(String circleId, String userId) {
        QueryWrapper<JoinCircle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(JoinCircle::getCircleId), circleId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(JoinCircle::getCreateId), userId);
        JoinCircle joinCircle = getOne(queryWrapper);
        return ObjectUtil.isEmpty(joinCircle) ? new JoinCircle(): joinCircle;
    }
}
