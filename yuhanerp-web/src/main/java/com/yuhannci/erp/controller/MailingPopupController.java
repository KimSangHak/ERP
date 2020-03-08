package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.yuhannci.erp.model.EmailTransferEntry;
import com.yuhannci.erp.model.IssueMailText;
import com.yuhannci.erp.model.MailSendIssue;
import com.yuhannci.erp.model.PurchaseRequestMailBody;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.JobPartner;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.db.JobPurchaseHistory;
import com.yuhannci.erp.model.db.JobPurchaseNew;
import com.yuhannci.erp.model.db.JobPurchaseNewList;
import com.yuhannci.erp.model.db.JobPurchaseNew_history;
import com.yuhannci.erp.model.db.Purchase_E_ab;
import com.yuhannci.erp.model.db.Purchase_I_ab;
import com.yuhannci.erp.model.db.SelectJobPurchaseNew;
import com.yuhannci.erp.model.db.UpdateJobPurchaseNew;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MailingService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.PostprocessService;
import com.yuhannci.erp.service.ProcessOutsourceListDataService;
import com.yuhannci.erp.service.PurchaseService;
import com.yuhannci.erp.service.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mail")
@Slf4j
public class MailingPopupController {

	@Autowired JobOrderService jobOrderService;
	@Autowired UserService userService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired MailingService mailingService;
	@Autowired ProcessOutsourceListDataService processOutsourceListDataService;
	@Autowired PartnerService partnerService;
	@Autowired PurchaseService purchaseService;
	@Autowired PostprocessService postprocessService;
		
	// #1 작업 발주 (지시) 메일 발송 ==> 27번 슬라이드참고	
	@RequestMapping("/compose/{type}/{id}")
	public ModelAndView composeJobOrderMail(@PathVariable("type") String type, @PathVariable("id") Long id, String partnerId ){
		
		ModelAndView mv = new ModelAndView("mail/order_mail");
		mv.addObject("id", id);
		mv.addObject("type", type);
		mv.addObject("partnerId", StringUtils.isEmpty(partnerId) ? "" : partnerId);
		
		return mv;
	}
	
	@RequestMapping("/test")
	public ModelAndView mailTestView( ){
		
		ModelAndView mv = new ModelAndView("mail/testview");
		return mv;
	}
	
	// 작업 지시 메일 본문
	@RequestMapping("/internal/order/{id}")
	public ModelAndView internalOrder(@PathVariable("id") Long id){
		ModelAndView mv = new ModelAndView("mail/internal/order");
		mv.addObject("item", jobOrderService.searchJob(id));
		mv.addObject("today", new Date());
		mv.addObject("sender", loginDongleService.getMyInformation());
		return mv;
	}
	

	// #2 외주 발주/견적 요청 메일폼 (slide #78)
	@RequestMapping("/outsourcing/{type}/{id}/{partnerId}")
	public ModelAndView outsourcingEstimateMail(@PathVariable("id") String id, @PathVariable("type") String type, @PathVariable("partnerId") String partnerId){
		
		final boolean isEstimateRequest = "estimates".equals(type);
		final String title = isEstimateRequest ? "견적 의뢰" : "발주 요청";
		//String partnerId = processOutsourceListDataService.getAssociatedPartnerId(!isEstimateRequest, id);
		if(StringUtils.isEmpty(partnerId))
			throw new RuntimeException("협력 업체 정보를 찾을 수 없습니다[1]");
		
		JobPartner partnerInformation = partnerService.getPartner(partnerId);
		if(partnerInformation == null)
			throw new RuntimeException("협력 업체 정보를 찾을 수 없습니다[2]");
		
		log.info(type + "/" + title + "-견적[" + id + "] 의 파트너 정보 ==> [" + partnerInformation + "]");

		ModelAndView mv = new ModelAndView("mail/estimate_order_mail");
		mv.addObject("id", id);				// yyyymmdd-nnn
		mv.addObject("type", type);			// estimate, order
		mv.addObject("title", title);
		mv.addObject("partnerId" ,partnerId);		
		mv.addObject("recipient", partnerInformation);
		return mv;
	}
	
	// 외주 견적 요청 메일 본문
	@RequestMapping("/internal/outsourcing/estimates/{id}")
	public ModelAndView internalOutsourcingEstimate(@PathVariable("id") String id) throws Exception{
		
		ModelAndView mv = new ModelAndView("mail/internal/estimate_order");
		mv.addObject("title", "견적 의뢰");
		mv.addObject("title2", "견적");
		mv.addObject("data", processOutsourceListDataService.getOutsourcingRequestDetailList(false, id));
		mv.addObject("sender", loginDongleService.getMyInformation());
		return mv;
	}
	
	// 외주 발주 요청 메일 본문
	@RequestMapping("/internal/outsourcing/order/{id}")
	public ModelAndView internalOutsourcingOrder(@PathVariable("id") String id) throws Exception{
				
		ModelAndView mv = new ModelAndView("mail/internal/estimate_order");
		mv.addObject("title", "발주 의뢰");
		mv.addObject("title2", "발주");
		mv.addObject("data", processOutsourceListDataService.getOutsourcingRequestDetailList(true, id));
		mv.addObject("sender", loginDongleService.getMyInformation());
		
		return mv;
	}
	
	// #3 후처리 발주 요청 메일폼 
	@RequestMapping("/postprocess/{id}/{partnerId}")
	public ModelAndView postprocessOutsourcingMail(@PathVariable("id") String id, @PathVariable("partnerId") String partnerId){
		
		if(StringUtils.isEmpty(partnerId))
			throw new RuntimeException("협력 업체 정보를 찾을 수 없습니다[1]");
		
		JobPartner partnerInformation = partnerService.getPartner(partnerId);
		if(partnerInformation == null)
			throw new RuntimeException("협력 업체 정보를 찾을 수 없습니다[2]");
		
		ModelAndView mv = new ModelAndView("mail/estimate_order_mail");
		mv.addObject("id", id);				// yyyymmdd-nnn
		mv.addObject("type", "postprocess");
		mv.addObject("title", "후처리 발주 요청");
		mv.addObject("partnerId" ,partnerId);		
		mv.addObject("recipient", partnerInformation);
		return mv;
	}	

	// 후처리 발주 요청 메일 본문
	@RequestMapping("/internal/outsourcing/postprocess/{id}")
	public ModelAndView internalOutsourcingPostprocess(@PathVariable("id") String id, String partnerId) throws Exception{
				
		ModelAndView mv = new ModelAndView("mail/internal/postprocess");
		mv.addObject("title", "후처리 발주 의뢰");
		mv.addObject("title2", "발주");
		mv.addObject("data", postprocessService.getDetailRequestList(partnerId, id));
		mv.addObject("sender", loginDongleService.getMyInformation());
		
		return mv;
	}
	
	@RequestMapping("/find")
	public ModelAndView find(String key, String text){
		ModelAndView mv = new ModelAndView("mail/search_result");

		mv.addObject("data", userService.findAllUser(key, text));		
		return mv;
	}
	
