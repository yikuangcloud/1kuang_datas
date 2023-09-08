package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadThreeDMapExcel {

    public static String read(Sheet sheet) {

        //获取行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        //获取列数
        short lastCellNum = row.getLastCellNum();
        
        JSONArray bars = new JSONArray();
        
        for (int k = 1; k <= rowNum; k++) {
        	row = sheet.getRow(k);
        	JSONObject bar = new JSONObject();
        	
        	List<String> valueList = Lists.newArrayList();
        	
        	for (int i = 0; i < lastCellNum; i++) {
        		//省市名称
        		if(i == 0){
        			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        			String name = row.getCell(i).getStringCellValue().trim();
        			bar.put("name", name);
        		}
        		//提示框内容
        		else{
        			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        			String value = row.getCell(i).getStringCellValue().trim();
        			valueList.add(value);
        		}
        		
        	}
        	bar.put("value", valueList);
            bars.add(bar);
        }

        return bars.toJSONString();
    }



}
