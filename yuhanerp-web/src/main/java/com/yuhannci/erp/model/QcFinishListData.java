package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QcFinishListData
{
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
	String outSourceName;
	String coatingParterName;
	String finishDate;			//가공완료일
	
	String getDate;			//인계 받은 날짜
	String getFromId;
	String getFromName;
	String getToId;
	String getToName;
	String workStatus;
	String workStatusText;

	String startDate;		//검사 시작
	String stopDate;		//검사 중지 시간
	String requestedDate;		//검사 완료일
	String deliveDate;		//전달 날짜
	
	String resultOk;
	int okQuantity;
	int failQuantity;
	
	String putFromId;
	String putFromName;
	String putToId;
	String putToName;
	String holdReason;
}
