package com.yuhannci.erp.model.db;

import lombok.Data;

@Data
public class TransitionProcessing {
	
	Long id;
	Long preId;
	Long jobOrderId;
	Long jobDesignDrawingId;
	String kindRepass;
	String passUsr;
	String receiveDept;
	String receiverUsr;
	String complet;
	String reYN;
	int quantity;
	int rquantity;
	String reReason;
	
	//컬럼이외 값
	String jobOrderNo;
	String designDrawingNo;
	int count;
	String userName;
	String deptName;
	String description;
	String dimension;
	String receiveUsrName;

}
