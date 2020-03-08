package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class IssueMailText {
	String jobOrderNo;
	String partnerName;
	String device;
	Long jobOrderId;
	String code;
	String unitNo;
	String description;
	String seq;
	String comment;
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
