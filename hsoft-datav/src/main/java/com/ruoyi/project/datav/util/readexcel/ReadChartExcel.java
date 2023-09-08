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
 * @ClassName: ReadChartExcel
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/11/30 17:35
 */
public class ReadChartExcel {

    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();

        List<String> nameList = Lists.newArrayList();
        JSONArray jsonArray = new JSONArray();
        //模板从第二行开始的第一列是我们想要的name,剩下的列是我们想要的data
        for (int j = 0; j <= lastRowNum; j++) {
            row = sheet.getRow(j);
            JSONObject obj = new JSONObject();
            for (int k = 0; k < lastCellNum; k++) {
                row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                String res = row.getCell(k).getStringCellValue().trim();
                if(j == 0){

                    nameList.add(res);
                }else{
                    obj.put(nameList.get(k),res);
                }
            }
            if(j > 0){
                jsonArray.add(obj);
            }
        }
        return jsonArray.toJSONString();
    }
}