	// #3 구매 견적/발주 요청 메일폼 (slide #58)
	// 견적 요청시 /mail/purchase/estimate/{partnerId}
	// 발주 요청시 /mail/purchase/issue/{partnerId}
	@RequestMapping("/purchase/{type}/{partnerId}")
	public ModelAndView purchaseEstimateIssueMail(@PathVariable("partnerId") String partnerId, @PathVariable("type") String type, String jobOrderId) throws Exception{
		
		
			
			
			final boolean isEstimateRequest = "estimates".equals(type);
			final String title = isEstimateRequest ? "견적 의뢰" : "발주 요청";
			JobPartner partnerInformation = partnerService.getPartner(partnerId);
			log.info("" + title + " 파트너 정보 ==> [" + partnerInformation + "], 대상 작업 ID = [" + jobOrderId + "]");
		
			if(StringUtils.isEmpty(jobOrderId) )
				throw new Exception("작업 지시 번호(Job Order-No) 가 없습니다");
		
			ModelAndView mv = new ModelAndView("mail/purchase_mail");
			mv.addObject("type", type);			// estimate, order
			mv.addObject("partnerId", partnerId);
			mv.addObject("jobOrderId", jobOrderId);
	
		
			mv.addObject("title", title);
			mv.addObject("recipient", partnerInformation);
			return mv;
	
	}
	
	
	public static String S_requestId = "";
	public static String S_jobPurchaseId = "";
	
	
	//구매 발주요청 메일폼
	@RequestMapping("/purchase/{type}")
	public ModelAndView purchaseIssueMail(
			@PathVariable("type") String type,
			@RequestParam("partnerId")String partnerId,
			@RequestParam("rawJobOrderId")String rawJobOrderId,
			@RequestParam("requestId") String[] requestIds,
			@RequestParam("jobPurchaseId") String[] jobPurchaseIds,
			@RequestParam("stockUseQuantity") Integer[] stockUseQuantitys,
			@RequestParam("issuePricell") Integer[] issuePricelles,
			@RequestParam("orderQuantity") Integer[] orderQuantitys,
			@RequestParam("receiveDate")String[] receiveDates,
			@RequestParam("stockId")String[] stockIds) throws Exception{
		
		
		
		for(int i=0;i<requestIds.length;i++) {
			
			String requestId = requestIds[i];
			S_requestId += requestId+",";
			Long jobPurchaseId = Long.parseLong(jobPurchaseIds[i]);
			S_jobPurchaseId += jobPurchaseIds[i] + ",";
			Integer stockUseQuantity = stockUseQuantitys[i];
			Integer issuePricell = issuePricelles[i];
			Integer orderQuantity = orderQuantitys[i];
			Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates[i]);
			
			
			Long stockId;
			
			//stockIds[i].trim();
		
			
			if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
				stockId = null; 
			}
			else {
				stockId = Long.parseLong(stockIds[i].trim());
			}
			
			
				JobPurchaseHistory JobPurchaseHistory_ab = new JobPurchaseHistory();
			
				JobPurchaseHistory_ab = purchaseService.selectHistory(requestId, jobPurchaseId);
			
				JobPurchaseHistory_ab.setStockUseQuantity(stockUseQuantity);
				JobPurchaseHistory_ab.setIssuePrice(issuePricell);
				JobPurchaseHistory_ab.setOrderQuantity(orderQuantity);
				JobPurchaseHistory_ab.setReceiveDate(receiveDate);
				JobPurchaseHistory_ab.setStockId(stockId);
			
