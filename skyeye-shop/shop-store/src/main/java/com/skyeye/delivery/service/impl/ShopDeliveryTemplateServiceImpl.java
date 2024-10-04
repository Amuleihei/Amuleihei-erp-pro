package com.skyeye.delivery.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.delivery.dao.ShopDeliveryTemplateDao;
import com.skyeye.delivery.entity.ShopDeliveryTemplate;
import com.skyeye.delivery.service.ShopDeliveryTemplateService;
import com.skyeye.delivery.vo.ShopDeliveryTemplateVo;
import com.skyeye.exception.CustomException;
import com.skyeye.store.entity.ShopStore;
import com.skyeye.store.service.ShopStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@SkyeyeService(name = "快递运费模版", groupName = "快递运费模版")
public class ShopDeliveryTemplateServiceImpl extends SkyeyeBusinessServiceImpl<ShopDeliveryTemplateDao, ShopDeliveryTemplate> implements ShopDeliveryTemplateService {

    @Autowired
    private ShopStoreService shopStoreService;

    /**
     * 分页查询-快递运费模版
     * @param commonPageInfo
     * @return
     */
    @Override
    public QueryWrapper<ShopDeliveryTemplate> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopDeliveryTemplate> queryWrapper = super.getQueryWrapper(commonPageInfo);
        String objectStr =  commonPageInfo.getObjectId();
        if (StrUtil.isNotEmpty(objectStr)) {
            queryWrapper.like(MybatisPlusUtil.toColumns(ShopDeliveryTemplate::getName), objectStr);
        }
        return queryWrapper;
    }

    /**
     * 批量删除快递运费模版信息
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
     * 查询快递运费模版信息
     *
     * @param inputObject 入参以及用户信息等获取对象
     */
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<ShopDeliveryTemplate> queryWrapper = new QueryWrapper<>();
        //如果enabled不为空，则添加该模糊查询条件
        if (params.containsKey("name") && StrUtil.isNotEmpty(params.get("name").toString())) {
            queryWrapper.like(MybatisPlusUtil.toColumns(ShopDeliveryTemplate::getName), params.get("name").toString());
        }
        if (params.containsKey("storeId") && StrUtil.isNotEmpty(params.get("storeId").toString())) {
            //如果enabled不为空，则添加该精确查询条件
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopDeliveryTemplate::getStoreId), params.get("storeId"));
        }
        List<ShopDeliveryTemplate> beans = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(beans), null);
    }

    /**
     * 重写新增编辑前置条件快递运费模版信息
     */
    @Override
    public void validatorEntity(ShopDeliveryTemplate shopDeliveryTemplate) {
        super.validatorEntity(shopDeliveryTemplate);
        //判断StoreId是否存在
        if (ObjectUtil.isNotNull(shopDeliveryTemplate.getStoreId())) {
            ShopStore shopStore = shopStoreService.selectById(shopDeliveryTemplate.getStoreId());
            //判断shopStore是否为空，如果为空，则抛出异常
            if (shopStore.getId() == null) {
                throw new CustomException("门店不存在: " + shopDeliveryTemplate.getStoreId());
            }
        }
    }

    /**
     * 获取精简的快递运费模版信息，主要用于下拉列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void shopDeliveryTemplateList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<ShopDeliveryTemplate> queryWrapper = new QueryWrapper<>();
        List<ShopDeliveryTemplate> list = list(queryWrapper);

        // 创建一个VO列表来存储转换后的结果
        List<ShopDeliveryTemplateVo> voList = new ArrayList<>();

        // 遍历原始列表并转换每个对象
        for (ShopDeliveryTemplate template : list) {
            ShopDeliveryTemplateVo vo = new ShopDeliveryTemplateVo();
            //进行属性复制
            vo.setId(template.getId());
            vo.setName(template.getName());
            voList.add(vo);
        }
        outputObject.setBeans(voList);
        outputObject.settotal(voList.size());
    }
}
