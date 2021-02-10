package com.fxys.opinion_plus.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;

    /**
    * 舆情关键字id
    */
    private Long kid;

    /**
    * 舆情内容
    */
    private String content;

    /**
    * 舆情正负面标志 1代表证明 0代表负面
    */
    private Integer posOrNeg;

    /**
    * 文本检测结果，有内容的把结果显示 ，没有的就写个 无
    */
    private String reason;

    /**
    * 地址链接
    */
    private String href;

    /**
    * 预警等级 1代表低 2代表中低 3代表中 4代表中高 5代表高
    */
    private Integer grade;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 地域 如广东
    */
    private String area;


    private String author;
}