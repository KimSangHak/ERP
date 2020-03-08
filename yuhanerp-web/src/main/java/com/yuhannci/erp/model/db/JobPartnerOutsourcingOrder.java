package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class JobPartnerOutsourcingOrder {
	String id;
	Long  jobDesignDrawingId;
	Long  jobOrderId;
	String requestType;
	String partnerId;
	
	Date requestedDate;
	Date deliveryDate;
	Date deliveryPlanDate;
	
	String drawingUrl;
	Integer unitPrice;
	Integer quantity;
	Integer spare;
	String withPostprocessing;
	String isReceive;
	Long uid;
	Long statementOfAccountDetailId;
}
