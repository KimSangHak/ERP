package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CarryInEntry {

	Long id;
	
	String jobOrderNo;
	
	/*
	 * 도면번호
	 */
	String designDrawingNo;	
	/*
	 * 발주번호
	 */
	String processIssueNo;
	/*
	 * 설계자
	 */
	String designUserName;
	/*
	 * Mat'l
	 */
	
	String workPosition;
	
	String description;
	
	String material;
	/*
	 * 열처리
	 */
	String thermal;
	/*
	 * 수량
	 */
	Integer quantity;
	/*
	 * Spare 수량
	 */
	Integer spare;
	/*
	 * 후처리 여부(Y/N)
	 */
	String postprocessing;
	/*
	 * 외주 업체명
	 */
	String outsourcingPartnerName;
	
	Long outUid;
	
	String requestDate;
	
	// 외주 가공 납기일
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryDate;
	// 외주 가공 납기 예정일
	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryPlanDate;
	/*
	 * 외주 가공 발주ID
	 */
	String outsourcingOrderNo;
	
	/*
	 * 검사 여부 (Y/N)
	 */
	String checking;
	/*
	 * 검사 일자
	 */
	Date inspectDate;

	/*
	 * 판정
	 */
	String testResult;
	
	// 검사 결과 파일
	String inspectFileHash;
}
