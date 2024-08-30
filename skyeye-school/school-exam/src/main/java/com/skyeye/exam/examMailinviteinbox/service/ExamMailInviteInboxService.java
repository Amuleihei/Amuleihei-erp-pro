package com.skyeye.exam.examMailinviteinbox.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.exam.examMailinviteinbox.entity.ExamMailInviteInbox;

public interface ExamMailInviteInboxService extends SkyeyeBusinessService<ExamMailInviteInbox> {

    void queryExamMailInviteInboxListById(InputObject inputObject, OutputObject outputObject);
}
