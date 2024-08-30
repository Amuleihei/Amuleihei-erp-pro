/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contract.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SupplierContractChildStateEnum
 * @Description: 合同产品状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/25 18:44
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SupplierContractChildStateEnum implements SkyeyeEnumClass {

    PENDING_ORDER("pendingOrder", "待下达订单", true, false),
    PARTIAL_RELEASE("partialRelease", "部分下达", true, false),
    ALL_ISSUED("allIssued", "全部下达", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
