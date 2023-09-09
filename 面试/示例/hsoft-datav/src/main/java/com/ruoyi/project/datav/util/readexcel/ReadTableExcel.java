package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadTableExcel {

    public static String read(Sheet sheet) {
       
        Row row = sheet.getRow(0);
        //获取行数
        int rowNum = sheet.getLastRowNum();
        //获取列数
        short lastCellNum = row.getLastCellNum();
        
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();

        //模板的第一行为字段名称
        row = sheet.getRow(0);
        for (int k = 0; k < lastCellNum; k++) {
            row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
            String res = row.getCell(k).getStringCellValue().trim();
            axisDataList.add(res);
        }
        
        
        JSONArray tables = new JSONArray();
        
        for (int k = 1; k <= rowNum; k++) {
        	row = sheet.getRow(k);
        	JSONObject table = new JSONObject();
        	
        	for (int i = 0; i < lastCellNum; i++) {
        		
				row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				String value = row.getCell(i).getStringCellValue().trim();
				table.put(axisDataList.get(i), value);
        		
        	}
			
            tables.add(table);
        }

        return tables.toJSONString();
    }



}
