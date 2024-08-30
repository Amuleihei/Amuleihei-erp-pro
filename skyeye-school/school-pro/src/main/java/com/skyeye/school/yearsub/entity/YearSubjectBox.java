/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.yearsub.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: YearSubjectBox
 * @Description: 学年科目入参
 * @author: skyeye云系列--卫志强
 * @date: 2023/9/6 10:27
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel(value = "学年科目入参")
public class YearSubjectBox {

    @TableField("year")
    @ApiModelProperty(value = "所属年份(哪一届)", required = "required")
    private Integer year;

    @TableField("major_id")
    @ApiModelProperty(value = "所属专业", required = "required")
    private String majorId;

    @TableField(exist = false)
    @ApiModelProperty(value = "学年科目", required = "json")
    private List<YearSubject> yearSubject;

}
