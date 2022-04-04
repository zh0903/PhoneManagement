package com.xm.gateway.exception;

/**

 * @Description 枚举通用的异常
 * @date 2021 年 02 月 23 日 15:40
 */
public enum BizCodeEnum {

    /**
     * 验证码过期或者错误
     */

    MI_DUN_INVALIDATOR(401,"非法请求"),
    MI_DUN_ERROR(403,"米盾认证失败"),
    MI_DUN_GET_USER_INFO_ERROR(406,"获取用户签名失败"),
    GATEWAY_ERROR(10001,"网关异常");
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
