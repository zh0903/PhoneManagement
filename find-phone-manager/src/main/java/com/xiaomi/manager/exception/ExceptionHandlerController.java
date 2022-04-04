package com.xm.manager.exception;

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

//    @ExceptionHandler(InvalidateTokenException.class)
//    public R InvalidateTokenExceptionHandler(InvalidateTokenException e) {
//        return R.error(e.getCode(), e.getMessage());
//    }


    @ExceptionHandler(NotFindPhoneException.class)
    public R InvalidateTokenExceptionHandler(NotFindPhoneException e) {
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotFindUserException.class)
    public R InvalidateTokenExceptionHandler(NotFindUserException e) {
        return R.error(e.getCode(), e.getMessage());
    }

//    @ExceptionHandler(SeatsNotEnoughException.class)
//    public R seatsNotEnoughExceptionHandler(SeatsNotEnoughException e) {
//        return R.error(BizCodeEnum.SEATS_NOT_ENOUGH.getCode(), BizCodeEnum.SEATS_NOT_ENOUGH.getMessage());
//    }

}
