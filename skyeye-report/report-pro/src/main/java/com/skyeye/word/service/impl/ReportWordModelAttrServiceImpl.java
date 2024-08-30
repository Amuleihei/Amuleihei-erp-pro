/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.word.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.word.dao.ReportWordModelAttrDao;
import com.skyeye.word.entity.WordModelAttr;
import com.skyeye.word.service.ReportWordModelAttrService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ReportWordModelAttrServiceImpl
 * @Description: 文字模型属性管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/2/26 15:45
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
@SkyeyeService(name = "文字模型属性管理", groupName = "文字模型管理", manageShow = false)
public class ReportWordModelAttrServiceImpl extends SkyeyeBusinessServiceImpl<ReportWordModelAttrDao, WordModelAttr> implements ReportWordModelAttrService {

    @Override
    public void deleteByWordId(String wordId) {
        QueryWrapper<WordModelAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(WordModelAttr::getWordId), wordId);
        remove(queryWrapper);
    }

    @Override
    public void save(String wordId, List<WordModelAttr> wordModelAttrList) {
        deleteByWordId(wordId);
        if (CollectionUtil.isNotEmpty(wordModelAttrList)) {
            wordModelAttrList.forEach(wordModelAttr -> {
                wordModelAttr.setWordId(wordId);
            });
            createEntity(wordModelAttrList, StrUtil.EMPTY);
        }
    }

    @Override
    public List<WordModelAttr> queryByWordId(String wordId) {
        QueryWrapper<WordModelAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(WordModelAttr::getWordId), wordId);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(WordModelAttr::getOrderBy));
        List<WordModelAttr> wordModelAttrList = list(queryWrapper);
        return wordModelAttrList;
    }

    @Override
    public Map<String, List<WordModelAttr>> queryByWordId(List<String> wordIds) {
        QueryWrapper<WordModelAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(WordModelAttr::getWordId), wordIds);
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(WordModelAttr::getOrderBy));
        List<WordModelAttr> wordModelAttrList = list(queryWrapper);
        return wordModelAttrList.stream().collect(Collectors.groupingBy(WordModelAttr::getWordId));
    }
}
