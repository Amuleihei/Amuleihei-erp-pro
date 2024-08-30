/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.material.dao.MaterialNormsCodeHisDao;
import com.skyeye.material.entity.MaterialNormsCodeHis;
import com.skyeye.material.service.MaterialNormsCodeHisService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MaterialNormsCodeHisServiceImpl
 * @Description: 商品规格一物一码条形码变更历史服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/6/11 17:14
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "商品条形码变更历史", groupName = "商品管理")
public class MaterialNormsCodeHisServiceImpl extends SkyeyeBusinessServiceImpl<MaterialNormsCodeHisDao, MaterialNormsCodeHis> implements MaterialNormsCodeHisService {


}
