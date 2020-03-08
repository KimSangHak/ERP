package com.yuhannci.erp.model;

public enum ChangeTypeEnum {
	STATUS("STATUS"),
	FIELD("FIELD"),
	DELETE("DELETE"),
	FINISH("FINISH");
				
	String value;
	ChangeTypeEnum(String value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return value;
	}	
}
