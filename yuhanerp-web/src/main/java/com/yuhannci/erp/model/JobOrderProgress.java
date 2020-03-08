package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class JobOrderProgress
{
	Long id;
	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date orderDate;			//가공완료일
	String customerId;			//고객사 코드
	String customerName;		//고객사 명
	String orderFullNo;			//Order No
	String currentStage;		//진행 상태
	String device;				//설명
	String customerUser;		//고객사 담당 이름
	String businessUserId;		//영업담당
	String businessUserName;	//영업담당
	String designUserId;		//설계담당
	String designUserName;		//설계담당
	String assemblyId;			//조립담당
	String assemblyName;		//조립담당
	String pgmId;				//프로그램 담당
	String pgmName;				//프로그램 담당
	String wiringId;			//배선담당
	String wiringName;			//배선담당
	String note;				//비고
	int quantity;
	Long internalUnitPrice;		//내부단가
	Long estimatedPrice;		//견적단가
	Long negotiatedPrice;		//네고 금액
	String purchaseNone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date shippingDate;		//출고예정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date deliveryDate;		//실 배송일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;			//셋업까지 모두 종료 예정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date realInstallDate;		//영업실 완료 예정일

	int designProgress;			//설계 진행율
	String designStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date designDate;			//설계완료 예정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date designStart;			//설계 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date designEnd;			//설계 종료일

	int assemblyProgress;		//조립 진행율
	String assemblyStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date assemblyDate;		//조립완료 예정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date assemblyStart;		//조립 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date assemblyEnd;			//조립 종료일

	int programProgress;		//프로그램 진행율
	String programStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date programDate;			//프로그램완료 예정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date programStart;		//프로그램 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date programEnd;			//프로그램 종료일

	String wiringStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date wiringDate;			//배선 완료 예정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date wiringStart;			//배선 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date wiringEnd;			//배선 종료일
	
	int processProgress;		//가공 진행율
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date processDate;			//가공 완료 예정일

	int purchaseProgress;		//구매 진행율
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date purchaseDate;		//구매 완료 예정일
	
	Long setupId;

	int drawingCount;			//가공 도면 전체 수
	int drawingComplete;		//가공 완료 도면수
	int purchaseCount;			//구매품 전체 수
	int purchaseInCount;		//구매품 입고수량

	public int GetProcessProg(){
		int _perVal = 0;
		if (drawingCount>0)
			_perVal = Integer.valueOf(drawingComplete/drawingCount)*100;
		return _perVal;
	}
	public int GetPurchaseProg(){
		int _perVal = 0;
		if (purchaseCount>0)
			_perVal = Integer.valueOf(purchaseInCount/purchaseCount)*100;
		return _perVal;
	}
}
