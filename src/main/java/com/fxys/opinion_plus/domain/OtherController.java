package com.fxys.opinion_plus.domain;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.service.IKeywordService;
import com.fxys.opinion_plus.util.ExportDataUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;

@RestController
public class OtherController {
    @Autowired
    private IKeywordService keywordService;
    @RequestMapping(value = PathConstants.KEYWORD_EXPORT+"/{id}")
    public ResponseEntity<Object> exportKeywordsData(@PathVariable Long id){

        Keyword keywords=keywordService.selectById(id);

        String fileName=keywords.getKname()+
                "-关键字监控对象舆情信息表.xls";
        String sheetName=keywords.getKname()+
                "关键字监控对象舆情信息";
        ByteArrayOutputStream bos=
                ExportDataUtil.exportKeywordsData(keywords,sheetName);
        try {
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建封装响应头信息的对象
            HttpHeaders header=new HttpHeaders();
            //封装响应内容类型（APPLICATION_OCTET_STREAM 响应的内容不限定）
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件名称
            header.setContentDispositionFormData(
                    "attachment",fileName);
            return new ResponseEntity<>(bos.toByteArray(),
                    header, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;

    }
}
