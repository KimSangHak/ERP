package com.yuhannci.erp.model;

public enum FinishStatusEnum {
	UNFINISHED("U"),
	FINISHED("F"),
	BOTH("B");
	
	String code;
	
	FinishStatusEnum(String code){
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
