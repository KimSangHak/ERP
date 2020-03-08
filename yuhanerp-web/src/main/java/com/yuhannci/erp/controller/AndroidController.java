package com.yuhannci.erp.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.yuhannci.erp.application.AndroidPushNotificationsService;
import com.yuhannci.erp.application.AndroidPushPeriodicNotifications;
import com.yuhannci.erp.model.db.AdminUser;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.PushAlarmService;

import ch.qos.logback.core.net.SyslogOutputStream;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/Android")
public class AndroidController {
	
	@Autowired AndroidPushNotificationsService androidPushNotificationsService;
	@Autowired PushAlarmService pushAlarmService;
	
	
	
	@RequestMapping("/Test2")
	public void androidTestWithRequest2(){
		
		try {
			
			pushAlarmService.orderStartService("작업지시","");
			
		}catch(Exception e) {
			
		}
		
	}
	
	@RequestMapping("/Test3")
	public void androidTestWithRequest3(){
		
		try {
			
			pushAlarmService.send2();
			
		}catch(Exception e) {
			
		}
		
	}
	
	
	
	
	
}
