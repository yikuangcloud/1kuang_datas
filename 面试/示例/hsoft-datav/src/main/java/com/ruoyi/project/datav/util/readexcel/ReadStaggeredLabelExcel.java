package com.ruoyi.project.datav.util.readexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class ReadStaggeredLabelExcel {


    public static String read(Sheet sheet) {
        //解析data集合
        List<Double> dataList = Lists.newArrayList();
        //解析axisData集合
        List<String> axisDataList = Lists.newArrayList();

        //获取行数
        int rowNum = sheet.getLastRowNum();
        
        for (int k = 0; k < rowNum +1; k++) {
        	sheet.getRow(k).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
        	sheet.getRow(k).getCell(1).setCellType(Cell.CELL_TYPE_NUMERIC);
        	//获取第一列为类型
            String res = sheet.getRow(k).getCell(0).getStringCellValue().trim();
            //获取第二列为值
            Double data = sheet.getRow(k).getCell(1).getNumericCellValue();
            axisDataList.add(res);
            dataList.add(data);
        }
        
        JSONObject staggeredLabels = new JSONObject();
        List<JSONObject> labels = new ArrayList<>();
		//构造指定格式数据
        for (int i = 0; i < axisDataList.size(); i++) {
        	JSONObject label = new JSONObject();
        	
			label.put("value", dataList.get(i) != null ? dataList.get(i) :0);
			labels.add(label);
        }
        
		staggeredLabels.put("yAxisData", axisDataList);
		staggeredLabels.put("series", labels);

        return staggeredLabels.toJSONString();
    }


}
