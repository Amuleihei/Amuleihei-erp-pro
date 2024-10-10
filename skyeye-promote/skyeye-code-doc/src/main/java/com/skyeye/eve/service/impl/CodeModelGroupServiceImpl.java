/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.CodeModelGroupDao;
import com.skyeye.eve.entity.CodeModelGroup;
import com.skyeye.eve.service.CodeModelGroupService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CodeModelGroupServiceImpl
 * @Description: 模板分组管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "模板分组", groupName = "代码生成器")
public class CodeModelGroupServiceImpl extends SkyeyeBusinessServiceImpl<CodeModelGroupDao, CodeModelGroup> implements CodeModelGroupService {

    @Autowired
    private CodeModelGroupDao codeModelGroupDao;

    @Value("${jdbc.database.name}")
    private String dbName;

    @Override
    public void createPrepose(CodeModelGroup entity) {
        entity.setCodeNum(String.valueOf(ToolUtil.card()));
    }

    @Override
    public void deletePreExecution(String id) {
        Map<String, Object> bean = codeModelGroupDao.queryCodeModelNumById(id);
        if (CollectionUtil.isNotEmpty(bean)) {
            if (Integer.parseInt(bean.get("modelNum").toString()) > 0) {
                // 该模板分组下存在模板
                throw new CustomException("该模板分组下存在模板，无法删除。");
            }
        }
    }

    /**
     * 根据表名获取表的相关信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryTableParameterByTableName(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("dbName", dbName);
        List<Map<String, Object>> beans = codeModelGroupDao.queryTableParameterByTableName(map);
        if (beans != null) {
            beans.forEach(bean -> {
                // 设置字段转Java对象字段的值
                setLowerColumnName(bean);
                // 设置必填属性
                setRef(bean);
            });
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    private void setRef(Map<String, Object> bean) {
        String isNullable = bean.get("isNullable").toString();
        String ref = "";
        if ("YES".equals(isNullable)) {
            ref += "required";
        }
        bean.put("ref", ref);
    }

    private void setLowerColumnName(Map<String, Object> bean) {
        String columnName = bean.get("columnName").toString();
        columnName = ToolUtil.replaceUnderLineAndUpperCase(columnName);
        columnName = ToolUtil.toLowerCaseFirstOne(columnName);
        bean.put("lowerColumnName", columnName);
    }

    /**
     * 根据表名获取表的相关转换信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryTableMationByTableName(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = new HashMap<>();
        String tableName = ToolUtil.replaceUnderLineAndUpperCase(map.get("tableName").toString());
        map.put("dbName", dbName);
        Map<String, Object> tableBz = codeModelGroupDao.queryTableBzByTableName(map);
        // 表备注
        bean.put("tableBzName", tableBz.get("tableComment"));
        // 将表名转化为Controller名
        bean.put("tableName", tableName);
        // 表名首字母小写
        bean.put("tableFirstISlowerName", ToolUtil.toLowerCaseFirstOne(tableName));
        // 表名全部小写
        bean.put("tableISlowerName", tableName.toLowerCase());
        bean.put("currentTime", DateUtil.getTimeAndToString());
        bean.put("table", map.get("tableName").toString());
        // 包名
        bean.put("ControllerPackageName", "com.skyeye." + tableName.toLowerCase() + ".controller");
        bean.put("ServicePackageName", "com.skyeye." + tableName.toLowerCase() + ".service");
        bean.put("ServiceImplPackageName", "com.skyeye." + tableName.toLowerCase() + ".service.impl");
        bean.put("DaoPackageName", "com.skyeye." + tableName.toLowerCase() + ".dao");
        outputObject.setBean(bean);
    }

    /**
     * 根据分组id获取模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCodeModelListByGroupId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = codeModelGroupDao.queryCodeModelListByGroupId(map);
        if (beans != null) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

}
