/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.chtopic.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.chtopic.dao.ChooseTopicDao;
import com.skyeye.chtopic.entity.ChooseTopic;
import com.skyeye.chtopic.service.ChooseTopicService;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.user.service.ChooseUserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ChooseTopicServiceImpl
 * @Description: 课题服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/5/8 10:21
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "课题管理", groupName = "课题管理")
public class ChooseTopicServiceImpl extends SkyeyeBusinessServiceImpl<ChooseTopicDao, ChooseTopic> implements ChooseTopicService {

    @Autowired
    private ChooseUserService chooseUserService;

    @Override
    public void importChooseTopic(InputObject inputObject, OutputObject outputObject) {
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(PutObject.getRequest().getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(PutObject.getRequest())) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) PutObject.getRequest();
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                ImportParams reportModelAttrParams = new ImportParams();
                reportModelAttrParams.setStartSheetIndex(0);
                List<ChooseTopic> chooseTopicList;
                try {
                    chooseTopicList = ExcelImportUtil.importExcel(file.getInputStream(), ChooseTopic.class, reportModelAttrParams);
                } catch (Exception ee) {
                    throw new CustomException(ee);
                }
                chooseTopicList.forEach(bean -> {
                    Map<String, Object> business = BeanUtil.beanToMap(bean);
                    bean.setChoose(1);
                    bean.setOddNumber(iCodeRuleService.getNextCodeByClassName(getServiceClassName(), business));
                });
                createEntity(chooseTopicList, StrUtil.EMPTY);
            }
        }
    }

    @Override
    public void chooseTopicById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        ChooseTopic chooseTopic = selectById(id);
        if (chooseTopic.getChoose() == 2) {
            throw new CustomException("该课题已被选择");
        }
        String userId = inputObject.getLogParams().get("id").toString();
        QueryWrapper<ChooseTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ChooseTopic::getChooseUserId), userId);
        ChooseTopic one = getOne(queryWrapper, false);
        if (ObjectUtil.isNotEmpty(one)) {
            throw new CustomException("您已选题，请勿重复选题");
        }

        UpdateWrapper<ChooseTopic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(ChooseTopic::getChoose), 2);
        updateWrapper.set(MybatisPlusUtil.toColumns(ChooseTopic::getChooseUserId), userId);
        update(updateWrapper);
        refreshCache(id);
    }

    @Override
    public void cnacleChooseTopicById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        ChooseTopic chooseTopic = selectById(id);
        if (chooseTopic.getChoose() == 1) {
            throw new CustomException("该课题未被选择");
        }
        String userId = inputObject.getLogParams().get("id").toString();
        if (!StrUtil.equals(userId, chooseTopic.getChooseUserId())) {
            throw new CustomException("您无法取消他人选择的课题");
        }
        UpdateWrapper<ChooseTopic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(CommonConstants.ID, id);
        updateWrapper.set(MybatisPlusUtil.toColumns(ChooseTopic::getChoose), 1);
        updateWrapper.set(MybatisPlusUtil.toColumns(ChooseTopic::getChooseUserId), StrUtil.EMPTY);
        update(updateWrapper);
        refreshCache(id);
    }

    @Override
    public void exportChooseTopic(InputObject inputObject, OutputObject outputObject) {
        exportExcel(InputObject.getResponse());
    }

    private void exportExcel(HttpServletResponse response) {
        // 导出数据
        List<ChooseTopic> list = list();
        chooseUserService.setDataMation(list, ChooseTopic::getChooseUserId);
        list.forEach(bean -> {
            if (ObjectUtil.isNotEmpty(bean.getChooseUserMation())) {
                bean.setChooseUserName(bean.getChooseUserMation().getName());
            }
        });
        //.xls格式
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //前端存在跨域不成功，设置可访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        //设置不要缓存
        response.setHeader("Pragma", "No-cache");
        try {
            response.setHeader("Content-disposition", "attachment;filename=chooseTopicResult.xls");
            //设置sheet名
            ExportParams params = new ExportParams();
            params.setSheetName("学生选题结果表");
            // 这里需要设置不关闭流
            Workbook workbook = ExcelExportUtil.exportExcel(params, ChooseTopic.class, list);
            //输出流
            OutputStream outStream = response.getOutputStream();
            //浏览器下载
            workbook.write(outStream);
            //关闭流
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
