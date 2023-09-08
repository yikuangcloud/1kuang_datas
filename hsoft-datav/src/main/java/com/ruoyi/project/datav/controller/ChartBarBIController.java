package com.ruoyi.project.datav.controller;

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
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartBarBIController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/3/25 15:18
 */

@RestController
@RequestMapping("/chart/BI/bar")
public class ChartBarBIController {

    @PostMapping(value = "analysis")
    public AjaxResult analysis(@RequestBody ChartDBInfo chartDBInfo) {

        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = chartDBInfo.getExecuteSql();
        System.out.println(sql);
        List<JSONObject> bars = new ArrayList<>();
        JSONArray legendArr = chartDBInfo.getChartStucture().getJSONArray("legend");
        String legendStr = JSONObject.toJSONString(legendArr, SerializerFeature.WriteClassName);
        List<String> legendList = JSONObject.parseArray(legendStr, String.class);

        JSONArray coordinateArr = chartDBInfo.getChartStucture().getJSONArray("coordinate");
        String coordinateStr = JSONObject.toJSONString(coordinateArr, SerializerFeature.WriteClassName);
        List<String> coordinateList = JSONObject.parseArray(coordinateStr, String.class);

        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);
            for (int i = 0; i < legendList.size(); i++) {
                JSONObject bar = new JSONObject();
                bar.put("name", legendList.get(i));
                List data = new ArrayList<>();
                List<String> axisData = new ArrayList<>();
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject object = (JSONObject) arr.get(j);
                    data.add(object.get(legendList.get(i)));
                    axisData.add((String) object.get("zbwd"));
                }
                bar.put("data", data);
                bar.put("axisData", axisData);
                bars.add(bar);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(bars);
    }

}
