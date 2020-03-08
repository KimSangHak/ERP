package com.yuhannci.erp.model.db;


import java.util.Date;
import lombok.Data;

@Data
public class Purchase_E_ab {
	
	 String estimateRequestId;
	 Long jobOrderId;
	 Long jobPurchaseId;
	 String partnerId;
	 Date regDate;
	 String description;
	 String maker;
	 String modelNo;
	 String material;
	 int estimatedPrice;
	 int quantity;
	 Date receiveDate;
	 String regUser;
	 String comment;
	 String jobOrderNo;
	 String unitNo;
	 String seq;
	 String code;
	 String remark;
	 String device;
	 String partnerName;
	 int maxPrice;
	 int minPrice;
	 int count;
	 String partnerNameD;
	 int sum;
	 Long stockId;
	 int sqty;
	 int dqty;
	 

}
