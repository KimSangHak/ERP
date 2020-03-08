package com.yuhannci.erp;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailSender {
	@Autowired EmailConfig emailConfig;

	@Bean
	public JavaMailSender createJavaMailSender(){
		Properties prop = new Properties(); 
		prop.setProperty("mail.smtp.starttls.enable", "true");
		//prop.setProperty("mail.debug", "true");		// SMTP 통신 내용 디버그
		
		JavaMailSenderImpl mail = new JavaMailSenderImpl();
		mail.setJavaMailProperties(prop);
		mail.setProtocol(emailConfig.getSmtpProtocol());		
		mail.setHost(emailConfig.getSmtpHost());
		mail.setUsername( emailConfig.getSmtpId());
		mail.setPassword(emailConfig.getSmtpPassword());
		mail.setPort(emailConfig.getSmtpPort());
		
		return mail;
	}
}
