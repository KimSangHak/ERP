package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.KeyValueEntry;
import com.yuhannci.erp.model.db.User;
import com.yuhannci.erp.model.db.UserData;

@Mapper
public interface UserMapper {
	public User selectUser(@Param("id") String id
							, @Param("password") String password 
							, @Param("existOnly") boolean existOnly );
	
	// 퇴사 처리
	public void markUserEnd(@Param("userId") String userId);
	
	// 이용자에게 부여된 권한을 불러온다
	public List<String> selectUserRoles(@Param("userId") String userId);
	
	public List<KeyValueEntry> selectBusinessDeptUserList();	
	public List<KeyValueEntry> selectDesignDeptUserList();		
	public List<KeyValueEntry> selectQcDeptUserList();
	public List<KeyValueEntry> selectManagerDeptUserList();
	public List<KeyValueEntry> selectAssembleDeptUserList();
	public List<KeyValueEntry> selectPGMUserList();
	public List<KeyValueEntry> selectRobotUserList();
	
	public String selectUserDeptCode(@Param("id") String userId);
	public String selectBusinessDeptCode();
	
	public List<User> findUser(@Param("key") String name, @Param("text") String dept);
	
	public List<UserData> getUserData(String id);
	public UserData getUserDataOne(String id);
	public List<UserData> getUserData2();
	public String UserSignPath(String id);
}
