package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class innerAndoutModel {
	
	Long jobPurchaseId;
	int warehousingQuantity;
	String outUser;
	String warehousingUser;
	String passUsr;
	String receiverUsr;
	int outQuantity;
	String outReason;

}
