/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.language.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.language.dao.LanguageDao;
import com.skyeye.language.entity.Language;
import com.skyeye.language.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: LanguageServiceImpl
 * @Description: 员工语言能力管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:40
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "员工语言能力", groupName = "员工语言能力", teamAuth = true)
public class LanguageServiceImpl extends SkyeyeBusinessServiceImpl<LanguageDao, Language> implements LanguageService {

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryLanguageList(commonPageInfo);
        return beans;
    }

    @Override
    public Language selectById(String id) {
        Language language = super.selectById(id);
        iSysDictDataService.setDataMation(language, Language::getLevelId);
        return language;
    }
}
