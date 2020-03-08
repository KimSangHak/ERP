package com.yuhannci.erp.model.db;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StatementEntry {
	
	String id;				// id @ job_partner_outsourcing_order
	String partnerId;
	String requestType;
	String jobOrderId;
	
	String partnerName;
	String outsourcingOrderNo;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date requestDate;
	
	String representiveDrawingNo;
	String representiveCustomerName;
	String representiveDevice;
	
	Integer drawings;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryDate;
	Integer orderTotal;
	
	String customerName;
	
}
