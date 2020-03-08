package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class Job_purchaseHistory {
	Long job_purchase_id;
	String request_id;
	String request_type;
	String model_no;
	String maker;
	int estimated_price;
	Date issue_date;
	int issue_price;
	int Order_quantity;
	int Stock_use_quantity;
	Long Stock_id;
	Date receive_date;
}
