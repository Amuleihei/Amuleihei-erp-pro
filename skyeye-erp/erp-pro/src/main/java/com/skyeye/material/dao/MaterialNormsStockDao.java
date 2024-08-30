/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.material.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.material.entity.MaterialNormsStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MaterialNormsStockDao
 * @Description: ERP商品规格初始化库存数据接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/21 17:46
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface MaterialNormsStockDao extends SkyeyeBaseMapper<MaterialNormsStock> {

    /**
     * 查询指定仓库某个规格商品的库存
     *
     * @param normsIds 规格id集合
     * @param depotId  仓库id
     * @return {currentTock: 库存数量, normsId: 规格id}
     */
    List<Map<String, Object>> queryMaterialStockByNormsId(@Param("normsIds") List<String> normsIds, @Param("depotId") String depotId);

    /**
     * 根据规格id获取初始化库存信息
     *
     * @param normsIds 规格id集合
     * @param type     类型  1.初始化库存  2.单据操作的库存
     * @return
     */
    List<MaterialNormsStock> queryNormsStockByNormsId(@Param("normsIds") List<String> normsIds, @Param("depotId") String depotId, @Param("type") Integer type);

}
