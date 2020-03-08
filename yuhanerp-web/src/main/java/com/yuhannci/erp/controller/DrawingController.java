package com.yuhannci.erp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.util.StringUtil;

//import org.json.JSONArray;
import org.json.simple.JSONArray;


//import org.json.JSONException;
import org.json.*;
import org.json.simple.*;

//import org.json.JSONObject;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuhannci.erp.mapper.DesignDrawingMapper;
import com.yuhannci.erp.model.DesignDrawingNoEntry;
import com.yuhannci.erp.model.DesignProgressFormEntry;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.service.DrawingHistoryService;
import com.yuhannci.erp.service.DrawingService;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.NoticeMessageService;
import com.yuhannci.erp.service.OrderNoService;
import com.yuhannci.erp.service.ProcessService;
import com.yuhannci.erp.service.PushAlarmService;
import com.yuhannci.erp.service.JobOrderStatusService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/drawing")
public class DrawingController {

	@Autowired	LoginDongleService loginDongleService;
	@Autowired	DesignDrawingMapper designDrawingMapper;
	@Autowired	OrderNoService orderNoService;
	@Autowired	DrawingHistoryService drawingRecordService;
	@Autowired	DrawingService drawingService;
	@Autowired 	JobOrderService jobOrderService;
	@Autowired 	NoticeMessageService noticeMessageService;
	@Autowired MenuService menuService;
	@Autowired 	JobOrderStatusService jobOrderStatusService;
	@Autowired PushAlarmService pushAlarmService;
	@Autowired ProcessService processService;

	final static SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 02-01. 도면 출도 리스트
	 * 
	 * @return
	 */
	@RequestMapping("/issue")
	public ModelAndView drawingList(String orderNoBase, String orderNoExtra) {
		log.info("drawingList");
		ModelAndView mv = new ModelAndView("drawing/issue/issue_list");
		mv.addObject("orderNoBase", orderNoBase);
		mv.addObject("orderNoExtra", orderNoExtra);
		System.out.println("******Start: "+orderNoBase);
		System.out.println("******Start EX: "+orderNoExtra);
		return mv;
	}

	
	
