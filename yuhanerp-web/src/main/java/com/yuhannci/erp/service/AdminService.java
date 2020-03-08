package com.yuhannci.erp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.AdminMapper;
import com.yuhannci.erp.mapper.PurchaseMapper;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {
	
	@Autowired
	AdminMapper AdminMapper;
	
	public List<AdminUser> selectAdminUser(){
		return AdminMapper.selectAdminUser();
	}
	
	public List<User> AllselectAdminUser(String id){
		return AdminMapper.AllselectAdminUser(id);
	}
	
	public List<MenuData> selectAllMenu(){
		return AdminMapper.selectAllMenu();
	}
	
	public MenuDataPK selectMenuPk(String title) {
		return AdminMapper.selectMenuPk(title);
	}
	
	public List<MenuDataPK> selectAllMenuPk(){
		return AdminMapper.selectAllMenuPk();
	}
	
	public void insertUserMapping(String highCode, String lowCode, String listCode, String userId) {
		AdminMapper.insertUserMapping(highCode, lowCode, listCode, userId);
	}
	
	public void deleteUserMapping(String userId) {
		AdminMapper.deleteUserMapping(userId);
	}
	
	public void updateUser(UserUpdate userUpdate) {
		AdminMapper.updateUser(userUpdate);
	}
	
	public void updateUserForGeneral(UserUpdate userUpdate) {
		AdminMapper.updateUserForGeneral(userUpdate);
	}
	
	public List<NameDept> selectAllDeptName(){
		return AdminMapper.selectAllDeptName();
	}
	public List<PositionId> selectAllPositionName(){
		return AdminMapper.selectAllPositionName();
	}
	
	public String selectOneDeptCode(String deptName) {
		return AdminMapper.selectOneDeptCode(deptName);
	}
	public int selectViewIndexCount() {
		return AdminMapper.selectViewIndexCount();
	}
	public void insertUser(UserInsert userInsert) {
		AdminMapper.insertUser(userInsert);
	}
	public void userDelete(String id) {
		AdminMapper.userDelete(id);
	}
	public void insertAllUserMapping(AllMenuDatains allMenuDatains) {
		AdminMapper.insertAllUserMapping(allMenuDatains);
	}
	public List<MappingTitle> selectUserMappingTitleVisible(String userId){
		return AdminMapper.selectUserMappingTitleVisible(userId);
	}
	public List<MappingTitle> selectUserMappingTitleUpdate(String userId){
		return AdminMapper.selectUserMappingTitleUpdate(userId);
	}
	
	
	public void updateUserMapping(String highCode, String lowCode, String listCode, String userId) {
		AdminMapper.updateUserMapping(highCode, lowCode, listCode, userId);
	}
	public void updateUserMappingLow(String highCode, String lowCode, String userId) {
		AdminMapper.updateUserMappingLow(highCode, lowCode, userId);
	}
	public void updateUserMappingHigh(String highCode, String userId) {
		AdminMapper.updateUserMappingHigh(highCode, userId);
	}
	
	
	public void updateUserMappingUP(String highCode, String lowCode, String listCode, String userId) {
		AdminMapper.updateUserMappingUP(highCode, lowCode, listCode, userId);
	}
	public void updateUserMappingUPLow(String highCode, String lowCode, String userId) {
		AdminMapper.updateUserMappingUPLow(highCode, lowCode, userId);
	}
	public void updateUserMappingUPHigh(String highCode, String userId) {
		AdminMapper.updateUserMappingUPHigh(highCode, userId);
	}
	
	
	public void updateUserMappingN(String userId) {
		AdminMapper.updateUserMappingN(userId);
	}
	
	public void insUserMappingAll(String id) {
		AdminMapper.insUserMappingAll(id);
	}

}
