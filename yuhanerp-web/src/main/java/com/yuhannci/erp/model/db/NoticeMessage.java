package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

// notice_message 테이블 맵핑용

@Data
public class NoticeMessage {
	Long id;

	// 알람 종류
	// G:일반, E:경고
	String alarmType;
	String alarmKind;
	String alarmManager;
	
	String deptCode, deptName;
	
	String messageType;
	String message;
	
	Date whenReceived;
	Date whenCreated;
	
	Long jobOrderId;
	String device;
	String customer;
	
	int sumprice;
	String kind;
	String useDate;
	String requestDate;
	Long leaveOverworkId;
	String conformStage;
	String roundNo;
	
	
	

}