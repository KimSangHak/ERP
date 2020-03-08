package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class Warehousing {
	
	String job_order_no;
	String partnerName;
	Long job_purchase_id;
	String request_id;
	Date issue_date;
	int supply_price;
	int quantity;
	String stage;
	Date estimate_requested_when;

}
