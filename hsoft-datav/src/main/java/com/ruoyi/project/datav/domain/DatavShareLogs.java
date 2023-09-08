package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
/**
 * 数据大屏分享模板日志datav_share_logs
 * @author wangying
 * @date 2021-02-08
 */
public class DatavShareLogs extends BaseEntity{
	
	 private static final long serialVersionUID = 1L;
	 /**主键id */
	 private String id;
	 /**发送人id */
	 private String senderId;
	 /**接收人id */
	 private String receiverId;
	 /** 原模板id*/
	 private String primaryScreenId;
	 /** 分享模板id*/
	 private String copyScreenId;
	 /**留言详情 */
	 private String message;
	 /**日志状态 0=正常,1=停用 */
	 private String status;	 
	 /** 删除标志（0代表存在 1代表删除） */
	 private String delFlag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getPrimaryScreenId() {
		return primaryScreenId;
	}
	public void setPrimaryScreenId(String primaryScreenId) {
		this.primaryScreenId = primaryScreenId;
	}
	public String getCopyScreenId() {
		return copyScreenId;
	}
	public void setCopyScreenId(String copyScreenId) {
		this.copyScreenId = copyScreenId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	 
}
