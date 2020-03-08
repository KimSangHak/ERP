package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class SeparatedDrawingNo {
	
	String orderNoBase;			// 작업지시번호-앞단
	String orderNoExtra;		// 작업지시번호-뒷단
	String drawingNo;			// 도면 파트 번호
}
