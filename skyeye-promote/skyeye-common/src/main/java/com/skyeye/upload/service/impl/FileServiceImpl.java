/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.upload.dao.FileDao;
import com.skyeye.upload.entity.File;
import com.skyeye.upload.service.FileService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FileServiceImpl
 * @Description: 文件服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 19:56
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "文件", groupName = "文件")
public class FileServiceImpl extends SkyeyeBusinessServiceImpl<FileDao, File> implements FileService {

    @Override
    public File queryByPath(String path) {
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(File::getPath), path);
        File file = getOne(wrapper, false);
        return file;
    }

}
