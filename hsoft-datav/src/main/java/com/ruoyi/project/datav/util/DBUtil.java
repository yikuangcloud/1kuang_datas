package com.ruoyi.project.datav.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DBUtil
 * @Description: 数据库操作工具类
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/2/23 16:06
 */

public class DBUtil {

    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<Connection> CONTEXT_HOLDER = new ThreadLocal<>();

    public static Connection getConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        //Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(driver);
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONArray execute(String driver, String url, String user, String password, String sql) throws SQLException, ClassNotFoundException {

        JSONArray array = new JSONArray();

        Connection conn = getConnection(driver, url, user, password);
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // 获取列数
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 遍历ResultSet中的每条数据
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();

                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName =metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                array.add(jsonObj);
            }

        }catch (SQLException e){
            try {
                conn.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return array;
    }

    public static JSONObject executeCustomSql(String driver, String url, String user, String password, String sql) throws SQLException, ClassNotFoundException {

        JSONObject obj = new JSONObject();

        JSONArray array = new JSONArray();

        Connection conn = getConnection(driver, url, user, password);
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // 获取列数
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 遍历ResultSet中的每条数据
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();

                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName =metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                array.add(jsonObj);
            }

            obj.put("result",array);
            obj.put("msg","success");

        }catch (SQLException e){
            obj.put("msg","error");
            try {
                conn.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return obj;
    }

    /**
     * 设置数据链接的变量
     */
    public static void setDataSourceType(Connection conn)
    {
        CONTEXT_HOLDER.set(conn);
    }

    /**
     * 获得数据链接的变量
     */
    public static Connection getDataSourceType()
    {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据链接变量
     */
    public static void clearDataSourceType()
    {
        CONTEXT_HOLDER.remove();
    }
}
