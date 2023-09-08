package com.ruoyi.project.datav.controller.chartBI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
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
@RequestMapping("/chart/BI/bartime")
public class ChartBarTimeBIController {
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
        
        List<JSONObject> bartimes = new ArrayList<>();

        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            System.out.println(arr);
            
            
            //按照分类字段做时间轴的分页
            for (int i = 0; i < categoryList.size(); i++) {
            	JSONObject barTime = new JSONObject();
            	barTime.put("name", categoryList.get(i));
            	
            	//遍历执行sql获取的集合，将满足该分页条件的对象添加进结果集中
            	List resultList = new ArrayList<>();
            	for (int j = 0; j < arr.size(); j++) {
                  JSONObject object = (JSONObject) arr.get(j);
                  
                  if(((String) object.get("flwd")).equals(categoryList.get(i))){
                	  
                	  resultList.add(object);
                  }
                  
            	}
            	List<String> label = new ArrayList<>();
            	//遍历图标列表
            	for(int m=0;m<legendList.size();m++){
            		
            		JSONObject bar = new JSONObject();
            		bar.put("name", legendList.get(m));
            		List data = new ArrayList<>();
            		
            		//遍历坐标列表
            		for(int n=0;n<coordinateList.size();n++){
            			//设置标识为false
            			boolean flag = false;
            			//遍历满足该分类条件的结果集
            			for(int k=0;k<resultList.size();k++){	
            				
            				//获取结果集中的每一个对象
    	               		JSONObject object=(JSONObject) JSONObject.toJSON(resultList.get(k));
    	               		//如果该对象的坐标值与该循环下的坐标值相等，则在数据值中加入该对象的值
    	               		if(object.get("zbwd").equals(coordinateList.get(n))){
    	               			flag = true;
    	               			float value = 0;
        	                    if(object.get(legendList.get(m)) != null) {
        	                    	value = Float.parseFloat(object.get(legendList.get(m)).toString());
        	                    }
        	                    data.add(value);
        	                    label.add((String) object.get("zbwd"));
    	               		}
    	               		  
    	               	}
            			//如果遍历结果集都不满足该循环下的坐标值，则在数据中放入0
            			if(!flag){
            				data.add(0);
            				label.add(coordinateList.get(n));
            			}
            			//创建该坐标分类的结果
    	               	bar.put("data", data);
    	               	//放入该图例条件下的结果
    	               	barTime.put("data"+(m+1), bar);
            			
            		}
	               	  
	            }
            	//循环的过程中坐标字段的列表会循环出现多次，按顺序去重只保留第一个结果
            	 Collection collection = new LinkedHashSet(label);
                 // 转为list
                 List list = new ArrayList(collection);
                 
                 barTime.put("label", list);
                 //最后在结果中放入该分页条件下的结果
                 bartimes.add(barTime);
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(bartimes);
    }
}
