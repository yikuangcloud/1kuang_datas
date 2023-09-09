package com.ruoyi.project.datav.controller.chartBI;

import java.sql.SQLException;
import java.util.*;

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

@RestController
@RequestMapping("/chart/BI/stackedBar")
public class ChartStackedBarBIController {
	@PostMapping(value = "analysis")
    public AjaxResult analysis(@RequestBody ChartDBInfo chartDBInfo) {

        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = chartDBInfo.getExecuteSql();
        System.out.println(sql);
        
        JSONArray legendArr = chartDBInfo.getChartStucture().getJSONArray("legend");
        String legendStr = JSONObject.toJSONString(legendArr, SerializerFeature.WriteClassName);
        List<String> legendList = JSONObject.parseArray(legendStr, String.class);

        JSONArray coordinateArr = chartDBInfo.getChartStucture().getJSONArray("coordinate");
        String coordinateStr = JSONObject.toJSONString(coordinateArr, SerializerFeature.WriteClassName);
        List<String> coordinateList = JSONObject.parseArray(coordinateStr, String.class);
        
        JSONArray categoryArr = chartDBInfo.getChartStucture().getJSONArray("category");
        String categoryStr = JSONObject.toJSONString(categoryArr, SerializerFeature.WriteClassName);
        List<String> categoryList = JSONObject.parseArray(categoryStr, String.class);
        
        
        JSONObject stack = new JSONObject();
        
        
        List<JSONObject> bars = new ArrayList<>();

        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);
            
            if(arr.size() > 0) {

                //按照图例循环
                for (int k = 0; k < legendList.size(); k++) {

                    JSONObject bar = new JSONObject();
                    //名称
                    bar.put("name", legendList.get(k));

                    //建立有序map集合存放按坐标数据顺序的数据值
                    Map<String, Float> hashMap = new LinkedHashMap<String, Float>();

                    //数值列表
                    List<Float> ds = Lists.newArrayList();

                    //遍历结果集
                    for (int i = 0; i < arr.size(); i++) {

                        JSONObject object = (JSONObject) arr.get(i);

                        float value = 0;

                        //遍历坐标取出符合该坐标的数据行
                        for (int j = 0; j < coordinateList.size(); j++) {


                            if (object.get("zbwd").equals(coordinateList.get(j)) && object.get(legendList.get(k)) != null) {

                                //获取数据
                                value = Float.parseFloat(object.get(legendList.get(k)).toString());

                                //设置堆叠名称
                                if(value > 0){
                                    bar.put("stack", (String) object.get("flwd"));
                                }
                                //加入map
                                hashMap.put(coordinateList.get(j),value);

                            }

                        }

                    }
                    //按照map数据顺序存放数据列表
                    Set<Map.Entry<String, Float>> set = hashMap.entrySet();
                    Iterator<Map.Entry<String, Float>> iterator = set.iterator();
                    while(iterator.hasNext()) {
                        Map.Entry<String, Float> entry = iterator.next();
                        Float value = (Float) entry.getValue();
                        ds.add(value);
                    }

                    //加入一组图例数据
                    bar.put("data", ds);
                    bars.add(bar);
                }

            }

            //构建堆叠数据结构
            stack.put("axisData", coordinateList);
            stack.put("series", bars);


        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(stack);
    }


}
