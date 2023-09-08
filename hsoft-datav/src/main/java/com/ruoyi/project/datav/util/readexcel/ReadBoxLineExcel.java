package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

public class ReadBoxLineExcel {

	public static String read(Sheet sheet) {
		
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();
        //模板的第一行，从第二个单元格开始是我们的axisData
        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        JSONArray jsonArray = new JSONArray();
        
        //模板从第一行开始的第一列是我们想要的axisData,剩下的列是我们想要的data
        for (int j = 0; j <= lastRowNum; j++) {
        	
            row = sheet.getRow(j);
            List<Double> ds = Lists.newArrayList();
            
            for (int k = 0; k < lastCellNum; k++) {
                if(k == 0) {
                    row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                    String res = row.getCell(k).getStringCellValue().trim();
                    axisDataList.add(res);
                } else {
                    row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Double res = row.getCell(k).getNumericCellValue();
                    ds.add(res);
                }
            }
            jsonArray.add(ds);
        }
      
        jsonArray.add(axisDataList);
        // System.err.println(jsonArray.toJSONString());
        
        return jsonArray.toJSONString();
    }
	
}
