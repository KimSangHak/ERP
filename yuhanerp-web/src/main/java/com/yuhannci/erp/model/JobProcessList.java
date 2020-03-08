package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class JobProcessList
{
	Long id;
	Long orderId;
	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;			//도면번호
	String drawingFullNo;		//전체 도면번호
	String drawingUserName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;		//납품일(D-DAY)

	String description;			//설명
	String material;			//소재
	String thermal;				//열처리
	String postprocessing;		//후처리
	int quantity;
	int spare;

	String currentStage;
	String currentStageText;
	String workStatus;
	String workStatusText;
	
	String outSourceName;
	String processCompleted;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date processFinishDate;		//가공완료일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date processFinishPlanDate;		//가공완료 예정일
	
	String checking;		//검사 필요여부
	
	String assemblyTransferFrom;
	String assemblyTrFromName;	//인계자
	String assemblyTransferTo;
	String assemblyTrToName;	//인수자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date assemblyTransferDate;			//인수날짜

	Long inspectId;			//검사 ID
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inspectPlanDate;		//실 배송일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date inspectDate;		//검사 완료일
	String inspectResult;	//검사 판정(NG/PASS)
}
