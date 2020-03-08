package com.yuhannci.erp.model;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarDayJob {
	String orderNo;
	String label;
	String color;
	Date scheduleDate;
}
