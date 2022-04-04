package com.xm.gateway.exception;

import com.xm.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**

 * @Description 统一异常处理器
 * @date 2021 年 05 月 10 日 8:51
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(InvalidateTokenException.class)
    public R InvalidateTokenExceptionHandler(InvalidateTokenException e) {
        return R.error(e.getCode(), e.getMessage());
    }
}
