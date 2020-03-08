package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RequestEstimateFormData {
	// 후처리도 함께 요청
	boolean withpp;
	String outPartnerId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date[] deliveryDate;
	
	String[] drawingUrl;
	Long[] jobDrawingId;	
	Integer[] unitPrice;
	Long[] jobDrawingOutUid;
	String[] workPosition;
}
