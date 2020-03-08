package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class SerachPartnerState {
	
	Long jobOrderId;
	String jobOrderNo;
	String partnerId;
	String partnerName;
	int countOrder;
	int sum;

}
