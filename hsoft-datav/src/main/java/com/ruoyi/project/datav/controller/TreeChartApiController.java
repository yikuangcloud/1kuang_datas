package com.ruoyi.project.datav.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.Carbon;
import com.ruoyi.project.datav.domain.TreeDto;
import com.ruoyi.project.datav.service.TreeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartApiController
 * @Description: 图表组件API测试接口
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/1/18 10:00
 */
@Api(value = "图表组件API测试接口", tags = { "图表组件API测试接口" })
@CrossOrigin
@RestController
@RequestMapping("/chart/api/tree")
public class TreeChartApiController {
	
	@Autowired
	private TreeService treeService;

	@ApiOperation(value = "柱状图接口数据结构", response = AjaxResult.class)
	@PostMapping(value = "testBar")
	public AjaxResult carBarTest(@RequestBody JSONObject car) {

		String type = car.getString("car");
		List<JSONObject> bars = new ArrayList<>();
		String[] axisData = { "No1", "No2", "No3", "No4", "No5", "No6"};
		if(type.equals("1")){
			
			JSONObject bar = new JSONObject();			
			String name = "test1";
			bar.put("name", name);
			int[] data = { 1, 3,5, 1, 6, 1 };
			bar.put("data", data);
			
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("2")){
			
			JSONObject bar = new JSONObject();			
			String name = "test2";
			bar.put("name", name);
			int[] data = { 3, 2, 1, 2, 5,9};
			bar.put("data", data);
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("3")){
			
			JSONObject bar = new JSONObject();			
			String name = "test3";
			bar.put("name", name);
			int[] data = { 2,1,1 ,1,5,4};
			bar.put("data", data);
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("4")){
			
			JSONObject bar = new JSONObject();			
			String name = "test4";
			bar.put("name", name);
			int[] data = { 1, 1, 1, 1,4,7};
			bar.put("data", data);
			bar.put("axisData", axisData);
			bars.add(bar);
		}
		else{
			JSONObject bar1 = new JSONObject();
			String name1 = "2020年";
			bar1.put("name", name1);
			int[] data1 = { 19325, 23438, 31000, 121594, 134141, 681807 };
			bar1.put("data", data1);
			String[] axisData1 = { "巴西", "印尼", "美国", "印度", "中国", "世界人口(万)" };
			bar1.put("axisData", axisData1);
			bars.add(bar1);
			
		}
		
		return AjaxResult.success(bars);
	}
	
