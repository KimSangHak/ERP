package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class Bulletin {
	int id;
	String userId;
	String userName;			// reference
	String userDeptName;		// reference
	Date whenCreated;
	String title;
	String body;
}
