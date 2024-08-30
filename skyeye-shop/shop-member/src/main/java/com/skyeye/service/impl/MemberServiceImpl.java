/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.CharUtil;
import com.skyeye.dao.MemberDao;
import com.skyeye.entity.Member;
import com.skyeye.eve.service.IAreaService;
import com.skyeye.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MemberServiceImpl
 * @Description: 会员管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/2 15:37
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "会员管理", groupName = "会员管理")
public class MemberServiceImpl extends SkyeyeBusinessServiceImpl<MemberDao, Member> implements MemberService {

    @Autowired
    private IAreaService iAreaService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        pageInfo.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryMemberByList(pageInfo);
        return beans;
    }

    @Override
    public void createPrepose(Member entity) {
        setMemberMation(entity);
    }

    @Override
    public void updatePrepose(Member entity) {
        setMemberMation(entity);
    }

    private void setMemberMation(Member entity) {
        String name = entity.getName();
        name = CharUtil.filterEmoji(name);
        name = CharUtil.removeFourChar(name);
        entity.setName(name);
    }

    @Override
    public Member selectById(String id) {
        Member member = super.selectById(id);
        iAreaService.setDataMation(member, Member::getProvinceId);
        iAreaService.setDataMation(member, Member::getCityId);
        iAreaService.setDataMation(member, Member::getAreaId);
        iAreaService.setDataMation(member, Member::getTownshipId);
        return member;
    }

    /**
     * 获取我录入的会员信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryMyWriteMemberList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo pageInfo = inputObject.getParams(CommonPageInfo.class);
        pageInfo.setCreateId(inputObject.getLogParams().get("id").toString());
        Page pages = PageHelper.startPage(pageInfo.getPage(), pageInfo.getLimit());
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryMemberByList(pageInfo);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

}
