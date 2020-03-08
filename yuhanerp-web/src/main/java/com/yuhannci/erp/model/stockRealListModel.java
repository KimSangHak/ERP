package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class stockRealListModel {
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
	Date registrationDate;
	Date finalDate;
	
	

}
