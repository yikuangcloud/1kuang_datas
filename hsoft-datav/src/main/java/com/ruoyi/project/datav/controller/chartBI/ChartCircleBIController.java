package com.ruoyi.project.datav.controller.chartBI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/chart/BI/circle")
public class ChartCircleBIController {
	@PostMapping(value = "analysis")
    public AjaxResult analysis(@RequestBody ChartDBInfo chartDBInfo) {

        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = chartDBInfo.getExecuteSql();
        System.out.println(sql);
        

        JSONArray coordinateArr = chartDBInfo.getChartStucture().getJSONArray("coordinate");
        String coordinateStr = JSONObject.toJSONString(coordinateArr, SerializerFeature.WriteClassName);
        List<String> coordinateList = JSONObject.parseArray(coordinateStr, String.class);

        JSONArray arr = null;
		List<JSONObject> circles = new ArrayList<>();
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);
            
            if(!arr.isEmpty()) {
	            JSONObject object = (JSONObject) arr.get(0);
	    		
	            for (int i = 0; i < coordinateList.size(); i++) {
	    			
	    			JSONObject circle = new JSONObject();
	    			Object value = object.get(coordinateList.get(i)) != null ? object.get(coordinateList.get(i)):0;
	    			
	    			circle.put("value", Float.parseFloat(value.toString()) );
	    			circle.put("name", coordinateList.get(i));
	
	    			circles.add(circle);
	    			
	            }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(circles);
    }
}
