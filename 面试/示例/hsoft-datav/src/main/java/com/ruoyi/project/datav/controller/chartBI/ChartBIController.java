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

@RestController
@RequestMapping("/chart/BI")
public class ChartBIController {
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

        JSONArray arr = null;
        JSONObject obj = null;
        try {
            obj = DBUtil.executeCustomSql(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
            if(obj.get("msg").toString().equals("success")){

                arr = obj.getJSONArray("result");
                for (int j = 0; j < arr.size(); j++) {
                    JSONObject object = (JSONObject) arr.get(j);
                    bars.add(object);

                }

            }else{
                return AjaxResult.error("sql执行错误");
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
