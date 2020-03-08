package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class JobMctHistory {
	Long id;

	String orderType; // "JOB" : 설비진행, "JIG" : 치공구 진행
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;			//도면번호
	String drawingFullNo;		//전체 도면번호
	String description;			//설명
	String material;			//소재
	String thermal;				//열처리
	String postprocessing;		//후처리
	int quantity;
	int spare;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;			//납기일
	
	int mctId;
	String userId;
	String userName;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date startDatetime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date endDatetime;
	int workTime;
	int workQuantity;
}
