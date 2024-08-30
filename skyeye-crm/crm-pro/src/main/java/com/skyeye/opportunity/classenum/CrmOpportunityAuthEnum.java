/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.opportunity.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CrmOpportunityAuthEnum
 * @Description: 客户商机权限控制枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/25 23:06
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CrmOpportunityAuthEnum implements SkyeyeEnumClass {

    LIST("list", "查看列表", true, false),
    ADD("add", "新增", true, false),
    EDIT("edit", "编辑", true, false),
    DELETE("delete", "删除", true, false),
    REVOKE("revoke", "撤销", true, false),
    INVALID("invalid", "作废", true, false),
    SUBMIT_TO_APPROVAL("submitToApproval", "提交审批", true, false),

    CONMUNICATE("conmunicate", "初期沟通", true, false),
    QUOTED_PRICE("quotedPrice", "方案与报价", true, false),
    TENDER("tender", "竞争与投标", true, false),
    NEGOTIATE("negotiate", "商务谈判", true, false),
    TURNOVER("turnover", "成交", true, false),
    LOSING_TABLE("losingTable", "丢单", true, false),
    LAY_ASIDE("layAside", "搁置", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
