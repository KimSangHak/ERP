package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class GroupedJobPurchaseEntry_histroy {
	
	String job_order_no;
	String partnerName;
	int issue_pricell;
	int MaxPrice;
	int MinPrice;
	Date OrderIssue_date;
	String unit_no;
	String description;
	int spare;
	Long job_purchase_id;
	String request_id;
	String request_type;
	String model_no;
	String maker;
	int estimated_price;
	Date issue_date;
	int issue_price;
	int order_quantity;
	int stock_use_quantity;
	Long stock_id;
	Date receive_date;
}
