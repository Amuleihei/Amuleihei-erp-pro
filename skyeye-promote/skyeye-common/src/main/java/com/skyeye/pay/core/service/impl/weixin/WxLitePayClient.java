/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.core.service.impl.weixin;

import com.skyeye.pay.enums.PayType;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: WxLitePayClient
 * @Description: 微信支付【小程序】的 PayClient 实现类
 * 由于公众号和小程序的微信支付逻辑一致，所以直接进行继承
 * 文档：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml">JSAPI 下单</>
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/11 9:39
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Slf4j
public class WxLitePayClient extends WxPubPayClient {

    public WxLitePayClient(Long channelId, WxPayClientConfig config) {
        super(channelId, PayType.WX_LITE.getKey(), config);
    }

}
