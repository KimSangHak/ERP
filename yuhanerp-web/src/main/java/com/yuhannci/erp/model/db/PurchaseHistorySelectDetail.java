package com.yuhannci.erp.model.db;

import lombok.Data;


@Data
public class PurchaseHistorySelectDetail {
	
	String stage;
	String description;
	String modelNo;
	String maker;
	int qty;
	String unitNo;
	String jobOrderNo;
}
