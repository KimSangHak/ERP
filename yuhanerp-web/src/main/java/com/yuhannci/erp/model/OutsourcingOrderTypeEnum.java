package com.yuhannci.erp.model;

public enum OutsourcingOrderTypeEnum {
	ESTIMATE("E"),
	PROCESS_ORDER("P"),
	POSTPROCESS_ORDER("C");
	
	String code;
	OutsourcingOrderTypeEnum(String code){
		this.code = code; 
	}
	
	public String toString(){
		return code;
	}
	
}
