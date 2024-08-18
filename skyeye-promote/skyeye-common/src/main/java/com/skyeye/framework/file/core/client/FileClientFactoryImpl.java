/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.framework.file.core.client;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.skyeye.upload.enums.FileStorageEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName: FileClientFactoryImpl
 * @Description: 文件客户端的工厂实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 9:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class FileClientFactoryImpl implements FileClientFactory {

    /**
     * 文件客户端 Map
     * key：配置编号
     */
    private final ConcurrentMap<String, AbstractFileClient<?>> clients = new ConcurrentHashMap<>();

    @Override
    public void removeFileClient(String configId) {
        clients.remove(configId);
    }

    @Override
    public FileClient getFileClient(String configId) {
        AbstractFileClient<?> client = clients.get(configId);
        if (client == null) {
            log.error("[getFileClient][配置编号({}) 找不到客户端]", configId);
        }
        return client;
    }

    @Override
    public <Config extends FileClientConfig> void createOrUpdateFileClient(String configId, Integer storage, Config config) {
        AbstractFileClient<Config> client = (AbstractFileClient<Config>) clients.get(configId);
        if (client == null) {
            client = this.createFileClient(configId, storage, config);
            client.init();
            clients.put(client.getId(), client);
        } else {
            client.refresh(config);
        }
    }

    private <Config extends FileClientConfig> AbstractFileClient<Config> createFileClient(
        String configId, Integer storage, Config config) {
        FileStorageEnum storageEnum = FileStorageEnum.getByStorage(storage);
        Assert.notNull(storageEnum, String.format("文件配置(%s) 为空", storageEnum));
        // 创建客户端
        return (AbstractFileClient<Config>) ReflectUtil.newInstance(storageEnum.getClientClass(), configId, config);
    }

}
