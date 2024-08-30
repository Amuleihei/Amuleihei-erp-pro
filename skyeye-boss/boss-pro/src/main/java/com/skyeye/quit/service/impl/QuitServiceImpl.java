/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.quit.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeFlowableServiceImpl;
import com.skyeye.centerrest.entity.staff.UserStaffLeaveRest;
import com.skyeye.centerrest.user.SysEveUserStaffService;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.FlowableStateEnum;
import com.skyeye.common.enumeration.UserStaffState;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.constants.BossConstants;
import com.skyeye.exception.CustomException;
import com.skyeye.quit.dao.QuitDao;
import com.skyeye.quit.entity.Quit;
import com.skyeye.quit.service.QuitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuitServiceImpl
 * @Description: 离职申请服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022-04-25 18:08:53
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "离职申请", groupName = "离职申请", flowable = true)
public class QuitServiceImpl extends SkyeyeFlowableServiceImpl<QuitDao, Quit> implements QuitService {

    @Autowired
    private SysEveUserStaffService sysEveUserStaffService;

    @Override
    public List<Map<String, Object>> queryPageData(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        pageInfo.setCreateId(inputObject.getLogParams().get("id").toString());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryBossInterviewQuitList(pageInfo);
        return beans;
    }

    @Override
    public void validatorEntity(Quit entity) {
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        boolean canApply = isCanApply(userId, entity.getId());
        if (!canApply) {
            throw new CustomException("您已提交过离职申请，请等待审批。");
        }
    }

    @Override
    public Quit selectById(String id) {
        Quit quit = super.selectById(id);
        iAuthUserService.setName(quit, "createId", "createName");
        return quit;
    }

    @Override
    protected void approvalEndIsSuccess(Quit entity) {
        Map<String, Object> userMation = iAuthUserService.queryDataMationById(entity.getCreateId());
        String staffId = userMation.get("staffId").toString();

        // 修改员工信息为离职状态
        UserStaffLeaveRest userStaffLeaveRest = new UserStaffLeaveRest();
        userStaffLeaveRest.setRowId(staffId);
        userStaffLeaveRest.setQuitTime(entity.getLeaveTime());
        userStaffLeaveRest.setQuitReason(entity.getRemark());
        ExecuteFeignClient.get(() -> sysEveUserStaffService.userStaffQuit(userStaffLeaveRest));
        // 删除该员工对应的缓存信息
        BossConstants.deleteCache(entity.getCreateId());
    }

    /**
     * 是否可以提交离职申请。true:可以；false:不可以
     *
     * @param userId 用户id
     * @return true:可以；false:不可以
     */
    private boolean isCanApply(String userId, String id) {
        Map<String, Object> user = iAuthUserService.queryDataMationById(userId);
        Integer state = Integer.parseInt(user.get("state").toString());
        // 是否可以提交离职申请。true:可以；false:不可以
        boolean canApply = false;
        if (state == UserStaffState.ON_THE_JOB.getKey()
            || state == UserStaffState.PROBATION.getKey()
            || state == UserStaffState.PROBATION_PERIOD.getKey()) {
            // 试用期，获取该用户是否有已经添加的离职申请(不是作废状态的)
            QueryWrapper<Quit> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(Quit::getCreateId), userId);
            queryWrapper.ne(MybatisPlusUtil.toColumns(Quit::getState), FlowableStateEnum.INVALID.getKey());
            if (StringUtils.isNotEmpty(id)) {
                queryWrapper.ne(CommonConstants.ID, id);
            }
            List<Quit> list = list(queryWrapper);
            if (CollectionUtil.isEmpty(list)) {
                // 为空，说明还没有提交离职申请
                canApply = true;
            }
        }
        return canApply;
    }
}
