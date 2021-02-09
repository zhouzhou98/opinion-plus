package com.fxys.opinion_plus.mapper;

import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.resp.Page;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);


    List<Log> getByPage(Page<Log> page);

    int getCountByPage(Page<Log> page);
}