package com.xm.manager.exception;

import com.xm.common.exception.RRException;
import lombok.Data;

import java.io.Serializable;

/**

 * @Description
 * @date 2021 年 10 月 28 日 上午10:44
 */
public class NotFindUserException extends RRException implements Serializable {
    private static final long serialVersionUID = 1L;


    public NotFindUserException(String msg, int code){
        super(msg,code);
    }
}
