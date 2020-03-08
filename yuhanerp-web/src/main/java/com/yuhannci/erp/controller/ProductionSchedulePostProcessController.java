package com.yuhannci.erp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.mapper.JobDesignDrawingMapper;
import com.yuhannci.erp.model.CoatingAbortTypeEnum;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.service.DrawingService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.PostprocessService;
import com.yuhannci.erp.service.ProcessService;

import lombok.extern.slf4j.Slf4j;

// 생산 일정 관리/반입 반출

@Slf4j
@Controller
@RequestMapping("/ps/postprocess")
public class ProductionSchedulePostProcessController {
	
	@Autowired JobDesignDrawingMapper jobDesignDrawingMapper;
	@Autowired PostprocessService postprocessService;
	@Autowired MenuService menuService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired ProcessService processService;
	@Autowired DrawingService drawingService;

	// 후처리 등록
	@RequestMapping("/reg")
	public ModelAndView normalIn(String partner, String orderNoBase, String orderNoExtra){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "03", "01");
		ModelAndView mv = new ModelAndView("production_schedule/postprocess/reg");
		mv.addObject("isupdate", isupdate);	
		mv.addObject("isupdate", "Y");
		return mv;
	}
	
	// 후처리 등록 리스트
	@RequestMapping("/data/reg")
	@ResponseBody
	public DataTableResponse dataReg(Integer draw, Integer start, Integer length,
										@RequestParam(name="id",required=false) String targetIdList, 
										String outsourcingPartnerId, 
										String orderNoBase, String orderNoExtra, 
										boolean processInsourcing, boolean processOutsourcing,
										@DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateTo ) {
		
		DataTableResponse res = null;
		
		try {
			if(StringUtils.isEmpty(outsourcingPartnerId))
				outsourcingPartnerId = null;
			if(StringUtils.isEmpty(orderNoBase))
				orderNoBase = null;
			if(StringUtils.isEmpty(orderNoExtra))
				orderNoExtra = null;
			
			String processSourcing = processInsourcing && !processOutsourcing ? "I" :
									!processInsourcing && processOutsourcing ? "O" : null;										
						
			// 후처리 스킵 팝업 , 후처리 발주 등록에서 호출하는 경우
			long[] id = null;
			if( !StringUtils.isEmpty(targetIdList)) {
				id = Stream.of(targetIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
				log.info("요청한 ID = " + Arrays.toString(id) );
			}
			
			res = postprocessService.getPostprocessingList(start, length, id, orderDateFrom, orderDateTo, null, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing);
			res.setDraw(draw);
			
		}catch(Exception e) {
			log.error("후처리 등록 할 목록 조회 중 오류", e);
		}
		
		return res;
	}
	
	// 후처리 등록 --> 후처리 스킵 팝업
	@RequestMapping("/popup/skip")
	public ModelAndView popupSkip(@RequestParam("id") String targetIdList) {
		
		ModelAndView mv = new ModelAndView("production_schedule/postprocess/popup_skip");
		mv.addObject("targetIdList", targetIdList);
		return mv;
	}
	
	// 후처리 스킵 등록 팝업 --> 후처리 스킵 처리
	@RequestMapping("/update/skip")
	@ResponseBody
	public StandardResponse updateSkip(@RequestParam("id") String targetIdList, Boolean finished) {
		StandardResponse res;
		try {
			long[] id = Stream.of(targetIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
			log.info("요청한 ID = [" + targetIdList + "]");

			postprocessService.markPostprocessSkip(id, finished);			
			
			res = StandardResponse.createSuccessResponse();
		}catch(RuntimeException e) {
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e) {
			res = StandardResponse.createErrorResponse();
		}
		return res;
	}
	
	// 후처리 등록 --> 후처리 발주 등록 팝업
	// 후처리 스킵 리스트 --> 후처리 발주 전환 팝업
	@RequestMapping("/popup/postprocess_order")
	public ModelAndView popupPostprocessOrder(@RequestParam("id") String targetIdList, String pageFrom) {
		
		ModelAndView mv = new ModelAndView("production_schedule/postprocess/popup_postprocess_order");
		mv.addObject("targetIdList", targetIdList);
		mv.addObject("pageFrom", pageFrom);
		mv.addObject("queryPage", "replace".equals(pageFrom) ? "skip" : "reg");
		mv.addObject("pageExtraTitle", "replace".equals(pageFrom) ? "전환 " : "");
		return mv;
	}
	
	// 후처리 발주
	// 후처리 전환 발주
	@RequestMapping("/make/outsourcing_order")
	@ResponseBody
	public StandardResponse makeOutsourcingOrder(	@RequestParam(required=false) String pageFrom, 
													String targetPartner, 
												@RequestParam("id") String[] rawDesignDrawingId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] deliveryDate ) {
		
		StandardResponse res = null;
		try {
			log.info("후처리 발주 요청. 후처리업체 = [" + targetPartner + "], 도면ID=" + Arrays.toString(rawDesignDrawingId) + ", pageFrom=" + pageFrom);
			final boolean isNormalOrder = !("replace".equals(pageFrom));
			
			if(deliveryDate == null || rawDesignDrawingId == null || rawDesignDrawingId.length == 0)
				throw new RuntimeException("대상이 없습니다");
			if(rawDesignDrawingId.length != deliveryDate.length)
				throw new RuntimeException("대상이 일치하지 않습니다");
			
			Long[] IDs = Stream.of(rawDesignDrawingId).map(a -> Long.parseLong(a)).toArray(Long[]::new);
								
			for(Date d : deliveryDate)
				if(d == null)
					throw new RuntimeException("납기일을 입력해 주세요");
			
			String postprocessOrderId = null;
			if(isNormalOrder) {
				postprocessOrderId = processService.makePostprocessingOrder(targetPartner, IDs, deliveryDate);
				
				for(int i=0; i<IDs.length;i++) {
					processService.insDesignHistry(IDs[i], "B", loginDongleService.getLoginId(), "도면이 ["+ drawingService.outScourcingPTNamefromHistory(targetPartner) +"] 으로 후처리 발주 요청 되었습니다.");
				}
			}
			else {
				postprocessOrderId = postprocessService.makePostprocessSkipOrder(IDs, deliveryDate, targetPartner);
				
				for(int i=0; i<IDs.length;i++) {
					processService.insDesignHistry(IDs[i], "B", loginDongleService.getLoginId(), "도면 후처리가 스킵되었습니다.");
				}
			}
				
			log.info("후처리 발주 ID = " + postprocessOrderId);
			
			res = StandardResponse.createSuccessResponse();
			res.setData(postprocessOrderId);
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e) {
			e.printStackTrace();
			res = StandardResponse.createErrorResponse();
		}	
		
		return res;
	}
	
	// 후처리 스킵 리스트 ========================================================================================================================================================================
	@RequestMapping("/skip")
	public ModelAndView postprocessedIn(){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "03", "02");
		ModelAndView mv = new ModelAndView("production_schedule/postprocess/skip");
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	// 후처리 스킵 리스트 목록
	@RequestMapping("data/skip")
	@ResponseBody	
	public DataTableResponse dataSkip(Integer draw, Integer start, Integer length, 
										@RequestParam(required = false, name="id") String targetIdList,										
										String outsourcingPartnerId, 
										String orderNoBase, String orderNoExtra, 
										boolean processInsourcing, boolean processOutsourcing,
										@DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateTo ) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			if(StringUtils.isEmpty(outsourcingPartnerId))
				outsourcingPartnerId = null;
			if(StringUtils.isEmpty(orderNoBase))
				orderNoBase = null;
			if(StringUtils.isEmpty(orderNoExtra))
				orderNoExtra = null;
			
			String processSourcing = processInsourcing && !processOutsourcing ? "I" :
									!processInsourcing && processOutsourcing ? "O" : null;										
						
			long[] id = null;
			if( !StringUtils.isEmpty(targetIdList)) {
				id = Stream.of(targetIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
				log.info("요청한 ID = " + Arrays.toString(id) );
			}

			res = postprocessService.getSkipList(start, length, id, orderDateFrom, orderDateTo, null, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing);
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			res.setErrorResponse(e);
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setErrorResponse();
		}
		
		return res;
	}
	
	// 후처리 발주 리스트
	@RequestMapping("/order")
	public ModelAndView postprocessOrder(){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "03", "03");
		ModelAndView mv = new ModelAndView("production_schedule/postprocess/order");
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	// 후처리 발주 리스트 목록
	@RequestMapping("data/order")
	@ResponseBody	
	public DataTableResponse dataOrder(Integer draw, Integer start, Integer length, 
										@RequestParam(required = false, name="id") String targetIdList,										
										String outsourcingPartnerId, 
										String orderNoBase, String orderNoExtra, 
										boolean processInsourcing, boolean processOutsourcing,
										@DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateTo ) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			if(StringUtils.isEmpty(outsourcingPartnerId))
				outsourcingPartnerId = null;
			if(StringUtils.isEmpty(orderNoBase))
				orderNoBase = null;
			if(StringUtils.isEmpty(orderNoExtra))
				orderNoExtra = null;
			
			String processSourcing = processInsourcing && !processOutsourcing ? "I" :
									!processInsourcing && processOutsourcing ? "O" : null;										
						
			long[] id = null;
			if( !StringUtils.isEmpty(targetIdList)) {
				id = Stream.of(targetIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
				log.info("요청한 ID = " + Arrays.toString(id) );
			}

			res = postprocessService.getOrderList(start, length, id, orderDateFrom, orderDateTo, null, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing);
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			res.setErrorResponse(e);
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setErrorResponse();
		}
		
		return res;
	}	
	
	// 후처리 발주 취소
	@RequestMapping("/popup/abort")
	public ModelAndView popupAbort(@RequestParam("id") String targetIdList) {
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "03", "03");
		ModelAndView mv = new ModelAndView("production_schedule/postprocess/popup_abort");
		mv.addObject("isupdate", isupdate);	
		mv.addObject("targetIdList", targetIdList);	// 취소할 도면 번호 리스트
		return mv;
	}
	
	@RequestMapping("/make/abort")
	@ResponseBody
	public StandardResponse makeAbort(CoatingAbortTypeEnum coatingAbortType, @RequestParam("id") String targetIdList, String reason ) {
		StandardResponse res = null;
		
		try {
			if(coatingAbortType == null)
				throw new RuntimeException("취소 유형을 지정해주세요");

			long[] id = null;
			if( !StringUtils.isEmpty(targetIdList)) {
				id = Stream.of(targetIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
				log.info("요청한 ID = " + Arrays.toString(id) );
			}
			
			postprocessService.abortCoatingOrder(coatingAbortType, id, reason);
			
			res = StandardResponse.createSuccessResponse();
		}catch(RuntimeException e) {
			e.printStackTrace();
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e) {
			e.printStackTrace();
			res = StandardResponse.createErrorResponse();
		}
		
		return res;
	}
}
