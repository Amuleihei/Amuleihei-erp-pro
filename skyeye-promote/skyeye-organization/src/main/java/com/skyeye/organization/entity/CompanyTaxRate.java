/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: CompanyTaxRate
 * @Description: 公司个人所得税税率信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/22 15:38
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@ApiModel("公司个人所得税税率信息实体类")
@TableName(value = "company_tax_rate")
public class CompanyTaxRate extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("company_id")
    @Property(value = "企业id")
    private String companyId;

    @TableField("min_money")
    @ApiModelProperty(value = "薪资范围-最小值(大于等于)", required = "required")
    private String minMoney;

    @TableField("max_money")
    @ApiModelProperty(value = "薪资范围-最大值(小于)", required = "required")
    private String maxMoney;

    @TableField("jan_rate")
    @ApiModelProperty(value = "一月份税率信息", required = "required")
    private String janRate;

    @TableField("feb_rate")
    @ApiModelProperty(value = "二月份税率信息", required = "required")
    private String febRate;

    @TableField("mar_rate")
    @ApiModelProperty(value = "三月份税率信息", required = "required")
    private String marRate;

    @TableField("apr_rate")
    @ApiModelProperty(value = "四月份税率信息", required = "required")
    private String aprRate;

    @TableField("may_rate")
    @ApiModelProperty(value = "五月份税率信息", required = "required")
    private String mayRate;

    @TableField("jun_rate")
    @ApiModelProperty(value = "六月份税率信息", required = "required")
    private String junRate;

    @TableField("jul_rate")
    @ApiModelProperty(value = "七月份税率信息", required = "required")
    private String julRate;

    @TableField("aug_rate")
    @ApiModelProperty(value = "八月份税率信息", required = "required")
    private String augRate;

    @TableField("sept_rate")
    @ApiModelProperty(value = "九月份税率信息", required = "required")
    private String septRate;

    @TableField("oct_rate")
    @ApiModelProperty(value = "十月份税率信息", required = "required")
    private String octRate;

    @TableField("nov_rate")
    @ApiModelProperty(value = "十一月份税率信息", required = "required")
    private String novRate;

    @TableField("dec_rate")
    @ApiModelProperty(value = "十二月份税率信息", required = "required")
    private String decRate;

    @TableField("sort_no")
    @ApiModelProperty(value = "值越大越往后", required = "required")
    private Integer sortNo;

}
