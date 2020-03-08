package com.yuhannci.erp.service;

import com.yuhannci.erp.model.LoginData;
import com.yuhannci.erp.model.db.User;

public interface LoginDongleService {
	
	public String getLoginId();
	public String getName();
	
	public String getDepartmentCode();
	public boolean isAdministrator();
	
	public LoginData getLoginData();
	public void clearLoginData();
	public void setLoginData(User user);
	
	public User getMyInformation();

}
