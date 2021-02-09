package com.fxys.opinion_plus.service.impl;

import com.fxys.opinion_plus.domain.Keyword;
import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.mapper.KeywordMapper;
import com.fxys.opinion_plus.resp.Page;
import com.fxys.opinion_plus.service.IKeywordService;
import com.fxys.opinion_plus.util.ExportHSSFCellStyleUtil;
import com.fxys.opinion_plus.vo.keyword.KeywordAdd;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class KeywordServiceImpl implements IKeywordService {
    @Autowired
    private KeywordMapper keywordMapper;
    @Override
    public Page<Keyword> getByPage(Page<Keyword> page) {
        // 查询数据
        List<Keyword> list = keywordMapper.getByPage(page);
        page.setList(list);
        // 查询总数
        int totalCount = keywordMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public String add(KeywordAdd req) {
        Long uid=req.getUid();
        String kname=req.getKname();
        Keyword k=keywordMapper.selectByKnameAndUid(uid,kname);
        if(k==null){
            Keyword keyword=new Keyword();
            keyword.setCreateTime(new Date());
            keyword.setUpdateTime(new Date());
            keyword.setDataVersion(0);
            keyword.setDescription(req.getDescription());
            keyword.setUid(req.getUid());
            keyword.setIsOpen(req.getIsOpen());
            keyword.setKname(req.getKname());
            try{
                keywordMapper.insertSelective(keyword);
                return ResultCodeEnums.INSERT_SUCCESS.message();
            }catch (Exception e){
                throw new OpinionException(ResultCodeEnums.INSERT_FAIL);
            }


        }else{
            throw new OpinionException(ResultCodeEnums.KEYWORD_HAS_EXISTED);
        }

    }

    @Override
    public Keyword selectById(Long id) {
        return keywordMapper.selectByPrimaryKey(id);
    }

    @Override
    public String update(Keyword keyword) {
        try {
            keyword.setUpdateTime(new Date());
            keywordMapper.updateKeyword(keyword);
            return ResultCodeEnums.UPDATE_SUCCESS.message();
        }catch (Exception e){
            throw new OpinionException(ResultCodeEnums.UPDATE_FAIL);
        }

    }

    @Override
    public String delete(Long id) {
        try {
            keywordMapper.deleteByPrimaryKey(id);
            return ResultCodeEnums.DELETE_SUCCESS.message();
        }catch (Exception e){
            throw new OpinionException(ResultCodeEnums.DELETE_FAIL);
        }

    }

    @Override
    public Workbook export(Long id) {

        Keyword k=keywordMapper.selectByPrimaryKey(id);
        System.err.println(k);
//        String fileName=k.getKname()+
//                "-关键字监控对象舆情信息表.xls";
        String sheetName=k.getKname()+
                "关键字监控对象舆情信息";
        //一、组装Excel文档
        //1组建工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //2创建样式
        HSSFCellStyle baseStyle= ExportHSSFCellStyleUtil.createBaseStyle(workbook);
        HSSFCellStyle subTitleStyle=ExportHSSFCellStyleUtil.createSubTitleStyle(workbook);
        HSSFCellStyle tableTitleStyle=ExportHSSFCellStyleUtil.createTableTitleStyle(workbook);
        HSSFCellStyle titleStyle=ExportHSSFCellStyleUtil.createTitleStyle(workbook);
        //3在工作簿下创建一个sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //4设置sheet
        sheet.setDefaultColumnWidth(30);
        sheet.setColumnWidth(1,50*256);

        //5合并
        CellRangeAddress region1=new CellRangeAddress(0,0,0,3);
        sheet.addMergedRegion(region1);

        //6创建第一行
        int index=0;
        HSSFRow row1=sheet.createRow(index);
        //6.1在第一行里面创建一个单元格
        HSSFCell row1_cell1=row1.createCell(0);
        //6.2设置标题样式
        row1_cell1.setCellStyle(titleStyle);
        //6.3设置单元格内容
        row1_cell1.setCellValue(k.getKname()+"关键字监控对象舆情信息");


        index++;
        HSSFRow row2=sheet.createRow(index);
        //7.1设置行高
        row2.setHeightInPoints(150);
        HSSFCell row2_Cell1 = row2.createCell(0);
        row2_Cell1.setCellStyle(baseStyle);
        row2_Cell1.setCellValue("监控关键字：");


        HSSFCell row2_Cell2 = row2.createCell(1);
        row2_Cell2.setCellStyle(baseStyle);
        row2_Cell2.setCellValue(k.getKname());

        index++;
        HSSFRow row3=sheet.createRow(index);

        HSSFCell row3_Cell1 = row3.createCell(0);
        row3_Cell1.setCellStyle(baseStyle);
        row3_Cell1.setCellValue("描述：");
        String description;
        if(k.getDescription()==null){
            description="创建者没有描述";
        }else{
            description=k.getDescription();
        }
        HSSFCell row3_Cell2 = row3.createCell(1);
        row3_Cell2.setCellStyle(baseStyle);
        row3_Cell2.setCellValue(description);

        index++;
        HSSFRow row7=sheet.createRow(index);
        HSSFCell row7_Cell3 = row7.createCell(2);
        row7_Cell3.setCellStyle(baseStyle);
        row7_Cell3.setCellValue("打印时间:");

        HSSFCell row7_Cell4 = row7.createCell(3);
        row7_Cell4.setCellStyle(baseStyle);
        row7_Cell4.setCellValue(new Date().toLocaleString());
        //创建内存流
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

        //把workbook里面的数据写到outputstream里面去
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

       return workbook;
    }
}
