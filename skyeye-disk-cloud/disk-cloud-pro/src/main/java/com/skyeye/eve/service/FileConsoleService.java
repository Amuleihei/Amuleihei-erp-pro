/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/
package com.skyeye.eve.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.FileConsole;

/**
 * @ClassName: FileConsoleService
 * @Description: 文件管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/17 17:26
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface FileConsoleService extends SkyeyeBusinessService<FileConsole> {

    void editNameById(String id, String name, String userId);

    void queryFileFolderByUserId(InputObject inputObject, OutputObject outputObject);

    void queryFilesListByFolderId(InputObject inputObject, OutputObject outputObject);

    void deleteFileFolderById(InputObject inputObject, OutputObject outputObject);

    void editFileFolderById(InputObject inputObject, OutputObject outputObject);

    void insertUploadFile(InputObject inputObject, OutputObject outputObject);

    void insertUploadFileChunks(InputObject inputObject, OutputObject outputObject);

    void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject);

    void editUploadOfficeFileById(InputObject inputObject, OutputObject outputObject);

    void queryAllFileSizeByUserId(InputObject inputObject, OutputObject outputObject);

    void insertShareFileListToSave(InputObject inputObject, OutputObject outputObject);

    void queryFileToShowById(InputObject inputObject, OutputObject outputObject);

    void createFileToService(InputObject inputObject, OutputObject outputObject);

    void insertDuplicateCopyToService(InputObject inputObject, OutputObject outputObject);

    void queryFileMationById(InputObject inputObject, OutputObject outputObject);

    void insertFileMationToPackageToFolder(InputObject inputObject, OutputObject outputObject);

    void insertFileMationPackageToFolder(InputObject inputObject, OutputObject outputObject);

    void insertPasteCopyToService(InputObject inputObject, OutputObject outputObject);

    void insertPasteCutToService(InputObject inputObject, OutputObject outputObject);

    void queryOfficeUpdateTimeToKey(InputObject inputObject, OutputObject outputObject);

    void queryFileNumStatistics(InputObject inputObject, OutputObject outputObject);

    void insertFileMationToPackageDownload(InputObject inputObject, OutputObject outputObject);

}
