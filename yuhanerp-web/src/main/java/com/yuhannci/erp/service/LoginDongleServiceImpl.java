package com.yuhannci.erp.service;

import java.net.HttpCookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yuhannci.erp.mapper.UserMapper;
import com.yuhannci.erp.model.LoginData;
import com.yuhannci.erp.model.db.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginDongleServiceImpl implements LoginDongleService{

	final String cookieDataKey = "you2";	

	HttpServletRequest req ;
	HttpServletResponse response ;
	
	DataCryptoService dataCryptoService = new DataCryptoService();
	
	// 현재 로그인 데이터
	LoginData loginData;
	
	@Autowired UserMapper userMapper;
	
	public LoginDongleServiceImpl(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.response = res;
		if(res == null)
			log.warn("response is null");
		
		String encData = getCookieValue();
		if(encData != null){
			loginData = dataCryptoService.decrypt(encData, LoginData.class);
		}		
		//log.debug("Login ID = " + ( loginData != null ? loginData.getUserId() : "(none)" ) );
	}
	
	public void clearLoginData(){
		loginData = null;
		
		Cookie cookie = new Cookie(cookieDataKey, "");
		//cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		log.info("쿠키[" + cookieDataKey + "] 값 지움");
	}
	
	public void setLoginData(User user){
		loginData = LoginData.create(user);
		
		String raw = dataCryptoService.encrypt(loginData);
				
		Cookie cookie = new Cookie(cookieDataKey, raw);
		cookie.setMaxAge(-1);	// 브라우저 닫으면 끝
		cookie.setPath("/");		
		response.addCookie(cookie);
				
		log.debug("쿠키[" + cookieDataKey + "] 에 [" + raw + "] 설정");
	}
	
	String getCookieValue(){
		
		if(req.getCookies() == null)
			return null;
				
		for(Cookie cookie : req.getCookies()){
			if(cookie.getName().equals(cookieDataKey)){
				return StringUtils.hasText( cookie.getValue() ) ? cookie.getValue() : null;
			}
		}
		return null;
	}
	
	public String getLoginId(){
		return loginData != null ? loginData.getUserId() : null;
	}
	
	public String getName(){
		return loginData != null ? loginData.getUserName() : null;
	}

	@Override
	public LoginData getLoginData() {
		// TODO Auto-generated method stub
		return loginData;
	}

	@Override
	public String getDepartmentCode() {
		return loginData.getDeptCd();
	}

	@Override
	public boolean isAdministrator() {
		return loginData.isAdmin();
	}

	@Override
	public User getMyInformation() {
		String myUserId = getLoginId();
		if(myUserId == null)
			return null;
		
		return userMapper.selectUser(myUserId, null, true);
	}
	
}
