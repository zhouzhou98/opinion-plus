package com.fxys.opinion_plus.vo.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordAdd {
    @NotNull
    private String kname;

    private String description;

    @NotNull
    private Long uid;

    @NotNull
    private Integer isOpen;
}
