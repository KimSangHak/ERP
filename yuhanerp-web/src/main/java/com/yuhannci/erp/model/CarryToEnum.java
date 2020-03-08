package com.yuhannci.erp.model;

public enum CarryToEnum {
	INSPECT("검사부"),
	ASSEMBLY("조립부"),
	MC("가공부"),
	TS("영업부"),
	T1("설계부"),
	PQ("구매부");
	
	String label;
	CarryToEnum(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
