package com.yuhannci.erp.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.AndroidData;
import com.yuhannci.erp.model.BulletinOrderEntry;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.NoticeMessage;
import com.yuhannci.erp.service.BulletinBoardService;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.MyMenuService;
import com.yuhannci.erp.service.NoticeMessageService;
import com.yuhannci.erp.service.PushAlarmService;
import com.yuhannci.erp.service.UserService;

import lombok.extern.slf4j.Slf4j;

// 전광판 표시

@Controller
@RequestMapping("/bulletin")
@Slf4j
public class BulletinBoardController {
	
	@Autowired BulletinBoardService bulletinBoardService; 
	@Autowired JobOrderService jobOrderService;
	@Autowired MyMenuService myMenuService;
	@Autowired UserService userService;
	@Autowired NoticeMessageService noticeMessageService;
	@Autowired PushAlarmService pushAlarmService;
	
	@RequestMapping("/order")
	public ModelAndView order(){
		
		ModelAndView mv = new ModelAndView("bulletin_board/order");
		return mv;		
	}
	
	@RequestMapping("/order/data")
	@ResponseBody
	public StandardResponse orderData(int pageNo){
		
		StandardResponse res = null;
		try{
			
			int total = bulletinBoardService.getOrderListTotalPageCount();
			int thisPage = pageNo > total ? 1 : pageNo;
			List<BulletinOrderEntry> list = bulletinBoardService.getOrderListEntries(thisPage);
			
			int nextPage = thisPage + 1;
			if(nextPage > total)
				nextPage = 1;
			
			res = StandardResponse.createResponse("OK");
			res.setValue(String.valueOf(nextPage));		// 다음 요청할 페이지번호
			res.setData(list);
			
			log.debug("전광판 작업 지시 상황 다운로드. 페이지 = " + thisPage + "/" + total + ", 다음 페이지 = " + nextPage + ", 현 페이지 데이터 = " + list.size());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR");
			log.error("전광판 작업 내용 처리중 오류 ", e);
			
		}
		
		return res;
	}
	
	@RequestMapping("/orderprog/{orderType}/{psize}/{page}")
	public ModelAndView orderprog(@PathVariable("orderType") String orderType, @PathVariable("psize") Integer psize, @PathVariable("page") Integer page){
		
		ModelAndView mv = new ModelAndView("bulletin_board/orderprog");
		mv.addObject("items", jobOrderService.getJobOrderProgressPage(orderType, psize, page));
		mv.addObject("orderType", orderType);
		return mv;		
	}
	
	@RequestMapping(value={"/notice/{N}", "/notice"})
	public ModelAndView notice(@PathVariable("N") Optional<Integer> N){		
		ModelAndView mv = new ModelAndView("bulletin_board/notice");
		mv.addObject("LIST", bulletinBoardService.getRecentNoticeMessage(N.isPresent() ? N.get() : null ));
		return mv;		
	}
	
	@RequestMapping("/noticeList/{psize}/{page}")
	public ModelAndView noticeList(@PathVariable("psize") Integer psize, @PathVariable("page") Integer page){
		
		ModelAndView mv = new ModelAndView("bulletin_board/notice");
		mv.addObject("LIST", bulletinBoardService.getNoticeMessagePage(psize, page));
		return mv;		
	}
	
	@RequestMapping("/orderCount/{orderType}")
	public void order_count(HttpServletRequest request, HttpServletResponse response, @PathVariable("orderType") String orderType) throws Exception{


		log.debug("orderType = " + orderType);

        response.setContentType("text/html;charset=UTF-8");
        JSONObject jSurvey = new JSONObject();

        jSurvey.put("COUNT", bulletinBoardService.getProgressListTotalPageCount(orderType));
        
        jSurvey.put("title", request.getParameter("title"));
        jSurvey.put("memo", request.getParameter("memo"));
        
        System.out.println(request.getParameter("title"));
	    System.out.println(request.getParameter("memo"));
        
        System.out.println(jSurvey);
        
        PrintWriter out=response.getWriter();
        out.print(jSurvey); 
	}
	
