package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class JobInspect {

	Long id;
	
	String inspectOrderId;
	Long jobDrawingId;
	
	Date getDate;
	String getFromId;
	String getToId;
	
	String workStatus;
	
	Date startDate;
	Date stopDate;
	
	String resultOk;
	
	Date requestedDate;
	Date deliverDate;

	
	Long resultFileId;
	
	String putFromId;
	String putToId;
	
	Integer okQuantity;
	Integer failQuantity;
	
	int inspectProgress;
	
	String isDelivery;
	
	String holdReason;
}
