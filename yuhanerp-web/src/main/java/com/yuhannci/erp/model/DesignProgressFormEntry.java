package com.yuhannci.erp.model;

import lombok.Data;

// 도면 출도 때 올라오는 
@Data
public class DesignProgressFormEntry {
	String drawingNo;
	String description;
	String dimensions;
	String material;
	String heatTreat;
	String quantity;
	String spare;
	String appliedFinish;
	String qc;
	String note;
	String classification;
	String reason;
}
