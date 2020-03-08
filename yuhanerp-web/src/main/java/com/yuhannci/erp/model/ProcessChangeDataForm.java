package com.yuhannci.erp.model;

import lombok.Data;

@Data
public class ProcessChangeDataForm {

	String action;
	String reason;
	
	ProcessChangeDataFormEntry[] data;
}
