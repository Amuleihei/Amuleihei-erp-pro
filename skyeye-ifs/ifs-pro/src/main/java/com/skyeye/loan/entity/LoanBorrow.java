/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.loan.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeFlowable;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: LoanBorrow
 * @Description: 借款单实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/5 13:59
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "ifs:loanBorrow", cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "ifs_loan_borrow", autoResultMap = true)
@ApiModel("借款单实体类")
public class LoanBorrow extends SkyeyeFlowable {

    @TableField(value = "purpose")
    @ApiModelProperty(value = "借款用途", required = "required")
    private String purpose;

    @TableField(value = "collection_name")
    @ApiModelProperty(value = "收款人全称", required = "required")
    private String collectionName;

    @TableField(value = "collection_code")
    @ApiModelProperty(value = "收款账号")
    private String collectionCode;

    @TableField(value = "pay_type_id")
    @ApiModelProperty(value = "付款方式id，参考数据字典", required = "required")
    private String payTypeId;

    @TableField(exist = false)
    @Property(value = "付款方式信息")
    private Map<String, Object> payTypeMation;

    @TableField(value = "opening_bank")
    @ApiModelProperty(value = "开户行")
    private String openingBank;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(value = "price")
    @ApiModelProperty(value = "借款金额", required = "required,double")
    private String price;

}
