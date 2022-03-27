package com.xiaomi.manager.exception;

/**
 * @author LYJ
 * @Description 枚举通用的异常
 * @date 2021 年 02 月 23 日 15:40
 */
public enum BizCodeEnum {

    /**
     * 验证码过期或者错误
     */

    CODE_NOT_MATCH(10001,"验证码过期或者错误"),
    TOKEN_INVALIDATE(403,"token过期或者异常"),
    ILLEGAL_REQUEST(402,"非法请求或无权限"),
    NOT_FIND_USER(10002,"没有找到用户"),
    NOT_FIND_PHONE(10003,"没有手机");
    private int code;
    private String message;

    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
