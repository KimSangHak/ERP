package com.yuhannci.erp.model.db;

import lombok.Data;

@Data
public class TransitionPurchase {
	Long id;
	Long preId;
	Long jobOrderId;
	Long jobPurchaseId;
	String kindPurchase;
	String kindRepass;
	Long stockId;
	Long stockHistoryId;
	String passUsr;
	String receiveDept;
	String receiverUsr;
	String complet;
	String jobOrderNo;
	int count;
	String userName;
	String deptName;
	String description;
	String modelNo;
	int warehousingQuantity;
	String abstock;
	String reYN;
	int quantity;
	int rquantity;
	String reReason;
	
	//컬럼이외값
	String receiveUsrName;

}
