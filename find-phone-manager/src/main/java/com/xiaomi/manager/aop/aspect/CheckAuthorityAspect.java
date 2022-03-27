package com.xiaomi.manager.aop.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaomi.common.constant.GateWayAndManagerConstant;
import com.xiaomi.common.entity.UserEntity;
import com.xiaomi.common.utils.R;
import com.xiaomi.manager.aop.annotation.CheckAuthority;
import com.xiaomi.manager.exception.BizCodeEnum;
import com.xiaomi.manager.myenum.UserIdentityEnum;
import com.xiaomi.manager.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author LYJ
 * @Description 权限校验的切面类
 * @date 2021 年 11 月 09 日 下午4:35
 */
@Aspect
@Component
public class CheckAuthorityAspect {

    private Logger logger = LoggerFactory.getLogger(CheckAuthorityAspect.class);

    @Autowired
    private UserService userService;


    @Pointcut("@annotation(com.xiaomi.manager.aop.annotation.CheckAuthority)")
    public void checkPointCut() {

    }


    @Around(value = "checkPointCut()")
    public Object checkBeforeExecutorMethod(ProceedingJoinPoint pjp) {
        try{
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                    .getRequestAttributes()))
                    .getRequest();
            String userEmail = request.getHeader(GateWayAndManagerConstant.USER_EMAIL);
            if (ObjectUtils.isEmpty(userEmail)) {
                return R.error(BizCodeEnum.ILLEGAL_REQUEST.getCode(), BizCodeEnum.ILLEGAL_REQUEST.getMessage());
            } else {
                UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("email", userEmail));
                if (userEntity.getIsRoot() == null ||  !UserIdentityEnum.ROOT_IDENTITY.getCode().equals(userEntity.getIsRoot())) {
                    return R.error(BizCodeEnum.ILLEGAL_REQUEST.getCode(), BizCodeEnum.ILLEGAL_REQUEST.getMessage());
                }
            }
            return pjp.proceed();
        } catch (Throwable throwable) {
            logger.error("execute aim method has exception {}",throwable.getMessage());
           return R.error(BizCodeEnum.ILLEGAL_REQUEST.getCode(), BizCodeEnum.ILLEGAL_REQUEST.getMessage());
        }
    }
}
