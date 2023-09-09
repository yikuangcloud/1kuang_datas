package com.ruoyi.project.datav.domain;


/**
 * 数据大屏分享模板日志提交表单
 * @author wangying
 * @date 2021-02-08
 */
public class DatavShareDto {
	
	/** 大屏id */
    private String screenId;
    /** 备注 */
    private String remark;
    /** 分享用户列表 */
    private String[] userList;
    /** 提取码 */
    private String extractedCode;
    /** 有效期 */
    private String effectiveTime;
    
	public String getScreenId() {
		return screenId;
	}
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String[] getUserList() {
		return userList;
	}
	public void setUserList(String[] userList) {
		this.userList = userList;
	}
	public String getExtractedCode() {
		return extractedCode;
	}
	public void setExtractedCode(String extractedCode) {
		this.extractedCode = extractedCode;
	}
	public String getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

}
