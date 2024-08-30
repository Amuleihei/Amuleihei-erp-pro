/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.wall.certification.rest;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: ICertificationRest
 * @Description: 学生认证信息
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/12 8:27
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-wall}", configuration = ClientConfiguration.class)
public interface ICertificationRest {

    /**
     * 根据id获取用户认证信息
     *
     * @param id 主键id
     */
    @GetMapping("/queryCertificationById")
    String queryCertificationById(@RequestParam("id") String id);

    /**
     * 根据学号批量获取用户信息
     *
     * @param studentNumber 学生学号，多个逗号隔开
     */
    @PostMapping("/queryUserByStudentNumber")
    String queryUserByStudentNumber(@RequestParam("studentNumber") String studentNumber);

}
