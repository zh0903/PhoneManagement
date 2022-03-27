package com.xiaomi.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lyj
 * @email 1499755237@qq.com
 * @date 2021-10-27 10:56:17
 */
@Data
@TableName("eam_phone")
public class PhoneEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 手机ID
	 */
	@TableId
	private Long id;
	/**
	 * 手机品牌
	 */
	private String brand;
	/**
	 * 0:北京 1:南京 2:深圳
	 */
	private String phoneOrigin;
	/**
	 * 镭雕号
	 */
	private String laserNumer;

	/**
	 * 手机类型
	 */
	private String phoneType;

	/**
	 * 版本号: 0:国内、1:国际、2:印度
	 */
	private String phoneVersion;
	/**
	 * 持有者ID
	 */
	private Long holderUserId;


	/**
	 * 内存
	 */
	private Integer memory;
	/**
	 * 硬盘
	 */
	private Integer disk;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 持有人
	 */
    private String holder;

	public String getHolder() {
		return holder;
	}

	/**
	 * 归属人
	 */
	private String owner;
}
