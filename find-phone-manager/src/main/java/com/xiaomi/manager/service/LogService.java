package com.xm.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xm.common.utils.PageUtils;
import com.xm.manager.entity.LogEntity;

import java.util.Map;


public interface LogService extends IService<LogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

