/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.other.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.other.entity.SplitList;
import com.skyeye.other.service.SplitListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SplitListController
 * @Description: 拆分单管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2019/10/16 15:32
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "拆分单", tags = "拆分单", modelName = "拆分单模块")
public class SplitListController {

    @Autowired
    private SplitListService splitListService;

    /**
     * 获取拆分单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "splitlist001", value = "获取拆分单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SplitListController/querySplitListList")
    public void querySplitListList(InputObject inputObject, OutputObject outputObject) {
        splitListService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑拆分单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSplitList", value = "新增/编辑拆分单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SplitList.class)
    @RequestMapping("/post/SplitListController/writeSplitList")
    public void writeSplitList(InputObject inputObject, OutputObject outputObject) {
        splitListService.saveOrUpdateEntity(inputObject, outputObject);
    }

}
