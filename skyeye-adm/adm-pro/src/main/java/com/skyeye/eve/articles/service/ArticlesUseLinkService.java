/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.articles.service;

import com.skyeye.base.business.service.SkyeyeLinkDataService;
import com.skyeye.eve.articles.entity.ArticlesUseLink;

/**
 * @ClassName: ArticlesUseLinkService
 * @Description: 用品领用申请关联的用品信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/19 19:52
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ArticlesUseLinkService extends SkyeyeLinkDataService<ArticlesUseLink> {

    void editActualUseNumById(String id, String state, Integer actualUseNum);

}
