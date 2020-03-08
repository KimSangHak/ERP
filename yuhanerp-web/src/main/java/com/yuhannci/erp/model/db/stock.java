package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class stock {
	
	Long id;
	Long jobPurchaseId;
	String requestId;
	String modelNo;
	String maker;
	String description;
	int issuePrice;
	int quantity;
	int abQuantity;
	int outQuantity;
	String deleted;
	
}
