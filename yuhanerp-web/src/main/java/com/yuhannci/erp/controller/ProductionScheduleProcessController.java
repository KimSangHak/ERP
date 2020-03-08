package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.controller.ProductionScheduleCarryController.XXXException;
import com.yuhannci.erp.mapper.JobDesignDrawingMapper;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.DesignHistorywhere;
import com.yuhannci.erp.model.ProcessChangeDataForm;
import com.yuhannci.erp.model.ProcessHisSearchForm;
import com.yuhannci.erp.model.ProcessList_ListFormData;
import com.yuhannci.erp.model.ProcessOrderListEntry;
import com.yuhannci.erp.model.ProcessOutsourceListData;
import com.yuhannci.erp.model.PsBillSearchForm;
import com.yuhannci.erp.model.RequestEstimateFormData;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.db.DesignDrawingHistory;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobPartnerOutsourcingOrder;
import com.yuhannci.erp.service.DrawingInspectService;
import com.yuhannci.erp.service.DrawingService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.ProcessOutsourceListDataService;
import com.yuhannci.erp.service.ProcessService;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

// 생산일정관리 > 가공 관리

@Controller
@RequestMapping("/ps/process")
@Slf4j
public class ProductionScheduleProcessController {
	
	@Autowired ProcessService processService;
	@Autowired ProcessOutsourceListDataService processOutsourceListDataService;
	
	@Autowired DrawingService drawingService;
	@Autowired PartnerService partnerService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired MenuService menuService;
	
	// 가공 내역 관리
	@RequestMapping("/list")
	public ModelAndView manufactureList(String orderNoBase, String orderNoExtra, String drawingNo, String device, String selectedCustomer){
			
		ModelAndView mv = new ModelAndView("production_schedule/process/list");
		mv.addObject("selectedCustomer", selectedCustomer);
		mv.addObject("device", device);
		mv.addObject("orderNoBase", orderNoBase);
		mv.addObject("orderNoExtra", orderNoExtra);
		mv.addObject("drawingNo", drawingNo);
		return mv;
	}
	
	// 가공 내역 관리에 표시될 리스트
	@RequestMapping("/data/list")
	@ResponseBody
	public DataTableResponse manufactureDataList(Integer draw, Integer start, Integer length 
													, String orderNoBase, String orderNoExtra, String drawingNo
													, String selectedCustomer
													, String device) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			res = processService.getProcessReadyDetailList(draw, start, length, selectedCustomer, device, orderNoBase, orderNoExtra, drawingNo);
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		
		
		
		
