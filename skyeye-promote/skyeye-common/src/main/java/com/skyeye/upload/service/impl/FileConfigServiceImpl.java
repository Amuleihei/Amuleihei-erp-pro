/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.enumeration.IsDefaultEnum;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.framework.file.core.client.FileClient;
import com.skyeye.framework.file.core.client.FileClientFactory;
import com.skyeye.upload.dao.FileConfigDao;
import com.skyeye.upload.entity.FileConfig;
import com.skyeye.upload.service.FileConfigService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private FileClientFactory fileClientFactory;

    private static final String FILE_CONFIG_IS_DEFAULT_CACHE_KEY = "skyeye:fileConfig:isDefault";

    @Override
    public void createPrepose(FileConfig entity) {
        if (entity.getIsDefault() == IsDefaultEnum.IS_DEFAULT.getKey()) {
            UpdateWrapper<FileConfig> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set(MybatisPlusUtil.toColumns(FileConfig::getIsDefault), IsDefaultEnum.NOT_DEFAULT.getKey());
            update(updateWrapper);
        }
    }

    @Override
    public void writePostpose(FileConfig entity, String userId) {
        if (entity.getIsDefault() == IsDefaultEnum.IS_DEFAULT.getKey()) {
            jedisClientService.del(FILE_CONFIG_IS_DEFAULT_CACHE_KEY);
        }
    }

    @Override
    public void deletePostpose(FileConfig entity) {
        if (entity.getIsDefault() == IsDefaultEnum.IS_DEFAULT.getKey()) {
            jedisClientService.del(FILE_CONFIG_IS_DEFAULT_CACHE_KEY);
        }
        // 移除文件客户端
        fileClientFactory.removeFileClient(entity.getId());
    }

    @Override
    public FileClient getMasterFileClient() {
        FileConfig fileConfig = redisCache.getBean(FILE_CONFIG_IS_DEFAULT_CACHE_KEY, key -> {
            QueryWrapper<FileConfig> wrapper = new QueryWrapper<>();
            wrapper.eq(MybatisPlusUtil.toColumns(FileConfig::getIsDefault), IsDefaultEnum.IS_DEFAULT.getKey());
            FileConfig config = getOne(wrapper, false);
            if (config != null) {
                fileClientFactory.createOrUpdateFileClient(config.getId(), config.getStorage(), config.getConfig());
            }
            return config;
        }, RedisConstants.THIRTY_DAY_SECONDS, FileClient.class);
        if (fileConfig == null) {
            throw new CustomException("没有设置默认文件存储");
        }

        return fileClientFactory.getFileClient(fileConfig.getId());
    }

    @Override
    public FileClient getFileClient(String configId) {
        FileClient fileClient = fileClientFactory.getFileClient(configId);
        if (fileClient == null) {
            FileConfig fileConfig = selectById(configId);
            if (fileConfig == null) {
                throw new CustomException("文件存储配置不存在");
            }
            fileClientFactory.createOrUpdateFileClient(fileConfig.getId(), fileConfig.getStorage(), fileConfig.getConfig());
            fileClient = fileClientFactory.getFileClient(configId);
        }
        return fileClient;
    }
}
