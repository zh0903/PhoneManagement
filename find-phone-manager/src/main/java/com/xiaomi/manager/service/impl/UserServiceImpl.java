package com.xm.manager.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xm.common.constant.GateWayAndManagerConstant;
import com.xm.common.entity.UserEntity;
import com.xm.common.utils.R;
import com.xm.manager.entity.PhoneEntity;
import com.xm.manager.exception.BizCodeEnum;
import com.xm.manager.myenum.UserIdentityEnum;
import com.xm.manager.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xm.common.utils.PageUtils;
import com.xm.common.utils.Query;

import com.xm.manager.dao.UserDao;
import com.xm.manager.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private PhoneService phoneService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R whoAmI() {
        try{
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                    .getRequestAttributes()))
                    .getRequest();
            String userEmail = request.getHeader(GateWayAndManagerConstant.USER_EMAIL);
            if (ObjectUtils.isEmpty(userEmail)) {
                return R.error(BizCodeEnum.CODE_NOT_MATCH.getCode(), BizCodeEnum.CODE_NOT_MATCH.getMessage());
            }
            else {
                UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().eq("email", userEmail));
                if (ObjectUtils.isEmpty(userEntity)) {
                    return R.error(BizCodeEnum.NOT_FIND_USER.getCode(), BizCodeEnum.NOT_FIND_USER.getMessage());
                }
                return R.ok().setData(userEntity);
            }
        } catch (Throwable throwable) {
            //logger.error("execute aim method has exception {}",throwable.getMessage());
            return R.error(BizCodeEnum.ILLEGAL_REQUEST.getCode(), BizCodeEnum.ILLEGAL_REQUEST.getMessage());
        }
    }
}
