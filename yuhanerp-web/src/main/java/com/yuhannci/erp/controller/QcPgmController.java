package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.JobSetup;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.JobOrderStatusService;
import com.yuhannci.erp.service.JobSetupService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.QcViewDataService;
import com.yuhannci.erp.service.ProcessService;
import com.yuhannci.erp.service.PushAlarmService;
import com.yuhannci.erp.service.NoticeMessageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/qcPgm")
public class QcPgmController {
	@Autowired QcViewDataService qcViewService;
	@Autowired JobOrderService jobOrderService;
	@Autowired JobOrderStatusService jobOrderStatusService;
	@Autowired JobSetupService jobSetupService;
	@Autowired MenuService menuService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired ProcessService processService;
	@Autowired 	NoticeMessageService noticeMessageService;
	@Autowired PushAlarmService pushAlarmService;

	@RequestMapping("/qclist")
	public ModelAndView qcList(String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo)
	{
		String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
		String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
		String adjusteddrawingNo = !StringUtils.isEmpty(drawingNo) ? drawingNo.trim() : null;
		
		String adjustedMctDateFrom = !StringUtils.isEmpty(mctDateFrom) ? mctDateFrom.trim() : null;
		String adjustedMctDateTo = !StringUtils.isEmpty(mctDateTo) ? mctDateTo.trim() : null;
		
		/*SimpleDateFormat  DateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE,  -7);
		String adjustedWeekday = DateFormatDay.format(cal.getTime());
		String adjustedToday = DateFormatDay.format(new Date());
		if (adjustedMctDateFrom==null)
		{
			adjustedMctDateFrom = adjustedWeekday;
		}
		if (adjustedMctDateTo==null)
		{
			adjustedMctDateTo = adjustedToday;
		}
		*/
		log.info("orderNoBase = " + adjustedorderNoBase + ", orderNoExtra = " + adjustedorderNoExtra);
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "40", "02", "01");
		
		ModelAndView mv = new ModelAndView("qc_pgm/qclist");
		mv.addObject("orderNoBase", adjustedorderNoBase);
		mv.addObject("orderNoExtra", adjustedorderNoExtra);
		mv.addObject("drawingNo", adjusteddrawingNo);

