package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.JobPartnerReg;
import com.yuhannci.erp.model.PsBillSearchForm;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.JobPartner;
import com.yuhannci.erp.model.db.StatementRegEntry;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.StatementAccountService;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

// 가공 일정 관리 - 거래명세표 관리

@Controller
@Slf4j
@RequestMapping("/ps/bill")
public class ProductionScheduleBillController {
	
	@Autowired MenuService menuService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired StatementAccountService statementAccountService;
	@Autowired PartnerService partnerService;
	
	@InitBinder
	public void allowEmptyDateBinding( WebDataBinder binder )
	{
	    // tell spring to set empty values as null instead of empty string.
	    binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
	   
	}
	// 거래명세 등록
	@RequestMapping("/all")
	public ModelAndView billAll(String partner, String orderNoBase, String orderNoExtra, Date orderDateTo, Date orderDateFrom){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "04", "01");
		ModelAndView mv = new ModelAndView("production_schedule/bill/all");
		mv.addObject("isupdate", isupdate);
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/data/all")
	public DataTableResponse dataAll(PsBillSearchForm form) {
	
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&& from ID = [ " + form.getOrderNoBase() + " - " + form.getOrderNoExtra()+ " ]");
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			if(!form.getIncludePostprocessing() && !form.getIncludeProcessing())
				throw new RuntimeException("가공발주 또는 후처리발주를 선택해주세요");
			
			res = statementAccountService.getUnpublishedStatementList(form);
		
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		return res;
	}	
	
