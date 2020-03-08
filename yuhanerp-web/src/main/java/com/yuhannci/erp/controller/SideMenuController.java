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
@RequestMapping("/sidemenu")
public class SideMenuController {
	
	@Autowired LoginDongleService loginDongleService;
	@Autowired MenuService menuService;
	
	
	@RequestMapping("/purchase")
	public ModelAndView purchaseMenu(){
		
		List<MenuData> ListMenu = menuService.selectListMenuPurchase(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("25");
		
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");

		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/dailyReport")
	public ModelAndView dailyReportMenu(){
		
		List<MenuData> ListMenu = menuService.selectListMenuDailyReort(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("10");

		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");

		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/drawing")
	public ModelAndView drawingMenu(){
		
		List<MenuData> ListMenu = menuService.selectListMenuDrawing(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("20");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");

		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/PS")
	public ModelAndView PSMenu(){
		
		List<MenuData> ListMenu = menuService.selectListMenuPS(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("30");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");
		
		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/MCT")
	public ModelAndView MCTMenu(){
		
		List<MenuData> ListMenu = menuService.selectListMenuMCT(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("40");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");
		
		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/Transition")
	public ModelAndView Transition(){
		
		List<MenuData> ListMenu = menuService.selectListMenuTransition(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("45");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");
		
		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/MyMenu")
	public ModelAndView MyMenu(){
		
		List<MenuData> ListMenu = menuService.selectListMenuMyMenu(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("70");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");
		
		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/Pidback")
	public ModelAndView Pidback(){
		
		List<MenuData> ListMenu = menuService.selectListMenuPidback(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("75");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");
		
		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/General")
	public ModelAndView General(){
		
		List<MenuData> ListMenu = menuService.selectListMenuGeneral(loginDongleService.getLoginId());
		String name = menuService.selectHighCodeName("90");
		ModelAndView mv = new ModelAndView("sidememu/sideMenuTest");
		
		mv.addObject("ListMenu", ListMenu);
		mv.addObject("name", name);
		return mv;
	}
	
}
