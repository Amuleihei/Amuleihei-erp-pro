/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveModelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveModelTypeController {

    @Autowired
    private SysEveModelTypeService sysEveModelTypeService;

    /**
     * 获取系统模板分类列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelTypeController/querySysEveModelTypeList")
    public void querySysEveModelTypeList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelTypeService.querySysEveModelTypeList(inputObject, outputObject);
    }

    /**
     * 新增系统模板分类
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelTypeController/insertSysEveModelType")
    public void insertSysEveModelType(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelTypeService.insertSysEveModelType(inputObject, outputObject);
    }

    /**
     * 删除系统模板分类
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelTypeController/delSysEveModelTypeById")
    public void delSysEveModelTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelTypeService.delSysEveModelTypeById(inputObject, outputObject);
    }

    /**
     * 根据id查询系统模板分类详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelTypeController/querySysEveModelTypeById")
    public void querySysEveModelTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelTypeService.querySysEveModelTypeById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的系统模板分类信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelTypeController/updateSysEveModelTypeById")
    public void updateSysEveModelTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelTypeService.updateSysEveModelTypeById(inputObject, outputObject);
    }

    /**
     * 通过parentId查找对应的系统模板分类列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelTypeController/querySysEveModelTypeByParentId")
    public void querySysEveModelTypeByParentId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelTypeService.querySysEveModelTypeByParentId(inputObject, outputObject);
    }
}
