package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class EstimateListUpdated {
	Long jobPurchaseId;
	String estimateRequestId;
	Long jobOrderId;
	Date receiveDate;
	int estimatedPrice;
	int orderQuantity;

}
