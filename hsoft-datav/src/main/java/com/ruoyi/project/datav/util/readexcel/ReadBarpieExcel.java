package com.ruoyi.project.datav.util.readexcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ReadBarpieExcel {

    public static String read(Sheet sheet) {

        //获取行数
        int rowNum = sheet.getLastRowNum();
        
        JSONArray jsonArry = new JSONArray();
        
        for (int k = 0; k < rowNum+1; k++) {
        	JSONObject bar = new JSONObject();
        	
        	sheet.getRow(k).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
        	sheet.getRow(k).getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
        	//获取第一列为类型
            String res = sheet.getRow(k).getCell(0).getStringCellValue().trim();
            //获取第二列为值
            Double data = sheet.getRow(k).getCell(1).getNumericCellValue();
            
            bar.put("name", res);
            bar.put("value", data );
            jsonArry.add(bar);
        }
        
        

        return jsonArry.toJSONString();
    }


}
