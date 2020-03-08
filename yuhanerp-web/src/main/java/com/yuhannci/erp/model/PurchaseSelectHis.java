package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PurchaseSelectHis extends DataTableRequestBase {
	
	Long seq;
	
	Integer draw;

}
