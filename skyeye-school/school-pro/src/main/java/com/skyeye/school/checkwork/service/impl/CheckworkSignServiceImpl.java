/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.checkwork.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.rest.wall.certification.service.ICertificationService;
import com.skyeye.school.checkwork.classenum.CheckworkSignState;
import com.skyeye.school.checkwork.classenum.CheckworkType;
import com.skyeye.school.checkwork.dao.CheckworkSignDao;
import com.skyeye.school.checkwork.entity.Checkwork;
import com.skyeye.school.checkwork.entity.CheckworkSign;
import com.skyeye.school.checkwork.service.CheckworkService;
import com.skyeye.school.checkwork.service.CheckworkSignService;
import com.skyeye.school.subject.entity.SubjectClassesStu;
import com.skyeye.school.subject.service.SubjectClassesStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CheckworkSignServiceImpl
 * @Description: 学生考勤签到服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/24 10:51
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "学生考勤签到", groupName = "考勤管理")
public class CheckworkSignServiceImpl extends SkyeyeBusinessServiceImpl<CheckworkSignDao, CheckworkSign> implements CheckworkSignService {

    @Autowired
    private CheckworkService checkworkService;

    @Autowired
    private SubjectClassesStuService subjectClassesStuService;

    @Autowired
    private ICertificationService iCertificationService;

