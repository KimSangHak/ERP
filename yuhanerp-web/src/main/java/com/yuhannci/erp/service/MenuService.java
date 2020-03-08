package com.yuhannci.erp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.MenuMapper;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.MenuData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuService {

	@Autowired MenuMapper menuMapper;
	
	public List<MenuData> getTopLevelMenu(String userId){
		
		return menuMapper.selectTopLevelMenu(userId);
		
	}
	
	public List<MenuData> getSubMenu(String userId){
		
		return menuMapper.selectSubMenu(userId);
	}
	
	public List<MenuData> selectSubMenuPurchase(@Param("userId") String userId){
		return menuMapper.selectSubMenuPurchase(userId);
	}
	public List<MenuData> selectListMenuPurchase(@Param("userId") String userId){
		return menuMapper.selectListMenuPurchase(userId);
	}
	
	public List<MenuData> selectSubMenuTransition(@Param("userId") String userId){
		return menuMapper.selectSubMenuTransition(userId);
	}
	
	public List<MenuData> selectListMenuTransition(@Param("userId") String userId){
		return menuMapper.selectListMenuTransition(userId);
	}
	
	public List<MenuData> selectSubMenuMyMenu(@Param("userId") String userId){
		return menuMapper.selectSubMenuMyMenu(userId);
	}
	
	public List<MenuData> selectListMenuMyMenu(@Param("userId") String userId){
		return menuMapper.selectListMenuMyMenu(userId);
	}
	
	public List<MenuData> selectSubMenuPidback(@Param("userId") String userId){
		return menuMapper.selectSubMenuPidback(userId);
	}
	
	public List<MenuData> selectListMenuPidback(@Param("userId") String userId){
		return menuMapper.selectListMenuPidback(userId);
	}
	
	public List<MenuData> selectSubMenuGeneral(@Param("userId") String userId){
		return menuMapper.selectSubMenuGeneral(userId);
	}
	
	public List<MenuData> selectListMenuGeneral(@Param("userId") String userId){
		return menuMapper.selectListMenuGeneral(userId);
	}
	
	
	
	public List<MenuData> selectSubMenuDailyReport(@Param("userId") String userId){
		return menuMapper.selectSubMenuDailyReport(userId);
	}
	
	public List<MenuData> selectListMenuDailyReort(@Param("userId") String userId){
		return menuMapper.selectListMenuDailyReort(userId);
	}
	
	public List<MenuData> selectSubMenuDrawing(@Param("userId") String userId){
		return menuMapper.selectSubMenuDrawing(userId);
	}
	
	public List<MenuData> selectListMenuDrawing(@Param("userId") String userId){
		return menuMapper.selectListMenuDrawing(userId);
	}
	
	public List<MenuData> selectSubMenuPS(@Param("userId") String userId){
		return menuMapper.selectSubMenuPS(userId);
	}
	public List<MenuData> selectListMenuPS(@Param("userId") String userId){
		return menuMapper.selectListMenuPS(userId);
	}
	public String AdminYN(@Param("id") String id) {
		return menuMapper.AdminYN(id);
	}
	
	public List<MenuData> selectSubMenuMCT(@Param("userId") String userId){
		return menuMapper.selectSubMenuMCT(userId);
	}
	public List<MenuData> selectListMenuMCT(@Param("userId") String userId){
		return menuMapper.selectListMenuMCT(userId);
	}
	
	public String updateYN(@Param("userId") String userId, @Param("highCode") String highCode, @Param("lowCode") String lowCode, @Param("listCode") String listCode) {
		return menuMapper.updateYN(userId, highCode, lowCode, listCode);
	}
	
	public String selectHighCodeName(String highCode) {
		return menuMapper.selectHighCodeName(highCode);
	}
	
}
