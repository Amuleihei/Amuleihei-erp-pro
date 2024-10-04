package com.skyeye.delivery.service.impl;

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
import com.skyeye.delivery.dao.ShopDeliveryTemplateChargeDao;
import com.skyeye.delivery.entity.ShopDeliveryTemplate;
import com.skyeye.delivery.entity.ShopDeliveryTemplateCharge;
import com.skyeye.delivery.service.ShopDeliveryTemplateChargeService;
import com.skyeye.delivery.service.ShopDeliveryTemplateService;
import com.skyeye.delivery.vo.ShopDeliveryTemplateChargeVo;
import com.skyeye.exception.CustomException;
import com.skyeye.store.entity.ShopArea;
import com.skyeye.store.service.ShopAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@SkyeyeService(name = "快递运费费用模版", groupName = "快递运费费用模版")
public class ShopDeliveryTemplateChargeServiceImpl extends SkyeyeBusinessServiceImpl<ShopDeliveryTemplateChargeDao, ShopDeliveryTemplateCharge> implements ShopDeliveryTemplateChargeService {

    @Autowired
    private ShopDeliveryTemplateService shopDeliveryTemplateService;

    @Autowired
    private ShopAreaService shopAreaService;

    /**
     * 分页查询-根据快递运费模版模板ID
     * @param commonPageInfo
     * @return
     */
    @Override
    public QueryWrapper<ShopDeliveryTemplateCharge> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<ShopDeliveryTemplateCharge> queryWrapper = super.getQueryWrapper(commonPageInfo);
        String objectStr =  commonPageInfo.getObjectId();
        if (StrUtil.isNotEmpty(objectStr)) {
            queryWrapper.like(MybatisPlusUtil.toColumns(ShopDeliveryTemplateCharge::getTemplateId), objectStr);
        }
        return queryWrapper;
    }

    /**
     * 批量删除快递运费费用模版信息
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
     * 查询快递运费模版信息,条件查询templateId和areaId
     *
     * @param inputObject 入参以及用户信息等获取对象
     */
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        QueryWrapper<ShopDeliveryTemplateCharge> queryWrapper = new QueryWrapper<>();

        if (params.containsKey("templateId") && StrUtil.isNotEmpty(params.get("templateId").toString())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopDeliveryTemplateCharge::getTemplateId), params.get("templateId").toString());
        }

        if (params.containsKey("areaId") && StrUtil.isNotEmpty(params.get("areaId").toString())) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(ShopDeliveryTemplateCharge::getAreaId), params.get("areaId").toString());
        }

        List<ShopDeliveryTemplateCharge> beans = list(queryWrapper);
        return JSONUtil.toList(JSONUtil.toJsonStr(beans), null);
    }

    /**
     * 重写新增编辑前置条件快递运费模版信息
     */
    @Override
    public void validatorEntity(ShopDeliveryTemplateCharge shopDeliveryTemplateCharge) {
        super.validatorEntity(shopDeliveryTemplateCharge);
        ShopDeliveryTemplate shopDeliveryTemplate = shopDeliveryTemplateService.selectById(shopDeliveryTemplateCharge.getTemplateId());
        ShopArea shopArea = shopAreaService.selectById(shopDeliveryTemplateCharge.getAreaId());

        // 判断shopDeliveryTemplate是否为空，如果为空则抛出异常
        if (shopDeliveryTemplate.getId() == null) {
            throw new CustomException("模板不存在: " + shopDeliveryTemplateCharge.getTemplateId());
        }

        // 判断shopArea是否为空，如果为空则抛出异常
        if (shopArea.getId() == null) {
            throw new CustomException("区域不存在: " + shopDeliveryTemplateCharge.getAreaId());
        }
    }

    /**
     * 重查询快递运费模版信息，只返回部分字段
     */
    @Override
    public void shopDeliveryTemplateChargeList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<ShopDeliveryTemplateCharge> queryWrapper = new QueryWrapper<>();
        List<ShopDeliveryTemplateCharge> list = list(queryWrapper);

        // 创建一个VO列表来存储转换后的结果
        List<ShopDeliveryTemplateChargeVo> voList = new ArrayList<>();

        // 遍历原始列表并转换每个对象
        for (ShopDeliveryTemplateCharge template : list) {
            ShopDeliveryTemplateChargeVo vo = new ShopDeliveryTemplateChargeVo();
            //进行属性复制
            vo.setTemplateId(template.getTemplateId());
            vo.setAreaId(template.getAreaId());
            voList.add(vo);
        }
        outputObject.setBeans(voList);
        outputObject.settotal(voList.size());
    }
}
