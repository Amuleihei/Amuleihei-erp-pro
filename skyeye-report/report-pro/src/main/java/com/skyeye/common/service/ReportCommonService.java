/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ReportCommonService
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2021/5/17 21:31
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ReportCommonService {

    void testConnection(InputObject inputObject, OutputObject outputObject);

    /**
     * 连接数据源
     *
     * @param driverClass  数据源驱动类
     * @param url          数据源连接字符串
     * @param user         用户名
     * @param password     密码
     * @param outputObject 出参以及提示信息的返回值对象
     * @return
     */
    boolean connectionDataBase(final String driverClass, final String url, final String user,
                               final String password, OutputObject outputObject);

    /**
     * 解析Xml格式文本
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void parseXmlText(InputObject inputObject, OutputObject outputObject);

    /**
     * 解析Json格式文本
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void parseJsonText(InputObject inputObject, OutputObject outputObject);

    /**
     * 获取数据源类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void queryDataBaseMationList(InputObject inputObject, OutputObject outputObject);

    /**
     * 获取连接池类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void queryPoolMationList(InputObject inputObject, OutputObject outputObject);

    /**
     * 解析SQL数据源
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void parseSQLText(InputObject inputObject, OutputObject outputObject);

    /**
     * 解析Rest接口
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    void parseRestText(InputObject inputObject, OutputObject outputObject);

    void parseJsonSubNode(Map<String, Object> paramMap, Set<String> sets, boolean isFirstTime, String name);
}
