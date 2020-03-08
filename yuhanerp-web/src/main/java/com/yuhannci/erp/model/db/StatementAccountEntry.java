package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class StatementAccountEntry {
	Long id;
	
	Date issueDate;
	
	String partnerTypeKind;
	String partnerId;
	
	Date regDatetime;
	
	String requestId;
	
	Long jobOrderId;
	Long sumPrice;
	Long negoPrice;
	
	String buyKind;

	// extra -----------
		
	String partnerName;
	String corporateNum;
	String corporatePhone;
	
}
