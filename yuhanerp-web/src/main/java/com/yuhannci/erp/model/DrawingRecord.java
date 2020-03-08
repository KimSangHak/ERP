package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 설비 작업 지시 등록
// 에서 입력해서 전송한 양식 맵핑

@Data
public class DrawingRecord {

	Long id;

	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	
	String customerId;
	String customerName;

	String orderNo;
	String orderNoBase;
	String orderNoExtra;
	
	String device;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;		// 납품일(설치일)
	
	String businessUserId;
	String businessUserName;
	String designUserId;
	String designUserName;
	
	int designProgress;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date designDate;		// 출도예정일
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date designEnd;		// 출도일


	int designCount;	//도면 수량

	public String isJigText(){
		if (orderType != null && orderType.equalsIgnoreCase("JIG"))
			return "지그";
		else
			return "설비";
	}
}
