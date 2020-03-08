package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class StatementInsList {

	String partnerName;
	String orderNo;
	Date orderRequestedWhen;
	Date warehousingDate;
	Long id;
	Date issueDate;
	String partnerTypeKind;
	String partnerId;
	Date regDatetime;
	String requestId;
	Long jobOrderId;
	Long sumPrice;
	Long negoPrice;
	String buyKind;
	int count;

	
	

}
