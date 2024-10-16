package com.skyeye.video.service.impl;

import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.video.dao.VideoRecordDao;
import com.skyeye.video.entity.VideoRecord;
import com.skyeye.video.service.VideoRecordService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: VideoRecordServiceImpl
 * @Description: 视频记录管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/3/9 14:31
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "视频点赞收藏管理", groupName = "视频点赞收藏管理")
public class VideoRecordServiceImpl extends SkyeyeBusinessServiceImpl<VideoRecordDao, VideoRecord> implements VideoRecordService {
}
