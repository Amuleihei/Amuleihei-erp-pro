/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.afterseal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.base.Joiner;
import com.skyeye.afterseal.classenum.AfterSealState;
import com.skyeye.afterseal.dao.AfterSealDao;
import com.skyeye.afterseal.entity.AfterSeal;
import com.skyeye.afterseal.service.AfterSealService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.crm.service.ICustomerService;
import com.skyeye.erp.service.IMaterialService;
import com.skyeye.eve.rest.mq.JobMateMation;
import com.skyeye.eve.service.IJobMateMationService;
import com.skyeye.exception.CustomException;
import com.skyeye.worker.service.SealWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SealSeServiceServiceImpl
 * @Description: 售后服务工单管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/8 21:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "售后工单管理", groupName = "售后工单")
public class AfterSealServiceImpl extends SkyeyeBusinessServiceImpl<AfterSealDao, AfterSeal> implements AfterSealService {

    @Autowired
    private IJobMateMationService iJobMateMationService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IMaterialService iMaterialService;

    @Autowired
    private SealWorkerService sealWorkerService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        setPageInfoOfType(pageInfo, inputObject.getLogParams().get("id").toString());
        List<Map<String, Object>> beans = skyeyeBaseMapper.querySealServiceOrderList(pageInfo);

        iCustomerService.setMationForMap(beans, "holderId", "holderMation");
        iAuthUserService.setMationForMap(beans, "declarationId", "declarationMation");
        iAuthUserService.setMationForMap(beans, "serviceUserId", "serviceUserMation");
        return beans;
    }

    private void setPageInfoOfType(CommonPageInfo pageInfo, String userId) {
        String state = pageInfo.getState();
        if (StrUtil.isEmpty(state)) {
            return;
        }
        if (StrUtil.equals(state, AfterSealState.BE_DISPATCHED.getKey())
            || StrUtil.equals(state, AfterSealState.BE_EVALUATED.getKey())
            || StrUtil.equals(state, AfterSealState.AUDIT.getKey())
            || StrUtil.equals(state, AfterSealState.COMPLATE.getKey())) {
            // 待派工，待评价，待审核，已完工的工单查询所有的
        } else {
            pageInfo.setCreateId(userId);
        }
    }

    @Override
    public AfterSeal selectById(String id) {
        AfterSeal afterSeal = super.selectById(id);

        iAuthUserService.setDataMation(afterSeal, AfterSeal::getDeclarationId);
        afterSeal.setServiceUserMation(sealWorkerService.selectByUserId(afterSeal.getServiceUserId()));
        if (CollectionUtil.isNotEmpty(afterSeal.getCooperationUserId())) {
            afterSeal.setCooperationUserMation(iAuthUserService.queryDataMationByIds(Joiner.on(CommonCharConstants.COMMA_MARK).join(afterSeal.getCooperationUserId())));
        }

        iMaterialService.setDataMation(afterSeal, AfterSeal::getProductId);
        iCustomerService.setDataMation(afterSeal, AfterSeal::getHolderId);
        return afterSeal;
    }

    @Override
    public void createPrepose(AfterSeal entity) {
        Map<String, Object> business = BeanUtil.beanToMap(entity);
        String oddNumber = iCodeRuleService.getNextCodeByClassName(this.getClass().getName(), business);
        entity.setOddNumber(oddNumber);
        if (StrUtil.isEmpty(entity.getServiceUserId())) {
            entity.setState(AfterSealState.BE_DISPATCHED.getKey());
        } else {
            // 默认有接单人
            entity.setState(AfterSealState.PENDING_ORDERS.getKey());
            entity.setServiceTime(DateUtil.getTimeAndToString());
        }
        entity.setDeclarationId(InputObject.getLogParamsStatic().get("id").toString());
    }

    @Override
    protected void validatorEntity(AfterSeal entity) {
        if (StrUtil.isNotEmpty(entity.getId())) {
            AfterSeal afterSeal = selectById(entity.getId());
            if (StrUtil.equals(afterSeal.getState(), AfterSealState.BE_DISPATCHED.getKey())
                || StrUtil.equals(afterSeal.getState(), AfterSealState.PENDING_ORDERS.getKey())) {
                // 待派工，待接单可以进行编辑
            } else {
                throw new CustomException("该数据状态已改变，请刷新页面！");
            }
        }
    }

    @Override
    protected void updatePrepose(AfterSeal entity) {
        if (StrUtil.isEmpty(entity.getServiceUserId())) {
            entity.setState(AfterSealState.BE_DISPATCHED.getKey());
        } else {
            // 默认有接单人
            entity.setState(AfterSealState.PENDING_ORDERS.getKey());
            entity.setServiceTime(DateUtil.getTimeAndToString());
        }
    }

    @Override
    protected void writePostpose(AfterSeal entity, String userId) {
        super.writePostpose(entity, userId);

        sendDispatchWork(entity.getId(), userId);
    }

    private void sendDispatchWork(String id, String userId) {
        // 发送消息
        Map<String, Object> notice = new HashMap<>();
        notice.put("serviceId", id);
        notice.put("type", MqConstants.JobMateMationJobType.WATI_WORKER_SEND.getJobType());
        JobMateMation jobMateMation = new JobMateMation();
        jobMateMation.setJsonStr(JSONUtil.toJsonStr(notice));
        jobMateMation.setUserId(userId);
        iJobMateMationService.sendMQProducer(jobMateMation);
    }

    /**
     * 派工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editSealSeServiceWaitToWorkMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        AfterSeal afterSeal = selectById(id);
        if (StrUtil.equals(afterSeal.getState(), AfterSealState.BE_DISPATCHED.getKey())) {
            // 待派工可以进行派工
            UpdateWrapper<AfterSeal> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(CommonConstants.ID, afterSeal.getId());
            updateWrapper.set(MybatisPlusUtil.toColumns(AfterSeal::getState), AfterSealState.PENDING_ORDERS.getKey());
            updateWrapper.set(MybatisPlusUtil.toColumns(AfterSeal::getServiceUserId), map.get("serviceUserId").toString());
            updateWrapper.set(MybatisPlusUtil.toColumns(AfterSeal::getCooperationUserId), map.get("cooperationUserId").toString());
            updateWrapper.set(MybatisPlusUtil.toColumns(AfterSeal::getServiceTime), DateUtil.getTimeAndToString());
            update(updateWrapper);
            // 派工成功mq消息任务
            sendDispatchWork(id, afterSeal.getCreateId());
            refreshCache(id);
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 接单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void receivingSealSeServiceOrderById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        AfterSeal afterSeal = selectById(map.get("id").toString());
        if (StrUtil.equals(afterSeal.getState(), AfterSealState.PENDING_ORDERS.getKey())) {
            // 待接单可以进行接单
            updateStateById(afterSeal.getId(), AfterSealState.BE_SIGNED.getKey());
            refreshCache(afterSeal.getId());
        } else {
            outputObject.setreturnMessage("该数据状态已改变，请刷新页面！");
        }
    }

    @Override
    public void deletePreExecution(AfterSeal afterSeal) {
        if (StrUtil.equals(afterSeal.getState(), AfterSealState.BE_DISPATCHED.getKey())
            || StrUtil.equals(afterSeal.getState(), AfterSealState.PENDING_ORDERS.getKey())) {
            // 待派工/待接单可以进行删除
        } else {
            throw new CustomException("该数据状态已改变，请刷新页面！");
        }
    }

    /**
     * 查询我的待完工状态的工单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySealSeServiceSignon(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<AfterSeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AfterSeal::getServiceUserId), InputObject.getLogParamsStatic().get("id").toString());
        queryWrapper.eq(MybatisPlusUtil.toColumns(AfterSeal::getState), AfterSealState.BE_COMPLETED.getKey());
        List<AfterSeal> afterSealList = list(queryWrapper);
        outputObject.setBeans(afterSealList);
        outputObject.settotal(afterSealList.size());
    }

    /**
     * 工单完工操作
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void auditSealSeServiceOrderById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        AfterSeal afterSeal = selectById(map.get("id").toString());
        if (StrUtil.equals(afterSeal.getState(), AfterSealState.BE_COMPLETED.getKey())) {
            // 只有待完工状态下可以完工，修改为待评价状态
            updateStateById(afterSeal.getId(), AfterSealState.BE_EVALUATED.getKey());
            refreshCache(afterSeal.getId());
        } else {
            throw new CustomException("该数据状态已改变，请刷新页面！");
        }
    }

    @Override
    public void updateStateById(String id, String state) {
        UpdateWrapper<AfterSeal> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(AfterSeal::getState), state);
        update(updateWrapper);
        refreshCache(id);
    }

    /**
     * 完工审核操作
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void finishSealSeServiceOrderById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        AfterSeal afterSeal = selectById(map.get("id").toString());
        if (StrUtil.equals(afterSeal.getState(), AfterSealState.AUDIT.getKey())) {
            // 待审核状态可以进行审核完工
            updateStateById(afterSeal.getId(), AfterSealState.COMPLATE.getKey());
            refreshCache(afterSeal.getId());
        } else {
            throw new CustomException("该数据状态已改变，请刷新页面！");
        }
    }

}
