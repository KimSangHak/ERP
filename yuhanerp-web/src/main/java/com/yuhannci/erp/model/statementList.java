package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class statementList {
	
	Long id;
	String jobOrderNo;
	String partnerName;
	String requestId;
	Date issueDate;
	int count;
	int sumPrice;

}
