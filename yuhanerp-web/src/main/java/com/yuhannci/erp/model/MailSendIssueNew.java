package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class MailSendIssueNew {
	
	Long id;
	String jobOrderNo;
	String partnerName;
	int issuePricell;
	int MaxPrice;
	int MinPrice;
	Date OrderIssueDate;
	String device;
	int stockQuantity;
	int abQuantity;
	String unitNo;
	String description;
	String seq;
	int spare;
	int quantity;
	Long jobOrderId;
	String partnerId;
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
