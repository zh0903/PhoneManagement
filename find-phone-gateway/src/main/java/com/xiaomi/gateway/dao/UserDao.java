package com.xm.gateway.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 

 *
 * @date 2021-10-27 10:56:17
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
