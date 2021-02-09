package com.fxys.opinion_plus.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {
    private Long id;

    /**
    * 用户id
    */
    private Long uid;

    /**
    * 舆情关键字
    */
    private String kname;

    /**
    * 舆情关键字描述
    */
    private String description;

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

    /**
    * 是否开启舆情预警
    */
    private Integer isOpen;

    private Boolean isLoading;
}