package com.yuhannci.erp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yuhannci.erp.model.db.User;
import com.yuhannci.erp.service.LoginDongleServiceImpl;

import lombok.Data;

// 로그인 후 쿠키에 보관돼 
// 계속 사용되는 데이터

@Data
@JsonIgnoreProperties(ignoreUnknown = true)			// <<--- 아래 UserTypeLabel 등 존재하지 않는 프로퍼티는 무시하도록
public class LoginData{
	String userId, userName;
	String deptCd, deptName;
	String position;
	
	boolean isAdmin;
	List<String> roles;
	
	public boolean hasRole(String role){
		return isAdmin || (roles.contains(role));
	}
	
	public static LoginData create(User user){
		LoginData data = new LoginData();
		data.userId = user.getId();
		data.userName = user.getName();
		
		data.deptCd = user.getDeptCode();
		data.deptName = user.getDeptName();
		
		data.position = user.getPosition();
		
		data.isAdmin = user.isAdministrator();
		data.roles = user.getRoles();
		return data;
	}
	
	public String getUserTypeLabel(){
		return isAdmin ? "관리자" : "사내직원";
	}
}