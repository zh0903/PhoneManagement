package com.xm.manager.dao;

import com.xm.manager.entity.LogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogDao extends BaseMapper<LogEntity> {
	
}
