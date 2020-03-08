package com.yuhannci.erp.model;

import java.util.Date;

import lombok.Data;

@Data
public class BulletinOrderEntry {
	Long id; 
	String orderNo;
	String device;
	String customerName;
	Date shippingDate;
	String status;
	String orderDate;
	String installDate;
	
	String designDate;
	Integer designProgress;
	String processDate;
	Integer processProgress;
	String purchaseDate;
	Integer purchaseProgress;
	String assemblyDate;
	Integer assemblyProgress;
	String programDate;
	Integer programProgress;	
}
