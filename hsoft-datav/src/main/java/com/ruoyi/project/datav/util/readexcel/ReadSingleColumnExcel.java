package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadSingleColumnExcel {

    public static String read(Sheet sheet) {
    	Row row ;
    	//解析data集合
        List<List<Double>> dataList = Lists.newArrayList();
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();

        //获取行数
        int rowNum = sheet.getLastRowNum();

        
        for (int k = 0; k < rowNum +1; k++) {
            //模板的第一行，第一个单元格是axisData，其他为data
        	//单柱图的数值可以是数组格式
            row = sheet.getRow(k);
            short lastCellNum = row.getLastCellNum();
            
        	List<Double> ds = Lists.newArrayList();
        	for(int j=0;j<lastCellNum;j++){
        		if(j==0){
        			sheet.getRow(k).getCell(j).setCellType(Cell.CELL_TYPE_STRING);
        			//获取第一列为类型
                    String res = sheet.getRow(k).getCell(j).getStringCellValue().trim();
                    axisDataList.add(res);
        		}else{
        			 Double data = sheet.getRow(k).getCell(j).getNumericCellValue();
        			 ds.add(data);
        		}
        	}
        	dataList.add(ds);
        }
        
        JSONArray singleBars = new JSONArray();
		//构造指定格式数据
        for (int i = 0; i < axisDataList.size(); i++) {
        	JSONObject bar = new JSONObject();
        	
        	bar.put("data", dataList.get(i) );
        	bar.put("name", axisDataList.get(i));
        	singleBars.add(bar);
        }
        

        return singleBars.toJSONString();
    }



}
