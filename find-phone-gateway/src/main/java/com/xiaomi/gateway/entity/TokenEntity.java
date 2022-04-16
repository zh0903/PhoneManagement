package com.xm.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @date 2021-10-28 15:49:45
 */
@AllArgsConstructor
@Data
@TableName("eam_token")
public class TokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * token字符串
	 */
	private String token;
	/**
	 * 用户的id
	 */
	private Long userId;
	/**
	 * 过期时间
	 */
	private Date expireTime;

}
