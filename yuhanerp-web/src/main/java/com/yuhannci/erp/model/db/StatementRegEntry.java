package com.yuhannci.erp.model.db;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StatementRegEntry {
	Long uid;
	
	String designDrawingNo;
	String description;
	String material;
	String thermal;
	
	Integer quantity;
	Integer spare;
		
	String postprocessing;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date finishDate;	// process_finish_date or coating_date
	
	String itemName;
	
	Long unitPrice;	
	Long totalQuantity;
	Long sum;
	
	Long exclude;	// Y or null
}
