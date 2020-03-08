package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class StatementUpdate {
	Long id;
	String status;
	int quantity;
	int price;
	String buyKind;
	Date regDate;
}
