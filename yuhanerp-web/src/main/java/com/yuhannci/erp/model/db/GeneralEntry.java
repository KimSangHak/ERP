package com.yuhannci.erp.model.db;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GeneralEntry {
	
	Long id;
	String name;
	String deptName;
	String position;
	String deptCode;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	Date joinDate;
	
	
	

}
