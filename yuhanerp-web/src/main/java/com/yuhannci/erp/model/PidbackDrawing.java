package com.yuhannci.erp.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PidbackDrawing {
	
	Long id;
	Long orderId;
	String description;
	int quantity;
	int spare;
	String designDrawingNo;
	String jobOrderNo;
	String device;
	

}
