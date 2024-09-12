/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.pay.enums;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PayTransferStatusResp
 * @Description: 渠道的转账状态枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/10 8:49
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PayTransferStatusResp implements SkyeyeEnumClass {

    WAITING(0, "等待转账", true, false),
    /**
     * TODO 转账到银行卡. 会有T+0 T+1 到账的请情况。 还未实现
     * TODO @jason：可以看看其它开源项目，针对这个场景，处理策略是怎么样的？例如说，每天主动轮询？这个状态的单子？
     */
    IN_PROGRESS(10, "转账进行中", true, false),
    SUCCESS(20, "转账成功", true, false),
    CLOSED(30, "转账关闭", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
