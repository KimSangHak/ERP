package com.yuhannci.erp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="yerp")
public class YerpConfig {

	Integer maxSelectionCount;
	String domainName;
	
	// 프로그램 처음 시작시 과거 전송 상태로 남아있는 상태를 원복시킨다
	Boolean resetSendingEmail;
}
