/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.userauth.user.UserTreeQueryDo;
import com.skyeye.personnel.entity.SysEveUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveUserDao extends SkyeyeBaseMapper<SysEveUser> {

    List<Map<String, Object>> querySysUserList(CommonPageInfo pageInfo);

    Map<String, Object> queryMationByUserCode(@Param("userCode") String userCode);

    Map<String, Object> queryBindRoleMationByUserId(Map<String, Object> map);

    int editRoleIdsByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryDeskTopsMenuByUserId(@Param("userId") String userId);

    int editUserInstallThemeColor(Map<String, Object> map);

    int editUserInstallWinBgPic(Map<String, Object> map);

    int editUserInstallWinLockBgPic(Map<String, Object> map);

    int editUserInstallWinStartMenuSize(Map<String, Object> map);

    int editUserInstallWinTaskPosition(Map<String, Object> map);

    int insertSysUserInstallMation(Map<String, Object> map);

    int editUserInstallVagueBgSrc(Map<String, Object> map);

    int editUserInstallLoadMenuIconById(Map<String, Object> map);

    Map<String, Object> queryUserDetailsMationByUserId(@Param("userId") String userId);

    int editUserDetailsMationByUserId(Map<String, Object> map);

    List<Map<String, Object>> querySysUserListByUserName(Map<String, Object> map);

    List<Map<String, Object>> querySysDeskTopByUserId(Map<String, Object> user);

    Map<String, Object> queryUserSchoolMationByUserId(@Param("userId") String userId);

    List<Map<String, Object>> queryUserStaffToTree(UserTreeQueryDo queryDo);

    List<Map<String, Object>> queryUserStaffDepToTree(UserTreeQueryDo queryDo);

    List<Map<String, Object>> queryTalkGroupUserListByUserId(UserTreeQueryDo queryDo);

    Map<String, Object> queryWxUserMationByOpenId(@Param("openId") String openId);

    Map<String, Object> queryUserMationByOpenId(@Param("openId") String openId);

    Map<String, Object> queryUserBindMationByUserId(@Param("userId") String userId);

    int updateBindUserMation(Map<String, Object> map);

    int insertWxUserMation(Map<String, Object> map);

}
