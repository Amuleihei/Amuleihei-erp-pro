/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEnclosureDao;
import com.skyeye.eve.dao.SysStaffJobResumeDao;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.SysStaffJobResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffJobResumeServiceImpl
 * @Description: 员工工作履历管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysStaffJobResumeServiceImpl implements SysStaffJobResumeService {

    @Autowired
    private SysStaffJobResumeDao sysStaffJobResumeDao;

    @Autowired
    private SysEnclosureDao sysEnclosureDao;

    /**
     * 查询所有工作履历列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryAllSysStaffJobResumeList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        List<Map<String, Object>> beans = sysStaffJobResumeDao.queryAllSysStaffJobResumeList(params);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 员工工作履历信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysStaffJobResumeMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        sysStaffJobResumeDao.insertSysStaffJobResumeMation(map);
    }

    /**
     * 编辑员工工作履历信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySysStaffJobResumeMationToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffJobResumeDao.querySysStaffJobResumeMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            // 附件
            if (certificate.containsKey("enclosure") && !ToolUtil.isBlank(certificate.get("enclosure").toString())) {
                List<Map<String, Object>> beans = sysEnclosureDao.queryEnclosureInfo(certificate.get("enclosure").toString());
                certificate.put("enclosureInfo", beans);
            }
            outputObject.setBean(certificate);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("该工作履历信息不存在.");
        }
    }

    /**
     * 编辑员工工作履历信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysStaffJobResumeMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffJobResumeDao.querySysStaffJobResumeMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysStaffJobResumeDao.editSysStaffJobResumeMationById(map);
        } else {
            outputObject.setreturnMessage("该工作履历信息不存在.");
        }
    }

    /**
     * 删除员工工作履历信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysStaffJobResumeMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysStaffJobResumeDao.deleteSysStaffJobResumeMationById(id);
    }

    /**
     * 查询指定员工的工作履历列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryPointStaffSysStaffJobResumeList(InputObject inputObject, OutputObject outputObject) {
        PointStaffQueryDo pointStaffQuery = inputObject.getParams(PointStaffQueryDo.class);
        Page pages = PageHelper.startPage(pointStaffQuery.getPage(), pointStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffJobResumeDao.queryPointStaffSysStaffJobResumeList(pointStaffQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
