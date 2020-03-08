package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PostprocessSkipListEntry {

	Long drawingId;
	String designDrawingNo;
	String device;
	String description;
	String dimension;
	
	String outsourcingRequestId;
	String designUserName;
	String customerName;
	String material;
	String thermal;
	Integer quantity;
	Integer spare;
	String postprocessing;
	
	String outsourcingPartnerName;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date outsourcingRequestDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryPlanDate;	
	
	String checking;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	Date inspectDate;
	
	String resultOk;
	String inspectFileHash;
	String currentStage;
	
	public Long getId() {
		return drawingId;
	}
}
