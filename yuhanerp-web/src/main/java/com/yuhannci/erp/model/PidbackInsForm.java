package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class PidbackInsForm {
	
	Long orderId;
	Long pid;
	String userId;
	String deptCode;
	String title;
	String body;
	String kind;

}
