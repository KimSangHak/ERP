package com.yuhannci.erp.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum OrderTypeEnum {
	JOB("JOB"),				// 설비 작업
	JIG("JIG");				// 치공구 
	
	String value;
	OrderTypeEnum(String value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	public static OrderTypeEnum parse(String text) throws Exception{
		
		if(text.equalsIgnoreCase("JOB"))
			return JOB;
		if(text.equalsIgnoreCase("JIG"))
			return JIG;

		throw new IllegalArgumentException("잘못된 Order-Type[" + text + "] 지정됨");
	}
}
