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
import com.google.common.collect.Lists;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.ChartDBInfo;
import com.ruoyi.project.datav.util.DBUtil;
/**
 * 
 * @Description: 双柱状图
 *
 */
@RestController
@RequestMapping("/chart/BI/doubleBar")
public class ChartDoubleBarBIController {

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

        //JSONArray coordinateArr = chartDBInfo.getChartStucture().getJSONArray("coordinate");
        //String coordinateStr = JSONObject.toJSONString(coordinateArr, SerializerFeature.WriteClassName);
        //List<String> coordinateList = JSONObject.parseArray(coordinateStr, String.class);

        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);
            
            if(!arr.isEmpty()) {
            	
            	for (int i = 0; i < legendList.size(); i++) {
                    JSONObject bar = new JSONObject();
                    bar.put("name", legendList.get(i));
                    //List<Object> data = new ArrayList<>();
                    List<Object> value = new ArrayList<>();
                    List<String> lable = new ArrayList<>();
                    for (int j = 0; j < arr.size(); j++) {
                        JSONObject object = (JSONObject) arr.get(j);
                        value.add(object.get(legendList.get(i)));
                        lable.add((String) object.get("zbwd"));
                    }
                    List<JSONObject> jsonObjects = Lists.newArrayList();
                    for(int k = 0; k < value.size(); k++) {
                    	String data = "{value:"+ value.get(k) + ",label:'" + lable.get(k) + "'}";
                    	JSONObject jsonObject = JSONObject.parseObject(data); // json字符串变成json对象
            			jsonObjects.add(jsonObject);
                    }
                    bar.put("data", jsonObjects);
                    bars.add(bar);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        
        System.err.println(bars);
        return AjaxResult.success(bars);
    }
	
}
