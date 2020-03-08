package com.yuhannci.erp.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.protocol.HTTP;
import org.apache.ibatis.annotations.Param;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuhannci.erp.application.AndroidPushNotificationsService;
import com.yuhannci.erp.application.AndroidPushPeriodicNotifications;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PushAlarmService {
	
	@Autowired AndroidPushNotificationsService androidPushNotificationsService;
	@Autowired BulletinBoardService bulletinBoardService;
	
	 public @ResponseBody ResponseEntity<String> orderStartService(String a, String b) throws JSONException, InterruptedException  {
	        
		 
		 
		 String notifications = AndroidPushPeriodicNotifications.orderStart(bulletinBoardService.getAndroidToken(),a, b);
		 
		 
	        
		
	        HttpEntity<String> request = new HttpEntity<>(notifications);
	        
	       

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	        	
	        	
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	 public @ResponseBody ResponseEntity<String> ceoSendService(String a, String b) throws JSONException, InterruptedException  {
	        
		 System.out.println("#################3 CEOSEND 여기 들어옴!?!?!?!?!?!?!?!?!?!?!!?!");
		 
		 String notifications = AndroidPushPeriodicNotifications.orderStart(bulletinBoardService.getAndroidTokenCEO(),a, b);
		 
		 
	        
		
	        HttpEntity<String> request = new HttpEntity<>(notifications);
	        
	       

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	        	
	        	
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	 
	 public @ResponseBody ResponseEntity<String> factorySendService(String a, String b) throws JSONException, InterruptedException  {
	        
		 System.out.println("#################3 CEOSEND 여기 들어옴!?!?!?!?!?!?!?!?!?!?!!?!");
		 
		 String notifications = AndroidPushPeriodicNotifications.orderStart(bulletinBoardService.getAndroidTokenFactory(),a, b);
		 
		 
	        
		
	        HttpEntity<String> request = new HttpEntity<>(notifications);
	        
	       

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	        	
	        	
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	 public @ResponseBody ResponseEntity<String> pqSendService(String a, String b) throws JSONException, InterruptedException  {
	        
		 System.out.println("#################3 CEOSEND 여기 들어옴!?!?!?!?!?!?!?!?!?!?!!?!");
		 
		 String notifications = AndroidPushPeriodicNotifications.orderStart(bulletinBoardService.getAndroidTokenPQ(),a, b);
		 
		 
	        
		
	        HttpEntity<String> request = new HttpEntity<>(notifications);
	        
	       

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	        	
	        	
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	 
	 
	 public @ResponseBody ResponseEntity<String> directorSendService(String a, String b) throws JSONException, InterruptedException  {
	        
		 System.out.println("#################3 CEOSEND 여기 들어옴!?!?!?!?!?!?!?!?!?!?!!?!");
		 
		 String notifications = AndroidPushPeriodicNotifications.orderStart(bulletinBoardService.getAndroidTokenDirector(),a, b);
		 
		 
	        
		
	        HttpEntity<String> request = new HttpEntity<>(notifications);
	        
	       

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	        	
	        	
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	 
	 public @ResponseBody ResponseEntity<String> managerSendService(String a, String b, String dept) throws JSONException, InterruptedException  {
	        
		 System.out.println("#################3 CEOSEND 여기 들어옴!?!?!?!?!?!?!?!?!?!?!!?!");
		 
		 String notifications = AndroidPushPeriodicNotifications.orderStart(bulletinBoardService.getAndroidTokenManager(dept),a, b);
		 
		 
	        
		
	        HttpEntity<String> request = new HttpEntity<>(notifications);
	        
	       

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	        	
	        	
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	 
	 public @ResponseBody ResponseEntity<String> send2() throws JSONException, InterruptedException  {
	        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson2();

	        HttpEntity<String> request = new HttpEntity<>(notifications);

	        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
	        CompletableFuture.allOf(pushNotification).join();

	        try{
	            String firebaseResponse = pushNotification.get();
	            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	        }
	        catch (InterruptedException e){
	        	log.debug("got interrupted!");
	            throw new InterruptedException();
	        }
	        catch (ExecutionException e){
	        	log.debug("execution error!");
	        }

	        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	    }
	 
	
	
	

}
