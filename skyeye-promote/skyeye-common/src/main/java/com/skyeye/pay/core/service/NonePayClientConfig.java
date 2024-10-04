/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service;

import com.skyeye.pay.core.PayClientConfig;
import lombok.Data;

import javax.validation.Validator;

/**
 * @ClassName: NonePayClientConfig
 * @Description: 无需任何配置 PayClientConfig 实现类
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 9:26
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class NonePayClientConfig implements PayClientConfig {

    /**
     * 配置名称
     * <p>
     * 如果不加任何属性，JsonUtils.parseObject2 解析会报错，所以暂时加个名称
     */
    private String name;

    public NonePayClientConfig() {
        this.name = "none-config";
    }

    @Override
    public void validate(Validator validator) {
        // 无任何配置不需要校验
    }
}
