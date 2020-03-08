package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class innerStockpop {
	
	String jobOrderNo;
	String partnerName;
	int stockQuantity;
	int abQuantity;
	int quantity;
	String unitNo;
	String description;
	Long jobOrderId;
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
