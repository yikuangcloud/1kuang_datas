package com.ruoyi.project.datav.util.readexcel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadCustomThreeDBarExcel {
    public static String read(Sheet sheet){
        //解析data集合
        List<Double> dataList = Lists.newArrayList();
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();
        //获取最大值
        Double max = null;

        //获取行数
        int rowNum = sheet.getLastRowNum();

        for (int k = 0; k < rowNum +1; k++) {
            sheet.getRow(k).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            sheet.getRow(k).getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
            //获取第一列为类型
            String res = sheet.getRow(k).getCell(0).getStringCellValue().trim();
            //获取第二列为值
            Double data = sheet.getRow(k).getCell(1).getNumericCellValue();
            //最后一行为最大值，单独取出
            if (k == rowNum && res.equals("MAX")){max = data;}
            else {
                axisDataList.add(res);
                dataList.add(data);
            }
        }
        JSONObject object = new JSONObject();
        object.put("axisData", axisDataList);

        JSONArray jsonArray = new JSONArray();
        JSONObject dataObject = new JSONObject();
        dataObject.put("value", dataList);
        dataObject.put("max", max);
        jsonArray.add(dataObject);
        object.put("data", jsonArray);
        return object.toJSONString();
    }

}
