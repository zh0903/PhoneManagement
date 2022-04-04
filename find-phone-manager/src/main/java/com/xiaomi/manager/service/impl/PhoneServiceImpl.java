package com.xm.manager.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xm.common.entity.UserEntity;
import com.xm.manager.entity.LogEntity;
import com.xm.manager.exception.BizCodeEnum;
import com.xm.manager.exception.NotFindPhoneException;
import com.xm.manager.exception.NotFindUserException;
import com.xm.manager.req.ScanBorrowPhoneReq;
import com.xm.manager.service.LogService;
import com.xm.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xm.common.utils.PageUtils;
import com.xm.common.utils.Query;

import com.xm.manager.dao.PhoneDao;
import com.xm.manager.entity.PhoneEntity;
import com.xm.manager.service.PhoneService;
import org.springframework.util.ObjectUtils;


@Service("phoneService")
public class PhoneServiceImpl extends ServiceImpl<PhoneDao, PhoneEntity> implements PhoneService {


    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PhoneEntity> page = this.page(
                new Query<PhoneEntity>().getPage(params),
                new QueryWrapper<PhoneEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void scanBorrowPhone(ScanBorrowPhoneReq req) {
        UserEntity user = userService.getById(req.getUserId());
        PhoneEntity phoneEntity = this.getById(req.getPhoneId());

        LogEntity logEntity = new LogEntity();
        logEntity.setLaserNumer(phoneEntity.getLaserNumer());
        logEntity.setAfterHolderUserId(user.getId());
        logEntity.setAfterHolder(user.getNickName());

        if(!ObjectUtils.isEmpty(phoneEntity.getHolderUserId())){
            logEntity.setBeforeHolderUserId(phoneEntity.getHolderUserId());
            logEntity.setBeforeHolder(phoneEntity.getHolder());
        }
        if (ObjectUtils.isEmpty(user)) {
            throw new NotFindUserException(BizCodeEnum.NOT_FIND_USER    .getMessage(), BizCodeEnum.NOT_FIND_USER.getCode());
        }
        if (ObjectUtils.isEmpty(phoneEntity)) {
            throw new NotFindPhoneException(BizCodeEnum.NOT_FIND_PHONE.getMessage(), BizCodeEnum.NOT_FIND_PHONE.getCode());
        }
        phoneEntity.setHolderUserId(user.getId());
        phoneEntity.setHolder(user.getNickName());

        this.updateById(phoneEntity);
        logService.save(logEntity);
    }


    @Override
    public List<PhoneEntity> fuzzySearch(String name){
        return this.getBaseMapper().selectList(
                new QueryWrapper<PhoneEntity>().like("laser_numer", name));
    }

    @Override
    public List<PhoneEntity> getMyPhone(String email) {
        UserEntity user = userService.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getEmail, email), false);
        if (ObjectUtils.isEmpty(user)) {
            throw new NotFindUserException(BizCodeEnum.NOT_FIND_USER.getMessage(), BizCodeEnum.NOT_FIND_USER.getCode());
        }

        return this.getBaseMapper().selectList(
                new QueryWrapper<PhoneEntity>().eq("holder_user_id", user.getId()));
    }
}