package com.xm.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xm.common.entity.UserEntity;
import com.xm.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *

 *
 * @date 2021-10-27 10:56:17
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

