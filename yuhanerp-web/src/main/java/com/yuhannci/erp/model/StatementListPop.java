package com.yuhannci.erp.model;


import java.util.Date;
import lombok.Data;

@Data
public class StatementListPop {
	String jobOrderNo;
	String description;
	String modelNo;
	int issuedQuantity;
	int issuedUnitPrice;

}
