package com.skyeye.level.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.adsense.entity.Adsense;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.level.dao.ShopMemberLevelDao;
import com.skyeye.level.entity.ShopMemberLevel;
import com.skyeye.level.service.ShopMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "会员等级", groupName = "会员等级")
public class ShopMemberLevelServiceImpl extends SkyeyeBusinessServiceImpl<ShopMemberLevelDao , ShopMemberLevel > implements ShopMemberLevelService {


    @Autowired
    private ShopMemberLevelDao shopMemberLevelDao;

    /**
     * 分页查询
     * @param commonPageInfo
     * @return
     */
    @Override
    public QueryWrapper<ShopMemberLevel > getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopMemberLevel > queryWrapper = super.getQueryWrapper(commonPageInfo);
        String objectStr =  commonPageInfo.getObjectId();
        if (StrUtil.isNotEmpty(objectStr)) {
            queryWrapper.like(MybatisPlusUtil.toColumns(ShopMemberLevel ::getName), objectStr);
        }
        return queryWrapper;
    }

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
        queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMemberLevel::getEnabled), "2");
        //查询
        List<ShopMemberLevel> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }

    /**
     * 批量删除会员等级模版信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteById(InputObject inputObject, OutputObject outputObject) {
        String ids = inputObject.getParams().get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(CommonCharConstants.COMMA_MARK));
        super.deleteById(idList);
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
        //获取name值
        String nameValue = map.get("name").toString();
        //判断name值是否存在，添加查询条件
        if (StrUtil.isNotEmpty(nameValue)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMemberLevel::getName), nameValue);
        }
        //获取name值
        String enabledValue = map.get("enabled").toString();
        //判断enabled值是否存在，添加查询条件
        if (StrUtil.isNotEmpty(enabledValue)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopMemberLevel::getEnabled), enabledValue);
        }
        //查询
        List<ShopMemberLevel> memberLevels = shopMemberLevelDao.selectList(queryWrapper);
        outputObject.setBeans(memberLevels);
        outputObject.settotal(memberLevels.size());
    }
}
