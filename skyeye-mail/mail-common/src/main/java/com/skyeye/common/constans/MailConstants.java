/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

/**
 * @ClassName: MailConstants
 * @Description: 通讯录常量类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/3 15:05
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class MailConstants {

    // 我的个人通讯录类型列表
    public static final String PERSON_MAIL_TYPE_LIST = "person_mail_type_list_";

    public static String getPersonMailTypeListByUserId(String userId) {
        return PERSON_MAIL_TYPE_LIST + userId;
    }

}
