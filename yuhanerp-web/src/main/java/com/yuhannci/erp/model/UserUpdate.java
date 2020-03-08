package com.yuhannci.erp.model;

import java.util.Date;
import lombok.Data;

@Data
public class UserUpdate {
	String id;
	String password;
	String name;
	String engName;
	String deptCode;
	String position;
	String email;
	String kakaoId;
	String handPhone;
	String inPhone;
	String homeAddress;
	String isAdmin;
	String isQc;
	String isPgm;
	String isRobot;
	Date passportExpireDate;

}
