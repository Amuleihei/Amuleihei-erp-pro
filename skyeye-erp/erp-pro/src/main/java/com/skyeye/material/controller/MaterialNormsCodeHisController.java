/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.material.service.MaterialNormsCodeHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MaterialNormsCodeHisController
 * @Description: 商品规格一物一码条形码变更历史
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/11 17:15
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "商品条形码变更历史", tags = "商品条形码变更历史", modelName = "商品管理")
public class MaterialNormsCodeHisController {

    @Autowired
    private MaterialNormsCodeHisService materialNormsCodeHisService;

}
