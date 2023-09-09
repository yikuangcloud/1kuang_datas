package com.ruoyi.project.datav.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;

public class ShareTokenLog extends BaseEntity {

	private String id; // 主键id
	private String screenId; // 大屏id
	private String screenName;//大屏名称
	private String extractionNumber; // 提取码
	private int effectiveTime; // 失效时间(天)
	private String tokenStr; // token令牌
	private String senderId;//分享人
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expirationTime;//过期时间

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getExtractionNumber() {
		return extractionNumber;
	}
	public void setExtractionNumber(String extractionNumber) {
		this.extractionNumber = extractionNumber;
	}
	public int getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(int effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getTokenStr() {
		return tokenStr;
	}
	public void setTokenStr(String tokenStr) {
		this.tokenStr = tokenStr;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
}
