package com.yuhannci.erp.model;

import java.util.List;

import com.yuhannci.erp.model.db.JobPurchase;

import lombok.Data;

@Data
public class PurchaseRequestMailBody {
	String device;
	String orderNo;
	
	List<JobPurchase> entries;
}
