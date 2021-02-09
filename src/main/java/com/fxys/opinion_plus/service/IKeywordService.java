package com.fxys.opinion_plus.service;

import com.fxys.opinion_plus.domain.Keyword;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.vo.keyword.KeywordAdd;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;

public interface IKeywordService {
    Page<Keyword> getByPage(Page<Keyword> page);

    String add(KeywordAdd req);

    Keyword selectById(Long id);

    String update(Keyword keyword);

    String delete(Long id);


    Workbook export(Long id);
}
