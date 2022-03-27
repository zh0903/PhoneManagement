package com.xiaomi.gateway.vo;

import lombok.Data;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 11 月 03 日 下午3:07
 */
@Data
public class UserInfoVO {
    private String user;
    private String name;
    private String displayName;
    private String departmentName;
    private String email;
    private String miID;
    private String avatar;
    private String type;
}
