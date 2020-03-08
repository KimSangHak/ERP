package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// 생산일정관리 > 가공발주 리스트 테이블 항목 맵핑

@Data
public class DrawingInspect {
	Long id;
	Long orderId;
	String drawingNumber;
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;
	
	String description;			//설명
	String demension;			//영역
	String material;			//재료종류
	String thermal;				//열처리
	int quantity;
	int spare;
	String postprocessing;		//후처리 종류
	String checking;			//검사여부(검사를 해야하는지 여부)
	
	String currentStage;		//현재공정 위치
	String workStatus;			//현재공정 상태
	String workPosition;		//가공위치
	String processCompleted;	//가공완료여부
	String drawingCurrentStatus; //도면상태
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date registrationDate;			//생관 가공일정 등록 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date finishDate;			// 가공완료일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date coatingDate;			// 후처리완료일(생관에서 등록한 후처리 완료일)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inspectDate;			// 검사완료일(생관에서 등록한 후처리 완료일)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date drawingDeliveryDate;	// 가공완료일(생관에서 등록한 납기일 완료일, 외주및사내)

	String designUserName;	//설계자

	String outsourcingPartnerId;	//외주업체 코드
	String partnerName;		//발주처
	String outOrderNo;		//발주번호
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date deliveryDate;		// 납기일
	String withPostprocessing;
	
	Long inspectId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inspectedDate;		//검사완료 등록일
	String inspectedOk;		//검사결과
	int okQuantity;			//합격 수량
	int failQuantity;		//불합격 수량
	int inspectProgress;	//검사진행율
	String inspectFileHash;

	public String isWithPostprocessText(){
		if (withPostprocessing != null && withPostprocessing.equalsIgnoreCase("Y"))
			return "후처리 포함";
		else
			return "";
	}
}
