package com.yuhannci.erp.model.db;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class GeneralVacationEntry {
	
	Long id;
	int baseHourCount;
	int hourCount;
	String usrName;
	String deptCode;
	String deptName;
	int useVacationD;
	int useVacationH;
	int remindD;
	int remindH;

}
