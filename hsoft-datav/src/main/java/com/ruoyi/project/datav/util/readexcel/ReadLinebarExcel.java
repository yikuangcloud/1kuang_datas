package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadLinebarExcel {

    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;

        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        
        JSONArray jsonArray = new JSONArray();
        //数值集合
        List<JSONObject> valueList = Lists.newArrayList();
        //分类集合
        List<String> categoryList = Lists.newArrayList();
        
        for (int k = 0; k <= lastRowNum; k++) {
        	
        	row = sheet.getRow(k);
        	
        	JSONObject object = new JSONObject();
        	List<Double> dataList = Lists.newArrayList();
        	
        	for (int i = 0; i < lastCellNum; i++) {
        		//第一行为axisData
        		if( k == 0){
        			//获取每列名称
        			if( i > 1){
        				
        				row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        				String res = row.getCell(i).getStringCellValue().trim();
        				categoryList.add(res);
        			}
        		}
        		//从第二行开始为数值
        		else{
        			//第一列为名称
        			if(i == 0){
        				row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        				String name = row.getCell(i).getStringCellValue().trim();
        				
        				object.put("name", name);
        			}
        			//第二列为类型
        			else if(i == 1){
        				row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        				String type = row.getCell(i).getStringCellValue().trim();
        				
        				object.put("type", type);
        			}
        			//其他列为数值
        			else{
        				 row.getCell(i).setCellType(Cell.CELL_TYPE_NUMERIC);
                         Double res = row.getCell(i).getNumericCellValue();
                         dataList.add(res);
        			}
        			
        		}
        		
        	}
        	if( k > 0){
        		object.put("data", dataList);
        		valueList.add(object);
        	}
        	
        }
        
        jsonArray.add(valueList);
        jsonArray.add(categoryList);
        
        return jsonArray.toJSONString();
    }



}