				purchaseService.insertHistoryAB(JobPurchaseHistory_ab);
			
			
		}
				
				
		final boolean isEstimateRequest = "issue".equals(type);
		final String title = isEstimateRequest ? "발주 요청" : "견적 의뢰";
		JobPartner partnerInformation = partnerService.getPartner(partnerId);
		log.info("" + title + " 파트너 정보 ==> [" + partnerInformation + "], 대상 작업 ID = [" + partnerId + "]");
				
		System.out.println("여기 까지 들어왔따!!!!!!!!!!!!!!!!!");
		System.out.println("***********"+rawJobOrderId);
		System.out.println("***********"+S_requestId);
		System.out.println("***********"+S_jobPurchaseId);
				
		ModelAndView mv = new ModelAndView("mail/purchase_mail_issue");
		mv.addObject("type", type);			// estimate, order
		mv.addObject("partnerId", partnerId);
		mv.addObject("jobOrderId", rawJobOrderId);
		mv.addObject("requestId", S_requestId);
		mv.addObject("jobPurchaseId", S_jobPurchaseId);
				
		mv.addObject("title", title);
		mv.addObject("recipient", partnerInformation);
				
		return mv;
				
	}
	
	public static String NewS_requestId = "";
	public static String NewS_jobPurchaseId = "";
	public static String NewI_requestId = "";
	public static String NewI_jobPurchaseId = "";
	public static String NewE_requestId = "";
	public static String NewE_jobPurchaseId = "";
	
	//신규 구매 발주요청 메일폼
		@RequestMapping("/purchasenew/{type}")
		public ModelAndView purchaseIssueMailNew(
				@PathVariable("type") String type,
				@RequestParam(value="partnerId", defaultValue="")String partnerId,
/*견적용*/				@RequestParam(value="rawJobOrderId", defaultValue="")String rawJobOrderId,
/*발주원*/				@RequestParam(value="ijobOrderIds", defaultValue="")Long[] ijobOrderIds,
/*발주원*/				@RequestParam(value="estimateRequestId", defaultValue="")String[] estimateRequestId,
				@RequestParam("requestId") String[] requestIds,
				@RequestParam("jobPurchaseId") String[] jobPurchaseIds,
				@RequestParam(value="stockUseQuantity", defaultValue="") Integer[] stockUseQuantitys,
				@RequestParam(value="issuePrice", defaultValue="") Integer[] issuePrices,
				@RequestParam("orderQuantity") Integer[] orderQuantitys,
				@RequestParam(value="receiveDate",  defaultValue="")String[] receiveDates,
/*발주원*/				@RequestParam(value="ireceiveDate",  defaultValue="")String[] ireceiveDates,
				@RequestParam(value="stockId", defaultValue="")String[] stockIds,
				@RequestParam(value="modelNo", defaultValue="")String[] modelNos,
/*견적용*/				@RequestParam(value="maker", defaultValue="")String[] makers,
/*견적용, 발주원*/				@RequestParam(value="selectPartnerName", defaultValue="")String selectPartnerName,
/*견적용*/				@RequestParam(value="EmodelNo", defaultValue="")String[] EmodelNo,
/*견적용*/				@RequestParam(value="material", defaultValue="")String[] material,
/*견적용*/				@RequestParam(value="comment", defaultValue="")String[] comment,
/*견적용*/				@RequestParam(value="description", defaultValue="")String[] description,
						@RequestParam(value="issueType", defaultValue="")String[] issueType) throws Exception{
			
				
			
	
			
			if(type.equals("estimate")) {
				
				for(int i=0;i<jobPurchaseIds.length;i++) {
					
					
					
					String requestId = requestIds[i];
					NewE_requestId += requestId+",";
		
					Long jobPurchaseId = Long.parseLong(jobPurchaseIds[i]);
					NewE_jobPurchaseId += jobPurchaseIds[i] + ",";
					Integer orderQuantity = orderQuantitys[i];
					
					Purchase_E_ab Purchase_E_ab = new Purchase_E_ab();
					
					Purchase_E_ab.setEstimateRequestId("aa");
					
					String[] jobOrderIds = rawJobOrderId.split(",");
					
					String SjobOrderId = null;
					
					for(int z=0;z<jobOrderIds.length;z++) {
						
						SjobOrderId = jobOrderIds[0];
					}
					
					Long jobOrderId = Long.parseLong(SjobOrderId);
					
					Purchase_E_ab.setJobOrderId(jobOrderId);
					Purchase_E_ab.setJobPurchaseId(jobPurchaseId);
					Purchase_E_ab.setPartnerId(purchaseService.NameforIdPartner(selectPartnerName));
					Purchase_E_ab.setDescription(description[i]);
					Purchase_E_ab.setMaker(makers[i]);
					Purchase_E_ab.setModelNo(EmodelNo[i]);
					if(material[i].isEmpty() || material[i].equals("")||material[i].equals(" ")) {
						Purchase_E_ab.setMaterial("-");
					}
					Purchase_E_ab.setMaterial(material[i]);
					Purchase_E_ab.setQuantity(orderQuantity);
					Purchase_E_ab.setRegUser(loginDongleService.getLoginId());
					if(comment[i].isEmpty() || comment[i].equals("")||comment[i].equals(" ")) {
						Purchase_E_ab.setComment("-");
					}
					Purchase_E_ab.setComment(comment[i]);
				
					if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
						Purchase_E_ab.setStockId(null);
			
						
						System.out.println("#####################여기들어오면 EMPTY!!!!!!!!!");
					}
					else {
						String s = stockIds[i].replace("J", "").trim();
						
						Long stockId = Long.parseLong(s);
						
						System.out.println("##################### stockId : "+stockId);
				
						Purchase_E_ab.setStockId(stockId);
						
						//purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
						
					}
					
					
					purchaseService.insertEstimateAB(Purchase_E_ab);
					
				}
				
				final boolean isEstimateRequest = "estimate".equals(type);
				final String title = isEstimateRequest ? "견적 의뢰" : "발주 요청";
				JobPartner partnerInformation = partnerService.getPartner(purchaseService.NameforIdPartner(selectPartnerName));
				String EpartnerID = purchaseService.NameforIdPartner(selectPartnerName);
				log.info("" + title + " 파트너 정보 ==> [" + partnerInformation + "], 대상 작업 ID = [" + EpartnerID + "]");
						
				System.out.println("여기 까지 들어왔따!!!!!!!!!!!!!!!!!");
				System.out.println("***********"+rawJobOrderId);
				System.out.println("***********"+NewE_requestId);
				System.out.println("***********"+NewE_jobPurchaseId);
				
				
						
				ModelAndView mv = new ModelAndView("mail/purchase_mail_estimate");
				mv.addObject("type", type);			// estimate, order
				mv.addObject("partnerId", EpartnerID);
				mv.addObject("jobOrderId", rawJobOrderId);
				mv.addObject("requestId", NewE_requestId);
				mv.addObject("jobPurchaseId", NewE_jobPurchaseId);
						
				mv.addObject("title", title);
				mv.addObject("recipient", partnerInformation);
						
				return mv;
					
					
			}
			
			else {
		
			
				if(rawJobOrderId.isEmpty() || rawJobOrderId.equals("")||rawJobOrderId.equals(" ")) {
					for(int i=0;i<jobPurchaseIds.length;i++) {
						
						String requestId = requestIds[i];
						NewI_requestId += requestId+",";
			
						Long jobPurchaseId = Long.parseLong(jobPurchaseIds[i]);
						NewI_jobPurchaseId += jobPurchaseIds[i] + ",";
						Integer orderQuantity = orderQuantitys[i];
						
						Purchase_I_ab Purchase_I_ab = new Purchase_I_ab();
						
						Purchase_I_ab.setIssueRequestId("aa");
						Purchase_I_ab.setJobOrderId(ijobOrderIds[i]);
						Purchase_I_ab.setJobPurchaseId(jobPurchaseId);
						Purchase_I_ab.setPartnerId(purchaseService.NameforIdPartner(selectPartnerName));
						Purchase_I_ab.setEstimateRequestId(estimateRequestId[i]);
						Purchase_I_ab.setModelNo(modelNos[i]);
						Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(ireceiveDates[i]);
						Purchase_I_ab.setReceiveDate(receiveDate);
						Purchase_I_ab.setRegUser(loginDongleService.getLoginId());
						Purchase_I_ab.setIssuePrice(issuePrices[i]);
						Purchase_I_ab.setQuantity(orderQuantity);
						Purchase_I_ab.setIssueType("B");
						Purchase_I_ab.setMaker(makers[i]);
						
						if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
							Purchase_I_ab.setStockId(null);
							Purchase_I_ab.setAbQuantity(0);
							
							System.out.println("#####################여기들어오면 EMPTY!!!!!!!!!");
						}
						else {
							
							String s = stockIds[i].replace("J", "").trim();
							
							Long stockId = Long.parseLong(s);
							
							System.out.println("##################### stockId : "+stockId);
					
							Purchase_I_ab.setStockId(stockId);
							Purchase_I_ab.setAbQuantity(stockUseQuantitys[i]);
							
							//purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
							
						}
						
						purchaseService.insertIssueAB(Purchase_I_ab);
						
						
					}
					
					
					final boolean isEstimateRequest = "issue".equals(type);
					final String title = isEstimateRequest ? "발주 요청" : "견적 의뢰";
					JobPartner partnerInformation = partnerService.getPartner(purchaseService.NameforIdPartner(selectPartnerName));
					String IpartnerID = purchaseService.NameforIdPartner(selectPartnerName);
					log.info("" + title + " 파트너 정보 ==> [" + partnerInformation + "], 대상 작업 ID = [" + IpartnerID + "]");
						
					
					String orderID = ijobOrderIds[0] + ",";
					ModelAndView mv = new ModelAndView("mail/purchase_mail_issue");
					mv.addObject("type", type);			// estimate, order
					mv.addObject("partnerId", IpartnerID);
					mv.addObject("jobOrderId", orderID);
					mv.addObject("requestId", NewI_requestId);
					mv.addObject("jobPurchaseId", NewI_jobPurchaseId);
					
					
					System.out.println("########################"+type);
					System.out.println("##############################");
					
					System.out.println("########################"+IpartnerID);
					System.out.println("##############################");
					
					System.out.println("########################"+orderID);
					System.out.println("##############################");
					
					System.out.println("########################"+NewI_requestId);
					System.out.println("##############################");
					
					System.out.println("########################"+NewI_jobPurchaseId);
					System.out.println("##############################");
					
						
					mv.addObject("title", title);
					mv.addObject("recipient", partnerInformation);
						
					return mv;
					
					
				}else {
					
					if(selectPartnerName.equals("직접카드구매")||selectPartnerName.equals("간이영수증")){
						

						
						String inDate   = new java.text.SimpleDateFormat("yyMMddhhmmss").format(new java.util.Date());	
						

						
						for(int i=0;i<jobPurchaseIds.length;i++) {
							
							String requestId = requestIds[i];
							NewS_requestId += requestId+",";
				
							Long jobPurchaseId = Long.parseLong(jobPurchaseIds[i]);
							NewS_jobPurchaseId += jobPurchaseIds[i] + ",";
							Integer orderQuantity = orderQuantitys[i];
										
							
							String[] jobOrderIds = rawJobOrderId.split(",");
							
							String SjobOrderId = null;
							
							for(int z=0;z<jobOrderIds.length;z++) {
								
								SjobOrderId = jobOrderIds[0];
							}
							
							Long jobOrderId = Long.parseLong(SjobOrderId);
							
							
							if(issueType[i].equals("C")){
								
								Purchase_I_ab Purchase_I_ab = new Purchase_I_ab();
								
								Purchase_I_ab.setIssueRequestId("I"+"-"+"CCCC1"+"-"+inDate);
								Purchase_I_ab.setJobOrderId(jobOrderId);
								Purchase_I_ab.setJobPurchaseId(jobPurchaseId);
								Purchase_I_ab.setPartnerId("CCCC1");
								Purchase_I_ab.setEstimateRequestId(null);
								Purchase_I_ab.setModelNo(EmodelNo[i]);
								Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates[i]);
								Purchase_I_ab.setReceiveDate(receiveDate);
								Purchase_I_ab.setRegUser(loginDongleService.getLoginId());
								Purchase_I_ab.setIssuePrice(issuePrices[i]);
								Purchase_I_ab.setQuantity(orderQuantity);
								Purchase_I_ab.setIssueType(issueType[i]);
								Purchase_I_ab.setMaker(makers[i]);
								
								purchaseService.UpdateStageP(Purchase_I_ab.getJobPurchaseId(), Purchase_I_ab.getIssueRequestId(), Purchase_I_ab.getRegUser(), Purchase_I_ab.getModelNo(), Purchase_I_ab.getPartnerId(), Purchase_I_ab.getMaker());
			
								purchaseService.insertIssueReal(Purchase_I_ab);
								
								if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
									
								}
								else {
									String s = stockIds[i].replace("J", "").trim();
									
									Long stockId = Long.parseLong(s);
									purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
									
								}
							}else {
								
								Purchase_I_ab Purchase_I_ab = new Purchase_I_ab();
								
								Purchase_I_ab.setIssueRequestId("I"+"-"+"HHHH1"+"-"+inDate);
								Purchase_I_ab.setJobOrderId(jobOrderId);
								Purchase_I_ab.setJobPurchaseId(jobPurchaseId);
								Purchase_I_ab.setPartnerId("HHHH1");
								Purchase_I_ab.setEstimateRequestId(null);
								Purchase_I_ab.setModelNo(EmodelNo[i]);
								Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates[i]);
								Purchase_I_ab.setReceiveDate(receiveDate);
								Purchase_I_ab.setRegUser(loginDongleService.getLoginId());
								Purchase_I_ab.setIssuePrice(issuePrices[i]);
								Purchase_I_ab.setQuantity(orderQuantity);
								Purchase_I_ab.setIssueType(issueType[i]);
								Purchase_I_ab.setMaker(makers[i]);
								
								purchaseService.UpdateStageP(Purchase_I_ab.getJobPurchaseId(), Purchase_I_ab.getIssueRequestId(), Purchase_I_ab.getRegUser(), Purchase_I_ab.getModelNo(), Purchase_I_ab.getPartnerId(), Purchase_I_ab.getMaker());
								
								purchaseService.insertIssueReal(Purchase_I_ab);
								
								if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
									
								}
								else {
									String s = stockIds[i].replace("J", "").trim();
									
									Long stockId = Long.parseLong(s);
									purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
									
								}
								
							}
							
							
						}
						
						ModelAndView mv = new ModelAndView("mail/orderCard_re");
						
						return mv;
						
						
						
					}
					else {
						
						String inDate   = new java.text.SimpleDateFormat("yyMMddhhmmss").format(new java.util.Date());
						
						for(int i=0;i<jobPurchaseIds.length;i++) {
							
							String requestId = requestIds[i];
							if(issueType[i].equals("B")) {
								NewS_requestId += requestId+",";
							}
				
							Long jobPurchaseId = Long.parseLong(jobPurchaseIds[i]);
							if(issueType[i].equals("B")) {
								NewS_jobPurchaseId += jobPurchaseIds[i] + ",";
							}
							Integer orderQuantity = orderQuantitys[i];
							
							Purchase_I_ab Purchase_I_ab = new Purchase_I_ab();
							
							if(issueType[i].equals("B")) {
								Purchase_I_ab.setIssueRequestId("aa");
							}
							
							String[] jobOrderIds = rawJobOrderId.split(",");
							
							String SjobOrderId = null;
							
							for(int z=0;z<jobOrderIds.length;z++) {
								
								SjobOrderId = jobOrderIds[0];
							}
							
							Long jobOrderId = Long.parseLong(SjobOrderId);
							
							
							if(issueType[i].equals("B")){
								
								Purchase_I_ab.setJobOrderId(jobOrderId);
								Purchase_I_ab.setJobPurchaseId(jobPurchaseId);
								Purchase_I_ab.setPartnerId(purchaseService.NameforIdPartner(selectPartnerName));
								Purchase_I_ab.setEstimateRequestId(null);
								Purchase_I_ab.setModelNo(EmodelNo[i]);
								Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates[i]);
								Purchase_I_ab.setReceiveDate(receiveDate);
								Purchase_I_ab.setRegUser(loginDongleService.getLoginId());
								Purchase_I_ab.setIssuePrice(issuePrices[i]);
								Purchase_I_ab.setQuantity(orderQuantity);
								Purchase_I_ab.setIssueType(issueType[i]);
								Purchase_I_ab.setMaker(makers[i]);
							
								
								
								
								if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
									Purchase_I_ab.setStockId(null);
									Purchase_I_ab.setAbQuantity(0);
									
									System.out.println("#####################여기들어오면 EMPTY!!!!!!!!!");
								}
								else {
									String s = stockIds[i].replace("J", "").trim();
									
									Long stockId = Long.parseLong(s);
									
									System.out.println("##################### stockId : "+stockId);
									System.out.println("##################### stockUseQuantitys : "+stockUseQuantitys[i]);
									Purchase_I_ab.setStockId(stockId);
									Purchase_I_ab.setAbQuantity(stockUseQuantitys[i]);
									//purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
									
								}
								
								purchaseService.insertIssueAB(Purchase_I_ab);
								
							}else if(issueType[i].equals("C")){
								
								Purchase_I_ab.setIssueRequestId("I"+"-"+"CCCC1"+"-"+inDate);
								Purchase_I_ab.setJobOrderId(jobOrderId);
								Purchase_I_ab.setJobPurchaseId(jobPurchaseId);
								Purchase_I_ab.setPartnerId("CCCC1");
								Purchase_I_ab.setEstimateRequestId(null);
								Purchase_I_ab.setModelNo(EmodelNo[i]);
								Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates[i]);
								Purchase_I_ab.setReceiveDate(receiveDate);
								Purchase_I_ab.setRegUser(loginDongleService.getLoginId());
								Purchase_I_ab.setIssuePrice(issuePrices[i]);
								Purchase_I_ab.setQuantity(orderQuantity);
								Purchase_I_ab.setIssueType(issueType[i]);
								Purchase_I_ab.setMaker(makers[i]);
								
								purchaseService.UpdateStageP(Purchase_I_ab.getJobPurchaseId(), Purchase_I_ab.getIssueRequestId(), Purchase_I_ab.getRegUser(), Purchase_I_ab.getModelNo(), Purchase_I_ab.getPartnerId(), Purchase_I_ab.getMaker());
								
								purchaseService.insertIssueReal(Purchase_I_ab);
								
								if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
									
								}
								else {
									String s = stockIds[i].replace("J", "").trim();
									
									Long stockId = Long.parseLong(s);
									purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
									
								}
							}else {
								
								Purchase_I_ab.setIssueRequestId("I"+"-"+"HHHH1"+"-"+inDate);
								Purchase_I_ab.setJobOrderId(jobOrderId);
								Purchase_I_ab.setJobPurchaseId(jobPurchaseId);
								Purchase_I_ab.setPartnerId("HHHH1");
								Purchase_I_ab.setEstimateRequestId(null);
								Purchase_I_ab.setModelNo(EmodelNo[i]);
								Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates[i]);
								Purchase_I_ab.setReceiveDate(receiveDate);
								Purchase_I_ab.setRegUser(loginDongleService.getLoginId());
								Purchase_I_ab.setIssuePrice(issuePrices[i]);
								Purchase_I_ab.setQuantity(orderQuantity);
								Purchase_I_ab.setIssueType(issueType[i]);
								Purchase_I_ab.setMaker(makers[i]);
								
								purchaseService.UpdateStageP(Purchase_I_ab.getJobPurchaseId(), Purchase_I_ab.getIssueRequestId(), Purchase_I_ab.getRegUser(), Purchase_I_ab.getModelNo(), Purchase_I_ab.getPartnerId(),Purchase_I_ab.getMaker());
								
								purchaseService.insertIssueReal(Purchase_I_ab);
								
								if(stockIds[i].isEmpty() || stockIds[i].equals("")||stockIds[i].equals(" ")) {
									
								}
								else {
									String s = stockIds[i].replace("J", "").trim();
									
									Long stockId = Long.parseLong(s);
									purchaseService.UpdateStockabQty(stockId, stockUseQuantitys[i]);
									
								}
								
							}
							
							
						}
						
					
						final boolean isEstimateRequest = "issue".equals(type);
						final String title = isEstimateRequest ? "발주 요청" : "견적 의뢰";
						JobPartner partnerInformation = partnerService.getPartner(purchaseService.NameforIdPartner(selectPartnerName));
						String IpartnerID = purchaseService.NameforIdPartner(selectPartnerName);
						log.info("" + title + " 파트너 정보 ==> [" + partnerInformation + "], 대상 작업 ID = [" + IpartnerID + "]");
							
							
						ModelAndView mv = new ModelAndView("mail/purchase_mail_issue");
						mv.addObject("type", type);			// estimate, order
						mv.addObject("partnerId", IpartnerID);
						mv.addObject("jobOrderId", rawJobOrderId);
						mv.addObject("requestId", NewS_requestId);
						mv.addObject("jobPurchaseId", NewS_jobPurchaseId);
						
					
							
						mv.addObject("title", title);
						mv.addObject("recipient", partnerInformation);
							
						return mv;
						
						
						
					}
					
				}
				
			
					
			
			}
			
					
		}
		
		
		
		
		
		
	
	
	/*
	//구매 발주요청 메일폼
	@RequestMapping("/purchase/{type}/{partnerId}/{rawJobOrderId}/{S_requestId}/{S_jobPurchaseId}")
	public ModelAndView purchaseIssueMail(@PathVariable("partnerId") String partnerId, @PathVariable("rawJobOrderId") String rawJobOrderId, @PathVariable("type") String type,@PathVariable("S_requestId") String S_requestId,@PathVariable("S_jobPurchaseId") String S_jobPurchaseId) throws Exception{
				
			
			
		final boolean isEstimateRequest = "issue".equals(type);
		final String title = isEstimateRequest ? "발주 요청" : "견적 의뢰";
		JobPartner partnerInformation = partnerService.getPartner(partnerId);
		log.info("" + title + " 파트너 정보 ==> [" + partnerInformation + "], 대상 작업 ID = [" + partnerId + "]");
			
				System.out.println("여기 까지 들어왔따!!!!!!!!!!!!!!!!!");
				System.out.println("***********"+rawJobOrderId);
				System.out.println("***********"+S_requestId);
				System.out.println("***********"+S_jobPurchaseId);
			
				ModelAndView mv = new ModelAndView("mail/purchase_mail_issue");
				mv.addObject("type", type);			// estimate, order
				mv.addObject("partnerId", partnerId);
				mv.addObject("jobOrderId", rawJobOrderId);
				mv.addObject("requestId", S_requestId);
				mv.addObject("jobPurchaseId", S_jobPurchaseId);
			
				mv.addObject("title", title);
				mv.addObject("recipient", partnerInformation);
			
				return mv;
			
		}
	*/
	
	
	// 견적 요청 메일 본문
	@RequestMapping("/internal/purchase/estimate/{partnerId}/{jobOrderId}")
	public ModelAndView internalOutsourcingOrder(@PathVariable("partnerId") String partnerId, @PathVariable("jobOrderId") String rawJobOrderId) throws Exception{
		
		String[] jobOrderIdTokens = rawJobOrderId.split(",");
		List<Long> jobOrderIds = new ArrayList(jobOrderIdTokens.length);
		for(int i=0;i<jobOrderIdTokens.length;i++){
			if(StringUtils.isEmpty(jobOrderIdTokens[i]))
				continue;
			jobOrderIds.add( Long.parseLong(jobOrderIdTokens[i]) );
		}
		
		System.out.println("#########"+jobOrderIds);
		
		Collection<List<JobPurchase>> result = purchaseService.getEstimateTargets(partnerId, jobOrderIds.stream().toArray(Long[]::new)  );
		
		
		ModelAndView mv = new ModelAndView("mail/internal/purchase_order");
		mv.addObject("title", "견적 의뢰");
		mv.addObject("title2", "견적");
		mv.addObject("data", result);
		mv.addObject("sender", loginDongleService.getMyInformation());
		
		return mv;
	}
	// 발주 요청 메일 본문
	@RequestMapping("/purchase/issue/orderMail")
	public ModelAndView internalOutsourcingOrder_issue(@RequestParam("partnerId") String partnerId, @RequestParam("jobOrderId") String rawJobOrderId, @RequestParam("requestId") String rawRequestId, @RequestParam("jobPurchaseId") String rawJobPurchaseId) throws Exception{
		
		String[] jobOrderIdTokens = rawJobOrderId.split(",");
		List<Long> jobOrderIds = new ArrayList(jobOrderIdTokens.length);
		for(int i=0;i<jobOrderIdTokens.length;i++){
			if(StringUtils.isEmpty(jobOrderIdTokens[i]))
				continue;
			jobOrderIds.add(Long.parseLong(jobOrderIdTokens[i]));
		}
		
		String[] requestIdTokens = rawRequestId.split(",");
		List<String> requestIds = new ArrayList(requestIdTokens.length);
		for(int i=0;i<requestIdTokens.length;i++){
			if(StringUtils.isEmpty(requestIdTokens[i]))
				continue;
			requestIds.add(requestIdTokens[i]);
		}
		
		
		String[] jobPurchaseIdTokens = rawJobPurchaseId.split(",");
		List<String> jobPurchaseIds = new ArrayList(jobPurchaseIdTokens.length);
		for(int i=0;i<jobPurchaseIdTokens.length;i++){
			if(StringUtils.isEmpty(jobPurchaseIdTokens[i]))
				continue;
			jobPurchaseIds.add(jobPurchaseIdTokens[i]);
		}
		
		List<String> pks = new ArrayList(jobPurchaseIdTokens.length);
		for(int i=0;i<jobPurchaseIdTokens.length;i++) {
			pks.add(jobPurchaseIdTokens[i]+requestIdTokens[i]);
		}
		
		
		
		System.out.println("###########"+jobOrderIds);
		System.out.println("###########"+requestIds);
		System.out.println("###########"+jobPurchaseIds);
		System.out.println("###########"+pks);
		
		
		
		
		Collection<List<Purchase_I_ab>> result = purchaseService.selectIssueMailText(partnerId, jobOrderIds.stream().toArray(Long[]::new), pks.stream().toArray(String[]::new)  );
		
		System.out.println("!!!!!!!!!!!!!!!"+result);
		
		ModelAndView mv = new ModelAndView("mail/internal/purchase_order");
		mv.addObject("title", "발주");
		mv.addObject("title2", "발주");
		mv.addObject("data", result);
		mv.addObject("sender", loginDongleService.getMyInformation());
		
		return mv;
	}
	
	
	// 견적 요청 메일 본문
		@RequestMapping("/purchase/estimate/orderMail")
		public ModelAndView internalOutsourcingOrder_estimate(@RequestParam("partnerId") String partnerId, @RequestParam("jobOrderId") String rawJobOrderId, @RequestParam("requestId") String rawRequestId, @RequestParam("jobPurchaseId") String rawJobPurchaseId) throws Exception{
			
			String[] jobOrderIdTokens = rawJobOrderId.split(",");
			List<Long> jobOrderIds = new ArrayList(jobOrderIdTokens.length);
			for(int i=0;i<jobOrderIdTokens.length;i++){
				if(StringUtils.isEmpty(jobOrderIdTokens[i]))
					continue;
				jobOrderIds.add(Long.parseLong(jobOrderIdTokens[i]));
			}
			
			String[] requestIdTokens = rawRequestId.split(",");
			List<String> requestIds = new ArrayList(requestIdTokens.length);
			for(int i=0;i<requestIdTokens.length;i++){
				if(StringUtils.isEmpty(requestIdTokens[i]))
					continue;
				requestIds.add(requestIdTokens[i]);
			}
			
			
			String[] jobPurchaseIdTokens = rawJobPurchaseId.split(",");
			List<String> jobPurchaseIds = new ArrayList(jobPurchaseIdTokens.length);
			for(int i=0;i<jobPurchaseIdTokens.length;i++){
				if(StringUtils.isEmpty(jobPurchaseIdTokens[i]))
					continue;
				jobPurchaseIds.add(jobPurchaseIdTokens[i]);
			}
			
			List<String> pks = new ArrayList(jobPurchaseIdTokens.length);
			for(int i=0;i<jobPurchaseIdTokens.length;i++) {
				pks.add(jobPurchaseIdTokens[i]+requestIdTokens[i]);
			}
			
			
			
			System.out.println("###########"+jobOrderIds);
			System.out.println("###########"+requestIds);
			System.out.println("###########"+jobPurchaseIds);
			System.out.println("###########"+pks);
			
			
			
			
			Collection<List<Purchase_E_ab>> result = purchaseService.getEstimateMailText(partnerId, jobOrderIds.stream().toArray(Long[]::new), pks.stream().toArray(String[]::new)  );
			
			System.out.println("!!!!!!!!!!!!!!!"+result);
			
			ModelAndView mv = new ModelAndView("mail/internal/purchase_order");
			mv.addObject("title", "견적 의뢰");
			mv.addObject("title2", "견적");
			mv.addObject("data", result);
			mv.addObject("sender", loginDongleService.getMyInformation());
			
			return mv;
		}
		
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	// 메일 제목 제안
	String suggestSubject(String type, Long id) throws Exception{
		if("order".equals(type) ){
			JobOrder order = jobOrderService.searchJob(id);
			if(order == null)
				throw new IllegalArgumentException("지정된 작업 지시를 찾을 수 없습니다");
			
			return "[작업지시] " + order.getOrderNo() + " " + order.getDevice();
		}
		else
			throw new IllegalArgumentException("아직 처리 할 수 없는 유형[" + type + "] 입니다");
	}
	
	// 작업 지시 메일 발송 등록
	@RequestMapping("/send")
	@ResponseBody
	public StandardResponse sendMail(Long id, String type, String partnerId, String body, String[] recipients){
		StandardResponse res = null;
		
		try{
			
			if(recipients == null || recipients.length == 0)
				throw new IllegalArgumentException("수신자를 선택해 주세요");
			
			if(StringUtils.isEmpty(body) || body.length() < 10)
				throw new IllegalArgumentException("본문을 입력해 주세요");
			
			// TODO :: 전송된 ID 가 유효한건지 확인
			
			// 메일 요청 등록
			EmailTransferEntry request = new EmailTransferEntry();
			request.setBody(body);
			request.setFromUserId(loginDongleService.getLoginId());
			request.setSubject(suggestSubject(type, id));
			request.setTargetUserIdList(Arrays.asList(recipients));
			
			mailingService.addNewMail(request);
			
			res = StandardResponse.createSuccessResponse();
		}catch(IllegalArgumentException e){
			log.error("메일 요청 등록 중 오류", e);
			
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e){
			log.error("메일 요청 등록 중 오류", e);
			
			res = StandardResponse.createErrorResponse();
		}
		
		return res;
	}
	
	// 외주 발주 메일 발송 처리
	@RequestMapping("/send/outsourcing")
	@ResponseBody
	public StandardResponse sendOutsourcingMail(String id, String sendPartnerId, String type, String body, String partnerEmail){
		StandardResponse res = null;
		
		try{
			final boolean isEstimateRequest = "estimates".equals(type);
			String partnerId = processOutsourceListDataService.getAssociatedPartnerId(!isEstimateRequest, id);
			JobPartner partnerInformation = partnerService.getPartner(partnerId);
			log.info("Title 정보 ==> [" + id + "]");
			log.info("sendPartnerId 정보 ==> [" + sendPartnerId + "]");
			log.info("메일 수신할 메일주소 정보 ==> [" + partnerEmail + "]");
			log.info("메일 수신할 파트너 정보 ==> [" + partnerInformation + "]");
			
			if(partnerInformation == null)
				throw new RuntimeException("수신자 정보가 없어 발송할 수 없습니다");
			
			if (partnerEmail == null)
				throw new RuntimeException("수신자 정보가 없어 발송할 수 없습니다");

			final String[] recipients = {partnerInformation.getPersonMail()};
			if(StringUtils.isEmpty(recipients[0]))
			{
				recipients[0] = partnerEmail;
				//throw new RuntimeException("담당자 메일 정보가 없어 발송할 수 없습니다");
			}

			if(StringUtils.isEmpty(body) || body.length() < 10)
				throw new IllegalArgumentException("본문을 입력해 주세요");
			
			final String subject = (isEstimateRequest ? "견적 요청" : "발주 요청") + " " + id;
						
			// 메일 요청 등록
			EmailTransferEntry request = new EmailTransferEntry();
			request.setBody(body);
			request.setFromUserId(loginDongleService.getLoginId());
			request.setSubject(subject);
			request.setTargetUserIdList(Arrays.asList(recipients));
			
			//mailingService.addNewMail(request); onlyou93 직원에게 메일 보내는 형식
			mailingService.addNewExtgernalMail(request);
			log.info("메일 등록 완료");

			res = StandardResponse.createResponse("OK");
		}catch(Exception e){
			log.error("메일 요청 등록 중 오류", e);
			
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}
		
		return res;
	}
	
	public String CalculatorNo(int num) {
		
		if(num<10) {
			String result = "00"+ String.valueOf(num).trim();
			return result;
		}
		else if(10<=num && num<100) {
			String result = "0"+ String.valueOf(num).trim();
			return result;
		}
		else if(100<=num) {
			String result = String.valueOf(num).trim();
			return result;
		}else {
			return String.valueOf(num).trim();
		}
	}
	
	public int getCharNumber(String str, char c) {
		 int count = 0;
	        for(int i=0;i<str.length();i++)
	        {
	            if(str.charAt(i) == c)
	                count++;
	        }
	        return count;
	}
	
	
	// 구매 견적 메일 발송 처리
	@RequestMapping("/send/purchase")
	@ResponseBody
	public StandardResponse sendPurchaseMail(String partnerId, String type, String body, String jobOrderId){
		StandardResponse res = null;
		
		try{
			
			System.out.println("*********"+type);
			
			if("estimate".equals(type)) {
				
				int countJobOrderId = getCharNumber(jobOrderId,',');
				String[] JobOrderIdList = jobOrderId.split(",",countJobOrderId);
				
				for(String job_order_id2 : JobOrderIdList) {
					if(job_order_id2.isEmpty()) {
						break;
					}
					String Sjob_order_id = job_order_id2.replace(",","");
					
					Long job_order_id = Long.parseLong(Sjob_order_id.trim());
					System.out.println("******////////*******"+job_order_id);
					SelectJobPurchaseNew SelectJobPurchaseNew = new SelectJobPurchaseNew();
					SelectJobPurchaseNew.setJob_order_id(job_order_id);
					SelectJobPurchaseNew.setPartner_id(partnerId);
					List<JobPurchaseNew> JobPurchaseNewList = purchaseService.getJobpurchaseNew(SelectJobPurchaseNew);
				
				
					int num = 1;
				
					String inDate   = new java.text.SimpleDateFormat("yyMMddhhmmss").format(new java.util.Date());	
					
					String estimate_request_id = "R" + partnerId.trim() + "-" + inDate.trim();
					
				
					for(JobPurchaseNew JobPurchaseNew : JobPurchaseNewList) {
					
					
						String estimate_request_user_id = loginDongleService.getLoginId();
						
						System.out.println("**********"+JobPurchaseNew.getId());
						
						UpdateJobPurchaseNew UpdateJobPurchaseNew = new UpdateJobPurchaseNew();
						UpdateJobPurchaseNew.setId(JobPurchaseNew.getId());
						UpdateJobPurchaseNew.setEstimate_request_id(estimate_request_id);
						UpdateJobPurchaseNew.setEstimate_request_user_id(estimate_request_user_id);
					
						purchaseService.UpdateJobPurchaseNew(UpdateJobPurchaseNew);
				
						JobPurchaseNew_history JobPurchaseNew_history = new JobPurchaseNew_history();
					
						JobPurchaseNew_history.setJob_purchase_id(JobPurchaseNew.getId());
						JobPurchaseNew_history.setRequest_id(estimate_request_id);
						JobPurchaseNew_history.setRequest_type("E");
						
						System.out.println("*************Model_no1= "+JobPurchaseNew.getModelNo());
						JobPurchaseNew_history.setModelNo(JobPurchaseNew.getModelNo());
						System.out.println("*************Model_no2= "+JobPurchaseNew_history.getModelNo());
						
						System.out.println("*************"+JobPurchaseNew.getMaker());
						JobPurchaseNew_history.setMaker(JobPurchaseNew.getMaker());
						
						System.out.println("*************"+JobPurchaseNew.getQuantity());
						JobPurchaseNew_history.setOrder_quantity(JobPurchaseNew.getQuantity());	
					
						purchaseService.InsertJobPurchaseNew_history(JobPurchaseNew_history);
						num++;		
				
					}
				}
			}else {
				
			}
			
			
			
			final boolean isEstimateRequest = "estimate".equals(type);
			JobPartner partnerInformation = partnerService.getPartner(partnerId);
			log.info("메일 수신할 파트너 정보 ==> [" + partnerInformation + "]");
			
			if(partnerInformation == null)
				throw new RuntimeException("수신자 정보가 없어 발송할 수 없습니다");
			
			final String[] recipients = {partnerInformation.getPersonMail()};
			if(StringUtils.isEmpty(recipients[0]))
				throw new RuntimeException("담당자 메일 정보가 없어 발송할 수 없습니다");
			
			if(StringUtils.isEmpty(body) || body.length() < 10)
				throw new IllegalArgumentException("본문을 입력해 주세요");
			
			final String subject = (isEstimateRequest ? "견적 요청" : "발주 요청");
						
			// 메일 요청 등록
			EmailTransferEntry request = new EmailTransferEntry();
			request.setBody(body);
			request.setFromUserId(loginDongleService.getLoginId());
			request.setSubject(subject);
			request.setTargetUserIdList(Arrays.asList(recipients));
			
			mailingService.addNewExtgernalMail(request);
			log.info("메일 등록 완료");

			res = StandardResponse.createResponse("OK");
		}catch(Exception e){
			log.error("메일 요청 등록 중 오류", e);
			
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}
		

		
		
		
		return res;
	}
	// 발주 메일 발송 처리
	@RequestMapping("/send/purchase/issueMail")
	@ResponseBody
	public StandardResponse sendPurchaseissueMail(String partnerId, String type, String body, String jobOrderId, String requestId, String jobPurchaseId, String partnerEmail){
		StandardResponse res = null;
		
		try {
		
		
			
			if("issue".equals(type)) {
				
				List<Purchase_I_ab> abIssue = purchaseService.selectIssueAB();
				
				int num = 1;
				
				String inDate   = new java.text.SimpleDateFormat("yyMMddhhmmss").format(new java.util.Date());	
				
				String IssueRequestId = "I"+ "-" + partnerId.trim() + "-" + inDate.trim();
				for(Purchase_I_ab Purchase_I_abS : abIssue) {
		
			
					String orderRequestUserId = loginDongleService.getLoginId();
			
			
			
				
					purchaseService.UpdateStageP(Purchase_I_abS.getJobPurchaseId(), IssueRequestId, orderRequestUserId, Purchase_I_abS.getModelNo(), partnerId, Purchase_I_abS.getMaker() );
			
					Purchase_I_ab Purchase_I_ab = new Purchase_I_ab();
			
					Purchase_I_ab = Purchase_I_abS;
			
					Purchase_I_ab.setIssueRequestId(IssueRequestId);
			
					purchaseService.insertIssueReal(Purchase_I_ab);
					
					if(Purchase_I_abS.getStockId()!=null) {
						purchaseService.UpdateStockabQty(Purchase_I_abS.getStockId(), Purchase_I_abS.getAbQuantity());
						
					} 
					
					
					
				
					num++;
				}
				
				
				final boolean isIssueRequest = "issue".equals(type);
				JobPartner partnerInformation = partnerService.getPartner(partnerId);
				log.info("메일 수신할 파트너 정보 ==> [" + partnerInformation + "]");
				
				if(partnerInformation == null)
					throw new RuntimeException("수신자 정보가 없어 발송할 수 없습니다");
				
				final String[] recipients = {partnerEmail};
				if(StringUtils.isEmpty(recipients[0]))
					throw new RuntimeException("담당자 메일 정보가 없어 발송할 수 없습니다");
				
				if(StringUtils.isEmpty(body) || body.length() < 10)
					throw new IllegalArgumentException("본문을 입력해 주세요");
				
				final String subject = (isIssueRequest ? "발주 요청" : "견적 요청") ;
							
				// 메일 요청 등록
				EmailTransferEntry request = new EmailTransferEntry();
				request.setBody(body);
				request.setFromUserId(loginDongleService.getLoginId());
				request.setSubject(subject);
				request.setTargetUserIdList(Arrays.asList(recipients));
				
				mailingService.addNewExtgernalMail(request);
				log.info("메일 등록 완료");

				res = StandardResponse.createResponse("OK");

			
			}
			else {
				
				List<Purchase_E_ab> abEstimate = purchaseService.selectEstimateAB();
				
				int num = 1;
				
				String inDate   = new java.text.SimpleDateFormat("yyMMddhhmmss").format(new java.util.Date());	
				
				String EstimateRequestId = "E"+ "-" + partnerId.trim() + "-" + inDate.trim();
			
				for(Purchase_E_ab Purchase_E_abS : abEstimate) {
		
			
					String orderRequestUserId = loginDongleService.getLoginId();
			
			
				
					purchaseService.UpdateStageE(Purchase_E_abS.getJobPurchaseId());
			
					Purchase_E_ab Purchase_E = new Purchase_E_ab();
			
					Purchase_E = Purchase_E_abS;
			
					Purchase_E.setEstimateRequestId(EstimateRequestId);
			
					purchaseService.insertEstimateReal(Purchase_E);
					
					
			
					num++;
				}
				
				
				final boolean isEstimateRequest = "estimate".equals(type);
				JobPartner partnerInformation = partnerService.getPartner(partnerId);
				log.info("메일 수신할 파트너 정보 ==> [" + partnerInformation + "]");
				
				if(partnerInformation == null)
					throw new RuntimeException("수신자 정보가 없어 발송할 수 없습니다");
				
				final String[] recipients = {partnerEmail};
				if(StringUtils.isEmpty(recipients[0]))
					throw new RuntimeException("담당자 메일 정보가 없어 발송할 수 없습니다");
				
				if(StringUtils.isEmpty(body) || body.length() < 10)
					throw new IllegalArgumentException("본문을 입력해 주세요");
				
				final String subject = (isEstimateRequest ? "견적 요청" : "발주 요청") ;
							
				// 메일 요청 등록
				EmailTransferEntry request = new EmailTransferEntry();
				request.setBody(body);
				request.setFromUserId(loginDongleService.getLoginId());
				request.setSubject(subject);
				request.setTargetUserIdList(Arrays.asList(recipients));
				
				mailingService.addNewExtgernalMail(request);
				log.info("메일 등록 완료");

				res = StandardResponse.createResponse("OK");

				
				
				
			}
			
			
		}
		
		catch(Exception e){
			log.error("메일 요청 등록 중 오류", e);
			
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}
		
		if("issue".equals(type)){
			purchaseService.deltePurchaseIssueAB();
		}
		else {
			purchaseService.deletePuchaseEstimateAB();
		}
		
		return res;
	}
	
	// 견적 메일 취소
	@RequestMapping("/exit/estimateMail")
	public String exitEstimateMail() throws Exception{
			
		System.out.println("가상 DB 삭제 여기왔다!!!");
		purchaseService.deletePuchaseEstimateAB();
				
		return "redirect:/purchase/estimate/reg_list";
	}
	
	// 발주 메일 취소
	@RequestMapping("/exit/issueMail")
	public String exitIssueMail() throws Exception{
		
		System.out.println("가상 DB 삭제 여기왔다!!!");
		purchaseService.deltePurchaseIssueAB();
			
		return "redirect:/purchase/issue/reg_list";
	}
	
	
}
