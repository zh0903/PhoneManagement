package com.xm.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
