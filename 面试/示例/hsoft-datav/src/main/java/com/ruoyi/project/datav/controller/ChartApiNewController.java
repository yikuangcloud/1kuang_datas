package com.ruoyi.project.datav.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ruoyi.framework.netty.ChatHandler;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartApiNewController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/11/21 11:05
 */
@Api(value = "图表组件API测试接口", tags = { "图表组件API测试接口" })
@CrossOrigin
@RestController
@RequestMapping("/chart/api/new")
public class ChartApiNewController {

    @ApiOperation(value = "柱状图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "bar", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult barTest() {
        String barsString ="[{\"name\":\"巴西\", \"type\":\"2011年\", \"value\":18203 }, { \"name\":\"印尼\", \"type\":\"2011年\", \"value\":23489 },"+
        "{ \"name\":\"美国\", \"type\":\"2011年\", \"value\":29034, }, { \"name\":\"印度\", \"type\":\"2011年\", \"value\":104970},"+
        "{ \"name\":\"中国\", \"type\":\"2011年\", \"value\":131744 }, { \"name\":\"世界人口(万)\", \"type\":\"2011年\", \"value\":630230}]";

        List<JSONObject> bars = JSONArray.parseArray(barsString,JSONObject.class);

        return AjaxResult.success(bars);
    }


    @ApiOperation(value = "堆叠图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "stackedBar", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult stackedBarTest() {
        String barsString ="[{\"name\":\"巴西\", \"series\":\"2011年\",\"type\":\"南美洲\", \"value\":18203 }, { \"name\":\"印尼\",  \"series\":\"2011年\",\"type\":\"亚洲\", \"value\":23489 },"+
                "{ \"name\":\"美国\",  \"series\":\"2011年\",\"type\":\"北美洲\", \"value\":29034, }, { \"name\":\"印度\", \"series\":\"2011年\", \"type\":\"亚洲\", \"value\":104970}"+
                "{\"name\":\"巴西\", \"series\":\"2012年\",\"type\":\"南美洲\", \"value\":18203 }, { \"name\":\"印尼\",  \"series\":\"2012年\",\"type\":\"亚洲\", \"value\":23489 },"+
                "{ \"name\":\"美国\",  \"series\":\"2012年\",\"type\":\"北美洲\", \"value\":29034, }, { \"name\":\"印度\", \"series\":\"2012年\", \"type\":\"亚洲\", \"value\":104970}]";


        List<JSONObject> bars = JSONArray.parseArray(barsString,JSONObject.class);

        return AjaxResult.success(bars);
    }
    @ApiOperation(value = "交错正负轴标签图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "staggered", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult staggeredLabelTest() {

        String barsString = "[{\"name\": \"苹果\",\"value\": 100},{\"name\": \"玉米\",\"value\": 200},{\"name\": \"土豆\",\"value\": 300},"+
        "{\"name\": \"地瓜\",\"value\": 150},{\"name\": \"萝卜\",\"value\": 450}]";

        List<JSONObject> staggeredLabels = JSONArray.parseArray(barsString,JSONObject.class);
        return AjaxResult.success(staggeredLabels);
    }

    @ApiOperation(value = "双柱状图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "doublebar", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult doubleBarTest() {
        String barsString = "[{\"name\": \"三星\",\"value\": 349,\"type\": \"家电品牌\"},{\"name\": \"小米\",\"value\": 230,\"type\": \"家电品牌\"},"+
                "{\"name\": \"华为\",\"value\": 400,\"type\": \"家电品牌\"},{\"name\": \"海尔\",\"value\": 840,\"type\": \"家电品牌\"},"+
                "{\"name\": \"三星\",\"value\": 649,\"type\": \"手机品牌\"},{\"name\": \"小米\",\"value\": 830,\"type\": \"手机品牌\"},"+
                "{\"name\": \"华为\",\"value\": 800,\"type\": \"手机品牌\"},{\"name\": \"海尔\",\"value\": 440,\"type\": \"手机品牌\"}]";
        List<JSONObject> res = JSONArray.parseArray(barsString,JSONObject.class);
        return AjaxResult.success(res);
    }

