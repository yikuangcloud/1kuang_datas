package com.ruoyi.project.system.service;
/**
 * @author zxy
 * @Description: 邮件发送
 *
 */
public interface ISysEmailService {

	/**
     * 发送HTML邮件，方便用户点击附带的code用来验证激活账户
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String to, String subject, String content);
	
	
}
