package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.MenuData;

@Mapper
public interface MenuMapper {

	public List<MenuData> selectTopLevelMenu(@Param("userId") String userId);
	public List<MenuData> selectSubMenu(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuPurchase(@Param("userId") String userId);
	public List<MenuData> selectListMenuPurchase(@Param("userId") String userId);
	
	
	public List<MenuData> selectSubMenuTransition(@Param("userId") String userId);
	public List<MenuData> selectListMenuTransition(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuMyMenu(@Param("userId") String userId);
	public List<MenuData> selectListMenuMyMenu(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuPidback(@Param("userId") String userId);
	public List<MenuData> selectListMenuPidback(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuGeneral(@Param("userId") String userId);
	public List<MenuData> selectListMenuGeneral(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuDailyReport(@Param("userId") String userId);
	public List<MenuData> selectListMenuDailyReort(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuDrawing(@Param("userId") String userId);
	public List<MenuData> selectListMenuDrawing(@Param("userId") String userId);
	
	public List<MenuData> selectSubMenuPS(@Param("userId") String userId);
	public List<MenuData> selectListMenuPS(@Param("userId") String userId);
	
	public String AdminYN(@Param("id") String id);
	
	public List<MenuData> selectSubMenuMCT(@Param("userId") String userId);
	public List<MenuData> selectListMenuMCT(@Param("userId") String userId);
	
	public String selectHighCodeName(String highCode);
	
	public String updateYN(@Param("userId") String userId, @Param("highCode") String highCode, @Param("lowCode") String lowCode, @Param("listCode") String listCode);
	
}
