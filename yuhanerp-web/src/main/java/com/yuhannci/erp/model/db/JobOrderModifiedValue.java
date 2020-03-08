package com.yuhannci.erp.model.db;

import lombok.Data;

@Data
public class JobOrderModifiedValue {

	Long id;
	Long jobOrderChangeId;
	String fieldName;
	String before;
	String after;
	
}