	@RequestMapping(value = "treeBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeBarTest(@RequestBody JSONObject tree) {

		String type = tree.getString("tree");
		List<JSONObject> bars = new ArrayList<>();
		if(type.equals("1")){
			
			JSONObject bar = new JSONObject();			
			String name = "柠条";
			bar.put("name", name);
			int[] data = { 1, 1, 1, 1, 1, 1,1,1 };
			bar.put("data", data);
			String[] axisData = { "Han", "Ying", "Ma", "Lin", "Qing", "You" ,"Hu","Long" };
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("2")){
			
			JSONObject bar = new JSONObject();			
			String name = "梭梭树";
			bar.put("name", name);
			int[] data = { 3, 2, 1, 2, 1,1, 2,1,1,2,1,2,3,2};
			bar.put("data", data);
			String[] axisData = { "Han", "Lin", "Qing", "You" ,"Hui","Wu","Qiang","Hu","Long","Feng","Tang","Wen","Qi","Yang"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("3")){
			
			JSONObject bar = new JSONObject();			
			String name = "沙柳";
			bar.put("name", name);
			int[] data = { 3, 1, 2, 1, 1, 1,2,1,1 ,1,1};
			bar.put("data", data);
			String[] axisData = { "Han",  "Ma", "Lin", "Li", "Qing" ,"You","Hui","Long","Wen","Jing","Yang"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("4")){
			
			JSONObject bar = new JSONObject();			
			String name = "花棒";
			bar.put("name", name);
			int[] data = { 1, 1, 1, 1};
			bar.put("data", data);
			String[] axisData = { "Han", "Li", "Qiang", "Long"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("5")){
			
			JSONObject bar = new JSONObject();			
			String name = "沙棘";
			bar.put("name", name);
			int[] data = { 1, 1, 1, 1,1};
			bar.put("data", data);
			String[] axisData = { "Ying", "Qing", "Hu", "Wen","Jing"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("6")){
			
			JSONObject bar = new JSONObject();			
			String name = "胡杨";
			bar.put("name", name);
			int[] data = { 1, 1, 1};
			bar.put("data", data);
			String[] axisData = { "Ying", "Lin", "Feng"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("7")){
			
			JSONObject bar = new JSONObject();			
			String name = "樟子松";
			bar.put("name", name);
			int[] data = { 1, 1, 1,1};
			bar.put("data", data);
			String[] axisData = { "Ma", "Li", "Qing","Hui"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("8")){
			
			JSONObject bar = new JSONObject();			
			String name = "侧柏";
			bar.put("name", name);
			int[] data = { 1};
			bar.put("data", data);
			String[] axisData = {"Qing"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("9")){
			
			JSONObject bar = new JSONObject();			
			String name = "油松";
			bar.put("name", name);
			int[] data = { 1};
			bar.put("data", data);
			String[] axisData = {"Qing"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("10")){
			
			JSONObject bar = new JSONObject();			
			String name = "红柳";
			bar.put("name", name);
			int[] data = { 1,1,1};
			bar.put("data", data);
			String[] axisData = {"You","Qiang","Jing"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}else if(type.equals("11")){
			
			JSONObject bar = new JSONObject();			
			String name = "山杏";
			bar.put("name", name);
			int[] data = { 1};
			bar.put("data", data);
			String[] axisData = {"Jing"};
			bar.put("axisData", axisData);
			bars.add(bar);
		}
		else{
			JSONObject bar1 = new JSONObject();
			String name1 = "2020年";
			bar1.put("name", name1);
			int[] data1 = { 19325, 23438, 31000, 121594, 134141, 681807 };
			bar1.put("data", data1);
			String[] axisData1 = { "巴西", "印尼", "美国", "印度", "中国", "世界人口(万)" };
			bar1.put("axisData", axisData1);
			bars.add(bar1);
			
		}
		
		return AjaxResult.success(bars);
	}
	
	@RequestMapping(value = "treeStacked", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeStackedTest(@RequestBody JSONObject modules) {
		System.out.println(modules.toJSONString());
		String[] nameArr = modules.getString("modules").split(",");
		int length = nameArr.length;
		List<JSONObject> bars = new ArrayList<>();
		
		for (int i=0;i<length;i++) {
			JSONObject bar = new JSONObject();			
			bar.put("name", nameArr[i]);
			if(nameArr[i].equals("Han")){
				int[] data = { 1, 3, 3, 1};
				bar.put("data", data);
				String[] axisData = { "柠条", "梭梭树", "沙柳", "花棒"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Ying")){
				int[] data = { 1, 1, 1};
				bar.put("data", data);
				String[] axisData = { "柠条", "沙棘", "胡杨"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Bin")){
				int[] data = {};
				bar.put("data", data);
				String[] axisData = {};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Ma")){
				int[] data = { 1, 1, 1};
				bar.put("data", data);
				String[] axisData = { "柠条", "沙柳", "花棒"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Lin")){
				int[] data = { 1, 2, 2, 1};
				bar.put("data", data);
				String[] axisData = { "柠条", "梭梭树", "沙柳", "胡杨"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Li")){
				int[] data = { 1,1,1};
				bar.put("data", data);
				String[] axisData = { "沙柳", "花棒","樟子松"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Qing")){
				int[] data = { 1, 1, 1, 1,1,1,1};
				bar.put("data", data);
				String[] axisData = { "柠条", "梭梭树","沙棘", "沙柳", "侧柏","油松","樟子松"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("You")){
				int[] data = { 1, 2, 1,1};
				bar.put("data", data);
				String[] axisData = { "柠条", "梭梭树", "沙柳","红柳"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Hui")){
				int[] data = { 1, 2, 1};
				bar.put("data", data);
				String[] axisData = { "梭梭树", "沙柳", "樟子松"};
				bar.put("axisData", axisData);
			}else if(nameArr[i].equals("Wu")){
				int[] data = { 1};
				bar.put("data", data);
				String[] axisData = {"梭梭树"};
				bar.put("axisData", axisData);
				
			}
			bars.add(bar);
		}
		
		
		
		return AjaxResult.success(bars);
	}
	
	@RequestMapping(value = "treeStackedBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeStackedBarTest(@RequestBody JSONObject modules) {
		
		String jsonArr = modules.getString("modules");
		  JSONObject stackedBar = new JSONObject();
		  String[] name = {"柠条", "梭梭树", "沙柳", "花棒", "沙棘", "胡杨","樟子松","侧柏","油松","红柳","山杏"};
		  
		  if(jsonArr != null && !jsonArr.isEmpty()) {
		   String[] nameArr = modules.getString("modules").split(",");
		   int length = nameArr.length;
		   String[] axisData = nameArr;
		  
		   int[] hans = {1,3,3,1,0,0,0,0,0,0,0};
		   int[] yings = {1,0,0,0,1,1,0,0,0,0,0};
		   int[] bins = {0,0,0,0,0,0,0,0,0,0,0};
		   int[] mas = {1,0,1,0,0,0,1,0,0,0,0};
		   int[] lins = {1,2,2,0,0,1,0,0,0,0,0};
		   int[] lis = {0,0,1,1,0,0,1,0,0,0,0};
		   int[] qings = {1,1,1,0,1,0,1,1,1,0,0};
		   int[] yous = {1,2,1,0,0,0,0,0,0,1,0};
		   int[] huis = {0,1,2,0,0,0,1,0,0,0,0};
		   int[] wus = {0,1,0,0,0,0,0,0,0,0,0};
		   int[][] data = new int[11][length];
		   for(int i=0;i<length;i++){
		    
		    for(int j=0;j<11;j++) {
		     if(nameArr[i].equals("Han")){
		      data[j][i] = hans[j];
		     }else if(nameArr[i].equals("Ying")) {
		      data[j][i] = yings[j] ;
		     }else if(nameArr[i].equals("Bin")) {
		      data[j][i] = bins[j];
		     }else if(nameArr[i].equals("Ma")) {
		      data[j][i] = mas[j];
		     }else if(nameArr[i].equals("Lin")) {
		      data[j][i] = lins[j];
		     }else if(nameArr[i].equals("Li")) {
		      data[j][i] = lis[j];
		     }else if(nameArr[i].equals("Qing")) {
		      data[j][i] = qings[j];
		     }else if(nameArr[i].equals("You")) {
		      data[j][i] = yous[j];
		     }else if(nameArr[i].equals("Hui")) {
		      data[j][i] = huis[j];
		     }else if(nameArr[i].equals("Wu")) {
		      data[j][i] = wus[j];
		     }
		    }
		    
		   }
		   
		//组装数据
		stackedBar.put("axisData", axisData);
		List<JSONObject> series = new ArrayList<>();
		for(int i = 0; i<name.length; i++) {
			JSONObject temp = new JSONObject();
			temp.put("name", name[i]);
			temp.put("data", data[i]);
			temp.put("stack", "Han");
			series.add(temp);
		}
		stackedBar.put("series", series);
	 }else{
		 String[] axisData = {"Han"};
		 stackedBar.put("axisData",axisData );
		 List<JSONObject> series = new ArrayList<>();
		 int[] hans = {1,3,3,1,0,0,0,0,0,0,0};
		 int[][] data = new int[11][1];
		 for(int j=0;j<11;j++) {
			 data[j][0] = hans[j];
		 }
		 for(int i = 0; i<name.length; i++) {
			JSONObject temp = new JSONObject();
			temp.put("name", name[i]);
			temp.put("data", data[i]);
			temp.put("stack", "Han");
			series.add(temp);
		}
		 stackedBar.put("series", series);
	 }
		return AjaxResult.success(stackedBar);
	
	}
	
	@RequestMapping(value = "treeRadar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeRadarTest(@RequestBody JSONObject modules) {
		
		String jsonArr = modules.getString("modules");
		String[] treeNames = {"柠条", "梭梭树", "沙柳", "花棒", "沙棘", "胡杨","樟子松","侧柏","油松","红柳","山杏"};
		
		List<JSONObject> radarList = new ArrayList<>();
		
		List<JSONObject> indicator = new ArrayList<>();
		for(int i = 0;i < 11;i++) {
			JSONObject obj = new JSONObject();
			obj.put("name", treeNames[i]);
			obj.put("max", 3);
			indicator.add(obj);
		}
		
		int[] hans = {1,3,3,1,0,0,0,0,0,0,0};
	    int[] yings = {1,0,0,0,1,1,0,0,0,0,0};
	    int[] bins = {0,0,0,0,0,0,0,0,0,0,0};
	    int[] mas = {1,0,1,0,0,0,1,0,0,0,0};
	    int[] lins = {1,2,2,0,0,1,0,0,0,0,0};
	    int[] lis = {0,0,1,1,0,0,1,0,0,0,0};
	    int[] qings = {1,1,1,0,1,0,1,1,1,0,0};
	    int[] yous = {1,2,1,0,0,0,0,0,0,1,0};
	    int[] huis = {0,1,2,0,0,0,1,0,0,0,0};
	    int[] wus = {0,1,0,0,0,0,0,0,0,0,0};
		
		if(jsonArr != null && !jsonArr.isEmpty()) {
		   String[] nameArr = modules.getString("modules").split(",");
		   int length = nameArr.length;
		   for(int i=0;i<length;i++){
			   JSONObject radar = new JSONObject();
			   radar.put("name", nameArr[i]);
				int[] data = new int[11];
				for(int j = 0;j < 11;j++) {
					 if(nameArr[i].equals("Han")){
						data[j] = hans[j];
				     }else if(nameArr[i].equals("Ying")) {
				    	 data[j] = yings[j] ;
				     }else if(nameArr[i].equals("Bin")) {
				      data[j] = bins[j];
				     }else if(nameArr[i].equals("Ma")) {
				      data[j] = mas[j];
				     }else if(nameArr[i].equals("Lin")) {
				      data[j] = lins[j];
				     }else if(nameArr[i].equals("Li")) {
				      data[j] = lis[j];
				     }else if(nameArr[i].equals("Qing")) {
				      data[j] = qings[j];
				     }else if(nameArr[i].equals("You")) {
				      data[j] = yous[j];
				     }else if(nameArr[i].equals("Hui")) {
				      data[j] = huis[j];
				     }else if(nameArr[i].equals("Wu")) {
				      data[j] = wus[j];
				     }
					
					
				}
//				int[] data = { 430, 1000, 2800, 2100, 5000, 1900 };
				radar.put("value", data);
				radarList.add(radar);
		   }
		   
		}
		
		
//		JSONObject radar1 = new JSONObject();
//		JSONObject radar2 = new JSONObject();
//		radar1.put("name", "预算分配");
//		int[] data1 = new int[6];
//		for(int i = 0;i < 6;i++) {
//			data1[i] = (int) (Math.random() * 100) +2;
//		}
////		int[] data = { 430, 1000, 2800, 2100, 5000, 1900 };
//		radar1.put("value", data1);
//		radarList.add(radar1);
//
//		radar2.put("name", "实际开销");
//		int[] data2 = new int[6]; 
//		for(int i = 0;i < 6;i++) {
//			data2[i] = (int) (Math.random() * 100) +2 ;
//		}
////		int[] data1 = { 5000, 1400, 2800, 3000, 4200, 2100 };
//		radar2.put("value", data2);
//		radarList.add(radar2);
		
		
		List<List<JSONObject>> result = new ArrayList<>();
		result.add(radarList);
		result.add(indicator);

		return AjaxResult.success(result);
	}
	
	@RequestMapping(value = "treeLine", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeLineTest(@RequestBody JSONObject modules) {
		
		String jsonArr = modules.getString("modules");
		String[] treeNames = {"柠条", "梭梭树", "沙柳", "花棒", "沙棘", "胡杨","樟子松","侧柏","油松","红柳","山杏"};
		
		int[] hans = {1,3,3,1,0,0,0,0,0,0,0};
	    int[] yings = {1,0,0,0,1,1,0,0,0,0,0};
	    int[] bins = {0,0,0,0,0,0,0,0,0,0,0};
	    int[] mas = {1,0,1,0,0,0,1,0,0,0,0};
	    int[] lins = {1,2,2,0,0,1,0,0,0,0,0};
	    int[] lis = {0,0,1,1,0,0,1,0,0,0,0};
	    int[] qings = {1,1,1,0,1,0,1,1,1,0,0};
	    int[] yous = {1,2,1,0,0,0,0,0,0,1,0};
	    int[] huis = {0,1,2,0,0,0,1,0,0,0,0};
	    int[] wus = {0,1,0,0,0,0,0,0,0,0,0};
	    
	    List<JSONObject> lines = Lists.newArrayList();
	    
		if(jsonArr != null && !jsonArr.isEmpty()) {
			   String[] nameArr = modules.getString("modules").split(",");
			   int length = nameArr.length;
			   for(int i=0;i<length;i++){
				    JSONObject line = new JSONObject();
				   	String name = nameArr[i];
					line.put("name", name);
					int[] data = new int[11];
					for(int j = 0;j < 11;j++) {
						 if(nameArr[i].equals("Han")){
							data[j] = hans[j];
					     }else if(nameArr[i].equals("Ying")) {
					    	 data[j] = yings[j] ;
					     }else if(nameArr[i].equals("Bin")) {
					      data[j] = bins[j];
					     }else if(nameArr[i].equals("Ma")) {
					      data[j] = mas[j];
					     }else if(nameArr[i].equals("Lin")) {
					      data[j] = lins[j];
					     }else if(nameArr[i].equals("Li")) {
					      data[j] = lis[j];
					     }else if(nameArr[i].equals("Qing")) {
					      data[j] = qings[j];
					     }else if(nameArr[i].equals("You")) {
					      data[j] = yous[j];
					     }else if(nameArr[i].equals("Hui")) {
					      data[j] = huis[j];
					     }else if(nameArr[i].equals("Wu")) {
					      data[j] = wus[j];
					     }
						
						
					}
					line.put("data", data);
					line.put("axisData", treeNames);
					lines.add(line);
				   
			   }
		}else{
			JSONObject line = new JSONObject();
			line.put("name", "Han");
			line.put("data", hans);
			line.put("axisData", treeNames);
			lines.add(line);
		}
		
		return AjaxResult.success(lines);
	}
	
	@RequestMapping(value = "treeLineBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeLineBarTest(@RequestBody TreeDto treeDto) {
		
		String startDate = "";
		String endDate = "";
		
		if(!StringUtils.isEmpty(treeDto.getTime())&&!treeDto.getTime().equals("null")){
			startDate = treeDto.getTime().split(",")[0];
			endDate = treeDto.getTime().split(",")[1];
		}
		treeDto.setStartDate(startDate);
		treeDto.setEndDate(endDate);
		List<Carbon> treeList = treeService.findList(treeDto);
		
		JSONObject lineBarDto = new JSONObject();
		String[] xAxis = new String[treeList.size()];
		float[] data1 = new float[treeList.size()];
		float[] data3 = new float[treeList.size()];
		
		List<JSONObject> lines = Lists.newArrayList();
		JSONObject line1 = new JSONObject();
		JSONObject line3 = new JSONObject();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//默认输出格式
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");//显示2017-10-27格式
				
		for(int i=0;i<treeList.size();i++){
			
			try {
				Date date=simpleDateFormat.parse(treeList.get(i).getDate());
				String time = sdf.format(date);
				xAxis[i] = time;
				data1[i] = treeList.get(i).getTurnover();
				data3[i] = treeList.get(i).getPrice();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		lineBarDto.put("xAxixData", xAxis);
		
		if(treeDto.getCity().equals("北京")){
			line1.put("name", "BEA成交量");
			line3.put("name", "BEA成交价");
		}else if(treeDto.getCity().equals("上海")){
			line1.put("name", "SHEA成交量");
			line3.put("name", "SHEA成交价");
		}else if(treeDto.getCity().equals("广东")){
			line1.put("name", "GDEA成交量");
			line3.put("name", "GDEA成交价");
		}else if(treeDto.getCity().equals("天津")){
			line1.put("name", "TJEA成交量");
			line3.put("name", "TJEA成交价");
		}else if(treeDto.getCity().equals("深圳")){
			line1.put("name", "SZA-2018成交量");
			line3.put("name", "SZA-2018成交价");
		}else if(treeDto.getCity().equals("湖北")){
			line1.put("name", "HBEA成交量");
			line3.put("name", "HBEA成交价");
		}else if(treeDto.getCity().equals("重庆")){
			line1.put("name", "CQEA成交量");
			line3.put("name", "CQEA成交价");
		}

		
		line1.put("type", "bar");
		line1.put("data", data1);
		lines.add(line1);

		
		line3.put("type", "line");
		line3.put("data", data3);
		lines.add(line3);

		lineBarDto.put("yAxisData", lines);

		return AjaxResult.success(lineBarDto);
	}
	
	@ApiOperation(value = "翻牌器接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "treeflop", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeFlopTest() {
		
		int max=600;
        int min=500;
        Random random = new Random();

        double s = random.nextInt(max)%(max-min+1) + min + Math.random() * 10;

		//double value =(double) Math.random() * 1000 + Math.random() * 100 + Math.random() * 10;

		return AjaxResult.success(s);
	}
}
