package com.xiaomi.gateway.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LYJ
 * @Description token校验异常
 * @date 2021 年 05 月 10 日 8:53
 */

@Data
public class InvalidateTokenException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code;

    public InvalidateTokenException(String msg){
        super(msg);
        this.msg = msg;
    }

    public InvalidateTokenException(String msg, int code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
