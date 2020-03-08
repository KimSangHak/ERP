package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.db.AdminUser;

import ch.qos.logback.core.net.SyslogOutputStream;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/KaKao")
public class KaKaoController {
	
	@RequestMapping("/Main")
	public ModelAndView KakaoMain(){
		
	
		ModelAndView mv = new ModelAndView("kakao/kakaoMain");
	
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView KakaoLogin(){
		
	
		ModelAndView mv = new ModelAndView("kakao/kakaoLogin");
	
		return mv;
	}
	
	@RequestMapping("/link")
	public ModelAndView KakaoLink(){
		
	
		ModelAndView mv = new ModelAndView("kakao/kakaoLinkAct");
	
		return mv;
	}

}
