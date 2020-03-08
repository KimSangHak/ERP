package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProcessHisSearchForm extends DataTableRequestBase {
	
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;
	
	
	
	Integer draw;

}
