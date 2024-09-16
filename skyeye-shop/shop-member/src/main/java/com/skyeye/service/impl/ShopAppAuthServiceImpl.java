/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.SysUserAuthConstants;
import com.skyeye.common.enumeration.RequestType;
import com.skyeye.common.enumeration.SmsSceneEnum;
import com.skyeye.common.object.GetUserToken;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.entity.Member;
import com.skyeye.exception.CustomException;
import com.skyeye.rest.sms.service.ISmsCodeService;
import com.skyeye.service.MemberService;
import com.skyeye.service.ShopAppAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: ShopAppAuthServiceImpl
 * @Description: 商城登录管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/9/16 11:57
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ShopAppAuthServiceImpl implements ShopAppAuthService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ISmsCodeService iSmsCodeService;

    @Override
    public void shopLoginForPC(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Member member = login(map, RequestType.PC.getKey());
        outputObject.setBean(member);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    private Member login(Map<String, Object> map, String requestType) {
        String phone = map.get("phone").toString();
        Member member = memberService.queryMemberByPhone(phone);
        if (ObjectUtil.isEmpty(member)) {
            throw new CustomException("手机号码不存在，请先注册！");
        }
        String password = map.get("password").toString();
        for (int i = 0; i < member.getPwdNumEnc(); i++) {
            password = ToolUtil.MD5(password);
        }
        if (!StrUtil.equals(password, member.getPassword())) {
            throw new CustomException("密码错误！");
        }
        SysUserAuthConstants.setUserLoginRedisCache(member.getId(), BeanUtil.beanToMap(member));
        String userToken;
        if (RequestType.APP.getKey().equals(requestType)) {
            userToken = GetUserToken.createNewToken(member.getId() + SysUserAuthConstants.APP_IDENTIFYING, password);
        } else {
            userToken = GetUserToken.createNewToken(member.getId(), password);
        }
        member.setPassword(null);
        member.setPwdNumEnc(null);
        member.setUserToken(userToken);
        return member;
    }

    @Override
    public void shopLoginForApp(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Member member = login(map, RequestType.APP.getKey());
        outputObject.setBean(member);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public void shopLogout(InputObject inputObject, OutputObject outputObject) {
        String userId = GetUserToken.getUserTokenUserId(PutObject.getRequest());
        SysUserAuthConstants.delUserLoginRedisCache(userId);
        inputObject.removeSession();
    }

    @Override
    public void editShopUserPassword(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String newPassword = map.get("newPassword").toString();
        String userId = inputObject.getLogParams().get("id").toString();
        int pwdNum = (int) (Math.random() * 100);
        for (int i = 0; i < pwdNum; i++) {
            newPassword = ToolUtil.MD5(newPassword);
        }
        memberService.editMemberPassword(userId, newPassword, pwdNum);
    }

    @Override
    public void sendShopSmsCode(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String mobile = params.get("mobile").toString();
        Integer scene = Integer.parseInt(params.get("scene").toString());
        // 情况 1：如果是修改手机场景，需要校验新手机号是否已经注册，说明不能使用该手机了
        if (scene == SmsSceneEnum.UPDATE_MOBILE.getKey()) {
            Member member = memberService.queryMemberByPhone(mobile);
            if (ObjectUtil.isNotEmpty(member)) {
                throw new CustomException("手机号已经被使用");
            }
        }
        // 情况 2：如果是重置密码场景，需要校验手机号是存在的
        if (scene == SmsSceneEnum.RESET_PASSWORD.getKey()) {
            Member member = memberService.queryMemberByPhone(mobile);
            if (ObjectUtil.isEmpty(member)) {
                throw new CustomException("手机号不存在");
            }
        }
        // 情况 3：如果是修改密码场景，需要查询手机号，无需前端传递
        if (scene == SmsSceneEnum.UPDATE_PASSWORD.getKey()) {
            String id = inputObject.getLogParams().get("id").toString();
            Member member = memberService.selectById(id);
            if (StrUtil.isEmpty(member.getPhone())) {
                throw new CustomException("您还没有绑定手机号，请先绑定手机号");
            }
            mobile = member.getPhone();
        }
        if (StrUtil.isEmpty(mobile)) {
            throw new CustomException("手机号不能为空");
        }
        // 发送短信验证码
        iSmsCodeService.sendSmsCodeReq(mobile, scene);
    }

    @Override
    public void smsShopLogin(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String mobile = params.get("mobile").toString();
        String smsCode = params.get("smsCode").toString();
        // 校验验证码
        iSmsCodeService.validateSmsCode(mobile, smsCode, SmsSceneEnum.LOGIN.getKey());
    }
}
