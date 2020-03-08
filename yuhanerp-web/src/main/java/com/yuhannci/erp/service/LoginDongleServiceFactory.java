package com.yuhannci.erp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoginDongleServiceFactory {

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public LoginDongleService createLoginDongleService(HttpServletRequest req, HttpServletResponse res){
		//log.info("OOPS " + req);
		return new LoginDongleServiceImpl(req, res);
	}
}
