package com.ruoyi.project.datav.util.readexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadHeatMapExcel {
    public static String read(Sheet sheet) {
        List<JSONObject> heatMapData = new ArrayList<>();
        JSONObject heatMapObject = new JSONObject();

        int rowNum = sheet.getLastRowNum();

        //第一行第二列起,是X轴坐标
        Row xAxisRow = sheet.getRow(0);
        int rowColsNum = xAxisRow.getLastCellNum();
        List<String> xAxisLabelList = Lists.newArrayList();

        for (int i = 1; i < rowColsNum; i++) {
            xAxisRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            String res = xAxisRow.getCell(i).getStringCellValue().trim();
            xAxisLabelList.add(res);
        }

        //第二行起，第一列是Y轴坐标,其余是数据
        List<String> yAxisLabelList = Lists.newArrayList();
        List<Object[]> data = Lists.newArrayList();
        for (int j = 1; j <= rowNum; j++) {
            Row row = sheet.getRow(j);
            for (int k = 0; k < rowColsNum; k++) {
                if ( k == 0 ){
                    row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                    String res = row.getCell(k).getStringCellValue().trim();
                    yAxisLabelList.add(res);
                }
                else {
                    Object[] dataObject =new Object[3];
                    dataObject[0] = xAxisLabelList.get(k-1);
                    dataObject[1] = yAxisLabelList.get(j-1);
                    row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                    Number res = row.getCell(k).getNumericCellValue();
                    dataObject[2] = res;
                    data.add(dataObject);
                }
            }
        }

        heatMapObject.put("xAxisData",xAxisLabelList);
        heatMapObject.put("yAxisData",yAxisLabelList);
        heatMapObject.put("data",data);
        heatMapData.add(heatMapObject);
        return heatMapData.toString();
    }
}
