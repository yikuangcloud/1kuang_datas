package com.ruoyi.project.datav.util.readexcel;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ReadRateBarExcel
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/5/6 16:30
 */
public class ReadRateBarExcel {
    public static String read(Sheet sheet) {
        //获取一个sheet有多少Row
        int lastRowNum = sheet.getLastRowNum();
        Row row ;
        //创建返回对象
        JSONObject rateBar = new JSONObject();
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
        rateBar.put("axisData",axisDataList);

        //创建数据返回对象
        JSONObject data = new JSONObject();

        //模板从第二行开始的第一列是我们想要的name,剩下的列是我们想要的data
        for (int j = 1; j <= 2; j++) {
            row = sheet.getRow(j);

            List<Double> ds = Lists.newArrayList();
            for (int k = 1; k < lastCellNum; k++) {

                row.getCell(k).setCellType(Cell.CELL_TYPE_NUMERIC);
                Double res = row.getCell(k).getNumericCellValue();
                ds.add(res);
            }
            if(j == 1){
                data.put("total",ds);
            }else if(j == 2){
                data.put("value",ds);
            }

        }
        rateBar.put("data",data);

        return rateBar.toJSONString();
    }
}
