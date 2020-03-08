package com.yuhannci.erp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class KeyValueEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String key;
	String value;
	
	public KeyValueEntry(String k, String v){
		key = k;
		value = v;
	}
}
