/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.assets.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.PropertiesUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.assets.classenum.AssetReportState;
import com.skyeye.eve.assets.dao.AssetReportDao;
import com.skyeye.eve.assets.entity.AssetReport;
import com.skyeye.eve.assets.entity.AssetReportQueryDo;
import com.skyeye.eve.assets.service.AssetReportService;
import com.skyeye.eve.assets.service.AssetService;
import com.skyeye.eve.rest.mq.JobMateMation;
import com.skyeye.eve.service.IBarCodeService;
import com.skyeye.eve.service.IJobMateMationService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AssetReportServiceImpl
 * @Description: 资产明细服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/9 14:49
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "资产明细管理", groupName = "资产模块")
public class AssetReportServiceImpl extends SkyeyeBusinessServiceImpl<AssetReportDao, AssetReport> implements AssetReportService {

    @Autowired
    private IBarCodeService iBarCodeService;

    @Autowired
    private IJobMateMationService iJobMateMationService;

    @Autowired
    private AssetService assetService;

    @Override
    public void getQueryWrapper(InputObject inputObject, QueryWrapper<AssetReport> wrapper) {
        setCustomerWrapper(inputObject, wrapper);
    }

    private static void setCustomerWrapper(InputObject inputObject, QueryWrapper<AssetReport> wrapper) {
        AssetReportQueryDo commonPageInfo = inputObject.getParams(AssetReportQueryDo.class);
        if (StrUtil.isNotEmpty(commonPageInfo.getAssetId())) {
            wrapper.eq(MybatisPlusUtil.toColumns(AssetReport::getAssetId), commonPageInfo.getAssetId());
        }
        if (StrUtil.equals("unPut", commonPageInfo.getState())) {
            // 未入库的（状态为空）
            String stateKey = MybatisPlusUtil.toColumns(AssetReport::getState);
            wrapper.isNull(stateKey).or().eq(stateKey, StrUtil.EMPTY);
        }
        if (StrUtil.equals("unUse", commonPageInfo.getState())) {
            // 未申领的
            String useUserIdKey = MybatisPlusUtil.toColumns(AssetReport::getUseUserId);
            wrapper.isNull(useUserIdKey).or().eq(useUserIdKey, StrUtil.EMPTY);
            // && 状态不能为空
            String stateKey = MybatisPlusUtil.toColumns(AssetReport::getState);
            wrapper.isNotNull(stateKey).ne(stateKey, StrUtil.EMPTY).ne(stateKey, AssetReportState.RETURN.getKey());
        }
        if (StrUtil.equals("myUse", commonPageInfo.getState())) {
            // 我借用中的
            String userId = inputObject.getLogParams().get("id").toString();
            wrapper.eq(MybatisPlusUtil.toColumns(AssetReport::getUseUserId), userId);
        }
        wrapper.orderByDesc(MybatisPlusUtil.toColumns(AssetReport::getCreateTime));
        wrapper.orderByDesc(MybatisPlusUtil.toColumns(AssetReport::getAssetNum));
    }

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        List<Map<String, Object>> beans = super.queryPageDataList(inputObject);
        iAuthUserService.setMationForMap(beans, "assetAdmin", "assetAdminMation");
        iAuthUserService.setMationForMap(beans, "useUserId", "useUserMation");
        iAuthUserService.setMationForMap(beans, "revertUserId", "revertUserMation");

        assetService.setMationForMap(beans, "assetId", "assetMation");

