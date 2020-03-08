package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class PreEstimateRequest {
	
	String jobOrderNo;
	String partnerName;
	String device;
	int stockQuantity;
	String unitNo;
	String description;
	String modelNo;
	String remark;
	String maker;
	String comment;
	String seq;
	int spare;
	int quantity;
	Long jobOrderId;
	String partnerId;
	int abQuantity;
	int stockUseQuantity;
	Long id;

}
