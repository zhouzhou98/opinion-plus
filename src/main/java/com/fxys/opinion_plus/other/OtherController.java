package com.fxys.opinion_plus.other;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.Blog;
import com.fxys.opinion_plus.domain.Keyword;
import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.service.IBlogService;
import com.fxys.opinion_plus.service.IKeywordService;
import com.fxys.opinion_plus.service.IUserService;
import com.fxys.opinion_plus.util.ExportDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;

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

    @RequestMapping(value = PathConstants.BLOG_EXPORT)
    public ResponseEntity<Object> exportBlogData(HttpServletRequest req){
        Long id=Long.valueOf(req.getParameter("id"));
        Long uid=Long.valueOf(req.getParameter("uid"));
        Blog b=blogService.selectById(id);
        User u=userService.selectById(uid);
        Keyword keyword=keywordService.selectById(b.getKid());
        String fileName=keyword.getKname()+"-关键字监控对象"+b.getAuthor()+"舆情信息表.xls";
        String sheetName=keyword.getKname()+"-关键字监控对象"+b.getAuthor()+"舆情信息";
        ByteArrayOutputStream bos= ExportDataUtil.exportBlogData(b,keyword,u,sheetName);
        try {
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建封装响应头信息的对象
            HttpHeaders header=new HttpHeaders();
            //封装响应内容类型（APPLICATION_OCTET_STREAM 响应的内容不限定）
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件名称
            header.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<>(bos.toByteArray(),header, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = PathConstants.BLOG_CLOUD)
    public void wordCloud(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        blogService.wordCloud(req,resp);
    }
}
