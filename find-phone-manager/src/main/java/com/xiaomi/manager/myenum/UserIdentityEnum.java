package com.xiaomi.manager.myenum;

import lombok.Data;

/**
 * @author LYJ
 * @Description 用户身份映射
 * @date 2021 年 11 月 09 日 下午5:21
 */
public enum UserIdentityEnum {
    ROOT_IDENTITY(1,"超级用户"),
    NORMAL_IDENTITY(0,"普通用户");

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String  msg;

    UserIdentityEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

