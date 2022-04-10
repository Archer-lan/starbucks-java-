package com.example.demo.util;

/**
 * @author wanghan
 */

public enum ResponesCode {
    /**
     * 详情描述
     */
    SUCCESS("0001","请求成功"),
    SUCCESS01("0002","注册成功"),
    SUCCESS02("0003","登录成功"),
    FAIL("9999","网络错误"),
    ERROR01("9998","用户名为空"),
    ERROR02("9997","手机号为空"),
    ERROR03("9996","密码为空"),
    ERROR04("9995","用户名已存在"),
    ERROR05("9994","账号密码错误"),
    ERROR06("9993","姓名电话未填写");

    private String code;
    private String msg;

    ResponesCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ResponesCode() {
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
}