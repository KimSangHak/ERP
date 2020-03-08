package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class stockOutHistory {
	
	Long id;
	Long stockID;
	Date passDate;
	String passUser;
	String receiverUsr;
	int outQuantity;
	String outReason;
	Long jobOrderId;
	String receiveDept;

}
