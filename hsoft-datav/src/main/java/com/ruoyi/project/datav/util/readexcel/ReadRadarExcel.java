package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadRadarExcel {

    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;

        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        
        List<JSONObject> valueList = Lists.newArrayList();
        JSONArray jsonArray = new JSONArray();
        
        //构造各分类数据
        //从第三列开始为分类数据
        for (int k = 2; k < lastCellNum; k++) {
        	JSONObject valueObject = new JSONObject();
        	List<Double> ds = Lists.newArrayList();
        	for (int i = 0; i <= lastRowNum; i++) {
        		row = sheet.getRow(i);
        		//第一行是分类名称
        		if(i == 0){
        			row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
        			valueObject.put("name", row.getCell(k).getStringCellValue().trim());
        		}
        		//其他行为分类数值
        		else{
        			row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Double res = row.getCell(k).getNumericCellValue();
                    ds.add(res);
        		}
        	}
        	valueObject.put("value", ds);
        	valueList.add(valueObject);
        }
        jsonArray.add(valueList);
        List<JSONObject> maxList = Lists.newArrayList();
        
        //构造名称与最大值
        //第一列为名称，第二列为最大值
        for (int j = 1; j <= lastRowNum; j++) {
        	row = sheet.getRow(j);
        	
        	JSONObject labelObject = new JSONObject();
        	
        	row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
            
            Double res = row.getCell(1).getNumericCellValue();
            
            labelObject.put("name", row.getCell(0).getStringCellValue().trim());
            labelObject.put("max", res);
            
            maxList.add(labelObject);
        }
        jsonArray.add(maxList);

        return jsonArray.toJSONString();
    }



}
