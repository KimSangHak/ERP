package com.yuhannci.erp.model.db;

import lombok.Data;

@Data
public class MenuData {
	String highCode;
	String lowCode;
	String listCode;
	String upAndview;
	
	String title;
	String url;
	
	String visible;
	String adminOnly;
}