	/**
	 * 설비 진행 도면 출도 팝업
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/issue/popup/design")
	public ModelAndView popupEquipmentPorgress(Long jobOrderId) throws Exception {
		if(jobOrderId == null)
			throw new Exception("도면 출도 대상을 지정해 주세요");
		
		JobOrder order = jobOrderService.searchJob(jobOrderId);
		if(order == null)
			throw new Exception("no such job order");
		
		log.debug("request job-order-id : " + jobOrderId + " ==> orderNo : " + order.getOrderNo());

		ModelAndView mv = new ModelAndView("/drawing/issue/popup/pop_regi_job");
		mv.addObject("jobOrderId", jobOrderId);
		mv.addObject("jobOrder", order);
		mv.addObject("base",order.getOrderNoBase());
		mv.addObject("extra",order.getOrderNoExtra());
		return mv;
	}
	
	/**
	 * 설비 진행 도면 추가출도 팝업
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/issue/popupNew/design")
	public ModelAndView popupEquipmentPorgressNew(Long jobOrderId) throws Exception {
		if(jobOrderId == null)
			throw new Exception("도면 출도 대상을 지정해 주세요");
		
		JobOrder order = jobOrderService.searchJob(jobOrderId);
		if(order == null)
			throw new Exception("no such job order");
		
		log.debug("request job-order-id : " + jobOrderId + " ==> orderNo : " + order.getOrderNo());

		ModelAndView mv = new ModelAndView("/drawing/issue/popup/pop_regi_jobNew");
		mv.addObject("jobOrderId", jobOrderId);
		mv.addObject("jobOrder", order);
		mv.addObject("base",order.getOrderNoBase());
		mv.addObject("extra",order.getOrderNoExtra());
		return mv;
	}


	/**
	 * 설비 진행 도면 출도 팝업 -> 도면 출도 등록
	 * 
	 * @return
	 */
	@RequestMapping("/issue/register")
	@ResponseBody
	public StandardResponse popupEquipmentPorgress(String excelData, Long jobOrderId, Integer achievementPercent) {
		
		StandardResponse res = null;
		
		try {
			if(achievementPercent == null || achievementPercent < 0 || achievementPercent > 100)
				throw new IllegalArgumentException("도면 출도 진행률을 0~100% 사이로 입력해 주세요");
			
			JobOrder order = jobOrderService.searchJob(jobOrderId);
			if(order == null)
				throw new IllegalArgumentException("지정된 작업 지시 항목을 찾을 수 없습니다");		
			
			log.debug("orderId : " + jobOrderId);// " orderNoExtra : " + orderNoExtra);
			log.debug("excelData : " + excelData);
			
			ObjectMapper om = new ObjectMapper();
			
			// 클라이언트가 보낸 정보를 object 로 변환
			DesignProgressFormEntry[] arr = om.readValue(excelData, DesignProgressFormEntry[].class);

			log.debug("record-count : " + arr.length);

			List<JobDesignDrawing> queued = new LinkedList<>();
			
			for (int i = 0; i < arr.length; i++) {
				
				DesignDrawingNoEntry parsedId = orderNoService.parseDesignDrawingNo(arr[i].getDrawingNo());
				
				if(StringUtils.isEmpty(arr[i].getDrawingNo()) || StringUtils.isEmpty(parsedId.getDrawingNo()))
					throw new IllegalArgumentException("도면 번호를 입력해 주세요");
				
				if(StringUtils.isEmpty(arr[i].getDescription()))
					throw new IllegalArgumentException("줄번호 " + (i+1) +"에 설명을 입력해 주세요");
				
				if(StringUtils.isEmpty(arr[i].getMaterial()))
					throw new IllegalArgumentException("줄번호 " + (i+1) +"에 Material을 입력해 주세요");
				
				if(StringUtils.isEmpty(arr[i].getQuantity()))
					throw new IllegalArgumentException("줄번호 " + (i+1) +"에 수량을 입력해 주세요");
				
				if( !order.getOrderNoBase().equals( parsedId.getOrderNoBase() ) || !order.getOrderNoExtra().equals(parsedId.getOrderNoExtra()) ){
					log.info("현재 처리중인 작업 지시 : " + order.getOrderNoBase() + "+" + order.getOrderNoExtra() + ", 도면 식별자 = " + parsedId);
					throw new IllegalArgumentException("도면번호[" + arr[i].getDrawingNo() + "] 는 타 작업 지시에 들어갈 도면입니다");
				}
				
				if(drawingService.checkExistDesignDrawingNo(parsedId))
					throw new IllegalArgumentException("지정된 도면 번호[" + arr[i].getDrawingNo() + "]가 이미 등록돼 있습니다");
				
				// 2018-04-26 분류, 사유 필드 확인
				if(StringUtils.isEmpty(arr[i].getClassification())){
					log.info("분류 없음. idx = " + i + ", classification = [" + arr[i].getClassification() + "], reason = " + arr[i].getReason());
					throw new IllegalArgumentException( (i+1) + " 행에 분류를 입력해 주세요");
				}
				if(!arr[i].getClassification().equals("신규제작") && ( StringUtils.isEmpty(arr[i].getReason()) || arr[i].getReason().equals("미지정")) ){
					log.info("사유 없음. idx = " + i + ", classification = [" + arr[i].getClassification() + "], reason = " + arr[i].getReason());
					throw new IllegalArgumentException( (i+1) + " 행에 사유를 입력해 주세요");
				}
				if(StringUtils.isEmpty(arr[i].getQc()) || !(arr[i].getQc().equals("N") || arr[i].getQc().equals("Y")) ){
					log.info("전달된 검사 값 변경. idx = " + i + ", original qc = " + arr[i].getQc());
					arr[i].setQc("N");
				}
					
				JobDesignDrawing jdd = new JobDesignDrawing();
				jdd.setOrderId(jobOrderId);
				jdd.setOrderNoBase(parsedId.getOrderNoBase());
				jdd.setOrderNoExtra(parsedId.getOrderNoExtra());
				jdd.setDrawingNo(parsedId.getDrawingNo());
				
				jdd.setOrderType(order.getOrderType());
				jdd.setDescription(arr[i].getDescription());
				jdd.setDimension(arr[i].getDimensions());
				jdd.setMaterial(arr[i].getMaterial());
				jdd.setThermal(arr[i].getHeatTreat());

				// 양식에 소수점 포함 가능성이 있어 int.parse 가 아닌 double.parse 로 먼저 변환 후 int 로 캐스팅				
				String quantity = arr[i].getQuantity();
				jdd.setQuantity( (int) Double.parseDouble( StringUtils.isEmpty( quantity ) ? "0" : quantity ));

				String spare = arr[i].getSpare();
				jdd.setSpare( (int) Double.parseDouble( StringUtils.isEmpty(spare) ? "0" : spare ));
				
				jdd.setPostprocessing(arr[i].getAppliedFinish());
				
				jdd.setWorkStatus("R");
				jdd.setChecking(arr[i].getQc());
				jdd.setNote(arr[i].getNote());
				
				// 2018-04-26 분류, 사유 필드 입력
				jdd.setClassification(arr[i].getClassification());
				jdd.setReason(arr[i].getReason());
				jdd.setChecking(arr[i].getQc());
							
				queued.add(jdd);
				
				
			}
			
			res = StandardResponse.createResponse("OK");
			
			List<Long> drawingIds = drawingService.registerDrawings(queued);
			for(Long drawingId : drawingIds) {
				
				processService.insDesignHistry(drawingId, "A", loginDongleService.getLoginId(), "도면이 출도 되었습니다.");
			}
			jobOrderService.updateJobOrderDesignProgress(order.getId(), achievementPercent, loginDongleService.getLoginId());
			noticeMessageService.addDesignDrawingRegisteredNotice(jobOrderId, order.getDevice(),noticeMessageService.getCustomerNameToNotice(jobOrderId));
			log.info("전달된 도면 처리 완료");
			
		}catch(IllegalArgumentException e){
			log.error("도면 처리 중 오류", e);
			res = StandardResponse.createResponse("ERROR", e.getMessage());
			
		} catch (Exception e) {
			log.error("처리 중 오류", e);
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}

		return res;
	}
	
