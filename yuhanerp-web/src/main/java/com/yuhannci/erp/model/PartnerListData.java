package com.yuhannci.erp.model;

import java.util.Collection;
import java.util.List;

import lombok.Data;

@Data
public class PartnerListData extends PagingInfo{
	
	List<BillingPartner> data;
	
}