    @ApiOperation(value = "动态双向柱状图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "doublebartime", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult doubleBarTimeTest() {
        String barsString = "[{\"name\": \"三星\",\"value\": 349,\"series\":\"2\",\"type\": \"家电品牌\"},{\"name\": \"小米\",\"value\": 230,\"series\":\"2\",\"type\": \"家电品牌\"},"+
                "{\"name\": \"华为\",\"value\": 400,\"series\":\"2\",\"type\": \"家电品牌\"},{\"name\": \"海尔\",\"value\": 840,\"series\":\"2\",\"type\": \"家电品牌\"},"+
                "{\"name\": \"三星\",\"value\": 649,\"series\":\"2\",\"type\": \"手机品牌\"},{\"name\": \"小米\",\"value\": 830,\"series\":\"2\",\"type\": \"手机品牌\"},"+
                "{\"name\": \"华为\",\"value\": 800,\"series\":\"2\",\"type\": \"手机品牌\"},{\"name\": \"海尔\",\"value\": 440,\"series\":\"2\",\"type\": \"手机品牌\"},"+
                "{\"name\": \"三星\",\"value\": 449,\"series\":\"3\",\"type\": \"家电品牌\"},{\"name\": \"小米\",\"value\": 530,\"series\":\"3\",\"type\": \"家电品牌\"},"+
                "{\"name\": \"华为\",\"value\": 300,\"series\":\"3\",\"type\": \"家电品牌\"},{\"name\": \"海尔\",\"value\": 740,\"series\":\"3\",\"type\": \"家电品牌\"},"+
                "{\"name\": \"三星\",\"value\": 249,\"series\":\"3\",\"type\": \"手机品牌\"},{\"name\": \"小米\",\"value\": 630,\"series\":\"3\",\"type\": \"手机品牌\"},"+
                "{\"name\": \"华为\",\"value\": 800,\"series\":\"3\",\"type\": \"手机品牌\"},{\"name\": \"海尔\",\"value\": 540,\"series\":\"3\",\"type\": \"手机品牌\"}]";
        List<JSONObject> res = JSONArray.parseArray(barsString,JSONObject.class);
        return AjaxResult.success(res);
    }
    @ApiOperation(value = "比例柱状图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "rateBar", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult rateBarTest() {

        String barsString = "[{\"name\":\"三星\",\"value\":600,\"total\":700,},{\"name\":\"小米\",\"value\":800,\"total\":900}," +
                "{\"name\":\"华为\",\"value\":589,\"total\":900,},{\"name\":\"苹果\",\"value\":567,\"total\":800} ]";

        List<JSONObject> rateBar = JSONArray.parseArray(barsString,JSONObject.class);
        return AjaxResult.success(rateBar);
    }
    @ApiOperation(value = "雨量流量关系图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "rain", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult rainTest() {

        List<JSONObject> rains = Lists.newArrayList();
        double[] data = { 18.7, 48.3, 103.5, 231.6, 46.6, 55.4, 18.4, 18.7, 48.3, 103.5, 231.6, 46.6, 55.4, 18.4 };

        String[] axisData = { "2020/1/1 1:00", "2020/1/2 1:00", "2020/1/3 1:00", "2020/1/4 1:00", "2020/1/5 1:00",
                "2020/1/6 1:00", "2020/1/7 1:00", "2020/1/8 1:00", "2020/1/9 1:00", "2020/1/10 1:00", "2020/1/11 1:00",
                "2020/1/12 1:00", "2020/1/13 1:00", "2020/1/14 1:00" };

        for (int i = 0; i < data.length; i++) {
            JSONObject rain1 = new JSONObject();
            rain1.put("name",axisData[i]);
            rain1.put("type","Rainfall");
            rain1.put("value",data[i]);
            rains.add(rain1);
        }
        double[] data2 = { 76.4, 28, 70.7, 195.6, 82.2, 48.7, 25.8, 76.4, 28, 70.7, 195.6, 82.2, 48.7, 25.8 };

        String[] axisData2 = { "2020/1/1 1:00", "2020/1/2 1:00", "2020/1/3 1:00", "2020/1/4 1:00", "2020/1/5 1:00",
                "2020/1/6 1:00", "2020/1/7 1:00", "2020/1/8 1:00", "2020/1/9 1:00", "2020/1/10 1:00", "2020/1/11 1:00",
                "2020/1/12 1:00", "2020/1/13 1:00", "2020/1/14 1:00" };

        for (int i = 0; i < data2.length; i++) {
            JSONObject rain2 = new JSONObject();
            rain2.put("name",axisData2[i]);
            rain2.put("type","Evaporation");
            rain2.put("value",data2[i]);
            rains.add(rain2);
        }
        return AjaxResult.success(rains);
    }
    @ApiOperation(value = "散点图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "scatter", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult scatterTest() {

        List<JSONObject> scatters = Lists.newArrayList();

        for(int i=0;i<4;i++) {

            JSONObject obj = new JSONObject();
            obj.put("x",String.format("%.2f",Math.random()*100));
            obj.put("y",String.format("%.2f",Math.random()*100));
            obj.put("value",String.format("%.2f",Math.random()*10 + 5));
            obj.put("type","土豆");
            obj.put("series","2021");
            scatters.add(obj);
        }
        for(int i=0;i<4;i++) {

            JSONObject obj = new JSONObject();
            obj.put("x",String.format("%.2f",Math.random()*100));
            obj.put("y",String.format("%.2f",Math.random()*100));
            obj.put("value",String.format("%.2f",Math.random()*10+5));
            obj.put("type","地瓜");
            obj.put("series","2022");
            scatters.add(obj);
        }

        return AjaxResult.success(scatters);
    }