	/**
	 * 설비 진행 도면 추가 출도 팝업 -> 도면 출도 등록
	 * 
	 * @return
	 */
	@RequestMapping("/issueNew/register")
	@ResponseBody
	public StandardResponse popupEquipmentPorgressNew(String excelData, Long jobOrderId, Integer achievementPercent, Integer achievementDay) {
		
		StandardResponse res = null;
		
		try {
			if(achievementPercent == null || achievementPercent < 0 || achievementPercent > 100)
				throw new IllegalArgumentException("도면 출도 진행률을 0~100% 사이로 입력해 주세요");
			
			JobOrder order = jobOrderService.searchJob(jobOrderId);
			if(order == null)
				throw new IllegalArgumentException("지정된 작업 지시 항목을 찾을 수 없습니다");		
			
			log.debug("orderId : " + jobOrderId);// " orderNoExtra : " + orderNoExtra);
			log.debug("excelData : " + excelData);
			
			ObjectMapper om = new ObjectMapper();
			
			// 클라이언트가 보낸 정보를 object 로 변환
			DesignProgressFormEntry[] arr = om.readValue(excelData, DesignProgressFormEntry[].class);

			log.debug("record-count : " + arr.length);

			List<JobDesignDrawing> queued = new LinkedList<>();
			
			for (int i = 0; i < arr.length; i++) {
				
				DesignDrawingNoEntry parsedId = orderNoService.parseDesignDrawingNo(arr[i].getDrawingNo());
				
				if(StringUtils.isEmpty(arr[i].getDrawingNo()) || StringUtils.isEmpty(parsedId.getDrawingNo()))
					throw new IllegalArgumentException("도면 번호를 입력해 주세요");
				
				if(StringUtils.isEmpty(arr[i].getDescription()))
					throw new IllegalArgumentException("줄번호 " + (i+1) +"에 설명을 입력해 주세요");
				
				if(StringUtils.isEmpty(arr[i].getMaterial()))
					throw new IllegalArgumentException("줄번호 " + (i+1) +"에 Material을 입력해 주세요");
				
				if(StringUtils.isEmpty(arr[i].getQuantity()))
					throw new IllegalArgumentException("줄번호 " + (i+1) +"에 수량을 입력해 주세요");
				
				if( !order.getOrderNoBase().equals( parsedId.getOrderNoBase() ) || !order.getOrderNoExtra().equals(parsedId.getOrderNoExtra()) ){
					log.info("현재 처리중인 작업 지시 : " + order.getOrderNoBase() + "+" + order.getOrderNoExtra() + ", 도면 식별자 = " + parsedId);
					throw new IllegalArgumentException("도면번호[" + arr[i].getDrawingNo() + "] 는 타 작업 지시에 들어갈 도면입니다");
				}
				
				if(drawingService.checkExistDesignDrawingNo(parsedId))
					throw new IllegalArgumentException("지정된 도면 번호[" + arr[i].getDrawingNo() + "]가 이미 등록돼 있습니다");
				
				// 2018-04-26 분류, 사유 필드 확인
				if(StringUtils.isEmpty(arr[i].getClassification())){
					log.info("분류 없음. idx = " + i + ", classification = [" + arr[i].getClassification() + "], reason = " + arr[i].getReason());
					throw new IllegalArgumentException( (i+1) + " 행에 분류를 입력해 주세요");
				}
				if(!arr[i].getClassification().equals("신규제작") && ( StringUtils.isEmpty(arr[i].getReason()) || arr[i].getReason().equals("미지정")) ){
					log.info("사유 없음. idx = " + i + ", classification = [" + arr[i].getClassification() + "], reason = " + arr[i].getReason());
					throw new IllegalArgumentException( (i+1) + " 행에 사유를 입력해 주세요");
				}
				if(StringUtils.isEmpty(arr[i].getQc()) || !(arr[i].getQc().equals("N") || arr[i].getQc().equals("Y")) ){
					log.info("전달된 검사 값 변경. idx = " + i + ", original qc = " + arr[i].getQc());
					arr[i].setQc("N");
				}
					
				JobDesignDrawing jdd = new JobDesignDrawing();
				jdd.setOrderId(jobOrderId);
				jdd.setOrderNoBase(parsedId.getOrderNoBase());
				jdd.setOrderNoExtra(parsedId.getOrderNoExtra());
				jdd.setDrawingNo(parsedId.getDrawingNo());
				
				jdd.setOrderType(order.getOrderType());
				jdd.setDescription(arr[i].getDescription());
				jdd.setDimension(arr[i].getDimensions());
				jdd.setMaterial(arr[i].getMaterial());
				jdd.setThermal(arr[i].getHeatTreat());

				// 양식에 소수점 포함 가능성이 있어 int.parse 가 아닌 double.parse 로 먼저 변환 후 int 로 캐스팅				
				String quantity = arr[i].getQuantity();
				jdd.setQuantity( (int) Double.parseDouble( StringUtils.isEmpty( quantity ) ? "0" : quantity ));

				String spare = arr[i].getSpare();
				jdd.setSpare( (int) Double.parseDouble( StringUtils.isEmpty(spare) ? "0" : spare ));
				
				jdd.setPostprocessing(arr[i].getAppliedFinish());
				
				jdd.setWorkStatus("R");
				jdd.setChecking(arr[i].getQc());
				jdd.setNote(arr[i].getNote());
				
				// 2018-04-26 분류, 사유 필드 입력
				jdd.setClassification(arr[i].getClassification());
				jdd.setReason(arr[i].getReason());
				jdd.setChecking(arr[i].getQc());
							
				queued.add(jdd);
			}
			
			res = StandardResponse.createResponse("OK");
			
			List<Long> drawingIds = drawingService.registerDrawings(queued);
			
			for(Long drawingId : drawingIds) {
				
				processService.insDesignHistry(drawingId, "N", loginDongleService.getLoginId(), "도면이 추가 출도 되었습니다.");
			}
			
			jobOrderService.updateJobOrderDesignProgress(order.getId(), achievementPercent, loginDongleService.getLoginId());
			noticeMessageService.addDesignDrawingRegisteredNotice(jobOrderId, order.getDevice(), noticeMessageService.getCustomerNameToNotice(jobOrderId));
			log.info("전달된 도면 처리 완료");
			
			
			jobOrderStatusService.insertWageRate(jobOrderId, achievementDay, loginDongleService.getLoginId(), null, null, "D");
			
		}catch(IllegalArgumentException e){
			log.error("도면 처리 중 오류", e);
			res = StandardResponse.createResponse("ERROR", e.getMessage());
			
		} catch (Exception e) {
			log.error("처리 중 오류", e);
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}

		return res;
	}

