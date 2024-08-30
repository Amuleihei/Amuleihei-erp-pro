/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.licence.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.licence.entity.Licence;

/**
 * @ClassName: LicenceService
 * @Description: 证照管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/24 23:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface LicenceService extends SkyeyeBusinessService<Licence> {

    void queryAllLicenceList(InputObject inputObject, OutputObject outputObject);

    void queryMyRevertLicenceList(InputObject inputObject, OutputObject outputObject);

    /**
     * 设置证照领用信息
     *
     * @param id        证照id
     * @param useUserId 领用人id
     */
    void setLicenceUse(String id, String useUserId);

    /**
     * 设置证照归还信息
     *
     * @param id 证照id
     */
    void setLicenceRevert(String id);

    void queryMyRevertLicencePageList(InputObject inputObject, OutputObject outputObject);
}
