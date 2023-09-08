package com.ruoyi.project.datav.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.ChartDBInfo;
import com.ruoyi.project.datav.util.DBUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartDBController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/2/24 11:22
 */

//@Api(value = "图表组件数据库链接控制", tags = { "图表组件数据库链接控制" })
//@CrossOrigin
@RestController
@RequestMapping("/chart/DB")
public class ChartDBController {

    //@ApiOperation(value = "测试连接", response = AjaxResult.class)
    @PostMapping(value = "testConnection")
    public AjaxResult testConnection(@RequestBody ChartDBInfo chartDBInfo) {

        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";

        try {
            Connection conn = DBUtil.getConnection(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword());
            DBUtil.setDataSourceType(conn);
            System.out.println(conn);
        } catch (Exception e) {
            return  AjaxResult.error("连接数据库失败");
        }

        return AjaxResult.success();
    }

    //@ApiOperation(value = "获取数据库下所有表", response = AjaxResult.class)
    @PostMapping(value = "getAllTable")
    public AjaxResult getAllTable(@RequestBody ChartDBInfo chartDBInfo) {
        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = "select table_name,table_comment from information_schema.tables WHERE table_schema='" + chartDBInfo.getBaseName() + "'";
        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(arr);
    }

    @PostMapping(value = "getAllField")
    public AjaxResult getAllField(@RequestBody ChartDBInfo chartDBInfo) {
        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = "SELECT column_name as columnName, data_type as dataType, column_comment as comment from INFORMATION_SCHEMA.COLUMNS WHERE table_name = '" + chartDBInfo.getTableName() + "' AND table_schema = '" + chartDBInfo.getBaseName() + "'";
        System.out.println(sql);
        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(arr);
    }

    @PostMapping(value = "getDistinctOneField")
    public AjaxResult getDistinctOneField(@RequestBody ChartDBInfo chartDBInfo) {

        String drive = null;
        if (chartDBInfo.getType().equals("mysql")) {
            drive = "com.mysql.cj.jdbc.Driver";
        }
        String url = "jdbc:mysql://" + chartDBInfo.getIpAdress() + ":" + chartDBInfo.getPort() + "/" + chartDBInfo.getBaseName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String sql = "SELECT DISTINCT " + chartDBInfo.getFieldName() + " as fieldName from " + chartDBInfo.getTableName() + " where "+ chartDBInfo.getFieldName() + " is not null";
        System.out.println(sql);
        JSONArray arr = null;
        try {
            arr = DBUtil.execute(drive, url, chartDBInfo.getUsername(), chartDBInfo.getPassword(), sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return AjaxResult.error("操作错误");
        }
        return AjaxResult.success(arr);
    }

}
