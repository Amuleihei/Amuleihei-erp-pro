/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.deploy.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.deploy.dao.DeployJarDao;
import com.skyeye.deploy.entity.deploy.JavaWebDeployInfo;
import com.skyeye.deploy.service.DeployJarService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: DeployJarServiceImpl
 * @Description: 部署jar服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/20 9:28
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DeployJarServiceImpl implements DeployJarService {

    @Autowired
    private DeployJarDao deployJarDao;

    /**
     * 添加项目
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void insertDeployProject(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        JavaWebDeployInfo javaWebDeployInfo = JSONUtil.toBean(JSON.toJSONString(params), JavaWebDeployInfo.class);
        if (StringUtils.isNotBlank(javaWebDeployInfo.getGitFolderName())) {
            javaWebDeployInfo.setGitFolderId(UUID.randomUUID().toString());
        }
        deployJarDao.insert(javaWebDeployInfo);
    }

    /**
     * 项目详情
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryDeployProject(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        JavaWebDeployInfo javaWebDeployInfo = deployJarDao.queryDeployProjectById(id);
        outputObject.setBean(javaWebDeployInfo);
        outputObject.settotal(1);
    }

}
