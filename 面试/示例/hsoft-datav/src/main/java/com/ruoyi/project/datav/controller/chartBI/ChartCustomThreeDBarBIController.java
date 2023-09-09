package com.ruoyi.project.datav.controller.chartBI;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.ChartDBInfo;
import com.ruoyi.project.datav.util.DBUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Description: 横向立体柱图
 *
 */
@RestController
@RequestMapping("/chart/BI/customThreeDBar")
public class ChartCustomThreeDBarBIController {

    @PostMapping(value = "analysis")
    public AjaxResult analysis(@RequestBody ChartDBInfo chartDBInfo) {
        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = chartDBInfo.getExecuteSql();
        System.out.println(sql);

        JSONObject returnObject = new JSONObject();
        JSONArray coordinateArr = chartDBInfo.getChartStucture().getJSONArray("coordinate");
        String coordinateStr = JSONObject.toJSONString(coordinateArr, SerializerFeature.WriteClassName);
        List<String> coordinateList = JSONObject.parseArray(coordinateStr, String.class);
        returnObject.put("axisData",coordinateList);

        JSONArray arr = null;
        List<JSONObject> dataList = new ArrayList<>();
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
//            System.out.println("312"+arr+"\n"+coordinateList);

            if(!arr.isEmpty()) {
                JSONObject object = (JSONObject) arr.get(0);
                JSONObject dataObject = new JSONObject();
                ArrayList valueList = new ArrayList();
                for (int i = 0; i < coordinateList.size(); i++) {
                    valueList.add(object.get(coordinateList.get(i)) != null ? object.get(coordinateList.get(i)):0);
//                    Object value = object.get(coordinateList.get(i)) != null ? object.get(coordinateList.get(i)):0;
                }
                dataObject.put( "value",valueList);
                dataObject.put("max",100);
                dataList.add(dataObject);
                returnObject.put("data",dataList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        System.err.println(returnObject);
        return AjaxResult.success(returnObject);
    }
}
