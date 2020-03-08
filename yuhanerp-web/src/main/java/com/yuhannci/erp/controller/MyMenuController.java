package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.StockListOrderNo;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.LeaveApplication;
import com.yuhannci.erp.model.db.NoticeMessage;
import com.yuhannci.erp.model.db.Overwork;
import com.yuhannci.erp.model.db.RoundRobin;
import com.yuhannci.erp.model.db.UserData;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MyMenuService;
import com.yuhannci.erp.service.NoticeMessageService;
import com.yuhannci.erp.service.PurchaseService;
import com.yuhannci.erp.service.PushAlarmService;
import com.yuhannci.erp.service.TransitionService;
import com.yuhannci.erp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mymenu")
@Slf4j
public class MyMenuController {
	
	@Autowired MyMenuService myMenuService;
	@Autowired UserService userService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired PurchaseService purchaseService;
	@Autowired TransitionService TransitionService;
	@Autowired NoticeMessageService noticeMessageService;
	@Autowired PushAlarmService pushAlarmService;
	
	final static SimpleDateFormat ymdDATE = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping("/memo/{n}")
	public ModelAndView Memo(@PathVariable("n") Integer n){
		log.info("메모 읽기. n=" + n);
		
		ModelAndView mv = new ModelAndView("mymenu/memo");
		return mv;
	}
	@RequestMapping("/memo")
	public ModelAndView MemoList(){
		
		ModelAndView mv = new ModelAndView("mymenu/memo");
		return mv;
	}
	
