package com.yuhannci.erp.controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UtilController {
	
	final static LinkedList<SimpleEntry<String, String>> processTypes = new LinkedList<>();
	
	static {
		processTypes.add(new SimpleEntry<String, String>("", "(선택)"));
		processTypes.add(new SimpleEntry<String, String>("I", "사내 가공"));
		processTypes.add(new SimpleEntry<String, String>("O", "사외 가공"));
		processTypes.add(new SimpleEntry<String, String>("S", "표준폼"));
		processTypes.add(new SimpleEntry<String, String>("D", "표준폼 가공"));
	}
	
	@Autowired UserService userService;
	@Autowired PartnerService partnerService;
	@Autowired 	JobOrderService jobOrderService;
	
	// 영업담당 리스트(콤보박스, <select/>) 만들기
	@RequestMapping("/internal/util/select/business")
	public ModelAndView selectBusinessMen(String controlName, String defaultValue, 
		@RequestParam(value="showUnspecifiedItem", required=false) Boolean showUnspecifiedItem){

		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getBusinessDeptUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("defaultValue", defaultValue);
		//if (showUnspecifiedItem==true)
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		//else
		//	mv.addObject("showUnspecifiedItem", false);
		return mv;
	}
	// 설계담당자 리스트 만들기
	@RequestMapping("/internal/util/select/design")
	public ModelAndView selectDesigners(String controlName, String defaultValue){
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getDesignDeptUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("defaultValue", defaultValue);
		return mv;
	}
	// 매출업체(고객사) 리스트 만들기
	@RequestMapping("/internal/util/select/customer")
	public ModelAndView selectPartners(String controlName, String defaultValue, Boolean showUnspecifiedItem, String cssClass){
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getSimpleCustomerList());
		mv.addObject("controlName", controlName);
		mv.addObject("defaultValue", defaultValue);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		return mv;
	}	

	// 가공 유형 리스트 만들기
	@RequestMapping("/internal/util/select/process")
	public ModelAndView selectProcess(String controlName, String defaultValue){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", processTypes);
		mv.addObject("controlName", controlName);
		mv.addObject("defaultValue", defaultValue);
		return mv;
	}	

	// 검사 직원 리스트 만들기
	@RequestMapping("/internal/util/select/qcuser")
	public ModelAndView selectQclist(String controlName, Boolean showUnspecifiedItem, String cssClass){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getQcDeptUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		return mv;
	}	
	// 생산관리 직원 리스트 만들기
	@RequestMapping("/internal/util/select/manager")
	public ModelAndView selectManagerlist(String controlName, Boolean showUnspecifiedItem, String cssClass){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getManagerDeptUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		return mv;
	}
	// 조립 직원 리스트 만들기
	@RequestMapping("/internal/util/select/assembleuser")
	public ModelAndView selectAssembleUser(String controlName, Boolean showUnspecifiedItem, String cssClass){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getAssembleDeptUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		return mv;
	}
	// 프로그램 직원 리스트 만들기
	@RequestMapping("/internal/util/select/pgmuser")
	public ModelAndView selectPgmUser(String controlName, Boolean showUnspecifiedItem, String cssClass){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getPgmUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		return mv;
	}
	// 로봇 직원 리스트 만들기
	@RequestMapping("/internal/util/select/robotuser")
	public ModelAndView selectRobotUser(String controlName, Boolean showUnspecifiedItem, String cssClass){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", userService.getRobotUserList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		return mv;
	}
	
	//현재 진행중인 Order No 리스트 만들기
	@RequestMapping("/internal/util/select/order_no")
	public ModelAndView selectOrderNoList(String controlName, Boolean showUnspecifiedItem, String cssClass, String style){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", jobOrderService.getIngOrderList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		mv.addObject("style", style);
		return mv;
	}
	

	// 후처리 업체 리스트 콤보
	@RequestMapping("/internal/util/select/postprocess_partner")
	public ModelAndView selectPostprocessPartner(String controlName, Boolean showUnspecifiedItem, String cssClass, String style){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getPostprocessParnterComboList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		mv.addObject("style", style);
		return mv;
	}

	// 파일 첨부 팝업
	@RequestMapping("/poup/upload/file")
	public ModelAndView popupUploadFile(String section, String fileCategory, /*String fileFieldName, */ String openerFunctionName, String parameter1, String parameter2, String parameter3){
		ModelAndView mv = new ModelAndView("util/popup_upload_file");
		mv.addObject("section", section);
		mv.addObject("fileCategory", fileCategory);		// RegisteredFileService 참고 요망
		mv.addObject("openerFunctionName", openerFunctionName);
		StringBuilder sb = new StringBuilder();
		if(!StringUtils.isEmpty(parameter1))
			sb.append("\"" + parameter1 + "\"");
		if(!StringUtils.isEmpty(parameter2))
			sb.append(",\"" + parameter2 + "\"");
		if(!StringUtils.isEmpty(parameter3))
			sb.append(",\"" + parameter3 + "\"");		
		mv.addObject("parameter", sb.toString());		
		return mv;
	}
		
	LinkedList<SimpleEntry<String, String>> LoadBuyPartnerListFromCache() {
		
		LinkedList<SimpleEntry<String, String>> buyPartners = new LinkedList<>();
		buyPartners.add(new SimpleEntry<String, String>("", "(선택)"));
		partnerService.getBuyPartnerList().stream().forEach(a -> buyPartners.add(new SimpleEntry<String, String>(a.getId(), a.getPartnerName())));
		
		return buyPartners;
	}
	
	
	// 가공 발주 업체 리스트 만들기
	@RequestMapping("/internal/util/select/buy_partner")
	public ModelAndView selectBuyPartner(String controlName, String defaultValue, String cssClass, 
									@RequestParam(name="showUnspecifiedItem", required=false) Boolean showUnspecifiedItem)
	{

		Boolean adjustedshowItem = (showUnspecifiedItem==null) ? false:showUnspecifiedItem;
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getSelectPartnerList("N","Y","N","N", null));
		mv.addObject("controlName", controlName);
		mv.addObject("defaultValue", defaultValue);
		mv.addObject("cssClass", cssClass);
		mv.addObject("showUnspecifiedItem", adjustedshowItem);
		return mv;
	}
	
	// 구매 발주 업체 리스트 만들기
	@RequestMapping("/internal/util/select/pur_partner")
	public ModelAndView selectPurPartner(String controlName, String defaultValue, String cssClass){

		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getSelectPartnerList("Y","N","N","N", null));
		mv.addObject("controlName", controlName);
		mv.addObject("defaultValue", defaultValue);
		mv.addObject("cssClass", cssClass);
		
		return mv;
	}
	
	// 매입 거래처 팝업
	@RequestMapping("/popup/buy_partner_list")
	public ModelAndView popupBuyPartnerList(String callback, String category, String keyword){
		
		ModelAndView mv = new ModelAndView("util/popup_select_buy_partner");
		mv.addObject("data", partnerService.getBuyPartnerList(category, keyword));
		mv.addObject("callback", callback);
		return mv;
	}
	
	// 취소 사유 입력
	@RequestMapping("/popup/abort_reason")
	public ModelAndView popupAbortReason(@RequestParam("js") String callbackJavascriptFunction) {
		
		if(StringUtils.isEmpty(callbackJavascriptFunction))
			return null;
		
		ModelAndView mv = new ModelAndView("util/popup_abort_reason");
		mv.addObject("callbackJavascriptFunction", callbackJavascriptFunction);
		
		return mv;
	}
	
	@RequestMapping("/internal/util/select/billing_after")
	public ModelAndView selectBillingAfter(String controlName, Boolean showUnspecifiedItem, String cssClass, String style){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getBillingAfterList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		mv.addObject("style", style);
		return mv;
	}	
	@RequestMapping("/internal/util/select/billing_day")
	public ModelAndView selectBillingDay(String controlName, Boolean showUnspecifiedItem, String cssClass, String style){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getBillingDayList());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		mv.addObject("style", style);
		return mv;
	}	
	@RequestMapping("/internal/util/select/partner_type")
	public ModelAndView selectPartnerTypes(String controlName, Boolean showUnspecifiedItem, String cssClass, String style){
		
		ModelAndView mv = new ModelAndView("util/select_combo");
		mv.addObject("data", partnerService.getPartnerTypes());
		mv.addObject("controlName", controlName);
		mv.addObject("showUnspecifiedItem", showUnspecifiedItem);
		mv.addObject("cssClass", cssClass);
		mv.addObject("style", style);
		return mv;
	}	
}
