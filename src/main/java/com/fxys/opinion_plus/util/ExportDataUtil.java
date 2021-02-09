package com.fxys.opinion_plus.util;


import com.fxys.opinion_plus.domain.Keyword;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExportDataUtil {
    public static ByteArrayOutputStream exportKeywordsData(Keyword keywords, String sheetName){
        //一、组装Excel文档
        //1组建工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //2创建样式
        HSSFCellStyle baseStyle=ExportHSSFCellStyleUtil.createBaseStyle(workbook);
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
        row1_cell1.setCellValue(keywords.getKname()+"关键字监控对象舆情信息");

        //7设置第二行
        index++;
        HSSFRow row2=sheet.createRow(index);
        //7.1设置行高
        row2.setHeightInPoints(150);
        HSSFCell row2_Cell1 = row2.createCell(0);
        row2_Cell1.setCellStyle(baseStyle);
        row2_Cell1.setCellValue("监控关键字：");


        HSSFCell row2_Cell2 = row2.createCell(1);
        row2_Cell2.setCellStyle(baseStyle);
        row2_Cell2.setCellValue(keywords.getKname());

        HSSFCell row2_Cell3 = row2.createCell(2);
        row2_Cell3.setCellStyle(baseStyle);

        //第三行
        index++;
        HSSFRow row3=sheet.createRow(index);

        HSSFCell row3_Cell1 = row3.createCell(0);
        row3_Cell1.setCellStyle(baseStyle);
        row3_Cell1.setCellValue("热度：");
        String description;
        if(keywords.getDescription()==null){
            description="无";
        }else{
            description=keywords.getDescription();
        }
        HSSFCell row3_Cell2 = row3.createCell(1);
        row3_Cell2.setCellStyle(baseStyle);
        row3_Cell2.setCellValue(description);


        //第四行
        index++;
        HSSFRow row4=sheet.createRow(index);

        HSSFCell row4_Cell1 = row4.createCell(0);
        row4_Cell1.setCellStyle(baseStyle);
        row4_Cell1.setCellValue("用户名：");




        //第五行 空行
        index++;

        //第七行
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

        return  outputStream;
    }


}
