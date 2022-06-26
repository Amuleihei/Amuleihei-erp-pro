/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysEveMenuDao;
import com.skyeye.eve.service.SysEveMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysEveMenuServiceImpl implements SysEveMenuService {

    @Autowired
    private SysEveMenuDao sysEveMenuDao;

    /**
     * 获取菜单列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysMenuList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveMenuDao.querySysMenuList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 添加菜单
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager")
    public void insertSysMenuMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        if (Constants.SYS_MENU_TYPE_IS_IFRAME.equals(map.get("menuType").toString())) {//iframe
            map.put("openType", Constants.SYS_MENU_OPEN_TYPE_IS_IFRAME);//1：打开iframe
        } else if (Constants.SYS_MENU_TYPE_IS_HTML.equals(map.get("menuType").toString())) {//html
            map.put("openType", Constants.SYS_MENU_OPEN_TYPE_IS_HTML);//2：打开html
        } else {
            outputObject.setreturnMessage("菜单类型错误。");
            return;
        }
        if ("0".equals(map.get("parentId").toString())) {
            map.put("menuLevel", 0);
        } else {
            String[] str = map.get("parentId").toString().split(",");
            map.put("menuLevel", str.length);
        }
        Map<String, Object> orderNum = sysEveMenuDao.querySysMenuAfterOrderBumByParentId(map);
        if (orderNum == null) {
            map.put("orderNum", 0);
        } else {
            if (orderNum.containsKey("orderNum")) {
                map.put("orderNum", Integer.parseInt(orderNum.get("orderNum").toString()) + 1);
            } else {
                map.put("orderNum", 0);
            }
        }
        DataCommonUtil.setCommonData(map, user.get("id").toString());
        sysEveMenuDao.insertSysMenuMation(map);
    }

    /**
     * 查看同级菜单
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveMenuDao.querySysMenuMationBySimpleLevel(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 编辑菜单时进行信息回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysMenuMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveMenuDao.querySysMenuMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑菜单信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager")
    public void editSysMenuMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        if (Constants.SYS_MENU_TYPE_IS_IFRAME.equals(map.get("menuType").toString())) {//iframe
            map.put("openType", Constants.SYS_MENU_OPEN_TYPE_IS_IFRAME);//1：打开iframe
        } else if (Constants.SYS_MENU_TYPE_IS_HTML.equals(map.get("menuType").toString())) {//html
            map.put("openType", Constants.SYS_MENU_OPEN_TYPE_IS_HTML);//2：打开html
        } else {
            outputObject.setreturnMessage("菜单类型错误。");
            return;
        }
        if ("0".equals(map.get("parentId").toString())) {
            map.put("menuLevel", 0);
        } else {
            String[] str = map.get("parentId").toString().split(",");
            map.put("menuLevel", str.length);
        }
        Map<String, Object> oldParent = sysEveMenuDao.queryOldParentIdById(map);
        if (!oldParent.get("parentId").toString().equals(map.get("parentId").toString())) {//修改之后不再是之前父类的子菜单
            Map<String, Object> orderNum = sysEveMenuDao.querySysMenuAfterOrderBumByParentId(map);
            if (orderNum == null) {
                map.put("orderNum", 0);
            } else {
                if (orderNum.containsKey("orderNum")) {
                    map.put("orderNum", Integer.parseInt(orderNum.get("orderNum").toString()) + 1);
                } else {
                    map.put("orderNum", 0);
                }
            }
        }
        setUpdateUserMation(inputObject, map);
        sysEveMenuDao.editSysMenuMationById(map);
    }

    /**
     * 删除菜单信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager")
    public void deleteSysMenuMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> menuBean = sysEveMenuDao.queryUseThisMenuRoleById(map);
        if (menuBean == null) {
            //删除子菜单
            sysEveMenuDao.deleteSysMenuChildMationById(map);
            //删除自身菜单
            sysEveMenuDao.deleteSysMenuMationById(map);
        } else {
            if (Integer.parseInt(menuBean.get("roleNum").toString()) == 0) {//该菜单没有角色使用
                //删除子菜单
                sysEveMenuDao.deleteSysMenuChildMationById(map);
                //删除自身菜单
                sysEveMenuDao.deleteSysMenuMationById(map);
            } else {
                outputObject.setreturnMessage("该菜单正在被一个或多个角色使用，无法删除。");
            }
        }
    }

    /**
     * 异步加载树查看商户拥有的系统
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryTreeSysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveMenuDao.queryTreeSysMenuMationBySimpleLevel(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 获取菜单级别列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysMenuLevelList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveMenuDao.querySysMenuLevelList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 菜单展示顺序上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager")
    public void editSysEveMenuSortTopById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> topBean = sysEveMenuDao.querySysEveMenuISTopByThisId(map);//根据同一级排序获取这条数据的上一条数据
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠前菜单，无法移动。");
        } else {
            map.put("orderNum", topBean.get("orderNum"));
            topBean.put("orderNum", topBean.get("thisOrderNum"));
            setUpdateUserMation(inputObject, map);
            sysEveMenuDao.editSysEveMenuSortTopById(map);
            setUpdateUserMation(inputObject, topBean);
            sysEveMenuDao.editSysEveMenuSortTopById(topBean);
        }
    }

    private void setUpdateUserMation(InputObject inputObject, Map<String, Object> map) throws Exception {
        map.put("lastUpdateId", inputObject.getLogParams().get("id"));
        map.put("lastUpdateTime", DateUtil.getTimeAndToString());
    }

    /**
     * 菜单展示顺序下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value = "transactionManager")
    public void editSysEveMenuSortLowerById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> topBean = sysEveMenuDao.querySysEveMenuISLowerByThisId(map);//根据同一级排序获取这条数据的下一条数据
        if (topBean == null) {
            outputObject.setreturnMessage("已经是最靠后菜单，无法移动。");
        } else {
            map.put("orderNum", topBean.get("orderNum"));
            topBean.put("orderNum", topBean.get("thisOrderNum"));
            setUpdateUserMation(inputObject, map);
            sysEveMenuDao.editSysEveMenuSortLowerById(map);
            setUpdateUserMation(inputObject, topBean);
            sysEveMenuDao.editSysEveMenuSortLowerById(topBean);
        }
    }

    /**
     * 获取该系统商户拥有的系统
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysWinMationListBySysId(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEveMenuDao.querySysWinMationListBySysId(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 系统菜单详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void querySysEveMenuBySysId(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = sysEveMenuDao.querySysEveMenuBySysId(map);
        if (!bean.isEmpty()) {
            outputObject.setBean(bean);
            outputObject.settotal(1);
        }
    }

}
