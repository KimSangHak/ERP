package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class BillingPartner {
	int		id;	// id@partner_billing
	String partnerId;
	String partnerName;
	String billingName;
	String payment;
	String region;
	int	billingLines;
}
