package com.fxys.opinion_plus.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 用户邮箱
    */
    private String email;

    /**
    * 密码
    */
    private String password;

    /**
    * 用户状态 1代表管理员 0代表普通用户
    */
    private Integer state;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 版本号
    */
    private Integer dataVersion;
}