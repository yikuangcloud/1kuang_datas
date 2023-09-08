package com.ruoyi.project.system.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ruoyi.project.system.service.ISysEmailService;

/**
 * @author zxy
 * @Description: 邮件发送
 *
 */
@Service
public class SysEmailServiceImpl implements ISysEmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    /**
     * 配置文件中我的163邮箱
     */
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送HTML邮件
     *
     * @param to      收件者
     * @param subject 邮件主题
     * @param content 文本内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            //邮件发送人
            helper.setFrom(from);
            //邮件主题
            helper.setSubject(subject);
            //邮件接收人
            helper.setTo(to);
            //邮件内容
            helper.setText(content, true);
            //发送邮件
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }

}
