package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class StatementInsPop {
	
	Long id;
	Long issueId;
	Long jobDesignDrawingId;
	Long jobPurchaseId;
	String issuedItemName;
	int issuedQuantity;
	int issuedUnitPrice;
	String jobOrderNo;
	String partnerName;
	int orderQuantity;
	String unitNo;
	String description;
	Date wareHousingDate;	
	int warehousingQuantity;
	String modelNo;
	String maker;
	int issuePrice;

}
