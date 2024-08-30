/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.sealservice.rest;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @ClassName: IServiceApplyRest
 * @Description: 我的申领单接口
 * @author: skyeye云系列--卫志强
 * @date: 2024/7/19 18:55
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-seal-service}", configuration = ClientConfiguration.class)
public interface IServiceApplyRest {

    /**
     * 修改配件申领单出库状态
     *
     * @param params 单据信息,需要包含id(单据id),otherState(出库状态)
     */
    @PostMapping("/editSealApplyOtherState")
    String editSealApplyOtherState(Map<String, Object> params);

    /**
     * 修改配件申领单已出库的数量
     *
     * @param params 单据信息
     */
    @PostMapping("/editSealApplyOutNum")
    String editSealApplyOutNum(Map<String, Object> params);

}
