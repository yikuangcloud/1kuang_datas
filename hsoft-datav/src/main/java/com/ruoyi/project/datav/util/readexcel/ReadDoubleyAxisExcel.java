package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadDoubleyAxisExcel {


    public static String read(Sheet sheet) {
        //解析data集合
        List<Double> dataList = Lists.newArrayList();
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();

        //获取行数
        int rowNum = sheet.getLastRowNum();
        
        for (int k = 0; k < rowNum +1; k++) {
        	sheet.getRow(k).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
        	sheet.getRow(k).getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
        	//获取第一列为类型
            String res = sheet.getRow(k).getCell(0).getStringCellValue().trim();
            //获取第二列为值
            Double data = sheet.getRow(k).getCell(1).getNumericCellValue();
            axisDataList.add(res);
            dataList.add(data);
        }
        
        JSONObject bars = new JSONObject();
        
        bars.put("yAxisData", axisDataList);
        bars.put("series", dataList);

        return bars.toJSONString();
    }




}
