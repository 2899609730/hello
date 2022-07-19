package com.fox.config;

import com.alibaba.fastjson.JSONObject;

public enum StatusCode {
    SUCCESS(0, "success"),
    FAIL(1, "fail");

    //定义属性
    private int code;
    private String message;

    //定义构造
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //重写toString,将枚举对象转化为JSON
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",code);
        jsonObject.put("msg",message);
        return jsonObject.toString();
    }
}
