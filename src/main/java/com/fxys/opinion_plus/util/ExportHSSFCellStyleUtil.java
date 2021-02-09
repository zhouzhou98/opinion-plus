package com.fxys.opinion_plus.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class ExportHSSFCellStyleUtil {
    /**
     * 创建基础样式
     * 水平和垂直居中
     */
    public static HSSFCellStyle createBaseStyle(HSSFWorkbook workbook){
        HSSFCellStyle style=workbook.createCellStyle();
        //设置水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }


    /**
     * 创建数据表格的样式
     */
    public static HSSFCellStyle createTableTitleStyle(HSSFWorkbook workbook){
        HSSFCellStyle style=createBaseStyle(workbook);

        //设置字体
        HSSFFont font=workbook.createFont();
        font.setBold(true);//设置粗体
        font.setItalic(true);//设置斜体
        font.setFontHeightInPoints((short) 15);//设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_TEAL.getIndex());//设置颜色
        font.setFontName("Consolas");//设置字体
        style.setFont(font);

        return style;
    }

    /**
     * 创建小标题样式
     */
    public static HSSFCellStyle createSubTitleStyle(HSSFWorkbook workbook){
        HSSFCellStyle style=createBaseStyle(workbook);

        //设置字体
        HSSFFont font=workbook.createFont();
        font.setBold(true);//设置粗体

        font.setFontHeightInPoints((short) 20);//设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());//设置颜色
        font.setFontName("楷体");//设置字体
        style.setFont(font);

        return style;
    }




    /**
     * 创建标题样式
     */
    public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook){
        HSSFCellStyle style=createBaseStyle(workbook);

        //设置字体
        HSSFFont font=workbook.createFont();
        font.setBold(true);//设置粗体

        font.setFontHeightInPoints((short) 25);//设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.BRIGHT_GREEN.getIndex());//设置颜色
        font.setFontName("华文行楷");//设置字体
        style.setFont(font);

        return style;
    }
}
