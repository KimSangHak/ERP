package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class JobPurchase {
	
	Long id;
	Long jobDesignDrawingId;
	Long jobOrderId;
	String jobOrderNo;
	
	Date regDate;
	
	String description;
	String modelNo;
	String maker;
	
	String unitNo;
	String seq;
	
	String partnerId;
	String partnerName;
	
	Integer quantity;
	Integer spare;
	
	String code;
	String comment;
	String remark;
	
	Long designFileNo;
	
	Integer progress;
	
	String stage;	
	
	String deleted;
	String deleteReason;
	String deleteUserId;

	// EXTRA -----
	String device;

	
	public boolean checkDeleted(){
		return deleted != null && deleted.equalsIgnoreCase("Y");
	}
}
