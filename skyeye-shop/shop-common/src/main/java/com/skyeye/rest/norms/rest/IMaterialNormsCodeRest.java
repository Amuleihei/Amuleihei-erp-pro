/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.norms.rest;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @ClassName: IMaterialNormsCodeRest
 * @Description: ERP物料规格条形码信息管理公共的一些操作
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 10:32
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@FeignClient(value = "${webroot.skyeye-erp}", configuration = ClientConfiguration.class)
public interface IMaterialNormsCodeRest {

    /**
     * 根据编码等信息查询门店规格条形码信息
     *
     * @param params 参数信息：
     *               storeId：门店id--必填
     *               normsCodeList：规格编码，多个用逗号隔开--必填
     *               storeUseState：门店使用状态--非必填
     */
    @PostMapping("/queryMaterialNormsCode")
    String queryMaterialNormsCode(Map<String, Object> params);

    /**
     * 根据编码等信息修改门店规格条形码使用状态
     *
     * @param params 参数信息：
     *               ids：规格条形码信息id，多个逗号隔开--必填
     *               storeUseState：门店使用状态--必填
     */
    @PostMapping("/editStoreMaterialNormsCodeUseState")
    String editStoreMaterialNormsCodeUseState(Map<String, Object> params);

}
