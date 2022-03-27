package com.xiaomi.manager.exception;

import com.xiaomi.common.exception.RRException;

import java.io.Serializable;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 10 月 28 日 上午10:47
 */

public class NotFindPhoneException extends RRException implements Serializable {
    private static final long serialVersionUID = 1L;


    public NotFindPhoneException(String msg, int code){
        super(msg,code);
    }
}
