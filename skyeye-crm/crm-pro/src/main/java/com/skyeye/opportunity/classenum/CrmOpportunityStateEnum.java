/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.opportunity.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import com.skyeye.common.enumeration.FlowableStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: CrmOpportunityStateEnum
 * @Description: 客户商机状态枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/26 12:09
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CrmOpportunityStateEnum implements SkyeyeEnumClass {

    INITIAL_COMMUNICATION("initialCommunication", "初期沟通", true, false),
    SCHEME_AND_QUOTATION("schemeAndQuotation", "方案与报价", true, false),
    COMPETITION_AND_BIDDING("competitionAndBidding", "竞争与投标", true, false),
    BUSINESS_NEGOTIATION("businessNegotiation", "商务谈判", true, false),
    STRIKE_BARGAIN("strikeBargain", "成交", true, false),
    LOST_ORDER("lostOrder", "丢单", true, false),
    LAY_ASIDE("layAside", "搁置", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static List<Class> dependOnEnum() {
        return Arrays.asList(FlowableStateEnum.class);
    }

}
