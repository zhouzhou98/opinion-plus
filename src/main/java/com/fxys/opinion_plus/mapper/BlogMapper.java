package com.fxys.opinion_plus.mapper;

import com.fxys.opinion_plus.domain.Blog;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    int selectByTime(@Param("kid") Long kid,@Param("start") Date start,@Param("end") Date end);


    int selectBySensitive(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end);


    int selectByNontive(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end);

    int selectByPositive(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end);

    int selectByNegative(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end);



    List<Blog> selectByAll(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end);

    int selectByCountAll(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end);

    int selectByArea(@Param("kid")Long kid,@Param("start") Date start,@Param("end") Date end,@Param("province") String province);
}