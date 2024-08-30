/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.material.entity.unit.MaterialUnit;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MaterialUnitService
 * @Description: 计量单位信息接口服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/30 11:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MaterialUnitService extends SkyeyeBusinessService<MaterialUnit> {

    /**
     * 批量保存计量单位信息
     *
     * @param unitList 计量单位信息
     * @param userId   用户id
     * @param groupId  所属组id
     */
    void saveBatchList(List<MaterialUnit> unitList, String userId, String groupId);

    /**
     * 查询组下的所有计量单位信息
     *
     * @param groupId 所属组id
     * @return 计量单位信息
     */
    List<MaterialUnit> queryUnitListByGroupId(String groupId);

    /**
     * 根据所属组id删除组下面的计量单位信息
     *
     * @param groupId 所属组id
     */
    void deleteByGroupId(String groupId);

    /**
     * 根据组id集合批量查询计量单位信息
     *
     * @param groupIds 组id集合
     * @return
     */
    Map<String, List<MaterialUnit>> queryUnitListByGroupId(List<String> groupIds);

}
