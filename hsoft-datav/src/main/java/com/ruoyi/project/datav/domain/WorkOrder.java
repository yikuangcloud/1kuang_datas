package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class WorkOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//主键id
	private String id;
	//当前产品
	private String product;
	//当前问题
	private String problem;
	//优先级（0.重要  1.一般）
	private String priority;
	//急速工单（0.使用  1.不使用）
	private String quickWorkOrder;
	//问题描述
	private String description;
	//手机号码
	private String phone;
	//接收短信提醒（0.任何时间  1.每天9：00-18：00  2.从不接受）
	private String message;
	//邮箱
	private String email;
	//附件
	private String enclosure;
	//是否受理（已读）
	private String isRead;
	//是否解决
	private String isComplete;
	//回复内容
	private String replyContent;
	//删除标识
	private String delFlag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getQuickWorkOrder() {
		return quickWorkOrder;
	}
	public void setQuickWorkOrder(String quickWorkOrder) {
		this.quickWorkOrder = quickWorkOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}
