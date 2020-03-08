package com.yuhannci.erp.model.db;

import lombok.Data;

@Data
public class StatementAccountDetailEntry {
	Long id;
	Long issueId;
	
	Long jobDesignDrawingId;
	
	Long jobOutsourcingOrderUid;
	Long jobPurchaseId;
	Long jobManagementId;
	
	String issuedItemName;
	Long issuedQuantity;
	Long issuedUnitPrice;
	
	// extra
	// 도면번호
	String representiveDrawingNo;
	
	// 공급가액
	public Long getSuppliedPrice() {
		return issuedQuantity * issuedUnitPrice;
	}
	
	// 세액
	public Long getSuppliedVat() {
		return (long) ((double)getSuppliedPrice() * (double) 0.1); 
	}
	
	// 합계
	public Long getSuppliedTotal() {
		return getSuppliedPrice() + getSuppliedVat();
	}
}
