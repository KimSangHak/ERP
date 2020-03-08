package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// 생산일정관리 > 가공발주 리스트 테이블 항목 맵핑

@Data
public class ProcessOutsourceListData {
	
	Long uid;				// 발주 내역 테이블 SEQ
	
	String id;				// 발주ID
	Long jobDrawingId;

	String requestType;
	String partnerId;

	String outOrderNo;		//발주번호
	String partnerName;		//발주처

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date requestedDate;			// 발주일

	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date processFinishDate;	// 납기일

	String customerName;		// 고객사명
	String device;
	
	String drawingNumber;
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;		// 도면번호
	
	String designUserName;
		
	String description;
	String dimension;
	String material;
	String thermal;
	int quantity;
	int spare;
	
	String postprocessing;

	@JsonFormat(pattern="yyyy-MM-dd")
	Date deliveryDate;		// 납기(예정)일
	
	Integer unitPrice;		// 발주 단가
	
	String withPostprocessing;
	String drawingCurrentStatus;
	
	String note;

	public String isWithPostprocessText(){
		if (withPostprocessing != null && withPostprocessing.equalsIgnoreCase("Y"))
			return "후처리 포함";
		else
			return "";
	}
}
