package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.MenuDataPK;
import com.yuhannci.erp.model.AllMenuDatains;
import com.yuhannci.erp.model.MappingTitle;
import com.yuhannci.erp.model.NameDept;
import com.yuhannci.erp.model.PositionId;
import com.yuhannci.erp.model.UserInsert;
import com.yuhannci.erp.model.UserUpdate;
import com.yuhannci.erp.model.db.AdminUser;
import com.yuhannci.erp.model.db.MenuData;
import com.yuhannci.erp.model.db.User;


@Mapper
public interface AdminMapper {
	public List<AdminUser> selectAdminUser();
	public List<User> AllselectAdminUser(String id);
	public List<MenuData> selectAllMenu();
	public MenuDataPK selectMenuPk(String title);
	public List<MenuDataPK> selectAllMenuPk();
	public void insertUserMapping(@Param("highCode") String highCode, @Param("lowCode") String lowCode,
			@Param("listCode") String listCode, @Param("userId") String userId);
	public void deleteUserMapping(String userId);
	public void updateUser(UserUpdate userUpdate);
	public void updateUserForGeneral(UserUpdate userUpdate);
	public List<NameDept> selectAllDeptName();
	public List<PositionId> selectAllPositionName();
	public String selectOneDeptCode(String deptName);
	public int selectViewIndexCount();
	public void insertUser(UserInsert userInsert);
	public void userDelete(String id);
	public void insertAllUserMapping(AllMenuDatains allMenuDatains);
	public List<MappingTitle> selectUserMappingTitleVisible(String userId);
	public List<MappingTitle> selectUserMappingTitleUpdate(String userId);
	
	public void updateUserMapping(@Param("highCode") String highCode, @Param("lowCode") String lowCode,
			@Param("listCode") String listCode, @Param("userId") String userId);
	public void updateUserMappingLow(@Param("highCode") String highCode, @Param("lowCode") String lowCode,
			@Param("userId") String userId);
	public void updateUserMappingHigh(@Param("highCode") String highCode, @Param("userId") String userId);
	
	
	
	public void updateUserMappingUP(@Param("highCode") String highCode, @Param("lowCode") String lowCode,
			@Param("listCode") String listCode, @Param("userId") String userId);
	public void updateUserMappingUPLow(@Param("highCode") String highCode, @Param("lowCode") String lowCode,
			@Param("userId") String userId);
	public void updateUserMappingUPHigh(@Param("highCode") String highCode, @Param("userId") String userId);
	
	
	public void updateUserMappingN(String userId);
	
	public void insUserMappingAll(String id);


}