	@RequestMapping(value="/Android/notice", produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject Android(HttpServletRequest request, HttpServletResponse response) throws Exception{


		//request.setCharacterEncoding("UTF-8");
       // response.setContentType("text/html;charset=UTF-8");
		
		System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("token"));
        
        if(bulletinBoardService.getOneAndroidToken(request.getParameter("token")).equals("Y")) {
        	bulletinBoardService.insertNewAndroidToken(request.getParameter("token"), request.getParameter("title"),
        											   request.getParameter("dept"), request.getParameter("isManager"),
        											   request.getParameter("isFactory"), request.getParameter("isDelector"),
        											   request.getParameter("isCEO"), request.getParameter("isPQ"));
        }
        

             
        List<AndroidData> androidData = bulletinBoardService.getNoticeToAndroid();
        


        
       
        
        JSONArray jArray = new JSONArray();
        JSONObject sendObject = new JSONObject();
        
        
        for(int i =0;i<androidData.size();i++) {
        	
        	AndroidData dto = androidData.get(i);
        	
        	JSONObject sObject = new JSONObject();
        	
        	sObject.put("id", dto.getId());
        	sObject.put("orderNo", dto.getOrderNo());
        	sObject.put("message", dto.getMessage());
        	sObject.put("whenCreated", dto.getWhenCreated());
        	sObject.put("device",dto.getDevice());
        	sObject.put("customer",dto.getCustomer());
        	
        	jArray.add(sObject);
        	
        	
        }
        
     
        
        sendObject.put("Data", jArray);
       // PrintWriter out=response.getWriter();
      //  out.print(sendObject); 
        
      
        
        return sendObject;

	}
	
