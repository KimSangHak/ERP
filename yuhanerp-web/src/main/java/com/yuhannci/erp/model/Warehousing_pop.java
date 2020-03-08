package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class Warehousing_pop {
	String job_order_no;
	String partnerName;
	int quantity;
	String stage;
	Date order_requested_when;
	String unit_no;
	String description;
	Long job_purchase_id;
	String request_id;
	String request_type;
	String model_no;
	String maker;
	int unit_price;
	int supply_price;
	int estimated_price;
	Date issue_date;
	int issue_price;
	
}
