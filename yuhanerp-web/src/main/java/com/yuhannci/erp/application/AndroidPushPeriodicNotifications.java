package com.yuhannci.erp.application;





import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuhannci.erp.service.BulletinBoardService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {
	
	

    public static String orderStart(List<String> dataList, String a, String b) throws JSONException {
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"cg1edtz_0vM:APA91bFr63t27AnUaRoFRTSYsEZ9H-6aZ4TfrGSY-WGwWsVdcNfwlIx3TFdH03N_bitWznZD5RruQcTYQptvnqBXqTbrt1-tYEUJZCnOWqr0rLMQ2yb7NBlPKSMyL_4h6d4fXjzeCcNR"};
        
       
        
        System.out.println("#################3 ORDERSTART 여긴??? 여기 들어옴!?!?!?!?!?!?!?!?!?!?!!?!");
        
        
        for(int i =0;i < dataList.size(); i++) {
        	System.out.println("@@@@@@@@@@@@@["+"i"+"] = "+dataList.get(i));
        }
        
        	
        	//{"cg1edtz_0vM:APA91bFr63t27AnUaRoFRTSYsEZ9H-6aZ4TfrGSY-WGwWsVdcNfwlIx3TFdH03N_bitWznZD5RruQcTYQptvnqBXqTbrt1-tYEUJZCnOWqr0rLMQ2yb7NBlPKSMyL_4h6d4fXjzeCcNR"};

        JSONObject body = new JSONObject();
        
  

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<dataList.size(); i++){
            tokenlist.add(dataList.get(i));
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        
        
        String title = "작업지시";
        
        
        
      
        notification.put("title", a);
    	notification.put("body", b);
   

    	
    	
    	body.put("notification", notification);

    	System.out.println(body.toString());
        

        return body.toString();
    }
    
    public static String PeriodicNotificationJson2() throws JSONException {
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"cg1edtz_0vM:APA91bFr63t27AnUaRoFRTSYsEZ9H-6aZ4TfrGSY-WGwWsVdcNfwlIx3TFdH03N_bitWznZD5RruQcTYQptvnqBXqTbrt1-tYEUJZCnOWqr0rLMQ2yb7NBlPKSMyL_4h6d4fXjzeCcNR"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title", "도면출도가 완료되었습니다.");
        notification.put("body","Today is "+localDate.getDayOfWeek().name()+"!");

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }
}

