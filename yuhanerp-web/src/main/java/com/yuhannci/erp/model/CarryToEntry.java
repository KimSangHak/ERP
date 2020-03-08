package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CarryToEntry {
	Long id;
	
	String designDrawingNo;
	
	String designUserId;
	String designUserName;
	
	String material;
	String thermal;
	Integer quantity;
	Integer spare;
	String postprocessing;
	
	String outsourcingPartnerName;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryPlanDate;
	
	String checking;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date inspectPlanDate;
	
	String testResult;
	
	String inspectFileHash;
	
	String drawingCurrentStatus;	
}
