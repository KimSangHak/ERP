package com.yuhannci.erp.model.db;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class PidbackEntry {
	
	  Long id;
	  Long jobOrderId;
	  Long jobPurchaseId;
	  Long jobDesignDrawingId;
	  String userId;
	  String dept;
	  String receiveDept;
	  String title;
	  String body;
	  Integer view;
	  @JsonFormat(pattern="yyyy-MM-dd")
	  Date whenCreated;
	  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	  Date whenUpdated;
	  String deleted;
	  String checking;
	  String kind;
	
	  
	  //컬럼이외의 값
	  String jobOrderNo;
	  String usrName;
	  Integer comentCount;
	  String fdrawingNo;
	  String deptName;
	  String position;
	  
	  Integer drawings;
	  Integer pidbackTotal;

}
