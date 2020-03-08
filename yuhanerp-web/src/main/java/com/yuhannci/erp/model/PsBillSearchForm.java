package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PsBillSearchForm extends DataTableRequestBase {

	String orderNoBase;
	String orderNoExtra;
	String outsourcingPartnerId;
	Long jobOrderId;
	String orderNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date orderDateFrom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date orderDateTo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date carryInDateFrom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date carryInDateTo;
	Boolean includeProcessing;
	Boolean includePostprocessing;
	
	Integer draw;
}
