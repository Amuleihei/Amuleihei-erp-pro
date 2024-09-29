package com.skyeye.delivery.vo;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@ApiModel("获取精简的快递运费模版信息，主要用于下拉列表")
public class ShopDeliveryTemplateVo  implements Serializable{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "模版名称")
        private String name;

}
