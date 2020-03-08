package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class AndroidData {
	
	Long id;
	String orderNo;
	String message;
	String whenCreated;
	String device;
	String customer;
	int sumprice;
	String kind;
	String useDate;
	String requestDate;
	Long leaveOverworkId;
	String conformStage;

}
