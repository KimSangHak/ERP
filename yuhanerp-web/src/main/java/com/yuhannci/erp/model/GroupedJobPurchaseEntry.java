package com.yuhannci.erp.model;

import java.util.Date;

import lombok.Data;

@Data
public class GroupedJobPurchaseEntry {
	Long jobOrderId;
	String jobOrderNo;
	
	String partnerId;
	String partnerName;
	
	Integer count;
}
