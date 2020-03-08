package com.yuhannci.erp.model.db;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class UserData {
	
	  String id;
	  String kakaoId;
	  String email;
	  String password;
	  String name;
	  String engName;
	  String deptCode;
	  String position;
	  String inPhone;
	  String directPhone;
	  String handPhone;
	  String homePhone;
	  String homeAddress;
	  Date joinDate;
	  Date quitDate;
	  Date passportExpireDate;
	  Date lastLogin;
	  int viewIndex;
	  String isManager;
	  String isDirector;
	  String isCeo;
	  String isAdmin;
	  String isQc;
	  String isPgm;
	  String isRobot;
	  String mctPasswd;
	  String isOut;
	  int hourCount;
	  
	  //추가 데이터
	  String deptName;
	  float remainLeave;
	  
	  int dateLeave;
	  int hourLeave;

}
