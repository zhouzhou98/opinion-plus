package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.mapper.LogMapper;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public void save(Log logger) {
        logMapper.insertSelective(logger);
    }

    @Override
    public Page<Log> getByPage(Page<Log> page) {
        // 查询数据
        List<Log> aboutList = logMapper.getByPage(page);
        page.setList(aboutList);
        // 查询总数
        int totalCount = logMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
