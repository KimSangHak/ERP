package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class Purchase_I_ab {
	 String issueRequestId;
	 Long jobOrderId; 
	 Long jobPurchaseId;
	 String partnerId;
	 String estimateRequestId;
	 String modelNo;
	 Date regDate;
	 Date receiveDate;
	 String regUser;
	 int issuePrice;
	 int quantity;
	 int minestimatedPrice;
	 int max;
	 int min;
	 int stockqty;
	 int abQuantity;
	 int stockUseQuantity;
	 Long stockId;
	 String issueType;
	 String jobOrderNo;
	 String unitNo;
	 String seq;
	 String code;
	 String comment;
	 String description;
	 String device;
	 String partnerName;
	 int orderQuantity;
	 int spare;
	 String maker;
	 Long id;
	 String partnerNameD;
	 int count;
	 int sum;
	 int purchasequatity;
	 String cancle;
	 String cancleReason;
	 Date robinRequestDate;
	 String roundRobinYN;
	 

}
