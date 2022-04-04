package com.xm.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 

 *
 * @date 2021-10-27 10:56:17
 */
@Data
@TableName("eam_log")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 转移手机
	 */
	private String laserNumer;

	public void setLaserNumer(String laserNumer) {
		this.laserNumer = laserNumer;
	}

	public void setBeforeHolderUserId(Long beforeHolderUserId) {
		this.beforeHolderUserId = beforeHolderUserId;
	}

	public void setAfterHolderUserId(Long afterHolderUserId) {
		this.afterHolderUserId = afterHolderUserId;
	}

	/**
	 * 被转移者id
	 */
	private Long beforeHolderUserId;
	/**
	 * 被转移者
	 */
	private String beforeHolder;
	/**
	 * 转移者id
	 */
	private Long afterHolderUserId;
	/**
	 * 转移者
	 */
	private String afterHolder;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;

}
