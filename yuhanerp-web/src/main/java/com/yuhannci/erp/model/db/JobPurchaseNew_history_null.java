package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;


@Data
public class JobPurchaseNew_history_null {
	
	String jobOrderNo;
	String partnerName;
	int issuePricell;
	int maxPrice;
	int minPrice;
	Date orderIssueDate;
	String unitNo;
	String description;
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
