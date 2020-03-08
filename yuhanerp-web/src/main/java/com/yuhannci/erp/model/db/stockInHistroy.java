package com.yuhannci.erp.model.db;

import java.util.Date;
import lombok.Data;

@Data
public class stockInHistroy {
	
	Long id;
	Long stockID;
	Date registrationDate;
	String registrationUser;
	String registrationReason;
	String stockAble;
	int inQuantity;
	
}
