package com.yuhannci.erp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

// application*.properties 에 emailer 설정 불러오는 빈
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix="emailer")
@Data
public class EmailConfig {

	String smtpId;
	String smtpPassword;
	String smtpHost;
	int smtpPort;
	String smtpFrom;
	String smtpProtocol;
}
