package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * common return
 *
 * @param <T>
 * @author xuxueli 2015-12-4 16:32:31
 */
public class ReturnT<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

    public static final ReturnT<String> SUCCESS = new ReturnT<>(null);
    public static final ReturnT<String> FAIL = new ReturnT<>(FAIL_CODE, null);

    private String returnCode = "0";

    private int code;
    private String msg;
    private T content;

    private T bean;

    public ReturnT() {
    }

    public ReturnT(int code, String msg) {
        this.returnCode = "0";
        this.code = code;
        this.msg = msg;
    }

    public ReturnT(T content) {
        this.returnCode = "0";
        this.code = SUCCESS_CODE;
        this.content = content;
    }

    public ReturnT(String returnCode, T content) {
        this.returnCode = returnCode;
        this.content = content;
        this.bean = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
