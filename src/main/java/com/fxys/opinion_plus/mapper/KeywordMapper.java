package com.fxys.opinion_plus.mapper;

import com.fxys.opinion_plus.domain.Keyword;
import com.fxys.opinion_plus.resp.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);


    List<Keyword> getByPage(Page<Keyword> page);

    int getCountByPage(Page<Keyword> page);

    Keyword selectByKnameAndUid(@Param("uid") Long uid,@Param("kname") String kname);


   void updateKeyword(Keyword keyword);
}