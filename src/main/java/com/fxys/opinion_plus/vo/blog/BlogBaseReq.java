package com.fxys.opinion_plus.vo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogBaseReq {
    @NotNull
    private String day;
    @NotNull
    private Long kid;
}
