package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class JobMctViewData {
	Long drawingId;
	String description;			//설명
	String material;			//소재
	String thermal;				//열처리
	String postprocessing;		//후처리
	int quantity;
	int spare;
	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;			//도면번호
	String drawingFullNo;		//전체 도면번호
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date finish_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;			//납기일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date processDate;			//가공완료예정일
	String workStatusText;
	
	int mctHisId;
	int mctNum;
	String userId;
	String userName;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date startDatetime;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date endDatetime;
	int workQuantity;
	String finishStatus;
	String finishStatusName;
}
