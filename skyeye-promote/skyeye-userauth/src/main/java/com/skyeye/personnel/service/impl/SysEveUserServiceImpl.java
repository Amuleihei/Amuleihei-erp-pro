/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.*;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.*;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.authority.service.SysAuthorityService;
import com.skyeye.eve.entity.userauth.user.UserTreeQueryDo;
import com.skyeye.exception.CustomException;
import com.skyeye.organization.service.*;
import com.skyeye.personnel.classenum.UserIsTermOfValidity;
import com.skyeye.personnel.classenum.UserLockState;
import com.skyeye.personnel.dao.SysEveUserDao;
import com.skyeye.personnel.entity.SysEveUser;
import com.skyeye.personnel.entity.SysEveUserStaff;
import com.skyeye.personnel.service.SysEveUserService;
import com.skyeye.personnel.service.SysEveUserStaffService;
import com.skyeye.role.service.SysEveRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: SysEveUserServiceImpl
 * @Description: 系统用户管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/13 9:50
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "用户管理", groupName = "用户管理")
public class SysEveUserServiceImpl extends SkyeyeBusinessServiceImpl<SysEveUserDao, SysEveUser> implements SysEveUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysEveUserServiceImpl.class);

    @Autowired
    private SysEveUserDao sysEveUserDao;

    @Autowired
    private SysEveRoleService sysEveRoleService;

    @Autowired
    private SysEveUserStaffService sysEveUserStaffService;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @Autowired
    private ICompanyService iCompanyService;

    @Autowired
    private IDepmentService iDepmentService;

    @Autowired
    private ICompanyJobService iCompanyJobService;

    @Autowired
    private CompanyMationService companyMationService;

    @Autowired
    private CompanyDepartmentService companyDepartmentService;

    @Autowired
    private CompanyJobService companyJobService;

    @Autowired
    private ICompanyJobScoreService iCompanyJobScoreService;

    /**
     * 获取管理员用户列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        List<Map<String, Object>> beans = sysEveUserDao.querySysUserList(pageInfo);
        iCompanyService.setNameForMap(beans, "companyId", "companyName");
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        iCompanyJobService.setNameForMap(beans, "jobId", "jobName");
        beans.forEach(bean -> {
            bean.put("staffServiceClassName", sysEveUserStaffService.getServiceClassName());
        });
        iAuthUserService.setNameForMap(beans, "createId", "createName");
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editSysUserLockStateToLockById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        SysEveUser sysEveUser = selectById(id);
        if (UserLockState.SYS_USER_LOCK_STATE_ISUNLOCK.getKey() == sysEveUser.getUserLock()) {
            // 未锁定，设置为锁定
            editUserLockState(id, UserLockState.SYS_USER_LOCK_STATE_ISLOCK.getKey());
        } else {
            outputObject.setreturnMessage("该账号已被锁定，请刷新页面.");
        }
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editSysUserLockStateToUnLockById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        SysEveUser sysEveUser = selectById(id);
        if (UserLockState.SYS_USER_LOCK_STATE_ISLOCK.getKey() == sysEveUser.getUserLock()) {
            // 锁定，设置为解锁
            editUserLockState(id, UserLockState.SYS_USER_LOCK_STATE_ISUNLOCK.getKey());
        } else {
            outputObject.setreturnMessage("该账号已解锁，请刷新页面.");
        }
    }

    /**
     * 创建账号
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void insertSysUserMationById(InputObject inputObject, OutputObject outputObject) {
        SysEveUser sysEveUser = inputObject.getParams(SysEveUser.class);
        // 判断账号是否存在
        QueryWrapper<SysEveUser> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SysEveUser::getUserCode), sysEveUser.getUserCode());
        long count = count(wrapper);
        if (count == 0) {
            String currentUserId = inputObject.getLogParams().get("id").toString();
            int pwdNum = (int) (Math.random() * 100);
            sysEveUser.setPwdNumEnc(pwdNum);
            sysEveUser.setPassword(getCalcPaswword(sysEveUser.getPassword(), pwdNum));
            sysEveUser.setUserLock(UserLockState.SYS_USER_LOCK_STATE_ISUNLOCK.getKey());

            // 根据员工id获取员工所属部门
            SysEveUserStaff staff = sysEveUserStaffService.selectById(sysEveUser.getStaffId());
            if (ObjectUtil.isNotEmpty(staff) && StrUtil.isNotEmpty(staff.getId())) {
                // 删除redis中缓存的单位下的用户
                jedisClientService.delKeys(Constants.getSysTalkGroupUserListMationById(staff.getDepartmentId()) + "*");
            } else {
                outputObject.setreturnMessage("员工信息不存在.");
                return;
            }
            // 1.新增用户信息
            String id = createEntity(sysEveUser, currentUserId);
            // 2.新增用户设置信息
            setUserBaseInstall(id);
            // 3.修改员工与账号的关系
            sysEveUserStaffService.editSysUserStaffBindUserId(sysEveUser.getStaffId(), id);
        } else {
            outputObject.setreturnMessage("该账号已存在，请更换！");
        }
    }

    private String getCalcPaswword(String password, int pwdNum) {
        for (int i = 0; i < pwdNum; i++) {
            password = ToolUtil.MD5(password);
        }
        return password;
    }

    @Override
    public SysEveUser selectById(String id) {
        SysEveUser sysEveUser = super.selectById(id);
        sysEveUser.setPassword(null);
        sysEveUser.setPwdNumEnc(null);
        return sysEveUser;
    }

    /**
     * 设置用户基础配置信息
     *
     * @param userId 用户id
     */
    private void setUserBaseInstall(String userId) {
        Map<String, Object> bean = new HashMap<>();
        bean.put("userId", userId);
        bean.put("winBgPicUrl", "/images/upload/winbgpic/default.jpg");
        bean.put("winLockBgPicUrl", "/images/upload/winlockbgpic/default.jpg");
        bean.put("winThemeColor", "31");
        bean.put("winStartMenuSize", "sm");
        bean.put("winTaskPosition", "bottom");
        DataCommonUtil.setCommonData(bean, userId);
        sysEveUserDao.insertSysUserInstallMation(bean);
    }

    /**
     * 重置密码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editSysUserPasswordMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        int pwdNum = (int) (Math.random() * 100);
        String password = map.get("password").toString();
        // 更新数据库中的密码
        UpdateWrapper<SysEveUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getPassword), getCalcPaswword(password, pwdNum));
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getPwdNumEnc), pwdNum);
        update(updateWrapper);
    }

    /**
     * 登录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserToLogin(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userCode = map.get("userCode").toString();
        Map<String, Object> userMation = sysEveUserDao.queryMationByUserCode(userCode);
        if (userMation == null) {
            outputObject.setreturnMessage("请确保用户名输入无误！");
        } else {
            int pwdNum = Integer.parseInt(userMation.get("pwdNum").toString());
            String password = map.get("password").toString();
            for (int i = 0; i < pwdNum; i++) {
                password = ToolUtil.MD5(password);
            }
            String userDBPassword = userMation.get("password").toString();
            if (password.equals(userDBPassword)) {
                int userLock = Integer.parseInt(userMation.get("userLock").toString());
                if (UserLockState.SYS_USER_LOCK_STATE_ISLOCK.getKey() == userLock) {
                    outputObject.setreturnMessage("您的账号已被锁定，请联系管理员解除！");
                } else {
                    // 校验用户有效期
                    chectUserEffectiveDate(userMation);

                    String userId = userMation.get("id").toString();
                    setUserOtherMation(userMation);
                    List<Map<String, Object>> authPoints = getMenuAndAuthToRedis(userMation, userId);
                    judgeAndGetSchoolMation(userMation, userId);
                    LOGGER.info("set userMation to redis cache start.");
                    setUserLoginRedisMation(userId, userMation);
                    LOGGER.info("set userMation to redis cache end.");
                    String userToken = GetUserToken.createNewToken(userId, userDBPassword);
                    userMation.put("userToken", userToken);
                    outputObject.setBean(userMation);
                    outputObject.setBeans(authPoints);
                }
            } else {
                outputObject.setreturnMessage("密码输入错误！");
            }
        }
    }

    private void chectUserEffectiveDate(Map<String, Object> userMation) {
        Integer isTermOfValidity = Integer.parseInt(userMation.get("isTermOfValidity").toString());
        if (isTermOfValidity == UserIsTermOfValidity.EFFECTIVE_TIME_PERIOD.getKey()) {
            // 时间段有效期
            String startTime = userMation.get("startTime").toString();
            String endTime = userMation.get("endTime").toString();
            String currentTime = DateUtil.getYmdTimeAndToString();
            if (DateUtil.getDistanceDay(startTime, currentTime) >= 0 && DateUtil.getDistanceDay(currentTime, endTime) >= 0) {
                // startTime <= 当前时间 <= endTime
            } else {
                throw new CustomException("用户有效期已过，请联系管理员续期！");
            }
        }
    }

    private void setUserLoginRedisMation(String userId, Map<String, Object> userMation) {
        SysUserAuthConstants.setUserLoginRedisCache(userId, userMation);
    }

    private void setUserOtherMation(Map<String, Object> userMation) {
        iCompanyService.setNameForMap(userMation, "companyId", "companyName");
        iDepmentService.setNameForMap(userMation, "departmentId", "departmentName");
        iCompanyJobService.setNameForMap(userMation, "jobId", "jobName");
    }

    /**
     * 获取用户菜单权限信息并存入redis缓存
     *
     * @param userMation
     * @param userId
     * @return
     */
    private List<Map<String, Object>> getMenuAndAuthToRedis(Map<String, Object> userMation, String userId) {
        LOGGER.info("get menu and auth mation.");
        String roleIds = userMation.get("roleId").toString();
        // 桌面菜单列表
        List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
        deskTops = ToolUtil.listToTree(deskTops, "id", "parentId", "childs");
        List<Map<String, Object>> authPoints = sysAuthorityService.getRoleHasMenuPointListByRoleIds(roleIds, userId);

        LOGGER.info("set menu and auth mation to redis cache start.");
        jedisClientService.set(ObjectConstant.getDeskTopsCacheKey(userId), JSONUtil.toJsonStr(deskTops));
        jedisClientService.set(ObjectConstant.getAllMenuCacheKey(userId), roleIds);
        jedisClientService.set("authPointsMation:" + userId, roleIds);
        LOGGER.info("set menu and auth mation to redis cache end.");
        userMation.remove("roleId");
        return authPoints;
    }

    /**
     * 处理该用户的学校权限信息
     *
     * @param userMation
     * @param userId
     */
    private void judgeAndGetSchoolMation(Map<String, Object> userMation, String userId) {
        // 处理学校权限信息
        // 当前登录帐号包含某所学校的id
        Map<String, Object> schoolMation = sysEveUserDao.queryUserSchoolMationByUserId(userId);
        if (schoolMation != null && !schoolMation.isEmpty()) {
            if (schoolMation.containsKey("schoolId") && !ToolUtil.isBlank(schoolMation.get("schoolId").toString())) {
                // 判断该用户的学校的数据权限-----数据权限  1.查看所有  2.查看本校
                int power = schoolMation.containsKey("schoolPower") ? Integer.parseInt(schoolMation.get("schoolPower").toString()) : 2;
                if (power == 2) {
                    // 将用户有权查看的学校id放入登录信息中
                    userMation.put("schoolPowerId", schoolMation.get("schoolId").toString());
                } else {
                    userMation.put("schoolPowerId", "all");
                }
            }
        }
    }

    /**
     * 从session中获取用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserMationBySession(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> userMation = inputObject.getLogParams();
        if (userMation == null) {
            outputObject.setreturnMessage("登录超时，请重新登录。");
        } else {
            outputObject.setBean(userMation);
        }
    }

    /**
     * 退出
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteUserMationBySession(InputObject inputObject, OutputObject outputObject) {
        String userId = GetUserToken.getUserTokenUserId(PutObject.getRequest());
        this.removeLogin(userId);
        inputObject.removeSession();
    }

    /**
     * 退出登录
     *
     * @param userId 用户id
     */
    @Override
    public void removeLogin(String userId) {
        SysUserAuthConstants.delUserLoginRedisCache(userId);
        jedisClientService.del(ObjectConstant.getDeskTopsCacheKey(userId));
        jedisClientService.del(ObjectConstant.getAllMenuCacheKey(userId));
        jedisClientService.del("authPointsMation:" + userId);
        if (userId.lastIndexOf(SysUserAuthConstants.APP_IDENTIFYING) < 0) {
            // PC端用户登录信息
        } else {
            // 手机端用户登录信息
        }
    }

    /**
     * 获取角色和当前已经绑定的角色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryRoleAndBindRoleByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 获取角色列表
        List<Map<String, Object>> roles = sysEveRoleService.queryAllDataForMap();
        // 获取用户绑定的角色ID串
        Map<String, Object> userRole = sysEveUserDao.queryBindRoleMationByUserId(map);
        String[] roleIds = userRole.get("roleIds").toString().split(",");
        for (Map<String, Object> bean : roles) {
            if (Arrays.asList(roleIds).contains(bean.get("id").toString())) {
                bean.put("isCheck", "checked");
            }
        }
        outputObject.setBeans(roles);
        outputObject.settotal(roles.size());
    }

    /**
     * 编辑用户绑定的角色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editRoleIdsByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        sysEveUserDao.editRoleIdsByUserId(map);
    }

    /**
     * 获取当前登录用户的桌面菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDeskTopMenuBySession(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> deskTops = inputObject.getLogDeskTopMenuParams();
        outputObject.setBeans(deskTops);
    }

    /**
     * 获取当前登录用户的所有菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllMenuBySession(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> deskTops = inputObject.getLogAllMenuParams();
        outputObject.setBeans(deskTops);
    }

    /**
     * 自定义设置主题颜色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallThemeColor(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        //修改reids中的用户信息
        user.put("winThemeColor", map.get("themeColor"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallThemeColor(map);
    }

    /**
     * 自定义设置win背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallWinBgPic(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        // 修改reids中的用户信息
        user.put("winBgPicUrl", map.get("winBgPicUrl"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallWinBgPic(map);
    }

    /**
     * 自定义设置win锁屏背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallWinLockBgPic(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        // 修改reids中的用户信息
        user.put("winLockBgPicUrl", map.get("winLockBgPicUrl"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallWinLockBgPic(map);
    }

    /**
     * 自定义设置win开始菜单尺寸
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallWinStartMenuSize(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        // 修改reids中的用户信息
        user.put("winStartMenuSize", map.get("winStartMenuSize"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallWinStartMenuSize(map);
    }

    /**
     * 自定义设置win任务栏在屏幕的位置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallWinTaskPosition(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        // 修改reids中的用户信息
        user.put("winTaskPosition", map.get("winTaskPosition"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallWinTaskPosition(map);
    }

    /**
     * 修改密码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserPassword(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        // 根据redis中的用户信息userCode获取用户信息
        Map<String, Object> userMation = sysEveUserDao.queryMationByUserCode(user.get("userCode").toString());
        int pwdNum = Integer.parseInt(userMation.get("pwdNum").toString());
        String password = map.get("oldPassword").toString();
        for (int i = 0; i < pwdNum; i++) {
            password = ToolUtil.MD5(password);
        }
        if (password.equals(userMation.get("password").toString())) {
            // 输入的旧密码数据库中的旧密码一致，转化新密码
            String newPassword = map.get("newPassword").toString();
            for (int i = 0; i < pwdNum; i++) {
                newPassword = ToolUtil.MD5(newPassword);
            }
            // 更新数据库中的密码
            UpdateWrapper<SysEveUser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(CommonConstants.ID, user.get("id").toString());
            updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getPassword), newPassword);
            update(updateWrapper);
        } else {
            outputObject.setreturnMessage("旧密码输入错误.");
        }
    }

    /**
     * 自定义设置win雾化
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallVagueBgSrc(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        // 修改reids中的用户信息
        user.put("winBgPicVague", map.get("winBgPicVague"));
        user.put("winBgPicVagueValue", map.get("winBgPicVagueValue"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallVagueBgSrc(map);
    }

    /**
     * 自定义设置窗口下面展示的是图标还是图标+文字
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserInstallLoadMenuIconById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        // 修改reids中的用户信息
        user.put("loadBottomMenuIcon", map.get("loadBottomMenuIcon"));
        setUserLoginRedisMation(user.get("id").toString(), user);
        sysEveUserDao.editUserInstallLoadMenuIconById(map);
    }

    /**
     * 锁屏密码解锁
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserLockByLockPwd(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        int pwdNum = Integer.parseInt(user.get("pwdNum").toString());
        String password = map.get("password").toString();
        for (int i = 0; i < pwdNum; i++) {
            password = ToolUtil.MD5(password);
        }
        if (!password.equals(user.get("password").toString())) {
            outputObject.setreturnMessage("密码输入错误。");
        }
    }

    /**
     * 修改个人信息时获取数据回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> user = inputObject.getLogParams();
        Map<String, Object> bean = sysEveUserDao.queryUserDetailsMationByUserId(user.get("id").toString());
        iCompanyService.setNameForMap(bean, "companyId", "companyName");
        iDepmentService.setNameForMap(bean, "departmentId", "departmentName");
        iCompanyJobService.setNameForMap(bean, "jobId", "jobName");
        iCompanyJobScoreService.setNameForMap(bean, "jobScoreId", "jobScoreName");
        outputObject.setBean(bean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 修改个人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        sysEveUserDao.editUserDetailsMationByUserId(map);
        // 删除用户在redis中存储的信息
        jedisClientService.del(Constants.getSysTalkUserThisMainMationById(user.get("id").toString()));
    }

    /**
     * 获取还没有分配账号的员工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysUserListByUserName(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = sysEveUserDao.querySysUserListByUserName(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 获取该用户拥有的桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysDeskTopByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("id", user.get("id"));
        List<Map<String, Object>> beans = sysEveUserDao.querySysDeskTopByUserId(map);
        outputObject.setBeans(beans);
    }

    /**
     * 根据用户id获取桌面菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDeskTopsMenuByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = map.get("userId").toString();
        // 桌面菜单列表
        List<Map<String, Object>> deskTops = sysEveUserDao.queryDeskTopsMenuByUserId(userId);
        outputObject.setBeans(deskTops);
        outputObject.settotal(deskTops.size());
    }

    /**
     * 人员选择获取所有公司和人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) {
        UserTreeQueryDo queryDo = inputObject.getParams(UserTreeQueryDo.class);
        compareSelUserListByParams(queryDo, inputObject);
        List<Map<String, Object>> result = new ArrayList<>();
        setOrganization(result, StringUtils.EMPTY);
        List<Map<String, Object>> beans = sysEveUserDao.queryUserStaffToTree(queryDo);
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        result.addAll(beans);
        outputObject.setBeans(result);
    }

    /**
     * 设置组织信息
     *
     * @param beans
     * @param companyId
     */
    private void setOrganization(List<Map<String, Object>> beans, String companyId) {
        beans.addAll(companyMationService.queryCompanyList(companyId));
        beans.addAll(companyDepartmentService.queryDepartmentList(Arrays.asList(companyId), new ArrayList<>()));
        beans.addAll(companyJobService.queryJobList(Arrays.asList(companyId), new ArrayList<>()));
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject) {
        UserTreeQueryDo queryDo = inputObject.getParams(UserTreeQueryDo.class);
        compareSelUserListByParams(queryDo, inputObject);
        String companyId = inputObject.getLogParams().get("companyId").toString();
        queryDo.setCompanyId(companyId);

        List<Map<String, Object>> result = new ArrayList<>();
        setOrganization(result, companyId);
        List<Map<String, Object>> beans = sysEveUserDao.queryUserStaffToTree(queryDo);
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        result.addAll(beans);
        outputObject.setBeans(result);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司部门展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject) {
        UserTreeQueryDo queryDo = inputObject.getParams(UserTreeQueryDo.class);
        compareSelUserListByParams(queryDo, inputObject);
        String companyId = inputObject.getLogParams().get("companyId").toString();
        queryDo.setCompanyId(companyId);
        List<Map<String, Object>> result = new ArrayList<>();
        result.addAll(companyMationService.queryCompanyList(companyId));
        result.addAll(companyDepartmentService.queryDepartmentList(Arrays.asList(companyId), new ArrayList<>()));
        List<Map<String, Object>> beans = sysEveUserDao.queryUserStaffDepToTree(queryDo);
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        result.addAll(beans);
        outputObject.setBeans(result);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司岗位展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject) {
        UserTreeQueryDo queryDo = inputObject.getParams(UserTreeQueryDo.class);
        compareSelUserListByParams(queryDo, inputObject);
        String companyId = inputObject.getLogParams().get("companyId").toString();
        queryDo.setCompanyId(companyId);
        List<Map<String, Object>> result = new ArrayList<>();
        setOrganization(result, companyId);
        List<Map<String, Object>> beans = sysEveUserDao.queryUserStaffToTree(queryDo);
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        result.addAll(beans);
        outputObject.setBeans(result);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司同级部门展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject) {
        UserTreeQueryDo queryDo = inputObject.getParams(UserTreeQueryDo.class);
        compareSelUserListByParams(queryDo, inputObject);
        String departmentId = inputObject.getLogParams().get("departmentId").toString();
        queryDo.setDepartmentId(departmentId);
        List<Map<String, Object>> beans = sysEveUserDao.queryUserStaffDepToTree(queryDo);
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        beans.addAll(companyDepartmentService.queryDepartmentList(new ArrayList<>(), Arrays.asList(inputObject.getLogParams().get("departmentId").toString())));
        outputObject.setBeans(beans);
    }

    /**
     * 根据聊天组展示用户
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject) {
        UserTreeQueryDo queryDo = inputObject.getParams(UserTreeQueryDo.class);
        compareSelUserListByParams(queryDo, inputObject);
        Map<String, Object> user = inputObject.getLogParams();
        queryDo.setUserId(user.get("id").toString());
        List<Map<String, Object>> beans = sysEveUserDao.queryTalkGroupUserListByUserId(queryDo);
        iDepmentService.setNameForMap(beans, "departmentId", "departmentName");
        outputObject.setBeans(beans);
    }

    /**
     * 获取人员列表时的参数转换
     *
     * @param queryDo
     * @param inputObject 入参以及用户信息等获取对象
     * @return
     */
    public void compareSelUserListByParams(UserTreeQueryDo queryDo, InputObject inputObject) {
        // 人员列表中是否包含自己--1.包含；其他参数不包含
        if (queryDo.getChooseOrNotMy() != 1) {
            Map<String, Object> user = inputObject.getLogParams();
            queryDo.setUserId(user.get("id").toString());
        }
        // 人员列表中是否必须绑定邮箱--1.必须；其他参数没必要
        if (queryDo.getChooseOrNotEmail() == 1) {
            queryDo.setHasEmail(1);
        }
    }

    /**
     * 手机端用户登录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPhoneToLogin(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userCode = map.get("userCode").toString();
        Map<String, Object> userMation = sysEveUserDao.queryMationByUserCode(userCode);
        if (userMation == null) {
            outputObject.setreturnMessage("请确保用户名输入无误！");
        } else {
            int pwdNum = Integer.parseInt(userMation.get("pwdNum").toString());
            String password = map.get("password").toString();
            for (int i = 0; i < pwdNum; i++) {
                password = ToolUtil.MD5(password);
            }
            if (password.equals(userMation.get("password").toString())) {
                int userLock = Integer.parseInt(userMation.get("userLock").toString());
                if (UserLockState.SYS_USER_LOCK_STATE_ISLOCK.getKey() == userLock) {
                    outputObject.setreturnMessage("您的账号已被锁定，请联系管理员解除！");
                } else {
                    String userId = userMation.get("id").toString();
                    String roleIds = userMation.get("roleId").toString();
                    userMation.remove("roleId");

                    // 获取动态token
                    String userToken = GetUserToken.createNewToken(userId, password);
                    userMation.put("userToken", userToken);

                    String appUserId = userId + SysUserAuthConstants.APP_IDENTIFYING;
                    iDepmentService.setNameForMap(userMation, "departmentId", "departmentName");
                    iCompanyJobService.setNameForMap(userMation, "jobId", "jobName");
                    SysUserAuthConstants.setUserLoginRedisCache(appUserId, userMation);
                    jedisClientService.set(ObjectConstant.getAllMenuCacheKey(userId), roleIds);
                    jedisClientService.set("authPointsMation:" + appUserId, roleIds);
                    // 获取用户权限点返回给前台
                    List<Map<String, Object>> authPoints = sysAuthorityService.getRoleHasMenuPointListByRoleIds(roleIds, appUserId);
                    outputObject.setBean(userMation);
                    outputObject.setBeans(authPoints);
                }
            } else {
                outputObject.setreturnMessage("密码输入错误！");
            }
        }
    }

    /**
     * 根据openId获取用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String openId = map.get("openId").toString();
        //判断该微信用户在redis中是否存在数据
        String key = WxchatUtil.getWechatUserOpenIdMation(openId);
        if (ToolUtil.isBlank(jedisClientService.get(key))) {
            //该用户没有绑定账号
            Map<String, Object> bean = sysEveUserDao.queryWxUserMationByOpenId(openId);
            //判断该用户的openId是否存在于数据库
            if (bean != null && !bean.isEmpty()) {
                //存在数据库
                map.putAll(bean);
                //1.将微信和账号的绑定信息存入redis
                jedisClientService.set(key, JSONUtil.toJsonStr(bean));
                //如果已经绑定用户，则获取用户信息
                if (bean.containsKey("userId") && !ToolUtil.isBlank(bean.get("userId").toString())) {
                    Map<String, Object> userMation = sysEveUserDao.queryUserMationByOpenId(openId);
                    iCompanyService.setNameForMap(userMation, "companyId", "companyName");
                    iDepmentService.setNameForMap(userMation, "departmentId", "departmentName");
                    // 2.将账号的信息存入redis
                    SysUserAuthConstants.setUserLoginRedisCache(bean.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, userMation);
                    //3.将权限的信息存入redis
                    jedisClientService.set("authPointsMation:" + bean.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, "");
                }
            } else {
                //不存在
                map.put("id", ToolUtil.getSurFaceId());
                map.put("joinTime", DateUtil.getTimeAndToString());
                map.put("openId", openId);
                map.put("userId", "");
                sysEveUserDao.insertWxUserMation(map);
                //1.将微信和账号的绑定信息存入redis
                jedisClientService.set(key, JSONUtil.toJsonStr(map));
            }
        } else {
            map = JSONUtil.toBean(jedisClientService.get(key), null);
            //如果已经绑定用户，则获取用户信息
            if (map.containsKey("userId") && !ToolUtil.isBlank(map.get("userId").toString())) {
                Map<String, Object> userMation = sysEveUserDao.queryUserMationByOpenId(openId);
                iCompanyService.setNameForMap(userMation, "companyId", "companyName");
                //2.将账号的信息存入redis
                SysUserAuthConstants.setUserLoginRedisCache(map.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, userMation);
                //3.将权限的信息存入redis
                jedisClientService.set("authPointsMation:" + map.get("userId").toString() + SysUserAuthConstants.APP_IDENTIFYING, "");
            } else {
                outputObject.setreturnMessage("您还未绑定用户，请前往绑定.", "-9000");
            }
        }
        outputObject.setBean(map);
    }

    @Override
    public void editUserLockState(String id, Integer userLock) {
        UpdateWrapper<SysEveUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getUserLock), userLock);
        update(updateWrapper);
    }

    /**
     * openId绑定用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void insertUserMationByOpenId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userCode = map.get("userCode").toString();
        String password = map.get("password").toString();
        String openId = map.get("openId").toString();
        // 根据账号获取用户信息
        Map<String, Object> userMation = sysEveUserDao.queryMationByUserCode(userCode);
        // 判断该账号是否存在
        if (userMation != null && !userMation.isEmpty()) {
            int pwdNum = Integer.parseInt(userMation.get("pwdNum").toString());
            for (int i = 0; i < pwdNum; i++) {
                password = ToolUtil.MD5(password);
            }
            //判断密码是否正确
            if (password.equals(userMation.get("password").toString())) {
                //判断账号是否锁定
                int userLock = Integer.parseInt(userMation.get("userLock").toString());
                if (UserLockState.SYS_USER_LOCK_STATE_ISLOCK.getKey() == userLock) {
                    outputObject.setreturnMessage("您的账号已被锁定，请联系管理员解除.");
                } else {
                    Map<String, Object> wxUserMation = sysEveUserDao.queryWxUserMationByOpenId(openId);
                    //判断该用户的openId是否存在于数据库
                    if (wxUserMation != null && !wxUserMation.isEmpty()) {
                        //判断当前openId是否已经绑定账号
                        if (wxUserMation.containsKey("userId") && !ToolUtil.isBlank(wxUserMation.get("userId").toString())) {
                            outputObject.setreturnMessage("该微信用户已绑定账号.");
                        } else {
                            //判断该账号是否被别人绑定
                            Map<String, Object> isBindInWx = sysEveUserDao.queryUserBindMationByUserId(userMation.get("id").toString());
                            if (isBindInWx != null && !isBindInWx.isEmpty()) {
                                outputObject.setreturnMessage("该账号已被绑定.");
                            } else {
                                iCompanyJobService.setNameForMap(userMation, "jobId", "jobName");
                                //构建绑定信息对象
                                map = new HashMap<>();
                                String userId = userMation.get("id").toString();
                                map.put("userId", userId);
                                map.put("bindTime", DateUtil.getTimeAndToString());
                                map.put("openId", openId);
                                sysEveUserDao.updateBindUserMation(map);
                                //重新获取绑定信息，存入redis，返回前端
                                map = sysEveUserDao.queryWxUserMationByOpenId(openId);
                                //1.将微信和账号的绑定信息存入redis
                                String key = WxchatUtil.getWechatUserOpenIdMation(openId);
                                jedisClientService.set(key, JSONUtil.toJsonStr(map));
                                //2.将账号的信息存入redis
                                SysUserAuthConstants.setUserLoginRedisCache(userId + SysUserAuthConstants.APP_IDENTIFYING, userMation);
                                //3.将权限的信息存入redis
                                jedisClientService.set("authPointsMation:" + userId + SysUserAuthConstants.APP_IDENTIFYING, "");
                                outputObject.setBean(map);
                            }
                        }
                    } else {
                        outputObject.setreturnMessage("该微信用户不存在.");
                    }
                }
            } else {
                outputObject.setreturnMessage("密码输入错误.");
            }
        } else {
            outputObject.setreturnMessage("该账号不存在，请核实后进行登录.");
        }
    }

    @Override
    public void resetUserEffectiveDate(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String id = map.get("id").toString();
        Integer isTermOfValidity = Integer.parseInt(map.get("isTermOfValidity").toString());
        String startTime = map.get("startTime").toString();
        String endTime = map.get("endTime").toString();
        UpdateWrapper<SysEveUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getIsTermOfValidity), isTermOfValidity);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getStartTime), startTime);
        updateWrapper.set(MybatisPlusUtil.toColumns(SysEveUser::getEndTime), endTime);
        update(updateWrapper);
    }

}
