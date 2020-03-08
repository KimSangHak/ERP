package com.yuhannci.erp.model.db;

import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GeneralWorkEntry {
	
	Long id;
	String usrName;
	String deptName;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	Date registrationDate;
	
	String commmuteStart;
	
	String commmuteEnd;
	
	String startkind;
	
	String endkind;
	
	String yn;
	
	
	

}
