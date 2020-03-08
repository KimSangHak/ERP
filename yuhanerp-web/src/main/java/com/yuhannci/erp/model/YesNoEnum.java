package com.yuhannci.erp.model;

public enum YesNoEnum {
	YES("Y"),
	NO("N");
	
	String id;
	YesNoEnum(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