		return res;		
	}
	
	//가공품 history 상세 내역 page
	@RequestMapping("/designdrawing")
	public ModelAndView processDetailHistory(String orderNoBase, String orderNoExtra, String drawingNo){
		
	
		ModelAndView mv = new ModelAndView("production_schedule/process/processHistoryDetail");
		
		
		return mv;
	}
	
	//history 상세 내역 page 데이터
	@ResponseBody
	@RequestMapping("/data/designdrawing")
	public DataTableResponse dataProcessDetailHistory(ProcessHisSearchForm form) {
	
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&& from ID = [ " + form.getOrderNoBase() + " - " + form.getOrderNoExtra()+ " ]");
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			
			res = processService.getProcessHistoryPageList(form);
			
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
	
	//가공품 상세 이력 popup
	@RequestMapping("/popup/historyPS")
	public ModelAndView historyPSpop(@RequestParam("id") Long id){
		
		
		List<DesignDrawingHistory> data = new ArrayList<>();
		
		data = processService.detailDesignDrawingOnehistory(id);
		
		String designDrawingNo;
		
		if(!(data.isEmpty())) {
			designDrawingNo = data.get(0).getDesignDrawingNo();
		}
		else {
			
			designDrawingNo = processService.drawHisFullNo(id);
		}
		DesignHistorywhere data2 = processService.nowDrawinghistoryWhere(id);
		
		String whereDept;
		
		if(data2 == null) {
			whereDept = "생산관리";
		}
		else {
			
			if(data2.getComplet().equals("N")) {
				whereDept = data2.getDeptName() + "(인수대기)";
			}else {
				whereDept = data2.getDeptName();
			}
			
		}

	
		ModelAndView mv = new ModelAndView("production_schedule/process/processHistoryPop");
		
		if(data.isEmpty()) {
			mv.addObject("data", null);
		}
		else{
			mv.addObject("data", data);
		}
		
		mv.addObject("designDrawingNo", designDrawingNo);
		mv.addObject("whereDept", whereDept);
		
		
		return mv;
	}
	
	
	
	
	// 가공 내역 관리 >> 날짜 일괄 등록 팝업
	@RequestMapping("/popup/date_batch_input")
	public ModelAndView popupDateBatchInput(String callback){
		
		ModelAndView mv = new ModelAndView("production_schedule/process/popup_date_batch_input");
		mv.addObject("callback", callback);
		return mv;
	}	
	// 가공 내역 관리 >> 일괄 입력 팝업
	@RequestMapping("/popup/batch_input")
	public ModelAndView popupBatchInput(String callback){
		
		ModelAndView mv = new ModelAndView("production_schedule/process/popup_batch_input");
		mv.addObject("callback", callback);
		return mv;
	}	
	
	// 가공 내역 관리 >> 팝업 : 가공 취소 사유 입력하기
	@RequestMapping("/popup/cancel_reason")
	public ModelAndView popupCancelReason(String callback, String key){
		
		ModelAndView mv = new ModelAndView("production_schedule/process/popup_cancel_reason");
		mv.addObject("callback", callback);
		mv.addObject("key", key);
		return mv;
	}


	// 가공 내역 관리 >> 등록/취소 선택 처리
	@PostMapping(value="/update/lists", consumes= {"application/json"} )
	@ResponseBody 
	
	public StandardResponse updateLists(@RequestBody ProcessChangeDataForm form){
		StandardResponse res = null;
		try{
			if(form == null)
				throw new RuntimeException("데이터가 없습니다");
			
			log.info("가공_내역 :: data = " + form);

			if("REG".equals(form.getAction())){
				
				processService.prepareProcess(form.getData());
				
			}else if("CANCEL".equals(form.getAction())) {
				
				if(StringUtils.isEmpty(form.getReason()))
					throw new RuntimeException("취소 사유가 필요합니다");
				
				processService.markAsProcessCancelled2 ( form.getData(), form.getReason() );
				
			}else {
				throw new RuntimeException("잘못된 요청입니다[" + form.getAction() + "]");
			}

			res = StandardResponse.createSuccessResponse();
		}catch(RuntimeException e){
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e){			
			res = StandardResponse.createErrorResponse();
			log.error("가공 내역 수정 중 오류", e);
		}
		
		log.info("가공_내역 :: 등록/취소 결과 = " + res);
		return res;
	}
	
	// 가공 관리 - 외주 가공 발주 ------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/outsourcing")
	public ModelAndView manufactureOutsourcing(){
		
		ModelAndView mv = new ModelAndView("production_schedule/process/outsourcing");
		return mv;
	}
	
	@RequestMapping("/data/outsourcing")
	@ResponseBody
	public DataTableResponse dataOutsouring(Integer draw, Integer start, Integer length,
												String orderNoBase, String orderNoExtra, String drawingNo, 
												String partnerId, String device, String selectedCustomer ) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			res = processService.getOutsourcingProcessDetailList(start, length, partnerId, selectedCustomer, device, orderNoBase, orderNoExtra, drawingNo);
			res.setDraw(draw);
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return res;		
		
	}

	
	// 가공 관리 - 외주가공발주 - 팝업 : 견적/발주요청
	// /popup/request_outsourcing/estimates --> 견적
	// /popup/request_outsourcing/order --> 발주
	@RequestMapping("/popup/request_outsourcing/{type}")
	public ModelAndView popupRequestEstimates(@PathVariable("type") String type, Long[] id, String partnerId){
		log.debug("외주가공발주 - 종류 : " + type);
		if(!(type.equalsIgnoreCase("estimates") || type.equalsIgnoreCase("order")))
			return null;
			
		final boolean isEstimateMode = type.equalsIgnoreCase("estimates");
		
		if(id == null || id.length == 0){
			log.info("id 파라미터 없음");
			return null;
		}

		ModelAndView mv = new ModelAndView(isEstimateMode ? "production_schedule/process/popup_request_estimates" :
															"production_schedule/process/popup_request_order");
		mv.addObject("data", drawingService.selectDesignDrawingList(id));
		mv.addObject("mode", isEstimateMode);
		mv.addObject("label", isEstimateMode ? "견적" : "발주");
		mv.addObject("action", type);
		mv.addObject("partnerName", partnerService.getPartnerName(partnerId));
		mv.addObject("partnerId", partnerId);
		
		return mv;
	}	
	
	// 외주 견적 요청 처리
	@RequestMapping("/update/outsourcing/estimates")
	@ResponseBody
	public StandardResponse updateEstimates(RequestEstimateFormData data){
		log.debug("FormData = " + data);
		
		StandardResponse res = null;
		try{
			if(data.getJobDrawingId() == null || data.getJobDrawingId().length == 0 
					|| data.getDeliveryDate() == null || data.getDeliveryDate().length == 0 
					|| data.getJobDrawingId().length != data.getDeliveryDate().length)
				throw new RuntimeException("견적 요청할 대상이 없습니다");
			
			List<JobDesignDrawing> arr = drawingService.selectDesignDrawingList(data.getJobDrawingId());
			
			String outsourcingPartnerId = null;
			for(JobDesignDrawing drawing : arr){
				if(outsourcingPartnerId == null)
					outsourcingPartnerId = drawing.getOutsourcingPartnerId();
					
				else
					if(!outsourcingPartnerId.equalsIgnoreCase(drawing.getOutsourcingPartnerId()))
						throw new RuntimeException("다른 외주 업체가 포함돼 있습니다");
				
				processService.insDesignHistry(drawing.getId(), "B", loginDongleService.getLoginId(), "도면이 ["+drawingService.outScourcingPTNamefromHistory(drawing.getOutsourcingPartnerId())+ "] 으로 견젹요청 되었습니다.");
			}
			
			
			
			String estimateId = processService.makeEstimateRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl());
			log.debug("등록된 견적서ID = " + estimateId );
			res = StandardResponse.createResponse("OK");
			res.setValue(estimateId);
			
		}catch(RuntimeException e){
			log.error("견적 저장 중 오류.", e);
			res = StandardResponse.createResponse("ERROR", e.getMessage());
		}catch(Exception e){
			log.error("견적 저장 중 오류", e);
			res = StandardResponse.createResponse("ERROR", "내부 처리 중 오류");
		}
		return res;
	}
	
	// 외주 발주 요청 처리
	@RequestMapping("/update/outsourcing/order")
	@ResponseBody
	public StandardResponse updateProcessOrder(RequestEstimateFormData data){
		log.debug("외주 발주 요청 FormData = " + data);
		
		StandardResponse res = null;
		try{
			// 파라미터 체크
			
			if(data.getJobDrawingId() == null || data.getUnitPrice() == null ||  data.getDeliveryDate() == null )
				throw new RuntimeException("파라미터 일부가 없습니다");
			
			if(data.getJobDrawingId().length == 0 || data.getUnitPrice() == null || data.getUnitPrice().length == 0 
					|| data.getJobDrawingId().length != data.getDeliveryDate().length  || data.getJobDrawingId().length != data.getUnitPrice().length)
				throw new RuntimeException("파라미터 개수가 일치하지 않습니다");
			
			List<JobDesignDrawing> arr = drawingService.selectDesignDrawingList(data.getJobDrawingId());
			
			// 전부 같은 외주 업체로 나가는 건인지 확인
			String outsourcingPartnerId = null;
			for(JobDesignDrawing drawing : arr){
				if(outsourcingPartnerId == null)
					outsourcingPartnerId = drawing.getOutsourcingPartnerId();
				else
					if(!outsourcingPartnerId.equalsIgnoreCase(drawing.getOutsourcingPartnerId()))
						throw new RuntimeException("다른 외주 업체가 포함돼 있습니다");
				
				processService.insDesignHistry(drawing.getId(), "B", loginDongleService.getLoginId(), "도면이 ["+drawingService.outScourcingPTNamefromHistory(drawing.getOutsourcingPartnerId())+ "] 으로 발주요청 되었습니다.");
			}							
			
			// 발주
			String processOrderId = processService.makeProcessOrderRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl(), data.getUnitPrice()); // processService.makeEstimateRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl());
			log.info("등록된 발주ID = " + processOrderId );
			
			res = StandardResponse.createResponse("OK");
			res.setValue(processOrderId);
			
		}catch(RuntimeException e){
			log.error("외주 가공 요청 저장 중 로직 오류.", e);
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e){
			log.error("외주 가공 요청 저장 중 오류", e);
			res = StandardResponse.createErrorResponse();
		}
		return res;
	}
	
	// 외주 이후 재발주 요청 처리
	@RequestMapping("/reoutsourcing")
	@ResponseBody
	public StandardResponse reupdateProcessOrder(RequestEstimateFormData data){
		log.debug("외주 발주 요청 FormData = " + data);
		
	
			
		StandardResponse res = null;
		try{
			// 파라미터 체크
				
			if(data.getJobDrawingId() == null || data.getDrawingUrl() == null ||  data.getDeliveryDate() == null )
				throw new RuntimeException("파라미터 일부가 없습니다");
				
			if(data.getJobDrawingId().length == 0 
					|| data.getJobDrawingId().length != data.getDeliveryDate().length )
				throw new RuntimeException("파라미터 개수가 일치하지 않습니다");
			
			if (data.getOutPartnerId() == null)
				throw new RuntimeException("파라미터 일부가 없습니다");
			
			//List<JobDesignDrawing> arr = drawingService.selectDesignDrawingList(data.getJobDrawingId());
			System.out.println("############여기까지나오나?");	
			// 전부 같은 외주 업체로 나가는 건인지 확인
			String outsourcingPartnerId = data.getOutPartnerId();
			int inx = 0;
			System.out.println("############여기까지나오나2222222?");	
			Long outUid[] = data.getJobDrawingOutUid();
			
			if(outUid == null || outUid.length == 0) {
				String getWorkPosition[] = data.getWorkPosition();
				
				if(data.getJobDrawingId() == null || data.getUnitPrice() == null ||  data.getDeliveryDate() == null )
					throw new RuntimeException("파라미터 일부가 없습니다");
				
				if(data.getJobDrawingId().length == 0 || data.getUnitPrice() == null || data.getUnitPrice().length == 0 
						|| data.getJobDrawingId().length != data.getDeliveryDate().length  || data.getJobDrawingId().length != data.getUnitPrice().length)
					throw new RuntimeException("파라미터 개수가 일치하지 않습니다");
				
				List<JobDesignDrawing> arr = drawingService.selectDesignDrawingList(data.getJobDrawingId());
				
				
				
				// 전부 같은 외주 업체로 나가는 건인지 확인
				System.out.println("########## ID OneOne = "+ outsourcingPartnerId);
				for(JobDesignDrawing drawing : arr){
					
					drawing.setOutsourcingPartnerId(outsourcingPartnerId);
					drawing.setWorkPosition("Z");
					
					processService.setOutsourcingPartnerIdAndWorkPosition(drawing);
					
					/*
					if(outsourcingPartnerId == null)
						outsourcingPartnerId = drawing.getOutsourcingPartnerId();
					else
						if(!outsourcingPartnerId.equalsIgnoreCase(drawing.getOutsourcingPartnerId()))
							
							System.out.println("########## ID One = "+ outsourcingPartnerId);
							System.out.println("########## ID Two = "+ drawing.getOutsourcingPartnerId());
							throw new RuntimeException("다른 외주 업체가 포함돼 있습니다");
							*/
					processService.insDesignHistry(drawing.getId(), "B", loginDongleService.getLoginId(), "도면이 가공 입고 후 [" + drawingService.outScourcingPTNamefromHistory(outsourcingPartnerId) +"] 으로 사외 재가공 요청 되었습니다.");
				}							
				
				// 발주
				String processOrderId = processService.makeProcessOrderRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl(), data.getUnitPrice()); // processService.makeEstimateRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl());
				log.info("등록된 발주ID = " + processOrderId );
				
				res = StandardResponse.createResponse("OK");
				res.setValue(processOrderId);
				
				
				
				
			}
			
			else {
			
				String getWorkPosition[] = data.getWorkPosition();
				System.out.println("############여기까지나오나333333333?");
				System.out.println("get ID COUNT = "+data.getJobDrawingId().length);
				System.out.println("outUid COUNT = "+outUid.length);
		
				System.out.println("outUid[0]=" + outUid[0]);
				System.out.println("getWorkPosition[0]=" + getWorkPosition[0]);
				for(inx=0; inx<data.getJobDrawingId().length; inx++){
					System.out.println("%%%%%%%%%%%%");
					if ("O".equals(getWorkPosition[inx])) {
						System.out.println("outsourcingPartnerId=" + outsourcingPartnerId);
						System.out.println("getSingleOutsourcingList Uid=" + outUid[inx]);

						JobPartnerOutsourcingOrder outEntry = processOutsourceListDataService.getSingleOutsourcingList(outUid[inx]);
						if (outsourcingPartnerId.equals(outEntry.getPartnerId()))
						{
							throw new RuntimeException("동일한 업체에 추가 발주를 진행할 수 없습니다.");
						}
					}
				}							

				// 발주
				System.out.println("%%%%%%%%%%%%");
				System.out.println("%%%%%%%%%%%%이거찍힘??");
				System.out.println("%%%%%%%%%%%%");
			
				System.out.println("outsourcingPartnerId:" + outsourcingPartnerId);
				System.out.println("JobDrawingId:" + data.getJobDrawingId().length);
				System.out.println("DrawingUrl:" + data.getDrawingUrl().length);
				System.out.println("UnitPrice:" + data.getUnitPrice().length);
				System.out.println("JobDrawingOutUid:" + data.getJobDrawingOutUid().length);
				System.out.println("WorkPosition:" + data.getWorkPosition().length);

				String processOrderId = processService.MarkAsAddprocessingProductIn(outsourcingPartnerId, data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl(), data.getUnitPrice(), data.getJobDrawingOutUid());
				//processService.makeEstimateRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl());
				log.info("등록된 발주ID = " + processOrderId );
			
				System.out.println("%%%%%%%%%%%%");
				System.out.println("%%%%%%%%%%%%DB 들어갔다나옴??");
				System.out.println("%%%%%%%%%%%%");
			
				res = StandardResponse.createResponse("OK");
			
				System.out.println("%%%%%%%%%%%%");
				System.out.println("%%%%%%%%%%%%4번쨰 res.setValue 문제???");
				System.out.println("%%%%%%%%%%%%");
			
				res.setValue(processOrderId);
			
				System.out.println("%%%%%%%%%%%%");
				System.out.println("%%%%%%%%%%%%모든게 OKOKOKOKKOKOKO");
				System.out.println("%%%%%%%%%%%%");
				}
				
			}catch(RuntimeException e){
				log.error("외주 가공 요청 저장 중 로직 오류.", e);
				res = StandardResponse.createErrorResponse(e);
			}catch(Exception e){
				log.error("외주 가공 요청 저장 중 오류", e);
				res = StandardResponse.createErrorResponse();
			}
			return res;
		
		
		
	}
	
	// 후처리 이후 재발주 요청 처리
	@RequestMapping("/ppreoutsourcing")
	@ResponseBody
	public StandardResponse ppreupdateProcessOrder(RequestEstimateFormData data){
		log.debug("외주 발주 요청 FormData = " + data);
				
		StandardResponse res = null;
		try{
			// 파라미터 체크
					
			if(data.getJobDrawingId() == null || data.getDrawingUrl() == null ||  data.getDeliveryDate() == null )
				throw new RuntimeException("파라미터 일부가 없습니다");
					
			if(data.getJobDrawingId().length == 0 
					|| data.getJobDrawingId().length != data.getDeliveryDate().length )
				throw new RuntimeException("파라미터 개수가 일치하지 않습니다");
					
			List<JobDesignDrawing> arr = drawingService.selectDesignDrawingList(data.getJobDrawingId());
					
			// 전부 같은 외주 업체로 나가는 건인지 확인
			String outsourcingPartnerId = null;
			for(JobDesignDrawing drawing : arr){
				if(outsourcingPartnerId == null)
					outsourcingPartnerId = drawing.getOutsourcingPartnerId();
				else
					if(!outsourcingPartnerId.equalsIgnoreCase(drawing.getOutsourcingPartnerId()))
						throw new RuntimeException("다른 외주 업체가 포함돼 있습니다");
			}							
			/*		
			// 발주
			String processOrderId = processService.MarkAsAddprocessingProductIn(data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl(), data.getUnitPrice()); // processService.makeEstimateRequest(data.isWithpp(), data.getJobDrawingId(), data.getDeliveryDate(), data.getDrawingUrl());
			log.info("등록된 발주ID = " + processOrderId );
					
			res = StandardResponse.createResponse("OK");
			res.setValue(processOrderId);
			*/
					
		}catch(RuntimeException e){
			log.error("외주 가공 요청 저장 중 로직 오류.", e);
			res = StandardResponse.createErrorResponse(e);
		}catch(Exception e){
			log.error("외주 가공 요청 저장 중 오류", e);
			res = StandardResponse.createErrorResponse();
		}
		return res;
	}	
	
	// 가공 관리 - 가공 내역 관리 - 팝업 : 일괄 입력
	@RequestMapping("/popup/request_order")
	public ModelAndView requestOrder(){
		
		ModelAndView mv = new ModelAndView("production_schedule/process/popup_request_order");
		return mv;
	}		
	
	// 가공 관리 - 가공 발주 리스트	------------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/order_list")
	public ModelAndView orderList(String orderNoBase, String orderNoExtra, String drawingNo, 
			String outsourcingPartnerId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateTo){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "01", "03");
		ModelAndView mv = new ModelAndView("production_schedule/process/order_list");
		mv.addObject("isupdate", isupdate);	
		
		return mv;
	}
	
	// 가공 발주 리스트 + 발주 수정 데이터 리스트 
	@RequestMapping("/data/order_list")
	@ResponseBody
	public DataTableResponse dataOrderList(Integer draw, Integer start, Integer length, String orderNoBase, String orderNoExtra, String drawingNo, 
			String outsourcingPartnerId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateTo,
			@RequestParam(value="id", required=false) String originalIdList){
		
		DataTableResponse res = new DataTableResponse(); 
		
		try {
			long[] IDs = null;
			if(originalIdList != null)
				IDs = Stream.of ( originalIdList.split("[,/]") ).mapToLong(a -> Long.parseLong(a)).toArray();
			
			res = processOutsourceListDataService.getProcessOutsourceListData(start, length, orderNoBase, orderNoExtra, drawingNo, outsourcingPartnerId, orderDateFrom, orderDateTo, IDs);
			res.setDraw(draw);
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}		
		
		return res;
	}

	// 가공 관리 - 가공 발주 리스트 - 팝업 : 발주 수정a
	@RequestMapping("/popup/request_update_order")
	public ModelAndView requestUpdateOrder(String id){
		if(StringUtils.isEmpty(id))
			return null;
		
		ModelAndView mv = new ModelAndView("production_schedule/process/popup_request_update_order");
		mv.addObject("id", id);
		return mv;
	}
	
	// 일괄 발주 취소 API
	@RequestMapping("/abort_order")
	@ResponseBody
	public StandardResponse abortOrder(@RequestParam("id") String originalIdList, String reason) {
		
		StandardResponse res = new StandardResponse();
		try {

			// 형 변환
			long[] IDs = Stream.of ( originalIdList.split("[,/]") ).mapToLong(a -> Long.parseLong(a)).toArray();
			
			// 일괄 취소 처리
			processService.markAsProcessCancelled(IDs, reason);
			
			res = StandardResponse.createSuccessResponse();
			
		}catch(RuntimeException e) {
			res.setReason("ERROR");
			res.setReason(e.getMessage());
		} catch(Exception e) {
			res.setReason("ERROR");
			res.setReason("발주 취소 중 오류가 발생하였습니다");			
		}		
		
		return res;
	}
	
	SimpleDateFormat dateConverter = new SimpleDateFormat("yyyy-MM-dd");
	
	// 발주 수정
	@RequestMapping("/update_order")
	@ResponseBody
	public StandardResponse  updateOrder(Long[] uid, @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] outsourcingFinishDate, Long[] unitPrice ) {
		StandardResponse res = new StandardResponse();
		res.setResult("ERROR");
		try {
			if(uid == null || outsourcingFinishDate == null || unitPrice == null || uid.length == 0)
				throw new RuntimeException("변경할 대상이 없습니다");
						
			processOutsourceListDataService.updateOutsourcingOrder(uid, outsourcingFinishDate, unitPrice);
							
			res.setResult("OK");
		}catch(RuntimeException e) {
			res.setResult("ERROR");
			res.setReason(e.getMessage());
			
			e.printStackTrace();
		}catch(Exception e) {
			res.setResult("ERROR");
			res.setReason("서버 오류로 처리 할 수 없습니다");
			
			e.printStackTrace();
		}
		return res;
	}

	// 가공 관리 - 가공 및 검사 현황 ------------------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/status")
	public ModelAndView manufactureStatus(){
		
		ModelAndView mv = new ModelAndView("production_schedule/process/status");
		return mv;
	}
	
	@RequestMapping("/data/status")
	@ResponseBody
	public DataTableResponse dataStatusResponse(Integer draw, Integer start, Integer length, String orderNoBase, String orderNoExtra, String drawingNo, String outsourcingPartnerId,  
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateTo, String inProcessYN) {
		
		DataTableResponse res = new DataTableResponse();
		try {
			
			res = processService.getProcessStatusList(orderNoBase, orderNoExtra, drawingNo, outsourcingPartnerId, orderDateFrom, orderDateTo, draw, start, length, inProcessYN );
			res.setDraw(draw);
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return res;		
	}
	
	//가공 내역 관리 이동
	
	@RequestMapping("/data/statusEdit")
	@ResponseBody 
	public StandardResponse manufactureStatusEdit(@RequestParam("id") String id){
		
		
		StandardResponse res = new StandardResponse();
		try {
			
			Long rid = Long.parseLong(id);
			
			if(processService.whatKindOfProcess(rid).equals("I")) {
			
			
				if(processService.whatProcessStatus(rid).equals("대기")) {
					
					processService.updateDesigToProcessFirst(rid);

					System.out.println("&&&&&&&&& ID = "+id);
				
					res.setResult("OK");
				
				}else {
					
					throw new RuntimeException("사내 작업중/외주 건은 되돌릴 수 없습니다.");
					
				}
			
			
			}else {
				throw new RuntimeException("사내 작업중/외주 건은 되돌릴 수 없습니다.");
			}
			
		}catch(RuntimeException e) {
			System.out.println("설마 catch 여기 들어옴????");
			res.setReason("ERROR");
			res.setReason(e.getMessage());
		}catch(Exception e) {
			res.setReason("ERROR");
			res.setReason("사내 작업중/외주 건은 되돌릴 수 없습니다.");			
		}		 
		
		return res;
	}
	
	//가공내역 관리 이동 외주가공대기품
	@RequestMapping("/data/statusEditFromI")
	public String manufactureStatusEditFromI(@RequestParam("id") Long[] id){
		
		for(int i=0;i<id.length;i++) {
			
			processService.updateDesignOToProcessFirst(id[i]);
			
		}

		
		return "redirect:/ps/process/outsourcing";
	}

}
