/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.pro.win;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @ClassName: SysEveWinDragDropService
 * @Description: 自定义桌面菜单服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/6 0:18
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-pro}", configuration = ClientConfiguration.class)
public interface SysEveWinDragDropService {

    /**
     * 新增轻应用到桌面菜单
     *
     * @param params 入参
     * @return
     */
    @PostMapping("/sysevewindragdrop002")
    String insertWinCustomMenu(Map<String, Object> params);

}