	//구매품의서 등록/조회 Page
	@RequestMapping("/roundRobin")
	public ModelAndView roundRobin(String title, String usrName, String DateBegin, String DateEnd, String kind){
		
		
		String requestId = null;
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		if(StringUtils.isEmpty(usrName)) {
			requestId = null;
		}else {
			requestId = myMenuService.usrNameForIdroundrobin(usrName);
		}
		
		if(StringUtils.isEmpty(kind)) {
			
			kind=null;
							
		}else {
			if(kind.equals("A")) {
				
				kind=null;
								
			}
			
		}
		
		String loginUserId = loginDongleService.getLoginId();
		
		List<RoundRobin> data = null;
		
		if(myMenuService.isManagerRoundrobin(loginUserId).equals("Y")) {
			
			if(myMenuService.isDirectorRoundrobin(loginUserId).equals("Y") || myMenuService.isFactoryRoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.roundRobinSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
				
			}else {
				
				if(myMenuService.isPQroundrobin(loginUserId).equals("PQ")) {
					
					data = myMenuService.roundRobinSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}else {
					
					
					data = myMenuService.roundRobinSelectManager(myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId()), requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}
				
				
			}
			
		}
		
		else {
			
			if(myMenuService.isCEORoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.roundRobinSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
				
			}else {
				
				if(myMenuService.isPQroundrobin(loginUserId).equals("PQ")) {
					
					data = myMenuService.roundRobinSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}else {
				
					data = myMenuService.roundRobinSelect(loginDongleService.getLoginId(), title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
				}
				
			}
			
			
			
		}
		
		
		
		
		
		
		ModelAndView mv = new ModelAndView("mymenu/roundRobinMain");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("usrName", usrName);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		mv.addObject("kind", kind);
		return mv;
	}
	
	//연월차 등록/조회 Page
		@RequestMapping("/leave")
		public ModelAndView leaveApllication(String title, String usrName, String DateBegin, String DateEnd, String kind){
			
			
			String requestId = null;
			
			//날짜 처리
			if (StringUtils.isEmpty(DateBegin))
				DateBegin = null;
			if (StringUtils.isEmpty(DateEnd))
				DateEnd = null;

			Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
			try {
				if (DateBegin != null)
					convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
				if (DateEnd != null)
					convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
			} catch (Exception e) {
				log.info("날짜 변환 중 오류", e);

				convertedDesignDateBegin = convertedDesignDateEnd = null;
			}
			
			if(StringUtils.isEmpty(title)) {
				
				title=null;
								
			}
			
			if(StringUtils.isEmpty(usrName)) {
				requestId = null;
			}else {
				requestId = myMenuService.usrNameForIdroundrobin(usrName);
			}
			
			if(StringUtils.isEmpty(kind)) {
				
				kind=null;
								
			}else {
				if(kind.equals("A")) {
					
					kind=null;
									
				}
				
			}
			
			String loginUserId = loginDongleService.getLoginId();
			
			List<LeaveApplication> data = null;
			
			if(myMenuService.isManagerRoundrobin(loginUserId).equals("Y")) {
				
				if(myMenuService.isDirectorRoundrobin(loginUserId).equals("Y")) {
					
					data = myMenuService.leaveSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}else if(myMenuService.isFactoryRoundrobin(loginUserId).equals("Y")) {
					
					data = myMenuService.leaveSelectFactory(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}
				
				
				else {
	
						data = myMenuService.leaveSelectManager(myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId()), requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);

					
				}
				
			}
			
			else {
				
				if(myMenuService.isCEORoundrobin(loginUserId).equals("Y")) {
					
					data = myMenuService.leaveSelectCEO(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}else {
				
					
						data = myMenuService.leaveSelect(loginDongleService.getLoginId(), title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
			
				}
				
				
				
			}
			
			
			
			float countDate;
			
			if(myMenuService.leaveCountHourReal(loginDongleService.getLoginId()) == 0) {
				
				countDate = (float)0;
			}
			else {
				
				countDate = (float)(myMenuService.leaveCountHourReal(loginDongleService.getLoginId())/8.0);
			}
			
			
			String isFM = "N";
			
			if((loginDongleService.getLoginId()).equals("F0007") || (loginDongleService.getLoginId()).equals("T0048") || (loginDongleService.getLoginId()).equals("18111")) {
				isFM = "Y";
			}
			
			
			int dateLeave = myMenuService.leaveCountHourReal(loginDongleService.getLoginId())/8;
			int hourLeave = myMenuService.leaveCountHourReal(loginDongleService.getLoginId())%8;
			
			
			
			
			
			ModelAndView mv = new ModelAndView("mymenu/leaveMain");
			mv.addObject("data", data);
			mv.addObject("countDate", countDate);
			mv.addObject("title", title);
			mv.addObject("usrName", usrName);
			mv.addObject("DateBegin", DateBegin);
			mv.addObject("DateEnd", DateEnd);
			mv.addObject("kind", kind);
			mv.addObject("isFM", isFM);
			mv.addObject("dateLeave", dateLeave);
			mv.addObject("hourLeave", hourLeave);
			return mv;
		}
		
		//연장근무 신청/조회
		@RequestMapping("/overwork")
		public ModelAndView overwork(String title, String usrName, String DateBegin, String DateEnd, String kind){
			
			
			String requestId = null;
			
			//날짜 처리
			if (StringUtils.isEmpty(DateBegin))
				DateBegin = null;
			if (StringUtils.isEmpty(DateEnd))
				DateEnd = null;

			Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
			try {
				if (DateBegin != null)
					convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
				if (DateEnd != null)
					convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
			} catch (Exception e) {
				log.info("날짜 변환 중 오류", e);

				convertedDesignDateBegin = convertedDesignDateEnd = null;
			}
			
			if(StringUtils.isEmpty(title)) {
				
				title=null;
								
			}
			
			if(StringUtils.isEmpty(usrName)) {
				requestId = null;
			}else {
				requestId = myMenuService.usrNameForIdroundrobin(usrName);
			}
			
			if(StringUtils.isEmpty(kind)) {
				
				kind=null;
								
			}else {
				if(kind.equals("A")) {
					
					kind=null;
									
				}
				
			}
			
			String loginUserId = loginDongleService.getLoginId();
			
			List<Overwork> data = null;
			
			if(myMenuService.isManagerRoundrobin(loginUserId).equals("Y")) {
				
				if(myMenuService.isDirectorRoundrobin(loginUserId).equals("Y")) {
					
					data = myMenuService.overworkSelectDirector(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}else if(myMenuService.isFactoryRoundrobin(loginUserId).equals("Y")) {
					
					data = myMenuService.overworkSelectFactory(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}
				
				
				else {
	
						data = myMenuService.overworkSelectManager(myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId()), requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);

					
				}
				
			}
			
			else {
				
				if(myMenuService.isCEORoundrobin(loginUserId).equals("Y")) {
					
					data = myMenuService.overworkSelectCEO(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
					
				}else {
				
					
						data = myMenuService.overworkSelect(loginDongleService.getLoginId(), title, convertedDesignDateBegin, convertedDesignDateEnd, kind);
			
				}
				
				
				
			}
			
			
			
		
			
			ModelAndView mv = new ModelAndView("mymenu/overworkMain");
			mv.addObject("data", data);
			mv.addObject("title", title);
			mv.addObject("usrName", usrName);
			mv.addObject("DateBegin", DateBegin);
			mv.addObject("DateEnd", DateEnd);
			mv.addObject("kind", kind);
			return mv;
		}
	
	//구매품의서 등록 popup
	@RequestMapping("/roundRobin/insertPop")
	public ModelAndView roundRobin_insertPop(){
		
		
		List<UserData> data = userService.getUserData(loginDongleService.getLoginId());
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		List<StockListOrderNo> orderNodata = purchaseService.searchOrderNo();
		List<Customer> customerData = myMenuService.selectAllCustomer();
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinInsert");
		mv.addObject("data", data);
		mv.addObject("signPath", signPath);
		mv.addObject("orderNodata", orderNodata);
		mv.addObject("today", ymdDATE.format(new Date()));
		mv.addObject("customerData", customerData);

		return mv;
	}
	
	//연월차 등록 popup
		@RequestMapping("/leave/insertPop")
		public ModelAndView leave_insertPop(){
			
			
			List<UserData> data = userService.getUserData(loginDongleService.getLoginId());
			String signPath = userService.UserSignPath(loginDongleService.getLoginId());

			
			ModelAndView mv = new ModelAndView("mymenu/popup/leaveInsert");
			mv.addObject("data", data);
			mv.addObject("signPath", signPath);
			mv.addObject("today", ymdDATE.format(new Date()));


			return mv;
		}
		
	//연월차 변경 popup
		@RequestMapping("/leave/changePop/{id}")
		public ModelAndView leave_changePop(@PathVariable("id") Long preId){
			
			
			List<UserData> data = userService.getUserData(loginDongleService.getLoginId());
			String signPath = userService.UserSignPath(loginDongleService.getLoginId());

			
			ModelAndView mv = new ModelAndView("mymenu/popup/leaveChange");
			mv.addObject("data", data);
			mv.addObject("preId", preId);
			mv.addObject("signPath", signPath);
			mv.addObject("today", ymdDATE.format(new Date()));


			return mv;
		}
		
	//대리 연월차 등록 popup
		@RequestMapping("/leave/insertPop2")
		public ModelAndView leave_insertPop2(){
			
			
			List<UserData> data = userService.getUserData2();
			String signPath = "/upimg/all.png";
			
			

			
			ModelAndView mv = new ModelAndView("mymenu/popup/leaveInsert2");
			mv.addObject("data", data);
			mv.addObject("signPath", signPath);
			mv.addObject("today", ymdDATE.format(new Date()));


			return mv;
		}
		
	//대리 연월차 등록 usr Data 조회
		@RequestMapping("/leave/dataUsr")
		@ResponseBody
		public StandardResponse dataUsr(String usr) {
			
			StandardResponse res = new StandardResponse();
			try {
				
				System.out.println("USER ID = "+usr +"  !!!" );
				
				UserData usrData = userService.getUserDataOne(usr);
				
				res.setData( usrData );
				res.setResult("OK");
				
			}catch(RuntimeException e) {
				res = StandardResponse.createErrorResponse(e);
				e.printStackTrace();
			}catch(Exception e) {
				res = StandardResponse.createErrorResponse();
				e.printStackTrace();
			}

			return res;
		}	
		
	//직원 연월차 수정 Pop(관리부, 인사과 과장 전용)	
	@RequestMapping("/leave/editUsrleavePop")
	public ModelAndView leave_editUsrleavePop(){
			
			
		List<UserData> data = myMenuService.getUsrAllDatafromeditUsrleave();
		
		
		for(UserData dataOne :data) {
			
			if(dataOne.getHourCount() == 0) {
				dataOne.setRemainLeave(0);
			}
			
			else {
				
				dataOne.setRemainLeave((float)(dataOne.getHourCount()/8.0));
				dataOne.setDateLeave((dataOne.getHourCount()/8));
				dataOne.setHourLeave((dataOne.getHourCount()%8));
				
			}
			
		}
		

		int pageNo = 0;
			
		ModelAndView mv = new ModelAndView("mymenu/popup/editUsrleavePop");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		mv.addObject("today", ymdDATE.format(new Date()));


		return mv;
	}
	
	//직원 연월차 수정 동작
	@RequestMapping("leave/editUsrleavePop/do")
	public String editUsrleavePop_do(@RequestParam("id")String[] id,
							   		@RequestParam("dateLeave") int[] dateLeave,
							   		@RequestParam("hourLeave") int[] hourLeave)throws Exception {
		
		
		for(int i=0; i<id.length;i++) {
			
			
		
			
			
			int result = (dateLeave[i]*8)+hourLeave[i];
			
			
			
			
			myMenuService.setVacationusrReal(id[i], result);
	
			
		}

				
		
		return "redirect:/mymenu/leave";
									
	}
	//연장근무 등록 popup A
	@RequestMapping("/overworkA/insertPop")
	public ModelAndView overworkA_insertPop(){
			
			
		List<UserData> data = userService.getUserData(loginDongleService.getLoginId());
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());

			
		ModelAndView mv = new ModelAndView("mymenu/popup/overworkInsertA");
		mv.addObject("data", data);
		mv.addObject("signPath", signPath);
		mv.addObject("today", ymdDATE.format(new Date()));


		return mv;
	}
	
	//휴일근무 등록 popup B
	@RequestMapping("/overworkB/insertPop")
	public ModelAndView overworkB_insertPop(){
			
			
		List<UserData> data = userService.getUserData(loginDongleService.getLoginId());
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());

			
		ModelAndView mv = new ModelAndView("mymenu/popup/overworkInsertB");
		mv.addObject("data", data);
		mv.addObject("signPath", signPath);
		mv.addObject("today", ymdDATE.format(new Date()));


		return mv;
	}	
	
	//구매품의서 insert 동작
	@RequestMapping("/roundRobin/insert/do")
	public String roundRobin_insertDo(@RequestParam("userId") String userId,
									  @RequestParam("img1Path") String img1Path,
									  @RequestParam("deptCode") String deptCode,
									  @RequestParam("orderNo") Long orderNo,
									  @RequestParam("customerId") String customerId,
									  @RequestParam("title") String title,
									  @RequestParam("reason") String reason,
									  @RequestParam("description") String[] description,
									  @RequestParam("modelNo") String[] modelNo,
									  @RequestParam("maker") String[] maker,
									  @RequestParam("quantity") int[] quantity,
									  @RequestParam("requestDate") String[] requestDate){
		
		
		
		String inDate   = new java.text.SimpleDateFormat("yyMMddhhmmss").format(new java.util.Date());
		
		String roundNo = inDate.trim() + "-" + userId.trim();
		
		String device = "";
		Long orderId = 0L;
		
		if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
			
			for(int i=0;i<modelNo.length;i++) {
				
				Date rdate = null;
				RoundRobin RoundRobin = new RoundRobin();
				
				try {
					if (requestDate[i] != null)
						rdate = CommonRule.ymdFormat.parse(requestDate[i]);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					rdate = null;

				}
				
				
				if(orderNo != 0) {
					RoundRobin.setJobOrderId(orderNo);
					orderId = orderNo;
				}
				else {
					RoundRobin.setJobOrderId(myMenuService.getConsumablesOrderId());
					orderId = myMenuService.getConsumablesOrderId();
				}
				
				
				
				RoundRobin.setRoundNo(roundNo);
				RoundRobin.setRequestUsr(userId);
				RoundRobin.setSignR(img1Path);
				RoundRobin.setSignM(img1Path);
				RoundRobin.setDescription(description[i]);
				RoundRobin.setRequestDept(deptCode);
				RoundRobin.setCustomerId(customerId);
				RoundRobin.setTitle(title);
				RoundRobin.setRequestReason(reason);
				RoundRobin.setModelNo(modelNo[i]);
				RoundRobin.setMaker(maker[i]);
				RoundRobin.setQuantity(quantity[i]);
				RoundRobin.setRequestDate(rdate);
				
				myMenuService.insertRobinToM(RoundRobin);
				
				device = device + modelNo[i] + ", ";
				
			}
			
			
			try {
				
				String fullOrder = myMenuService.orderNoFullNameFromNoticeMessage(orderId);
				
				if(fullOrder.equals("ZZZZZ")) {
					fullOrder = "consumables";
				}
				
				noticeMessageService.addRoundRobinNotice("B", "PQ", deptCode, orderId, "M", device, "구매품의서가 등록되었습니다." , myMenuService.sumPriceFromRoundNumNoticeMessage(roundNo), roundNo);
				pushAlarmService.pqSendService("["+ fullOrder + "]", "PurchaseRoundRobin");
			
			}catch(Exception e) {
				
				System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
			}
			
			
		}else {
			
			
			for(int i=0;i<modelNo.length;i++) {
				
				Date rdate = null;
				RoundRobin RoundRobin = new RoundRobin();
				
				try {
					if (requestDate[i] != null)
						rdate = CommonRule.ymdFormat.parse(requestDate[i]);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					rdate = null;

				}
				
				
				if(orderNo != 0) {
					RoundRobin.setJobOrderId(orderNo);
					orderId = orderNo;
				}
				else {
					RoundRobin.setJobOrderId(myMenuService.getConsumablesOrderId());
					orderId = myMenuService.getConsumablesOrderId();
				}
				
				
				
				RoundRobin.setRoundNo(roundNo);
				RoundRobin.setRequestUsr(userId);
				RoundRobin.setSignR(img1Path);
				RoundRobin.setDescription(description[i]);
				RoundRobin.setRequestDept(deptCode);
				RoundRobin.setCustomerId(customerId);
				RoundRobin.setTitle(title);
				RoundRobin.setRequestReason(reason);
				RoundRobin.setModelNo(modelNo[i]);
				RoundRobin.setMaker(maker[i]);
				RoundRobin.setQuantity(quantity[i]);
				RoundRobin.setRequestDate(rdate);
				
				myMenuService.insertRobin(RoundRobin);
				
				device = device + modelNo[i] + ", ";
				
			}
			
			
			try {
				
				String fullOrder = myMenuService.orderNoFullNameFromNoticeMessage(orderId);
				
				if(fullOrder.equals("ZZZZZ")) {
					fullOrder = "consumables";
				}
				
				noticeMessageService.addRoundRobinNotice("B", "M", deptCode, orderId, "R", device, "구매품의서가 등록되었습니다." , myMenuService.sumPriceFromRoundNumNoticeMessage(roundNo), roundNo);
				pushAlarmService.managerSendService("["+ fullOrder + "]", "PurchaseRoundRobin", deptCode);
			
			}catch(Exception e) {
				
				System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
			}
			
			
			
		}
		
		
		

		return "redirect:/mymenu/roundRobin";
	}
	
	//연장근무 insert동작 A
	@RequestMapping("/overworkA/insert/do")
	public String overworkA_insertDo(@RequestParam("userId") String userId,
									  @RequestParam("img1Path") String img1Path,
									  @RequestParam("deptCode") String deptCode,									 
									  @RequestParam("title") String title,
									  @RequestParam("requestDate") String requestDate,
									  @RequestParam("endhour") String endhour,
									  @RequestParam("endmin") String endmin,
									  @RequestParam("requestReason") String requestReason){
			
			
			
			
		if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				
				
			if(myMenuService.isDirectorRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				Overwork overwork = new Overwork();
				
				overwork.setTitle(title);
				overwork.setRequestReason(requestReason);
				overwork.setRequestDept(deptCode);
				overwork.setRequestUsr(userId);

				Date wdate = null;
				try {
					if (requestDate != null)
						wdate = CommonRule.ymdFormat.parse(requestDate);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					wdate = null;

				}
				
				overwork.setRequestDate(wdate);
				overwork.setKind("A");		
				overwork.setRequestStrtime("22:00");
				overwork.setRequestEndtime(endhour + ":" + endmin);
				
				int ehour = Integer.parseInt(endhour);
				
				if(ehour<22) {
				
					int result = 120 + (ehour*60) + Integer.parseInt(endmin);
					
					overwork.setRequestMin(result);
					
				}else {
					
					int rhour = ehour - 22;
					
					int result = (rhour*60) + Integer.parseInt(endmin);
					
					overwork.setRequestMin(result);
					
				}
				
				
					
				overwork.setKindDept("A");
				
				overwork.setConformStage("J");
				overwork.setSignR(img1Path);
				overwork.setSignM(img1Path);
				overwork.setSignJ(img1Path);
				
				
				
				
				
				Long leaveOverworkId = myMenuService.insertoverworkAJ(overwork);
				
				
			
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
				
				
					
			}
			else if(myMenuService.isFactoryRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				Overwork overwork = new Overwork();
				
				overwork.setTitle(title);
				overwork.setRequestReason(requestReason);
				overwork.setRequestDept(deptCode);
				overwork.setRequestUsr(userId);

				Date wdate = null;
				try {
					if (requestDate != null)
						wdate = CommonRule.ymdFormat.parse(requestDate);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					wdate = null;

				}
				
				overwork.setRequestDate(wdate);
				overwork.setKind("A");		
				overwork.setRequestStrtime("22:00");
				overwork.setRequestEndtime(endhour + ":" + endmin);
				
				int ehour = Integer.parseInt(endhour);
				
				if(ehour<22) {
				
					int result = 120 + (ehour*60) + Integer.parseInt(endmin);
					
					overwork.setRequestMin(result);
					
				}else {
					
					int rhour = ehour - 22;
					
					int result = (rhour*60) + Integer.parseInt(endmin);
					
					overwork.setRequestMin(result);
					
				}
				
				
					
				overwork.setKindDept("B");
				
				overwork.setConformStage("J");
				overwork.setSignR(img1Path);
				overwork.setSignM(img1Path);
				overwork.setSignJ(img1Path);
				
				
				
				
				
				Long leaveOverworkId = myMenuService.insertoverworkAJ(overwork);
				
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
					
					
			}
				
				
			else {
				
				Overwork overwork = new Overwork();
				
				overwork.setTitle(title);
				overwork.setRequestReason(requestReason);
				overwork.setRequestDept(deptCode);
				overwork.setRequestUsr(userId);

				Date wdate = null;
				try {
					if (requestDate != null)
						wdate = CommonRule.ymdFormat.parse(requestDate);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					wdate = null;

				}
				
				overwork.setRequestDate(wdate);
				overwork.setKind("A");		
				overwork.setRequestStrtime("22:00");
				overwork.setRequestEndtime(endhour + ":" + endmin);
				
				int ehour = Integer.parseInt(endhour);
				
				if(ehour<22) {
				
					int result = 120 + (ehour*60) + Integer.parseInt(endmin);
					
					overwork.setRequestMin(result);
					
				}else {
					
					int rhour = ehour - 22;
					
					int result = (rhour*60) + Integer.parseInt(endmin);
					
					overwork.setRequestMin(result);
					
				}
				
				if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
					
					overwork.setKindDept("B");
				
				}else {
					
					overwork.setKindDept("A");
				}
				
				overwork.setConformStage("M");
				overwork.setSignR(img1Path);
				overwork.setSignM(img1Path);
				
				
				
				
				
				Long leaveOverworkId = myMenuService.insertoverworkAM(overwork);
				
				
				
				
				
				if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
					
					try {
						
						noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
						pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
					
					}catch(Exception e) {
						
						System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
					}
					
				}else {
					
					try {
						
						noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
						pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
					
					}catch(Exception e) {
						
						System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
					}
					
					
					
				}
				
					
					
			}
				

				
		}else if(myMenuService.isCEORoundrobin(loginDongleService.getLoginId()).equals("Y")) {
			
			Overwork overwork = new Overwork();
			
			overwork.setTitle(title);
			overwork.setRequestReason(requestReason);
			overwork.setRequestDept(deptCode);
			overwork.setRequestUsr(userId);

			Date wdate = null;
			try {
				if (requestDate != null)
					wdate = CommonRule.ymdFormat.parse(requestDate);
			
			} catch (Exception e) {
				log.info("날짜 변환 중 오류", e);
				wdate = null;

			}
			
			overwork.setRequestDate(wdate);
			overwork.setKind("A");		
			overwork.setRequestStrtime("22:00");
			overwork.setRequestEndtime(endhour + ":" + endmin);
			
			int ehour = Integer.parseInt(endhour);
			
			if(ehour<22) {
			
				int result = 120 + (ehour*60) + Integer.parseInt(endmin);
				
				overwork.setRequestMin(result);
				
			}else {
				
				int rhour = ehour - 22;
				
				int result = (rhour*60) + Integer.parseInt(endmin);
				
				overwork.setRequestMin(result);
				
			}
			
			
				
			overwork.setKindDept("A");
			
			overwork.setConformStage("C");
			overwork.setSignR(img1Path);
			overwork.setSignM(img1Path);
			overwork.setSignJ(img1Path);
			overwork.setSignC(img1Path);
			
			
			
			
			myMenuService.insertoverworkAC(overwork);
				
				
		}
			
			
		else {
			
			Overwork overwork = new Overwork();
			
			overwork.setTitle(title);
			overwork.setRequestReason(requestReason);
			overwork.setRequestDept(deptCode);
			overwork.setRequestUsr(userId);

			Date wdate = null;
			try {
				if (requestDate != null)
					wdate = CommonRule.ymdFormat.parse(requestDate);
			
			} catch (Exception e) {
				log.info("날짜 변환 중 오류", e);
				wdate = null;

			}
			
			overwork.setRequestDate(wdate);
			overwork.setKind("A");		
			overwork.setRequestStrtime("22:00");
			overwork.setRequestEndtime(endhour + ":" + endmin);
			
			int ehour = Integer.parseInt(endhour);
			
			if(ehour<22) {
			
				int result = 120 + (ehour*60) + Integer.parseInt(endmin);
				
				overwork.setRequestMin(result);
				
			}else {
				
				int rhour = ehour - 22;
				
				int result = (rhour*60) + Integer.parseInt(endmin);
				
				overwork.setRequestMin(result);
				
			}
			
			if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
				
				overwork.setKindDept("B");
			
			}else {
				
				overwork.setKindDept("A");
			}
			
