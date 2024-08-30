/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.eve.classenum.DickCloudType;
import com.skyeye.eve.dao.FileRecycleBinDao;
import com.skyeye.eve.entity.FileCatalog;
import com.skyeye.eve.entity.FileConsole;
import com.skyeye.eve.entity.FileRecycleBin;
import com.skyeye.eve.service.FileCatalogService;
import com.skyeye.eve.service.FileConsoleService;
import com.skyeye.eve.service.FileRecycleBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FileRecycleBinServiceImpl
 * @Description: 回收站服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 22:28
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "回收站管理", groupName = "回收站管理")
public class FileRecycleBinServiceImpl extends SkyeyeBusinessServiceImpl<FileRecycleBinDao, FileRecycleBin> implements FileRecycleBinService {

    @Autowired
    private FileConsoleService fileConsoleService;

    @Autowired
    private FileCatalogService fileCatalogService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        pageInfo.setCreateId(inputObject.getLogParams().get("id").toString());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryFileRecycleBinList(pageInfo);
        beans.forEach(bean -> {
            bean.put("fileTypeName", DickCloudType.getTypeName(bean.get("fileType").toString()));
        });
        return beans;
    }

    @Override
    public void createPrepose(FileRecycleBin entity) {
        FileCatalog fileCatalog = fileCatalogService.selectById(entity.getFileId());
        if (ObjectUtil.isNotEmpty(fileCatalog) && StrUtil.isNotEmpty(fileCatalog.getId())) {
            entity.setFileType(DickCloudType.FOLDER.getKey());
            entity.setFileName(fileCatalog.getName());
        }

        FileConsole fileConsole = fileConsoleService.selectById(entity.getFileId());
        if (ObjectUtil.isNotEmpty(fileConsole) && StrUtil.isNotEmpty(fileConsole.getId())) {
            entity.setFileType(DickCloudType.FILE.getKey());
            entity.setFileName(fileConsole.getName());
        }
    }
}
