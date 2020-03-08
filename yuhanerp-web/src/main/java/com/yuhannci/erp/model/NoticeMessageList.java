package com.yuhannci.erp.model;

import java.util.Date;

import lombok.Data;

// notice_message 테이블 맵핑용

@Data
public class NoticeMessageList {
	Long id;

	// 알람 종류
	// G:일반, E:경고
	String alarmType;	
	
	String deptCode, deptName;
	
	String messageType;
	String message;
	
	Date whenReceived;
	Date whenCreated;
	
	String orderNo;
	String device;

}