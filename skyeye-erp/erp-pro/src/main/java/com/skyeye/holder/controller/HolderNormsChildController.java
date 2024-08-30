/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.holder.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.holder.service.HolderNormsChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HolderNormsChildController
 * @Description: 关联的客户/供应商/会员购买或者出售的商品子信息控制层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/16 10:36
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "关联的客户/供应商/会员购买或者出售的商品子信息", tags = "关联的客户/供应商/会员购买或者出售的商品子信息", modelName = "关联的客户/供应商/会员购买或者出售的商品子信息")
public class HolderNormsChildController {

    @Autowired
    private HolderNormsChildService holderNormsChildService;

    /**
     * 获取关联的客户/供应商/会员购买或者出售的商品信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryHolderNormsChildList", value = "获取关联的客户/供应商/会员购买或者出售的商品子信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/HolderNormsChildController/queryHolderNormsChildList")
    public void queryHolderNormsList(InputObject inputObject, OutputObject outputObject) {
        holderNormsChildService.queryPageList(inputObject, outputObject);
    }

}
