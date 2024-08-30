package com.skyeye.xxljob;

import com.skyeye.popularpost.service.PopularPostService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PostXxlJob
 * @Description: 热门帖子记录
 * @author: skyeye云系列--卫志强
 * @date: 2023/10/11 19:20
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class PostXxlJob {

    @Autowired
    private PopularPostService popularPostService;

    @XxlJob("getHotPostHandler")
    public void getHotPost() {
        popularPostService.insertPopularPostList();
    }
}
