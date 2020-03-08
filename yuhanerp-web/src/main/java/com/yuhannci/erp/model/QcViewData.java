package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QcViewData {
	Long id;
	Long jobDrawingId;
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
	String outSourceName;
	String workStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date finishDate;			//가공완료일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date installDate;			//납기일
	
	String workStatusText;
}
