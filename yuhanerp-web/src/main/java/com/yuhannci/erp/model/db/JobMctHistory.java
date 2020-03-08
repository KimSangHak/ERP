package com.yuhannci.erp.model.db;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class JobMctHistory {
	Long id;
	
	int mctId;

	String userId;
	String userName;
	
	String drawingId;			//파트번호
	
	Long drawingIdmct;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDatetime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDatetime;

	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행

	String orderNoBase;
	String orderNoExtra;
	String drawingNo;			//파트번호
	String drawingFullNo;
	
	Long workMin;
	
	int workQuantity;
	String finishStatus;
	String finishStatusName;
	
	String holdReason;
}
