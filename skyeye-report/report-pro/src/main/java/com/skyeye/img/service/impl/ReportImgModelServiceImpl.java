/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.img.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.img.dao.ReportImgModelDao;
import com.skyeye.img.entity.ImgModel;
import com.skyeye.img.service.ReportImgModelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ReportImgModelServiceImpl
 * @Description: 图片模型服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/9/5 16:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "图片模型管理", groupName = "图片模型管理")
public class ReportImgModelServiceImpl extends SkyeyeBusinessServiceImpl<ReportImgModelDao, ImgModel> implements ReportImgModelService {

    @Override
    public void queryAllEnabledImgModelList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<ImgModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ImgModel::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<ImgModel> imgModels = list(queryWrapper);
        iSysDictDataService.setName(imgModels, "typeId", "typeName");
        outputObject.setBeans(imgModels);
        outputObject.settotal(imgModels.size());
    }

}
