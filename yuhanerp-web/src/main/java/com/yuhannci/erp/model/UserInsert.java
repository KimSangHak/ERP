package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class UserInsert {
	
	String id;
	String password;
	String name;
	String engName;
	String email;
	String kakaoId;
	String deptCode;
	String position;
	String inPhone;
	String directPhone;
	String handPhone;
	String homeAddress;
	int viewIndex;
	String isAdmin;
	String isQc;
	String isPgm;
	String isRobot;
	String homePhone;
	Date passportExpireDate;
	String mctPasswd;

}
