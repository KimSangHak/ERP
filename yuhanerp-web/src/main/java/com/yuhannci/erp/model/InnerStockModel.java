package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class InnerStockModel {
	 String issueRequestId;
	 Long jobOrderId;
	 Long jobPurchaseId;
	 String partnerId;
	 String estimateRequestId;
	 String modelNo;
	 String maker;
	 Date regDate;
	 Date receiveDate;
	 String regUser;
	 int issuePrice;
	 int quantity;
	 String issueType;
	 String cancle;
	 String cancleReason;
	 String jobOrderNo;
	 String partnerName;
	 int noneinnersotck;
	 int sumPrice;
	 int count;
	 int stockQuantity;
	 int dquantitiy;
	 String unitNo;
	 String description;
	 String stage;
	 int warehousingQuantity;
	 int possibleQty;
}
