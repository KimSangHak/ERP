package com.yuhannci.erp.model.db;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
	String id;	// userid
	String kakaoId;	
	String email;
	String password;
	String name, engName;
	String deptCode, deptName;
	String position;
	
	String inPhone, directPhone, handPhone, homePhone;
	String homeAddress;
	
	Date joinDate, quitDate;
	Date passportExpireDate;
	
	Integer viewIndex;
	String isAdmin;
	
	String isQc;
	String isPgm;
	String isRobot;
	
	List<String> roles;
	
	// 관리자냐 아니냐~
	public boolean isAdministrator(){
		return isAdmin.equalsIgnoreCase("Y");
	}
	
	// 퇴사 했는지 여부
	public boolean isQuitted(){
		return quitDate != null;
	}
}
