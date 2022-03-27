package com.xiaomi.manager.validator.annotation;

import com.xiaomi.manager.validator.PatternNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 10 月 28 日 上午11:21
 */
@Documented
@Constraint(validatedBy = {PatternNumberValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISNumberValue {
    String message() default "非法数字";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
