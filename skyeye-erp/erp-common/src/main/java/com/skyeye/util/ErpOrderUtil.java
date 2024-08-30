/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.util;

import com.skyeye.exception.CustomException;

import java.util.Map;

/**
 * @ClassName: ErpOrderUtil
 * @Description: ERP工具类
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/23 13:52
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class ErpOrderUtil {

    public static Integer checkOperNumber(Integer surplusNum, String normsId, Map<String, Integer>... nums) {
        for (Map<String, Integer> num : nums) {
            surplusNum -= num.containsKey(normsId) ? num.get(normsId) : 0;
        }
        if (surplusNum < 0) {
            throw new CustomException("超出来源单据的商品数量.");
        }
        return surplusNum;
    }

}