	// 거래명세 등록 팝업
	@RequestMapping("/popup/reg")
	public ModelAndView popupReg(@RequestParam("id") String outsourcingId, String partnerId, String requestType, @RequestParam("jobOrderId") String jobOrderId) {
		
		log.info("outsourcingId=" + outsourcingId + ", partnerId=" + partnerId + ", requestType=" + requestType, "jobOrderId=" + jobOrderId);
		
		JobPartner partnerInfo = partnerService.getPartner(partnerId);
		if(partnerInfo == null)
			throw new RuntimeException("지정된 업체 정보를 찾을 수 없음");
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );

		
		ModelAndView mv = new ModelAndView("production_schedule/bill/popup_reg");
		mv.addObject("partnerName", partnerInfo.getPartnerName() );
		mv.addObject("id",  outsourcingId);
		mv.addObject("partnerId", partnerId );
		mv.addObject("requestType",  requestType);
		mv.addObject("jobOrderId",  jobOrderId);
		mv.addObject("today",  mTime);
		return mv;
	}
	
	// 명세서 등록 팝업에서 표기되는 데이터
	@ResponseBody
	@RequestMapping("/data/reg")
	public DataTableResponse dataReg(Integer draw, @RequestParam("id") String outsourcingId, String partnerId, String requestType, @RequestParam("jobOrderId") String jobOrderId) {
		DataTableResponse res = new DataTableResponse();
		try {
			
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   ===  " + jobOrderId);
			
			Long roderId = Long.parseLong(jobOrderId);
			
			res = statementAccountService.getUnregisteredDrawingEntry(draw, outsourcingId, partnerId, requestType, roderId);
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		return res;
	}
	
	// 명세서 발행
	@ResponseBody
	@PostMapping(value="/make/statement", consumes= {"application/json"})
	public StandardResponse makeStatement(@RequestBody StatementRegEntry[] data
												, String partnerId
												, String requestType
												, String outsourcingOrderId
												, Long negoPrice
												, @DateTimeFormat(pattern="yyyy-MM-dd") Date issueDate) {
		
		StandardResponse res = new StandardResponse();
		try {			
			if( !"P".equals(requestType) && !"C".equals(requestType) )
				throw new RuntimeException("가공/후처리 작업 건만 등록 가능합니다");
			
			if(issueDate == null)
				throw new RuntimeException("발행 날짜를 입력해 주세요");
			
			if(partnerId == null)
				throw new RuntimeException("협력 업체ID 가 없습니다");
			
			if(negoPrice == null)
				throw new RuntimeException("네고 금액을 입력해 주세요");
			

			// '제외'가 체크 안된 항목만 사용
			log.info("src = " + Arrays.toString(data));
			List<StatementRegEntry> valid = Stream.of(data).filter(a -> a.getExclude() == null).collect(Collectors.toList());
			log.info("valid = " + valid);
			if(valid.size() == 0)
				throw new RuntimeException("등록할 대상이 없습니다");
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@src = " + Arrays.toString(data));
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@valid = " + valid);
			
			// 명세서 등록 처리
			statementAccountService.registerStatement(partnerId, requestType, outsourcingOrderId, negoPrice, issueDate, valid);
			
			res = StandardResponse.createSuccessResponse();
			
		}catch(RuntimeException e) {
			res = StandardResponse.createErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res = StandardResponse.createErrorResponse();
			e.printStackTrace();
		}
		return res;
	}
	
	// 거래명세서 조회
	@RequestMapping("/list")
	public ModelAndView billList(String partner, String orderNoBase, String orderNoExtra){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "04", "02");
		ModelAndView mv = new ModelAndView("production_schedule/bill/list");
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	// 거래명세서 조회 데이터 (명세서 등록된 건)
	@ResponseBody
	@RequestMapping("/data/list")
	public DataTableResponse dataList(PsBillSearchForm form) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			if(!form.getIncludePostprocessing() && !form.getIncludeProcessing())
				throw new RuntimeException("가공발주 또는 후처리발주를 선택해주세요");
			
			res = statementAccountService.getPublishedStatementList(form);
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		return res;
	}	
	
	@RequestMapping("/popup/view")
	public ModelAndView popupView(Long statementId) {
		ModelAndView mv = new ModelAndView("/production_schedule/bill/popup_view");
		mv.addObject("statementId", statementId);
		mv.addObject("statement", statementAccountService.getStatementInformation(statementId));
		return mv;
	}
	
	@RequestMapping("/data/view")
	@ResponseBody
	public DataTableResponse dataView(Integer draw, Long statementId) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			res = statementAccountService.loadStatementInformation(draw, statementId);
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		return res;
		
	}
	
	// 거래처 관리
	@RequestMapping("/partner")
	public ModelAndView billPartner(String partnerId){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "04", "03");
		ModelAndView mv = new ModelAndView("production_schedule/bill/partner");
		mv.addObject("isupdate", isupdate);
		return mv;
	}
	
	@RequestMapping("/data/partner")
	@ResponseBody
	public DataTableResponse dataPartner(Integer draw, Integer start, Integer length, Integer sortOrder) {
		
		DataTableResponse res = new DataTableResponse();
		try {
		
			res = partnerService.getSimplePartnerList(draw, sortOrder, start, length);
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		return res;		
	}
	
	@RequestMapping("/popup/partner")
	public ModelAndView popupPartner(@RequestParam(name="id", required=false) String partnerId) {
		
		if(StringUtils.isEmpty(partnerId))
			partnerId = null;
		
		ModelAndView mv = new ModelAndView("production_schedule/bill/popup_partner");
		mv.addObject("partnerId", partnerId);
		return mv;
	}
	
	@RequestMapping("/data/partner_info")
	@ResponseBody
	public StandardResponse partnerInfo(String partnerId) {
		StandardResponse res = new StandardResponse();
		try {
			
			JobPartner data = partnerService.getPartner(partnerId);
			if(data == null)
				throw new RuntimeException("업체 정보를 찾을 수 없습니다");
			
			res.setData( data );
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
	
	// 새 업체 코드 조회
	@RequestMapping("/data/next")
	@ResponseBody
	public StandardResponse dataNext(String partnerTypeId) {
		
		StandardResponse res = new StandardResponse();
		try {
			
			if(partnerTypeId == null || partnerTypeId.length() != 2)
				throw new RuntimeException("업체 종류 ID 가 잘못되었습니다");
			
			String newPartnerId = partnerService.getNextPartnerId(partnerTypeId);
			
			res.setData( newPartnerId );
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
	
	@RequestMapping("/submit/partner")
	@ResponseBody
	public StandardResponse submitPartner(JobPartnerReg partner) {
		
		StandardResponse res = new StandardResponse();
		
		try {
			
			partnerService.updatePartner(partner);
			res = StandardResponse.createSuccessResponse();
			
		}catch(RuntimeException e) {
			res = StandardResponse.createErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res = StandardResponse.createErrorResponse();
			e.printStackTrace();
		}
		
		return res;
		
	}
	
}
