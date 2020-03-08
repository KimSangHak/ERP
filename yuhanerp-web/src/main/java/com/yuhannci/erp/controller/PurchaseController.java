package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuhannci.erp.model.Choice_Estimate;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.EstimateListUpdated;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_Issue;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_histroy;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_issueHistory;
import com.yuhannci.erp.model.InnerStockModel;
import com.yuhannci.erp.model.MailSendIssue;
import com.yuhannci.erp.model.MailSendIssueNew;
import com.yuhannci.erp.model.PreEstimateRequest;
import com.yuhannci.erp.model.ProcessHisSearchForm;
import com.yuhannci.erp.model.PurchasePartner;
import com.yuhannci.erp.model.PurchaseSelectHis;
import com.yuhannci.erp.model.SearchCountOrderState;
import com.yuhannci.erp.model.SearchCountPartnerState;
import com.yuhannci.erp.model.SearchOrderState;
import com.yuhannci.erp.model.SerachPartnerState;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.StatementDetail;
import com.yuhannci.erp.model.StatementIns;
import com.yuhannci.erp.model.StatementInsList;
import com.yuhannci.erp.model.statementList;
import com.yuhannci.erp.model.StatementInsPop;
import com.yuhannci.erp.model.StatementListPartner;
import com.yuhannci.erp.model.StatementListPop;
import com.yuhannci.erp.model.StatementPartner;
import com.yuhannci.erp.model.StatementUpdate;
import com.yuhannci.erp.model.StockHIsIns;
import com.yuhannci.erp.model.StockIns;
import com.yuhannci.erp.model.StockListOrderNo;
import com.yuhannci.erp.model.SumPriceState;
import com.yuhannci.erp.model.Warehousing;
import com.yuhannci.erp.model.Warehousing_pop;
import com.yuhannci.erp.model.billingDay;
import com.yuhannci.erp.model.billingMonth;
import com.yuhannci.erp.model.innerAndoutModel;
import com.yuhannci.erp.model.innerStockpop;
import com.yuhannci.erp.model.innerstockListModel;
import com.yuhannci.erp.model.jobpurchaseEstimateHistory;
import com.yuhannci.erp.model.stockRealListModel;
import com.yuhannci.erp.model.stockRealOutModel;
import com.yuhannci.erp.model.Excel.PurchaseLine;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.db.JobPartner;
import com.yuhannci.erp.model.db.JobPartnerType;
import com.yuhannci.erp.model.db.JobPartnerTypeNew;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.db.JobPurchaseDB;
import com.yuhannci.erp.model.db.JobPurchaseNew_history;
import com.yuhannci.erp.model.db.JobPurchaseNew_history_null;
import com.yuhannci.erp.model.db.Purchase_E_ab;
import com.yuhannci.erp.model.db.Purchase_I_ab;
import com.yuhannci.erp.model.db.RoundRobin;
import com.yuhannci.erp.model.db.SurchAllOrder;
import com.yuhannci.erp.model.db.SurchAllPartner;
import com.yuhannci.erp.model.db.TransitionPurchase;
import com.yuhannci.erp.model.db.stock;
import com.yuhannci.erp.model.db.stockInHistroy;
import com.yuhannci.erp.model.db.stockOutHistory;
import com.yuhannci.erp.model.db.warehousing_list;
import com.yuhannci.erp.model.db.JobPurchaseHistory;
import com.yuhannci.erp.model.db.JobPurchaseNew;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.MyMenuService;
import com.yuhannci.erp.service.NoticeMessageService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.PurchaseService;
import com.yuhannci.erp.service.PushAlarmService;
import com.yuhannci.erp.service.UtilityService;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	JobOrderService jobOrderService;
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	LoginDongleService loginDongleService;
	@Autowired
	PartnerService partnerService;
	@Autowired
	UtilityService utilityService;
	
	@Autowired MyMenuService myMenuService;
	
	@Autowired MenuService menuService;
	
	@Autowired NoticeMessageService noticeMessageService;
	@Autowired PushAlarmService pushAlarmService;
	
	public static String Code;
	public static String Kind;

	// 구매 리스트 등록 (슬라이드 50)
	@RequestMapping("/list")
	public ModelAndView PurchaseList(String orderNoBase, String orderNoExtra, String keyword, String designDateBegin,
			String designDateEnd) {

		// 날짜 처리
		if (StringUtils.isEmpty(designDateBegin))
			designDateBegin = null;
		if (StringUtils.isEmpty(designDateEnd))
			designDateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (designDateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(designDateBegin);
			if (designDateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(designDateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}

		// 출력

		List<JobOrder> orders = jobOrderService.getUnfinishedJob(null, keyword, orderNoBase, orderNoExtra, null, null,
				null, null, convertedDesignDateBegin, convertedDesignDateEnd);

		ModelAndView mv = new ModelAndView("purchase/reg_list");
		mv.addObject("designDateBegin", designDateBegin);
		mv.addObject("designDateEnd", designDateEnd);
		mv.addObject("data", orders);

		return mv;
	}

	// 구매 리스트 등록에서 등록 팝업 (슬라이드 51)
	@RequestMapping("/popup/register")
	public ModelAndView PurchaseRegisterPopup(String jobOrderId) {

		JobOrder order = null;
		Long id = 0L;
		try {
			if (StringUtils.isEmpty(jobOrderId)) {
				log.debug("구매 리스트 등록 팝업에 id 없음");
				return null;
			}

			id = Long.parseLong(jobOrderId);
			order = jobOrderService.getSpecificJob(id);
			if (order == null) {
				log.info("지정된 작업 지시[" + jobOrderId + "]를 찾을 수 없음");
				return null;
			}
		} catch (Exception e) {
			log.error("구매 리스트 등록 팝업에 작업 지시 번호 처리 오류 [" + jobOrderId + "]", e);
			return null;
		}

		ModelAndView mv = new ModelAndView("purchase/popup/register");
		mv.addObject("order", order);
		mv.addObject("jobOrderId", jobOrderId);
		return mv;
	}
	
	//구매품 없음 등록
	@RequestMapping("/nonePurchase")
	@ResponseBody
	public StandardResponse nonePurchase(Long jobOrderId) {
		StandardResponse res = null;

		try {

			
			if (jobOrderId == null)
				throw new IllegalArgumentException("작업 지시자를 찾을 수 없습니다");


			purchaseService.nonePurchaseUpdate(jobOrderId);

		
			res = StandardResponse.createResponse("OK");
			
			System.out.println("%%%%%%%%%%%%%%%%%% = " + res.getResult());

		} catch (IllegalArgumentException e) {
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		} catch (Exception e) {
			res = StandardResponse.createResponse("ERROR", "구매품 없음 등록 중 오류가 발생하였습니다");
			log.error("구매품 없음 등록 중 오류", e);
		}

		return res;
	}

	// 구매 리스트 등록
	@RequestMapping("/perform_reg_purchase")
	@ResponseBody
	public StandardResponse performRegPurchase(Long jobOrderId, String gridData) {
		StandardResponse res = null;

		try {
			
			System.out.println("GridData = " + gridData);

			
			if (jobOrderId == null)
				throw new IllegalArgumentException("작업 지시자를 찾을 수 없습니다");

			ObjectMapper om = new ObjectMapper();

			PurchaseLine[] lines = null;
			try {
			
				lines = om.readValue(gridData, PurchaseLine[].class);
			} catch (Exception e) {
				log.error("JSON 파싱 오류", e);
				lines = null;
			}
			if (lines == null || lines.length == 0)
				throw new IllegalArgumentException("구매 데이터가 없습니다");

			int lineNo = 0;
			for (PurchaseLine line : lines) {
				lineNo++;
				
				if (StringUtils.isEmpty(line.getModelNo()) || StringUtils.isEmpty(line.getMaker()))
					throw new IllegalArgumentException(lineNo + "줄에 모델/제조사 항목이 없습니다");

				if (StringUtils.isEmpty(line.getUnitNo()))
					throw new IllegalArgumentException(lineNo + "줄에 유닛 번호가 없습니다");

				String partnerId = partnerService.getPartnerId(line.getProvider());
				if (partnerId == null)
					throw new IllegalArgumentException(lineNo + "줄에 [" + line.getProvider() + "] 업체 정보를 찾을 수 없습니다");

				line.setPartnerId(partnerId);
			}
			

			System.out.println("여기 나옴?????????");
			purchaseService.addPurchaseList(jobOrderId, lines);
			System.out.println("여기 나옴?????????22222222222");
			
			System.out.println("!!!!!!!!!!!!!!!데이터 = " + lines.length + "   !!!!!!!!!!ID = "+jobOrderId);

			log.info("데이터 = " + lines.length + " 라인, jobOrderId = " + jobOrderId);
			res = StandardResponse.createResponse("OK");

		} catch (IllegalArgumentException e) {
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		} catch (Exception e) {
			res = StandardResponse.createResponse("ERROR", "저장 중 오류가 발생하였습니다");
			log.error("구매 리스트 등록 중 오류", e);
		}

		return res;
	}
	
	
	// 구매 LIST 조회
	@RequestMapping("/history")
	public ModelAndView PurchaseViewList(String maker, String desc, String customer, String modelNo, String unitNo) {
		
		String partnerId;
		if(StringUtils.isEmpty(customer)) {
			
			partnerId=null;
			
		}else {
			partnerId = purchaseService.selectPartnerId(customer);
		}
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "01", "02");
		
		
		// jobOrderService
		List<JobPurchase> data = purchaseService.getPurchaseList(partnerId, maker, desc, modelNo, unitNo);

		ModelAndView mv = new ModelAndView("purchase/history_list");
		mv.addObject("maker", maker);
		mv.addObject("desc", desc);
		mv.addObject("customer", customer);
		mv.addObject("modelNo", modelNo);
		mv.addObject("unitNo", unitNo);
		mv.addObject("data", data);
		mv.addObject("isupdate", isupdate);	

		return mv;
	}
	
	
	//구매품 조회
	@RequestMapping("/select")
	public ModelAndView purchaseDetail(Long seq){
		
	
		ModelAndView mv = new ModelAndView("purchase/PurchaseSelect");
		
		
		return mv;
	}
	
	//구매품 조회 데이터
	@ResponseBody
	@RequestMapping("/data/select")
	public DataTableResponse dataPurchaseDetail(PurchaseSelectHis form) {
	
		System.out.println("******************************************* SEQ = "+form.getSeq());
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			
			res = purchaseService.getPurchaseSelectPageList(form);
			
			System.out.println(res.getData());
			System.out.println("여기들어옴???????????????????????????????????????????????????");
		
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		return res;
	}

	

	// 구매 리스트 수정 팝업
	@RequestMapping("/popup/edit_purchase_list/{jobPurchaseId}")
	public ModelAndView editPurchaseListPopup(@PathVariable("jobPurchaseId") Long id) {

		JobPurchase data = purchaseService.getPurchaseListEntry(id);
		if (data == null) {
			log.info("구매 리스트 수정 팝업에 전달된 구매ID[" + id + "]로 데이터를 찾을 수 없음");
			return null;
		}

		ModelAndView mv = new ModelAndView("purchase/popup/edit_purchase_list");
		mv.addObject("jobPurchaseId", id);
		mv.addObject("data", data);
		log.info("데이터 ==> [" + data + "]");
		return mv;
	}

	// 구매 리스트 수정 처리
	@RequestMapping("/perform_update_purchase")
	@ResponseBody
	public StandardResponse performUpdatePurchase(JobPurchase data) {
		StandardResponse res = null;
		try {
			if (data.getId() == null)
				throw new IllegalArgumentException("지정된 구매 항목을 찾을 수 없습니다");

			JobPurchase org = purchaseService.getPurchaseListEntry(data.getId());
			if (org == null)
				throw new IllegalArgumentException("지정된 구매 항목을 찾을 수 없습니다");

			if (org.checkDeleted())
				throw new IllegalArgumentException("지정된 구매 항목이 삭제되었습니다");

			if (data.getQuantity() == null || data.getQuantity() == 0)
				throw new IllegalArgumentException("수량은 0 보다 커야 합니다");

			// update
			purchaseService.updatePurchaseListEntry(data);

			res = StandardResponse.createResponse("OK");

		} catch (IllegalArgumentException e) {
			res = StandardResponse.createResponse("ERROR", e.getMessage());
			log.error("수정 중 오류 발생", e);
		} catch (Exception e) {
			res = StandardResponse.createResponse("ERROR", "변경 중 오류가 발생하였습니다");
			log.error("수정 중 오류 발생", e);
		}
		return res;
	}

	@RequestMapping("/delete_purchase")
	@ResponseBody
	public StandardResponse deletePurchase(Long id, String reason, String withIssue) {

		StandardResponse res = null;
		try {
			if (!(withIssue.equals("Y") || withIssue.equals("N")))
				throw new IllegalArgumentException("파라미터가 잘못되었습니다");

			if (id == null)
				throw new IllegalArgumentException("지정된 구매 항목을 찾을 수 없습니다");

			JobPurchase org = purchaseService.getPurchaseListEntry(id);
			if (org == null)
				throw new IllegalArgumentException("지정된 구매 항목을 찾을 수 없습니다");

			if (org.checkDeleted())
				throw new IllegalArgumentException("지정된 구매 항목이 삭제되었습니다");
		
			

			// update
			purchaseService.deletePurchaseEntry(id, loginDongleService.getLoginId(), reason);

			res = StandardResponse.createResponse("OK");

		} catch (IllegalArgumentException e) {
			res = StandardResponse.createResponse("ERROR", e.getMessage());
			log.error("수정 중 오류 발생", e);
		} catch (Exception e) {
			res = StandardResponse.createResponse("ERROR", "삭제 처리중 오류가 발생하였습니다");
			log.error("수정 중 오류 발생", e);
		}
		return res;

	}

	// 모델 검색 팝업
	@RequestMapping("/popup/model_search")
	public ModelAndView modelSearchPopup(String category, String keyword) {
		ModelAndView mv = new ModelAndView("purchase/popup/model_search");
		mv.addObject("category", category);
		mv.addObject("keyword", keyword);
		mv.addObject("data", purchaseService.searchModel(keyword, category));
		return mv;
	}

	// 업체 검색 팝업
	@RequestMapping("/popup/partner_search")
	public ModelAndView partnerSearchPopup(String keyword) {
		ModelAndView mv = new ModelAndView("purchase/popup/partner_search");
		mv.addObject("keyword", keyword);
		mv.addObject("data", purchaseService.searchPartner(keyword));
		return mv;
	}

	// 구매 견적/발주 요청 등록
	@RequestMapping("/{mode}/reg_list")
    public ModelAndView PurchaseEstimateRegList(@PathVariable("mode") String mode, String orderNoBase, String orderNoExtra, String roundRobinYN) {
	
    	if( !((mode.equals("issue") || mode.equals("estimate") )) )
    		return null;
    
    	// 구매 발주 요청 목록 or 구매 견적 요청 목록
    	final boolean isPurchaseIssueList = mode.equals("issue");
    	
    	if (isPurchaseIssueList) {
    	    // 발주 요청 할 수 있는 목록 조회
    		List<GroupedJobPurchaseEntry_Issue> data = null;
    		Integer pageNo=0; 
    	    data = purchaseService.getPurchaseListForIssue(orderNoBase, orderNoExtra, roundRobinYN);
    	    ModelAndView mv = new ModelAndView("purchase/estimate_reg_list");
        	mv.addObject("data", data);
        	mv.addObject("mode", mode);
        	mv.addObject("pageNo", pageNo);
        	mv.addObject("orderNoBase", orderNoBase);
        	mv.addObject("orderNoExtra", orderNoExtra);
        	mv.addObject("roundRobinYN", roundRobinYN);
        
        	return mv;
    	} else {
    	    // 견적 요청 할 수 있는 목록
    		List<GroupedJobPurchaseEntry> data = null;
    		Integer pageNo=0;
    	    data = purchaseService.getPurchaseListForEstimate(orderNoBase, orderNoExtra, roundRobinYN);
    		ModelAndView mv = new ModelAndView("purchase/estimate_reg_list");
        	mv.addObject("data", data);
        	mv.addObject("mode", mode);
        	mv.addObject("pageNo", pageNo);
        	mv.addObject("orderNoBase", orderNoBase);
        	mv.addObject("orderNoExtra", orderNoExtra);
        	mv.addObject("roundRobinYN", roundRobinYN);
        
        	return mv;
    	}
    
    	
    }
	
	@RequestMapping("/{mode}/register_history")
	@ResponseBody
	public String registerHistory(@PathVariable("mode") String mode, String partnerId, String jobOrderId){
		List<Long> jobOrderIds = utilityService.tokenizeValues(jobOrderId);
		
		
		
		String id = utilityService.generateId(null, partnerId);
		return id;
	}

	// 거래처 상세 현황 팝업
	@RequestMapping("/popup/{mode}/partner_detail")
	public ModelAndView partnerDetailPopup(@PathVariable("mode") String mode, String partnerId, Long jobOrderId) throws Exception {

		ModelAndView mv = new ModelAndView("purchase/popup/partner_detail");
		mv.addObject("mode", mode);
		mv.addObject("partnerId", partnerId);
		mv.addObject("jobOrderId", jobOrderId);
		mv.addObject("partner", partnerService.getPartner(partnerId));
		mv.addObject("jobOrder", jobOrderService.getSpecificJob(jobOrderId));
		mv.addObject("items", purchaseService.getAssignedList(jobOrderId, partnerId));

		return mv;
	}
	
	// 거래처 단가 및 재고현황
	@RequestMapping("/popup/{mode}/partner_stock")
	public ModelAndView partnerStockPopup(@PathVariable("mode") String mode){
		
		ModelAndView mv = new ModelAndView("purchase/popup/partner_stock");
		mv.addObject("mode", mode);
		
		return mv;
	}

	// 구매 견적/발주 리스트 조회
	@RequestMapping("/{mode}/history")
	public ModelAndView PurchaseEstimateHistoryList(@PathVariable("mode") String mode, String desc,
			String maker, String customer, String DateBegin, String DateEnd, String kind
			,String orderNoBase, String orderNoExtra) {
		
		
		
		// 날짜 처리
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
		
		String partnerId;
		if(StringUtils.isEmpty(customer)) {
			
			partnerId=null;
			
		}else {
			partnerId = purchaseService.selectPartnerId(customer);
		}
		
		
		
		if( !((mode.equals("issue") || mode.equals("estimate") )) )
    		return null;
    
    	// 구매 발주 요청 목록 or 구매 견적 요청 목록
    	final boolean isPurchaseIssueList = mode.equals("issue");
    	
    	if (isPurchaseIssueList) {
    	    // 발주 요청 할 수 있는 목록 조회
    		List<Purchase_I_ab> data = null;
    	    data = purchaseService.getPurchaseListForIssue_request(desc, maker, partnerId, convertedDesignDateBegin, convertedDesignDateEnd, kind, orderNoBase, orderNoExtra);
    	    System.out.println(data);
    	    int pageNo = 0;
    	    String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "02", "04");
    	    ModelAndView mv = new ModelAndView("purchase/estimate_history_list");
    		mv.addObject("data", data);
        	mv.addObject("mode", mode);
        	mv.addObject("desc", desc);
        	mv.addObject("maker", maker);
        	mv.addObject("customer", customer);
        	mv.addObject("DateBegin", DateBegin);
        	mv.addObject("DateEnd", DateEnd);
        	mv.addObject("isupdate", isupdate);
        	mv.addObject("orderNoBase", orderNoBase);
        	mv.addObject("orderNoExtra", orderNoExtra);
        	mv.addObject("pageNo", pageNo);
        	
        	return mv;
    	} else {
    	    // 견적 요청 할 수 있는 목록
    		
    	    List<Purchase_E_ab> data = purchaseService.getPurchaseListForEstimate_request(desc, maker, partnerId, convertedDesignDateBegin, convertedDesignDateEnd, kind, orderNoBase, orderNoExtra);
    	    System.out.println(data);
    	    int pageNo = 0;
    	    String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "02", "02");
    	    ModelAndView mv = new ModelAndView("purchase/estimate_history_list");
    		mv.addObject("data", data);
        	mv.addObject("mode", mode);
        	mv.addObject("desc", desc);
        	mv.addObject("maker", maker);
        	mv.addObject("customer", customer);
        	mv.addObject("DateBegin", DateBegin);
        	mv.addObject("DateEnd", DateEnd);
        	mv.addObject("isupdate", isupdate);
        	mv.addObject("orderNoBase", orderNoBase);
        	mv.addObject("orderNoExtra", orderNoExtra);
        	mv.addObject("pageNo", pageNo);
        	
        	return mv;
    	}

		
	}
	

	// 견적List 수정 팝업
	@RequestMapping("/history/estimate/{estimateRequestId}")
	public ModelAndView Estimate_edit(@PathVariable("estimateRequestId") String estimateRequestId) {
			
		Choice_Estimate Choice_Estimate = new Choice_Estimate();
		Choice_Estimate.setEstimateRequestId(estimateRequestId);
			
			
		List<Purchase_E_ab> data =purchaseService.getPurchaseListForEstimate_ListPOP(Choice_Estimate);
		
		List<String> partnerName = 	purchaseService.selectPartnerName();
		String onePartnerName = purchaseService.IdforNamePartner(purchaseService.sspartnerId(estimateRequestId));
		
		Integer pageNo=0; 
		System.out.println(data);
			
		ModelAndView mv = new ModelAndView("purchase/popup/edit_estimate");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		mv.addObject("partnerName", partnerName);
		mv.addObject("onePartnerName", onePartnerName);
		
		return mv;
	}
	
	
	// 발주 List 수정 팝업
	@RequestMapping("/history/issue/{issueRequestId}")
	public ModelAndView Issue_edit(@PathVariable("issueRequestId") String issueRequestId) {
				
		Choice_Estimate Choice_Estimate = new Choice_Estimate();
		Choice_Estimate.setEstimateRequestId(issueRequestId);
				
				
		List<Purchase_I_ab> data =purchaseService.getPurchaseListForIssue_ListPOP(Choice_Estimate);
			
	
		Integer pageNo=0; 
		System.out.println(data);
				
		ModelAndView mv = new ModelAndView("purchase/popup/edit_issue");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);

			
		return mv;
	}
	//발주 List 상세 보기 팝업
	@RequestMapping("/history/issueView/{issueRequestId}")
	public ModelAndView Issue_view(@PathVariable("issueRequestId") String issueRequestId) {
				
		Choice_Estimate Choice_Estimate = new Choice_Estimate();
		Choice_Estimate.setEstimateRequestId(issueRequestId);
				
				
		List<Purchase_I_ab> data =purchaseService.getPurchaseListForIssue_ListPOP(Choice_Estimate);
			
	
		Integer pageNo=0; 
		System.out.println(data);
				
		ModelAndView mv = new ModelAndView("purchase/popup/edit_view");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);

			
		return mv;
	}
	// 발주 취소
	@RequestMapping("/issue/cancle")
	public String Issue_cancle(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
							   @RequestParam("jobOrderId")Long[] jobOrderId,
							   @RequestParam("issueRequestId")String[] issueRequestId,
							   @RequestParam("cancleReason")String cancleReason) {
		
		for(int i=0;i<jobPurchaseId.length;i++) {
			
			purchaseService.issueCancle(jobOrderId[i], jobPurchaseId[i], issueRequestId[i], cancleReason);
			
		}
					
			
		return "redirect:/purchase/issue/history";	
	}
	
		
	// 견적List 삭제
	@RequestMapping("/history/estimate/deleted")
	public String Estimate_deleted(@RequestParam("jobPurchaseId")Long jobPurchaseId,
								   @RequestParam("estimateRequestId")String estimateRequestId,
								   @RequestParam("jobOrderId")Long jobOrderId) {
				
		purchaseService.EstimateListDeleted(jobPurchaseId, estimateRequestId, jobOrderId);
					
		return "redirect:/purchase/estimate/history";
	}
	
	// 견적List 수정
	@RequestMapping("/history/estimate/updated")
	public String Estimate_updated(
			@RequestParam("jobPurchaseId")Long jobPurchaseId,
			@RequestParam("estimateRequestId")String estimateRequestId,
			@RequestParam("jobOrderId")Long jobOrderId,
			@RequestParam("receiveDate")String receiveDates,
			@RequestParam("estimatedPrice")int estimatedPrice) {
		
		try {
			Date receiveDate = new SimpleDateFormat("yyyy-MM-dd").parse(receiveDates);
			
			EstimateListUpdated EstimateListUpdated = new EstimateListUpdated();
			
			EstimateListUpdated.setJobPurchaseId(jobPurchaseId);
			EstimateListUpdated.setEstimateRequestId(estimateRequestId);
			EstimateListUpdated.setJobOrderId(jobOrderId);
			EstimateListUpdated.setReceiveDate(receiveDate);
			EstimateListUpdated.setEstimatedPrice(estimatedPrice);
			
			purchaseService.EstimateListUpdated(EstimateListUpdated);
			
			
		}catch(Exception ex){ex.printStackTrace();} 			
		
						
		return "redirect:/purchase/estimate/history";
	}
	
	
	// 발주List Hitory 팝업
	
	@RequestMapping("/issuehistory/{job_purchase_id}/{request_id}/{model_no}")
	public ModelAndView issuehistory(@PathVariable("job_purchase_id") Long job_purchase_id, @PathVariable("request_id") String request_id,  @PathVariable("model_no") String model_no) {
				

				
		List<GroupedJobPurchaseEntry_issueHistory> data = null;
		data=purchaseService.selectPurchaseList_History(job_purchase_id, request_id);
		System.out.println(data);
		
		List<jobpurchaseEstimateHistory> data2 = null;
		data2=purchaseService.EstimateHistory(job_purchase_id, model_no);
				
		ModelAndView mv = new ModelAndView("purchase/popup/issue_history");
		mv.addObject("data", data);
		mv.addObject("data2", data2);
		mv.addObject("jobPurchaseId", job_purchase_id);
			
		return mv;
	}
	
	//발주 취소
	@RequestMapping("/deleteIssue")
	public ModelAndView deleteIssue(@RequestParam("jobPurchaseId")String jobPurchaseId) {
				
		Long id = Long.parseLong(jobPurchaseId);
		
		System.out.println("############"+id);
		
		String stage = purchaseService.IsStage_F(id);
		
		System.out.println("&&&&&&&&&&&&&&&"+stage);
		
		if(stage.equals("F")) {
			ModelAndView mv = new ModelAndView("purchase/popup/isStageF");
			return mv;
		}
		else {
			
			ModelAndView mv = new ModelAndView("purchase/popup/IssueDelete");
			mv.addObject("jobPurchaseId", jobPurchaseId);
			return mv;
			
		}
		
			
	}
	
	//발주 취소 동작
	@RequestMapping("/deleteIssue/start")
	public String deleteIssueStart(@RequestParam("jobPurchaseId")String jobPurchaseId, @RequestParam("reason")String reason) {
					
		Long id = Long.parseLong(jobPurchaseId);
		String deleteUserId = loginDongleService.getLoginId();
		String deleteReason = reason.trim();
		
		purchaseService.deleteIssueList(id, deleteUserId, deleteReason);
		
		
		
		

		return "redirect:/purchase/issue/history";
				
	}
		
	
	// 구매 거래처 관리
	
	@RequestMapping("/Purchase_Partner")
	public ModelAndView Purchase_partner(String partnerName, String Code){
		
		Integer pageNo=0; 
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "01", "03");
		ModelAndView mv = new ModelAndView("purchase/Purchase_Partner");
		mv.addObject("partner", purchaseService.searchPartner2(partnerName, Code));
		mv.addObject("pageNo", pageNo);
		mv.addObject("partnerName", partnerName);
		mv.addObject("Code", Code);
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	
	@RequestMapping("/Purchase_Partner/popup/typekind")
	public ModelAndView Purchase_partner_popup_typekind(@RequestParam("param")String param, 
														@RequestParam(value = "param2", defaultValue = "none")String param2) throws Exception{
		
		ModelAndView mv = new ModelAndView("purchase/popup/select_partner_typekind");
		
		System.out.println("*************"+param);
		System.out.println("*************"+param2);
		System.out.println("이거 출력됨?이거 출력됨?이거 출력됨?이거 출력됨?이거 출력됨?이거 출력됨?이거 출력됨?이거 출력됨?이거 출력됨?");
		
		List<JobPartnerTypeNew> JobPartnerTypeKind = purchaseService.selectTypeKind(param);
		
		for(JobPartnerTypeNew one:JobPartnerTypeKind) {
			System.out.println(one.getTypeName());
		}
		
		mv.addObject("result", JobPartnerTypeKind);
		mv.addObject("typeKind", param2);
		
	
		return mv;
	}
	
	
	@RequestMapping("/Purchase_Partner/popup")
	public ModelAndView Purchase_partner_popup(@RequestParam(value = "countPartnerResult", defaultValue = "") String countPartnerResult,
			@RequestParam(value = "typeCode", defaultValue = "none") String typeCode,
			@RequestParam(value = "typeKind", defaultValue = "none") String typeKind){
		
		List<JobPartnerTypeNew> JobPartnerTypeCode = purchaseService.selectTypeCode();
		List<billingMonth> billingMonth = purchaseService.selectBillingAfter();
		List<billingDay> billingDay = purchaseService.selectBillingDay();
		
		ModelAndView mv = new ModelAndView("purchase/popup/purchase_partner_ins");
		mv.addObject("countPartnerResult", countPartnerResult);
		mv.addObject("typeCode", typeCode);
		mv.addObject("typeKind", typeKind);
		mv.addObject("alltypecode", JobPartnerTypeCode);
		mv.addObject("billingMonth", billingMonth);
		mv.addObject("billingDay", billingDay);
		
	
		return mv;
	}
	@RequestMapping("/Purchase_Partner/popup/insert")
	public String Purchase_partner_popup_insert(
			@RequestParam("id")String id,
			@RequestParam("partnerName")String partnerName,
			@RequestParam("corporateNum1")String corporateNum1,
			@RequestParam("corporateNum2")String corporateNum2,
			@RequestParam("corporateNum3")String corporateNum3,
			@RequestParam("corporateAddr1")String corporateAddr1,
			@RequestParam("corporateAddr2")String corporateAddr2,
			@RequestParam("personPhone1")String personPhone1,
			@RequestParam("personPhone2")String personPhone2,
			@RequestParam("personPhone3")String personPhone3,
			@RequestParam("personName")String personName,
			@RequestParam("personPosition")String personPosition,
			@RequestParam("personMail")String personMail,
			@RequestParam("billingName")String billingName,
			@RequestParam("corporatePhone")String corporatePhone,
			@RequestParam("corporateMail")String corporateMail,
			@RequestParam("billingAfter")String billingAfter,
			@RequestParam("billingDay")String billingDay,
			@RequestParam("bankName")String bankName,
			@RequestParam("bankAccount")String bankAccount,
			@RequestParam("note")String note){
		
			JobPartner JobPartner = new JobPartner();
			
		
			
			try {
				
				String typeCode=id.substring(0,1);
				String typeKind=id.substring(1,2);
				
				
				
				JobPartner.setId(id);
				JobPartner.setPartnerName(partnerName);
				JobPartner.setTypeCode(typeCode);
				JobPartner.setTypeKind(typeKind);
				
				String corporateNum=corporateNum1.trim()+corporateNum2.trim()+corporateNum3.trim();
				JobPartner.setCorporateNum(corporateNum);
				
				String corporateAddr = corporateAddr1.trim()+" "+corporateAddr2.trim();
				JobPartner.setCorporateAddr(corporateAddr);
				
				String personPhone=personPhone1.trim()+personPhone2.trim()+personPhone3.trim();
				JobPartner.setPersonPhone(personPhone);
				JobPartner.setPersonName(personName);
				JobPartner.setPersonPosition(personPosition);
				JobPartner.setPersonMail(personMail);
				
				if(billingName.isEmpty()) {JobPartner.setBillingName(null);}
				else {JobPartner.setBillingName(billingName);}
				
				JobPartner.setCorporatePhone(corporatePhone);
				JobPartner.setCorporateMail(corporateMail);
				
				if(billingAfter.equals("null")) {JobPartner.setBillingAfter(null);}
				else {JobPartner.setBillingAfter(billingAfter);}
				
				if(billingDay.equals("null")) {JobPartner.setBillingDay(null);}
				else {JobPartner.setBillingDay(billingDay);}
				
				if(bankName.isEmpty()) {JobPartner.setBankName(null);}
				else {JobPartner.setBankName(bankName);}
				
				if(bankAccount.isEmpty()) {JobPartner.setBankAccount(null);}
				else {JobPartner.setBankAccount(bankAccount);}
				
				if(note.isEmpty()) {JobPartner.setNote(null);}
				else {JobPartner.setNote(note);}
				
				
				purchaseService.insertPartner(JobPartner);

				
			}catch(Exception ex){ex.printStackTrace();}
	
	
		return "redirect:/purchase/Purchase_Partner";
	}
	
	
	@RequestMapping("/Purchase_Partner/popup/autoPartner")
	public String Purchase_partner_popup_autoPartner(@RequestParam("typeCode") String typeCode, @RequestParam("typeKind")String typeKind, RedirectAttributes RedirectAttributes){
		
		
	
		String PartnerType = typeCode.trim()+typeKind.trim();
		
		JobPartnerType JobPartnerType = new JobPartnerType();
		JobPartnerType.setTypeCode(typeCode);
		JobPartnerType.setTypeKind(typeKind);
		
		
		int countPartner=purchaseService.jobPartnerTypeNextValue(JobPartnerType)+1;
		
		JobPartnerType.setNextValue(countPartner);
		
		purchaseService.jobPartnerTypeUP(JobPartnerType);

		if(countPartner<10) {
			String countPartnerResult = PartnerType.trim()+"00"+String.valueOf(countPartner).trim();
			RedirectAttributes.addAttribute("countPartnerResult",countPartnerResult );
			
		}
		else if(10<=countPartner && countPartner<100) {
			String countPartnerResult = PartnerType.trim()+"0"+String.valueOf(countPartner).trim();
			RedirectAttributes.addAttribute("countPartnerResult",countPartnerResult );
		}
		else if(100<=countPartner) {
			String countPartnerResult = PartnerType.trim()+String.valueOf(countPartner).trim();
			RedirectAttributes.addAttribute("countPartnerResult",countPartnerResult );
		}
		
		RedirectAttributes.addAttribute("typeCode",typeCode);
		RedirectAttributes.addAttribute("typeKind",typeKind);
	
	
		return "redirect:/purchase/Purchase_Partner/popup";
	}
	
	@RequestMapping("/Purchase_Partner/popup_Ed/{id}")
	public ModelAndView Purchase_partner_popup_Ed(@PathVariable("id") String id){
		
		ModelAndView mv = new ModelAndView("purchase/popup/purchase_partner_Edit");
		List<billingMonth> billingMonth = purchaseService.selectBillingAfter();
		List<billingDay> billingDay = purchaseService.selectBillingDay();
		
		List<JobPartner> partner = purchaseService.selectOnePartnerList(id);
		
		mv.addObject("partner", partner);
		mv.addObject("billingMonth", billingMonth);
		mv.addObject("billingDay", billingDay);
	
		return mv;
	}
	
	@RequestMapping("/Purchase_Partner/popup_Ed/update")
	public String Purchase_partner_popupEd_update(
			@RequestParam("id")String id,
			@RequestParam("partnerName")String partnerName,
			@RequestParam("corporateNum")String corporateNum,
			@RequestParam("corporateAddr")String corporateAddr,
			@RequestParam("personPhone")String personPhone,
			@RequestParam("personName")String personName,
			@RequestParam("personPosition")String personPosition,
			@RequestParam("personMail")String personMail,
			@RequestParam("billingName")String billingName,
			@RequestParam("corporatePhone")String corporatePhone,
			@RequestParam("corporateMail")String corporateMail,
			@RequestParam("billingAfter")String billingAfter,
			@RequestParam("billingDay")String billingDay,
			@RequestParam("bankName")String bankName,
			@RequestParam("bankAccount")String bankAccount,
			@RequestParam("note")String note){
		
			JobPartner JobPartner = new JobPartner(); 
			
			try {
				
				JobPartner.setId(id);
				JobPartner.setPartnerName(partnerName);
				JobPartner.setCorporateNum(corporateNum);
				JobPartner.setCorporateAddr(corporateAddr);
				JobPartner.setPersonPhone(personPhone);
				JobPartner.setPersonName(personName);
				JobPartner.setPersonPosition(personPosition);
				JobPartner.setPersonMail(personMail);
				
				if(billingName.isEmpty()) {JobPartner.setBillingName(null);}
				else {JobPartner.setBillingName(billingName);}
				
				JobPartner.setCorporatePhone(corporatePhone);
				JobPartner.setCorporateMail(corporateMail);
				
				if(billingAfter.equals("null")) {JobPartner.setBillingAfter(null);}
				else {JobPartner.setBillingAfter(billingAfter);}
				
				if(billingDay.equals("null")) {JobPartner.setBillingDay(null);}
				else {JobPartner.setBillingDay(billingDay);}
				
				if(bankName.isEmpty()) {JobPartner.setBankName(null);}
				else {JobPartner.setBankName(bankName);}
				
				if(bankAccount.isEmpty()) {JobPartner.setBankAccount(null);}
				else {JobPartner.setBankAccount(bankAccount);}
				
				if(note.isEmpty()) {JobPartner.setNote(null);}
				else {JobPartner.setNote(note);}
				
				
				System.out.println("쿼리 전 쿼리 전 쿼리 전 쿼리 전 쿼리 전 쿼리 전 쿼리 전쿼리 전 쿼리 전");
				purchaseService.EditPartner(JobPartner);
				System.out.println("쿼리 후 쿼리 후 쿼리 후 쿼리 후 쿼리 후 쿼리 후 쿼리 후 쿼리 후 쿼리 후");

				
			}catch(Exception ex){ex.printStackTrace();System.out.println("문제 문제 문제 문제 문제 문제 문제 문제 문제 문제");}
	
	
		return "redirect:/purchase/Purchase_Partner";
	}
	
	//입고품 등록
	@RequestMapping("/Warehousing_ins")
	public ModelAndView Warehousing_ins(){
		
		ModelAndView mv = new ModelAndView("purchase/Warehousing_ins");
		
		List<Warehousing> data = purchaseService.getWarehousing();
		mv.addObject("data", data);
		
		
		return mv;
	}
	
	//입고품 입고확인 popup
	@RequestMapping("/Warehousing_ins/pop/{id}")
	public ModelAndView Warehousing_ins_pop(@PathVariable("id") String id){
		
		
		System.out.println("**************"+id);
		ModelAndView mv = new ModelAndView("purchase/popup/Warehousing_Insert");
		List<Warehousing_pop> data = purchaseService.getWarehousing_pop(id);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("id", id);
		
		
		return mv;
	}
	
	//입고품 입고확인 insert
	@RequestMapping("/Warehousing_ins/pop/ins")
	public String Warehousing_pop_Insert(
			@RequestParam("BuyNum")String BuyNum,
			@RequestParam("warehousing_quantity")int warehousing_quantity,
			@RequestParam("out_quantity")int out_quantity){
		
			warehousing_list warehousing_list = new warehousing_list();
			
			
			
			try {
				warehousing_list.setBuyNum(BuyNum);
				warehousing_list.setWarehousing_quantity(warehousing_quantity);
				warehousing_list.setOut_quantity(out_quantity);
				warehousing_list.setOut_reason(null);
				warehousing_list.setOut_person(null);
				
				purchaseService.insertWarehousing(warehousing_list);
				purchaseService.updateStage(BuyNum);
		
			}catch(Exception ex){ex.printStackTrace();}
	
	
		return "redirect:/purchase/Warehousing_ins";
	}
	
	
	//견적리스트 발송전REAL
	@RequestMapping("/pre/{activePartnerId}/{jobOrderId}")
	public ModelAndView internalOutsourcingOrder_issue(@PathVariable("activePartnerId") String partnerId, @PathVariable("jobOrderId") String rawJobOrderId) throws Exception{
		
		String[] jobOrderIdTokens = rawJobOrderId.split(",");
		List<Long> jobOrderIds = new ArrayList(jobOrderIdTokens.length);
		for(int i=0;i<jobOrderIdTokens.length;i++){
			if(StringUtils.isEmpty(jobOrderIdTokens[i]))
				continue;
			jobOrderIds.add( Long.parseLong(jobOrderIdTokens[i]) );
			
			System.out.println("@@@@@@@@"+jobOrderIdTokens[i]);
		}
		
		
		Collection<List<PreEstimateRequest>> result2 = purchaseService.preEstimateRequestTargets(partnerId, jobOrderIds.stream().toArray(Long[]::new)  );
		List<String> partnerName = 	purchaseService.selectPartnerName();
		String onePartnerName = purchaseService.IdforNamePartner(partnerId);
		System.out.println("@@@@@@@@@@"+result2);
		ModelAndView mv = new ModelAndView("purchase/popup/estimateSendPreNew");
		Integer pageNo=0; 
		mv.addObject("data", result2);
		mv.addObject("partnerName", partnerName);
		mv.addObject("onePartnerName", onePartnerName);
		mv.addObject("partnerId", partnerId);
		mv.addObject("rawJobOrderId", rawJobOrderId);
		mv.addObject("pageNo", pageNo);
			
		return mv;
		
		
	}
	
	
	//신규발주리스트 발송전REAL
	@RequestMapping("/issue/pre/{activePartnerId}/{jobOrderId}")
	public ModelAndView internalOutsourcingOrder_issuepre(@PathVariable("activePartnerId") String partnerId, @PathVariable("jobOrderId") String rawJobOrderId) throws Exception{
			
		String[] jobOrderIdTokens = rawJobOrderId.split(",");
		List<Long> jobOrderIds = new ArrayList(jobOrderIdTokens.length);
		
		purchaseService.deletedTrashData();
		for(int i=0;i<jobOrderIdTokens.length;i++){
			if(StringUtils.isEmpty(jobOrderIdTokens[i]))
				continue;
			jobOrderIds.add( Long.parseLong(jobOrderIdTokens[i]) );
			
			System.out.println("@@@@@@@@"+jobOrderIdTokens[i]);
		}
			
		
		Collection<List<Purchase_I_ab>> result2 = purchaseService.preIssueRequestTargets(partnerId, jobOrderIds.stream().toArray(Long[]::new)  );
		
		
		
		List<String> partnerName = 	purchaseService.selectPartnerName();
		String onePartnerName = purchaseService.IdforNamePartner(partnerId);
		System.out.println("@@@@@@@@@@"+result2);
		ModelAndView mv = new ModelAndView("purchase/popup/IssueSendPreReal");
		Integer pageNo=0; 
		mv.addObject("data", result2);
		mv.addObject("partnerName", partnerName);
		mv.addObject("onePartnerName", onePartnerName);
		mv.addObject("partnerId", partnerId);
		mv.addObject("rawJobOrderId", rawJobOrderId);
		mv.addObject("pageNo", pageNo);
				
		return mv;
			
			
	}
	
	
	//견적리스트 발송전
	@RequestMapping("/estimate/pre/{activePartnerId}/{jobOrderId}")
	public ModelAndView internalOutsourcingOrder_estimate(@PathVariable("activePartnerId") String partnerId, @PathVariable("jobOrderId") String rawJobOrderId) throws Exception{
			
		String[] jobOrderIdTokens = rawJobOrderId.split(",");
		List<Long> jobOrderIds = new ArrayList(jobOrderIdTokens.length);
		
		purchaseService.deletedTrashData2();
		for(int i=0;i<jobOrderIdTokens.length;i++){
			if(StringUtils.isEmpty(jobOrderIdTokens[i]))
				continue;
			jobOrderIds.add( Long.parseLong(jobOrderIdTokens[i]) );
				
			System.out.println("@@@@@@@@"+jobOrderIdTokens[i]);
		}
		
	

				
			Collection<List<PreEstimateRequest>> result2 = purchaseService.preEstimateRequestTargets(partnerId, jobOrderIds.stream().toArray(Long[]::new)  );
			List<String> partnerName = 	purchaseService.selectPartnerName();
			String onePartnerName = purchaseService.IdforNamePartner(partnerId);
			System.out.println("@@@@@@@@@@"+result2);
			ModelAndView mv = new ModelAndView("purchase/popup/estimateSendPreNew");
			Integer pageNo=0; 
			mv.addObject("data", result2);
			mv.addObject("partnerName", partnerName);
			mv.addObject("onePartnerName", onePartnerName);
			mv.addObject("partnerId", partnerId);
			mv.addObject("rawJobOrderId", rawJobOrderId);
			mv.addObject("pageNo", pageNo);
				
			return mv;
				
		
			
	}
	
	
	//발주 재고사용시 modelNo 자재 검색
	@RequestMapping("/stockUse/{modelNo}/{No}")
	public ModelAndView stockUse_ModelNo(@PathVariable("modelNo") String modelNo, @PathVariable("No") int No) throws Exception{
		
		
		
		List<stock> result = purchaseService.selectstock(modelNo);
		
		
		final boolean stockIsEmpty = result.isEmpty();
		final String IsEmpty = stockIsEmpty ? "Y":"N";
		
		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("purchase/popup/stockUse");
		mv.addObject("data", result);
		mv.addObject("IsEmpty", IsEmpty);
		mv.addObject("modelNo", modelNo);
		mv.addObject("No", No);
		mv.addObject("pageNo", pageNo);
		return mv;
	}
	
	
	
	//입고품 등록 List
	@RequestMapping("/innerStock")
	public ModelAndView innerStock(String customer, String DateBegin, String DateEnd, String roundRobinYN) {
		
		
		// 날짜 처리
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
				
		String partnerId;
		if(StringUtils.isEmpty(customer)) {
					
			partnerId=null;
					
		}else {
			partnerId = purchaseService.selectPartnerId(customer);
		}
				
		
						
		List<InnerStockModel> InnerStockModel = null;
		InnerStockModel = purchaseService.innerStockList(partnerId, convertedDesignDateBegin, convertedDesignDateEnd, roundRobinYN);
		
		int pageNo = 0;
		ModelAndView mv = new ModelAndView("purchase/innerStock");
		mv.addObject("data", InnerStockModel);
		mv.addObject("customer", customer);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		mv.addObject("roundRobinYN", roundRobinYN);
		mv.addObject("pageNo",pageNo);
		return mv;
					
	}
	
	//입고품 등록 팝업
	@RequestMapping("/innerStock/popList/{issueRequestId}")
	public ModelAndView innerStock_pop(@PathVariable("issueRequestId") String issueRequestId
										) throws Exception{
							
		List<InnerStockModel> result = null;
		
	
	
		result = purchaseService.innerStockpop(issueRequestId);
		
		//List<String> userName = purchaseService.userSurch();
		
		List<String> deptName = purchaseService.deptSurch();
		
		//Long jobPurchaseId = purchaseService.jobpurchaseIDselect(requestId);
		
		Integer pageNo=0; 
		
		ModelAndView mv = new ModelAndView("purchase/popup/innerStockpopup");
		mv.addObject("data", result);
		//mv.addObject("jobPurchaseId", jobPurchaseId);
		mv.addObject("pageNo", pageNo);
		mv.addObject("deptName", deptName);
		
		return mv;
						
	}
	
	
	//입고품 반품 팝업
	@RequestMapping("/stockOut/{jobPurchaseId}/{issueRequestId}/{jobOrderId}/{No}")
	public ModelAndView outStock_pop(@PathVariable("jobPurchaseId") Long jobPurchaseId ,@PathVariable("issueRequestId") String issueRequestId,
			@PathVariable("jobOrderId") Long jobOrderId,@PathVariable("No") int No) throws Exception{
								
	
			
		List<InnerStockModel> result = purchaseService.outStockpop(jobPurchaseId, issueRequestId, jobOrderId);
		List<String> userName = purchaseService.userSurch();
			
		ModelAndView mv = new ModelAndView("purchase/popup/stockOut");
		mv.addObject("data", result);
		mv.addObject("No", No);
		mv.addObject("userName", userName);
			
		return mv;
							
	}
	
	//반품 후 재구매 등록
	@RequestMapping("/stockReBuy")
	public String outStockReBuy(@RequestParam("jobPurchaseId") Long jobPurchaseId,
							  @RequestParam("issueRequestId") String issueRequestId,
							  @RequestParam("jobOrderId") Long jobOrderId,
							  @RequestParam("outQuantity") int outQuantity,
							  @RequestParam("outUser") String outUser,
							  @RequestParam("outReason") String reason) throws Exception{
									
		
		
		
		JobPurchaseDB JobPurchaseDB = new JobPurchaseDB();
		
		JobPurchaseDB = purchaseService.selectOneJobPurchase(jobPurchaseId);
		JobPurchaseDB.setQuantity(outQuantity);
		JobPurchaseDB.setSpare(0);
		
	
		
		purchaseService.updateJobPurchase(jobPurchaseId, outQuantity, outUser, reason);
		
		System.out.println("#############업데이트 jobPurchase 완료!!!");
		
		purchaseService.reBuyreg(JobPurchaseDB);
		
		String msg = "redirect:/purchase/innerStock/popList/"+issueRequestId;
		
		
		return msg;
			
	}
	
	
	//입고 및 반품 처리
	@RequestMapping("/innerAndOut")
	public String innerandout(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
							  @RequestParam("wareHousingQuantity") int[] wareHousingQuantity,
							  @RequestParam("outUser") String[] outUsers,
							  @RequestParam("outQuantity") int[] outQuantity,
							  @RequestParam("outReason") String[] outReasons,
							  @RequestParam("issueRequestId") String[] issueRequestIds,
							  @RequestParam("jobOrderId") Long[] jobOrderIds,
							  @RequestParam("modelNo") String[] modelNoes,
							  @RequestParam(value="receiverUsr", defaultValue="") String receiverUsr) throws Exception{
		
		
		if(receiverUsr.isEmpty() || receiverUsr.equals("")||receiverUsr.equals(" ")){
			
			StatementIns statementIns = new StatementIns(); 
			
			String[] requestIdTokens = issueRequestIds[0].split("-");
			
			statementIns.setRequestId(issueRequestIds[0]);
			statementIns.setPartnerId(requestIdTokens[1]);
			statementIns.setJobOrderId(jobOrderIds[0]);
			
			Long StatementId = purchaseService.addStatement(statementIns);
			
			String device = "";
			
			for(int i=0;i<wareHousingQuantity.length;i++) {
				
				String outUser;
				String outReason;
				
				if(outUsers[i].isEmpty() || outUsers[i].equals("")||outUsers[i].equals(" ")) {
					
					outUser = outUsers[i]; 
					
				}
				else {
					outUser = purchaseService.userCodeSurch(outUsers[i]);
				}
				
				if(outReasons[i].isEmpty() || outReasons[i].equals("")||outReasons[i].equals(" ")) {
					
					outReason = outReasons[i]; 
					
				}
				else {
					outReason = outReasons[i];
				}
			
				
				innerAndoutModel innerAndoutModel = new innerAndoutModel();
				StatementDetail StatementDetail = new StatementDetail();
				
				
				innerAndoutModel.setJobPurchaseId(jobPurchaseId[i]);
				innerAndoutModel.setWarehousingQuantity(wareHousingQuantity[i]);
				innerAndoutModel.setOutUser(outUser);
				innerAndoutModel.setOutQuantity(outQuantity[i]);
				innerAndoutModel.setOutReason(outReason);
				innerAndoutModel.setWarehousingUser(loginDongleService.getLoginId());
		
				
				StatementDetail.setIssueId(StatementId);
				StatementDetail.setJobPurchaseId(jobPurchaseId[i]);
				
	
				purchaseService.innerAndOutUpdate(innerAndoutModel);
				purchaseService.insertStatementDetail(StatementDetail);
				
				device = device + modelNoes[i] + ", ";
			}
			
			try {
				
				noticeMessageService.addInnerStockNoticeMsg("A", "A", jobOrderIds[0], "구매품이 입고 되었습니다.", device);
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(jobOrderIds[0])+"]", "Inner Stock");
				
			}catch(Exception e) {
				System.out.println("notice 등록 에러");
			}
			
			
		}
		
		else {
			
			StatementIns statementIns = new StatementIns(); 
			
			String[] requestIdTokens = issueRequestIds[0].split("-");
			
			statementIns.setRequestId(issueRequestIds[0]);
			statementIns.setPartnerId(requestIdTokens[1]);
			statementIns.setJobOrderId(jobOrderIds[0]);
			
			Long StatementId = purchaseService.addStatement(statementIns);
			
			String device = "";
			
			for(int i=0;i<wareHousingQuantity.length;i++) {
				
				String outUser;
				String outReason;
				
				if(outUsers[i].isEmpty() || outUsers[i].equals("")||outUsers[i].equals(" ")) {
					
					outUser = outUsers[i]; 
					
				}
				else {
					outUser = purchaseService.userCodeSurch(outUsers[i]);
				}
				
				if(outReasons[i].isEmpty() || outReasons[i].equals("")||outReasons[i].equals(" ")) {
					
					outReason = outReasons[i]; 
					
				}
				else {
					outReason = outReasons[i];
				}
			
				
				innerAndoutModel innerAndoutModel = new innerAndoutModel();
				StatementDetail StatementDetail = new StatementDetail();
				
				innerAndoutModel.setJobPurchaseId(jobPurchaseId[i]);
				innerAndoutModel.setWarehousingQuantity(wareHousingQuantity[i]);
				innerAndoutModel.setOutUser(outUser);
				innerAndoutModel.setOutQuantity(outQuantity[i]);
				innerAndoutModel.setOutReason(outReason);
				innerAndoutModel.setWarehousingUser(loginDongleService.getLoginId());
				innerAndoutModel.setPassUsr(loginDongleService.getLoginId());
				innerAndoutModel.setReceiverUsr(purchaseService.deptCode(receiverUsr));
				
				
				TransitionPurchase TransitionPurchase = new TransitionPurchase();
				
				TransitionPurchase.setPreId(0L);
				TransitionPurchase.setJobOrderId(jobOrderIds[i]);
				TransitionPurchase.setJobPurchaseId(jobPurchaseId[i]);
				TransitionPurchase.setKindPurchase("P");
				TransitionPurchase.setQuantity(wareHousingQuantity[i]);
				TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
				TransitionPurchase.setReceiveDept(purchaseService.deptCode(receiverUsr));
				
				
				
			
				
				StatementDetail.setIssueId(StatementId);
				StatementDetail.setJobPurchaseId(jobPurchaseId[i]);
				
	
				purchaseService.innerAndOutUpdateO(innerAndoutModel);
				purchaseService.insertStatementDetail(StatementDetail);
				purchaseService.insertTransitionpurchase(TransitionPurchase);
				
			
				device = device + modelNoes[i] + ", ";
			
			
		}
			
			try {
				
				noticeMessageService.addInnerStockNoticeMsg("A", "A", jobOrderIds[0], "구매품이 입고 되었습니다.", device);
				pushAlarmService.orderStartService("["+jobOrderService.selectJobOrderFromPush(jobOrderIds[0])+"]", "Inner Stock");
				
			}catch(Exception e) {
				System.out.println("notice 등록 에러");
			}
		
		}
		
	
			
		return "redirect:/purchase/innerStock";
							
	}
	

	//입고품 등록 List
	@RequestMapping("/innerstockList")
	public ModelAndView innerstockList(
			 String customer, String DateBegin, String DateEnd, String stage) {
		
		
		// 날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;
		
		if(StringUtils.isEmpty(stage)) {
			stage = "F";
		}

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
						
		String partnerId;
		if(StringUtils.isEmpty(customer)) {
							
			partnerId=null;
							
		}else {
			partnerId = purchaseService.selectPartnerId(customer);
		}
		
		
		
							
		List<InnerStockModel> InnerStockModel = null;
		
		InnerStockModel = purchaseService.innerstockListModel(partnerId, convertedDesignDateBegin, convertedDesignDateEnd, stage);
		
		int pageNo = 0;
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "02", "06");
		ModelAndView mv = new ModelAndView("purchase/innerStockList");
		
		mv.addObject("data", InnerStockModel);
		mv.addObject("stage", stage);
    	mv.addObject("customer", customer);
    	mv.addObject("DateBegin", DateBegin);
    	mv.addObject("DateEnd", DateEnd);
    	mv.addObject("isupdate", isupdate);
    	mv.addObject("pageNo", pageNo);	
		return mv;
						
	}
	
	//재고등록 리스트 입고 팝업
	@RequestMapping("/innerstockList/popList/{issueRequestId}/{stage}")
	public ModelAndView innerStockList_popNew(@PathVariable("issueRequestId") String issueRequestId,
											  @PathVariable("stage") String stage
											) throws Exception{
								
		List<InnerStockModel> result = null;
			
		
		
		result = purchaseService.innerListStockpop(issueRequestId, stage);
		
		System.out.println("###########################################"+result);
			
		//List<String> userName = purchaseService.userSurch();
		
		 List<DeptData> deptName = purchaseService.deptSurch2();
			
		//Long jobPurchaseId = purchaseService.jobpurchaseIDselect(requestId);
			
		Integer pageNo=0; 
			
		ModelAndView mv = new ModelAndView("purchase/popup/innerStockListpopjsp");
		mv.addObject("data", result);
		//mv.addObject("jobPurchaseId", jobPurchaseId);
		mv.addObject("pageNo", pageNo);
		mv.addObject("deptName", deptName);
		mv.addObject("stage", stage);
			
		return mv;
							
	}
	
	
	//입고 리스트 불출동착
	@RequestMapping("/ListReceivePass")
	public String ListReceivePass(@RequestParam("jobPurchaseId") Long[] jobPurchaseIds,
										@RequestParam("receiverUsr") String receiverUsr,
										@RequestParam("warehousingQuantity") int warehousingQuantity
												) throws Exception{
									
		for(int i=0;i<jobPurchaseIds.length;i++) {
			purchaseService.innerUpdateList(jobPurchaseIds[i], receiverUsr, loginDongleService.getLoginId());
			
			Long jobOrderId = purchaseService.selectOrderIdFrompurchase(jobPurchaseIds[i]);
			
			TransitionPurchase TransitionPurchase = new TransitionPurchase();
			
			TransitionPurchase.setJobOrderId(jobOrderId);
			TransitionPurchase.setPreId(0L);
			TransitionPurchase.setJobPurchaseId(jobPurchaseIds[i]);
			TransitionPurchase.setKindPurchase("P");
			TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
			TransitionPurchase.setReceiveDept(receiverUsr);
			TransitionPurchase.setQuantity(warehousingQuantity);
			
			purchaseService.insertTransitionpurchase(TransitionPurchase);
			
		}
				

				
		return "redirect:/purchase/innerstockList";
								
	}
	
	
	
	
	
	//입고품 재고등록 page
	@RequestMapping("/insertStock/{jobPurchaseId}/{issueRequestId}")
	public ModelAndView insertStock(@PathVariable("jobPurchaseId") Long jobPurchaseId, @PathVariable("issueRequestId") String issueRequestId, @PathVariable("jobOrderId") String jobOrderId) {
								
		List<innerstockListModel> innerstockListModel = null;
			
		innerstockListModel = purchaseService.viewInsertstock(jobPurchaseId, issueRequestId);
		List<String> userName = purchaseService.userSurch();
	
		ModelAndView mv = new ModelAndView("purchase/popup/stockInsertPage");
			
		mv.addObject("data", innerstockListModel);
		mv.addObject("userName", userName);
		return mv;
							
	}
	
	//입고품 재고등록 동작
	@RequestMapping("/insertStockMotion")
	public String insertStockMotion(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
			  						@RequestParam("issueRequestId") String[] issueRequestId,
			  						@RequestParam("modelNo") String[] modelNo,
			  						@RequestParam("maker") String[] maker,
			  						@RequestParam("description") String[] description,
			  						@RequestParam("issuePrice") int[] issuePrice,
			  						@RequestParam("stockUseQuantity") int[] stockUseQuantity,
			  						@RequestParam("registrationReason") String registrationReason,
			  						@RequestParam("stockAble") String stockAble) throws Exception {
		
		for(int i=0;i<jobPurchaseId.length;i++) {
			System.out.println("############################--jobPurchaseId["+i+"] = "+jobPurchaseId[i]);
			System.out.println("############################--issueRequestId["+i+"] = "+issueRequestId[i]);
			System.out.println("############################--modelNo["+i+"] = "+modelNo[i]);
			System.out.println("############################--maker["+i+"] = "+maker[i]);
			System.out.println("############################--description["+i+"] = "+description[i]);
			System.out.println("############################--issuePrice["+i+"] = "+issuePrice[i]);
			System.out.println("############################--stockQuantity["+i+"] = "+stockUseQuantity[i]);
			System.out.println("############################--registrationReason["+i+"] = "+registrationReason);
			System.out.println("############################--stockAble["+i+"] = "+stockAble);
		}
	
		
		for(int i=0;i<jobPurchaseId.length;i++) {
			
		
			stock stockIsnull = null;
			stockIsnull = purchaseService.stockIsnull(modelNo[i]);
			
			
	
		
		
			if(stockIsnull == null) {
			
				stock stock = new stock();
				stock.setJobPurchaseId(jobPurchaseId[i]);
				stock.setRequestId(issueRequestId[i]);
				stock.setModelNo(modelNo[i]);
				stock.setMaker(maker[i]);
				stock.setDescription(description[i]);
				stock.setIssuePrice(issuePrice[i]);
				stock.setQuantity(stockUseQuantity[i]);
				stock.setAbQuantity(0);
				stock.setOutQuantity(0);
				stock.setDeleted("N");
				purchaseService.insertStockList(stock);
			
				stockInHistroy stockInHistroy = new stockInHistroy();
				stockInHistroy.setStockID(purchaseService.stockIdSelect(stock.getModelNo()));
				stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
				stockInHistroy.setRegistrationReason(registrationReason);
				stockInHistroy.setStockAble(stockAble);
				stockInHistroy.setInQuantity(stockUseQuantity[i]);
				purchaseService.StockInHistoryIns(stockInHistroy);
			
			
			
			}else {
			
				stockInHistroy stockInHistroy = new stockInHistroy();
				stockInHistroy.setStockID(purchaseService.stockIdSelect(modelNo[i]));
				stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
				stockInHistroy.setRegistrationReason(registrationReason);
				stockInHistroy.setStockAble(stockAble);
				stockInHistroy.setInQuantity(stockUseQuantity[i]);
				purchaseService.StockInHistoryIns(stockInHistroy);
			
				int remindQt = purchaseService.selectStockQt(modelNo[i]);
				int quantity = remindQt + stockUseQuantity[i];
				purchaseService.UpdateStock(modelNo[i], quantity);
			
			
			}
		
		
			
		
		}
	
	
		return "redirect:/purchase/innerstockList";
								
	}
	
	
	//재고 List
	@RequestMapping("/stockRealList")
	public ModelAndView stockRealList(String kind, String CodeNO,String modelNo, String maker, String desc, String DateBegin, String DateEnd) {
		
		// 날짜 처리
		if (StringUtils.isEmpty(DateBegin))
			DateBegin = null;
		if (StringUtils.isEmpty(DateEnd))
			DateEnd = null;
		if(StringUtils.isEmpty(CodeNO)) {
			CodeNO = null;
		}else {
			CodeNO = CodeNO.replace("J", "").trim();
		}
		
	

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
		
		
		int pageNo = 0;
		
							
		List<stockRealListModel> stockRealListModel = null;
		stockRealListModel = purchaseService.stockRealListSelect(kind ,CodeNO, modelNo, maker, desc, convertedDesignDateBegin, convertedDesignDateEnd);
		
		Long sumStockAll = purchaseService.sumStockAllList();
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "03", "01");
		String isPQ = "N";
		
		if(myMenuService.isPQroundrobin(loginDongleService.getLoginId()).equals("PQ")) {
			isPQ = "Y";
		}
		
		
		
		ModelAndView mv = new ModelAndView("purchase/stockRealList");
		
		mv.addObject("data", stockRealListModel);
		mv.addObject("CodeNO", CodeNO);
		mv.addObject("modelNo", modelNo);
		mv.addObject("maker", maker);
		mv.addObject("desc", desc);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		mv.addObject("isupdate", isupdate);
		mv.addObject("pageNo", pageNo);
		mv.addObject("sumStockAll", sumStockAll);
		mv.addObject("isPQ", isPQ);
		return mv;
						
	}
	
	//재고 List pop
	@RequestMapping("/stockRealList/pop/{id}")
	public ModelAndView stockRealListpop(@PathVariable("id") Long id) {
								
		List<stockRealListModel> stockRealListModel = null;
		stockRealListModel = purchaseService.stockRealListSelectId(id);
		List<String> userName = purchaseService.userSurch();
		List<String> deptName = purchaseService.deptSurch();
		List<StockListOrderNo> orderNodata = purchaseService.searchOrderNo();
			
		ModelAndView mv = new ModelAndView("purchase/popup/stocRealkListpop");
		mv.addObject("data", stockRealListModel);
		mv.addObject("userName", userName);
		mv.addObject("deptName", deptName);
		mv.addObject("orderNodata", orderNodata);
		return mv;
							
	}
	
	//재고 수정 팝업
	@RequestMapping("/stockRealEd/popEd/{id}")
	public ModelAndView stockRealEdpop(@PathVariable("id") Long id) {
								
		List<stockRealListModel> stockRealListModel = null;
		stockRealListModel = purchaseService.stockRealListSelectId(id);
		List<String> userName = purchaseService.userSurch();
		List<String> deptName = purchaseService.deptSurch();
		List<StockListOrderNo> orderNodata = purchaseService.searchOrderNo();
			
		ModelAndView mv = new ModelAndView("purchase/popup/stockRealEdpop");
		mv.addObject("data", stockRealListModel);
		mv.addObject("userName", userName);
		mv.addObject("deptName", deptName);
		mv.addObject("orderNodata", orderNodata);
		return mv;
							
	}
	
	//재고 List 수정 동작
	@RequestMapping("/stockEdReal")
	public String stockEdReal(@RequestParam("id")String id,
								     @RequestParam("maker") String maker,
								     @RequestParam("description") String description,
								     @RequestParam("modelNo") String modelNo,
								     @RequestParam("remind") int remind,
								     @RequestParam("issuePrice") int issuePrice
								     )throws Exception {
		
		String sid = id.replace("J", "");

		Long rid = Long.parseLong(sid.trim());
		
		
		StockIns stockIns = new StockIns();
		
		stockIns.setId(rid);
		stockIns.setMaker(maker);
		stockIns.setDescription(description);
		stockIns.setModelNo(modelNo);
		stockIns.setQuantity(remind);
		stockIns.setIssuePrice(issuePrice);
		
		purchaseService.setStockListUdate(stockIns);

		
	
		return "redirect:/purchase/stockRealList";
								
	}
	
	//재고 List 불출 등록
	@RequestMapping("/stockOutReal")
	public String stockOutReal(@RequestParam("id")String id,
								     @RequestParam("passDate") String passDates,
								     @RequestParam("passUser") String passUser,
								     @RequestParam("receiverUsr") String receiverUsr,
								     @RequestParam("outQuantity") int outQuantity,
								     @RequestParam("outReason") String outReason,
								     @RequestParam("orderNo") Long orderNo,
								     @RequestParam("type") String type)throws Exception {
		
		Date passDate = new SimpleDateFormat("yyyy-MM-dd").parse(passDates);
		
		String sid = id.replace("J", "");

		Long rid = Long.parseLong(sid.trim());
		
		
		if(type.equals("B")) {
			
			stockOutHistory stockOutHistory = new stockOutHistory();
		
			stockOutHistory.setStockID(rid);
			stockOutHistory.setPassDate(passDate);
			stockOutHistory.setPassUser(purchaseService.userCodeSurch(passUser));
			stockOutHistory.setReceiverUsr(purchaseService.userCodeSurch(receiverUsr));
			stockOutHistory.setOutQuantity(outQuantity);
			stockOutHistory.setOutReason(outReason);
			stockOutHistory.setJobOrderId(orderNo);
			stockOutHistory.setReceiveDept(purchaseService.deptCode(receiverUsr));
			
			Long stockHisId = purchaseService.addStockHistory(stockOutHistory);
			
			Long jobPurchaseId = purchaseService.selectPurchaseIdFromstock(rid);
			
			//Long jobOrderId = purchaseService.selectOrderIdFrompurchase(jobPurchaseId);
			
			TransitionPurchase TransitionPurchase = new TransitionPurchase();
			
			TransitionPurchase.setJobOrderId(orderNo);
			TransitionPurchase.setPreId(0L);
			TransitionPurchase.setJobPurchaseId(jobPurchaseId);
			TransitionPurchase.setKindPurchase("S");
			TransitionPurchase.setQuantity(outQuantity);
			TransitionPurchase.setStockId(rid);
			TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
			TransitionPurchase.setReceiveDept(purchaseService.deptCode(receiverUsr));
			TransitionPurchase.setStockHistoryId(stockHisId);
			TransitionPurchase.setAbstock("N");
			
			purchaseService.insertTransitionpurchase(TransitionPurchase);
		
			
			purchaseService.stockOutUpdate(rid, outQuantity);
			
		}else {
			
			stockOutHistory stockOutHistory = new stockOutHistory();
			
			stockOutHistory.setStockID(rid);
			stockOutHistory.setPassDate(passDate);
			stockOutHistory.setPassUser(purchaseService.userCodeSurch(passUser));
			stockOutHistory.setReceiverUsr(purchaseService.userCodeSurch(receiverUsr));
			stockOutHistory.setOutQuantity(outQuantity);
			stockOutHistory.setOutReason(outReason);
			stockOutHistory.setJobOrderId(orderNo);
			stockOutHistory.setReceiveDept(purchaseService.deptCode(receiverUsr));
			
			Long stockHisId = purchaseService.addStockHistory(stockOutHistory);
			
			Long jobPurchaseId = purchaseService.selectPurchaseIdFromstock(rid);
			
			//Long jobOrderId = purchaseService.selectOrderIdFrompurchase(jobPurchaseId);
			
			TransitionPurchase TransitionPurchase = new TransitionPurchase();
			
			TransitionPurchase.setJobOrderId(orderNo);
			TransitionPurchase.setPreId(0L);
			TransitionPurchase.setJobPurchaseId(jobPurchaseId);
			TransitionPurchase.setKindPurchase("S");
			TransitionPurchase.setQuantity(outQuantity);
			TransitionPurchase.setStockId(rid);
			TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
			TransitionPurchase.setReceiveDept(purchaseService.deptCode(receiverUsr));
			TransitionPurchase.setStockHistoryId(stockHisId);
			TransitionPurchase.setAbstock("Y");
			
			purchaseService.insertTransitionpurchase(TransitionPurchase);
		

			purchaseService.stockOutUpdateAB(rid, outQuantity);
			
		}
									
		
				
	
		return "redirect:/purchase/stockRealList";
								
	}
	
	
	//재고 List 삭제
	@RequestMapping("/stockRealList/deleted/{id}")
	public String stockDeletedAct(@PathVariable("id") Long id)throws Exception {
		
		System.out.println("&&&&&&&&&&&&&&&& = "+id);
		
		
		purchaseService.deletedStockYN(id);
									
		
				
	
		return "redirect:/purchase/stockRealList";
								
	}
	
	//재고 등록 popUp
	@RequestMapping("/stockRealList/addPop")
	public ModelAndView stockRealListAddPop() {
	
		ModelAndView mv = new ModelAndView("purchase/popup/stockAdd");
		
		return mv;
							
	}
	
	//재고 등록 동작
	@RequestMapping("/stockRealList/addAction")
	public String stockAddAct(@RequestParam("modelNo")String modelNo,
			  				  @RequestParam("description")String description,
			  				  @RequestParam("maker")String maker,
			  				  @RequestParam("issuePrice")int issuePrice,
			  				  @RequestParam("quantity")int quantity)throws Exception {
		
		StockIns stockIns = new StockIns();
		
		stockIns.setModelNo(modelNo);
		stockIns.setDescription(description);
		stockIns.setMaker(maker);
		stockIns.setIssuePrice(issuePrice);
		stockIns.setQuantity(quantity);
		
		
		Long stockID = purchaseService.addInsStockfromPurchase(stockIns);
		
		StockHIsIns stockHIsIns = new StockHIsIns();
		
		stockHIsIns.setStockID(stockID);
		stockHIsIns.setRegistrationUser(loginDongleService.getLoginId());
		stockHIsIns.setInQuantity(quantity);
		
		
		purchaseService.stockHisInsAct(stockHIsIns);
		
		
									
		
				
	
		return "redirect:/purchase/stockRealList";
								
	}
	
	
	//재고 불출 List
	@RequestMapping("/stockRealOutList")
	public ModelAndView stockRealOutList(String orderNoBase, String orderNoExtra,  String CodeNO,String modelNo, String maker, String desc, String DateBegin, String DateEnd) {
		
		// 날짜 처리
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
				
		
								
		List<stockRealOutModel> stockRealOutModel = null;
		stockRealOutModel = purchaseService.selectStockOutRealList(orderNoBase, orderNoExtra, CodeNO, modelNo, maker, desc, convertedDesignDateBegin, convertedDesignDateEnd);
			
		ModelAndView mv = new ModelAndView("purchase/stockRealoutList");
		mv.addObject("data", stockRealOutModel);
		mv.addObject("orderNoBase", orderNoBase);
		mv.addObject("orderNoExtra", orderNoExtra);
		mv.addObject("CodeNO", CodeNO);
		mv.addObject("modelNo", modelNo);
		mv.addObject("maker", maker);
		mv.addObject("desc", desc);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		return mv;
							
	}

	//재고 불출 List 상세 팝업
	@RequestMapping("/stockoutListpop/{id}")
	public ModelAndView stockoutListpop(@PathVariable("id") Long id)throws Exception {
			
		
		
		List<stockRealOutModel> stockRealOutModel = null;
		
		stockRealOutModel = purchaseService.listoutpopdetail(id);
		
		
		String pusr = purchaseService.userNameSurch(stockRealOutModel.get(0).getPassUser());
		String rusr = purchaseService.userNameSurch(stockRealOutModel.get(0).getReceiverUsr());
		String deptName = purchaseService.deptNameSurch(stockRealOutModel.get(0).getReceiveDept());
		
	
			
		ModelAndView mv = new ModelAndView("purchase/popup/sotckoutDetailpop");								
		
		mv.addObject("data", stockRealOutModel);
		mv.addObject("pusr", pusr);
		mv.addObject("rusr", rusr);
		mv.addObject("deptName", deptName);

		return mv;
									
	}
	
	//재고 재입고 동작
	@RequestMapping("/restockinner")
	public String restockinner(@RequestParam("stockID")Long stockID,
							   @RequestParam("registrationReason")String registrationReason,
							   @RequestParam("stockAble")String stockAble,
							   @RequestParam("inQuantity")int inQuantity)throws Exception {
			
		
		stockInHistroy stockInHistroy = new stockInHistroy();
		stockInHistroy.setStockID(stockID);
		stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
		stockInHistroy.setRegistrationReason(registrationReason);
		stockInHistroy.setStockAble(stockAble);
		stockInHistroy.setInQuantity(inQuantity);
		purchaseService.StockInHistoryIns(stockInHistroy);
	
		int remindQt = purchaseService.selectStockQtfromID(stockID);
		int quantity = remindQt + inQuantity;
		purchaseService.UpdateStockfromID(stockID, quantity);
		
		
		

		return "redirect:/purchase/stockRealOutList";
									
	}
	
	
	
	//거래명세표등록
	@RequestMapping("/statementIns")
	public ModelAndView statementIns(String partnerName, String DateBegin, String DateEnd) {
		
		
		// 날짜 처리
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
		
		String PartnerCode =null;
		
		
		if(!(StringUtils.isEmpty(partnerName))) {	
			PartnerCode = purchaseService.surchPartnerCode(partnerName);
			if(StringUtils.isEmpty(PartnerCode)) {
				PartnerCode = "A";
			}
		}
		
		System.out.println("**********"+PartnerCode);
		
		List<StatementInsList> StatementInsList = purchaseService.statementInsPage(PartnerCode, convertedDesignDateBegin, convertedDesignDateEnd);
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "04", "01");
		
		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("purchase/statementIns");
		mv.addObject("data", StatementInsList);
		mv.addObject("isupdate", isupdate);
		mv.addObject("pageNo", pageNo);	
		return mv;
						
	}
	
	
	//거래명세표 등록 popup
	@RequestMapping("/statementIns/pop/{id}")
	public ModelAndView statementInspop(@PathVariable("id") Long id) {
									
		List<StatementInsPop> statementInsPop = null;
		statementInsPop = purchaseService.statementInspop(id);
		
		int pageNo = 0;
				
		ModelAndView mv = new ModelAndView("purchase/popup/statementinspop");
		mv.addObject("data", statementInsPop);
		mv.addObject("pageNo", pageNo);
		return mv;
								
	}
	
	//거래명세표 등록 pop 동작
	@RequestMapping("/statementIns/pop/insertdo")
	public String statementInspopdo(@RequestParam("id")Long[] id,
							   		@RequestParam("issueId") Long[] issueId,
							   		@RequestParam("negoPrice") int negoPrice,
							   		@RequestParam("sumPrice") int sumPrice,
							   		@RequestParam("issuedItemName") String[] issuedItemName,
							   		@RequestParam("issuedQuantity") int[] issuedQuantity,
							   		@RequestParam("issuedUnitPrice") int[] issuedUnitPrice,
							   		@RequestParam("issueDate") String issueDate,
							   		@RequestParam("buyKind") String buyKind)throws Exception {
		
		Date issueDateR = new SimpleDateFormat("yyyy-MM-dd").parse(issueDate);
		
		String requestId = purchaseService.getRequestIdfromstatement(issueId[0]);
		Long jobOrderId = purchaseService.getJobOrderIdfromstatement(issueId[0]);
		
		
		purchaseService.StatementofUpdate(issueId[0], buyKind, sumPrice, negoPrice, issueDateR);
		
		
		for(int i=0; i<id.length;i++) {
			
			purchaseService.StatementofDetailUpdate(id[i], issuedItemName[i], issuedQuantity[i], issuedUnitPrice[i]);
			
			Long jobPurchaseId = purchaseService.getPurchaseIdfromstatementDetail(id[i]);
			
			purchaseService.upDatePurchaseStatementA(jobPurchaseId);
			purchaseService.upDatePurchaseIssueStatementY(requestId, jobPurchaseId, jobOrderId);
			
	
			
		}

				
		
		return "redirect:/purchase/statementIns";
									
	}
	
	//거래명세표리스트 page
	@RequestMapping("/statementList")
	public ModelAndView statementList(String partnerName, String DateBegin, String DateEnd, 
			String orderNoBase, String orderNoExtra) {
			
			
		// 날짜 처리
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
		
		
			
		String PartnerCode =null;
			
			
		if(!(StringUtils.isEmpty(partnerName))) {	
			PartnerCode = purchaseService.surchPartnerCode(partnerName);
			if(StringUtils.isEmpty(PartnerCode)) {
				PartnerCode = "A";
			}
		}
			
		
			
		List<statementList> statementList = purchaseService.statementListPage(PartnerCode, convertedDesignDateBegin, convertedDesignDateEnd, 
				 orderNoBase, orderNoExtra);
			
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "25", "04", "02");
			
			
		ModelAndView mv = new ModelAndView("purchase/statementList");
		mv.addObject("data", statementList);
		mv.addObject("isupdate", isupdate);	
		return mv;
							
	}
	
	//거래명세표 LIST 팝업
	@RequestMapping("/statementList/pop/{id}")
	public ModelAndView sstatementListpop(@PathVariable("id") Long id) {
		
		
		StatementPartner StatementPartner = new StatementPartner();
		
		StatementPartner=purchaseService.selectDatePID(id);
		
		StatementListPartner StatementListPartner = new StatementListPartner();
		
		StatementListPartner = purchaseService.selectPartner(StatementPartner.getPartnerId());
		
		
		List<StatementListPop> StatementListPop = purchaseService.statementListPop(id);
		
		Long sumPrice = purchaseService.statementListSum(id);
		Long negoPrice = purchaseService.statementListNego(id);
		
		int pageNo = 0;

		ModelAndView mv = new ModelAndView("purchase/popup/statementListPop");
		mv.addObject("issueDate", StatementPartner.getIssueDate());
		mv.addObject("partner", StatementListPartner);
		mv.addObject("data", StatementListPop);
		mv.addObject("sumPrice", sumPrice);
		mv.addObject("negoPrice", negoPrice);
		mv.addObject("pageNo", pageNo);
		return mv;
								
	}
	
	
	//OrderNo 기준 Page
	@RequestMapping("/serachOrderNoState")
	public ModelAndView serachOrderNoState(String designDateBegin, String designDateEnd) {
		
		
		//날짜처리
		if (StringUtils.isEmpty(designDateBegin))
			designDateBegin = null;
		if (StringUtils.isEmpty(designDateEnd))
			designDateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (designDateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(designDateBegin);
			if (designDateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(designDateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		List<SearchCountOrderState> OrderCount = purchaseService.serachCountOrderState(null, null, convertedDesignDateBegin, convertedDesignDateEnd);
		List<SearchOrderState> OrderState = purchaseService.serachOrderState(null, null, convertedDesignDateBegin, convertedDesignDateEnd);
		
		
		
		ModelAndView mv = new ModelAndView("purchase/orderNoStatePage");
		mv.addObject("designDateBegin", designDateBegin);
		mv.addObject("designDateEnd", designDateEnd);
		mv.addObject("data",OrderCount);
		mv.addObject("data2",OrderState);
	
		return mv;
						
	}
	
	//매입업체 기준 page
	@RequestMapping("/serachPartnerState")
	public ModelAndView serachPartnerState(String designDateBegin, String designDateEnd) {
		
		//날짜처리
		if (StringUtils.isEmpty(designDateBegin))
			designDateBegin = null;
		if (StringUtils.isEmpty(designDateEnd))
			designDateEnd = null;

		Date convertedDesignDateBegin = null, convertedDesignDateEnd = null;
		try {
			if (designDateBegin != null)
				convertedDesignDateBegin = CommonRule.ymdFormat.parse(designDateBegin);
			if (designDateEnd != null)
				convertedDesignDateEnd = CommonRule.ymdFormat.parse(designDateEnd);
		} catch (Exception e) {
			log.info("날짜 변환 중 오류", e);

			convertedDesignDateBegin = convertedDesignDateEnd = null;
		}
		
		Integer pageNo=0; 
		
		
		List<SearchCountPartnerState> PartnerCount = purchaseService.serachCountPartnerState(convertedDesignDateBegin, convertedDesignDateEnd);
		
		List<SerachPartnerState> PartnerState = purchaseService.serachPartnerState(convertedDesignDateBegin, convertedDesignDateEnd);
		
		
		
		
		
		
		ModelAndView mv = new ModelAndView("purchase/partnerStatePage");
		mv.addObject("designDateBegin", designDateBegin);
		mv.addObject("designDateEnd", designDateEnd);
		mv.addObject("data",PartnerCount);
		mv.addObject("data2",PartnerState);
		mv.addObject("pageNo", pageNo);
	
		return mv;
						
	}
	
	
	//월별합계
	@RequestMapping("/serachDateSum")
	public ModelAndView serachDateSum(String type) {
		
		if (StringUtils.isEmpty(type))
			type = "P";
		
		if(type.equals("O")) {
			
			List<SurchAllOrder> allOrder = purchaseService.surchAllOrder();
			
			List<SumPriceState> Jan = purchaseService.janSumOrder();
			List<SumPriceState> Feb = purchaseService.febSumOrder();
			List<SumPriceState> Mar = purchaseService.marSumOrder();
			List<SumPriceState> Apr = purchaseService.aprSumOrder();
			List<SumPriceState> May = purchaseService.maySumOrder();
			List<SumPriceState> Jun = purchaseService.junSumOrder();
			List<SumPriceState> Jul = purchaseService.julSumOrder();
			List<SumPriceState> Aug = purchaseService.augSumOrder();
			List<SumPriceState> Sep = purchaseService.sepSumOrder();
			List<SumPriceState> Oct = purchaseService.octSumOrder();
			List<SumPriceState> Nov = purchaseService.novSumOrder();
			List<SumPriceState> Dec = purchaseService.decSumOrder();
			
			Integer pageNo=0; 
			
			int countJan = purchaseService.janCountOrder();
			int countFeb = purchaseService.febCountOrder();
			int countMar = purchaseService.marCountOrder();
			int countApr = purchaseService.aprCountOrder();
			int countMay = purchaseService.mayCountOrder();
			int countJun = purchaseService.junCountOrder();
			int countJul = purchaseService.julCountOrder();
			int countAug = purchaseService.augCountOrder();
			int countSep = purchaseService.sepCountOrder();
			int countOct = purchaseService.octCountOrder();
			int countNov = purchaseService.novCountOrder();
			int countDec = purchaseService.decCountOrder();
			

			ModelAndView mv = new ModelAndView("purchase/sumPriceState");
			
			mv.addObject("data",allOrder);
			
			mv.addObject("Jan",Jan);
			mv.addObject("Feb",Feb);
			mv.addObject("Mar",Mar);
			mv.addObject("Apr",Apr);
			mv.addObject("May",May);
			mv.addObject("Jun",Jun);
			mv.addObject("Jul",Jul);
			mv.addObject("Aug",Aug);
			mv.addObject("Sep",Sep);
			mv.addObject("Oct",Oct);
			mv.addObject("Nov",Nov);
			mv.addObject("Dec",Dec);
			
			mv.addObject("pageNo", pageNo);
			
			mv.addObject("countJan",countJan);
			mv.addObject("countFeb",countFeb);
			mv.addObject("countMar",countMar);
			mv.addObject("countApr",countApr);
			mv.addObject("countMay",countMay);
			mv.addObject("countJun",countJun);
			mv.addObject("countJul",countJul);
			mv.addObject("countAug",countAug);
			mv.addObject("countSep",countSep);
			mv.addObject("countOct",countOct);
			mv.addObject("countNov",countNov);
			mv.addObject("countDec",countDec);
			
			mv.addObject("type", type);
		
			return mv;
			
		}
		else {
			
			List<SurchAllPartner> allPartner = purchaseService.surchAllPartner();
			
			List<SumPriceState> Jan = purchaseService.janSum();
			List<SumPriceState> Feb = purchaseService.febSum();
			List<SumPriceState> Mar = purchaseService.marSum();
			List<SumPriceState> Apr = purchaseService.aprSum();
			List<SumPriceState> May = purchaseService.maySum();
			List<SumPriceState> Jun = purchaseService.junSum();
			List<SumPriceState> Jul = purchaseService.julSum();
			List<SumPriceState> Aug = purchaseService.augSum();
			List<SumPriceState> Sep = purchaseService.sepSum();
			List<SumPriceState> Oct = purchaseService.octSum();
			List<SumPriceState> Nov = purchaseService.novSum();
			List<SumPriceState> Dec = purchaseService.decSum();
			
			int countJanPartner = purchaseService.janCountPartner();
			int countFebPartner = purchaseService.febCountPartner();
			int countMarPartner = purchaseService.marCountPartner();
			int countAprPartner = purchaseService.aprCountPartner();
			int countMayPartner = purchaseService.mayCountPartner();
			int countJunPartner = purchaseService.junCountPartner();
			int countJulPartner = purchaseService.julCountPartner();
			int countAugPartner = purchaseService.augCountPartner();
			int countSepPartner = purchaseService.sepCountPartner();
			int countOctPartner = purchaseService.octCountPartner();
			int countNovPartner = purchaseService.novCountPartner();
			int countDecPartner = purchaseService.decCountPartner();
			
			Integer pageNo=0; 
			

			ModelAndView mv = new ModelAndView("purchase/sumPriceState");
			
			mv.addObject("data",allPartner);
		
			
			mv.addObject("Jan",Jan);
			mv.addObject("Feb",Feb);
			mv.addObject("Mar",Mar);
			mv.addObject("Apr",Apr);
			mv.addObject("May",May);
			mv.addObject("Jun",Jun);
			mv.addObject("Jul",Jul);
			mv.addObject("Aug",Aug);
			mv.addObject("Sep",Sep);
			mv.addObject("Oct",Oct);
			mv.addObject("Nov",Nov);
			mv.addObject("Dec",Dec);
			
			mv.addObject("pageNo", pageNo);
			
			mv.addObject("countJanPartner",countJanPartner);
			mv.addObject("countFebPartner",countFebPartner);
			mv.addObject("countMarPartner",countMarPartner);
			mv.addObject("countAprPartner",countAprPartner);
			mv.addObject("countMayPartner",countMayPartner);
			mv.addObject("countJunPartner",countJunPartner);
			mv.addObject("countJulPartner",countJulPartner);
			mv.addObject("countAugPartner",countAugPartner);
			mv.addObject("countSepPartner",countSepPartner);
			mv.addObject("countOctPartner",countOctPartner);
			mv.addObject("countNovPartner",countNovPartner);
			mv.addObject("countDecPartner",countDecPartner);
			
			mv.addObject("type", type);
		
			return mv;
			
			
			
		}
						
	}
	
	
	//OrderNo 총합
	@RequestMapping("/orderNoSumState")
	public ModelAndView orderNoSumState(String orderNoBase, String orderNoExtra) {
		
		
		
		
		List<SearchCountOrderState> OrderCount = purchaseService.serachCountOrderState(orderNoBase, orderNoExtra, null, null);
		List<SearchOrderState> OrderState = purchaseService.serachOrderState(orderNoBase, orderNoExtra, null, null);
		
		
		
		ModelAndView mv = new ModelAndView("purchase/orderNoSumState");

		mv.addObject("data",OrderCount);
		mv.addObject("data2",OrderState);
		mv.addObject("orderNoBase", orderNoBase);
    	mv.addObject("orderNoExtra", orderNoExtra);
	
		return mv;
						
	}
	
	
	//구매 품의서 구매 등록 Main
	@RequestMapping("/purchaseroundRobin")
	public ModelAndView purchaseroundRobin(String title, String DateBegin, String DateEnd){
		
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
		
		
		int pageNo = 0;
		List<RoundRobin> data = myMenuService.purchaseroundRobinSelect(title, convertedDesignDateBegin, convertedDesignDateEnd);
		
		ModelAndView mv = new ModelAndView("purchase/purchaseRoundRobinMain");
		mv.addObject("data", data);
		mv.addObject("title", title);
		mv.addObject("DateBegin", DateBegin);
		mv.addObject("DateEnd", DateEnd);
		mv.addObject("pageNo", pageNo);
		return mv;
	}
	
	//구매품의서 구매 List 등록 popup
	@RequestMapping("/purchaseroundRobin/insPop/{roundNo}")
	public ModelAndView purchaseroundRobin_insPop(@PathVariable("roundNo") String roundNo){
		
		RoundRobin line = myMenuService.detailRobinOne(roundNo);
		List<RoundRobin> data2 = myMenuService.detailRobinAll(roundNo);
		
		List<StockListOrderNo> orderNodata = purchaseService.searchOrderNo();
		List<Customer> customerData = myMenuService.selectAllCustomer();
		List<PurchasePartner> partner = purchaseService.selectPartnerIdName();
		int pageNo =0;
		
		ModelAndView mv = new ModelAndView("purchase/popup/purchaseRoundRobinPop");
		mv.addObject("line", line);
		mv.addObject("data2", data2);
		mv.addObject("orderNodata", orderNodata);
		mv.addObject("customerData", customerData);
		mv.addObject("partner", partner);
		mv.addObject("pageNo", pageNo);
	
		return mv;
	}
	
	//구매품의서 구매 LIST 등록 동작
	@RequestMapping("/purchaseroundRobin/insert/do")
	public String purchaseroundRobin_insertDo(@RequestParam("orderNo") Long orderNo,
											  @RequestParam("customerId") String customerId,
											  @RequestParam("partnerId") String partnerId,
											  @RequestParam("roundNo") String roundNo){
		
		List<RoundRobin> RoundRobins = myMenuService.detailRobinAll(roundNo);
		
		
		
		for(RoundRobin RoundRobin : RoundRobins ) {
			
			JobPurchaseDB JobPurchaseDB = new JobPurchaseDB();
			
			JobPurchaseDB.setJobOrderId(orderNo);
			JobPurchaseDB.setCustomerId(customerId);
			JobPurchaseDB.setDescription(RoundRobin.getDescription());
			JobPurchaseDB.setModelNo(RoundRobin.getModelNo());
			JobPurchaseDB.setMaker(RoundRobin.getMaker());
			JobPurchaseDB.setPartnerId(partnerId);
			JobPurchaseDB.setQuantity(RoundRobin.getQuantity());
			JobPurchaseDB.setRobinRequestDate(RoundRobin.getRequestDate());
			
			purchaseService.insertJobPurchasefromRobin(JobPurchaseDB);
			
			
		}
		
		
		
		
		myMenuService.roundRobinStageF(roundNo);
		
		
	
		return "redirect:/purchase/purchaseroundRobin";
	}
	
	
	
	
	

	
	
	

	
	

}
