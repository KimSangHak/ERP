package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;


@Data
public class stockRealOutModel {
	Long id;
	Long did;
	Long stockID;
	String passUser;
	String outReason;
	String maker;
	String modelNo;
	String description;
	int outQuantity;
	String receiverUsr;
	Date passDate;	
	String orderNo;
	String pusr;
	String rusr;
	String deptName;
	String receiveDept;
	Long jobOrderId;

}
