package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class JobPurchaseNew_history {
	Long job_purchase_id;
	String request_id;
	String request_type;
	String modelNo;
	String maker;
	int quantity;
	int estimated_price;
	int issue_price;
	int order_quantity;
	int stock_use_quantity;
	Long stock_id;
	Date receive_date;	
}
