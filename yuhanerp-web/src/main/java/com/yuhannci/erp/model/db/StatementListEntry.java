package com.yuhannci.erp.model.db;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StatementListEntry {
	Long id;
	
	String partnerId;
	String partnerName;
	
	String statementNo;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date issueDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	Date regDatetime;
	
	String outsourcingOrderNo;
	@JsonFormat(pattern="yyyy-MM-dd")
	Date outsourcingRequestDate;
	
	String representiveDrawingNo;
	Integer drawingCount;
	
	Long amount;	// net + vat
	public Long getNet() {
		return amount; // (long) Math.ceil( ((double)amount / (double)1.1) );
	}
	
	public Long getVat() {
		return (long)((double)amount * (double)0.1); // - getNet();
	}
}
