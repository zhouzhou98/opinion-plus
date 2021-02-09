package com.fxys.opinion_plus.service;

import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.resp.Page;

public interface ILogService {
    void save(Log logger);

    Page<Log> getByPage(Page<Log> page);
}
