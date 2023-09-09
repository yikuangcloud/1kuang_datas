package com.ruoyi.project.datav.util.readexcel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadDoubleBarTimeExcel {

    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();

        //模板的第一行，从第二个单元格开始是我们的axisData
        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        
        for (int j = 1; j <= lastRowNum; j++) {
        	sheet.getRow(j).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            String res = sheet.getRow(j).getCell(0).getStringCellValue().trim();
            axisDataList.add(res);
        }
        //将axisData去重
        HashSet h = new HashSet(axisDataList);   
        axisDataList.clear();   
        axisDataList.addAll(h); 
        
        
        JSONArray jsonArray = new JSONArray();
        for (int k = 2; k < lastCellNum; k++) {
        	JSONObject object = new JSONObject();
        	
            row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
            String res = row.getCell(k).getStringCellValue().trim();
            object.put("name", res);
            object.put("label",axisDataList);
            
        	List<DoubleBar> barList = new ArrayList<>();
            for (int j = 1; j <= lastRowNum; j++) {
            	
            	DoubleBar doubleBar = new DoubleBar();
            	//名称
        		sheet.getRow(j).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String label = sheet.getRow(j).getCell(0).getStringCellValue().trim();
                doubleBar.setLabel(label);
                //分类
                sheet.getRow(j).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String axis = sheet.getRow(j).getCell(1).getStringCellValue().trim();
                doubleBar.setAxis(axis);
                //数值
                sheet.getRow(j).getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                Double data = sheet.getRow(j).getCell(k).getNumericCellValue();
                doubleBar.setData(data);
                
                barList.add(doubleBar);
                
            	
            }
            Map<String,List<DoubleBar>> stringListMap = barList.stream().collect(Collectors.groupingBy(DoubleBar::getAxis));
            
            int i = -1;
            for(String key : stringListMap.keySet()){
            	i++;
            	JSONObject barObject = new JSONObject();
            	
            	barObject.put("name", key);
            	//获取该分类下数值集合
            	List<Double> ds = Lists.newArrayList();
            	List<DoubleBar> axisList = stringListMap.get(key);
            	
            	for(int m=0;m<axisList.size();m++){
            		ds.add(axisList.get(m).getData());
            	}
            	
            	barObject.put("data", ds);
            	
            	//分别赋值两组数值集合
            	if(i == 0){
            		object.put("data1", barObject);
            	}else{
            		object.put("data2", barObject);
            	}

             }
            
            jsonArray.add(object);
        }
        
        
        return jsonArray.toJSONString();
    }
    
}
class DoubleBar{
	
	private String label;
	
	private String axis;
	
	private Double data;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}
	
}
