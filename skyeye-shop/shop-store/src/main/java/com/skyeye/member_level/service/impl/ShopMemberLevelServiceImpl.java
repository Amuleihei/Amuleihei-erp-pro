package com.skyeye.member_level.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.member_level.dao.ShopMemberLevelDao;
import com.skyeye.member_level.entity.ShopMemberLevel;
import com.skyeye.member_level.service.ShopMemberLevelService;
import com.skyeye.store.entity.ShopStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "会员等级", groupName = "会员等级")
public class ShopMemberLevelServiceImpl extends SkyeyeBusinessServiceImpl<ShopMemberLevelDao , ShopMemberLevel > implements ShopMemberLevelService {


    @Autowired
    private ShopMemberLevelDao shopMemberLevelDao;

    /**
     * 获取会员等级前端下拉列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void streamlineMemberLevelList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<ShopMemberLevel> queryWrapper = new QueryWrapper<>();
        // 添加查询条件 enabled = "2"
        queryWrapper.eq("enabled", "2");
        List<ShopMemberLevel> list = shopMemberLevelDao.selectList(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }
    /**
     * 获取会员等级列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void memberLevelList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        QueryWrapper<ShopMemberLevel > queryWrapper = new QueryWrapper<>();
        String nameValue = map.get("name").toString();
        if (StrUtil.isNotEmpty(nameValue)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMemberLevel::getName), nameValue);
        }
        String enabledValue = map.get("enabled").toString();
        if (StrUtil.isNotEmpty(enabledValue)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMemberLevel::getEnabled), enabledValue);
        }
        List<ShopMemberLevel> memberLevels = shopMemberLevelDao.selectList(queryWrapper);
        outputObject.setBeans(memberLevels);
        outputObject.settotal(memberLevels.size());
    }
}
