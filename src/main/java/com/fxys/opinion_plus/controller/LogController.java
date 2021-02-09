package com.fxys.opinion_plus.controller;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.service.ILogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LogController {
    @Autowired
    private ILogService logService;
    /**
     * 日志数据分页
     * @param page 分页对象
     * @return Page对象
     */
    @PostMapping(value = PathConstants.LOG_SEARCH)
    public Page<Log>getByPage(@RequestBody Page<Log> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"createTime", "updateTime"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn)) {
                throw new OpinionException(ResultCodeEnums.PARAM_IS_INVALID);
            }
        }
        return logService.getByPage(page);
    }
}
