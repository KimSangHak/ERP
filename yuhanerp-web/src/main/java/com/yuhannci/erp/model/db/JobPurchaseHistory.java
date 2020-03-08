package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class JobPurchaseHistory {
	
	Long jobPurchaseId;
	String requestId;
	String requestType;
	String modelNo;
	String maker;
	int estimatedPrice;
	Date issueDate;
	int issuePrice;
	int orderQuantity;
	int stockUseQuantity;
	Long stockId;
	Date receiveDate;
	
}
