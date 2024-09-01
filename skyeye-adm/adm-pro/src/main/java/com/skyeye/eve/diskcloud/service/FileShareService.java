/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.diskcloud.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.diskcloud.entity.FileShare;

/**
 * @ClassName: FileShareService
 * @Description: 文件分享服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/18 11:42
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface FileShareService extends SkyeyeBusinessService<FileShare> {

    void queryShareFileMationById(InputObject inputObject, OutputObject outputObject);

    void checkShareFilePwdMation(InputObject inputObject, OutputObject outputObject);

    void queryShareFileListByParentId(InputObject inputObject, OutputObject outputObject);

}
