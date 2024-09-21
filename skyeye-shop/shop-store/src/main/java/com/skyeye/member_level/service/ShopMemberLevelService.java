package com.skyeye.member_level.service;


import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.member_level.entity.ShopMemberLevel;

public interface ShopMemberLevelService extends SkyeyeBusinessService<ShopMemberLevel> {

    void streamlineMemberLevelList(InputObject inputObject, OutputObject outputObject);


    void memberLevelList(InputObject inputObject, OutputObject outputObject);
}
