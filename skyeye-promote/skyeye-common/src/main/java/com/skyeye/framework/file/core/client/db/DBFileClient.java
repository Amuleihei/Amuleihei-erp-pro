/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.framework.file.core.client.db;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.skyeye.framework.file.core.client.AbstractFileClient;
import com.skyeye.upload.entity.FileContent;
import com.skyeye.upload.service.FileContentService;

/**
 * @ClassName: DBFileClient
 * @Description: 基于 DB 存储的文件客户端的配置类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 17:24
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DBFileClient extends AbstractFileClient<DBFileClientConfig> {

    private FileContentService fileContentService;

    public DBFileClient(String id, DBFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
        fileContentService = SpringUtil.getBean(FileContentService.class);
    }

    @Override
    public String upload(byte[] content, String path, String type) {
        FileContent fileContent = new FileContent();
        fileContent.setConfigId(getId());
        fileContent.setPath(path);
        fileContent.setContent(content);
        fileContentService.createEntity(fileContent, StrUtil.EMPTY);
        // 拼接返回路径
        return super.formatFileUrl(path);
    }

    @Override
    public void delete(String path) {
        fileContentService.deleteByPath(path);
    }

    @Override
    public byte[] getContent(String path) {
        FileContent fileContent = fileContentService.queryByPath(path);
        return fileContent.getContent();
    }

}
