package com.example.demo.util;

/**
 * @author wanghan
 */
public class ResponesData {
    private String code;
    private String msg;
    private Object data;

    public ResponesData(ResponesCode responesCode, Object data) {
        this.code = responesCode.getCode();
        this.msg = responesCode.getMsg();
        this.data = data;
    }

    public ResponesData(ResponesCode responesCode) {
        this.code = responesCode.getCode();
        this.msg = responesCode.getMsg();
    }

    public ResponesData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}