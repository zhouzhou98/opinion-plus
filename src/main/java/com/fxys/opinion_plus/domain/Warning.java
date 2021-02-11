package com.fxys.opinion_plus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warning {
    private String grade;

    private Integer low;
    private Integer low_mid;
    private Integer mid;
    private Integer mid_hei;
    private Integer height;
}
