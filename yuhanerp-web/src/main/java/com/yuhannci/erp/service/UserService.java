package com.yuhannci.erp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.DayOffMapper;
import com.yuhannci.erp.mapper.UserMapper;
import com.yuhannci.erp.model.KeyValueEntry;
import com.yuhannci.erp.model.db.DayOff;
import com.yuhannci.erp.model.db.User;
import com.yuhannci.erp.model.db.UserData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired UserMapper userMapper;
	@Autowired DayOffMapper dayOffMapper;
	
	public User login(String id, String password){
		User user = userMapper.selectUser(id, password, true);
		if(user != null){
			user.setRoles( userMapper.selectUserRoles(id) );
		}
		return user;
	}
		
	public List<KeyValueEntry> getBusinessDeptUserList(){
		return userMapper.selectBusinessDeptUserList();
	}
	public List<KeyValueEntry> getDesignDeptUserList(){
		return userMapper.selectDesignDeptUserList();
	}	
	public List<KeyValueEntry> getQcDeptUserList(){
		return userMapper.selectQcDeptUserList();
	}	
	public List<KeyValueEntry> getManagerDeptUserList(){
		return userMapper.selectManagerDeptUserList();
	}	
	public List<KeyValueEntry> getAssembleDeptUserList(){
		return userMapper.selectAssembleDeptUserList();
	}
	public List<KeyValueEntry> getPgmUserList(){
		return userMapper.selectPGMUserList();
	}
	public List<KeyValueEntry> getRobotUserList(){
		return userMapper.selectRobotUserList();
	}	

	public boolean isBusinessDeptUser(String id){
		String bizDeptCd = userMapper.selectBusinessDeptCode();
		String yourDeptCd = userMapper.selectUserDeptCode(id);
		return bizDeptCd != null && bizDeptCd.equalsIgnoreCase(yourDeptCd);
	}
	
	public List<User> findAllUser(String key, String text){
		key = key.toLowerCase();
		return userMapper.findUser(key,text);
	}
	
	public List<UserData> getUserData(String id){
		return userMapper.getUserData(id);
	}
	
	public UserData getUserDataOne(String id){
		return userMapper.getUserDataOne(id);
	}
	
	public List<UserData> getUserData2(){
		return userMapper.getUserData2();
	}
	
	public String UserSignPath(String id) {
		return userMapper.UserSignPath(id);
	}
}
