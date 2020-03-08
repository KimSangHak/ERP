package com.yuhannci.erp.model.db;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

// job_order 테이블
// 설비진행 및 치공구 진행 관련

@Data
public class JobDesignDrawing {

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
	String postprocessing;	//후처리 종류
	String classification;	//분류(신규제작, 추가제작,...)
	String reason;			//사유(설계불량, 업체요구,..)
	
	String checking;		//검사 여부(Y/N)
	
	String note;			//비고

	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date designDate;		// 출도일

	String currentStage; //'설계A, 생관B, 가공C, 검사D, 조립E, 배선F, 후처리G'
	String workStatus; //'R:대기, Q:완료, C:취소, I:작업중, H:홀딩, S:중지'
	int workQuantity;	//가공완료 수량
	String workPosition; //'사내(I)-In, 외주(O)-Out'
	
	String outsourcingPartnerId; //외주 업체 코드
	String outsourcingPartnerName;
	
	// 외주 가공 요청 ID
	String outsourcingRequestId;
	// 외주 가공 요청 시각
	Date outsourcingRequestDate;
	
	// 외주 가공 견적 요청 ID
	String outsourcingEstimateRequestId;
	// 외주 가공 견적 요청 시각
	Date outsourcingEstimateRequestDate;
	
	String passStay;

	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date outsourcingDeliveryDate;			//'외주가공 납기일 완료일(사내가공은 가공완료일과 동일하게 등록)',
	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date processFinishDate;					//'가공 완료 일
	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date processFinishPlanDate;				//'가공 완료 예정일

	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date coatingDate;		// 후처리 완료일
	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date coatingFinishPlanDate;		// 후처리 완료 예정일
	
	String processCompleted;
	
	String coatingPartnerId;
	
	String coatingRequestId;
	
	String coatingStage;
	
	String coatingRequested;
	
	String 	holdReason;		//작업 취소/홀딩/중지 사유
	
	
	Long inspectId;
	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")
	Date inspectDate;		//'검사 완료일'
	@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern="yyyy-MM-dd")	
	Date inspectPlanDate;		// 후처리 완료 예정일
	
	// 검사 결과 파일
	String inspectFileHash;
	
	String testResult;
	
	public boolean isJigJob(){
		return orderType != null && orderType.equalsIgnoreCase("JIG");
	}
}
