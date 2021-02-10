package com.fxys.opinion_plus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tendency extends SingleTendency{


    private Integer sensitive;
    private Integer nonsensitive;
    private Integer positive;
    private Integer negative;

    public Tendency(String time, Integer total, Integer sensitive, Integer nonsensitive, Integer positive, Integer negative) {
        super(time, total);
        this.sensitive = sensitive;
        this.nonsensitive = nonsensitive;
        this.positive = positive;
        this.negative = negative;
    }


}
