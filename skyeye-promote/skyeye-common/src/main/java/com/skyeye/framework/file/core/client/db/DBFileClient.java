/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.framework.file.core.client.db;

import com.skyeye.framework.file.core.client.AbstractFileClient;

/**
 * @ClassName: DBFileClient
 * @Description: 基于 DB 存储的文件客户端的配置类
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 17:24
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class DBFileClient extends AbstractFileClient<DBFileClientConfig> {

//    private FileContentMapper fileContentMapper;

    public DBFileClient(Long id, DBFileClientConfig config) {
        super(id, config);
    }

    @Override
    protected void doInit() {
//        fileContentMapper = SpringUtil.getBean(FileContentMapper.class);
    }

    @Override
    public String upload(byte[] content, String path, String type) {
//        FileContentDO contentDO = new FileContentDO().setConfigId(getId())
//                .setPath(path).setContent(content);
//        fileContentMapper.insert(contentDO);
//        // 拼接返回路径
//        return super.formatFileUrl(config.getDomain(), path);
        return null;
    }

    @Override
    public void delete(String path) {
//        fileContentMapper.deleteByConfigIdAndPath(getId(), path);
    }

    @Override
    public byte[] getContent(String path) {
//        List<FileContentDO> list = fileContentMapper.selectListByConfigIdAndPath(getId(), path);
//        if (CollUtil.isEmpty(list)) {
//            return null;
//        }
//        // 排序后，拿 id 最大的，即最后上传的
//        list.sort(Comparator.comparing(FileContentDO::getId));
//        return CollUtil.getLast(list).getContent();
        return null;
    }

}
