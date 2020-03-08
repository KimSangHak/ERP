package com.yuhannci.erp.controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuhannci.erp.model.IdNameEntry;
import com.yuhannci.erp.model.KeyValueEntry;
import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.OrderNoService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {

	@Autowired PartnerService partnerService;
	@Autowired UserService userService;
	@Autowired JobOrderService jobOrderService;
	@Autowired OrderNoService orderNoService;
	
	// 협력사 리스트 조회 API
	@RequestMapping("/list/customer")
	public List<IdNameEntry> partnerList(){		
		return partnerService.enumSimpleCustomerList();		
	}
	
	// 영업/설계 담당자 목록 조회 API
	@RequestMapping("/list/user/{type}")
	public List<KeyValueEntry> userList(@PathVariable("type") String userType, HttpServletResponse res ){
	
		if(userType.equalsIgnoreCase("business"))
			return userService.getBusinessDeptUserList();
		else if(userType.equalsIgnoreCase("design"))
			return userService.getDesignDeptUserList();
		
		res.setStatus(400);
		return null;
	}
	
	// 주문번호 뒷자리 조회 API
	
	/**
	 * @param jobType			 컨셉(CONCEPT)인지 작업(JOB)인지
	 * @param partnerId		파트너ID 2자리
	 * @param orderType		작업 유형. JOB=설비, JIG=치공구
	 * @return
	 */
	@RequestMapping("/orderno/next/{jobType}/{partnerId}/{orderType}")
	public StandardResponse nextPartnerSequenceNo(@PathVariable("jobType") String jobType, @PathVariable("partnerId") String partnerId, @PathVariable("orderType") OrderTypeEnum orderType ){
	
		StandardResponse res = new StandardResponse();
		try{
			if(partnerId == null || partnerId.length() < 2){
				throw new Exception("invalid partnerId parameter.");
			}
			
			boolean isForConcept = jobType.equalsIgnoreCase("concept");
			boolean isForJob = jobType.equalsIgnoreCase("job");
			
			if(StringUtils.isEmpty(jobType) || !(isForConcept || isForJob))
				throw new Exception("용도를 지정해 주세요");
			
			res.setValue(partnerService.getNextSequenceNo(isForConcept, orderType, partnerId));
			res.setResult("OK");			 
		}catch(Exception e){
			log.error("PartnerID[" + partnerId + "] 용 순번 요청 중 오류", e);
			res.setResult("ERROR");
			res.setReason(e.getMessage());
			
			e.printStackTrace();
		}
		
		return res;
	}	

	/*
	// 주문번호 존재 조회 API
	@RequestMapping("/orderno/{where}/exists")
	public StandardResponse checkOrderNoExists(@PathVariable("where") String where, String orderNo){
	
		StandardResponse res = new StandardResponse();
		try{
			if(orderNo == null || orderNo.length() < 2){
				throw new Exception("invalid Order-No parameter.");
			}
			
			boolean isForConcept = where.equalsIgnoreCase("concept");
			boolean isForJob = where.equalsIgnoreCase("job");
			
			if(StringUtils.isEmpty(where))
				throw new Exception("용도를 지정해 주세요");
						
			SimpleEntry<String, String> entry = orderNoService.parseOrderNo(orderNo);			
			res.setValue( jobOrderService.checkOrderNoExists(entry) ? "EXISTS" : "NEW" );
			res.setResult("OK");			
		}catch(Exception e){
			res.setResult("ERROR");
			res.setReason(e.getMessage());
		}
		
		return res;
	}	*/
	
	@RequestMapping("/list/buy_partner/{category}")
	public StandardResponse listBuyPartner(@PathVariable("category") String cateogory ){
		
		StandardResponse res = new StandardResponse();
		try{
			
			
			
		}catch(RuntimeException e){
			res.setResult("ERROR");
			res.setReason(e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			res.setResult("ERROR");
			res.setReason("조회 중 오류");
			e.printStackTrace();
		}
		
		return res;
		
	}
}
