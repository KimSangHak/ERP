package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// 가공 및 검사 현황 리스트 row

@Data
public class ProcessingAndCheckListEntry {

	Long id;
	
	String designDrawingNo;			// 도면번호
	String outsourcingOrderNo;		// 가공 발주번호
	
	String designUserName;
	
	String material;
	String thermal;
	Integer quantity;
	Integer spare;
	
	String postprocessing;
	
	String outsourcingPartnerName;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryDate;				// 가공 납기일
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryPlanDate;			// 가공 납기 예정일
	
	String processStatus;			// 가공 상태
	String checking;				// 검사 여부
	@JsonFormat(pattern="yyyy-MM-dd")
	Date inspectDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date inspectPlanDate;
	
	String testResult;
	String inspectFileHash;
	String currentStage;
	
	
}
