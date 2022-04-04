package com.xm.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共entity 因为common模块和gateWay模块都需要使用到
 *
 * @date 2021-10-27 10:56:17
 */
@Data
@TableName("eam_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户
	 */
	@TableId
	private Long id;

	public Long getId() {
		return id;
	}

	/**
	 * 飞书openAPI提供的用户唯一标识
	 */
	private String openId;
	/**
	 * 飞书的用户名称
	 */
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	/**
	 * 飞书用户的组信息(不知道这个能不能获取到，具体到最小的组信息) 隔断组使用-分割 eg：手机部-系统软件部-xxxx
	 */
	private String department;
	/**
	 * 头像url，根据飞书开放用户信息相关的api获取
	 */
	private String avatarUrl;
	/**
	 * 飞书用户的邮箱
	 */
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 电话
	 */
	private Long phone;
	/**
	 * 工位(非必须)
	 */
	private String workSeat;
	/**
	 * 0:北京 1:南京 2:深圳
	 */
	private Integer userOrigin;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;


	/**
     * 员工类型
	 */
	private String type;

    /**
     * mi_id 唯一Id
	 */
	private String   miId;
	/**
	 * is_root
	 * 0 普通用户
	 */
	private Integer isRoot;

}
