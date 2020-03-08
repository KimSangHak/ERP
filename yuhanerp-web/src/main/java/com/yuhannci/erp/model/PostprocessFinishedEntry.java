package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PostprocessFinishedEntry {

	// 도면ID
	Long id;
	
	// 도면번호
	String designDrawingNo;
	// 설계자
	String designUserName;
	String description;
	String material;
	String thermal;
	
	// 수량
	Integer quantity;
	Integer spare;
	
	// 후처리
	String postprocessing;
	// 가공업체
	String outsourcingPartnerName;
	
	// 후처리 가공 납기일
	@JsonFormat(pattern="yyyy-MM-dd")
	Date outsourcingDeliveryDate;
	
	
	// 검사여부
	String checking;
	// 검사일
	@JsonFormat(pattern="yyyy-MM-dd")
	Date inspectDate;
	// 판정
	String testResult;		
	// 성적서 FileID
	String inspectFileHash;		
	
	String currentStage;
}
