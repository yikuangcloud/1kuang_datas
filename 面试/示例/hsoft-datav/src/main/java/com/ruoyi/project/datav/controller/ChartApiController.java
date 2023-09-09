package com.ruoyi.project.datav.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ruoyi.framework.netty.ChatHandler;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
@RequestMapping("/chart/api")
public class ChartApiController {

	@Autowired
	private ChatHandler chatHandler;

	@ApiOperation(value = "柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "bar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult barTest() {
		List<JSONObject> bars = new ArrayList<>();
		JSONObject bar = new JSONObject();
		JSONObject bar1 = new JSONObject();
		String name = "2019年";
		bar.put("name", name);
		int[] data = { 18203, 23489, 29034, 104970, 131744, 630230 };
		bar.put("data", data);
		String[] axisData = { "巴西", "印尼", "美国", "印度", "中国", "世界人口(万)" };
		bar.put("axisData", axisData);
		bars.add(bar);

		String name1 = "2020年";
		bar1.put("name", name1);
		int[] data1 = { 19325, 23438, 31000, 121594, 134141, 681807 };
		bar1.put("data", data1);
		String[] axisData1 = { "巴西", "印尼", "美国", "印度", "中国", "世界人口(万)" };
		bar1.put("axisData", axisData1);
		bars.add(bar1);

		return AjaxResult.success(bars);
	}

	@ApiOperation(value = "交错正负轴标签图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "staggered", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult staggeredLabelTest() {

		JSONObject staggeredLabels = new JSONObject();
		List<JSONObject> labels = new ArrayList<>();

		String[] category = { "大米", "玉米", "蔬菜", "鸡蛋", "坚果", "土豆", "冬瓜", "蘑菇" };
		for (int i = 0; i < 8; i++) {
			JSONObject label = new JSONObject();
			label.put("value", String.format("%.2f", (Math.random()) * (Math.random() > 0.5 ? 1 : -1)));
			labels.add(label);

		}

		staggeredLabels.put("yAxisData", category);
		staggeredLabels.put("series", labels);

		return AjaxResult.success(staggeredLabels);
	}

	@ApiOperation(value = "双柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "doublebar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult doubleBarTest() {
		JSONObject res = new JSONObject();

		JSONObject bar = new JSONObject();
		JSONObject bar1 = new JSONObject();
		String name = "2019年";
		bar.put("name", name);
		String name1 = "2020年";
		bar1.put("name", name1);

		List<JSONObject> bars = new ArrayList<>();
		List<JSONObject> bars1 = new ArrayList<>();

		int rowY = (int) (Math.random() * 6) + 2;
		for (int i = 0; i < rowY; i++) {
			JSONObject data = new JSONObject();
			JSONObject data1 = new JSONObject();
			int value = (int) (Math.random() * 100) + 2;
			data.put("value", value);
			data.put("label", "label" + i);
			bars.add(data);
			int value1 = (int) (Math.random() * 100) + 2;
			data1.put("value", value1);
			data1.put("label", "label" + i);
			bars1.add(data1);
		}
		bar.put("data", bars);
		bar1.put("data", bars1);
		res.put("a1", bar);
		res.put("a2", bar1);
		return AjaxResult.success(res);
	}

	@ApiOperation(value = "排行榜接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "topbar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult topBarTest() {

		List<JSONObject> tops = new ArrayList<>();
		int rowY = (int) (Math.random() * 10) + 2;
		for (int i = 0; i < rowY; i++) {
			JSONObject top = new JSONObject();
			int value = (int) (Math.random() * 100);
			top.put("value", value);
			String name = "排行" + i;
			top.put("name", name);

			tops.add(top);
		}

		return AjaxResult.success(tops);
	}

	@ApiOperation(value = "水平柱图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "parallebar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult paralleParTest() {

		List<JSONObject> tops = new ArrayList<>();
		int rowY = (int) (Math.random() * 10) + 2;
		for (int i = 0; i < rowY; i++) {
			JSONObject top = new JSONObject();
			int value = (int) (Math.random() * 100);
			top.put("value", value);
			String name = "数据" + i;
			top.put("name", name);

			tops.add(top);
		}

		return AjaxResult.success(tops);
	}

	@ApiOperation(value = "双y轴柱图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "doubleyAxis", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult doubleyAxisTest() {

		JSONObject doubleyAxis = new JSONObject();

		String[] categoryData = { "鸡蛋", "坚果", "土豆", "冬瓜", "蘑菇" };
		double[] data = new double[5];
		for (int i = 0; i < 5; i++) {

			data[i] = Math.round(Math.random() * 5000);
		}
		doubleyAxis.put("yAxisData", categoryData);
		doubleyAxis.put("series", data);
		return AjaxResult.success(doubleyAxis);
	}

	@ApiOperation(value = "双向柱形图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "doubleDirectionBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult doubleDirectionBarTest() {

		JSONObject doubleyDirec = new JSONObject();

		String[] categoryData = { "鸡蛋", "坚果", "土豆", "冬瓜", "蘑菇" };
		doubleyDirec.put("xData", categoryData);
		double[] leftdata = new double[5];
		double[] rightdata = new double[5];
		for (int i = 0; i < 5; i++) {

			leftdata[i] = Math.round(Math.random() * 10);
			rightdata[i] = Math.round(Math.random() * 10);
		}
		List<JSONObject> series = new ArrayList<>();
		JSONObject yData1 = new JSONObject();
		yData1.put("name", "2020");
		yData1.put("data", leftdata);
		series.add(yData1);
		JSONObject yData2 = new JSONObject();
		yData2.put("name", "2021");
		yData2.put("data", rightdata);
		series.add(yData2);
		doubleyDirec.put("series", series);
		return AjaxResult.success(doubleyDirec);
	}

	@ApiOperation(value = "动态双向柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "doublebartime", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult doubleBarTimeTest() {
		List<JSONObject> res = new ArrayList<>();
		int rowY = (int) (Math.random() * 6) + 2;// 时间轴
		int dataNum = (int) (Math.random() * 10) + 2;// 数据数

		for (int i = 0; i < rowY; i++) {
			JSONObject bar = new JSONObject();
			bar.put("name", "" + i);

			List<String> label = new ArrayList<>();
			JSONObject data1 = new JSONObject();
			data1.put("name", "aa");
			List<Integer> data11 = new ArrayList<>();
			JSONObject data2 = new JSONObject();
			data2.put("name", "bb");
			List<Integer> data22 = new ArrayList<>();

			for (int j = 0; j < dataNum; j++) {
				label.add("label" + j);
				int value = (int) (Math.random() * 1000);
				data11.add(value);
				int value2 = (int) (Math.random() * 1000);
				data22.add(value2);
			}

			bar.put("label", label);
			data1.put("data", data11);
			data2.put("data", data22);

			bar.put("data1", data1);
			bar.put("data2", data2);

			res.add(bar);

		}
		return AjaxResult.success(res);
	}
	@ApiOperation(value = "单柱图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "singleColumn", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult singleColumnTest() {
		List<JSONObject> singleColumns = new ArrayList<>();
		JSONObject singleColumn1 = new JSONObject();
		singleColumn1.put("name", "苹果");
		singleColumn1.put("data", Stream.of(new Random().nextInt(100)).collect(Collectors.toList()));
		singleColumns.add(singleColumn1);
		JSONObject singleColumn2 = new JSONObject();
		singleColumn2.put("name", "香蕉");
		singleColumn2.put("data", Stream.of(new Random().nextInt(100)).collect(Collectors.toList()));
		singleColumns.add(singleColumn2);
		JSONObject singleColumn3 = new JSONObject();
		singleColumn3.put("name", "橘子");
		singleColumn3.put("data", Stream.of(new Random().nextInt(100)).collect(Collectors.toList()));
		singleColumns.add(singleColumn3);
		JSONObject singleColumn4 = new JSONObject();
		singleColumn4.put("name", "葡萄");
		singleColumn4.put("data", Stream.of(new Random().nextInt(100)).collect(Collectors.toList()));
		singleColumns.add(singleColumn4);
		JSONObject singleColumn5 = new JSONObject();
		singleColumn5.put("name", "西瓜");
		singleColumn5.put("data", Stream.of(new Random().nextInt(100)).collect(Collectors.toList()));
		singleColumns.add(singleColumn5);

		return AjaxResult.success(singleColumns);
	}

	@ApiOperation(value = "立体柱接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "cylinder", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult cylinderTest() {
		List<JSONObject> cylinders = new ArrayList<>();

		JSONObject cylinder = new JSONObject();
		int value = (int) (Math.random() * 100);
		cylinder.put("value", value);
		cylinder.put("name", "立体柱");

		cylinders.add(cylinder);
		return AjaxResult.success(cylinders);
	}

	@ApiOperation(value = "伪立体柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "threeDBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult threeDBarTest() {

		JSONObject threeDBar = new JSONObject();
		// 数据
		String[] axisData = { "2019年", "2020年" };
		String[] name = { "韩国", "日本", "瑞士", "泰国", "中国", "世界人口" };
		String[] stack = { "南美洲", "亚洲", "北美洲", "亚洲", "亚洲", "世界人口" };
		int[][] data = { { 1203, 1955 }, { 2389, 2943 }, { 2934, 3100 }, { 10497, 12159 }, { 13174, 14141 },
				{ 6302, 6818 } };

		// 组装数据
		threeDBar.put("axisData", axisData);
		List<JSONObject> series = new ArrayList<>();
		for (int i = 0; i < name.length; i++) {
			JSONObject temp = new JSONObject();
			temp.put("name", name[i]);
			temp.put("data", data[i]);
			temp.put("stack", stack[i]);
			series.add(temp);
		}
		threeDBar.put("series", series);
		return AjaxResult.success(threeDBar);
	}

	@ApiOperation(value = "比例柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "rateBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult rateBarTest() {

		JSONObject rateBar = new JSONObject();
		// 数据
		String[] axisData = { "交通", "营商", "环保", "税务"};

		// 组装数据
		rateBar.put("axisData", axisData);
		int total[] = {900, 700, 800, 900};

		int value[] = {600, 630, 730, 600};

		JSONObject data = new JSONObject();
		data.put("total",total);
		data.put("value",value);

		rateBar.put("data", data);
		return AjaxResult.success(rateBar);
	}

	@ApiOperation(value = "散点图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "scatter", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult scatterTest() {

		List<double[]> scatters = new ArrayList<>();

		for(int i=0;i<6;i++) {
			double[] scatter = { Math.random() * 10 , Math.random() * 10 };
			scatters.add(scatter);
		}

		return AjaxResult.success(scatters);
	}

	@ApiOperation(value = "文本框接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "text", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult textTest() {

		String text = "这是一个通用文本";

		return AjaxResult.success("操作成功", text);
	}

	@ApiOperation(value = "字符云接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "wordcloud", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult wordCloudTest() {

		List<JSONObject> wordList = new ArrayList<>();
		JSONObject word1 = new JSONObject();
		JSONObject word2 = new JSONObject();
		JSONObject word3 = new JSONObject();
		word1.put("name", "武汉");
		word1.put("value", Math.random() * 100 + Math.random() * 10);
		wordList.add(word1);

		word2.put("name", "成都");
		word2.put("value", Math.random() * 100 + Math.random() * 10);
		wordList.add(word2);

		word3.put("name", "郑州");
		word3.put("value", Math.random() * 100 + Math.random() * 10);
		wordList.add(word3);

		return AjaxResult.success(wordList);
	}
	@ApiOperation(value = "翻牌器接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "flop", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult flopTest() {

		double value =(double) Math.random() * 1000 + Math.random() * 100 + Math.random() * 10;

		return AjaxResult.success("操作成功", value);
	}

	@ApiOperation(value = "分页表格接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "table", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult tableTest() {

		List<JSONObject> taBles = new ArrayList<>();
		int rowX = (int) (Math.random() * 100);
		for (int i = 0; i < rowX; i++) {
			JSONObject stu = new JSONObject();
			stu.put("name", "小王" + i);
			stu.put("address", "哈尔滨");
			stu.put("date", "2020-01-01");

			taBles.add(stu);
		}
		return AjaxResult.success(taBles);
	}

	@ApiOperation(value = "颜色块接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "colorBlock", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult colorBlockTest() {

		List<JSONObject> blocks = new ArrayList<>();
		int rowY = (int) (Math.random() * 10) + 2;
		for (int i = 0; i < rowY; i++) {
			JSONObject block = new JSONObject();
			int value = (int) (Math.random() * 100);
			block.put("value", value);
			String name = "颜色块" + i;
			block.put("name", name);
			block.put("suffix", "单位" + i);
			blocks.add(block);
		}

		return AjaxResult.success(blocks);
	}

    @ApiOperation(value = "卡片接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "card", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult cardTest() {
        //创建卡片列表
        List<JSONObject> cards = new ArrayList<>();

        JSONObject card = new JSONObject();
        //卡片头部信息
        JSONObject head = new JSONObject();
        head.put("headImg","http://221.212.111.73:2080/prod-api/profile/backgroundBox/2022/04/14/d60a075bdd447611d2ac2f48806226cc.png");
        head.put("tipText","迟到2次");
        card.put("head",head);

        //卡片名称信息
        card.put("name","测试02");

        //卡片中间信息行
        List<JSONObject> infoItems = new ArrayList<>();
        JSONObject infoItem = new JSONObject();
        infoItem.put("label","所在单位");
        infoItem.put("value","工大软件");
        infoItems.add(infoItem);

        infoItem = new JSONObject();
        infoItem.put("label","所在位置");
        infoItem.put("value","A座404");
        infoItems.add(infoItem);

        card.put("infoItem",infoItems);

        //卡片详细信息行
        String[] details = {"工作日报查看"};
        card.put("detailItem",details);

        cards.add(card);

        return AjaxResult.success(cards);
    }

	@ApiOperation(value = "轮播图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "rotation", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult rotationTest() {

		List<String> pictures = new ArrayList<>(Arrays.asList(
				"//bpic.588ku.com//back_water_img/19/07/23/16e1186b39b60b14027e1220141fac8295.jpg!/fw/750/quality/99/unsharp/true/compress/true",
				"//bpic.588ku.com//back_water_img/19/07/26/10f0335223063fb24895246038f4b55b00.jpg!/fw/750/quality/99/unsharp/true/compress/true",
				"//bpic.588ku.com//back_origin_min_pic/19/07/03/6c172b63684e76d55c2fbd7c98fd039a.jpg!/fw/750/quality/99/unsharp/true/compress/true",
				"//bpic.588ku.com//back_origin_min_pic/19/04/12/e0065163931dc6012dc1934897969fe9.jpg!/fw/750/quality/99/unsharp/true/compress/true",
				"//bpic.588ku.com//back_origin_min_pic/19/08/03/c8bd31b7bd194cbbb3e11f47af3b7cd2.jpg!/fw/750/quality/99/unsharp/true/compress/true"));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < pictures.size(); i++) {
			String str = "{value:'" + pictures.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}
	@ApiOperation(value = "饼图-折线图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "pieline", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult pieLineTest() {

		String[] line1 = { "product", "2012", "2013", "2014", "2015", "2016", "2017" };
		String[] line2 = { "Matcha Latte", "41.1", "30.4", "65.1", "53.3", "803.8", "98.7" };
		String[] line3 = { "Milk Tea", "860.5", "92.1", "850.7", "83.1", "73.4", "550.1" };
		String[] line4 = { "Cheese Cocoa", "24.1", "670.2", "79.5", "860.4", "65.2", "82.5" };
		String[] line5 = { "Walnut Brownie", "550.2", "670.1", "69.2", "72.4", "53.9", "39.1" };

		List<String[]> list = Lists.newArrayList();

		list.add(line1);
		list.add(line2);
		list.add(line3);
		list.add(line4);
		list.add(line5);

		return AjaxResult.success(list);
	}

	@ApiOperation(value = "柱状图-饼图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "barpie", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult barPieTest() {

		List<JSONObject> pies = new ArrayList<>();
		int rowY = (int) (Math.random() * 10) + 2;
		for (int i = 0; i < rowY; i++) {
			JSONObject pie = new JSONObject();
			int value = (int) (Math.random() * 100);
			pie.put("value", value);
			String name = "pie" + i;
			pie.put("name", name);

			pies.add(pie);
		}

		return AjaxResult.success(pies);
	}

	@ApiOperation(value = "折线图-柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "linebar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult lineBarTest() {
		JSONObject lineBarDto = new JSONObject();
		String[] xAxis = { "1月", "2月", "3月", "4月", "5月", "6月" };
		lineBarDto.put("xAxixData", xAxis);

		List<JSONObject> lines = Lists.newArrayList();
		JSONObject line1 = new JSONObject();
		JSONObject line2 = new JSONObject();
		JSONObject line3 = new JSONObject();

		line1.put("name", "蒸发量");
		line1.put("type", "bar");
		double[] data = { 2.0, 4.9, 7.0, 23.2, 25.6, 76.7 };
		line1.put("data", data);
		// line1.put("yAxisIndex", 0);
		lines.add(line1);

		line2.put("name", "降水量");
		line2.put("type", "bar");
		double[] data2 = { 175.6, 182.2, 48.7, 18.8, 6.0, 2.3 };
		line2.put("data", data2);
		// line2.put("yAxisIndex", 0);
		lines.add(line2);

		line3.put("name", "平均温度");
		line3.put("type", "line");
		double[] data3 = { 6.3, 10.2, 20.3, 23.4, 23.0, 16.5 };
		line3.put("data", data3);
		// line3.put("yAxisIndex", 1);
		lines.add(line3);

		lineBarDto.put("yAxisData", lines);

		return AjaxResult.success(lineBarDto);
	}

	@ApiOperation(value = "地图-柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "mapbar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult mapBarTest() {

		List<String> names = new ArrayList<>(
				Arrays.asList("北京市", "天津市", "河北省,", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省"));
		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			int v = (int) (Math.random() * 1000);
			String str = "{value:" + v + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "堆叠图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "stackedBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult stackedBarTest() {

		JSONObject stackedBar = new JSONObject();
		//数据
		String[] axisData = {"2011年","2015年"};
		String[] name = {"巴西", "印尼", "美国", "印度", "中国", "世界人口"};
		String[] stack = {"南美洲", "亚洲", "北美洲", "亚洲", "亚洲", "世界人口"};
		int[][] data = {{18203, 19325},{234089, 23438},{29034, 31000},{104970, 121594},{131744, 134141},{630230, 681807}};

		//组装数据
		stackedBar.put("axisData", axisData);
		List<JSONObject> series = new ArrayList<>();
		for(int i = 0; i<name.length; i++) {
			JSONObject temp = new JSONObject();
			temp.put("name", name[i]);
			temp.put("data", data[i]);
			temp.put("stack", stack[i]);
			series.add(temp);
		}
		stackedBar.put("series", series);

		return AjaxResult.success(stackedBar);
	}

	@ApiOperation(value = "折线图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "line", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult lineTest() {

		List<JSONObject> lines = Lists.newArrayList();
		JSONObject line1 = new JSONObject();
		JSONObject line2 = new JSONObject();
		JSONObject line3 = new JSONObject();

		String name = "line1";
		line1.put("name", name);
		double[] data = { 18.7, 48.3, 103.5, 231.6, 46.6, 55.4, 18.4 };
		line1.put("data", data);
		String[] axisData = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		line1.put("axisData", axisData);
		lines.add(line1);

		String name2 = "line2";
		line2.put("name", name2);
		double[] data2 = { 26.4, 28.7, 69.2, 175.6, 162.2, 23.7, 18.8 };
		line2.put("data", data2);
		String[] axisData2 = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		line1.put("axisData", axisData2);
		lines.add(line2);

		String name3 = "line3";
		line3.put("name", name3);
		double[] data3 = { 76.4, 28, 70.7, 195.6, 82.2, 48.7, 25.8 };
		line3.put("data", data3);
		String[] axisData3 = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		line1.put("axisData", axisData3);
		lines.add(line3);

		return AjaxResult.success(lines);
	}

	@ApiOperation(value = "雨量流量关系图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "rain", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult rainTest() {

		List<JSONObject> rains = Lists.newArrayList();
		JSONObject rain1 = new JSONObject();
		JSONObject rain2 = new JSONObject();

		String name = "Rainfall";// 降雨量
		rain1.put("name", name);
		double[] data = { 18.7, 48.3, 103.5, 231.6, 46.6, 55.4, 18.4, 18.7, 48.3, 103.5, 231.6, 46.6, 55.4, 18.4 };
		rain1.put("data", data);
		String[] axisData = { "2020/1/1 1:00", "2020/1/2 1:00", "2020/1/3 1:00", "2020/1/4 1:00", "2020/1/5 1:00",
				"2020/1/6 1:00", "2020/1/7 1:00", "2020/1/8 1:00", "2020/1/9 1:00", "2020/1/10 1:00", "2020/1/11 1:00",
				"2020/1/12 1:00", "2020/1/13 1:00", "2020/1/14 1:00" };
		rain1.put("axisData", axisData);
		rains.add(rain1);

		String name2 = "Evaporation";// 蒸发量
		rain2.put("name", name2);
		double[] data2 = { 76.4, 28, 70.7, 195.6, 82.2, 48.7, 25.8, 76.4, 28, 70.7, 195.6, 82.2, 48.7, 25.8 };
		rain2.put("data", data2);
		String[] axisData2 = { "2020/1/1 1:00", "2020/1/2 1:00", "2020/1/3 1:00", "2020/1/4 1:00", "2020/1/5 1:00",
				"2020/1/6 1:00", "2020/1/7 1:00", "2020/1/8 1:00", "2020/1/9 1:00", "2020/1/10 1:00", "2020/1/11 1:00",
				"2020/1/12 1:00", "2020/1/13 1:00", "2020/1/14 1:00" };
		rain2.put("axisData", axisData2);
		rains.add(rain2);

		return AjaxResult.success(rains);
	}

	@ApiOperation(value = "饼图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "pie", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult pieTest() {

		List<JSONObject> pies = new ArrayList<>();
		int rowY = (int) (Math.random() * 10) + 2;
		for (int i = 0; i < rowY; i++) {
			JSONObject pie = new JSONObject();
			int value = (int) (Math.random() * 100);
			pie.put("value", value);
			String name = "pie" + i;
			pie.put("name", name);

			pies.add(pie);
		}

		return AjaxResult.success(pies);
	}

	@ApiOperation(value = "环饼图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "ringPie", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult ringPieTest() {

		List<JSONObject> ringPies = new ArrayList<>();
		JSONObject ringPie1 = new JSONObject();
		ringPie1.put("name", "苹果");
		ringPie1.put("value", new Random().nextInt(100));
		ringPies.add(ringPie1);
		JSONObject ringPie2 = new JSONObject();
		ringPie2.put("name", "香蕉");
		ringPie2.put("value", new Random().nextInt(100));
		ringPies.add(ringPie2);
		JSONObject ringPie3 = new JSONObject();
		ringPie3.put("name", "橘子");
		ringPie3.put("value", new Random().nextInt(100));
		ringPies.add(ringPie3);
		JSONObject ringPie4 = new JSONObject();
		ringPie4.put("name", "葡萄");
		ringPie4.put("value", new Random().nextInt(100));
		ringPies.add(ringPie4);
		JSONObject ringPie5 = new JSONObject();
		ringPie5.put("name", "西瓜");
		ringPie5.put("value", new Random().nextInt(100));
		ringPies.add(ringPie5);

		return AjaxResult.success(ringPies);
	}

	@ApiOperation(value = "漏斗图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "funnel", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult funnelTest() {
		List<JSONObject> funnels = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			JSONObject funnel = new JSONObject();
			int value = (int) (Math.random() * 100);
			funnel.put("value", value);
			String name = "funnel" + i;
			funnel.put("name", name);

			funnels.add(funnel);
		}

		return AjaxResult.success(funnels);
	}

	@ApiOperation(value = "仪表盘接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "gauge", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult gaugeTest() {

		List<JSONObject> gauges = new ArrayList<>();

		JSONObject gauge = new JSONObject();
		int value = (int) (Math.random() * 100);
		gauge.put("value", value);
		String name = "gauge";
		gauge.put("name", name);

		gauges.add(gauge);
		return AjaxResult.success(gauges);
	}

	@ApiOperation(value = "雷达图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "radar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult radarTest() {

		List<JSONObject> radars = new ArrayList<>();
		JSONObject radar1 = new JSONObject();
		JSONObject radar2 = new JSONObject();
		radar1.put("name", "预算分配");
		int[] data1 = new int[6];
		for(int i = 0;i < 6;i++) {
			data1[i] = (int) (Math.random() * 100) +2;
		}
//		int[] data = { 430, 1000, 2800, 2100, 5000, 1900 };
		radar1.put("value", data1);
		radars.add(radar1);

		radar2.put("name", "实际开销");
		int[] data2 = new int[6];
		for(int i = 0;i < 6;i++) {
			data2[i] = (int) (Math.random() * 100) +2 ;
		}
//		int[] data1 = { 5000, 1400, 2800, 3000, 4200, 2100 };
		radar2.put("value", data2);
		radars.add(radar2);

		List<JSONObject> indicator = new ArrayList<>();
		for(int i = 0;i < 6;i++) {
			JSONObject obj = new JSONObject();
			obj.put("name", "数据"+i);
			obj.put("max", (int) (Math.random() * 1000));
			indicator.add(obj);
		}
		List<List<JSONObject>> result = new ArrayList<>();
		result.add(radars);
		result.add(indicator);

		return AjaxResult.success(result);
	}

	@ApiOperation(value = "下拉菜单接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "select", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult selectTest() {

		List<String> value = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
		List<String> tab = new ArrayList<>(Arrays.asList("麻辣烫", "米线", "花甲粉", "烤肉拌饭", "石锅拌饭", "炸鸡", "泡面"));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < value.size(); i++) {
			String str = "{value:'" + value.get(i) + "', tab:'" + tab.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}
	@ApiOperation(value = "选项卡接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "tab", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult tabTest() {
		List<JSONObject> lists = Lists.newArrayList();

		for (int i = 0; i < 3; i++) {
			JSONObject obj = new JSONObject();
			obj.put("bindid",i);
			obj.put("content","选项卡"+i);

			lists.add(obj);
		}


		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "级联选择接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "cascade", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult cascadeTest() {

		JSONArray jsonArray = new JSONArray();
		JSONObject province = new JSONObject();
		province.put("label", "黑龙江省");
		province.put("value", "230000");

		JSONObject city = new JSONObject();
		city.put("label", "哈尔滨市");
		city.put("value", "230100");

		List<JSONObject> lists = Lists.newArrayList();
		List<String> names = new ArrayList<>(
				Arrays.asList("道里区", "南岗区", "道外区", "香坊区", "平房区", "松北区", "呼兰区", "依兰县", "方正县","宾县",
						"巴彦县","木兰县","通河县","延寿县","阿城市","双城市","尚志市","五常市"));
		List<String> values = new ArrayList<>(
				Arrays.asList("230102", "230103", "230104", "230106", "230108", "230109", "230111", "230123", "230124","230125",
						"230126","230127","230128","230129","230181","230182","230183","2301814"));

		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + values.get(i) + ", label:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}
		city.put("children", lists);
		List<JSONObject> citylists = Lists.newArrayList();
		citylists.add(city);
		province.put("children", citylists);

		//String result = "[" + province.toJSONString() + "]" ;
		jsonArray.add(province);
		return AjaxResult.success("操作成功", jsonArray.toString());
	}

	@ApiOperation(value = "地图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "map", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult mapTest() {

		List<String> names = new ArrayList<>(
				Arrays.asList("北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏", "浙江"));
		List<Integer> value = new ArrayList<>(Arrays.asList(69, 93, 113, 31, 31, 55, 29, 710, 436, 132));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + value.get(i) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}
	@ApiOperation(value = "飞线地图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "mapline", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult mapLineTest() {

		List<String> names = new ArrayList<>(
				Arrays.asList("北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏", "浙江"));
		List<Integer> value = new ArrayList<>(Arrays.asList(69, 93, 113, 31, 31, 55, 29, 710, 436, 132));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + value.get(i) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		JSONObject result = new JSONObject();
		result.put("nodes",lists);

		JSONObject obj1 = new JSONObject();
		JSONObject source = new JSONObject();
		source.put("name","北京");
		source.put("value",100);

		obj1.put("source",source);

		JSONObject target = new JSONObject();
		target.put("name","吉林");

		obj1.put("target",target);

		List<JSONObject> lists2 = Lists.newArrayList();
		lists2.add(obj1);
		result.put("links",lists2);

		return AjaxResult.success(result);
	}

	@ApiOperation(value = "地图下钻接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "mapmore", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult mapmoreTest() {

		List<String> names = new ArrayList<>(
				Arrays.asList("北京", "天津", "上海", "重庆", "香港", "澳门", "内蒙古", "广西", "西藏", "宁夏", "新疆",
						"河北", "山西", "吉林", "黑龙江", "江苏", "浙江", "安徽", "黑龙江", "福建", "江西", "山东",
						"河南", "湖北", "湖南", "广东", "海南", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "台湾"));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + (int)(Math.random() * 1000) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		//System.err.println(lists);
		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "3D地图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "threedMap", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult threedMapTest() {

		List<String> names = new ArrayList<>(
				Arrays.asList("北京", "天津", "河北", "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江"));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + (int)(Math.random() * 1000) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "3D地图柱状图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "threedMapBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult threedMapBarTest() {

		List<String> names = new ArrayList<>(
				Arrays.asList("北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏", "浙江"));
		List<Integer> value = new ArrayList<>(Arrays.asList(69, 93, 113, 31, 31, 55, 29, 710, 436, 132));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + value.get(i) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "跑马灯接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "lamp", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult lampTest() {

		String text = "这是一个跑马灯|第二个跑马灯|123456";

		return AjaxResult.success("操作成功", text);
	}

	@ApiOperation(value = "水球图数据结构", response = AjaxResult.class)
	@RequestMapping(value = "waterball", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult waterBallTest() {

		double i = 0.6;

		return AjaxResult.success(i);
	}

	@ApiOperation(value = "水球图加基线数据结构", response = AjaxResult.class)
	@RequestMapping(value = "waterballline", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult waterBallLineTest() {

		double[] i = { 0.7, 0.4 };

		return AjaxResult.success(i);
	}

	@ApiOperation(value = "圆点环形图数据结构", response = AjaxResult.class)
	@RequestMapping(value = "circle", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult circleTest() {

		List<String> names = new ArrayList<>(Arrays.asList("鸡西", "大庆", "牡丹江", "双鸭山"));
		List<Integer> value = new ArrayList<>(Arrays.asList(69, 125, 293, 360));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{value:" + value.get(i) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "多环形图数据结构", response = AjaxResult.class)
	@RequestMapping(value = "piemore", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult piemoreTest() {

		List<JSONObject> pies = Lists.newArrayList();
		JSONObject pie1 = new JSONObject();
		JSONObject pie2 = new JSONObject();
		JSONObject pie3 = new JSONObject();

		String name = "剧情";
		pie1.put("name", name);
		int data = 25;
		pie1.put("value", data);
		String[] axisData = { "剧情", "奇幻", "爱情" };
		pie1.put("axisData", axisData);
		pies.add(pie1);

		String name2 = "奇幻";
		pie2.put("name", name2);
		int data2 = 24;
		pie2.put("value", data2);
		pies.add(pie2);

		String name3 = "爱情";
		pie3.put("name", name3);
		int data3 = 14;
		pie3.put("value", data3);
		pies.add(pie3);

		return AjaxResult.success(pies);
	}

	@ApiOperation(value = "进度图表数据结构", response = AjaxResult.class)
	@RequestMapping(value = "progress", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult progressTest() {

		// 数据
		String[] name = { "世界人口", "中国", "印度", "美国", "印尼", "巴西" };
		int[] value = { 630230, 431744, 404970, 339034, 233489, 148203 };

		// 组装数据
		List<JSONObject> progressChart = new ArrayList<>();
		for (int i = 0; i < name.length; i++) {
			JSONObject temp = new JSONObject();
			temp.put("name", name[i]);
			temp.put("value", value[i]);
			progressChart.add(temp);
		}

		return AjaxResult.success(progressChart);
	}

	@ApiOperation(value = "知识图谱数据结构", response = AjaxResult.class)
	@RequestMapping(value = "knowledge", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult knowledgeTest() {
		JSONObject know = new JSONObject();

		List<JSONObject> nodes = Lists.newArrayList();
		int nodeNum = (int) (Math.random() * 10) + 2;
		int categorys = (int) (Math.random() * 5) + 1;
		for (int i = 0; i < nodeNum; i++) {
			JSONObject node = new JSONObject();
			node.put("name", "node" + i);
			node.put("category", i % categorys);
			nodes.add(node);
		}
		know.put("nodes", nodes);
		List<JSONObject> links = Lists.newArrayList();
		int names = (int) (Math.random() * 7) + 1;
		for (int i = 0; i < nodeNum; i++) {
			for (int j = 0; j < nodeNum; j++) {
				if (Math.random() > 0.5) {
					JSONObject link = new JSONObject();
					link.put("name", "link" + ((i * nodeNum + j) % names));
					link.put("source", nodes.get(i).get("name"));
					link.put("target", nodes.get(j).get("name"));
					links.add(link);
				}

			}
		}
		know.put("links", links);
		return AjaxResult.success(know);
	}

	@ApiOperation(value = "知识图谱数据结构", response = AjaxResult.class)
	@RequestMapping(value = "pops", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult popsTest() {
		List<String> names = new ArrayList<>(
				Arrays.asList("北京", "天津", "河北,", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "江苏", "浙江"));
		List<Integer> value = new ArrayList<>(Arrays.asList(69, 93, 113, 31, 31, 55, 29, 710, 436, 132));

		List<JSONObject> lists = Lists.newArrayList();
		for (int i = 0; i < names.size(); i++) {
			String str = "{data:" + value.get(i) + ", name:'" + names.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "旭日图数据结构", response = AjaxResult.class)
	@RequestMapping(value = "sunburst", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult sunburstTest() {
		List<JSONObject> lists = Lists.newArrayList();

		JSONObject child1 = new JSONObject();
		child1.put("name","小说");
		child1.put("value",4);

		List<JSONObject> childlists1 = Lists.newArrayList();

		JSONObject grandson3 = new JSONObject();
		grandson3.put("name","平凡的世界");
		grandson3.put("value",3);
		childlists1.add(grandson3);
		child1.put("children",childlists1);

		JSONObject child2 = new JSONObject();
		child2.put("name","技术");
		child2.put("value",8);

		List<JSONObject> childlists = Lists.newArrayList();

		JSONObject grandson1 = new JSONObject();
		grandson1.put("name","代码整洁之道");
		grandson1.put("value",5);
		childlists.add(grandson1);

		JSONObject grandson2 = new JSONObject();
		grandson2.put("name","Three.js 开发指南");
		grandson2.put("value",3);
		childlists.add(grandson2);
		child2.put("children",childlists);

		lists.add(child1);
		lists.add(child2);


		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "文本复选框数据结构", response = AjaxResult.class)
	@RequestMapping(value = "textCheckBox", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult textCheckBoxTest() {
		List<String> keys = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
		List<String> values = new ArrayList<>(Arrays.asList("麻辣烫", "米线", "花甲粉", "烤肉拌饭", "石锅拌饭", "炸鸡", "泡面"));

		List<JSONObject> lists = Lists.newArrayList();



		for (int i = 0; i < keys.size(); i++) {
			String str = "{value:'" + values.get(i) + "', key:'" + keys.get(i) + "'}";
			JSONObject object = JSONObject.parseObject(str);
			lists.add(object);
		}

		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "盒须图数据结构", response = AjaxResult.class)
	@RequestMapping(value = "boxline", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult boxlineTest() {
		String[] arr0 = {"34","54","67","75","86"};
		String[] arr1 = {"10","25","45","67","73"};
		String[] arr2 = {"24","34","45","55","66"};
		String[] arr3 = {"32","45","50","60","70"};
		String[] arr4 = {"44","59","70","75","88"};
		String[] arr5 = {"28","38","48","58","68"};
		String[] arr6 = {"38","48","58","68","78"};
		String[] arr7 = {"机电工程学院", "自动化学院", "计算机学院", "轻工化工学院", "材料与能源学院", "信息工程学院", "管理学院"};
		ArrayList<String[]> lists = new ArrayList<>();
		lists.add(arr0);
		lists.add(arr1);
		lists.add(arr2);
		lists.add(arr3);
		lists.add(arr4);
		lists.add(arr5);
		lists.add(arr6);
		lists.add(arr7);
		return AjaxResult.success(lists);
	}

	@ApiOperation(value = "进度仪表盘接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "progauge", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult progaugeTest() {

		int value = (int) (Math.random() * 100);

		return AjaxResult.success("操作成功", value);
	}

	@ApiOperation(value = "象形柱图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "pictorialBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult pictorialBarTest() {

		List<JSONObject> pictorialBarDatas = new ArrayList<>();
		JSONObject pictorialBar = new JSONObject();
		JSONObject pictorialBarData1 = new JSONObject();
		JSONObject pictorialBarData2 = new JSONObject();

		String name = "2011年";
		pictorialBarData1.put("name", name);
		int[] dataData = { 57,21,66,78,83 };
		pictorialBarData1.put("value", dataData);
		int max1 = 100;
		pictorialBarData1.put("max",max1);
		pictorialBarDatas.add(pictorialBarData1);

		String name2 = "2012年";
		pictorialBarData2.put("name", name2);
		int[] data2 = { 66,39,49,97,74 };
		pictorialBarData2.put("value", data2);
		int max2 = 100;
		pictorialBarData1.put("max",max2);
		pictorialBarDatas.add(pictorialBarData2);

		String[] axisData = { "reindeer", "ship", "plane", "train", "car"};
		pictorialBar.put("axisData", axisData);
		pictorialBar.put("data",pictorialBarDatas);

		return AjaxResult.success(pictorialBar);
	}

	@ApiOperation(value = "自定义3d柱图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "custom3dBar", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult custom3dBarTest() {

		List<JSONObject> pictorialBarDatas = new ArrayList<>();
		JSONObject pictorialBar = new JSONObject();
		JSONObject pictorialBarData = new JSONObject();
		JSONObject pictorialBarData2 = new JSONObject();

		String[] axisData = { "reindeer", "ship", "plane", "train", "car"};
		pictorialBar.put("axisData", axisData);

		String name = "2011年";
		pictorialBarData.put("name", name);
		int[] dataData = { 67,31,56,48,63 };
		pictorialBarData.put("value", dataData);
		int max = 100;
		pictorialBarData.put("max",max);
		pictorialBarDatas.add(pictorialBarData);

		pictorialBar.put("data",pictorialBarDatas);

		return AjaxResult.success(pictorialBar);
	}

	@ApiOperation(value = "桑基图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "sankey", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult sankeyTest() {

		List<JSONObject> sankeyData = new ArrayList<>();

		JSONObject data = new JSONObject();

		List<JSONObject> nodesList = new ArrayList<>();
		String[] nodeName = {"a","b","c","a1","b1","e"};
		for(int i = 0;i<6;i++){
			JSONObject node = new JSONObject();
			node.put("name",nodeName[i]);
			nodesList.add(node);
		}
		data.put("nodes", nodesList);

		List<JSONObject> linksList = new ArrayList<>();
		JSONObject link1 = new JSONObject();
		JSONObject link2 = new JSONObject();
		JSONObject link3 = new JSONObject();
		JSONObject link4 = new JSONObject();
		JSONObject link5 = new JSONObject();
		JSONObject link6 = new JSONObject();

		link1.put("source","a");
		link1.put("target","a1");
		link1.put("value",5);
		linksList.add(link1);
		link2.put("source","a");
		link2.put("target","b1");
		link2.put("value",3);
		linksList.add(link2);
		link3.put("source","b1");
		link3.put("target","a1");
		link3.put("value",1);
		linksList.add(link3);
		link4.put("source","e");
		link4.put("target","b");
		link4.put("value",3);
		linksList.add(link4);
		link5.put("source","b1");
		link5.put("target","c");
		link5.put("value",2);
		linksList.add(link5);
		link6.put("source","b");
		link6.put("target","c");
		link6.put("value",1);
		linksList.add(link6);
		data.put("links",linksList);

		sankeyData.add(data);

		return AjaxResult.success(sankeyData);
	}

	@ApiOperation(value = "树状结构图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "treeLayout", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult treeLayoutTest() {

		JSONObject treeLayoutData = new JSONObject();
		List<JSONObject> nodesList = new ArrayList<>();
		List<JSONObject> linksList = new ArrayList<>();
		String[] nodes = {"a","b","c","d"};

		treeLayoutData.put("rootId",nodes[0]);

		for (int i = 0; i <nodes.length; i++) {
			JSONObject nodeObject = new JSONObject();
			nodeObject.put("id",nodes[i]);
			nodeObject.put("text",nodes[i].toUpperCase());
			nodesList.add(nodeObject);
		}
		treeLayoutData.put("nodes",nodesList);
		for (int i = 1; i < nodes.length; i++) {
			JSONObject linkObject = new JSONObject();
			linkObject.put("from",nodes[0]);
			linkObject.put("to",nodes[i]);
			linkObject.put("text","关系"+i);
			linksList.add(linkObject);
		}
		treeLayoutData.put("links",linksList);

		return AjaxResult.success(treeLayoutData);
	}

	@ApiOperation(value = "坐标系热力图接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "heatMap", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult heatMapTest() {

		JSONArray heatMapData = new JSONArray();
		JSONObject jsonData = new JSONObject();

		String[] xAxisData = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
		String[] yAxisData = {"2015", "2016", "2017","2018", "2019", "2020", "2021"};
		Number[][] data = {{0, 0, 5}, {0, 1, 1}, {0, 2, 8}, {0, 3, 0}, {0, 4, 0}, {0, 5, 0}, {0, 6, 0}};

		jsonData.put("xAxisData",xAxisData);
		jsonData.put("yAxisData",yAxisData);
		jsonData.put("data",data);
		heatMapData.add(jsonData);
		return AjaxResult.success(heatMapData);
	}
	@ApiOperation(value = "时间轴接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "timeline", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult timelineTest() {

		String[] xAxisData = {"1", "2", "3", "4", "5", "6"};

		return AjaxResult.success(xAxisData);
	}

	@ApiOperation(value = "websocket接口", response = AjaxResult.class)
	@PostMapping(value = "websocket")
	public void websocket(@RequestBody JSONObject object) {
		chatHandler.toComponent(JSONObject.toJSONString(object));
	}

	@ApiOperation(value = "测试接口数据结构", response = AjaxResult.class)
	@RequestMapping(value = "test", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxResult normalTest() {

		String text = "这是一个测试接口";

		return AjaxResult.success("操作成功", text);
	}
}