		mv.addObject("mctDateFrom", adjustedMctDateFrom);
		mv.addObject("mctDateTo", adjustedMctDateTo);
		mv.addObject("data", qcViewService.getQcViewList());
		mv.addObject("isupdate", isupdate);
		return mv;
	}
	
	@RequestMapping("/qcFinishList")
	public ModelAndView qcCompleteList(String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo)
	{
		String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
		String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
		String adjusteddrawingNo = !StringUtils.isEmpty(drawingNo) ? drawingNo.trim() : null;
		
		String adjustedMctDateFrom = !StringUtils.isEmpty(mctDateFrom) ? mctDateFrom.trim() : null;
		String adjustedMctDateTo = !StringUtils.isEmpty(mctDateTo) ? mctDateTo.trim() : null;
		
		SimpleDateFormat  DateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE,  -7);
		String adjustedWeekday = DateFormatDay.format(cal.getTime());
		String adjustedToday = DateFormatDay.format(new Date());
		if (adjustedMctDateFrom==null)
		{
			adjustedMctDateFrom = adjustedWeekday;
		}
		if (adjustedMctDateTo==null)
		{
			adjustedMctDateTo = adjustedToday;
		}

		log.info("orderNoBase = " + adjustedorderNoBase + ", orderNoExtra = " + adjustedorderNoExtra);
		
		ModelAndView mv = new ModelAndView("qc_pgm/qc_finish_list");
		mv.addObject("orderNoBase", adjustedorderNoBase);
		mv.addObject("orderNoExtra", adjustedorderNoExtra);
		mv.addObject("drawingNo", adjusteddrawingNo);
		mv.addObject("mctDateFrom", adjustedMctDateFrom);
		mv.addObject("mctDateTo", adjustedMctDateTo);
		return mv;
	}
	@RequestMapping("/qcFinishList/data")
	@ResponseBody
	public DataTableResponse qcCompleteData(Integer draw, Integer start, Integer length, 
			String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo)
	{
		DataTableResponse res = new DataTableResponse();
		
		try {
			
			String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
			String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
			String adjusteddrawingNo = !StringUtils.isEmpty(drawingNo) ? drawingNo.trim() : null;
			
			String adjustedMctDateFrom = !StringUtils.isEmpty(mctDateFrom) ? mctDateFrom.trim() : null;
			String adjustedMctDateTo = !StringUtils.isEmpty(mctDateTo) ? mctDateTo.trim() : null;
			Integer adjustedStart = (start==null) ? 0:start;
			Integer adjustedLength = (length==null) ? 20:length;
			
			SimpleDateFormat  DateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
		    cal.setTime(new Date());
		    cal.add(Calendar.DATE,  -7);
			String adjustedWeekday = DateFormatDay.format(cal.getTime());
			String adjustedToday = DateFormatDay.format(new Date());
			if (adjustedMctDateFrom==null)
			{
				adjustedMctDateFrom = adjustedWeekday;
			}
			if (adjustedMctDateTo==null)
			{
				adjustedMctDateTo = adjustedToday;
			}

			log.info("orderNoBase = " + adjustedorderNoBase + ", orderNoExtra = " + adjustedorderNoExtra);
			res.setDraw(draw);
			res.setRecordsFiltered(0);
			res.setData(qcViewService.getQcFinishList(adjustedStart, adjustedLength, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));

		}catch(RuntimeException e) {
			res.setError(e.getMessage());
			log.error("검사완료 목록 조회 중 오류", e);
		}catch(Exception e) {
			res.setError("검사완료 조회 중 오류가 발생하였습니다");
			log.error("검사완료 목록 조회 중 오류", e); 
		}
		
		return res;
	}

	@RequestMapping("/assembleList")
	public ModelAndView assembleList()
	{
		ModelAndView mv = new ModelAndView("qc_pgm/assemble_list");
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "40", "03", "01");

		mv.addObject("jobData", jobOrderService.getJobOrderProgress("JOB", null, null));
		mv.addObject("isupdate", isupdate);
		mv.addObject("jigData", jobOrderService.getJobOrderProgress("JIG", null, null));
		return mv;
	}

	@RequestMapping("/programList")
	public ModelAndView programList()
	{
		ModelAndView mv = new ModelAndView("qc_pgm/program_list");
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "40", "03", "01");

		mv.addObject("jobData", jobOrderService.getJobOrderProgress("JOB", null, null));
		mv.addObject("isupdate", isupdate);
		return mv;
	}
	
	@RequestMapping("/setupList")
	public ModelAndView setupList()
	{
		ModelAndView mv = new ModelAndView("qc_pgm/setup_list");
		
		//String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "40", "03", "01");

		mv.addObject("items", jobSetupService.getQcViewList());
		mv.addObject("updateId", loginDongleService.getLoginId());
		return mv;
	}

	@RequestMapping("/popup/cancel_reason")
	public ModelAndView popupCancelReason(String callback, String key, String dwkey, String atype){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_cancel_reason");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("dwkey", dwkey);
		mv.addObject("atype", atype);
		return mv;
	}
	@RequestMapping("/popup/qc_complete")
	public ModelAndView popupQcComplete(String callback, String key, String dwkey, String totCnt){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_qc_complete");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("dwkey", dwkey);
		mv.addObject("totCnt", totCnt);
		return mv;
	}
	@RequestMapping("/popup/qc_delive")
	public ModelAndView popupQcDelive(String callback, String key, String dwkey){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_qc_delive");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("dwkey", dwkey);
		return mv;
	}
	
	@RequestMapping("/popup/assemble_status")
	public ModelAndView popupAssembleStatus(String callback, Long key){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_assemble_status");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}
	@RequestMapping("/popup/assemble_finish")
	public ModelAndView popupAssembleFinish(String callback, Long key){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_assemble_finish");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}
	@RequestMapping("/popup/assemble_progress")
	public ModelAndView popupAssembleProgress(Long key){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_assemble_progress");
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}
	@RequestMapping("/popup/process_list")
	public ModelAndView popupProcessList(Long key, Integer searchType){

		log.info("popupProcessList ::key=" + key + ",searchType=" + searchType);
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_process_list");
		mv.addObject("orderId", key);
		mv.addObject("searchType", searchType);

		mv.addObject("items", processService.getAssembleProcessList(key, searchType));
		return mv;
	}
	@RequestMapping("/popup/purchase_list")
	public ModelAndView popupPurchaseList(Long key, Integer searchType){

		log.info("popup purchase_list ::key=" + key + ",searchType=" + searchType);
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_purchase_list");
		mv.addObject("orderId", key);
		mv.addObject("searchType", searchType);

		mv.addObject("items", processService.getAssembleProcessList(key, searchType));
		return mv;
	}
	
	@RequestMapping("/popup/program_status")
	public ModelAndView popupProgramStatus(String callback, Long key){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_program_status");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}
	@RequestMapping("/popup/program_progress")
	public ModelAndView popupProgramProgress(Long key){
		
		ModelAndView mv = new ModelAndView("qc_pgm/popup/popup_program_progress");
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}

	@RequestMapping("/status_update")
	@ResponseBody
	public StandardResponse statusUpdate(String atype, Long key, Long dwkey, String reason){
		StandardResponse res = null;
		try{
			String adjustedAType = !StringUtils.isEmpty(atype) ? atype.trim() : "null";
			String adjustedReason = !StringUtils.isEmpty(reason) ? reason.trim() : "";
			Long adjustedKey = (key==null) ? 0:key;
			Long adjusteddwKey = (key==null) ? 0:dwkey;
			
			log.info("검사진행리스트  상태수정 :: atype = " + adjustedAType + ",key=" + adjustedKey + ",dwkey=" + adjusteddwKey + ",reason=" + adjustedReason);
			if (adjustedKey > 0 && adjustedAType.equals("I"))
			{
				qcViewService.makeQcPlay(adjustedKey);
				qcViewService.makeQcDrawingStatusSet(adjusteddwKey, adjustedAType);
			}
			else if (adjustedKey > 0 && adjustedAType.equals("H"))
			{
				qcViewService.makeQcHold(adjustedKey, adjustedAType, adjustedReason);
				qcViewService.makeQcDrawingStatusSet(adjusteddwKey, adjustedAType);
			}
			else if (adjustedKey > 0 && adjustedAType.equals("S"))
			{
				qcViewService.makeQcHold(adjustedKey, adjustedAType, adjustedReason);
				qcViewService.makeQcDrawingStatusSet(adjusteddwKey, adjustedAType);
			}
			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("검사진행리스트  상태수정", e);
		}
		
		log.info("검사진행리스트  상태수정 :: 결과 = " + res);
		return res;
	}
	@RequestMapping("/qc_complete")
	@ResponseBody
	public StandardResponse qcComplete(Long key, Long dwkey, String resultok, int okcnt, int failcnt){
		StandardResponse res = null;
		try{
			String adjustedResultok = !StringUtils.isEmpty(resultok) ? resultok.trim() : "null";
			Long adjustedKey = (key==null) ? 0:key;
			
			log.error("검사결과 등록 :: result = " + adjustedResultok + ",key=" + adjustedKey + ",dwkey=" + dwkey + ",ok=" + okcnt + ",fail=" + failcnt);
			qcViewService.makeQcComplete(adjustedKey, resultok, okcnt, failcnt);
			qcViewService.makeQcCompleteDrawingSet(dwkey, okcnt, failcnt);

			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("검사결과 등록", e);
		}
		
		log.info("검사결과 등록 :: 결과 = " + res);
		return res;
	}
	
	@RequestMapping("/qc_delivery")
	@ResponseBody
	public StandardResponse qcDelivery(Long key, Long dwkey, String fromid, String toid){
		StandardResponse res = null;
		try{
			Long adjustedKey = (key==null) ? 0:key;
			
			log.error("검사결과 등록 :: key=" + adjustedKey + ",dwkey=" + dwkey + ",fromid=" + fromid + ",toid=" + toid);
			qcViewService.makeQcDelivery(adjustedKey, fromid, toid);
			qcViewService.makeQcDeliveryDrawingSet(dwkey);
			qcViewService.makesetTransitionps(dwkey, fromid, toid);

			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("검사결과 등록", e);
		}
		
		log.info("검사결과 등록 :: 결과 = " + res);
		return res;
	}
	@RequestMapping("/assemble_status")
	@ResponseBody
	public StandardResponse assembleStatus(Long key, String atype, String setuser, String reason){
		StandardResponse res = null;
		try{
			String adjustedAType = !StringUtils.isEmpty(atype) ? atype.trim() : "null";
			String adjustedReason = !StringUtils.isEmpty(reason) ? reason.trim() : "";
			String adjustedUser = !StringUtils.isEmpty(setuser) ? setuser.trim() : "";
			Long adjustedKey = (key==null) ? 0:key;

			log.error("조립  상태수정 :: atype = " + adjustedAType + ",key=" + adjustedKey + ",reason=" + adjustedReason);
			if (adjustedKey > 0 && adjustedAType.equals("I"))
			{
				jobOrderStatusService.makeAssemblePlay(adjustedKey, adjustedUser, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "G", "조립현황", "조립작업이 시작되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Assembly Start");
			}
			else if (adjustedKey > 0 && adjustedAType.equals("F"))
			{
				jobOrderStatusService.makeAssembleComplete(adjustedKey, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "G", "조립현황", "조립이 완료되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Assembly Complete");
				
				String strDate = jobOrderStatusService.getStartDateOrderA(adjustedKey);
				String endDate = jobOrderStatusService.getEndtDateOrderA(adjustedKey);
				
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ====  " + strDate );
				
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& End====  " + endDate );
				
				try {
				
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					
					Date str = format.parse(strDate);
			        Date end = format.parse(endDate);
			      
			        long calDate = end.getTime() - str.getTime(); 
			        
		
			        long calDateDays = calDate / ( 24*60*60*1000); 
			 
			        calDateDays = Math.abs(calDateDays);
			        
			        Long cal = calDateDays;
	
			        int result = cal.intValue() + 1 ;
			        
			        System.out.println("임율계산 ========== "+result);
			        
			        jobOrderStatusService.insertWageRate(adjustedKey, result, null, loginDongleService.getLoginId(), null, "A");
		
				}catch(Exception e) {
					
					 System.out.println("임율계산 XXXXXXXXXXXXXXXXXXXXXXXXXXx ");
				}
			}
			else if (adjustedKey > 0 && adjustedAType.equals("H"))
			{
				jobOrderStatusService.makeAssembleHold(adjustedKey, adjustedAType, adjustedReason, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "E", "조립현황", "조립이 중지되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Assembly Hold");
			}
			else if (adjustedKey > 0 && adjustedAType.equals("S"))
			{
				jobOrderStatusService.makeAssembleHold(adjustedKey, adjustedAType, adjustedReason, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "E", "조립현황", "조립이 중지되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Assembly Hold");
			}
			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("assemble_status수정", e);
		}
		
		log.info("assemble_status수정 :: 결과 = " + res);
		return res;
	}

	@RequestMapping("/assemble_finish")
	@ResponseBody
	public StandardResponse assembleFinsh(Model model, HttpServletRequest request){
		StandardResponse res = null;
		try{
			SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd");
			JobSetup jobsetupIn = new JobSetup();
			log.error("assemble_finish Start");

			jobsetupIn.setOrderId(Long.parseLong(request.getParameter("orderId")));
			jobsetupIn.setExpectBuyoffInDate(dt.parse(request.getParameter("expectBuyoffInDate")));
			
			log.error("assemble_finish :: key = " + request.getParameter("orderId")
			+ "buyIn=" + jobsetupIn.getExpectBuyoffInDate().toString());
			
			jobsetupIn.setExpectBuyoffOutDate(dt.parse(request.getParameter("expectBuyoffOutDate")));
			jobsetupIn.setExpectShippingDate(dt.parse(request.getParameter("expectShippingDate")));
			jobsetupIn.setExpectFinishDate(dt.parse(request.getParameter("expectFinishDate")));
			jobsetupIn.setManagerId(request.getParameter("managerId"));
			jobsetupIn.setAssemble1Id(request.getParameter("assemble1Id"));
			jobsetupIn.setAssemble2Id(request.getParameter("assemble12d"));
			jobsetupIn.setAssemble3Id(request.getParameter("assemble13d"));
			jobsetupIn.setPgm1Id(request.getParameter("pgm1Id"));
			jobsetupIn.setPgm2Id(request.getParameter("pgm2Id"));
			jobsetupIn.setPgm3Id(request.getParameter("pgm3Id"));
			jobsetupIn.setWiring1Id(request.getParameter("wiring1Id"));
			jobsetupIn.setWiring2Id(request.getParameter("wiring2Id"));
			jobsetupIn.setWiring3Id(request.getParameter("wiring3Id"));
			jobsetupIn.setRobot1Id(request.getParameter("robot1Id"));
			jobsetupIn.setRobot2Id(request.getParameter("robot2Id"));
			jobsetupIn.setRobot3Id(request.getParameter("robot3Id"));
			jobsetupIn.setBusiness1Id(request.getParameter("business1Id"));
			jobsetupIn.setBusiness2Id(request.getParameter("business2Id"));
			jobsetupIn.setBusiness3Id(request.getParameter("business3Id"));

			jobSetupService.makeJobSetup(jobsetupIn, loginDongleService.getLoginId());
			noticeMessageService.addRegisteredNotice(jobsetupIn.getOrderId(), "G", "SETUP", "Setup이 시작되었습니다.", noticeMessageService.getDeviceToNotice(jobsetupIn.getOrderId()), noticeMessageService.getCustomerNameToNotice(jobsetupIn.getOrderId()));
			pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(jobsetupIn.getOrderId())+"]", "Setup Start");
			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("assemble_finish", e);
		}
		
		log.info("assemble_finish :: 결과 = " + res);
		return res;
	}
	
	@RequestMapping("/assemble_progset")
	@ResponseBody
	public StandardResponse assembleProgSet(Long key, Integer assambleProg){
		StandardResponse res = null;
		try{
			Long adjustedKey = (key==null) ? 0:key;
			Integer adjustedAssambleProg = (assambleProg==null) ? 0:assambleProg;

			log.error("조립  ProgSet :: key=" + adjustedKey + ",AssamblePro=" + adjustedAssambleProg);

			jobOrderStatusService.updateAssembleProg(adjustedKey, assambleProg, loginDongleService.getLoginId());

			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("assemble_progset 수정", e);
		}
		
		log.info("assemble_progset 수정 :: 결과 = " + res);
		return res;
	}

	@RequestMapping("/program_status")
	@ResponseBody
	public StandardResponse programStatus(Long key, String atype, String setuser, String reason){
		StandardResponse res = null;
		try{
			String adjustedAType = !StringUtils.isEmpty(atype) ? atype.trim() : "null";
			String adjustedReason = !StringUtils.isEmpty(reason) ? reason.trim() : "";
			String adjustedUser = !StringUtils.isEmpty(setuser) ? setuser.trim() : "";
			Long adjustedKey = (key==null) ? 0:key;

			log.error("프로그램  상태수정 :: atype = " + adjustedAType + ",key=" + adjustedKey + ",reason=" + adjustedReason);
			if (adjustedKey > 0 && adjustedAType.equals("I"))
			{
				jobOrderStatusService.makeProgramPlay(adjustedKey, adjustedUser, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "G", "PGM현황", "PGM작업이 시작되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "PGM Start");
			}
			else if (adjustedKey > 0 && adjustedAType.equals("F"))
			{
				jobOrderStatusService.makeProgramComplete(adjustedKey, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "G", "PGM현황", "PGM작업이 완료되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "PGM Complete");
				String strDate = jobOrderStatusService.getStartDateOrderP(adjustedKey);
				String endDate = jobOrderStatusService.getEndtDateOrderP(adjustedKey);
				
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ====  " + strDate );
				
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& End====  " + endDate );
				
				try {
				
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					
					Date str = format.parse(strDate);
			        Date end = format.parse(endDate);
			      
			        long calDate = end.getTime() - str.getTime(); 
			        
		
			        long calDateDays = calDate / ( 24*60*60*1000); 
			 
			        calDateDays = Math.abs(calDateDays);
			        
			        Long cal = calDateDays;
	
			        int result = cal.intValue() + 1 ;
			        
			        System.out.println("임율계산 ========== "+result);
			        
			        jobOrderStatusService.insertWageRate(adjustedKey, result, null, null, loginDongleService.getLoginId(), "P");
		
				}catch(Exception e) {
					
					 System.out.println("임율계산 XXXXXXXXXXXXXXXXXXXXXXXXXXx ");
				}
			}
			else if (adjustedKey > 0 && adjustedAType.equals("H"))
			{
				jobOrderStatusService.makeProgramHold(adjustedKey, adjustedAType, adjustedReason, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "E", "PGM현황", "PGM작업이 중지되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "PGM Hold");
			}
			else if (adjustedKey > 0 && adjustedAType.equals("S"))
			{
				jobOrderStatusService.makeProgramHold(adjustedKey, adjustedAType, adjustedReason, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "E", "PGM현황", "PGM작업이 중지되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "PGM Hold");
			}
			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("program_status 수정", e);
		}
		
		log.info("program_status 수정 :: 결과 = " + res);
		return res;
	}
	
	@RequestMapping("/program_progset")
	@ResponseBody
	public StandardResponse programProgSet(Long key, Integer progValue){
		StandardResponse res = null;
		try{
			Long adjustedKey = (key==null) ? 0:key;
			Integer adjustedProg = (progValue==null) ? 0:progValue;

			log.error("program_progset :: key=" + adjustedKey + ",ProValue=" + adjustedProg);

			jobOrderStatusService.updateProgramProg(adjustedKey, adjustedProg, loginDongleService.getLoginId());

			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("program_progset 수정", e);
		}
		
		log.info("program_progset 수정 :: 결과 = " + res);
		return res;
	}

}
