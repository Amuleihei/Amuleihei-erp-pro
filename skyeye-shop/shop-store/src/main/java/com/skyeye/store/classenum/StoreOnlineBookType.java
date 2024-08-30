/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.store.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: StoreOnlineBookType
 * @Description: 门店线上预约类型的设定
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/12 9:59
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StoreOnlineBookType implements SkyeyeEnumClass {

    MAINTENANCE_STAND(1, "按维修机位", true, true),
    WORKER_NUM(2, "按工人数", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
