/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.upload.dao.FileContentDao;
import com.skyeye.upload.entity.FileContent;
import com.skyeye.upload.service.FileContentService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FileContentServiceImpl
 * @Description: 文件内容服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 20:20
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "文件内容", groupName = "文件内容")
public class FileContentServiceImpl extends SkyeyeBusinessServiceImpl<FileContentDao, FileContent> implements FileContentService {

    @Override
    public void deleteByPath(String path) {
        QueryWrapper<FileContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(FileContent::getPath), path);
        remove(queryWrapper);
    }

    @Override
    public FileContent queryByPath(String path) {
        QueryWrapper<FileContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(FileContent::getPath), path);
        FileContent fileContent = getOne(queryWrapper, false);
        if (fileContent == null) {
            throw new IllegalArgumentException("文件不存在");
        }
        return fileContent;
    }

}
