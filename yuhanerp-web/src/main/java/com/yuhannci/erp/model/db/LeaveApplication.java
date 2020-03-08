package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;


@Data
public class LeaveApplication {
	
	 Long id;
	 String title;
	 String requestReason;
	 Date regDate;
	 String requestDept;
	 String requestUsr;
	 Date requestStrdate;
	 Date requestEnddate;
	 int requestDate;
	 int requestHour;
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
	 
	 //추가된 컬럼 값
	 String changed;
	 Long preId;
	 
	 
	 //컬럼이외 값
	 String usrName;
	 String deptName;
	 String position;

}
