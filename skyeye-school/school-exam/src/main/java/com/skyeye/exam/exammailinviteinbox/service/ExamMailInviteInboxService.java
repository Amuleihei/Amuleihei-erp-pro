package com.skyeye.exam.exammailinviteinbox.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.exammailinviteinbox.entity.ExamMailInviteInbox;

public interface ExamMailInviteInboxService extends SkyeyeBusinessService<ExamMailInviteInbox> {

    void queryExamMailInviteInboxListById(InputObject inputObject, OutputObject outputObject);
}