    @ApiOperation(value = "坐标系热力图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "heatMap", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult heatMapTest() {

        JSONArray heatMapData = new JSONArray();


        String[] xAxisData = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        String[] yAxisData = {"2018", "2019", "2020", "2021"};

        for (int i = 0; i < yAxisData.length; i++) {
            for (int j = 0; j < xAxisData.length; j++) {
                JSONObject jsonData = new JSONObject();
                jsonData.put("name",xAxisData[j]);
                jsonData.put("type",yAxisData[i]);
                jsonData.put("value",(int)(Math.random()*100));
                heatMapData.add(jsonData);
            }

        }
        return AjaxResult.success(heatMapData);
    }

    @ApiOperation(value = "雷达图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "radar", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult radarTest() {

        List<JSONObject> indicator = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            for(int i = 0;i < 6;i++) {
                JSONObject obj = new JSONObject();
                obj.put("name", "数据"+i);
                obj.put("type", "系列"+j);
                obj.put("max", 100);
                obj.put("value", (int) (Math.random() * 100));
                indicator.add(obj);
            }
        }


        return AjaxResult.success(indicator);
    }
    @ApiOperation(value = "水球图数据结构", response = AjaxResult.class)
    @RequestMapping(value = "waterball", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult waterBallTest() {
        List<JSONObject> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("value", Math.random());
        list.add(obj);

        return AjaxResult.success(list);
    }

    @ApiOperation(value = "水球图加基线数据结构", response = AjaxResult.class)
    @RequestMapping(value = "waterballline", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult waterBallLineTest() {

        List<JSONObject> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("value", Math.random());
        obj.put("line", Math.random());
        list.add(obj);

        return AjaxResult.success(list);
    }
    @ApiOperation(value = "水球图加基线数据结构", response = AjaxResult.class)
    @RequestMapping(value = "cusWaterballline", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult cusWaterBallLineTest() {

        List<JSONObject> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("value", Math.random());
        obj.put("line1", Math.random());
        obj.put("line2", Math.random());
        obj.put("line3", Math.random());
        list.add(obj);

        return AjaxResult.success(list);
    }
    @ApiOperation(value = "折线图柱状图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "linebar", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult lineBarTest() {
        String barsString = "[{\"name\": \"三星\",\"value\": 349,\"series\":\"家电品牌\",\"type\": \"bar\",\"yAxisIndex\":0},{\"name\": \"小米\",\"value\": 230,\"series\":\"家电品牌\",\"type\": \"bar\",\"yAxisIndex\":0},"+
                "{\"name\": \"华为\",\"value\": 400,\"series\":\"家电品牌\",\"type\": \"bar\",\"yAxisIndex\":0},{\"name\": \"海尔\",\"value\": 840,\"series\":\"家电品牌\",\"type\": \"bar\",\"yAxisIndex\":0},"+
                "{\"name\": \"三星\",\"value\": 249,\"series\":\"手机品牌\",\"type\": \"line\",\"yAxisIndex\":1},{\"name\": \"小米\",\"value\": 630,\"series\":\"手机品牌\",\"type\": \"line\",\"yAxisIndex\":1},"+
                "{\"name\": \"华为\",\"value\": 800,\"series\":\"手机品牌\",\"type\": \"line\",\"yAxisIndex\":1},{\"name\": \"海尔\",\"value\": 540,\"series\":\"手机品牌\",\"type\": \"line\",\"yAxisIndex\":1}]";
        List<JSONObject> res = JSONArray.parseArray(barsString,JSONObject.class);
        return AjaxResult.success(res);
    }

    @ApiOperation(value = "飞线地图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "mapline", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult mapLineTest() {
        List<JSONObject> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("from","哈尔滨");
        obj.put("to","石家庄");
        obj.put("value",90);
        obj.put("lng1","127.9688");
        obj.put("lat1","45.368");
        obj.put("lng2","114.4995");
        obj.put("lat2","38.1006");
        list.add(obj);

        JSONObject obj2 = new JSONObject();
        obj2.put("from","哈尔滨");
        obj2.put("to","呼伦贝尔");
        obj2.put("value",30);
        obj2.put("lng1","127.9688");
        obj2.put("lat1","45.368");
        obj2.put("lng2","119.758168");
        obj2.put("lat2","49.215333");
        list.add(obj2);

        JSONObject obj3 = new JSONObject();
        obj3.put("from","哈尔滨");
        obj3.put("to","哈尔滨");
        obj3.put("value",40);
        obj3.put("lng1","127.9688");
        obj3.put("lat1","45.368");
        obj3.put("lng2","127.9688");
        obj3.put("lat2","45.368");
        list.add(obj3);
        return AjaxResult.success(list);
    }
    @ApiOperation(value = "盒须图数据结构", response = AjaxResult.class)
    @RequestMapping(value = "boxline", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult boxlineTest() {
        String[] arr0 = {"34","54","67","75","86","28","38"};
        String[] arr1 = {"10","25","45","67","73","58","68"};
        String[] arr2 = {"24","34","45","55","66","48","58"};
        String[] arr3 = {"32","45","50","60","70","58","68"};
        String[] arr4 = {"44","59","70","75","88","38","48"};

        String[] arr7 = {"机电工程学院", "自动化学院", "计算机学院", "轻工化工学院", "材料与能源学院", "信息工程学院", "管理学院"};
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < arr7.length; i++) {
            JSONObject obj = new JSONObject();
            obj.put("name",arr7[i]);
            obj.put("min",arr0[i]);
            obj.put("q1",arr1[i]);
            obj.put("medium",arr2[i]);
            obj.put("q3",arr3[i]);
            obj.put("max",arr4[i]);
            obj.put("type","学分");
            list.add(obj);
        }
        return AjaxResult.success(list);
    }
    @ApiOperation(value = "桑基图接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "sankey", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult sankeyTest() {

        List<JSONObject> linksList = new ArrayList<>();
        JSONObject link1 = new JSONObject();
        JSONObject link2 = new JSONObject();
        JSONObject link3 = new JSONObject();
        JSONObject link4 = new JSONObject();
        JSONObject link5 = new JSONObject();

        link1.put("source","a");
        link1.put("target","a1");
        link1.put("value",5);
        linksList.add(link1);
        link2.put("source","a");
        link2.put("target","b1");
        link2.put("value",3);
        linksList.add(link2);
        link3.put("source","b1");
        link3.put("target","c");
        link3.put("value",1);
        linksList.add(link3);
        link4.put("source","c");
        link4.put("target","");
        link4.put("value",3);
        linksList.add(link4);
        link5.put("source","a1");
        link5.put("target","c");
        link5.put("value",2);
        linksList.add(link5);

        return AjaxResult.success(linksList);
    }
    @ApiOperation(value = "知识图谱数据结构", response = AjaxResult.class)
    @RequestMapping(value = "knowledge", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult knowledgeTest() {


        List<JSONObject> nodes = Lists.newArrayList();
        int nodeNum = (int) (Math.random() * 10) + 2;
        int categorys = (int) (Math.random() * 5) + 1;
        for (int i = 0; i < nodeNum; i++) {
            JSONObject node = new JSONObject();
            node.put("name", "node" + i);
            nodes.add(node);
        }

        List<JSONObject> links = Lists.newArrayList();
        for (int i = 0; i < nodeNum; i++) {
            if (Math.random() > 0.5) {
                JSONObject link = new JSONObject();
                link.put("name", "name" + i);
                link.put("source", nodes.get(i).get("name"));
                link.put("target", nodes.get( i % categorys).get("name"));
                link.put("category", i % categorys);
                links.add(link);
            }

        }

        return AjaxResult.success(links);
    }
    @ApiOperation(value = "旭日图数据结构", response = AjaxResult.class)
    @RequestMapping(value = "sunburst", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult sunburstTest() {
        List<JSONObject> lists = Lists.newArrayList();

        JSONObject child1 = new JSONObject();
        child1.put("name","小说");
        child1.put("value",4);
        child1.put("parent","");
        lists.add(child1);

        JSONObject child2 = new JSONObject();
        child2.put("name","技术");
        child2.put("value",8);
        child2.put("parent","");
        lists.add(child2);


        JSONObject grandson3 = new JSONObject();
        grandson3.put("name","平凡的世界");
        grandson3.put("value",3);
        grandson3.put("parent","小说");
        lists.add(grandson3);


        JSONObject grandson1 = new JSONObject();
        grandson1.put("name","代码整洁之道");
        grandson1.put("value",5);
        grandson1.put("parent","技术");
        lists.add(grandson1);

        JSONObject grandson2 = new JSONObject();
        grandson2.put("name","Three.js 开发指南");
        grandson2.put("value",3);
        grandson2.put("parent","技术");
        lists.add(grandson2);


        return AjaxResult.success(lists);
    }
    @ApiOperation(value = "文本框接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "text", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult textTest() {
        List<JSONObject> lists = Lists.newArrayList();

        JSONObject obj = new JSONObject();
        obj.put("text","这是一个通用文本");
        lists.add(obj);

        return AjaxResult.success("操作成功", lists);
    }

    @ApiOperation(value = "跑马灯接口数据结构", response = AjaxResult.class)
    @RequestMapping(value = "lamp", method = { RequestMethod.GET, RequestMethod.POST })
    public AjaxResult lampTest() {
        List<JSONObject> lists = Lists.newArrayList();

        JSONObject obj = new JSONObject();
        obj.put("text","这是一个跑马灯|第二个跑马灯|123456");
        lists.add(obj);

        return AjaxResult.success("操作成功", lists);
    }
}
