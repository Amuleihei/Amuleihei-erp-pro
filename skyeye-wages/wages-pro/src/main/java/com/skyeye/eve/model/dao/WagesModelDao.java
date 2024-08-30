/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.model.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.model.entity.WagesModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WagesModelDao
 * @Description: 薪资模板数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/1/21 11:19
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface WagesModelDao extends SkyeyeBaseMapper<WagesModel> {

    List<Map<String, Object>> queryWagesModelList(CommonPageInfo pageInfo);

    List<Map<String, Object>> queryWagesModelListByApplicableObjectIds(@Param("list") List<String> wagesApplicableObjectIds);
}
