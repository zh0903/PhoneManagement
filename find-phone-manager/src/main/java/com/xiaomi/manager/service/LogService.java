package com.xiaomi.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.common.utils.PageUtils;
import com.xiaomi.manager.entity.LogEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-27 10:56:17
 */
public interface LogService extends IService<LogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