			overwork.setConformStage("R");
			overwork.setSignR(img1Path);
			
			
			
			
			Long leaveOverworkId = myMenuService.insertoverworkA(overwork);
			
			
			if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
				
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
					pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
				
			}
			
			else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
					pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
				
			}
			
			else {
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, "연장근무", requestDate, "22:00 ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연장근무 신청");
					pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork", deptCode);
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
				
				
				
				
				
			}
				
		}
			
			
			

		return "redirect:/mymenu/overwork";
	}
	
	//휴일근무 insert 동작 B
	@RequestMapping("/overworkB/insert/do")
	public String overworkB_insertDo(@RequestParam("userId") String userId,
									  @RequestParam("img1Path") String img1Path,
									  @RequestParam("deptCode") String deptCode,									 
									  @RequestParam("title") String title,
									  @RequestParam("requestDate") String requestDate,
									  @RequestParam("strhour") String strhour,
									  @RequestParam("strmin") String strmin,
									  @RequestParam("endhour") String endhour,
									  @RequestParam("endmin") String endmin,
									  @RequestParam("requestReason") String requestReason){
			
			
			
			
		if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				
				
			if(myMenuService.isDirectorRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				Overwork overwork = new Overwork();
				
				overwork.setTitle(title);
				overwork.setRequestReason(requestReason);
				overwork.setRequestDept(deptCode);
				overwork.setRequestUsr(userId);

				Date wdate = null;
				try {
					if (requestDate != null)
						wdate = CommonRule.ymdFormat.parse(requestDate);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					wdate = null;

				}
				
				overwork.setRequestDate(wdate);
				overwork.setKind("B");		
				overwork.setRequestStrtime(strhour + ":" + strmin);
				overwork.setRequestEndtime(endhour + ":" + endmin);
				
				int shour = Integer.parseInt(strhour);
				int ehour = Integer.parseInt(endhour);
				
				
				
				if(shour<ehour) {
				
					int strSumtime = (shour*60)+Integer.parseInt(strmin);
					
					int endSumtime = (ehour*60)+Integer.parseInt(endmin);
					
					int result = endSumtime - strSumtime;
					
					overwork.setRequestMin(result);
					
				}else {
					
					
					int strSumtime = (shour*60)+Integer.parseInt(strmin);
					
					int endSumtime = ((ehour+24)*60)+Integer.parseInt(endmin);
					
					int result = endSumtime - strSumtime;
					
					overwork.setRequestMin(result);
					
				}
				
				
					
				overwork.setKindDept("A");
				
				overwork.setConformStage("J");
				overwork.setSignR(img1Path);
				overwork.setSignM(img1Path);
				overwork.setSignJ(img1Path);
				
				
				
				
				
				Long leaveOverworkId = myMenuService.insertoverworkAJ(overwork);
				
				

				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
					
					
			}
			else if(myMenuService.isFactoryRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				Overwork overwork = new Overwork();
				
				overwork.setTitle(title);
				overwork.setRequestReason(requestReason);
				overwork.setRequestDept(deptCode);
				overwork.setRequestUsr(userId);

				Date wdate = null;
				try {
					if (requestDate != null)
						wdate = CommonRule.ymdFormat.parse(requestDate);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					wdate = null;

				}
				
				overwork.setRequestDate(wdate);
				overwork.setKind("B");		
				overwork.setRequestStrtime(strhour + ":" + strmin);
				overwork.setRequestEndtime(endhour + ":" + endmin);
				
				int shour = Integer.parseInt(strhour);
				int ehour = Integer.parseInt(endhour);
				
				
				
				if(shour<ehour) {
				
					int strSumtime = (shour*60)+Integer.parseInt(strmin);
					
					int endSumtime = (ehour*60)+Integer.parseInt(endmin);
					
					int result = endSumtime - strSumtime;
					
					overwork.setRequestMin(result);
					
				}else {
					
					
					int strSumtime = (shour*60)+Integer.parseInt(strmin);
					
					int endSumtime = ((ehour+24)*60)+Integer.parseInt(endmin);
					
					int result = endSumtime - strSumtime;
					
					overwork.setRequestMin(result);
					
				}
				
				
					
				overwork.setKindDept("B");
				
				overwork.setConformStage("J");
				overwork.setSignR(img1Path);
				overwork.setSignM(img1Path);
				overwork.setSignJ(img1Path);
				
				
				
				
				
				Long leaveOverworkId = myMenuService.insertoverworkAJ(overwork);
				
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
					pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
					
					
			}
				
				
			else {
				
				Overwork overwork = new Overwork();
				
				overwork.setTitle(title);
				overwork.setRequestReason(requestReason);
				overwork.setRequestDept(deptCode);
				overwork.setRequestUsr(userId);

				Date wdate = null;
				try {
					if (requestDate != null)
						wdate = CommonRule.ymdFormat.parse(requestDate);
				
				} catch (Exception e) {
					log.info("날짜 변환 중 오류", e);
					wdate = null;

				}
				
				overwork.setRequestDate(wdate);
				overwork.setKind("B");		
				overwork.setRequestStrtime(strhour+":"+strmin);
				overwork.setRequestEndtime(endhour + ":" + endmin);
				
				int shour = Integer.parseInt(strhour);
				int ehour = Integer.parseInt(endhour);
				
				
				
				if(shour<ehour) {
				
					int strSumtime = (shour*60)+Integer.parseInt(strmin);
					
					int endSumtime = (ehour*60)+Integer.parseInt(endmin);
					
					int result = endSumtime - strSumtime;
					
					overwork.setRequestMin(result);
					
				}else {
					
					
					int strSumtime = (shour*60)+Integer.parseInt(strmin);
					
					int endSumtime = ((ehour+24)*60)+Integer.parseInt(endmin);
					
					int result = endSumtime - strSumtime;
					
					overwork.setRequestMin(result);
					
				}
				
				if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
					
					overwork.setKindDept("B");
				
				}else {
					
					overwork.setKindDept("A");
				}
				
				overwork.setConformStage("M");
				overwork.setSignR(img1Path);
				overwork.setSignM(img1Path);
				
				
				
				
				
				Long leaveOverworkId = myMenuService.insertoverworkAM(overwork);
				
				
				if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
					
					try {
						
						noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
						pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
					
					}catch(Exception e) {
						
						System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
					}
					
				}else {
					
					try {
						
						noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
						pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
					
					}catch(Exception e) {
						
						System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
					}
					
					
					
				}
				
					
					
			}
				

				
		}else if(myMenuService.isCEORoundrobin(loginDongleService.getLoginId()).equals("Y")) {
			
			Overwork overwork = new Overwork();
			
			overwork.setTitle(title);
			overwork.setRequestReason(requestReason);
			overwork.setRequestDept(deptCode);
			overwork.setRequestUsr(userId);

			Date wdate = null;
			try {
				if (requestDate != null)
					wdate = CommonRule.ymdFormat.parse(requestDate);
			
			} catch (Exception e) {
				log.info("날짜 변환 중 오류", e);
				wdate = null;

			}
			
			overwork.setRequestDate(wdate);
			overwork.setKind("B");		
			overwork.setRequestStrtime(strhour + ":" + strmin);
			overwork.setRequestEndtime(endhour + ":" + endmin);
			
			int shour = Integer.parseInt(strhour);
			int ehour = Integer.parseInt(endhour);
			
			
			
			if(shour<ehour) {
			
				int strSumtime = (shour*60)+Integer.parseInt(strmin);
				
				int endSumtime = (ehour*60)+Integer.parseInt(endmin);
				
				int result = endSumtime - strSumtime;
				
				overwork.setRequestMin(result);
				
			}else {
				
				
				int strSumtime = (shour*60)+Integer.parseInt(strmin);
				
				int endSumtime = ((ehour+24)*60)+Integer.parseInt(endmin);
				
				int result = endSumtime - strSumtime;
				
				overwork.setRequestMin(result);
				
			}
			
			
				
			overwork.setKindDept("A");
			
			overwork.setConformStage("C");
			overwork.setSignR(img1Path);
			overwork.setSignM(img1Path);
			overwork.setSignJ(img1Path);
			overwork.setSignC(img1Path);
			
			
			
			
			myMenuService.insertoverworkAC(overwork);
				
				
		}
			
			
		else {
			
			Overwork overwork = new Overwork();
			
			overwork.setTitle(title);
			overwork.setRequestReason(requestReason);
			overwork.setRequestDept(deptCode);
			overwork.setRequestUsr(userId);

			Date wdate = null;
			try {
				if (requestDate != null)
					wdate = CommonRule.ymdFormat.parse(requestDate);
			
			} catch (Exception e) {
				log.info("날짜 변환 중 오류", e);
				wdate = null;

			}
			
			overwork.setRequestDate(wdate);
			overwork.setKind("B");		
			overwork.setRequestStrtime(strhour + ":" + strmin);
			overwork.setRequestEndtime(endhour + ":" + endmin);
			
			
			
			int shour = Integer.parseInt(strhour);
			int ehour = Integer.parseInt(endhour);
			
			
			
			if(shour<ehour) {
			
				int strSumtime = (shour*60)+Integer.parseInt(strmin);
				
				int endSumtime = (ehour*60)+Integer.parseInt(endmin);
				
				int result = endSumtime - strSumtime;
				
				overwork.setRequestMin(result);
				
			}else {
				
				
				int strSumtime = (shour*60)+Integer.parseInt(strmin);
				
				int endSumtime = ((ehour+24)*60)+Integer.parseInt(endmin);
				
				int result = endSumtime - strSumtime;
				
				overwork.setRequestMin(result);
				
			}
			
			if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
				
				overwork.setKindDept("B");
			
			}else {
				
				overwork.setKindDept("A");
			}
			
			overwork.setConformStage("R");
			overwork.setSignR(img1Path);
			
			
			
			
			Long leaveOverworkId = myMenuService.insertoverworkA(overwork);
			
			
			
			if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
				
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
					pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
				
			}
			
			else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
					pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork");
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}
				
			}
			
			else {
				
				try {
					
					noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, "휴일근무", requestDate, strhour+":"+strmin+" ~ "+ endhour+":"+endmin, leaveOverworkId, overwork.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 휴일근무 신청");
					pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "OverWork", deptCode);
				
				}catch(Exception e) {
					
					System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
				}

				
			}
				
		}
			
			
			

		return "redirect:/mymenu/overwork";
	}
		
		
		//연월차 insert 동작
		@RequestMapping("/leave/insert/do")
		public String leave_insertDo(@RequestParam("userId") String userId,
										  @RequestParam("img1Path") String img1Path,
										  @RequestParam("deptCode") String deptCode,									 
										  @RequestParam("title") String title,
										  @RequestParam("requestStrdate") String requestStrdate,
										  @RequestParam("requestEnddate") String requestEnddate,
										  @RequestParam("requestDate") int requestDate,
										  @RequestParam("requestReason") String requestReason,
										  @RequestParam(value="changeY", defaultValue="N")String changeY,
										  @RequestParam(value="preId", defaultValue="")Long preId){
			
			
			if(changeY.equals("Y")) {
				
			
			
			if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				
				
				if(myMenuService.isDirectorRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
					

					
					
					LeaveApplication leaveApplication = new LeaveApplication();
					
					if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("A");	
						leaveApplication.setKindDept("A");
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						
						//변경  추가 로직
						leaveApplication.setChanged(changeY);
						leaveApplication.setPreId(preId);
						
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
						
						if(myMenuService.leaveGetStage(preId).equals("Y")) {
							if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
								
								int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
								
								myMenuService.addUserVacation(userId, leaveDate);
								
								myMenuService.vacationDeletedAndChange(preId);

								
							}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}else {
								
								int leaveHour = myMenuService.leaveGetRequestHour(preId);
								
								myMenuService.addUserVacation(userId, leaveHour);
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}
							
							
						}else {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						//여기까지 변경 추가 로직
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}
					else if(title.equals("경조휴가")||title.equals("공가")) {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("B");
						leaveApplication.setKindDept("A");			
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						
						leaveApplication.setChanged(changeY);
						leaveApplication.setPreId(preId);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
						
						if(myMenuService.leaveGetStage(preId).equals("Y")) {
							if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
								
								int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
								
								myMenuService.addUserVacation(userId, leaveDate);
								
								myMenuService.vacationDeletedAndChange(preId);

								
							}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}else {
								
								int leaveHour = myMenuService.leaveGetRequestHour(preId);
								
								myMenuService.addUserVacation(userId, leaveHour);
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}
							
							
						}else {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}else {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(0);
						leaveApplication.setRequestHour(requestDate);
						leaveApplication.setKind("C");
						leaveApplication.setKindDept("A");				
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						
						leaveApplication.setChanged(changeY);
						leaveApplication.setPreId(preId);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
						
						if(myMenuService.leaveGetStage(preId).equals("Y")) {
							if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
								
								int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
								
								myMenuService.addUserVacation(userId, leaveDate);
								
								myMenuService.vacationDeletedAndChange(preId);

								
							}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}else {
								
								int leaveHour = myMenuService.leaveGetRequestHour(preId);
								
								myMenuService.addUserVacation(userId, leaveHour);
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}
							
							
						}else {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
						
					}
					
					
					
				}
				else if(myMenuService.isFactoryRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
					

					
					
					LeaveApplication leaveApplication = new LeaveApplication();
					
					if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("A");	
						leaveApplication.setKindDept("B");
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						
						leaveApplication.setChanged(changeY);
						leaveApplication.setPreId(preId);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
						
						if(myMenuService.leaveGetStage(preId).equals("Y")) {
							if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
								
								int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
								
								myMenuService.addUserVacation(userId, leaveDate);
								
								myMenuService.vacationDeletedAndChange(preId);

								
							}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}else {
								
								int leaveHour = myMenuService.leaveGetRequestHour(preId);
								
								myMenuService.addUserVacation(userId, leaveHour);
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}
							
							
						}else {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}
					else if(title.equals("경조휴가")||title.equals("공가")) {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("B");
						leaveApplication.setKindDept("B");			
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						
						leaveApplication.setChanged(changeY);
						leaveApplication.setPreId(preId);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
						
						if(myMenuService.leaveGetStage(preId).equals("Y")) {
							if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
								
								int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
								
								myMenuService.addUserVacation(userId, leaveDate);
								
								myMenuService.vacationDeletedAndChange(preId);

								
							}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}else {
								
								int leaveHour = myMenuService.leaveGetRequestHour(preId);
								
								myMenuService.addUserVacation(userId, leaveHour);
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}
							
							
						}else {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}else {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(0);
						leaveApplication.setRequestHour(requestDate);
						leaveApplication.setKind("C");
						leaveApplication.setKindDept("B");				
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						
						leaveApplication.setChanged(changeY);
						leaveApplication.setPreId(preId);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
						
						if(myMenuService.leaveGetStage(preId).equals("Y")) {
							if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
								
								int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
								
								myMenuService.addUserVacation(userId, leaveDate);
								
								myMenuService.vacationDeletedAndChange(preId);

								
							}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}else {
								
								int leaveHour = myMenuService.leaveGetRequestHour(preId);
								
								myMenuService.addUserVacation(userId, leaveHour);
								
								myMenuService.vacationDeletedAndChange(preId);
								
							}
							
							
						}else {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
						
					}

					
				}
				
				
				else {
				
				LeaveApplication leaveApplication = new LeaveApplication();
				
				if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(requestDate);
					leaveApplication.setRequestHour(0);
					leaveApplication.setKind("A");
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						leaveApplication.setKindDept("B");
					
					}else {
						
						leaveApplication.setKindDept("A");
					}
					
					leaveApplication.setConformStage("M");
					leaveApplication.setSignR(img1Path);
					leaveApplication.setSignM(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					Long leaveOverworkId = myMenuService.insertLeaveApplicationM(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}
					
					
					
					
				}
				else if(title.equals("경조휴가")||title.equals("공가")) {
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(requestDate);
					leaveApplication.setRequestHour(0);
					leaveApplication.setKind("B");
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						leaveApplication.setKindDept("B");
					
					}else {
						
						leaveApplication.setKindDept("A");
					}
					
					leaveApplication.setConformStage("M");
					leaveApplication.setSignR(img1Path);
					leaveApplication.setSignM(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					Long leaveOverworkId = myMenuService.insertLeaveApplicationM(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}
					
					
				}else {
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(0);
					leaveApplication.setRequestHour(requestDate);
					leaveApplication.setKind("C");
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						leaveApplication.setKindDept("B");
					
					}else {
						
						leaveApplication.setKindDept("A");
					}
					
					leaveApplication.setConformStage("M");
					leaveApplication.setSignR(img1Path);
					leaveApplication.setSignM(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					Long leaveOverworkId = myMenuService.insertLeaveApplicationM(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
					}
				}
					
					
					
				}
				

				
			}else if(myMenuService.isCEORoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				
				
				LeaveApplication leaveApplication = new LeaveApplication();
				
				if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(requestDate);
					leaveApplication.setRequestHour(0);
					leaveApplication.setKind("A");	
					leaveApplication.setKindDept("A");
					leaveApplication.setConformStage("C");
					leaveApplication.setSignR(img1Path);
					leaveApplication.setSignM(img1Path);
					leaveApplication.setSignJ(img1Path);
					leaveApplication.setSignC(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					myMenuService.insertLeaveApplicationC(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					
				}
				else if(title.equals("경조휴가")||title.equals("공가")) {
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(requestDate);
					leaveApplication.setRequestHour(0);
					leaveApplication.setKind("B");
					leaveApplication.setKindDept("A");			
					leaveApplication.setConformStage("J");
					leaveApplication.setSignR(img1Path);
					leaveApplication.setSignM(img1Path);
					leaveApplication.setSignJ(img1Path);
					leaveApplication.setSignC(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					myMenuService.insertLeaveApplicationC(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					
				}else {
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(0);
					leaveApplication.setRequestHour(requestDate);
					leaveApplication.setKind("C");
					leaveApplication.setKindDept("A");				
					leaveApplication.setConformStage("J");
					leaveApplication.setSignR(img1Path);
					leaveApplication.setSignM(img1Path);
					leaveApplication.setSignJ(img1Path);
					leaveApplication.setSignC(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					myMenuService.insertLeaveApplicationC(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					
					
				}
				
				
			}
			
			
			else {
				
				
				LeaveApplication leaveApplication = new LeaveApplication();
				
				if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(requestDate);
					leaveApplication.setRequestHour(0);
					leaveApplication.setKind("A");
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						leaveApplication.setKindDept("B");
					
					}else {
						
						leaveApplication.setKindDept("A");
					}
					
					leaveApplication.setConformStage("R");
					leaveApplication.setSignR(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					Long leaveOverworkId = myMenuService.insertLeaveApplication(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation", deptCode);
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
						
					}
					
					
					
					
					
					
					
				}
				else if(title.equals("경조휴가")||title.equals("공가")) {
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(requestDate);
					leaveApplication.setRequestHour(0);
					leaveApplication.setKind("B");
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						leaveApplication.setKindDept("B");
					
					}else {
						
						leaveApplication.setKindDept("A");
					}
					
					leaveApplication.setConformStage("R");
					leaveApplication.setSignR(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					Long leaveOverworkId = myMenuService.insertLeaveApplication(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation", deptCode);
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
						
					}
					
					
				}else {
					
					leaveApplication.setTitle(title);
					leaveApplication.setRequestReason(requestReason);
					leaveApplication.setRequestDept(deptCode);
					leaveApplication.setRequestUsr(userId);
					
					Date sdate = null;
					Date edate = null;
					try {
						if (requestStrdate != null)
							sdate = CommonRule.ymdFormat.parse(requestStrdate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						sdate = null;

					}
					
					try {
						if (requestEnddate != null)
							edate = CommonRule.ymdFormat.parse(requestEnddate);
					
					} catch (Exception e) {
						log.info("날짜 변환 중 오류", e);
						edate = null;

					}
					
					leaveApplication.setRequestStrdate(sdate);
					leaveApplication.setRequestEnddate(edate);
					leaveApplication.setRequestDate(0);
					leaveApplication.setRequestHour(requestDate);
					leaveApplication.setKind("C");
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
						
						leaveApplication.setKindDept("B");
					
					}else {
						
						leaveApplication.setKindDept("A");
					}
					
					leaveApplication.setConformStage("R");
					leaveApplication.setSignR(img1Path);
					
					leaveApplication.setChanged(changeY);
					leaveApplication.setPreId(preId);
					
					Long leaveOverworkId = myMenuService.insertLeaveApplication(leaveApplication);
					
					if(myMenuService.leaveGetStage(preId).equals("Y")) {
						if((myMenuService.leaveGettitle(preId).equals("연차휴가")) || (myMenuService.leaveGettitle(preId).equals("생리휴가")) || (myMenuService.leaveGettitle(preId).equals("병가"))) {
							
							int leaveDate = (myMenuService.leaveGetRequestDate(preId)) * 8;
							
							myMenuService.addUserVacation(userId, leaveDate);
							
							myMenuService.vacationDeletedAndChange(preId);

							
						}else if((myMenuService.leaveGettitle(preId).equals("경조휴가")) || (myMenuService.leaveGettitle(preId).equals("공가"))) {
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}else {
							
							int leaveHour = myMenuService.leaveGetRequestHour(preId);
							
							myMenuService.addUserVacation(userId, leaveHour);
							
							myMenuService.vacationDeletedAndChange(preId);
							
						}
						
						
					}else {
						
						myMenuService.vacationDeletedAndChange(preId);
						
					}
					
					if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
					}else {
						
						try {
							
							noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 변경 신청");
							pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation", deptCode);
						
						}catch(Exception e) {
							
							System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
						}
						
						
						
					}
					
					
					
				}
				
				
			}
			
			}
			else {
				
				

				
				
				
				if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
					
					
					
					if(myMenuService.isDirectorRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
						

						
						
						LeaveApplication leaveApplication = new LeaveApplication();
						
						if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
							
							leaveApplication.setTitle(title);
							leaveApplication.setRequestReason(requestReason);
							leaveApplication.setRequestDept(deptCode);
							leaveApplication.setRequestUsr(userId);
							
							Date sdate = null;
							Date edate = null;
							try {
								if (requestStrdate != null)
									sdate = CommonRule.ymdFormat.parse(requestStrdate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								sdate = null;

							}
							
							try {
								if (requestEnddate != null)
									edate = CommonRule.ymdFormat.parse(requestEnddate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								edate = null;

							}
							
							leaveApplication.setRequestStrdate(sdate);
							leaveApplication.setRequestEnddate(edate);
							leaveApplication.setRequestDate(requestDate);
							leaveApplication.setRequestHour(0);
							leaveApplication.setKind("A");	
							leaveApplication.setKindDept("A");
							leaveApplication.setConformStage("J");
							leaveApplication.setSignR(img1Path);
							leaveApplication.setSignM(img1Path);
							leaveApplication.setSignJ(img1Path);
							
							Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}
						else if(title.equals("경조휴가")||title.equals("공가")) {
							
							leaveApplication.setTitle(title);
							leaveApplication.setRequestReason(requestReason);
							leaveApplication.setRequestDept(deptCode);
							leaveApplication.setRequestUsr(userId);
							
							Date sdate = null;
							Date edate = null;
							try {
								if (requestStrdate != null)
									sdate = CommonRule.ymdFormat.parse(requestStrdate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								sdate = null;

							}
							
							try {
								if (requestEnddate != null)
									edate = CommonRule.ymdFormat.parse(requestEnddate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								edate = null;

							}
							
							leaveApplication.setRequestStrdate(sdate);
							leaveApplication.setRequestEnddate(edate);
							leaveApplication.setRequestDate(requestDate);
							leaveApplication.setRequestHour(0);
							leaveApplication.setKind("B");
							leaveApplication.setKindDept("A");			
							leaveApplication.setConformStage("J");
							leaveApplication.setSignR(img1Path);
							leaveApplication.setSignM(img1Path);
							leaveApplication.setSignJ(img1Path);
							
							Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}else {
							
							leaveApplication.setTitle(title);
							leaveApplication.setRequestReason(requestReason);
							leaveApplication.setRequestDept(deptCode);
							leaveApplication.setRequestUsr(userId);
							
							Date sdate = null;
							Date edate = null;
							try {
								if (requestStrdate != null)
									sdate = CommonRule.ymdFormat.parse(requestStrdate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								sdate = null;

							}
							
							try {
								if (requestEnddate != null)
									edate = CommonRule.ymdFormat.parse(requestEnddate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								edate = null;

							}
							
							leaveApplication.setRequestStrdate(sdate);
							leaveApplication.setRequestEnddate(edate);
							leaveApplication.setRequestDate(0);
							leaveApplication.setRequestHour(requestDate);
							leaveApplication.setKind("C");
							leaveApplication.setKindDept("A");				
							leaveApplication.setConformStage("J");
							leaveApplication.setSignR(img1Path);
							leaveApplication.setSignM(img1Path);
							leaveApplication.setSignJ(img1Path);
							
							Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
							
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외출/반차 신청");
								pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
							
						}
						
						
						
					}
					else if(myMenuService.isFactoryRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
						

						
						
						LeaveApplication leaveApplication = new LeaveApplication();
						
						if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
							
							leaveApplication.setTitle(title);
							leaveApplication.setRequestReason(requestReason);
							leaveApplication.setRequestDept(deptCode);
							leaveApplication.setRequestUsr(userId);
							
							Date sdate = null;
							Date edate = null;
							try {
								if (requestStrdate != null)
									sdate = CommonRule.ymdFormat.parse(requestStrdate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								sdate = null;

							}
							
							try {
								if (requestEnddate != null)
									edate = CommonRule.ymdFormat.parse(requestEnddate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								edate = null;

							}
							
							leaveApplication.setRequestStrdate(sdate);
							leaveApplication.setRequestEnddate(edate);
							leaveApplication.setRequestDate(requestDate);
							leaveApplication.setRequestHour(0);
							leaveApplication.setKind("A");	
							leaveApplication.setKindDept("B");
							leaveApplication.setConformStage("J");
							leaveApplication.setSignR(img1Path);
							leaveApplication.setSignM(img1Path);
							leaveApplication.setSignJ(img1Path);
							
							Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}
						else if(title.equals("경조휴가")||title.equals("공가")) {
							
							leaveApplication.setTitle(title);
							leaveApplication.setRequestReason(requestReason);
							leaveApplication.setRequestDept(deptCode);
							leaveApplication.setRequestUsr(userId);
							
							Date sdate = null;
							Date edate = null;
							try {
								if (requestStrdate != null)
									sdate = CommonRule.ymdFormat.parse(requestStrdate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								sdate = null;

							}
							
							try {
								if (requestEnddate != null)
									edate = CommonRule.ymdFormat.parse(requestEnddate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								edate = null;

							}
							
							leaveApplication.setRequestStrdate(sdate);
							leaveApplication.setRequestEnddate(edate);
							leaveApplication.setRequestDate(requestDate);
							leaveApplication.setRequestHour(0);
							leaveApplication.setKind("B");
							leaveApplication.setKindDept("B");			
							leaveApplication.setConformStage("J");
							leaveApplication.setSignR(img1Path);
							leaveApplication.setSignM(img1Path);
							leaveApplication.setSignJ(img1Path);
							
							Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
							
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}else {
							
							leaveApplication.setTitle(title);
							leaveApplication.setRequestReason(requestReason);
							leaveApplication.setRequestDept(deptCode);
							leaveApplication.setRequestUsr(userId);
							
							Date sdate = null;
							Date edate = null;
							try {
								if (requestStrdate != null)
									sdate = CommonRule.ymdFormat.parse(requestStrdate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								sdate = null;

							}
							
							try {
								if (requestEnddate != null)
									edate = CommonRule.ymdFormat.parse(requestEnddate);
							
							} catch (Exception e) {
								log.info("날짜 변환 중 오류", e);
								edate = null;

							}
							
							leaveApplication.setRequestStrdate(sdate);
							leaveApplication.setRequestEnddate(edate);
							leaveApplication.setRequestDate(0);
							leaveApplication.setRequestHour(requestDate);
							leaveApplication.setKind("C");
							leaveApplication.setKindDept("B");				
							leaveApplication.setConformStage("J");
							leaveApplication.setSignR(img1Path);
							leaveApplication.setSignM(img1Path);
							leaveApplication.setSignJ(img1Path);
							
							Long leaveOverworkId = myMenuService.insertLeaveApplicationJ(leaveApplication);
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "C", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외출/반차 신청");
								pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
							
						}

						
					}
					
					
					else {
					
					LeaveApplication leaveApplication = new LeaveApplication();
					
					if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("A");
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							leaveApplication.setKindDept("B");
						
						}else {
							
							leaveApplication.setKindDept("A");
						}
						
						leaveApplication.setConformStage("M");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationM(leaveApplication);
						
						
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}
						
						
						
						
					}
					else if(title.equals("경조휴가")||title.equals("공가")) {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("B");
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							leaveApplication.setKindDept("B");
						
						}else {
							
							leaveApplication.setKindDept("A");
						}
						
						leaveApplication.setConformStage("M");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationM(leaveApplication);
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}
						
						
					}else {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(0);
						leaveApplication.setRequestHour(requestDate);
						leaveApplication.setKind("C");
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							leaveApplication.setKindDept("B");
						
						}else {
							
							leaveApplication.setKindDept("A");
						}
						
						leaveApplication.setConformStage("M");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplicationM(leaveApplication);
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외출/반차 신청");
								pushAlarmService.factorySendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외출/반차 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
						}
					}
						
						
						
					}
					

					
				}else if(myMenuService.isCEORoundrobin(loginDongleService.getLoginId()).equals("Y")) {
					
					
					LeaveApplication leaveApplication = new LeaveApplication();
					
					if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("A");	
						leaveApplication.setKindDept("A");
						leaveApplication.setConformStage("C");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						leaveApplication.setSignC(img1Path);
						
						myMenuService.insertLeaveApplicationC(leaveApplication);
						
						
					}
					else if(title.equals("경조휴가")||title.equals("공가")) {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("B");
						leaveApplication.setKindDept("A");			
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						leaveApplication.setSignC(img1Path);
						
						myMenuService.insertLeaveApplicationC(leaveApplication);
						
						
					}else {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(0);
						leaveApplication.setRequestHour(requestDate);
						leaveApplication.setKind("C");
						leaveApplication.setKindDept("A");				
						leaveApplication.setConformStage("J");
						leaveApplication.setSignR(img1Path);
						leaveApplication.setSignM(img1Path);
						leaveApplication.setSignJ(img1Path);
						leaveApplication.setSignC(img1Path);
						
						myMenuService.insertLeaveApplicationC(leaveApplication);
						
						
						
					}
					
					
				}
				
				
				else {
					
					
					LeaveApplication leaveApplication = new LeaveApplication();
					
					if(title.equals("연차휴가")||title.equals("생리휴가")||title.equals("병가")){
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("A");
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							leaveApplication.setKindDept("B");
						
						}else {
							
							leaveApplication.setKindDept("A");
						}
						
						leaveApplication.setConformStage("R");
						leaveApplication.setSignR(img1Path);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplication(leaveApplication);
						
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 연차 신청");
								pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation", deptCode);
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
							
						}
						
						
						
						
						
						
						
					}
					else if(title.equals("경조휴가")||title.equals("공가")) {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(requestDate);
						leaveApplication.setRequestHour(0);
						leaveApplication.setKind("B");
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							leaveApplication.setKindDept("B");
						
						}else {
							
							leaveApplication.setKindDept("A");
						}
						
						leaveApplication.setConformStage("R");
						leaveApplication.setSignR(img1Path);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplication(leaveApplication);
						
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, title,  Integer.toString(requestDate) + " 일", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 경조/공가 신청");
								pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation", deptCode);
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
							
						}
						
						
					}else {
						
						leaveApplication.setTitle(title);
						leaveApplication.setRequestReason(requestReason);
						leaveApplication.setRequestDept(deptCode);
						leaveApplication.setRequestUsr(userId);
						
						Date sdate = null;
						Date edate = null;
						try {
							if (requestStrdate != null)
								sdate = CommonRule.ymdFormat.parse(requestStrdate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							sdate = null;

						}
						
						try {
							if (requestEnddate != null)
								edate = CommonRule.ymdFormat.parse(requestEnddate);
						
						} catch (Exception e) {
							log.info("날짜 변환 중 오류", e);
							edate = null;

						}
						
						leaveApplication.setRequestStrdate(sdate);
						leaveApplication.setRequestEnddate(edate);
						leaveApplication.setRequestDate(0);
						leaveApplication.setRequestHour(requestDate);
						leaveApplication.setKind("C");
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MC") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("MM")) {
							
							leaveApplication.setKindDept("B");
						
						}else {
							
							leaveApplication.setKindDept("A");
						}
						
						leaveApplication.setConformStage("R");
						leaveApplication.setSignR(img1Path);
						
						Long leaveOverworkId = myMenuService.insertLeaveApplication(leaveApplication);
						
						if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("FM") || (myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("T2")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "D", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외츨/반차 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else if((myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId())).equals("AD")) {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "F", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외츨/반차 신청");
								pushAlarmService.directorSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
						}else {
							
							try {
								
								noticeMessageService.addOverWorkLeaveNotice("C", "M", deptCode, title,  Integer.toString(requestDate) + " 시간", requestStrdate+" ~ "+requestEnddate, leaveOverworkId, leaveApplication.getConformStage(), myMenuService.usrNameForInsNoticeMessage(userId)+" 조퇴/외츨/반차 신청");
								pushAlarmService.managerSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation", deptCode);
							
							}catch(Exception e) {
								
								System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
							}
							
							
							
						}
						
						
						
					}
					
					
				}
				
				
			}
			
			
			

			return "redirect:/mymenu/leave";
		}
	
	//구매품의서 상세 popup
	@RequestMapping("/roundRobin/detailPop/{roundNo}")
	public ModelAndView roundRobin_detailPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		
		String isOwn = null;
		String isManager = null;
		
		int pageNo = 0;
		if(loginDongleService.getLoginId().equals(myMenuService.requestUsrIdRounrobin(roundNo))) {
			
			isOwn = "Y";
		}else {
			
			isOwn = "N";
		}
		
		if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
			isManager = "Y";
		}else {
			isManager = "N";
		}
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinDetail");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("isOwn", isOwn);
		mv.addObject("isManager", isManager);
		mv.addObject("pageNo", pageNo);
	
		return mv;
	}
	
	//휴가원 상세 popup
	@RequestMapping("/leave/detailPop/{id}")
	public ModelAndView roundRobin_detailPop(@PathVariable("id") Long id){
			
		LeaveApplication line = myMenuService.detailLeaveOne(id);

			
		String isOwn = null;
		String isManager = null;
		String isUpdate = null;
		
		
			
		if(loginDongleService.getLoginId().equals(myMenuService.requestUsrIdLeave(id))) {
				
			isOwn = "Y";
		}else {
				
			isOwn = "N";
		}
			
		if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
			isManager = "Y";
		}else {
			isManager = "N";
		}
		
		if(myMenuService.isUpdatePossible(id)) {
			isUpdate = "Y";
		}else {
			isUpdate = "N";
		}
			
			
		ModelAndView mv = new ModelAndView("mymenu/popup/leaveDetail");
		mv.addObject("line", line);
		mv.addObject("isOwn", isOwn);
		mv.addObject("isManager", isManager);
		mv.addObject("isUpdate", isUpdate);
		
		return mv;
	}
	
	
	//연장근무 상세 팝업
	@RequestMapping("/overwork/detailPop/{id}")
	public ModelAndView overwork_detailPop(@PathVariable("id") Long id){
		
		Overwork line = myMenuService.detailoverworkOne(id);
		
		String isOwn = null;
		String isManager = null;
		
		if(loginDongleService.getLoginId().equals(myMenuService.requestUsrIdOverwork(id))) {
			
			isOwn = "Y";
		}else {
			
			isOwn = "N";
		}
		
		if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
			isManager = "Y";
		}else {
			isManager = "N";
		}
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/overworkDetail");
		mv.addObject("line", line);
		mv.addObject("isOwn", isOwn);
		mv.addObject("isManager", isManager);
	
		return mv;
	}
	
	//구매품의서 취소 동작
	@RequestMapping("/roundRobin/delte/do")
	public String roundRobin_delteDo(@RequestParam("deleteReason") String deleteReason,
									 @RequestParam("roundNo") String roundNo){
		
		
		myMenuService.delteRobin(deleteReason, roundNo, loginDongleService.getLoginId());
	
		
		

		return "redirect:/mymenu/roundRobin";
	}
	
	//휴가원 취소 동작
	@RequestMapping("/leave/delte/do")
	public String leave_delteDo(@RequestParam("deleteReason") String deleteReason,
									 @RequestParam("id") Long id){
			
			
		myMenuService.delteLeave(deleteReason, id, loginDongleService.getLoginId());
		
			
			

		return "redirect:/mymenu/leave";
	}
	
	//연장근무 취소 동작
	@RequestMapping("/overwork/delte/do")
	public String overwork_delteDo(@RequestParam("deleteReason") String deleteReason,
									 @RequestParam("id") Long id){
			
			
		myMenuService.delteOverwork(deleteReason, id, loginDongleService.getLoginId());
		
			
			

		return "redirect:/mymenu/overwork";
	}
	
	
	//팀장 결제 page
	@RequestMapping("/roundRobinConform")
	public ModelAndView roundRobinConform(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		String[] DeptCode = myMenuService.managerDeptCode(loginDongleService.getLoginId());
		
		List<RoundRobin> data = null;
		
		if(DeptCode != null) {
			
			data = myMenuService.roundRobinManagerSelect(DeptCode, title, convertedDesignDateBegin, convertedDesignDateEnd);
			
		}

		
		ModelAndView mv = new ModelAndView("mymenu/roundRobinConform");
		mv.addObject("data", data);
		return mv;
	}
	
	//구매품의서 구매부 결제 page
	@RequestMapping("/roundRobinConformP")
	public ModelAndView roundRobinConformP(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		
		
		List<RoundRobin> data = null;
		
	
			
		data = myMenuService.roundRobinPurchaseSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);


		
		ModelAndView mv = new ModelAndView("mymenu/roundRobinConformP");
		mv.addObject("data", data);
		return mv;
	}
	
	//구매부 품의서 상세 팝업
	@RequestMapping("/roundRobin/conformPPop/{roundNo}")
	public ModelAndView roundRobin_conformPPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinConformP");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//구매부 -> 관리부 이관 동작
	@RequestMapping("/roundRobin/moveToM/do")
	public String roundRobin_moveToM(@RequestParam("roundNo") String roundNo){
		
		myMenuService.roundrobinMoveToM(roundNo);

		return "redirect:/mymenu/roundRobinConformP";
	}
	
	//구매부 일반 승인 결제 동작
	@RequestMapping("/roundRobin/conformPPop/do")
	public String roundRobin_conformPPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo,
			 							   @RequestParam("price") int[] price,
			 							   @RequestParam("id") Long[] id){
		
		String stage = myMenuService.getRoundrobinStage(roundNo);
		
		if(stage.equals("R")) {
			
			
			for(int i = 0;i<id.length;i++) {
				
				myMenuService.setPriceForPurchaseConfrom(id[i], price[i]);
				
			}
			
			myMenuService.conformPToAll("/upimg/all.png" ,signPath, roundNo);
			
		}
		else {
			
			for(int i = 0;i<id.length;i++) {
				
				myMenuService.setPriceForPurchaseConfrom(id[i], price[i]);
				
			}
			
			myMenuService.conformPToDo(signPath, roundNo);
			
			
			try {
				
				Long orderId = myMenuService.orderIdFromNoticeMessageFromRound(roundNo);
				
				String fullOrder = myMenuService.orderNoFullNameFromNoticeMessage(orderId);
				
				if(fullOrder.equals("ZZZZZ")) {
					fullOrder = "consumables";
				}
				
				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromRoundRobin(roundNo);
				
				noticeMessage.setConformStage("P");
				noticeMessage.setAlarmKind("D");
				
				noticeMessageService.addRoundRobinNoticeFromNoticeClass(noticeMessage);
				
				pushAlarmService.directorSendService("["+ fullOrder + "]", "PurchaseRoundRobin");
			
			}catch(Exception e) {
				
				System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
			}
		}
	
	
		return "redirect:/mymenu/roundRobinConformP";
	}
	
	//구매부 전결 승인 동작
	@RequestMapping("/roundRobin/conformPPopAll/do")
	public String roundRobin_conformPPopDoAll(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo,
			 							   @RequestParam("price") int[] price,
			 							   @RequestParam("id") Long[] id){
		
		String stage = myMenuService.getRoundrobinStage(roundNo);
		
		if(stage.equals("R")) {
			
			
			for(int i = 0;i<id.length;i++) {
				
				myMenuService.setPriceForPurchaseConfrom(id[i], price[i]);
				
			}
			
			myMenuService.conformPToAllround("/upimg/all.png" ,signPath, roundNo);
			
		}
		else {
			
			for(int i = 0;i<id.length;i++) {
				
				myMenuService.setPriceForPurchaseConfrom(id[i], price[i]);
				
			}
			
			myMenuService.conformPToAllroundStageM("/upimg/all.png" , signPath, roundNo);
		}
	
	
		return "redirect:/mymenu/roundRobinConformP";
	}
	
	//연월차 팀장 결제 페이지
	@RequestMapping("/leaveConform")
	public ModelAndView leaveConform(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		String[] DeptCode = myMenuService.managerDeptCode(loginDongleService.getLoginId());
		
		List<LeaveApplication> data = null;
		
		if(DeptCode != null) {
			
			data = myMenuService.leaveManagerSelect(DeptCode, title, convertedDesignDateBegin, convertedDesignDateEnd);
			
		}

		
		ModelAndView mv = new ModelAndView("mymenu/leaveConform");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//연장근무 팀장 결제 페이지
	@RequestMapping("/overworkConform")
	public ModelAndView overworkConform(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		String[] DeptCode = myMenuService.managerDeptCode(loginDongleService.getLoginId());
		
		List<Overwork> data = null;
		
		if(DeptCode != null) {
			
			data = myMenuService.overworkManagerSelect(DeptCode, title, convertedDesignDateBegin, convertedDesignDateEnd);
			
		}

		
		ModelAndView mv = new ModelAndView("mymenu/overworkConform");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//팀장 결제 popup
	@RequestMapping("/roundRobin/conformMPop/{roundNo}")
	public ModelAndView roundRobin_conformMPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinConformM");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//연월차 팀장 결제 popup
	@RequestMapping("/leave/conformMPop/{id}")
	public ModelAndView leave_conformMPop(@PathVariable("id") Long id){
		
		LeaveApplication line = myMenuService.detailLeaveOne(id);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/leaveConformM");
		mv.addObject("line", line);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//연장근무 팀장 결제 popup
	@RequestMapping("/overwork/conformMPop/{id}")
	public ModelAndView overwork_conformMPop(@PathVariable("id") Long id){
		
		Overwork line = myMenuService.detailoverworkOne(id);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/overworkConformM");
		mv.addObject("line", line);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//팀장 결제 동작
	@RequestMapping("/roundRobin/conformMPop/do")
	public String roundRobin_conformMPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo){
		
		
		myMenuService.conformM(signPath, roundNo);
		
		try {
			
			Long orderId = myMenuService.orderIdFromNoticeMessageFromRound(roundNo);
			
			String fullOrder = myMenuService.orderNoFullNameFromNoticeMessage(orderId);
			
			if(fullOrder.equals("ZZZZZ")) {
				fullOrder = "consumables";
			}
			
			NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromRoundRobin(roundNo);
			
			noticeMessage.setConformStage("M");
			noticeMessage.setAlarmKind("PQ");
			
			noticeMessageService.addRoundRobinNoticeFromNoticeClass(noticeMessage);
			
			pushAlarmService.pqSendService("["+ fullOrder + "]", "PurchaseRoundRobin");
		
		}catch(Exception e) {
			
			System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
		}
	
	
		return "redirect:/mymenu/roundRobinConform";
	}
	
	//연월차 팀장 결제 동작
	@RequestMapping("/leave/conformMPop/do")
	public String leave_conformMPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("id") Long id){
		
		
		myMenuService.leaveConformM(signPath, id);
		
		try {
		
			NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(id);
		
			
			String userId = myMenuService.leaveRequestUsrfromNoticeMessage(id);
			String kindDept = myMenuService.leaveKindDetpfromNoticeMessage(id);
			
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
		
		
		
	
	
		return "redirect:/mymenu/leaveConform";
	}
	
	//연장근무 팀장 결제 동작
	@RequestMapping("/overwork/conformMPop/do")
	public String overwork_conformMPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("id") Long id){
		
		
		myMenuService.overworkConformM(signPath, id);
		
		
		try {
			
			NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(id);
		
			
			String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(id);
			String kindDept = myMenuService.overWorkDetpfromNoticeMessage(id);
			
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
	
	
		return "redirect:/mymenu/overworkConform";
	}
	
	//전결 page
	@RequestMapping("/roundRobinConformAll")
	public ModelAndView roundRobinConformAll(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<RoundRobin> data = null;
		
		String deptCode = TransitionService.serchuserDeptCode(loginDongleService.getLoginId());
		
		
		
		if(deptCode.equals("PQ")) {
	
			data = myMenuService.roundRobinAlltoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
			
		ModelAndView mv = new ModelAndView("mymenu/roundRobinConformAll");
		mv.addObject("data", data);
		return mv;
	}
	
	//전결 popUp
	@RequestMapping("/roundRobin/conformAllPop/{roundNo}")
	public ModelAndView roundRobin_conformAllPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		String signPath = "/upimg/all.png";
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinConformAll");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	
	//전결 동작
	@RequestMapping("/roundRobin/conformAllPop/do")
	public String roundRobin_conformAllPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo){
		
		String stage = myMenuService.getRoundrobinStage(roundNo);
		
		if(stage.equals("R")) {
			
			myMenuService.conformAllToAll(signPath, roundNo);
			
		}
		else if(stage.equals("M")) {
			
			myMenuService.conformAllToM(signPath, roundNo);
			
		}else if(stage.equals("J")) {
			myMenuService.conformCEO(signPath, roundNo);
		}
		
	
	
		return "redirect:/mymenu/roundRobinConformAll";
	}
	
	//재무이사 결제 page
	@RequestMapping("/roundRobinConformD")
	public ModelAndView roundRobinConformD(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<RoundRobin> data = null;
		
		if((myMenuService.directorYN(loginDongleService.getLoginId())).equals("Y")) {
	
			data = myMenuService.roundRobinDirectoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
			
		ModelAndView mv = new ModelAndView("mymenu/roundRobinConformDpage");
		mv.addObject("data", data);
		return mv;
	}
	
	// 이사/공장장 휴가원 결제 page
	@RequestMapping("/leaveConformD")
	public ModelAndView leaveConformD(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<LeaveApplication> data = null;
		
		if((myMenuService.directorYN(loginDongleService.getLoginId())).equals("Y")) {
	
			data = myMenuService.leaveDirectoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
		
		else if((myMenuService.isFactoryRoundrobin(loginDongleService.getLoginId())).equals("Y")) {
			data = myMenuService.leaveFactorySelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
			
		}
		
			
		ModelAndView mv = new ModelAndView("mymenu/leaveConformDpage");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	
	//연장/휴일 근무 이사/공장장 결제 page
	@RequestMapping("/overworkConformD")
	public ModelAndView overworkConformD(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<Overwork> data = null;
		
		if((myMenuService.directorYN(loginDongleService.getLoginId())).equals("Y")) {
	
			data = myMenuService.overworkDirectoSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
		
		else if((myMenuService.isFactoryRoundrobin(loginDongleService.getLoginId())).equals("Y")) {
			data = myMenuService.overworkFactorySelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
			
		}
		
			
		ModelAndView mv = new ModelAndView("mymenu/overworkConformDpage");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//재무이사 결제 pop
	@RequestMapping("/roundRobin/conformDPop/{roundNo}")
	public ModelAndView roundRobin_conformDPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		int pageNo = 0;
		
		
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinConformD");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("signPath", signPath);
		mv.addObject("pageNo", pageNo);

	
		return mv;
	}
	
	//연월차 이사/공장장 결제 pop
	@RequestMapping("/leave/conformDPop/{id}")
	public ModelAndView leave_conformDPop(@PathVariable("id") Long id){
		
		LeaveApplication line = myMenuService.detailLeaveOne(id);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/leaveConformD");
		mv.addObject("line", line);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//연장/휴일근무 이사/공장장 결제 pop
	@RequestMapping("/overwork/conformDPop/{id}")
	public ModelAndView overwork_conformDPop(@PathVariable("id") Long id){
		
		Overwork line = myMenuService.detailoverworkOne(id);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/overworkConformD");
		mv.addObject("line", line);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//재무이사 결제 동작
	@RequestMapping("/roundRobin/conformDPop/do")
	public String roundRobin_conforDMPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo,
			 							   @RequestParam("roundKind") String roundKind){
		
		String stage = myMenuService.getRoundrobinStage(roundNo);
		
		if(stage.equals("R")) {
			
			myMenuService.conformDtoR(signPath, roundNo, roundKind);
			
		}else {
		
			myMenuService.conformD(signPath, roundNo, roundKind);
			
			try {
				
				Long orderId = myMenuService.orderIdFromNoticeMessageFromRound(roundNo);
				
				String fullOrder = myMenuService.orderNoFullNameFromNoticeMessage(orderId);
				
				if(fullOrder.equals("ZZZZZ")) {
					fullOrder = "consumables";
				}
				
				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromRoundRobin(roundNo);
				
				noticeMessage.setConformStage("J");
				noticeMessage.setAlarmKind("C");
				
				noticeMessageService.addRoundRobinNoticeFromNoticeClass(noticeMessage);
				
				pushAlarmService.ceoSendService("["+ fullOrder + "]", "PurchaseRoundRobin");
			
			}catch(Exception e) {
				
				System.out.println("$$$$$$$$$$$날짜 변환 오류 날짜 변환 오류 날짜 변환 오류 날짜 변환 오류");
			}
			
		}
	
		return "redirect:/mymenu/roundRobinConformD";
	}
	
	//재무이사 전결 동작
	@RequestMapping("/roundRobin/conformDPopAll/do")
	public String roundRobin_conforDMPopDoAll(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo,
			 							   @RequestParam("roundKind") String roundKind){
		
		String stage = myMenuService.getRoundrobinStage(roundNo);
		
		if(stage.equals("R")) {
			
			myMenuService.conformDtoR(signPath, roundNo, roundKind);
			
		}else {
		
			myMenuService.conformDAll("/upimg/all.png" ,signPath, roundNo, roundKind);
		}
	
		return "redirect:/mymenu/roundRobinConformD";
	}
	
	//이사/공장장 연월차 결제 동작
	@RequestMapping("/leave/conformDPop/do")
	public String leave_conforDMPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("id") Long id
			 							   ){
		
		String stage = myMenuService.getLeaveStage(id);
		
		if(stage.equals("R")) {
			
			myMenuService.leaveconformDtoR(signPath, id);
			
			
			try {
				
				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(id);
				
				noticeMessage.setAlarmKind("C");
				noticeMessage.setConformStage("J");
				
				noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
				
				String userId = myMenuService.leaveRequestUsrfromNoticeMessage(id);

				
				
				pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
					
			}catch(Exception e) {
				
				System.out.println("!결제 알람 에러");
			}
			
		}else {
		
			myMenuService.leaveconformD(signPath, id);
			
			try {
				
				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromLeave(id);
				
				noticeMessage.setAlarmKind("C");
				noticeMessage.setConformStage("J");
				
				noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
				
				String userId = myMenuService.leaveRequestUsrfromNoticeMessage(id);

				
				
				pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "vacation");
					
			}catch(Exception e) {
				
				System.out.println("!결제 알람 에러");
			}
		}
	
		return "redirect:/mymenu/leaveConformD";
	}
	
	//이사/공장장 연장/휴일 근무 결제 동작
	@RequestMapping("/overwork/conformDPop/do")
	public String overwork_conforDMPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("id") Long id
			 							   ){
		
		String stage = myMenuService.getOverworkStage(id);
		
		if(stage.equals("R")) {
			
			myMenuService.overworkconformDtoR(signPath, id);
			
			try {
				
				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(id);
			
				
				String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(id);
			
				noticeMessage.setConformStage("C");
				noticeMessage.setAlarmKind("J");
					
				noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
					
				pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");

			
			}catch(Exception e) {
				
				System.out.println("!결제 알람 에러");
			}
			
		}else {
		
			myMenuService.overworkconformD(signPath, id);
			
			try {
				
				NoticeMessage noticeMessage = noticeMessageService.selectConformToNoticeFromOverWork(id);
			
				
				String userId = myMenuService.overWorkRequestUsrfromNoticeMessage(id);
			
				noticeMessage.setConformStage("C");
				noticeMessage.setAlarmKind("J");
					
				noticeMessageService.addOverWorkLeaveNoticeFromNoticeClass(noticeMessage);
					
				pushAlarmService.ceoSendService("["+myMenuService.usrENGNameForInsNoticeMessage(userId) + "]", "Overwork");

			
			}catch(Exception e) {
				
				System.out.println("!결제 알람 에러");
			}
		}
	
		return "redirect:/mymenu/overworkConformD";
	}
	
	//CEO 결제 page
	@RequestMapping("/roundRobinConformCEO")
	public ModelAndView roundRobinConformCEO(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<RoundRobin> data = null;
		
		if((myMenuService.ceoYN(loginDongleService.getLoginId())).equals("Y")) {
	
			data = myMenuService.roundRobinCEOSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
			
		ModelAndView mv = new ModelAndView("mymenu/roundRobinConformCEOpage");
		mv.addObject("data", data);
		return mv;
	}
	
	//연월차 대표이사 결제 페이지
	@RequestMapping("/leaveConformCEO")
	public ModelAndView leaveConformCEO(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<LeaveApplication> data = null;
		
		if((myMenuService.ceoYN(loginDongleService.getLoginId())).equals("Y")) {
	
			data = myMenuService.leaveCEOSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
			
		ModelAndView mv = new ModelAndView("mymenu/leaveConformCEOpage");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	
	//연장/휴일근무 대표이사 결제 page 
	@RequestMapping("/overworkConformCEO")
	public ModelAndView overworkConformCEO(String title, String DateBegin, String DateEnd){
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		List<Overwork> data = null;
		
		if((myMenuService.ceoYN(loginDongleService.getLoginId())).equals("Y")) {
	
			data = myMenuService.overworkCEOSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		}
			
		ModelAndView mv = new ModelAndView("mymenu/overworkConformCEOpage");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//대표이사 결제 pop
	@RequestMapping("/roundRobin/conformCEOPop/{roundNo}")
	public ModelAndView roundRobin_conforCEOPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		int pageNo = 0;
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/roundRobinConformCEO");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("signPath", signPath);
		mv.addObject("pageNo", pageNo);
	
		return mv;
	}
	
	//연월차 대표이사 결제 pop
	@RequestMapping("/leave/conformCEOPop/{id}")
	public ModelAndView leave_conformCEOPop(@PathVariable("id") Long id){
		
		LeaveApplication line = myMenuService.detailLeaveOne(id);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/leaveConformCEO");
		mv.addObject("line", line);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//연장/휴일 근무 대표이사 결제 pop
	@RequestMapping("/overwork/conformCEOPop/{id}")
	public ModelAndView overwork_conformCEOPop(@PathVariable("id") Long id){
		
		Overwork line = myMenuService.detailoverworkOne(id);
		String signPath = userService.UserSignPath(loginDongleService.getLoginId());
		
		
		ModelAndView mv = new ModelAndView("mymenu/popup/overworkConformCEO");
		mv.addObject("line", line);
		mv.addObject("signPath", signPath);
	
		return mv;
	}
	
	//대표이사 결제 동작
	@RequestMapping("/roundRobin/conformCEOPop/do")
	public String roundRobin_conformCEOPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("roundNo") String roundNo){
		
		String stage = myMenuService.getRoundrobinStage(roundNo);
		
		if(stage.equals("R")) {
			
			myMenuService.conformCEOtoR(signPath, roundNo);
			
		}else if(stage.equals("M")){
			myMenuService.conformCEOtoM(signPath, roundNo);
			
		}else if(stage.equals("P")) {
			
			myMenuService.conformCEOFromJ("/upimg/all.png", signPath, roundNo);
		}
		
		else {
			
			myMenuService.conformCEO(signPath, roundNo);
			
			
		}
	
	
		return "redirect:/mymenu/roundRobinConformCEO";
	}
	
	//연월차 대표이사 결제 동작
	@RequestMapping("/leave/conformCEOPop/do")
	public String leave_conformCEOPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("id") Long id){
		
		String stage = myMenuService.getLeaveStage(id);
		
		String requestUsrId = myMenuService.leaveRequestUsrIdSelect(id);
		
		String kind = myMenuService.leaveKindSelect(id);
		
		if(stage.equals("R")) {
			
			if(kind.equals("A")) {
				
				int Date = myMenuService.leaveDateSelect(id);
				
				int hour = Date*8;
				
				int before = myMenuService.usrVacationHourReal(requestUsrId);
				
				int result = before - hour ;
				
				myMenuService.setVacationusrReal(requestUsrId, result);
				
				myMenuService.leaveconformCEOtoR(signPath, id);
				
				
			}else if(kind.equals("C")) {
				
				int hour = myMenuService.leaveHourSelect(id);
				
				int before = myMenuService.usrVacationHourReal(requestUsrId);
				
				int result = before - hour ;
				
				myMenuService.setVacationusrReal(requestUsrId, result);
				
				myMenuService.leaveconformCEOtoR(signPath, id);
				
			}else {
				
				myMenuService.leaveconformCEOtoR(signPath, id);
				
			}
			
			
			
		}else if(stage.equals("M")){
			
			if(kind.equals("A")) {
				
				int Date = myMenuService.leaveDateSelect(id);
				
				int hour = Date*8;
				
				int before = myMenuService.usrVacationHourReal(requestUsrId);
				
				int result = before - hour ;
				
				myMenuService.setVacationusrReal(requestUsrId, result);
				
				myMenuService.leaveconformCEOtoM(signPath, id);
				
				
			}else if(kind.equals("C")) {
				
				int hour = myMenuService.leaveHourSelect(id);
				
				int before = myMenuService.usrVacationHourReal(requestUsrId);
				
				int result = before - hour ;
				
				myMenuService.setVacationusrReal(requestUsrId, result);
				
				myMenuService.leaveconformCEOtoM(signPath, id);
				
			}else {
				
				myMenuService.leaveconformCEOtoM(signPath, id);
			}
			
			
			
		}else {
			
			if(kind.equals("A")) {
				
				int Date = myMenuService.leaveDateSelect(id);
				
				int hour = Date*8;
				
				int before = myMenuService.usrVacationHourReal(requestUsrId);
				
				int result = before - hour ;
				
				myMenuService.setVacationusrReal(requestUsrId, result);
				
				myMenuService.leaveconformCEO(signPath, id);
				
				
			}else if(kind.equals("C")) {
				
				int hour = myMenuService.leaveHourSelect(id);
				
				int before = myMenuService.usrVacationHourReal(requestUsrId);
				
				int result = before - hour ;
				
				myMenuService.setVacationusrReal(requestUsrId, result);
				
				myMenuService.leaveconformCEO(signPath, id);
				
			}else {
				
				myMenuService.leaveconformCEO(signPath, id);
			}
			
			

		}

		return "redirect:/mymenu/leaveConformCEO";
	}
	
	
	//연장/휴일 근무 대표이사 결제 동작
	@RequestMapping("/overwork/conformCEOPop/do")
	public String overwork_conformCEOPopDo(@RequestParam("signPath") String signPath,
			 							   @RequestParam("id") Long id){
		
		String stage = myMenuService.getOverworkStage(id);

		
		if(stage.equals("R")) {
			
			myMenuService.overworkconformCEOtoR(signPath, id);
		
		}else if(stage.equals("M")){
			
			myMenuService.overworkconformCEOtoM(signPath, id);

		}else {
			
		
			myMenuService.overworkconformCEO(signPath, id);

		}

		return "redirect:/mymenu/overworkConformCEO";
	}
	
	
	//취소/반려 품의서 조회
	@RequestMapping("/roundRobinDeleted")
	public ModelAndView roundRobinDeleted(String title, String usrName, String DateBegin, String DateEnd){
		
		String requestId = null;
		
		List<RoundRobin> data = null;
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(usrName)) {
			requestId = null;
		}else {
			requestId = myMenuService.usrNameForIdroundrobin(usrName);
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		String loginUserId = loginDongleService.getLoginId();
		
		
		if(myMenuService.isManagerRoundrobin(loginUserId).equals("Y")) {
			
			if(myMenuService.isDirectorRoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.roundRobinSelectDeletedAll(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
				
			}else {
				
				if(myMenuService.isPQroundrobin(loginUserId).equals("PQ")) {
					
					data = myMenuService.roundRobinSelectDeletedAll(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
					
				}else {
					
					
					data = myMenuService.roundRobinSelectDeletedManager(myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId()), requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
					
				}
				
				
			}
			
		}
		
		else {
			
			if(myMenuService.isCEORoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.roundRobinSelectDeletedAll(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
			}else {
				
				if(myMenuService.isPQroundrobin(loginUserId).equals("PQ")) {
					
				data = myMenuService.roundRobinSelectDeletedAll(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
					
				}else {
					
					data = myMenuService.roundRobinSelectDeleted(loginDongleService.getLoginId(), title, convertedDesignDateBegin, convertedDesignDateEnd);
				
					
				}
				
			}
			
			
			
		}
		
		
		
	 
		
		ModelAndView mv = new ModelAndView("mymenu/roundRobinDeleted");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("usrName", usrName);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	
	//연월차 취소 반려 품의서 조회
	@RequestMapping("/leaveDeleted")
	public ModelAndView leaveDeleted(String title, String usrName, String DateBegin, String DateEnd){
		
		String requestId = null;
		
		List<LeaveApplication> data = null;
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(usrName)) {
			requestId = null;
		}else {
			requestId = myMenuService.usrNameForIdroundrobin(usrName);
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		String loginUserId = loginDongleService.getLoginId();
		
		
		if(myMenuService.isManagerRoundrobin(loginUserId).equals("Y")) {
			
			if(myMenuService.isDirectorRoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.leaveSelectDirectorDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
				
			}else if(myMenuService.isFactoryRoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.leaveSelectFactoryDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
			}
			
			
			else {

				data = myMenuService.leaveSelectManagerDeleted(myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId()), requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
					
				
				
			}
			
		}
		
		else {
			
			if(myMenuService.isCEORoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.leaveSelectCEODelete(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
			}else {
				
			
					
				data = myMenuService.leaveSelectDeleted(loginDongleService.getLoginId(), title, convertedDesignDateBegin, convertedDesignDateEnd);
				
					

				
			}
			
			
			
		}
		
		
		
	 
		
		ModelAndView mv = new ModelAndView("mymenu/leaveDeleted");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("usrName", usrName);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//연장/휴일 근무 취소/반려 조회
	@RequestMapping("/overworkDeleted")
	public ModelAndView overworkDeleted(String title, String usrName, String DateBegin, String DateEnd){
		
		String requestId = null;
		
		List<Overwork> data = null;
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(usrName)) {
			requestId = null;
		}else {
			requestId = myMenuService.usrNameForIdroundrobin(usrName);
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		String loginUserId = loginDongleService.getLoginId();
		
		
		if(myMenuService.isManagerRoundrobin(loginUserId).equals("Y")) {
			
			if(myMenuService.isDirectorRoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.overworkSelectDirectorDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
				
			}else if(myMenuService.isFactoryRoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.overworkSelectFactoryDeleted(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
			}
			
			
			else {

				data = myMenuService.overworkSelectManagerDeleted(myMenuService.deptCodeFromRoundrobin(loginDongleService.getLoginId()), requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
					
				
				
			}
			
		}
		
		else {
			
			if(myMenuService.isCEORoundrobin(loginUserId).equals("Y")) {
				
				data = myMenuService.overworkSelectCEODelete(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
				
			}else {
				
			
					
				data = myMenuService.overworkSelectDeleted(loginDongleService.getLoginId(), title, convertedDesignDateBegin, convertedDesignDateEnd);
				
					

				
			}
			
			
			
		}

		
		ModelAndView mv = new ModelAndView("mymenu/overworkDeleted");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("usrName", usrName);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//최종승인 연월차
	@RequestMapping("/leaveComplet")
	public ModelAndView leaveComplet(String title, String usrName, String DateBegin, String DateEnd){
		
		
		String requestId = null;
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		if(StringUtils.isEmpty(usrName)) {
			requestId = null;
		}else {
			requestId = myMenuService.usrNameForIdroundrobin(usrName);
		}
		
		
		
		
		List<LeaveApplication> data = null;
		
		
		
		data = myMenuService.leaveSelectComplet(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		
	
		
		
		ModelAndView mv = new ModelAndView("mymenu/leaveComplet");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("usrName", usrName);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	//최종승인 연장/휴일 근무 조회
	@RequestMapping("/overworkComplet")
	public ModelAndView overworkComplet(String title, String usrName, String DateBegin, String DateEnd){
		
		
		String requestId = null;
		
		//날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (DateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(DateBegin);
			if (DateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(DateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		if(StringUtils.isEmpty(title)) {
			
			title=null;
							
		}
		
		if(StringUtils.isEmpty(usrName)) {
			requestId = null;
		}else {
			requestId = myMenuService.usrNameForIdroundrobin(usrName);
		}
		
		
		
		
		List<Overwork> data = null;
		
		
		
		data = myMenuService.overworkSelectComplet(requestId, title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		
	
		
		
		ModelAndView mv = new ModelAndView("mymenu/overworkComplet");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("usrName", usrName);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
	}
	
	
}
