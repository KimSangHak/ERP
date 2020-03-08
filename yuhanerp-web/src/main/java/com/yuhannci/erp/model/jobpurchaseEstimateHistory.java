package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class jobpurchaseEstimateHistory {
	String requestId;
	Date issueDate;
	String jobOrderNo;
	String unitNo;
	String description;
	String modelNo;
	String maker;
	String partnerName;
	int orderQuantity;
	Date receiveDate;
	int estimatedPrice;
	
	
	
}
