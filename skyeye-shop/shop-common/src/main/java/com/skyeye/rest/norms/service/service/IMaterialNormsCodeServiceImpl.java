/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.rest.norms.service.service;

import com.google.common.base.Joiner;
import com.skyeye.base.rest.service.impl.IServiceImpl;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.rest.norms.rest.IMaterialNormsCodeRest;
import com.skyeye.rest.norms.service.IMaterialNormsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IMaterialNormsCodeServiceImpl
 * @Description: ERP物料规格条形码信息管理公共的一些操作
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/15 10:32
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
public class IMaterialNormsCodeServiceImpl extends IServiceImpl implements IMaterialNormsCodeService {

    @Autowired
    private IMaterialNormsCodeRest iMaterialNormsCodeRest;

    @Override
    public List<Map<String, Object>> queryMaterialNormsCode(String storeId, List<String> normsCodeList, Integer storeUseState) {
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("normsCodeList", Joiner.on(CommonCharConstants.COMMA_MARK).join(normsCodeList));
        params.put("storeUseState", storeUseState);
        return ExecuteFeignClient.get(() -> iMaterialNormsCodeRest.queryMaterialNormsCode(params)).getRows();
    }

    @Override
    public void editStoreMaterialNormsCodeUseState(List<String> ids, Integer storeUseState) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", Joiner.on(CommonCharConstants.COMMA_MARK).join(ids));
        params.put("storeUseState", storeUseState);
        ExecuteFeignClient.get(() -> iMaterialNormsCodeRest.editStoreMaterialNormsCodeUseState(params)).getRows();
    }

}
