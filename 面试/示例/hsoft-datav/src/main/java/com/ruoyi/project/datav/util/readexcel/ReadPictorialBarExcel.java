package com.ruoyi.project.datav.util.readexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadPictorialBarExcel {
    public static String read(Sheet sheet){
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        //解析name集合
        List<String> nameList = Lists.newArrayList();
        //解析data集合
        List<List<Double>> dataList = Lists.newArrayList();
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();
        //解析data的最大值集合
        List<Double> maxList = Lists.newArrayList();

        //模板的第一行，从第二个单元格开始是我们的axisData, 最后一个是最大值，单独取出
        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        for (int k = 1; k < lastCellNum; k++) {
            row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
            String res = row.getCell(k).getStringCellValue().trim();
            if(!res.equals("MAX")){
                axisDataList.add(res);
            }
        }

        //模板从第二行开始的第一列是我们想要的name,剩下的列是我们想要的data,最后一列是每行数据的最大值
        for (int j = 1; j <= lastRowNum; j++) {
            row = sheet.getRow(j);

            List<Double> ds = Lists.newArrayList();
            for (int k = 0; k < lastCellNum; k++) {
                if(k == 0) {
                    row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                    String res = row.getCell(k).getStringCellValue().trim();
                    nameList.add(res);
                }
                else if (k == lastCellNum-1){
                    row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Double res = row.getCell(k).getNumericCellValue();
                    maxList.add(res);
                }
                else {
                    row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Double res = row.getCell(k).getNumericCellValue();
                    ds.add(res);
                }
            }
            dataList.add(ds);
        }
        JSONObject object = new JSONObject();
        object.put("axisData", axisDataList);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < nameList.size(); i++) {
            JSONObject dataObject = new JSONObject();
            dataObject.put("name", nameList.get(i));
            dataObject.put("value", dataList.get(i));
            dataObject.put("max", maxList.get(i));
            jsonArray.add(dataObject);
        }
        object.put("data", jsonArray);
        object.put("axisMax",getMax(maxList));
        return object.toJSONString();
    }

    private static Double getMax(List<Double> arrayList){
        double axisMax = arrayList.get(0);
        for (int i = 1; i<arrayList.size();i++){
            if (axisMax< arrayList.get(i)){
                axisMax = arrayList.get(i);
            }
        }
        return axisMax;
    }
}

