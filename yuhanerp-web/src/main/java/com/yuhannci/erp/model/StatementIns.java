package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class StatementIns {
	Long id;
	Long jobPurchaseId;
	Long jobOrderId;
	String modelNo;
	String requestId;
	String partnerId;

}
