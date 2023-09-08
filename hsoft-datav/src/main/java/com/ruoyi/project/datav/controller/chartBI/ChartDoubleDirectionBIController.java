package com.ruoyi.project.datav.controller.chartBI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

@RestController
@RequestMapping("/chart/BI/doubleDirection")
public class ChartDoubleDirectionBIController {
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
        JSONObject doubleyDirec = new JSONObject();
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);

            List<JSONObject> series = new ArrayList<>();
            List<String> axisData = new ArrayList<>();
            for (int i = 0; i < legendList.size(); i++) {
            	JSONObject yData = new JSONObject();
            	yData.put("name", legendList.get(i));
            	List data = new ArrayList<>();
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject object = (JSONObject) arr.get(j);
                    float value = 0;
                    if(object.get(legendList.get(i)) != null) {
                    	value = Float.parseFloat(object.get(legendList.get(i)).toString());
                    }
                    data.add(value);
                    axisData.add((String) object.get("zbwd"));
                }
                yData.put("data", data);
                series.add(yData);
                
            }
            
            Collection collection = new LinkedHashSet(axisData);
            // 转为list
            List list = new ArrayList(collection);
            doubleyDirec.put("xData", list);
            doubleyDirec.put("series", series);
    		

        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(doubleyDirec);
	}
}
