package com.ruoyi.project.datav.util.readexcel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ReadBarExcel
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/6/15 15:05
 */

public class ReadBarExcel {

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

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < nameList.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("name", nameList.get(i));
            object.put("data", dataList.get(i));
            object.put("axisData", axisDataList);
            jsonArray.add(object);
        }

        return jsonArray.toJSONString();
    }

}
