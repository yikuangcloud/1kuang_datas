package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

public class ReadScatterExcel {

    public static String read(Sheet sheet) {
        
        //解析axisData集合
    	JSONArray scatterList =new JSONArray();

        //获取行数
        int rowNum = sheet.getLastRowNum();
        
        for (int k = 1; k < rowNum +1; k++) {
        	//解析data集合
            List<Double> dataList = Lists.newArrayList();
        	sheet.getRow(k).getCell(0).setCellType(Cell.CELL_TYPE_NUMERIC);
        	sheet.getRow(k).getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
        	//获取第一列为横坐标
        	Double data1 = sheet.getRow(k).getCell(0).getNumericCellValue();
            //获取第二列为纵坐标
            Double data2 = sheet.getRow(k).getCell(1).getNumericCellValue();
            dataList.add(data1);
            dataList.add(data2);
            
            scatterList.add(dataList);
        }
        

        return scatterList.toJSONString();
    }

}
