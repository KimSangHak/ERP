package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;


@Data
public class Overwork {
	
	Long id;
	String title; 	  
	String requestReason;
	String requestDept;
	Date regDate;
	String requestUsr;
	Date requestDate;
	int requestMin;
	String requestStrtime;
	String requestEndtime;
	String kind;
	String kindDept;
	String conformStage;
	String signR;
	String signM;
	Date mDate;
	String signJ;
	Date jDate;
	String signC;
	Date cDate;
	String complet;
	String deleted;
	String deleteReason;
	String deleteUserId;
	
	//컬럼 이외의 값
	String usrName;
	String deptName;
	String position;

}
