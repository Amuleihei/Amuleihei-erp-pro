/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.SpringUtils;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysQuartzDao;
import com.skyeye.eve.service.SysQuartzService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: SysQuartzServiceImpl
 * @Description: 定时任务服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/3 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysQuartzServiceImpl implements SysQuartzService {

    @Autowired
    private SysQuartzDao sysQuartzDao;

    @Autowired
    private JedisClientService jedisClient;

    /**
     * 获取系统定时任务列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void querySystemQuartzList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysQuartzDao.querySystemQuartzList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 获取我的定时任务列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryMyTaskQuartzList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("userId", inputObject.getLogParams().get("id"));
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysQuartzDao.queryMyTaskQuartzList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 启动系统定时任务
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void runSystemQuartz(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        // 获取任务信息
        Map<String, Object> quartz = sysQuartzDao.querySystemQuartzByIdAndType(id, 2);
        if (quartz != null || !quartz.isEmpty()) {
            String userId = inputObject.getLogParams().get("id").toString();
            String quartzCreateIdKey = String.format(Locale.ROOT, "%s-userId", id);
            jedisClient.set(quartzCreateIdKey, userId);
            String classPath = quartz.get("classPath").toString();
            String methodName = quartz.get("methodName").toString();
            String className = ToolUtil.toLowerCaseFirstOne(classPath.substring(classPath.lastIndexOf(".") + 1));
            Object object = SpringUtils.getBean(className);
            Method method = ReflectionUtils.findMethod(object.getClass(), methodName);
            ReflectionUtils.invokeMethod(method, object);
        }
    }

}