	@RequestMapping(value="/Android/notice2", produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject AndroidTesk2(HttpServletRequest request, HttpServletResponse response) throws Exception{


		//request.setCharacterEncoding("UTF-8");
       // response.setContentType("text/html;charset=UTF-8");
		
		
             

        
        List<AndroidData> androidData2 = bulletinBoardService.getNoticeToAndroid2();

        
       
        

        JSONArray jArray2 = new JSONArray();
        JSONObject sendObject = new JSONObject();
        
        
      
        
        for(int i =0;i<androidData2.size();i++) {
        	
        	AndroidData dto = androidData2.get(i);
        	
        	JSONObject sObject = new JSONObject();
        	
        	sObject.put("id", dto.getId());
        	sObject.put("orderNo", dto.getOrderNo());
        	sObject.put("message", dto.getMessage());
        	sObject.put("whenCreated", dto.getWhenCreated());
        	sObject.put("device",dto.getDevice());
        	
        	jArray2.add(sObject);
        	
        	
        }
        
        sendObject.put("Data2", jArray2);
        System.out.println("여기찍히나?여기찍히나?여기찍히나?여기찍히나?여기찍히나?여기찍히나?여기찍히나?여기찍히나?여기찍히나?여기찍히나?");
       // PrintWriter out=response.getWriter();
      //  out.print(sendObject); 
        
      
        
        return sendObject;

	}
	
	@RequestMapping(value="/Android/notice3", produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject AndroidTesk3(HttpServletRequest request, HttpServletResponse response) throws Exception{


		//request.setCharacterEncoding("UTF-8");
       // response.setContentType("text/html;charset=UTF-8");
		
		
		
		List<AndroidData> androidData3 = new ArrayList<>();
		

		
		
		
		if(myMenuService.isPQroundrobin(request.getParameter("title")).equals("PQ")) {
			
			
			androidData3 = bulletinBoardService.getNoticeToAndroid3();
			
			
			
		}else if(myMenuService.isDirectorRoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData3 = bulletinBoardService.getNoticeToAndroid3ToDirector();
			
		}else if(myMenuService.isCEORoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData3 = bulletinBoardService.getNoticeToAndroid3ToCEO();
			
		}else if(myMenuService.isManagerRoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData3 = bulletinBoardService.getNotice3ToManager(myMenuService.deptCodeFromRoundrobin(request.getParameter("title")));
			
		}
		
		
		
             
   
		if(!(androidData3.isEmpty())) {
       
			
        

			JSONArray jArray3 = new JSONArray();
			JSONObject sendObject = new JSONObject();
        
        
      
        
			for(int i =0;i<androidData3.size();i++) {
				
				AndroidData dto = androidData3.get(i);
        	
				JSONObject sObject = new JSONObject();
				
				if(dto.getOrderNo().equals("ZZZZZ")) {
					dto.setOrderNo("소모품");
				}
        	
				sObject.put("id", dto.getId());
				sObject.put("orderNo", dto.getOrderNo());
				sObject.put("message", dto.getMessage());
				sObject.put("whenCreated", dto.getWhenCreated());
				sObject.put("device",dto.getDevice());
				sObject.put("sumprice", dto.getSumprice());
        	
				jArray3.add(sObject);
        	
        	
			}
        
			sendObject.put("Data3", jArray3);

			return sendObject;
			
		}else {
			
			
			
			JSONArray jArray3 = new JSONArray();
			JSONObject sendObject = new JSONObject();
			
			AndroidData dto = new AndroidData();
			
			JSONObject sObject = new JSONObject();
        	
			sObject.put("id", 0L);
			sObject.put("orderNo", null);
			sObject.put("message", null);
			sObject.put("whenCreated", null);
			sObject.put("device",null);
			sObject.put("sumprice", null);
    	
			jArray3.add(sObject);
			
			sendObject.put("Data3", jArray3);

			return sendObject;
		}
		
		

	}
	
	@RequestMapping(value="/Android/notice4", produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject AndroidTesk4(HttpServletRequest request, HttpServletResponse response) throws Exception{


		//request.setCharacterEncoding("UTF-8");
       // response.setContentType("text/html;charset=UTF-8");
		
		
		
		List<AndroidData> androidData4 = new ArrayList<>();
		

		
		
		
		if(myMenuService.isDirectorRoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData4 = bulletinBoardService.getNoticeToAndroid4ToDirector();
			
		}else if(myMenuService.isCEORoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData4 = bulletinBoardService.getNoticeToAndroid4ToCEO();
			
		}else if(myMenuService.isFactoryRoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData4 = bulletinBoardService.getNoticeToAndroid4ToFactory();
			
		}else if(myMenuService.isManagerRoundrobin(request.getParameter("title")).equals("Y")) {
			
			androidData4 = bulletinBoardService.getNoticeToAndroid4ToManager(myMenuService.deptCodeFromRoundrobin(request.getParameter("title")));
			
		}
		
		
		
             
   
		if(!(androidData4.isEmpty())) {
       
			
        

			JSONArray jArray4 = new JSONArray();
			JSONObject sendObject = new JSONObject();
        
        
      
        
			for(int i =0;i<androidData4.size();i++) {
        	
				AndroidData dto = androidData4.get(i);
        	
				JSONObject sObject = new JSONObject();
        	
				sObject.put("id", dto.getId());
				sObject.put("kind", dto.getKind());
				sObject.put("message", dto.getMessage());
				sObject.put("whenCreated", dto.getWhenCreated());
				sObject.put("useDate",dto.getUseDate());
				sObject.put("requestDate", dto.getRequestDate());
				sObject.put("leaveOverworkId", dto.getLeaveOverworkId());
				sObject.put("conformStage", dto.getConformStage());
        	
				jArray4.add(sObject);
        	
        	
			}
        
			sendObject.put("Data4", jArray4);

			return sendObject;
			
		}else {
			
			
			
			JSONArray jArray4 = new JSONArray();
			JSONObject sendObject = new JSONObject();
			
			AndroidData dto = new AndroidData();
			
			JSONObject sObject = new JSONObject();
        	
			sObject.put("id", 0L);
			sObject.put("kind", null);
			sObject.put("message", null);
			sObject.put("whenCreated", null);
			sObject.put("useDate", null);
			sObject.put("requestDate", null);
			sObject.put("leaveOverworkId", 0L);
			sObject.put("conformStage", null);
    	
			jArray4.add(sObject);
			
			sendObject.put("Data4", jArray4);

			return sendObject;
		}
		
		

	}
	
	
	@RequestMapping(value="/Android/conform", produces="application/json;charset=utf-8")
	public void AndroidConform(HttpServletRequest request, HttpServletResponse response) throws Exception{


	
		
		System.out.println("[param1] = "+request.getParameter("usrId"));
        System.out.println("[param2] = "+request.getParameter("dept"));
        System.out.println("[param3] = "+request.getParameter("isManager"));
        System.out.println("[param4] = "+request.getParameter("isFactory"));
        System.out.println("[param5] = "+request.getParameter("isDelector"));
        System.out.println("[param6] = "+request.getParameter("isCeo"));
        System.out.println("[param7] = "+request.getParameter("kind"));
        System.out.println("[param8] = "+request.getParameter("conformStage"));
        System.out.println("[param9] = "+request.getParameter("leaveOverworkId"));
        
        
        String usrId = request.getParameter("usrId");
        String dept = request.getParameter("dept");
        Long leaveOverworkId = Long.parseLong(request.getParameter("leaveOverworkId"));
        String conformStage = request.getParameter("conformStage");
        String kind = request.getParameter("kind");
        
        
        if(request.getParameter("isManager").equals("Y")) {
        	
        	if(request.getParameter("isDelector").equals("Y")) {
        			
        		if(request.getParameter("kind").equals("연장근무") || request.getParameter("kind").equals("휴일근무")) {
        			
        			if(conformStage.equals("R")) {
        				
        				myMenuService.overworkconformDtoR(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(leaveOverworkId);
        				
        					
        					String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(leaveOverworkId);
        				
        					noticeMessage.setConformStage("C");
        					noticeMessage.setAlarmKind("J");
        						
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        						
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");

        				
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        				
        			}else {
        			
        				myMenuService.overworkconformD(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(leaveOverworkId);
        				
        					
        					String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(leaveOverworkId);
        				
        					noticeMessage.setConformStage("C");
        					noticeMessage.setAlarmKind("J");
        						
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        						
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");

        				
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        			}
        			
        			
        			
        		}else {
        			
        			if(conformStage.equals("R")) {
        				
        				myMenuService.leaveconformDtoR(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(leaveOverworkId);
        					
        					noticeMessage.setAlarmKind("C");
        					noticeMessage.setConformStage("J");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					String userId = myMenuService.leaveRequestUsrfromNoticeMessage(leaveOverworkId);

        					
        					
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
        						
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        				
        			}else {
        			
        				myMenuService.leaveconformD(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(leaveOverworkId);
        					
        					noticeMessage.setAlarmKind("C");
        					noticeMessage.setConformStage("J");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					String userId = myMenuService.leaveRequestUsrfromNoticeMessage(leaveOverworkId);

        					
        					
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
        						
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        			}
        			
        			
        		}
        		
        		
        	}else if(request.getParameter("isFactory").equals("Y")) {
        		
        		if(request.getParameter("kind").equals("연장근무") || request.getParameter("kind").equals("휴일근무")) {
        			
        			if(conformStage.equals("R")) {
        				
        				myMenuService.overworkconformDtoR(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(leaveOverworkId);
        				
        					
        					String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(leaveOverworkId);
        				
        					noticeMessage.setConformStage("C");
        					noticeMessage.setAlarmKind("J");
        						
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        						
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");

        				
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        				
        			}else {
        			
        				myMenuService.overworkconformD(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(leaveOverworkId);
        				
        					
        					String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(leaveOverworkId);
        				
        					noticeMessage.setConformStage("C");
        					noticeMessage.setAlarmKind("J");
        						
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        						
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");

        				
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        			}
 	
        			
        		}else {
        			
        			if(conformStage.equals("R")) {
        				
        				myMenuService.leaveconformDtoR(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(leaveOverworkId);
        					
        					noticeMessage.setAlarmKind("C");
        					noticeMessage.setConformStage("J");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					String userId = myMenuService.leaveRequestUsrfromNoticeMessage(leaveOverworkId);

        					
        					
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
        						
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        				
        			}else {
        			
        				myMenuService.leaveconformD(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				try {
        					
        					NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(leaveOverworkId);
        					
        					noticeMessage.setAlarmKind("C");
        					noticeMessage.setConformStage("J");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					String userId = myMenuService.leaveRequestUsrfromNoticeMessage(leaveOverworkId);

        					
        					
        					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
        						
        				}catch(Exception e) {
        					
        					System.out.println("!결제 알람 에러");
        				}
        			}
		
        		}
        		
        		
        		
        	}else {
        		
        		if(request.getParameter("kind").equals("연장근무") || request.getParameter("kind").equals("휴일근무")) {
        			
        			myMenuService.overworkConformM(userService.UserSignPath(usrId), leaveOverworkId);
        			
        			try {
        				
        				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(leaveOverworkId);
        			
        				
        				String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(leaveOverworkId);
        				String kindDept = myMenuService.overWorkDetpfromNoticeMessage(leaveOverworkId);
        				
        				if(kindDept.equals("A")) {
        					
        					noticeMessage.setConformStage("M");
        					noticeMessage.setAlarmKind("D");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");
        					
        				}else if(kindDept.equals("B")) {
        					
        					noticeMessage.setConformStage("M");
        					noticeMessage.setAlarmKind("F");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					
        					pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");
        				}
        				

        			
        			}catch(Exception e) {
        				
        				System.out.println("!결제 알람 에러");
        			}
        			
        			
        			
        		}else {
        			
        			myMenuService.leaveConformM(userService.UserSignPath(usrId), leaveOverworkId);
        			
        			try {
        			
        				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(leaveOverworkId);
        			
        				
        				String userId = myMenuService.leaveRequestUsrfromNoticeMessage(leaveOverworkId);
        				String kindDept = myMenuService.leaveKindDetpfromNoticeMessage(leaveOverworkId);
        				
        				if(kindDept.equals("A")) {
        					
        					noticeMessage.setConformStage("M");
        					noticeMessage.setAlarmKind("D");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
        					
        				}else if(kindDept.equals("B")) {
        					
        					noticeMessage.setConformStage("M");
        					noticeMessage.setAlarmKind("F");
        					
        					noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
        					
        					
        					pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
        				}
        				

        			
        			}catch(Exception e) {
        				
        				System.out.println("!결제 알람 에러");
        			}
        			
        			
        			
        		}
        		
        		
        		
        	}
        	      	
        }else if(request.getParameter("isCeo").equals("Y")) {
        	
        	
        	if(request.getParameter("kind").equals("연장근무") || request.getParameter("kind").equals("휴일근무")) {

        		myMenuService.overworkconformCEO(userService.UserSignPath(usrId), leaveOverworkId);

        	}else {
        		
        		
        		String requestUsrId = myMenuService.leaveRequestUsrIdSelect(leaveOverworkId);
        		
        		String kindLeave = myMenuService.leaveKindSelect(leaveOverworkId);
        		
        			
        		if((myMenuService.completYNfromLeaveConfromCEO(leaveOverworkId)).equals("N")) {
        			
        			if(kindLeave.equals("A")) {
        				
        				int Date = myMenuService.leaveDateSelect(leaveOverworkId);
        				
        				int hour = Date*8;
        				
        				int before = myMenuService.usrVacationHourReal(requestUsrId);
        				
        				int result = before - hour ;
        				
        				myMenuService.setVacationusrReal(requestUsrId, result);
        				
        				myMenuService.leaveconformCEO(userService.UserSignPath(usrId), leaveOverworkId);
        				
        				
        			}else if(kindLeave.equals("C")) {
        				
        				int hour = myMenuService.leaveHourSelect(leaveOverworkId);
        				
        				int before = myMenuService.usrVacationHourReal(requestUsrId);
        				
        				int result = before - hour ;
        				
        				myMenuService.setVacationusrReal(requestUsrId, result);
        				
        				myMenuService.leaveconformCEO(userService.UserSignPath(usrId), leaveOverworkId);
        				
        			}else {
        				
        				myMenuService.leaveconformCEO(userService.UserSignPath(usrId), leaveOverworkId);
        			}
        		}
        			
  		
        	}
        	
        	
        	
        }
       
	}
	
	
	  @RequestMapping("/android3")
	    public void androidTestWithRequest(HttpServletRequest request){
	        System.out.println(request.getParameter("title"));
	        System.out.println(request.getParameter("memo"));
	    }

	
	@RequestMapping("/noticeCount")
	public void notice_count(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.debug("notice_count");

        response.setContentType("text/html;charset=UTF-8");
        JSONObject jSurvey = new JSONObject();

        jSurvey.put("COUNT", bulletinBoardService.getNoticeListTotalPageCount());
        
        System.out.println(jSurvey);
        
        PrintWriter out=response.getWriter();
        out.print(jSurvey); 
	}
}
