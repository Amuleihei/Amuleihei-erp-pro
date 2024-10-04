package com.skyeye.school.groups.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.school.groups.entity.GroupsInformation;

public interface GroupsInformationService  extends SkyeyeBusinessService<GroupsInformation> {
    void editGroupsInformationStuNum(String id, Boolean isAdd);
}