	/**
	 * 도면 이력 리스트
	 * 
	 * @return
	 */
	@RequestMapping("/history")
	public ModelAndView drawingRecordList(String partnerId, String orderNoBase, String orderNoExtra,
									String keyword, @DateTimeFormat(pattern = "yyyy-MM-dd") Date drawingDateFrom, @DateTimeFormat(pattern = "yyyy-MM-dd") Date drawingDateTo) {
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "20", "02", "01");
		ModelAndView mv = new ModelAndView("drawing/history/list");
		
		mv.addObject("partnerId", partnerId);
		mv.addObject("orderNoBase", orderNoBase);
		mv.addObject("orderNoExtra", orderNoExtra);
		mv.addObject("keyword", keyword);
		mv.addObject("drawingDateFrom", drawingDateFrom);
		mv.addObject("drawingDateTo", drawingDateTo);
		mv.addObject("isupdate", isupdate);
		
		log.debug("drawing_record : drawingDateFrom is " + drawingDateFrom);
		
		mv.addObject("data", drawingRecordService.getDrawingRecordList(partnerId, orderNoBase, orderNoExtra, keyword, drawingDateFrom, drawingDateTo));

		//mv.addObject("data", drawingRecordService.getDrawingRecordList2());
		return mv;
	}
	
	@RequestMapping("/history/popup/detail")
	public ModelAndView drawingRecordPopDetailList(Long orderId, String classification, String reason) {
		
		log.debug("pop_drawing_detail : id is " + orderId);
		
		if(StringUtils.isEmpty(classification))
			classification = null;
		if(StringUtils.isEmpty(reason))
			reason = null;
		if(orderId == null || orderId == 0)
			return null;
		
		ModelAndView mv = new ModelAndView("drawing/history/popup/detail");		
		mv.addObject("orderId", orderId);
		mv.addObject("classification", classification);
		mv.addObject("reason", reason);
		mv.addObject("jobData", drawingRecordService.getDrawingRecordPopDetailList(orderId));
		mv.addObject("drawData", drawingService.selectJobDrawingRecord(orderId, classification, reason));

		return mv;
	}
	
	/**
	 * 구매 list 등록
	 * 
	 * @return
	 */
	@RequestMapping("/drawing_buy_registration")
	public ModelAndView buyRegistration() {

		ModelAndView mv = new ModelAndView("drawing/buy/buy_registration");

		return mv;
	}

	/**
	 * 구매 list 조회
	 * 
	 * @return
	 */
	@RequestMapping("/drawing_buy_check")
	public ModelAndView buyCheck() {

		ModelAndView mv = new ModelAndView("drawing/buy/buy_check");

		return mv;
	}

	/**
	 * 구매 거래처 관리
	 * 
	 * @return
	 */
	@RequestMapping("/drawing_buy_management")
	public ModelAndView buyMenagement() {

		ModelAndView mv = new ModelAndView("drawing/buy/buy_management");

		return mv;
	}

	/**
	 * 발주 LIST 등록
	 * 
	 * @return
	 */
	@RequestMapping("/drawing_order_registration")
	public ModelAndView orderRegistration() {

		ModelAndView mv = new ModelAndView("drawing/order/order_registration");

		return mv;
	}

	/**
	 * 발주 list 조회
	 * 
	 * @return
	 */
	@RequestMapping("/drawing_order_check")
	public ModelAndView orderCheck() {

		ModelAndView mv = new ModelAndView("drawing/order/order_check");

		return mv;
	}

	@RequestMapping("/drawing_status")
	public ModelAndView assembleList(String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("drawing/issue/drawing_status");
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "40", "03", "01");

		mv.addObject("jobData", jobOrderService.getJobOrderProgress("JOB", orderNoBase, orderNoExtra));
		mv.addObject("isupdate", isupdate);
		mv.addObject("jigData", jobOrderService.getJobOrderProgress("JIG", orderNoBase, orderNoExtra));
		mv.addObject("orderNoBase", orderNoBase);
		mv.addObject("orderNoExtra", orderNoExtra);
		return mv;
	}
	
	@RequestMapping("/issue/popup/drawing_status")
	public ModelAndView popupAssembleStatus(String callback, Long key){
		
		ModelAndView mv = new ModelAndView("drawing/issue/popup/drawing_status");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}

	@RequestMapping("/popup/drawing_progress")
	public ModelAndView popupProgramProgress(Long key){
		
		ModelAndView mv = new ModelAndView("drawing/issue/popup/drawing_progress");
		mv.addObject("item", jobOrderService.getJobOrderProgressSingle(key));
		return mv;
	}
	
	@RequestMapping("/status_update")
	@ResponseBody
	public StandardResponse designStatus(Long key, String atype, String setuser, String reason){
		StandardResponse res = null;
		try{
			String adjustedAType = !StringUtils.isEmpty(atype) ? atype.trim() : "null";
			String adjustedReason = !StringUtils.isEmpty(reason) ? reason.trim() : "";
			String adjustedUser = !StringUtils.isEmpty(setuser) ? setuser.trim() : "";
			Long adjustedKey = (key==null) ? 0:key;

			log.error("설계  상태수정 :: atype = " + adjustedAType + ",key=" + adjustedKey + ",user=" + adjustedUser);
			if (adjustedKey > 0 && adjustedAType.equals("I"))
			{
				jobOrderStatusService.makeDesginPlay(adjustedKey, adjustedUser, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "G", "도면설계", "설계 작업이 시작되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Design Start");
				
			}
			else if (adjustedKey > 0 && adjustedAType.equals("F"))
			{
				jobOrderStatusService.makeDesginComplete(adjustedKey, loginDongleService.getLoginId());
				//noticeMessageService.addRegisteredNotice(adjustedKey, "G", "도면설계", "도면 출도가 완료되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				//pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Design Complete");
				
				String strDate = jobOrderStatusService.getStartDateOrder(adjustedKey);
				String endDate = jobOrderStatusService.getEndtDateOrder(adjustedKey);
				
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
			        
			        jobOrderStatusService.insertWageRate(adjustedKey, result, loginDongleService.getLoginId(), null, null, "D");
		
				}catch(Exception e) {
					
					 System.out.println("임율계산 XXXXXXXXXXXXXXXXXXXXXXXXXXx ");
				}
				

				
			}
			else if (adjustedKey > 0 && adjustedAType.equals("H"))
			{
				jobOrderStatusService.makeDesginHold(adjustedKey, adjustedAType, adjustedReason, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "E", "도면설계", "설계작업이 중지되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Design Hold");
			}
			else if (adjustedKey > 0 && adjustedAType.equals("S"))
			{
				jobOrderStatusService.makeDesginHold(adjustedKey, adjustedAType, adjustedReason, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(adjustedKey, "E", "도면설계", "설계작업이 중지되었습니다.", noticeMessageService.getDeviceToNotice(adjustedKey), noticeMessageService.getCustomerNameToNotice(adjustedKey));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(adjustedKey)+"]", "Design Hold");
			}
			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("designStatus수정", e);
		}
		
		log.info("designStatus수정 :: 결과 = " + res);
		return res;
	}
	
	@RequestMapping("/design_progset")
	@ResponseBody
	public StandardResponse designProgSet(Long key, Integer progValue){
		StandardResponse res = null;
		try{
			Long adjustedKey = (key==null) ? 0:key;
			Integer adjustedProg = (progValue==null) ? 0:progValue;

			log.error("design_progset :: key=" + adjustedKey + ",ProValue=" + adjustedProg);

			jobOrderStatusService.updateDesginProg(adjustedKey, adjustedProg, loginDongleService.getLoginId());

			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
			log.error("design_progset 수정", e);
		}
		
		log.info("design_progset 수정 :: 결과 = " + res);
		return res;
	}

}
