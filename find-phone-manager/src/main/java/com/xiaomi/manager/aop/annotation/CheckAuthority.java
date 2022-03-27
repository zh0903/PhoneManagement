package com.xiaomi.manager.aop.annotation;

import com.xiaomi.common.entity.UserEntity;

import java.lang.annotation.*;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 11 月 09 日 下午4:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAuthority {

}
