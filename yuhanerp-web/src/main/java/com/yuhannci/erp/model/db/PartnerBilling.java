package com.yuhannci.erp.model.db;

import lombok.Data;

@Data
public class PartnerBilling {
	int id;
	
	String partnerId;
	String billingName;
	String billingAfter;
	String billingDay;
}
