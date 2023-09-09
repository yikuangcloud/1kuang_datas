package com.ruoyi.project.datav.controller.chartBI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.ObjectExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.ChartDBInfo;
import com.ruoyi.project.datav.util.DBUtil;
/**
 *
 * @Description: 坐标系热力图
 *
 */
@RestController
@RequestMapping("/chart/BI/heatMap")
public class ChartHeatMapBIController {
    @PostMapping(value = "analysis")
    public AjaxResult analysis(@RequestBody ChartDBInfo chartDBInfo) {

        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = chartDBInfo.getExecuteSql();
        System.out.println(sql);
        List<JSONObject> heatMapData = new ArrayList<>();
        JSONObject heatMapObject = new JSONObject();
        JSONArray xAxisLabelArr = chartDBInfo.getChartStucture().getJSONArray("legend");
        String xAxisLabelStr = JSONObject.toJSONString(xAxisLabelArr, SerializerFeature.WriteClassName);
        List<String> xAxisLabelList = JSONObject.parseArray(xAxisLabelStr, String.class);

        JSONArray yAxisLabelArr = chartDBInfo.getChartStucture().getJSONArray("coordinate");
        String yAxisLabelStr = JSONObject.toJSONString(yAxisLabelArr, SerializerFeature.WriteClassName);
        List<String> yAxisLabelList = JSONObject.parseArray(yAxisLabelStr, String.class);

        JSONArray arr = null;
        Object[] data = new Object[xAxisLabelList.size() * yAxisLabelList.size()];
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);
            int dataIndex = 0;
            for (int i = 0; i < arr.size(); i++) {
                JSONObject object = (JSONObject) arr.get(i);
                for (int j = 0; j < xAxisLabelList.size(); j++) {
                    Object[] cellData = new Object[3];
                    cellData[0] = xAxisLabelList.get(j);
                    cellData[1] = object.get("zbwd");
                    Object resValue = object.get(xAxisLabelList.get(j));
                    if (resValue == null){
                        cellData[2] = 0;
                    }
                    else {
                        cellData[2] = Double.parseDouble(resValue.toString());
                    }
                    data[dataIndex] = cellData;
                    dataIndex++;
                }
            }
            heatMapObject.put("xAxisData",xAxisLabelList);
            heatMapObject.put("yAxisData",yAxisLabelList);
            heatMapObject.put("data",data);
            heatMapData.add(heatMapObject);
        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }

        return AjaxResult.success(heatMapData);
    }
}
