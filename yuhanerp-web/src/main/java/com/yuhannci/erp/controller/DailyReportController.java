package com.yuhannci.erp.controller;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ArgumentTypePreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.JobConceptRegForm;
import com.yuhannci.erp.model.JobConceptRegForm.RegMode;
import com.yuhannci.erp.model.EquipmentDeliveryForm;
import com.yuhannci.erp.model.JobOrderForm;
import com.yuhannci.erp.model.JobTypeEnum;
import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.ConceptJobOrder;
import com.yuhannci.erp.model.db.JobDelivery;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.service.JobDeliveryService;
import com.yuhannci.erp.service.JobOrderChangeHistoryService;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MessageService;
import com.yuhannci.erp.service.NoticeMessageService;
import com.yuhannci.erp.service.OrderNoService;
import com.yuhannci.erp.service.PushAlarmService;
import com.yuhannci.erp.service.RegisteredFileService;
import com.yuhannci.erp.service.UserService;
import com.yuhannci.erp.service.RegisteredFileService.FileCategory;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DailyReportController {

	@Autowired MessageService messageService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired RegisteredFileService registeredFileService;
	@Autowired JobOrderService jobOrderService;
	@Autowired JobDeliveryService jobDeliveryService;
	@Autowired UserService userService;
	@Autowired OrderNoService orderNoService;
	@Autowired JobOrderChangeHistoryService jobOrderChangeHistoryService;
	@Autowired NoticeMessageService noticeMessageService;
	@Autowired PushAlarmService pushAlarmService;
	
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public enum ConceptRequestedAction{
		edit,
		delete,
		finish,
		A,
		B,
		C,
		D,
		E,
		F
	}
	
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	// 영업일보 - 리스트
	@RequestMapping("/daily_report_list")
	public ModelAndView list(String orderNoBase, String orderNoExtra, String keyword){
		
		String adjustedKeyword = !StringUtils.isEmpty(keyword) ? keyword.trim() : null;
		String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
		String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
		log.info("keyword = [" + adjustedKeyword + "], orderNoBase = [" + adjustedorderNoBase + "], orderNoExtra = " + adjustedorderNoExtra);
		
		ModelAndView mv = new ModelAndView("daily_report/list");
		// mv.addObject("isBusinessUser", userService.isBusinessDeptUser( loginDongleService.getLoginId() ) );
		mv.addObject("orderNoBase", adjustedorderNoBase);
		mv.addObject("orderNoExtra", adjustedorderNoExtra);
		mv.addObject("keyword", adjustedKeyword);
		return mv;
	}
	// 영업일보 - 리스트
	@RequestMapping("/daily_report_print")
	public ModelAndView print(String orderNoBase, String orderNoExtra, String keyword){
		
		String adjustedKeyword = !StringUtils.isEmpty(keyword) ? keyword.trim() : null;
		String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
		String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
		log.info("keyword = [" + adjustedKeyword + "], orderNoBase = [" + adjustedorderNoBase + "], orderNoExtra = " + adjustedorderNoExtra);
		
		ModelAndView mv = new ModelAndView("daily_report/print");
		mv.addObject("isBusinessUser", userService.isBusinessDeptUser( loginDongleService.getLoginId() ) );
		mv.addObject("orderNoBase", adjustedorderNoBase);
		mv.addObject("orderNoExtra", adjustedorderNoExtra);
		mv.addObject("keyword", adjustedKeyword);
		return mv;
	}
	
	// 영업일보 - 등록&수정
	@RequestMapping("/daily_report_write")
	public ModelAndView write(){
		ModelAndView mv = new ModelAndView("daily_report/write");
		mv.addObject("isBusinessUser", userService.isBusinessDeptUser( loginDongleService.getLoginId() ) );
		return mv;				
	}
	
	// 영업일보 - 지난 영업일보
	@RequestMapping("/daily_report_archive")
	public ModelAndView archive(String keyword, String orderNoBase, String orderNoExtra, String orderDateFrom, String orderDateTo, String deliveryDateFrom, String deliveryDateTo){
		String adjustedKeyword = !StringUtils.isEmpty(keyword) ? keyword.trim() : null;
		String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
		String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
		
		String adjustedOrderDateFrom = !StringUtils.isEmpty(orderDateFrom) ? orderDateFrom.trim() : null;
		String adjustedOrderDateTo = !StringUtils.isEmpty(orderDateTo) ? orderDateTo.trim() : null;
		String adjustedDeliveryDateFrom = !StringUtils.isEmpty(deliveryDateFrom) ? deliveryDateFrom.trim() : null;
		String adjustedDeliveryDateTo = !StringUtils.isEmpty(deliveryDateTo) ? deliveryDateTo.trim() : null;
		
		log.info("keyword = [" + adjustedKeyword + "], orderNoBase = [" + adjustedorderNoBase + "], orderNoExtra = " + adjustedorderNoExtra);
		
		
		ModelAndView mv = new ModelAndView("daily_report/archive");
		mv.addObject("isBusinessUser", userService.isBusinessDeptUser( loginDongleService.getLoginId() ) );
		mv.addObject("orderNoBase", adjustedorderNoBase);
		mv.addObject("orderNoExtra", adjustedorderNoExtra);
		mv.addObject("keyword", adjustedKeyword);
		
		mv.addObject("orderDateFrom", adjustedOrderDateFrom);
		mv.addObject("orderDateTo", adjustedOrderDateTo);
		mv.addObject("deliveryDateFrom", adjustedDeliveryDateFrom);
		mv.addObject("deliveryDateTo", adjustedDeliveryDateTo);
		return mv;				
	}
	
	// 영업일보 - 측면 메세지 표시
	@RequestMapping("/daily_report_left_message")
	public ModelAndView leftMessage(){
				
		ModelAndView mv = new ModelAndView("daily_report/left_message");
		mv.addObject("recentMessage", messageService.getRecentMessage(loginDongleService.getLoginId()));
		return mv;		
	}
	
	final static SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
	
	// 설비/치공구 작업 지시 등록 팝업
	// no-parameter --> 새로
	
	@RequestMapping("/daily_report/popup/new_{orderType}")
	public ModelAndView popupEquipmentProcessJob(@PathVariable("orderType") String orderType, Long fromConcept, Long fromJob){
		
		ModelAndView mv = new ModelAndView("daily_report/write_sub/popup_new_jobjig");
		
		final boolean isJob = orderType.equalsIgnoreCase("JOB");
		
		JobOrder data = null;
		if(fromConcept != null){			
			ConceptJobOrder concept = jobOrderService.getSpecificConcept(fromConcept, isJob ? OrderTypeEnum.JOB : OrderTypeEnum.JIG, null);
			data = new JobOrder();
			data.setOrderType(isJob ? "JOB" : "JIG");
			data.setOrderNoBase(concept.getOrderNoBase());
			data.setOrderNoExtra(concept.getOrderNoExtra());
			data.setCustomerId(concept.getCustomerId());			
			data.setDevice(concept.getDevice());
			data.setBusinessUserId(concept.getBusinessUserId());
			data.setDesignUserId(concept.getDesignUserId());
			data.setCustomerUser(concept.getCustomerUser());
			data.setNote(concept.getNote());
			data.setQuantity(concept.getQuantity());
			data.setInternalUnitPrice(concept.getInternalUnitPrice());
			data.setNegotiatedPrice(concept.getNegotiatedPrice());
			data.setEstimatedPrice( concept.getEstimatedPrice());
			
			log.debug("컨셉 데이터 = " + concept);
			log.debug("설비 데이터 = "+ data);
		}else if(fromJob != null){
			data = jobOrderService.searchJob(fromJob);
		}
		if(data== null)
			data = new JobOrder();

		mv.addObject("jobType", isJob ? "JOB" : "JIG");
		mv.addObject("jobTypeDesc" , isJob ? "설비" : "치공구");
		mv.addObject("fromConcept", fromConcept);
		mv.addObject("data", data);
		mv.addObject("today", ymdSDF.format(new Date()));
		return mv;				
	}
	
	//지그 재제작 팝업
	
	@RequestMapping("/daily_report/popupNew/new_{orderType}")
	public ModelAndView popupEquipmentProcessJobNew(@PathVariable("orderType") String orderType, Long fromConcept, Long fromJob){
		
		ModelAndView mv = new ModelAndView("daily_report/write_sub/popup_new_jobjigRe");
		
		final boolean isJob = orderType.equalsIgnoreCase("JOB");
		
		JobOrder data = null;
		if(fromConcept != null){			
			ConceptJobOrder concept = jobOrderService.getSpecificConcept(fromConcept, isJob ? OrderTypeEnum.JOB : OrderTypeEnum.JIG, null);
			data = new JobOrder();
			data.setOrderType(isJob ? "JOB" : "JIG");
			data.setOrderNoBase(concept.getOrderNoBase());
			data.setOrderNoExtra(concept.getOrderNoExtra());
			data.setCustomerId(concept.getCustomerId());			
			data.setDevice(concept.getDevice());
			data.setBusinessUserId(concept.getBusinessUserId());
			data.setDesignUserId(concept.getDesignUserId());
			data.setCustomerUser(concept.getCustomerUser());
			data.setNote(concept.getNote());
			data.setQuantity(concept.getQuantity());
			data.setInternalUnitPrice(concept.getInternalUnitPrice());
			data.setNegotiatedPrice(concept.getNegotiatedPrice());
			data.setEstimatedPrice( concept.getEstimatedPrice());
			
			log.debug("컨셉 데이터 = " + concept);
			log.debug("설비 데이터 = "+ data);
		}else if(fromJob != null){
			data = jobOrderService.searchJob(fromJob);
		}
		if(data== null)
			data = new JobOrder();

		mv.addObject("jobType", isJob ? "JOB" : "JIG");
		mv.addObject("jobTypeDesc" , isJob ? "설비" : "치공구");
		mv.addObject("fromConcept", fromConcept);
		mv.addObject("fromJob", fromJob);
		mv.addObject("data", data);
		mv.addObject("today", ymdSDF.format(new Date()));
		return mv;				
	}
	
	@RequestMapping("/daily_report/register_job")
	public @ResponseBody StandardResponse registerJob(@Valid JobOrder form, String fromConcept, BindingResult result){
		
		StandardResponse response = new StandardResponse();
		try{
			Long jobOrderId = 0L;
			
			if(StringUtils.isEmpty(form.getOrderNoBase()) )
				throw new IllegalArgumentException("Order-No 첫번째 항목은 필수로 입력해 주세요");
			
			if(StringUtils.isEmpty(form.getDevice()))
				throw new IllegalArgumentException("Device 이름을 입력해 주세요");
			
			if(jobOrderService.checkOrderNoExists(JobTypeEnum.JOB, OrderTypeEnum.parse(form.getOrderType()), form.getOrderNoBase(), form.getOrderNoExtra()))
				throw new IllegalArgumentException("이미 사용중인 Order-No 입니다");
			
			if(StringUtils.isEmpty(form.getCustomerId()))
				throw new IllegalArgumentException("업체를 선택해 주세요");
			
			if( !StringUtils.isEmpty(fromConcept) ){
				Long conceptId = Long.parseLong(fromConcept);
				log.info("참조 Concept-ID = " + conceptId);
				
				jobOrderId = jobOrderService.addJobFromConcept(form, conceptId, loginDongleService.getLoginId());
			}
			else
				jobOrderId = jobOrderService.addJob(form);
							
			response = StandardResponse.createResponse("OK");
			response.setData(jobOrderId);
			
			noticeMessageService.addRegisteredNotice(jobOrderId, "G", "작업지시", "신규 작업지시 등록", form.getDevice(), noticeMessageService.getCustomerNameToNotice(jobOrderId));
			pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(jobOrderId)+"]", "Order Start");
			
			
			
		}catch(IllegalArgumentException e){
			log.error("작업 지시 저장 중 오류. 원 데이터 = " + form, e);			
			response.setResult("ERROR");
			response.setReason(e.getMessage());
			
		}catch(Exception e){
			log.error("작업 지시 저장 중 오류. 원 데이터 = " + form, e);			
			response.setResult("ERROR");
			response.setReason("내부 처리 중 오류가 발생하였습니다");
		}
		
		return response;
	}
	
	//재제작 발송
	@RequestMapping("/daily_report/register_jobNew")
	public @ResponseBody StandardResponse registerJobNew(@Valid JobOrder form, String fromConcept, BindingResult result, Long fromJob){
		
	
		
		StandardResponse response = new StandardResponse();
		try{
			
			form.setId(fromJob);
			
			if(StringUtils.isEmpty(form.getOrderNoBase()) )
				throw new IllegalArgumentException("Order-No 첫번째 항목은 필수로 입력해 주세요");
			
			if(StringUtils.isEmpty(form.getDevice()))
				throw new IllegalArgumentException("Device 이름을 입력해 주세요");
			
			if(StringUtils.isEmpty(form.getCustomerId()))
				throw new IllegalArgumentException("업체를 선택해 주세요");
			
			System.out.println("비지니스 user = "+form.getBusinessUserId());
			System.out.println("고객사  ID = "+form.getCustomerId());
			System.out.println("OrderNo = "+form.getOrderNoBase());
			System.out.println("설계자 ID = "+form.getDesignUserId());
		
			jobOrderService.reUpdateJob(form);
							
			response = StandardResponse.createResponse("OK");
			response.setData(fromJob);
			
			
			
			noticeMessageService.addRegisteredNotice(fromJob, "G", "재제작", "재제작 작업지시 등록", form.getDevice(), noticeMessageService.getCustomerNameToNotice(fromJob));
			
			pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(form.getId())+"]", "Order ReStart");
			
		}catch(IllegalArgumentException e){
			log.error("작업 지시 저장 중 오류. 원 데이터 = " + form, e);			
			response.setResult("ERROR");
			response.setReason(e.getMessage());
			
		}catch(Exception e){
			log.error("작업 지시 저장 중 오류. 원 데이터 = " + form, e);			
			response.setResult("ERROR");
			response.setReason("내부 처리 중 오류가 발생하였습니다");
		}
		
		return response;
		
	}
	
	// 설비 배송 등록 팝업
	@RequestMapping("/daily_report/popup_job_delivery")
	public ModelAndView popupEquipmentDeliverReg(Long jobOrderId){
		
		JobOrder data = null;
		try{
			
			data = jobOrderService.searchJob(jobOrderId);
			if(data ==null)
				throw new Exception("no such job[" + jobOrderId + "]");
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		ModelAndView mv = new ModelAndView("daily_report/write_sub/popup_job_delivery");		
		mv.addObject("data", data);
		return mv;						
	}
	
	@RequestMapping("/daily_report/register_delivery")
	public @ResponseBody StandardResponse registerDelivery(@Valid EquipmentDeliveryForm form
												, BindingResult result
												, MultipartFile image_file1
												, MultipartFile image_file2
												, MultipartFile image_file3
												, MultipartFile image_file4
												, MultipartFile image_file5){
		StandardResponse response = new StandardResponse();
		try{
			if(result.hasErrors())
				throw new Exception("양식을 확인해 주세요" + result.toString());
			
			SimpleEntry<String, String> orderNo = orderNoService.getOrderNoFromJobOrderId(form.getJobOrderId());
			
			JobDelivery data = new JobDelivery();
			data.setJobOrderId(form.getJobOrderId());
			data.setOrderNoBase(orderNo.getKey());
			data.setOrderNoExtra(orderNo.getValue());
			data.setCarrierName(form.getCarrierName());
			data.setCarrierType(form.getCarrierType());
			data.setDestination(form.getDestination());
			data.setShippingFee(form.getShippingFee());

			if(image_file1 != null)
				data.setImageFileNo1( registeredFileService.saveFile(image_file1,FileCategory.IMG, "DELIVERY", loginDongleService.getLoginId()) );
			if(image_file2 != null)
				data.setImageFileNo2( registeredFileService.saveFile(image_file2,FileCategory.IMG, "DELIVERY", loginDongleService.getLoginId()) );
			if(image_file3 != null)
				data.setImageFileNo3( registeredFileService.saveFile(image_file3,FileCategory.IMG, "DELIVERY", loginDongleService.getLoginId()) );
			if(image_file4 != null)
				data.setImageFileNo4( registeredFileService.saveFile(image_file4,FileCategory.IMG, "DELIVERY", loginDongleService.getLoginId()) );
			if(image_file5 != null)
				data.setImageFileNo5( registeredFileService.saveFile(image_file5,FileCategory.IMG, "DELIVERY", loginDongleService.getLoginId()) );
			
			jobDeliveryService.registerDelivery(data, loginDongleService.getLoginId());
			
			noticeMessageService.addRegisteredNotice(form.getJobOrderId(), "G", "제품출하", "제품이 출하되었습니다.", noticeMessageService.getDeviceToNotice(form.getJobOrderId()), noticeMessageService.getCustomerNameToNotice(form.getJobOrderId()));
			pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(form.getJobOrderId())+"]", "Order Shipment");
			response.setResult("OK");
			
		}catch(Exception e){
			response.setResult("ERROR");
			response.setReason(e.getMessage());
		}
		return response;
	}
	
	// 설비 컨셉 등록/수정 팝업
	@RequestMapping("/daily_report/popup/{orderType}_concept")
	public ModelAndView popupEquipmentConceptJob(@PathVariable("orderType") String orderType, Long id) throws Exception {
		ModelAndView mv = new ModelAndView("daily_report/write_sub/popup_new_job_concept");
		
		ConceptJobOrder conceptData = new ConceptJobOrder();	// for dummy
		
		if(id != null){
			conceptData = jobOrderService.getSpecificConcept(id, OrderTypeEnum.parse(orderType), null);
			if(conceptData == null){
				log.error("지정된 id[" + id + "] 를 가진 컨셉을 찾을 수 없음");
				return null;
			}
		}
		
		mv.addObject("orderType", orderType);
		mv.addObject("orderTypeDesc", orderType.equalsIgnoreCase("JOB") ? "설비" : "치공구" );
		mv.addObject("id", id);
		mv.addObject("item",  conceptData );
		mv.addObject("mode", id != null ? "update" : "create");
		mv.addObject("today", ymdSDF.format(conceptData.getOrderDate() != null ? conceptData.getOrderDate() : new Date()));
		return mv;				
	}		
	
	// 설비 컨셉 작업 DB 등록
	@RequestMapping("/daily_report/register_concept")
	public @ResponseBody StandardResponse registerConceptJob(@ModelAttribute @Valid JobConceptRegForm form
																, BindingResult result
																, MultipartHttpServletRequest request
																){
		StandardResponse response = new StandardResponse();
		
		try{
			MultipartFile internalPriceFile = request.getFile("internalPriceFile");
			MultipartFile conceptFile = request.getFile("conceptFile");
			if(form.getMode() == null)
				throw new IllegalArgumentException("Mode 값이 없습니다");
			
			if(form.getMode() == RegMode.create 
						&& jobOrderService.checkOrderNoExists(JobTypeEnum.CONCEPT
																, OrderTypeEnum.parse(form.getOrderType())
																, form.getOrderNoBase()
																, form.getOrderNoExtra()) )
				throw new IllegalArgumentException("이미 사용중인 Order-No 입니다");
			
			if(form.getQuantity() == null)
				throw new IllegalArgumentException("수량을 입력해 주세요");
			
			if(result.hasErrors())
				throw new IllegalArgumentException("양식을 확인해 주세요\n" + result.toString());
						
			// 파일 등록
			Long conceptFileNo = null, internalPriceFileNo = null;		// registeredFile 테이블과 연관이 돼 있기 떄문에 0 이면 안됨 
			if(conceptFile != null)
				conceptFileNo = registeredFileService.saveFile( conceptFile
																, RegisteredFileService.FileCategory.DOC
																, "CONCEPT"
																, loginDongleService.getLoginId() );
			if(internalPriceFile != null)
				internalPriceFileNo = registeredFileService.saveFile( internalPriceFile
																		, RegisteredFileService.FileCategory.DOC
																		, "CONCEPT"
																		, loginDongleService.getLoginId() );
			
			if( StringUtils.isEmpty(form.getOrderNoBase()) ){
				form.setOrderNoBase(null);
				form.setOrderNoExtra(null);
			}
			
			ConceptJobOrder data = new ConceptJobOrder();			
			if(form.getMode() == RegMode.create){
				data.setOrderType(form.getOrderType());
				data.setOrderDate(new Date());
				data.setCustomerId(StringUtils.isEmpty(form.getCustomerId()) ? null : form.getCustomerId());
				data.setOrderNoBase(form.getOrderNoBase());
				data.setOrderNoExtra(form.getOrderNoExtra());
				data.setDevice(form.getDevice());
				data.setQuantity(form.getQuantity());
				data.setBusinessUserId(form.getBusinessUserId());
				data.setDesignUserId(form.getDesignUserId());
				data.setCustomerUser(form.getCustomerUser());
				data.setNote(form.getNote());
				data.setInternalUnitPrice(form.getInternalUnitPrice());
				data.setInternalUnitPriceSharedDate(form.getInternalUnitPriceSharedDate());
				data.setEstimatedPrice(form.getEstimatedPrice());
				data.setNegotiatedPrice(form.getNegotiatedPrice());
				data.setInternalPriceFileNo(internalPriceFileNo);
				data.setConceptFileNo(conceptFileNo);
				
				log.debug("컨센 등록 데이터 ==> " + data);
				jobOrderService.addNewConcept(data);
			}else{
				data = jobOrderService.getSpecificConcept(form.getId(), OrderTypeEnum.parse(form.getOrderType()) , null);
				
				// update
				data.setCustomerId(StringUtils.isEmpty(form.getCustomerId()) ? null : form.getCustomerId());
				data.setDevice(form.getDevice());
				data.setQuantity(form.getQuantity());
				data.setBusinessUserId(form.getBusinessUserId());
				data.setDesignUserId(form.getDesignUserId());
				data.setCustomerUser(form.getCustomerUser());
				data.setNote(form.getNote());
				data.setInternalUnitPrice(form.getInternalUnitPrice());
				data.setInternalUnitPriceSharedDate(form.getInternalUnitPriceSharedDate());
				data.setEstimatedPrice(form.getEstimatedPrice());
				data.setNegotiatedPrice(form.getNegotiatedPrice());
				if(conceptFileNo != null)
					data.setConceptFileNo(conceptFileNo);
				if(internalPriceFileNo != null)
					data.setInternalPriceFileNo(internalPriceFileNo);
				data.setLastModifiedUserId(loginDongleService.getLoginId());
				jobOrderService.modifyConcept(data);
				
				// 완료 처리
				jobOrderService.makeConceptFinished(form.getId(), loginDongleService.getLoginId());
			}
			
			response.setResult("OK");
			
		}catch(IllegalArgumentException e){
			response.setResult("ERROR");
			response.setReason(e.getMessage());
			log.error("컨셉 처리 중 파라미터 오류. 파라미터 = " + form, e);			
		}catch(Exception e){
			response.setResult("ERROR");
			response.setReason("처리 중 오류가 발생하였습니다");
			log.error("컨셉 처리 중 오류. 파라미터 = " + form, e);
		}
		
		return response;
	}
	
	// 설비, 치공구 수정
	@RequestMapping("/daily_report/update/job")
	@ResponseBody
	public StandardResponse updateJob( JobOrder requested, String requestedAction ){
		StandardResponse res = null;
		try{
			
			JobOrder order = jobOrderService.searchJob(requested.getId());
			if(order == null)
				throw new IllegalArgumentException("지정된 설비/치공구 건을 찾을 수 없음");
			
			if(requestedAction.equals("edit")){
				// 수정
				jobOrderService.modifyJob(requested, loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(order.getId(), "G", "생산일정", "생산일정이 수정되었습니다.", order.getDevice(), noticeMessageService.getCustomerNameToNotice(order.getId()));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(order.getId())+"]", "Order Config");
			}else if(requestedAction.equals("delete")){
				// 삭제 처리
				jobOrderService.deleteJob(order.getId(), loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(order.getId(), "G", "생산일정", "생산일정이 삭제되었습니다.", order.getDevice(), noticeMessageService.getCustomerNameToNotice(order.getId()));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(order.getId())+"]", "Order Deleted");
			}else if(requestedAction.equals("finish")){
				// 완료 처리
				jobOrderService.makeJobFinished(order.getId(), loginDongleService.getLoginId());
				noticeMessageService.addRegisteredNotice(order.getId(), "G", "생산일정", "생산일정이 완료되었습니다.", order.getDevice(), noticeMessageService.getCustomerNameToNotice(order.getId()));
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(order.getId())+"]", "Order Complet");
			}else{
				throw new IllegalArgumentException("[" + requestedAction + "]는 지원되지 않는 동작입니다");
			}
			
			res = StandardResponse.createResponse("OK");
			
		}catch(IllegalArgumentException e){
			log.error("DB 처리 중 오류 - 파라미터 오류", e);
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			log.error("DB 처리 중 오류", e);
			res = StandardResponse.createResponse("ERROR", "DB 처리 중 오류 발생");
		}
		return res;
	}
	
	// 설비 컨셉, 치공구 컨셉 '수정'
	@RequestMapping("/daily_report/update/concept")
	@ResponseBody
	public StandardResponse updateConcept(String orderNo, Long id, 
											String device, 
											String internalUnitPriceSharedDate, String orderDate,
											Integer quantity, String customerUser, String note,
											String businessUserId, String designUserId,
											Long internalPriceFileNo, Long conceptFileNo,
											ConceptRequestedAction requestedAction){
		StandardResponse res = null;
		try{
			Date parsedInternalUnitPriceSharedDate = null, parsedOrderDate = null;
			
			if(orderNo == null || id == null)
				throw new InvalidParameterException("수정할 대상이 지정되지 않았습니다");
			
			log.debug("업데이트 전 컨셉 검색. ID=" + id + ", orderNo = " + orderNo);
			
			ConceptJobOrder concept = jobOrderService.getSpecificConcept(id);
			if(concept == null){
				log.error("지정된 컨셉 정보를 찾을 수 없음. ID = " + id + ", orderNo = " + orderNo);
				throw new InvalidParameterException("지정된 컨셉 작업을 찾을 수 없습니다.");
			}

			if(!StringUtils.isEmpty(internalUnitPriceSharedDate)) {
				try{
					parsedInternalUnitPriceSharedDate = sdf.parse(internalUnitPriceSharedDate);
				}catch(Exception e){
					throw new InvalidParameterException("단가 공유일 날짜 형식이 잘못되었습니다. [" + internalUnitPriceSharedDate + "]" );
				}
			}
			if(!StringUtils.isEmpty(orderDate)) {
				try{
					parsedOrderDate = sdf.parse(orderDate);
				}catch(Exception e){
					throw new InvalidParameterException("발주일자 형식이 잘못되었습니다. [" + parsedOrderDate + "]" );
				}
			}
			
			concept.setDevice(device);
			concept.setInternalUnitPriceSharedDate(parsedInternalUnitPriceSharedDate);
			concept.setQuantity(quantity);
			concept.setCustomerUser(customerUser);		// 치공구 컨셉 진행&예정에서는 customerUser 가 전달되지 않음
			concept.setNote(note);
			concept.setLastModifiedUserId( loginDongleService.getLoginId() );
			concept.setBusinessUserId(businessUserId);
			concept.setDesignUserId(designUserId);
			concept.setOrderDate(parsedOrderDate);
			if(internalPriceFileNo != null){
				log.debug("컨셉[" + id + "] 의 내부 단가 파일을 [" + internalPriceFileNo + "] 로 변경" );
				concept.setInternalPriceFileNo(internalPriceFileNo);
			}
			if(conceptFileNo != null){
				log.debug("컨셉[" + id + "] 의 컨셉 도면 파일을 [" + conceptFileNo + "] 로 변경" );
				concept.setConceptFileNo(conceptFileNo);
			}
						
			jobOrderService.modifyConcept(concept);
			
			switch(requestedAction){
			case	edit:
				// 수정된걸 그대로 업로드
				break;
				
			case delete:
				jobOrderService.deleteConcept(id, loginDongleService.getLoginId());
				break;
				
			case finish:
				if(concept.getConceptFileNo() == null)
					throw new InvalidParameterException("컨셉도면 파일을 등록해 주세요");
				
				jobOrderService.makeConceptFinished(id, loginDongleService.getLoginId());
				break;
			case A:
				concept.setCurrentStage("A");
				jobOrderService.modifyConcept(concept);
				break;
			case B:
				concept.setCurrentStage("B");
				jobOrderService.modifyConcept(concept);
				break;
			case C:
				concept.setCurrentStage("C");
				jobOrderService.modifyConcept(concept);
				break;
			case D:
				concept.setCurrentStage("D");
				jobOrderService.modifyConcept(concept);
				break;
			case E:
				concept.setCurrentStage("E");
				jobOrderService.modifyConcept(concept);
				break;
			default:
				throw new InvalidParameterException("[" + requestedAction + "] 지원되지 않는 동작입니다");
			}
			
			res = StandardResponse.createResponse("OK");
		}catch(InvalidParameterException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
			log.error("설비 컨셉 수정 중 오류 - " + e.getMessage());
		}catch(Exception e){
			log.error("설비 컨셉 수정 중 오류 ", e);
			res = StandardResponse.createResponse("ERROR", "컨셉 정보 수정 중 오류");
		}
		return res;
	}
	
	// 견적가 수정 팝업
	@RequestMapping("/daily_report/popup/edit_estimation")
	public ModelAndView popupEstimationEdit(Long jobId){
		
		JobOrder job = jobOrderService.searchJob(jobId);
		if(job == null){
			log.info("견적가 수정을 위한 작업 지시 내역을 찾을 수 없음. ID=" + jobId);
			return null;
		}
		
		log.debug("작업[" + jobId + "]의 유형은 " + job.getOrderType() + ", isJig = " + job.isJigJob());
		ModelAndView mv = new ModelAndView(job.isJigJob() ? "daily_report/list_sub/popup_jig_estimation_edit" : "daily_report/list_sub/popup_job_estimation_edit");
		mv.addObject("jobId", jobId);
		mv.addObject("data", job);
		return mv;
	}
	
	// 견적가 수정
	@RequestMapping("/daily_report/update/estimation")
	@ResponseBody
	public StandardResponse updateEstimation(JobOrder order){
		
		StandardResponse res = null;
		try{
			
			if(order.getId() == null)
				throw new IllegalArgumentException("id가 없음");
			
			if(StringUtils.isEmpty(order.getDevice()))
				throw new IllegalArgumentException("device가 없음"); 
			
			if(StringUtils.isEmpty(order.getBusinessUserId()))
				throw new IllegalArgumentException("영업 담당자를 선택해 주세요");
			
			jobOrderService.updateJobEstimation(order, loginDongleService.getLoginId());
			
			res = StandardResponse.createResponse("OK");
			
		}catch(IllegalArgumentException e){
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			res = StandardResponse.createResponse("ERROR", "견적가 수정 중 오류");
			log.error("견적가 수정 중 오류", e);
		}		
		
		return res;
	}	
	
	@RequestMapping("/daily_report/api/modification_list")
	@ResponseBody
	public StandardResponse apiDailyReportModification(Long id){
		
		StandardResponse res = null;
		try{
			
			res = StandardResponse.createResponse("OK");
			res.setData( jobOrderChangeHistoryService.loadChangeHistory(id) );
			
		}catch(Exception e){
			e.printStackTrace();
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}
		return res;
	}
}
