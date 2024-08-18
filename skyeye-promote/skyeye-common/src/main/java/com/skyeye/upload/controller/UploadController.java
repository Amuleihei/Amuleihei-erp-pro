/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.upload.entity.Upload;
import com.skyeye.upload.entity.UploadChunks;
import com.skyeye.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UploadController
 * @Description: 文件上传、下载控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "文件上传、下载", tags = "文件上传、下载", modelName = "基础模块")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 断点续传上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "skyeyeUploadFile", value = "断点续传上传文件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Upload.class)
    @RequestMapping("/post/UploadController/uploadFileResume")
    public void uploadFileResume(InputObject inputObject, OutputObject outputObject) {
        uploadService.uploadFileResume(inputObject, outputObject);
    }

    /**
     * 上传文件合并块
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "skyeyeUploadFileChunks", value = "上传文件合并块", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = UploadChunks.class)
    @RequestMapping("/post/UploadController/uploadFileChunks")
    public void uploadFileChunks(InputObject inputObject, OutputObject outputObject) {
        uploadService.uploadFileChunks(inputObject, outputObject);
    }

    /**
     * 文件分块上传检测是否上传
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "checkUploadFileChunks", value = "文件分块上传检测是否上传", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "md5", name = "md5", value = "文件唯一标示", required = "required"),
        @ApiImplicitParam(id = "chunk", name = "chunk", value = "分块上传的块下标", required = "required"),
        @ApiImplicitParam(id = "chunkSize", name = "chunkSize", value = "分块上传时，块的大小，用于最后合并", required = "required")})
    @RequestMapping("/post/UploadController/checkUploadFileChunks")
    public void checkUploadFileChunks(InputObject inputObject, OutputObject outputObject) {
        uploadService.checkUploadFileChunks(inputObject, outputObject);
    }

    /**
     * 上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "common003", value = "上传文件", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "type", name = "type", value = "文件类型", required = "required,num")})
    @RequestMapping("/post/UploadController/uploadFile")
    public void uploadFile(InputObject inputObject, OutputObject outputObject) {
        uploadService.uploadFile(inputObject, outputObject);
    }

    /**
     * 上传文件Base64
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "common004", value = "上传文件Base64", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "type", name = "type", value = "文件类型", required = "required,num"),
        @ApiImplicitParam(id = "images", name = "images", value = "图片Base64", required = "required")})
    @RequestMapping("/post/UploadController/uploadFileBase64")
    public void uploadFileBase64(InputObject inputObject, OutputObject outputObject) {
        uploadService.uploadFileBase64(inputObject, outputObject);
    }

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @param configId
     */
    @GetMapping("/upload/{configId}/get/**")
    public void getFileContent(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("configId") String configId) {
        uploadService.getFileContent(request, response, configId);
    }

    /**
     * 删除文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteFileByPath", value = "删除文件", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "path", name = "path", value = "文件路径", required = "required")})
    @RequestMapping("/post/UploadController/deleteFileByPath")
    public void deleteFileByPath(InputObject inputObject, OutputObject outputObject) {
        uploadService.deleteFileByPath(inputObject, outputObject);
    }

}
