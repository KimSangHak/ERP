package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class GeneralWorkSearch extends DataTableRequestBase{
	
	String usrName;
	String deptCode;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date DateBegin;
	
	Integer draw;
	

}
