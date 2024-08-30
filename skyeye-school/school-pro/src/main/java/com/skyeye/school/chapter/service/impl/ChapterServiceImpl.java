/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.school.chapter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.school.chapter.dao.ChapterDao;
import com.skyeye.school.chapter.entity.Chapter;
import com.skyeye.school.chapter.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: ChapterServiceImpl
 * @Description: 章节管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/25 11:08
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "章节管理", groupName = "章节管理")
public class ChapterServiceImpl extends SkyeyeBusinessServiceImpl<ChapterDao, Chapter> implements ChapterService {

    /**
     * 根据科目表与班级表的关系id获取章节列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryChapterListBySubjectClassesId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String subjectClassesId = map.get("subjectClassesId").toString();
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Chapter::getSubjectClassesId), subjectClassesId);
        List<Chapter> chapterList = list(queryWrapper);
        chapterList.forEach(chapter -> {
            chapter.setName(String.format(Locale.ROOT, "第 %s 章 %s", chapter.getSection(), chapter.getName()));
        });
        iAuthUserService.setDataMation(chapterList, Chapter::getCreateId);
        iAuthUserService.setDataMation(chapterList, Chapter::getLastUpdateId);
        outputObject.setBeans(chapterList);
        outputObject.settotal(chapterList.size());
    }

}
