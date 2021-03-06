package com.fxys.opinion_plus.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotNull
    private String username;
    @NotNull
    private String password;

}
