package com.skyeye.video.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.video.entity.Video;

public interface VideoService extends SkyeyeBusinessService<Video> {
    void queryMyVideoList(InputObject inputObject, OutputObject outputObject);

    void supportOrNotVideo(InputObject inputObject, OutputObject outputObject);

    void collectOrNotVideo(InputObject inputObject, OutputObject outputObject);

    void queryMySupportVideo(InputObject inputObject, OutputObject outputObject);

    void queryMyCollectVideo(InputObject inputObject, OutputObject outputObject);

    void refreshVisitVideo(InputObject inputObject, OutputObject outputObject);
}
