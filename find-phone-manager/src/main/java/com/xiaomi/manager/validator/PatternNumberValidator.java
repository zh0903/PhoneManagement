package com.xiaomi.manager.validator;

import com.xiaomi.manager.validator.annotation.ISNumberValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 10 月 28 日 上午11:19
 */
public class PatternNumberValidator implements ConstraintValidator<ISNumberValue, Long> {

    private static final String REGX = "[1-9]\\d*";

    @Override
    public void initialize(ISNumberValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {

        return aLong.toString().matches(REGX);
    }
}
