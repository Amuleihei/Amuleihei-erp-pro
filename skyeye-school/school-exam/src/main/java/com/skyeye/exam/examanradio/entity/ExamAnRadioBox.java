package com.skyeye.exam.examanradio.entity;

import com.baomidou.mybatisplus.annotation.TableField;

public class ExamAnRadioBox {

    /**
     * 单选题
     */
    @TableField(exist = false)
    private ExamAnRadio examAnRadio;

    @TableField("id")
    private String id;







}
