package com.yuhannci.erp.application;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import com.yuhannci.erp.application.HeaderRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {
	
	private static final String FIRBASE_SEVER_KEY = "AAAA9QSVuzk:APA91bE7pvknvSDUPhQX_-vMIpPlhD7PFoMABO0XPuVzqPu16txi03VzQ4IQaDjRDMDjvX-wU5xThqeQLk2vxojswAZ02zkIcD4de0vCG31BRAZ3Zld-nHHOgb1U0jg-mcoVzTiBZEEK";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity){
		RestTemplate restTemplate = new RestTemplate();
		
		/**
		 https://fcm.googleapis.com/fcm/send
		 content-Type:application/json
		 Authorization:key=FIREBASE_SERVER_KEY
		 */
		
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIRBASE_SEVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json;charset=UTF-8"));
		restTemplate.setInterceptors(interceptors);
		
		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
		
		return CompletableFuture.completedFuture(firebaseResponse);
		
		
	}

}
