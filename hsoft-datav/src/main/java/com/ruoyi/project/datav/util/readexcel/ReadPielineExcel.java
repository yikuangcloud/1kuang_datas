package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

public class ReadPielineExcel {

    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;

        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        
        JSONArray jsonArray = new JSONArray();
        
        
        for (int k = 0; k <= lastRowNum; k++) {
        	
        	row = sheet.getRow(k);
        	
        	List<String> valueList = Lists.newArrayList();
        	
        	for (int i = 0; i < lastCellNum; i++) {
        		//第一行为axisData
        		if( k == 0){
        			//其中第一列固定为product
        			if( i == 0){
        				valueList.add("product");
        			}else{
        				row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        				String res = row.getCell(i).getStringCellValue().trim();
        				valueList.add(res);
        			}
        		}else{
        			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
    				String res = row.getCell(i).getStringCellValue().trim();
    				valueList.add(res);
        		}
        		
        	}
        	jsonArray.add(valueList);
        }
       
        return jsonArray.toJSONString();
    }

}
