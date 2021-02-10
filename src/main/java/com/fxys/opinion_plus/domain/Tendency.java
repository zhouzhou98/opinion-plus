package com.fxys.opinion_plus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tendency {
    private String time;
    private Integer total;
    private Integer sensitive;
    private Integer nonsensitive;
    private Integer positive;
    private Integer negative;
}
