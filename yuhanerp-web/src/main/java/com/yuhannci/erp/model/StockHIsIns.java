package com.yuhannci.erp.model;

import java.util.Date;

import lombok.Data;


@Data
public class StockHIsIns {
	
	Long id;
	Long stockID;
	Date registrationDate;
	String registrationUser;
	String registrationReason;
	String stockAble;
	int inQuantity;
	
	
	

}
