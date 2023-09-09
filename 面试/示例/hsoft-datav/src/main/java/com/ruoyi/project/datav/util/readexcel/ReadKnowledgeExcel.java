package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadKnowledgeExcel {

    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        //解析数据源集合
        List<String> sourceList = Lists.newArrayList();
        //解析目标节点集合
        List<String> targetList = Lists.newArrayList();

        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        
        //从第三列开始为目标节点
        //获取目标节点集合
        for (int k = 2; k < lastCellNum; k++) {
            row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
            String res = row.getCell(k).getStringCellValue().trim();
            targetList.add(res);
        }

        JSONObject knowledgeObject = new JSONObject();
        
        JSONArray nodesList = new JSONArray();
        for (int j = 1; j <= lastRowNum; j++) {
        	row = sheet.getRow(j);
        	//获取源节点集合
        	row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
        	String source = row.getCell(0).getStringCellValue().trim();
        	sourceList.add(source);
        	
        	JSONObject object = new JSONObject();
        	//获取节点名称以及分类
        	for (int k = 0; k < 2; k++) {
        		
        		if(k == 0) {
        			row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
        			String name = row.getCell(k).getStringCellValue().trim();
        			object.put("name", name);
        		}else{
        			row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
        			Double category = row.getCell(k).getNumericCellValue();
        			object.put("category", category);
        		}
        	}
        	nodesList.add(object);
        }
        
        //获取连接集合
        JSONArray linksList = new JSONArray();
        for (int j = 1; j <= lastRowNum; j++) {
        	row = sheet.getRow(j);
        	
        	
        	for (int k = 2; k < lastCellNum; k++) {
        		row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
        		
        		String name = row.getCell(k).getStringCellValue().trim();
        		
        		if(!StringUtils.isEmpty(name)){
        			JSONObject object = new JSONObject();
        			object.put("name", name);
        			object.put("source", sourceList.get(j-1));
        			object.put("target", targetList.get(k-2));
        			linksList.add(object);
        		}
        		
        	}
        	
        }
        
        knowledgeObject.put("nodes", nodesList);
        knowledgeObject.put("links", linksList);
        
        return knowledgeObject.toJSONString();
    }

}
