package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class innerstockListModel {
	
	String jobOrderNo;
	String partnerName;
	String unitNo;
	String description;
	int stockQuantity;
	int abQuantity;
	int quantity;
	int wareHousingQuantity;
	int outQuantity;
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
