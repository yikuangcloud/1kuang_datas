package com.ruoyi.project.datav.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: ChartDBInfo
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/2/24 11:31
 */

public class ChartDBInfo {

    private String type;

    private String driver;

    private String ipAdress;

    private String port;

    private String baseName;

    private String username;

    private String password;

    private String tableName;

    private String fieldName;

    private String executeSql;

    private JSONObject chartStucture;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(String executeSql) {
        this.executeSql = executeSql;
    }

    public JSONObject getChartStucture() {
        return chartStucture;
    }

    public void setChartStucture(JSONObject chartStucture) {
        this.chartStucture = chartStucture;
    }
}
