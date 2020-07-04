/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.dao.MemberDao;
import com.skyeye.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author 奈何繁华如云烟
 * @Description TODO
 * @Date 2019/9/16 21:24
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 获取会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryMemberByList(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = memberDao.queryMemberByList(params,
                new PageBounds(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString())));
        PageList<Map<String, Object>> beansPageList = (PageList<Map<String, Object>>)beans;
        int total = beansPageList.getPaginator().getTotalCount();
        outputObject.setBeans(beans);
        outputObject.settotal(total);
    }

    /**
     * 添加会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void insertMember(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        //验证某一租户下会员信息是否存
        Map<String, Object> bean = memberDao.queryMemberByUserIdAndMember(params);
        if(bean != null){
            outputObject.setreturnMessage("该会员信息已存在！");
            return;
        }
        params.put("id", ToolUtil.getSurFaceId());
        params.put("createTime", ToolUtil.getTimeAndToString());
        params.put("memberType", 3);
        params.put("enabled", 1);
        params.put("isystem", 1);
        params.put("deleteFlag", 0);
        memberDao.insertMember(params);
    }

    /**
     * 根据ID查询会员信息，用于信息回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryMemberById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> bean = memberDao.queryMemberById(params);
        if (bean == null){
            outputObject.setreturnMessage("未查询到该会员信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void deleteMemberById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        params.put("deleteFlag", 1);
        memberDao.editMemberByDeleteFlag(params);
    }

    /**
     * 编辑会员信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editMemberById(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> memberName = memberDao.queryMemberByIdAndName(params);
        if(memberName != null){
            outputObject.setreturnMessage("会员名称已存在！");
            return;
        }
        memberDao.editMemberById(params);
    }

    /**
     * 会员状态改为启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editMemberByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        params.put("enabled", 1);
        Map<String, Object> bean = memberDao.queryMemberByEnabled(params);
        if (bean != null){
            outputObject.setreturnMessage("状态已改变，请不要重复操作！");
            return;
        }
        memberDao.editMemberByEnabled(params);
    }

    /**
     * 会员状态改为未启用
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    @Transactional(value="transactionManager")
    public void editMemberByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        params.put("enabled", 2);
        Map<String, Object> bean = memberDao.queryMemberByEnabled(params);
        if (bean != null){
            outputObject.setreturnMessage("状态已改变，请不要重复操作！");
            return;
        }
        memberDao.editMemberByNotEnabled(params);
    }

    /**
     * 查看会员详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryMemberByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        Map<String, Object> bean = memberDao.queryMemberByIdAndInfo(params);
        if(bean == null){
            outputObject.setreturnMessage("未查询到信息！");
            return;
        }
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 获取会员列表信息展示为下拉框
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
	@Override
	public void queryMemberListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> params = inputObject.getParams();
        params.put("tenantId", inputObject.getLogParams().get("tenantId"));
        List<Map<String, Object>> beans = memberDao.queryMemberListToSelect(params);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
	}
}
