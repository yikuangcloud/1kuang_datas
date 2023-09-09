package com.ruoyi.project.datav.util.readexcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ReadColorBlockExcel {

    public static String read(Sheet sheet) {

        //获取行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        //获取列数
        short lastCellNum = row.getLastCellNum();
        
        JSONArray blocks = new JSONArray();
        
        for (int k = 1; k <= rowNum; k++) {
        	row = sheet.getRow(k);
        	JSONObject block = new JSONObject();
        	
        	for (int i = 0; i < lastCellNum; i++) {
        		//名称
        		if(i == 0){
        			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        			String name = row.getCell(i).getStringCellValue().trim();
        			block.put("name", name);
        		}
        		//数值
        		else if(i == 1){
        			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        			String value = row.getCell(i).getStringCellValue().trim();
        			block.put("value", value);
        		}
        		//后缀
        		else{
        			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        			String suffix = row.getCell(i).getStringCellValue().trim();
        			block.put("suffix", suffix);
        		}
        		
        	}
			
            blocks.add(block);
        }

        return blocks.toJSONString();
    }

}
