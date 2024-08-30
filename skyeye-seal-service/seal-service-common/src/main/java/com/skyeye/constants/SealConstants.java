/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.constants;

/**
 * @ClassName: SealConstants
 * @Description: 常量类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 16:29
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public class SealConstants {

    /**
     * @param orderNum 获取工单派工内容字符串
     * @param userName 接收人
     * @return
     */
    public static String getNoticeServiceUserContent(String orderNum, String userName) {
        return "尊敬的" + userName + "，您好：<br/>" + "您有一份待接单工单，工单号为：" + orderNum + "，请及时接单。";
    }

    // 协助人
    public static String getNoticeCooperationUserContent(String orderNum, String userName) {
        return "尊敬的" + userName + "，您好：<br/>" + "您有一份协助工单，工单号为：" + orderNum + "，请配合工单接收人完成该售后服务。";
    }

}
