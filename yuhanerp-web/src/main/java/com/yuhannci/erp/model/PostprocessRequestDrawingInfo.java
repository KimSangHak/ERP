package com.yuhannci.erp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PostprocessRequestDrawingInfo {
	String designDrawingNo;
	String description;
	String dimension;
	String material;
	String thermal;
	String postprocessing;
	
	Integer quantity;
	Integer spare;
	
	Integer unitPrice;
	public Integer getAmount() {
		return unitPrice * (quantity + spare);
	}
	
	String drawingUrl;
}
