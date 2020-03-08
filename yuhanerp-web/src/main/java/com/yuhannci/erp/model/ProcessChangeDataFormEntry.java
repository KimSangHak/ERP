package com.yuhannci.erp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProcessChangeDataFormEntry {
	Long id;
	String workPosition;
	String outsourcingPartnerId;
	@DateTimeFormat(pattern="yyyy-MM-dd") Date coatingDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") Date finishDate;
	@DateTimeFormat(pattern="yyyy-MM-dd") Date inspectDate;
}
