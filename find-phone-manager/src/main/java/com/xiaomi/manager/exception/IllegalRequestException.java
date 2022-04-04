package com.xm.manager.exception;

import com.xm.common.exception.RRException;

import java.io.Serializable;

/**

 * @Description
 * @date 2021 年 11 月 09 日 下午5:10
 */
public class IllegalRequestException extends RRException implements Serializable {
    private static final long serialVersionUID = 1L;


    public IllegalRequestException(String msg, int code) {
        super(msg, code);
    }
}
