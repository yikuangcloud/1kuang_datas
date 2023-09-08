package com.ruoyi.project.datav.util.readexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadDoubleBarExcel {


    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        //解析name集合
        List<String> nameList = Lists.newArrayList();
        //解析data集合
        List<List<Double>> dataList = Lists.newArrayList();
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();
        
        //模板的第一行，从第二个单元格开始是我们的axisData
        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        for (int k = 1; k < lastCellNum; k++) {
            row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
            String res = row.getCell(k).getStringCellValue().trim();
            axisDataList.add(res);
        }

        //模板从第二行开始的第一列是我们想要的name,剩下的列是我们想要的data
        for (int j = 1; j <= lastRowNum; j++) {
            row = sheet.getRow(j);

            List<Double> ds = Lists.newArrayList();
            for (int k = 0; k < lastCellNum; k++) {
                if(k == 0) {
                    row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                    String res = row.getCell(k).getStringCellValue().trim();
                    nameList.add(res);
                } else {
                    row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Double res = row.getCell(k).getNumericCellValue();
                    ds.add(res);
                }
            }
            dataList.add(ds);
        }
        

        JSONArray bars = new JSONArray();
        for (int i = 0; i < nameList.size(); i++) {
            
            JSONObject bar = new JSONObject();
            bar.put("name", nameList.get(i));
            List<Object> value = new ArrayList<>();
            List<String> lable = new ArrayList<>();
            for (int j = 0; j < axisDataList.size(); j++) {
                value.add(dataList.get(i).get(j));
                lable.add(axisDataList.get(j));
            }
            List<JSONObject> jsonObjects = Lists.newArrayList();
            for(int k = 0; k < value.size(); k++) {
            	String data = "{value:"+ value.get(k) + ",label:'" + lable.get(k) + "'}";
            	JSONObject jsonObject = JSONObject.parseObject(data); // json字符串变成json对象
    			jsonObjects.add(jsonObject);
            }
            bar.put("data", jsonObjects);
            bars.add(bar);
        }

        return bars.toJSONString();
    }


}
