/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.word.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.word.entity.WordModelAttr;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReportWordModelAttrService
 * @Description: 文字模型属性管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/26 15:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
public interface ReportWordModelAttrService extends SkyeyeBusinessService<WordModelAttr> {

    void deleteByWordId(String wordId);

    void save(String wordId, List<WordModelAttr> wordModelAttrList);

    List<WordModelAttr> queryByWordId(String wordId);

    Map<String, List<WordModelAttr>> queryByWordId(List<String> wordIds);

}
