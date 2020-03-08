package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class warehousing_list {
	Long id;
	String BuyNum;
	int warehousing_quantity;
	int out_quantity;
	String out_reason;
	String out_person;
	Date warehousing_Date;
}
