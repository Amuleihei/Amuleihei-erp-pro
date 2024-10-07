package com.skyeye.video.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.video.dao.VideoRecordDao;
import com.skyeye.video.entity.VideoRecord;
import com.skyeye.video.service.VideoRecordService;
import org.springframework.stereotype.Service;

@Service
@SkyeyeService(name = "视频点赞收藏管理", groupName = "视频点赞收藏管理")
public class VideoRecordServiceImpl extends SkyeyeBusinessServiceImpl<VideoRecordDao, VideoRecord> implements VideoRecordService {
}
