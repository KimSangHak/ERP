package com.yuhannci.erp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yuhannci.erp.model.LoginForm;
import com.yuhannci.erp.model.db.User;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@Autowired LoginDongleService loginDongleService;
	@Autowired UserService userService;
	
	@RequestMapping(value="/login", method={RequestMethod.POST, RequestMethod.GET} )
	public ModelAndView login(@Valid LoginForm loginForm, BindingResult binding){
		ModelAndView mv = new ModelAndView("login");
		try{
			if(!binding.hasErrors() ){
				// TODO :: login 
				User user = userService.login(loginForm.getUserId(), loginForm.getPassword());
				if(user != null){
					
					loginDongleService.setLoginData(user);
					mv = new ModelAndView(new RedirectView("/main"));
					log.info("로그인 성공 --> " + user.getId());
				}else{
					mv.addObject("loginResult", "ID, 비밀번호를 확인해 주세요");
					log.info("로그인 실패 --> " + loginForm.getUserId());
				}
			}else{
				log.debug("파라미터가 없으면 그냥 ...");
			}
		}catch(Exception e){
			log.error("로그인 중 처리 오류", e);
		}
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		
		loginDongleService.clearLoginData();
		return new ModelAndView(new RedirectView("/login"));
				
	}
}
