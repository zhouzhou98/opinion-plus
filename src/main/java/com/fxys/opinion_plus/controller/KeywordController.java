package com.fxys.opinion_plus.controller;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.Keyword;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.service.IKeywordService;
import com.fxys.opinion_plus.vo.keyword.KeywordAdd;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

@RestController
public class KeywordController {
    @Autowired
    private IKeywordService keywordService;
    @PostMapping(value = PathConstants.KEYWORD_SEARCH)
    public Page<Keyword> getByPage(@RequestBody Page<Keyword> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"createTime", "updateTime"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn)) {
                throw new OpinionException(ResultCodeEnums.PARAM_IS_INVALID);
            }
        }
        return keywordService.getByPage(page);
    }

    @PostMapping(value = PathConstants.KEYWORD_ADD)
    public String add(@RequestBody KeywordAdd req){
        return keywordService.add(req);
    }

    @GetMapping(value = PathConstants.KEYWORD_GET+"/{id}")
    public Keyword get(@PathVariable Long id){
        return keywordService.selectById(id);
    }

    @PostMapping(value = PathConstants.KEYWORD_UPDATE)
    public String update(@RequestBody Keyword keyword){
        return keywordService.update(keyword);
    }

    @PostMapping(value = PathConstants.KEYWORD_DELETE+"/{id}")
    public String delete(@PathVariable Long id){
        return keywordService.delete(id);
    }

//    @PostMapping(value = PathConstants.KEYWORD_EXPORT)
//    public void export(HttpServletResponse response, HttpServletRequest req)throws Exception{
//        Long id=Long.valueOf(req.getParameter("id"));
//        Workbook workbook=keywordService.export(id);
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + "日志");
//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
//    }
}
