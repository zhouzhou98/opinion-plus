package com.fxys.opinion_plus.service;

import com.fxys.opinion_plus.domain.User;

public interface IUserService {
    User selectById(Long id);
}
