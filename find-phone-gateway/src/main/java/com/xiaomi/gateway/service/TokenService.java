package com.xiaomi.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.common.utils.PageUtils;
import com.xiaomi.gateway.entity.TokenEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-28 15:49:45
 */
public interface TokenService extends IService<TokenEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