    @Override
    public void deleteCheckworkSignByCheckworkId(String checkworkId) {
        QueryWrapper<CheckworkSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getCheckworkId), checkworkId);
        remove(queryWrapper);
    }

    @Override
    public List<CheckworkSign> queryCheckworkSignByCheckworkId(String checkworkId) {
        QueryWrapper<CheckworkSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getCheckworkId), checkworkId);
        return list(queryWrapper);
    }

    @Override
    public Map<String, List<CheckworkSign>> queryCheckworkSignByCheckworkId(String... checkworkId) {
        List<String> checkworkIdList = Arrays.asList(checkworkId);
        if (CollectionUtil.isEmpty(checkworkIdList)) {
            return MapUtil.newHashMap();
        }
        QueryWrapper<CheckworkSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getCheckworkId), checkworkId);
        List<CheckworkSign> checkworkSignList = list(queryWrapper);
        Map<String, List<CheckworkSign>> listMap = checkworkSignList.stream()
            .collect(Collectors.groupingBy(CheckworkSign::getCheckworkId));
        return listMap;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void createCheckworkSignBySourceCode(InputObject inputObject, OutputObject outputObject) {
        String sourceCode = inputObject.getParams().get("sourceCode").toString();
        Checkwork checkwork = checkworkService.queryCheckworkBySourceCode(sourceCode);
        if (ObjectUtil.isEmpty(checkwork)) {
            throw new CustomException("未查询到该考勤信息，请确认考勤信息是否正确.");
        }
        if (checkwork.getType() != CheckworkType.SCAN_THE_CODE.getKey()) {
            throw new CustomException("该考勤信息仅支持扫码签到.");
        }
        String userId = inputObject.getLogParams().get("id").toString();
        saveCheckworkSign(userId, checkwork);
    }

    private void saveCheckworkSign(String userId, Checkwork checkwork) {
        // 判断是否为教师用户
        Map<String, Object> userMation = iAuthUserService.queryDataMationById(userId);
        if (CollectionUtil.isNotEmpty(userMation)) {
            throw new CustomException("该用户为教师用户，不能参与考勤签到.");
        }
        // 判断是否认证
        Map<String, Object> certification = iCertificationService.queryCertificationById(userId);
        String studentNumber = certification.get("studentNumber").toString();
        if (CollectionUtil.isEmpty(certification) || StrUtil.isEmpty(studentNumber)) {
            throw new CustomException("认证信息为空，或者认证的学号信息不存在.");
        }

        String signTime = DateUtil.getTimeAndToString();
        long distanceMinute = DateUtil.getDistanceMinute(checkwork.getCreateTime(), signTime);
        if (distanceMinute > checkwork.getMaintainTime()) {
            // 考勤时间已过
            throw new CustomException("考勤时间已过，不能签到.");
        }
        // 判断该学生是否在该考勤信息中
        List<String> studentIds = checkwork.getCheckworkSignList().stream().map(CheckworkSign::getUserId).collect(Collectors.toList());
        if (studentIds.contains(userId)) {
            List<CheckworkSign> notSignList = checkwork.getCheckworkSignList().stream()
                .filter(checkworkSign -> StrUtil.equals(checkworkSign.getUserId(), userId)
                    && checkworkSign.getState() == CheckworkSignState.NOT_SIGN.getKey()).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(notSignList)) {
                throw new CustomException("该学生已签到，请勿重复签到.");
            }
            // 更新签到状态
            UpdateWrapper<CheckworkSign> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getCheckworkId), checkwork.getId());
            updateWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getUserId), userId);
            updateWrapper.set(MybatisPlusUtil.toColumns(CheckworkSign::getState), CheckworkSignState.SIGN.getKey());
            updateWrapper.set(MybatisPlusUtil.toColumns(CheckworkSign::getSignTime), signTime);
            update(updateWrapper);
        } else {
            CheckworkSign checkworkSign = new CheckworkSign();
            checkworkSign.setCheckworkId(checkwork.getId());
            checkworkSign.setUserId(userId);
            checkworkSign.setState(CheckworkSignState.SIGN.getKey());
            checkworkSign.setSignTime(signTime);
            createEntity(checkworkSign, StrUtil.EMPTY);
            // 保存学生信息到班级中
            SubjectClassesStu subjectClassesStu = new SubjectClassesStu();
            subjectClassesStu.setSubClassLinkId(checkwork.getSubClassLinkId());
            subjectClassesStuService.saveToClassStu(subjectClassesStu, userId, false);
        }
    }

    @Override
    public void createCheckworkSignById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String checkworkId = params.get("checkworkId").toString();
        String codeNumber = params.get("codeNumber").toString();
        Checkwork checkwork = checkworkService.selectById(checkworkId);
        if (ObjectUtil.isEmpty(checkwork)) {
            throw new CustomException("未查询到该考勤信息，请确认考勤信息是否正确.");
        }
        if (checkwork.getType() != CheckworkType.DIGIT.getKey()) {
            throw new CustomException("该考勤信息仅支持数字签到.");
        }
        if (!StrUtil.equals(codeNumber, checkwork.getCodeNumber())) {
            throw new CustomException("数字错误，请确认数字是否正确.");
        }
        String userId = inputObject.getLogParams().get("id").toString();
        saveCheckworkSign(userId, checkwork);
    }

    @Override
    public void queryCheckworkWaitSignList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        String userId = inputObject.getLogParams().get("id").toString();
        QueryWrapper<CheckworkSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getUserId), userId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getState), CheckworkSignState.NOT_SIGN.getKey());
        List<CheckworkSign> checkworkSignList = list(queryWrapper);
        checkworkService.setDataMation(checkworkSignList, CheckworkSign::getCheckworkId);
        outputObject.setBeans(checkworkSignList);
        outputObject.settotal(pages.getTotal());
    }

    @Override
    public void queryCheckworkAlreadySignList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        String userId = inputObject.getLogParams().get("id").toString();
        QueryWrapper<CheckworkSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getUserId), userId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(CheckworkSign::getState), CheckworkSignState.SIGN.getKey());
        queryWrapper.orderByDesc(MybatisPlusUtil.toColumns(CheckworkSign::getSignTime));
        List<CheckworkSign> checkworkSignList = list(queryWrapper);
        checkworkService.setDataMation(checkworkSignList, CheckworkSign::getCheckworkId);
        outputObject.setBeans(checkworkSignList);
        outputObject.settotal(pages.getTotal());
    }

}
