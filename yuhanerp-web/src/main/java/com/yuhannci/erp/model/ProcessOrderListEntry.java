package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// 가공 발주 리스트 

@Data
public class ProcessOrderListEntry {
	
	Long id;	// 도면 ID
	Long uid;	// 발주PK
	
	String device;
	String customerName;
	String designUserName;
	
	String outsourcingPartnerId;
	String outsourcingPartnerName;
	
	String outsourcingRequestNo;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date outsourcingRequestDate;
	
	String designDrawingNo;
	
	String description;
	String dimension;
	String material;
	String thermal;
	
	Long quantity;
	Long spare;
	
	String postprocessing;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryPlanDate;
	
	Long unitPrice;
	String note;
	String drawingCurrentStatus;	
}
