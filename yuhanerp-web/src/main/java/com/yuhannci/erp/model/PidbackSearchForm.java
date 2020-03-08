package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class PidbackSearchForm extends DataTableRequestBase {
	
	String kind;
	String usrName;
	String title;
	String orderNoBase;
	String orderNoExtra;
	String drawingNo;
	String seq;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date DateBegin;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date DateEnd;
	
	Integer draw;

}
