/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.ExExplainDao;
import com.skyeye.eve.service.ExExplainService;
import com.skyeye.jedis.JedisClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExExplainServiceImpl implements ExExplainService {

    @Autowired
    private ExExplainDao exExplainDao;

    @Autowired
    public JedisClientService jedisClient;

    /**
     * 添加使用说明信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertExExplainMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = exExplainDao.queryExExplainMation(map);
        if (bean == null) {
            Integer type = Integer.parseInt(map.get("type").toString());
            Map<String, Object> user = inputObject.getLogParams();
            String id = ToolUtil.getSurFaceId();
            map.put("id", id);
            map.put("createId", user.get("id"));
            map.put("createTime", DateUtil.getTimeAndToString());
            exExplainDao.insertExExplainMation(map);
            jedisClient.del(Constants.getSysExplainExexplainRedisKey(type));
            bean = new HashMap<>();
            bean.put("id", id);
            outputObject.setBean(bean);
        } else {
            outputObject.setreturnMessage("该代码生成器说明已存在，不可进行二次保存");
        }
    }

    /**
     * 编辑使用说明信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryExExplainMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = exExplainDao.queryExExplainMation(map);
        bean.put("content", HtmlUtils.htmlUnescape(bean.get("content").toString()));
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑使用说明信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editExExplainMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = exExplainDao.queryExExplainMation(map);
        if (bean == null) {
            outputObject.setreturnMessage("该代码生成器说明不存在，不可进行编辑");
        } else {
            Integer type = Integer.parseInt(map.get("type").toString());
            jedisClient.del(Constants.getSysExplainExexplainRedisKey(type));
            exExplainDao.editExExplainMationById(map);
        }
    }

    /**
     * 获取使用说明信息供展示
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryExExplainMationToShow(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Integer type = Integer.parseInt(map.get("type").toString());
        String key = Constants.getSysExplainExexplainRedisKey(type);
        if (jedisClient.exists(key)) {
            map = JSONUtil.toBean(jedisClient.get(key), null);
        } else {
            Map<String, Object> bean = exExplainDao.queryExExplainMation(map);
            if (bean == null) {
                map.put("title", "标题");
                map.put("content", "等待发布说明。");
            } else {
                bean.put("content", HtmlUtils.htmlUnescape(bean.get("content").toString()));
                jedisClient.set(key, JSONUtil.toJsonStr(bean));
                map = bean;
            }
        }
        outputObject.setBean(map);
    }

}
