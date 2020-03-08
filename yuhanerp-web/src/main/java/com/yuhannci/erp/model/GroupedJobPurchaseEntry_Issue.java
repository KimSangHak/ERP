package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class GroupedJobPurchaseEntry_Issue {
	
	Long jobOrderId;
	String jobOrderNo;
	Date regDate;
	String device;
	String partnerId;
	String partnerName;
	String requestId;
	Date issueDate;
	Integer count;
	
}


