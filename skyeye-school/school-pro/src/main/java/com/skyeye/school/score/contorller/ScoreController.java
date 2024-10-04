package com.skyeye.school.score.contorller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.school.score.entity.Score;
import com.skyeye.school.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "成绩管理", tags = "成绩管理", modelName = "成绩管理")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 添加或修改成绩信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeScore", value = "添加或修改成绩信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Score.class)
    @RequestMapping("/post/ScoreController/writeScore")
    public void writeScore(InputObject inputObject, OutputObject outputObject) {
        scoreService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取成绩信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryScoreById", value = "根据id获取成绩信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ScoreController/queryScoreById")
    public void queryScoreById(InputObject inputObject, OutputObject outputObject) {
        scoreService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除成绩信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteScoreById", value = "根据id删除成绩信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ScoreController/deleteScoreById")
    public void deleteScoreById(InputObject inputObject, OutputObject outputObject) {
        scoreService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据no学号获取我的成绩信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryMyScoreListByNo", value = "根据no学号获取我的成绩信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
            @ApiImplicitParam(id = "no", name = "no", value = "学号", required = "required")})
    @RequestMapping("/post/ScoreController/queryMyScoreListByNo")
    public void queryMyScoreListByNo(InputObject inputObject, OutputObject outputObject) {
        scoreService.queryMyScoreListByNo(inputObject, outputObject);
    }


}
