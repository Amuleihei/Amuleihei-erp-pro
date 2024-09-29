package com.skyeye.delivery.vo;


import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("获取精简的快递运费模版费用信息，主要用于下拉列表")
public class ShopDeliveryTemplateChargeVo implements Serializable {

    @ApiModelProperty(value = "模版id")
    private String templateId;

    @ApiModelProperty(value = "区域id")
    private String areaId;
}
