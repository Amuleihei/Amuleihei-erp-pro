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
import com.skyeye.eve.dao.SysStaffEducationDao;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.IEnclosureService;
import com.skyeye.eve.service.ISysDictDataService;
import com.skyeye.eve.service.SysStaffEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysStaffEducationServiceImpl
 * @Description: 员工教育背景管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:38
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysStaffEducationServiceImpl implements SysStaffEducationService {

    @Autowired
    private SysStaffEducationDao sysStaffEducationDao;

    @Autowired
    private IEnclosureService iEnclosureService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    /**
     * 查询所有教育背景列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllSysStaffEducationList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        List<Map<String, Object>> beans = sysStaffEducationDao.queryAllSysStaffEducationList(params);
        iSysDictDataService.getDictDataNameByIdList(beans, "educationId", "educationName");
        iSysDictDataService.getDictDataNameByIdList(beans, "learningModalityId", "learningModalityName");
        iSysDictDataService.getDictDataNameByIdList(beans, "schoolNature", "schoolNatureName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 员工教育背景信息录入
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertSysStaffEducationMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("id", ToolUtil.getSurFaceId());
        map.put("createTime", DateUtil.getTimeAndToString());
        sysStaffEducationDao.insertSysStaffEducationMation(map);
    }

    /**
     * 编辑员工教育背景信息时回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysStaffEducationMationToEdit(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffEducationDao.querySysStaffEducationMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            iSysDictDataService.getDictDataNameByIdBean(certificate, "educationId", "educationName");
            iSysDictDataService.getDictDataNameByIdBean(certificate, "learningModalityId", "learningModalityName");
            iSysDictDataService.getDictDataNameByIdBean(certificate, "schoolNature", "schoolNatureName");
            // 附件
            if (certificate.containsKey("enclosure") && !ToolUtil.isBlank(certificate.get("enclosure").toString())) {
                List<Map<String, Object>> beans = iEnclosureService.queryEnclosureInfoByIds(certificate.get("enclosure").toString());
                certificate.put("enclosureInfo", beans);
            }
            outputObject.setBean(certificate);
            outputObject.settotal(1);
        } else {
            outputObject.setreturnMessage("该教育背景信息不存在.");
        }
    }

    /**
     * 编辑员工教育背景信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editSysStaffEducationMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Map<String, Object> certificate = sysStaffEducationDao.querySysStaffEducationMationToEdit(id);
        if (certificate != null && !certificate.isEmpty()) {
            sysStaffEducationDao.editSysStaffEducationMationById(map);
        } else {
            outputObject.setreturnMessage("该教育背景信息不存在.");
        }
    }

    /**
     * 删除员工教育背景信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteSysStaffEducationMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        sysStaffEducationDao.deleteSysStaffEducationMationById(id);
    }

    /**
     * 查询指定员工的教育背景列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPointStaffSysStaffEducationList(InputObject inputObject, OutputObject outputObject) {
        PointStaffQueryDo pointStaffQuery = inputObject.getParams(PointStaffQueryDo.class);
        Page pages = PageHelper.startPage(pointStaffQuery.getPage(), pointStaffQuery.getLimit());
        List<Map<String, Object>> beans = sysStaffEducationDao.queryPointStaffSysStaffEducationList(pointStaffQuery);
        iSysDictDataService.getDictDataNameByIdList(beans, "educationId", "educationName");
        iSysDictDataService.getDictDataNameByIdList(beans, "learningModalityId", "learningModalityName");
        iSysDictDataService.getDictDataNameByIdList(beans, "schoolNature", "schoolNatureName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
