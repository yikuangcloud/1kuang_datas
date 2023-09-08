package com.ruoyi.project.datav.service;

import com.alibaba.fastjson.JSONArray;
import org.apache.poi.ss.formula.functions.T;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartDBService
 * @Description: 图表数据分析数据库服务接口
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/3/25 14:02
 */

public interface ChartDBService {

   public JSONArray getDistinctOneField(String sql);

   public JSONArray getRSTemplate(String sql);
}
