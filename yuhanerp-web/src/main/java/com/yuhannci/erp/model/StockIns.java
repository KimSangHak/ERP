package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class StockIns {
	
	Long id;
	String modelNo;
	String description;
	String maker;
	int issuePrice;
	int quantity;

}
