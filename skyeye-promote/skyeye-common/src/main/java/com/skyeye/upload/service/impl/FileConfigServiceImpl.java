/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.upload.dao.FileConfigDao;
import com.skyeye.upload.entity.FileConfig;
import com.skyeye.upload.service.FileConfigService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FileConfigServiceImpl
 * @Description: 文件配置服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 17:19
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "文件配置", groupName = "文件配置")
public class FileConfigServiceImpl extends SkyeyeBusinessServiceImpl<FileConfigDao, FileConfig> implements FileConfigService {

}
