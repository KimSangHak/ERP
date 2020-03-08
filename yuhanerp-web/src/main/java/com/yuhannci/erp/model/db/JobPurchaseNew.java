package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;


@Data
public class JobPurchaseNew {
	Long id;
	Long job_design_drawing_Id;
	Long job_order_id;
	String customer_id;
	Date reg_date;
	String unit_no;
	String seq;
	String description;
	String modelNo;
	String maker;
	String partner_id;
	int quantity;
	int spare;
	String code;
	String comment;
	String remark;
	Long design_file_no;
	String stage;
	String estimate_request_id;
	Date estimate_requested_when;
	String estimate_request_user_id;
	String order_request_id;
	Date order_requested_when;
	String order_request_user_id;
	String deleted;
	String delete_reason;
	String delete_user_id;
	int wareHousing_quantity;
	String wareHousing_user;
	Date pass_date;
	String pass_usr;
	String receiver_usr;
	int out_quantity;
	String out_user;
	String out_reason;
}
