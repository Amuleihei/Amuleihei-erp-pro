/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.centerrest.common;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: CommonService
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/6 12:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-pro}", configuration = ClientConfiguration.class)
public interface CommonService {

    /**
     * 根据文件类型获取文件的保存地址以及访问地址
     *
     * @param fileType 文件类型
     * @return
     */
    @GetMapping("/queryFilePathByFileType")
    String queryFilePathByFileType(@RequestParam("fileType") Integer fileType);

}
