package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class PibackPurchase {
	
	Long id;
	Long jobOrderId;
	String description;
	String modelNo;
	String maker;
	int quantity;
	int spare;
	String jobOrderNo;

}
