package com.xm.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xm.common.utils.PageUtils;
import com.xm.gateway.entity.TokenEntity;

import java.util.Map;

/**
 * 
 *

 *
 * @date 2021-10-28 15:49:45
 */
public interface TokenService extends IService<TokenEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

