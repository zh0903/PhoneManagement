package com.xiaomi.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-27 10:56:17
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
