package com.xm.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xm.common.entity.UserEntity;
import com.xm.common.utils.PageUtils;
import com.xm.common.utils.R;

import java.util.Map;


public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    R whoAmI();
}

