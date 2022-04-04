package com.xm.manager.aop.annotation;

import com.xm.common.entity.UserEntity;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAuthority {

}
