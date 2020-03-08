package com.yuhannci.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.LoginDongleServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UrlInterceptor extends HandlerInterceptorAdapter{
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LoginDongleService loginDongleService = new LoginDongleServiceImpl(request, response);
		
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		final String loginId = loginDongleService.getLoginId();
		log.info("ACCESS URL = " + uri + " from " + request.getRemoteAddr() + ", Login ID = " + loginId);		
		
		boolean needLogin = uri != null ? (!(uri.equals("/error") || uri.equals("/login") || uri.equals("/logout") || uri.startsWith("/api/") || uri.startsWith("/bulletin/") )) : false; 
		if(needLogin && loginId == null){
			log.info("로그인 정보 없어 로그인 페이지로 이동 uri = " + uri);
			response.sendRedirect("/login");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}
