package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 
 * @author 张馨月
 * @Description: 数据源字段实体类
 *
 */
public class DatavSourse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//主键id
	private String id;
	//数据库类型
	private String databaseType;
	//IP地址
	private String ipAddress;
	//端口号
	private String port;
	//数据库名称
	private String databaseName;
	//用户名
	private String username;
	//密码
	private String password;
	//连接名称
	private String linkName;
	//必有字段
	private String delFlag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
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
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}
