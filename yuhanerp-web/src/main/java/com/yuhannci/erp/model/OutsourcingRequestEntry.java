package com.yuhannci.erp.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.yuhannci.erp.model.db.JobDesignDrawing;

import lombok.Data;

/**
 * 외주 견적/요청 메일 보낼 때
 * 본문에 삽입될 대상 목록 
 * 
 * @author kth
 *
 */
@Data
public class OutsourcingRequestEntry {

	Long jobOrderId;
	String jobOrderNo;
	
	// 요청일
	Date requestedDate;
	
	// 납기일
	Date dueDate;	
	String device;
	Boolean withPostProcessing;
	
	List<JobDesignDrawing> drawings;	
	
	public OutsourcingRequestEntry(){
		drawings = new LinkedList<>();
	}
}
