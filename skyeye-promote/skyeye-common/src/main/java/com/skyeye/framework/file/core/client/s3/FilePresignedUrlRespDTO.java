/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.framework.file.core.client.s3;

import com.skyeye.annotation.api.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FilePresignedUrlRespDTO
 * @Description: 文件预签名地址 Response DTO
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 17:25
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilePresignedUrlRespDTO {

    @Property(value = "文件配置id")
    private String configId;

    @Property(value = "文件上传 URL（用于上传）")
    private String uploadUrl;

    @Property(value = "文件 URL（用于读取、下载等）")
    private String url;

}
