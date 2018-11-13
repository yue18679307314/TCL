package com.kuyu.task;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.MimeMessageHelper;  
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuyu.controller.WeixinController;

import org.springframework.stereotype.Service;

@Service
public class MailService {
	private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
	@Autowired  
    JavaMailSender mailSender;  
      
    @RequestMapping("sendemail")  
    public Object sendEmail(String toUser,String subject,String text)  
    {  
        try  
        {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom("sales_mailer@tcl.com");  
            message.setTo(toUser);  
            message.setSubject(subject);  //测试邮件主题
            message.setText(text);  //"测试邮件内容"
            this.mailSender.send(mimeMessage); 
            log.info("邮件发送成功："+toUser+":"+text);
            return "ok";  
        }  
        catch(Exception ex)  
        {  
        	log.info("邮件发送失败："+toUser);
            return "fail";  
        }  
    } 
	
}
