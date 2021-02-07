package com.fxys.opinion_plus.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Long id;

    /**
    * 用户id
    */
    private Long uid;

    /**
    * 请求响应路径
    */
    private String url;

    /**
    * 请求参数
    */
    private String params;

    /**
    * 访问状态
    */
    private Integer status;

    /**
    * 异常信息
    */
    private String message;

    /**
    * 请求方法
    */
    private String method;

    /**
    * 响应时间
    */
    private Long time;

    /**
    * 请求ip
    */
    private String ip;

    /**
    * 创建时间
    */
    private Date createTime;
}