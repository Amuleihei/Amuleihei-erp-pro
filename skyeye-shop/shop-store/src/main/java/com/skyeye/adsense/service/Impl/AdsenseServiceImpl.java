package com.skyeye.adsense.service.Impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.adsense.dao.AdsenseDao;
import com.skyeye.adsense.entity.Adsense;
import com.skyeye.adsense.service.AdsenseService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.level.entity.ShopMemberLevel;
import com.skyeye.type.entity.StoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@SkyeyeService(name = "广告位管理", groupName = "广告位管理")
public class AdsenseServiceImpl  extends SkyeyeBusinessServiceImpl<AdsenseDao, Adsense>  implements AdsenseService {

    @Autowired
    private AdsenseService adsenseService;

    /**
     * 根据条件获取广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     */
    //重写queryDataList查询方法
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<Adsense> queryWrapper = new QueryWrapper<>();
        //如果enabled不为空，则添加该模糊查询条件
        if (params.containsKey("name") && StrUtil.isNotEmpty(params.get("name").toString())) {
            queryWrapper.like(MybatisPlusUtil.toColumns(Adsense::getName), params.get("name").toString());
        }
        if (params.containsKey("enabled") && StrUtil.isNotEmpty(params.get("enabled").toString())) {
            //如果enabled不为空，则添加该精确查询条件
            queryWrapper.eq(MybatisPlusUtil.toColumns(Adsense::getEnabled), params.get("enabled"));
        }
        List<Adsense> beans = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(beans), null);
    }


    /**
     * 获取在用的广告位管理的信息广告位管理信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void streamlineAdsenseList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<Adsense> queryWrapper = new QueryWrapper<>();
        //查询在用的广告位管理的信息
        queryWrapper.eq(MybatisPlusUtil.toColumns(Adsense::getEnabled), "2");
        List<Adsense> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }


    /**
     * 批量删除广告位管理信息
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
}