        iBarCodeService.setBarCodeMationForMap(beans, "id", getServiceClassName());
        return beans;
    }

    @Override
    public void deletePreExecution(String id) {
        AssetReport assetReport = selectById(id);
        if (assetReport.getState() != null) {
            throw new CustomException("该编码正常使用中，无法删除");
        }
    }

    @Override
    public void deletePostpose(String id) {
        iBarCodeService.deleteByObjectId(id);
    }

    @Override
    public void setAssetReportEmployee(String id, String useId, String useUserId) {
        UpdateWrapper<AssetReport> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        AssetReport assetReportMation = new AssetReport();
        assetReportMation.setUseId(useId);
        assetReportMation.setUseUserId(useUserId);
        // 设置归还信息为空
        assetReportMation.setRevertId(StringUtil.EMPTY);
        assetReportMation.setRevertUserId(StringUtil.EMPTY);
        update(assetReportMation, updateWrapper);
        refreshCache(id);
    }

    @Override
    public void setAssetReportRevert(String id, String revertId, String revertUserId) {
        UpdateWrapper<AssetReport> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        AssetReport assetReportMation = new AssetReport();
        assetReportMation.setRevertId(revertId);
        assetReportMation.setRevertUserId(revertUserId);
        // 设置领用信息为空
        assetReportMation.setUseId(StringUtil.EMPTY);
        assetReportMation.setUseUserId(StringUtil.EMPTY);
        update(assetReportMation, updateWrapper);
        refreshCache(id);
    }

    @Override
    public Long getAssetReportNumByAssetId(String assetId) {
        QueryWrapper<AssetReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AssetReport::getAssetId), assetId);
        return count(queryWrapper);
    }

    @Override
    public List<AssetReport> queryAssetReportListByCodeNum(List<String> codeNumList, Boolean depotState) {
        codeNumList = codeNumList.stream().distinct().collect(Collectors.toList());
        QueryWrapper<AssetReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(AssetReport::getAssetNum), codeNumList);
        if (!depotState) {
            String stateKey = MybatisPlusUtil.toColumns(AssetReport::getState);
            queryWrapper.isNull(stateKey).or().eq(stateKey, StrUtil.EMPTY);
        } else {
            String stateKey = MybatisPlusUtil.toColumns(AssetReport::getState);
            queryWrapper.isNotNull(stateKey).ne(stateKey, StrUtil.EMPTY);
        }
        List<AssetReport> assetReportList = list(queryWrapper);
        return assetReportList;
    }

    @Override
    public void insertAssetReport(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> list = JSONUtil.toList(inputObject.getParams().get("list").toString(), null);
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        List<String> assetIdList = list.stream().map(bean -> bean.get("assetId").toString()).distinct().collect(Collectors.toList());
        if (list.size() != assetIdList.size()) {
            throw new CustomException("存在重复的资产信息，请确认");
        }

        // 创建任务
        Map<String, Object> jobBody = new HashMap<>();
        // 是否创建任务
        jobBody.put("whetherCreatTask", false);
        jobBody.put("list", JSONUtil.toJsonStr(list));
        jobBody.put("className", getServiceClassName());
        jobBody.put("userId", inputObject.getLogParams().get("id").toString());
        String topic = PropertiesUtil.getPropertiesValue("${topic.asset-generate-barcode}");
        jobBody.put("topic", topic);
        JobMateMation jobMateMation = new JobMateMation();
        jobMateMation.setJsonStr(JSONUtil.toJsonStr(jobBody));
        jobMateMation.setUserId(inputObject.getLogParams().get("id").toString());
        iJobMateMationService.sendMQProducer(jobMateMation);
    }

    @Override
    public void queryAssetReportCodeList(InputObject inputObject, OutputObject outputObject) {
        AssetReportQueryDo commonPageInfo = inputObject.getParams(AssetReportQueryDo.class);
        QueryWrapper<AssetReport> wrapper = new QueryWrapper<>();
        setCustomerWrapper(inputObject, wrapper);

        if (commonPageInfo.getNumber() != null) {
            wrapper.last(String.format(Locale.ROOT, "limit %s", commonPageInfo.getNumber()));
        } else {
            wrapper.last(String.format(Locale.ROOT, "limit %s", 20));
        }
        wrapper.select(MybatisPlusUtil.toColumns(AssetReport::getAssetNum));
        List<AssetReport> assetReportList = list(wrapper);
        List<String> codeNumList = assetReportList.stream().map(AssetReport::getAssetNum).collect(Collectors.toList());
        outputObject.setBeans(codeNumList);
        outputObject.settotal(codeNumList.size());
    }

}
