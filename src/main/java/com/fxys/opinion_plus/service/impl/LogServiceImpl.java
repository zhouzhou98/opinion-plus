package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.mapper.LogMapper;
import com.fxys.opinion_plus.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public void save(Log logger) {
        logMapper.insertSelective(logger);
    }
}
