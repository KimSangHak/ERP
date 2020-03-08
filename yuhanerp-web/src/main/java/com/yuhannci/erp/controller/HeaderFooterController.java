package com.yuhannci.erp.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.db.MenuData;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/internal")
public class HeaderFooterController {
	@Autowired LoginDongleService loginDongleService;
	@Autowired MenuService menuService;
	
	@RequestMapping("/head")
	public ModelAndView head(){
		log.debug("현재 로그인 ID = " + loginDongleService.getLoginId() + "(" + loginDongleService.getName() + ")");
		String IsAdmin = menuService.AdminYN(loginDongleService.getLoginId());
		ModelAndView mv = new ModelAndView("headerfooter/head");
		mv.addObject("loginData", loginDongleService.getLoginData());
		mv.addObject("admin", IsAdmin);
		return mv;
	}
	
	@RequestMapping("/menu")
	public ModelAndView menu(){
		
		List<MenuData> topMenu = menuService.getTopLevelMenu( loginDongleService.getLoginId() );
		LinkedHashMap<String, LinkedList<MenuData>> allMenu = new LinkedHashMap<String, LinkedList<MenuData>>();
		for(MenuData menu : menuService.getSubMenu( loginDongleService.getLoginId()) ){
			if( allMenu.containsKey( menu.getHighCode() ) ){
				allMenu.get(menu.getHighCode()).add(menu);
			}else{
				LinkedList<MenuData> item = new LinkedList<>();
				item.add(menu);
				allMenu.put(menu.getHighCode(), item);
			}
		}
		
		ModelAndView mv = new ModelAndView("headerfooter/menu");
		mv.addObject("topMenu", topMenu);
		mv.addObject("subMenu", allMenu.values());
		return mv;
	}	
}
