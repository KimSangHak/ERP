package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// job_order 테이블
// 설비진행 및 치공구 진행 관련

@Data
public class JobDesignDrawingOutSource {

	Long id;
	Long orderId;		//job_order의 id
	String jobOrderNo;			// 작업지시번호
	String designDrawingNo;		//도면번호(FULL)	
	String customerName;

	String device;
	
	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	String orderNoBase;	//원 작업지시코드
	String orderNoExtra;
	String drawingNo;			//파트번호
	
	String designUserName;		//설계담당자
	
	String description;		//도면 설명
	String dimension;		//가공 사이즈(원재료 사이즈)
	String material;		//원재료 종류
	String thermal;			//열처리
	int quantity;			//수량
	int spare;				//여분수량
	String workPosition;	//사내, 외주
	String postprocessing;	//후처리 종류
	Date processFinishDate;					//'가공 완료 일
	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date processFinishPlanDate;				//'가공 완료 예정일

	Long outUid;

	public boolean isJigJob(){
		return orderType != null && orderType.equalsIgnoreCase("JIG");
	}
}
