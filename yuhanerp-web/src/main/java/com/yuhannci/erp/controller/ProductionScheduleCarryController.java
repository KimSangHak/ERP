package com.yuhannci.erp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.AndroidData;
import com.yuhannci.erp.model.CarryToEnum;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.FinishStatusEnum;
import com.yuhannci.erp.model.NGcarry;
import com.yuhannci.erp.model.StandardResponse;
import com.yuhannci.erp.model.StockListOrderNo;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobInspect;
import com.yuhannci.erp.model.db.JobMctHistory;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.service.DrawingService;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.PartnerService;
import com.yuhannci.erp.service.ProcessService;
import com.yuhannci.erp.service.PurchaseService;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.yuhannci.erp.service.JobOrderService;

import lombok.extern.slf4j.Slf4j;

// 생산 일정 관리
// 	입출고 관리

@Controller
@RequestMapping("/ps/carry")
@Slf4j
public class ProductionScheduleCarryController {

	@Autowired ProcessService processService;
	@Autowired MenuService menuService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired DrawingService drawingService;
	@Autowired PartnerService partnerService;
	@Autowired 	JobOrderService jobOrderService;
	@Autowired PurchaseService purchaseService;
	
	// 완품/가공 입고
	@RequestMapping("in")
	public ModelAndView normalIn(){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "02", "01");
		ModelAndView mv = new ModelAndView("production_schedule/carry/in");

		mv.addObject("isupdate", isupdate);
		return mv;
	}
	
	// 완품/가공 입고 -> 입고 내역 리스트
	@RequestMapping("/data/in")
	@ResponseBody
	public DataTableResponse dataIn(Integer draw, Integer start, Integer length
										, String outsourcingPartnerId
										, String orderNoBase, String orderNoExtra
										, String processInsourcing
										, String processOutsourcing
										, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateFrom
										, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateTo
										, String drawings) {
		
		DataTableResponse res = new DataTableResponse();
		
		try {
			long[] designDrawingIds = drawings != null ? Stream.of (drawings.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray() : null;
			
			res = processService.getCarryInList(draw, start, length, outsourcingPartnerId, 
												orderNoBase, orderNoExtra, null, 
												orderDateFrom, orderDateTo, 
												"Y".equals(processInsourcing), "Y".equals(processOutsourcing),
												designDrawingIds);
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		
		return res;
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
	
	//검사 'Y' 변경
	@RequestMapping("popup/checkTrue")
	public String checkTrue(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		
		int countDesignId = getCharNumber(id,',') + 1;
		String[] DesignIdList = id.split(",",countDesignId);
		
		for(String rid : DesignIdList) {
			
			String rrid = rid.replace(",","");
			
			Long realId = Long.parseLong(rrid.trim());
			
			processService.updateCheckTrueFromCarryIn(realId);
			
			processService.insDesignHistry(realId, "B", loginDongleService.getLoginId(), "도면이 생산관리 부서에서 검사필요 항목으로 변경 되었습니다.");
			
		}
	

		
		return "redirect:/ps/carry/in";
	}
	
	//사외 가공 입고후 사내 재가공
	@RequestMapping("popup/reprocessOutToIn")
	public String reprocessOutToIn(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		
		int countDesignId = getCharNumber(id,',') + 1;
		String[] DesignIdList = id.split(",",countDesignId);
		
		for(String rid : DesignIdList) {
			
			String rrid = rid.replace(",","");
			
			Long realId = Long.parseLong(rrid.trim());
			
			processService.updateReProcessOutToIn(realId);
			
			processService.insDesignHistry(realId, "B", loginDongleService.getLoginId(), "도면이 사외가공 입고 후 사내 재가공 요청되었습니다.");
			
		}
	

		
		return "redirect:/ps/carry/in";
	}
	
	// 완품/가공 입고 ==> "선택도면 완품 입고" 팝업
	@RequestMapping("popup/finishedIn")
	public ModelAndView finishedIn(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_finished_in");
		mv.addObject("id", id);
		mv.addObject("scene", "A");	// 완품입고
		mv.addObject("title", "완품");	// 완품입고
		return mv;
	}
	
	// 완품/가공 입고 ==> "선택도면 가공 입고" 팝업
	@RequestMapping("popup/processIn")
	public ModelAndView processIn(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_finished_in");
		mv.addObject("id", id);
		mv.addObject("scene", "B");	// 가공입고
		mv.addObject("title", "가공");	// 가공입고
		return mv;
	}
	
	// 완품/가공 입고 ==> "선택도면 후처리 입고" 팝업
	@RequestMapping("popup/postProcIn")
	public ModelAndView postProcIn(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_finished_in");
		mv.addObject("id", id);
		mv.addObject("scene", "C");	// 후처리입고
		mv.addObject("title", "후처리 가공");	// 후처리입고
			return mv;
	}
	
	// 완품/가공 입고 ==> "선택도면 가공 입고 후 재가공" 팝업
	@RequestMapping("popup/reprocessIn")
	public ModelAndView reprocessIn(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		String[] IdTokens = id.split(",");
		
		Long[] ids = new Long[IdTokens.length];
		
		for(int i=0;i<IdTokens.length;i++){
			if(StringUtils.isEmpty(IdTokens[i]))
				continue;
			ids[i] = ( Long.parseLong(IdTokens[i]) );
		}
		
			
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_reprocess_in");
		mv.addObject("seldata", partnerService.getProcessParnterComboList());
		mv.addObject("data", drawingService.selectDesignDrawingOutList(ids));
		mv.addObject("mode", false);
		mv.addObject("label", "발주");
		mv.addObject("action", "order");
		mv.addObject("id", id);

			return mv;
	}
	
	// 후처리/가공 입고 ==> "선택도면 가공 입고 후 재가공" 팝업
	@RequestMapping("popup/ppinprocessre")
	public ModelAndView ppinprocessre(String id) {
		if(StringUtils.isEmpty(id))
			return null;
			
		String[] IdTokens = id.split(",");
			
		Long[] ids = new Long[IdTokens.length];
			
		for(int i=0;i<IdTokens.length;i++){
			if(StringUtils.isEmpty(IdTokens[i]))
				continue;
			ids[i] = ( Long.parseLong(IdTokens[i]) );
		}
			
				
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_reprocess_in_pp");
		mv.addObject("data", drawingService.selectDesignDrawingList(ids));
		mv.addObject("mode", false);
		mv.addObject("label", "발주");
		mv.addObject("action", "order");
		mv.addObject("id", id);

			return mv;
	}
			
	
	
	// 완품/가공 입고 ==> "선택도면 가공 입고 후 재가공" 팝업
	@RequestMapping("popup/remake")
	public ModelAndView remake(@PathVariable("id") String id) {
		if(StringUtils.isEmpty(id))
			return null;
			
		String[] IdTokens = id.split(",");
			
		Long[] ids = new Long[IdTokens.length];
			
		for(int i=0;i<IdTokens.length;i++){
			if(StringUtils.isEmpty(IdTokens[i]))
				continue;
			ids[i] = ( Long.parseLong(IdTokens[i]) );
		}
			
				
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_reprocess_in");
		mv.addObject("data", drawingService.selectDesignDrawingList(ids));
		mv.addObject("mode", false);
		mv.addObject("label", "발주");
		mv.addObject("action", "order");
		mv.addObject("id", id);

			return mv;
	}
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	// 완품/가공 입고 처리
	@RequestMapping("perform/in")
	@ResponseBody
	public StandardResponse performFinishIn(String scene, String id, String partnerId, @RequestParam(required=false, value="coatingFinishPlanDate") String coatingFinishPlanDateList) {
		
		StandardResponse res = null;
		try {
			if(scene == null )
				throw new RuntimeException("처리 방법을 지정해주세요");
			
			if(id == null)
				throw new RuntimeException("처리할 대상이 없습니다");
			
			long[] designDrawingId = Stream.of (id.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
			
			if(designDrawingId == null || designDrawingId.length == 0)
				throw new RuntimeException("처리할 대상이 없습니다");
			
			if(scene.equalsIgnoreCase("A")) {
				processService.MarkAsFinishedProductIn(designDrawingId, loginDongleService.getLoginId());
				
				for(int i=0; i<designDrawingId.length; i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 완품입고 처리 되었습니다.");
				}
			}else if(scene.equalsIgnoreCase("B")) {
				
				processService.MarkAsProcessedProductIn(designDrawingId, loginDongleService.getLoginId());
				
				for(int i=0; i<designDrawingId.length; i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 가공입고 처리 되었습니다.");
				}
			}else if(scene.equals("C")){
				
				if(StringUtils.isEmpty(partnerId))
					throw new RuntimeException("후처리 업체를 선택해 주세요");
				
				if(StringUtils.isEmpty(coatingFinishPlanDateList))
					throw new RuntimeException("납기 예정일이 없습니다");
				
				Date[] coatingFinishPlanDate = Stream.of( coatingFinishPlanDateList.split("[,.]") ).map(a -> {
					try {
						return sdf.parse(a);
					} catch (ParseException e) {
						log.info("날짜[" + a + "] 형식에 오류가 있습니다", e);
					}
					return null;
				}).toArray(Date[]::new);
				
				if( Stream.of(coatingFinishPlanDate).anyMatch(a -> a == null) )
					throw new RuntimeException("잘못된 형식의 날짜가 입력되었습니다");
				
				System.out.println("********************");
				System.out.println("********************");
				System.out.println("********************");
				System.out.println("************"+coatingFinishPlanDate);
				System.out.println("********************");
				System.out.println("********************");
				System.out.println("********************");
				processService.MarkAsPostprocessingProductIn(designDrawingId, loginDongleService.getLoginId(), partnerId, coatingFinishPlanDate);
				
				for(int i=0; i<designDrawingId.length; i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 후처리 입고 처리 되었습니다.");
				}
			}
			
			res = StandardResponse.createResponse("OK");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			res = StandardResponse.createErrorResponse(ex);
		} catch(Exception e) {
			e.printStackTrace();
			res = StandardResponse.createErrorResponse();
		}
		
		return res;
	}
	
	// 후처리완료 입고	-----------------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/pp_in")
	public ModelAndView postprocessedIn(){
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "02", "02");
		
		ModelAndView mv = new ModelAndView("production_schedule/carry/pp_in");
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	// 후처리완료 입고 - 목록 조회
	@RequestMapping("/data/pp_in")
	@ResponseBody
	public DataTableResponse dataPostprocessedIn(Integer draw, Integer start, Integer length
													, String orderNoBase, String orderNoExtra
													, String outsourcingPartnerId
													, String processInsourcing, String processOutsourcing, String postprocessFinished, String inspectFinished, String inspectUnfinished
													, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateFrom, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDateTo) {
		DataTableResponse res = new DataTableResponse();
		try {

			boolean insourcingProcess = "Y".equals(processInsourcing);
			boolean outsourcingProcess = "Y".equals(processOutsourcing);
			
			res = processService.getPostprocessFinishedList(draw, start, length, outsourcingPartnerId, orderNoBase, orderNoExtra, null, orderDateFrom, orderDateTo, insourcingProcess, outsourcingProcess);

		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			log.error("후처리완료입고 목록 조회 중 오류", e);
		}catch(Exception e) {
			res.setErrorResponse();
			log.error("후처리완료입고 목록 조회 중 오류", e); 
		}
		
		return res;
	}
	
	@RequestMapping("/perform/finish_pp")
	@ResponseBody
	public StandardResponse performFinishPP(@RequestParam("id") String designDrawingIdList) {
		
		StandardResponse res = new StandardResponse();
		
		try {
			long[] designDrawingId = Stream.of (designDrawingIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();

			processService.updateCoatingFinished(designDrawingId, loginDongleService.getLoginId());
			
			for(int i=0; i<designDrawingId.length; i++) {
				processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 후처리 완료 입고 되었습니다.");
			}
			
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
	
	// 조립/검사 인계
	@RequestMapping("/assembly_check")
	public ModelAndView assembly_check(String partner, String orderNoBase, String orderNoExtra){
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "02", "04");
		ModelAndView mv = new ModelAndView("production_schedule/carry/assembly_check");
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	//가공품 Order 이관
	@RequestMapping("/moveOrder")
	public ModelAndView moveOrder(){
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "30", "02", "05");
		
		List<JobOrder> data =  processService.selectMovePossibleOrder();
		List<JobOrder> data2 =  processService.selectMovePossibleOrder2();
		
		int pageNo = 0;
		
		
		ModelAndView mv = new ModelAndView("production_schedule/carry/moveOrder");
		mv.addObject("isupdate", isupdate);
		mv.addObject("data", data);
		mv.addObject("data2", data2);
		mv.addObject("pageNo", pageNo);	
		return mv;
	}
	
	//이관 가공품 Order 완료 처리
	@RequestMapping("/moveOrder/completdo")
	public String moveOrderCompletDo(@RequestParam("id") Long id){
		
		processService.moveOrderCompletFromMM(id);
		
		return "redirect:/ps/carry/moveOrder";
	}

	
	
	
	//가공품 Order 이관 popUp
	@RequestMapping("/moveOrder/popup/{id}")
	public ModelAndView moveOrder_popup(@PathVariable("id") Long id){
		
		List<JobDesignDrawing> data =  processService.MoveDesignDrawingPopup(id);
		
		int pageNo = 0;
		
		List<StockListOrderNo> orderNodata = purchaseService.searchOrderNo();
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_moveOrder");
		
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		mv.addObject("orderNodata", orderNodata);
		mv.addObject("id", id);
		return mv;
	}
	
	public class XXXException extends Exception{
	    public XXXException() {}
	    public XXXException(String message) { super(message); }
	}

	
	//가공품 Order 이관 동작
	@RequestMapping("/moveOrder/do")
	public String moveOrder_do(@RequestParam("grandOrderId") Long grandOrderId,
			  				   @RequestParam("selectOrderId") Long selectOrderId,
			  				   @RequestParam("grandQuantity") int[] grandQuantity,
			  				   @RequestParam("quantity") int[] quantity,									 
			  				   @RequestParam("id") Long[] id){
		
		
		String selectOrderNo = processService.OrderIdToOrderNoFromMoverOrder(selectOrderId);
		String selectOrderNoBase = processService.OrderIdToOrderNoBaseFromMoverOrder(selectOrderId);
		String selectOrderNoExtra= processService.OrderIdToOrderNoExtraFromMoverOrder(selectOrderId);
		
		
		
		try {

			for(int i=0;i<id.length;i++) {
				
				Long nodeId = processService.jobDesignIdFromMove(selectOrderId, processService.selectDrawingNoFromMoveOrder(id[i]));
			
				String grandDrawingNo = processService.selectDrawingNoFromMoveOrder(id[i]);
			
				if((processService.countIsDrawingNoEqauls(selectOrderId, grandDrawingNo)) == 0) {
				
					throw new XXXException("1");
				}
			
				if(grandQuantity[i]<quantity[i]) {
					throw new XXXException("2");
				}
				
				if(quantity[i] == 0) {
					throw new XXXException("3");
				}
				
				if((processService.nodeOrderDesignqtyCount(nodeId)) < quantity[i]) {
					throw new XXXException("4");
				}

			}
			
			
			for(int i=0;i<id.length;i++) {
				
				Long nodeId = processService.jobDesignIdFromMove(selectOrderId, processService.selectDrawingNoFromMoveOrder(id[i]));
				
				
				
				if(grandQuantity[i]>quantity[i]) {
					
					List<JobMctHistory> jobMctHistoryData = processService.JobHistroyMctFromMoveAll(id[i]);
					
					for(JobMctHistory jobMctHistory : jobMctHistoryData) {
						
						
						
						if(jobMctHistory.getWorkQuantity() != 0) {
							
							
							processService.processSetWorkMinAll(jobMctHistory.getId());
							
							if(jobMctHistory.getWorkQuantity() == quantity[i]) {
								
								JobMctHistory insertJobMctHistory = new JobMctHistory();
								
								insertJobMctHistory.setMctId(jobMctHistory.getMctId());
								insertJobMctHistory.setUserId(jobMctHistory.getUserId());
								insertJobMctHistory.setDrawingIdmct(nodeId);
								insertJobMctHistory.setStartDatetime(jobMctHistory.getStartDatetime());
								insertJobMctHistory.setEndDatetime(jobMctHistory.getEndDatetime());
								insertJobMctHistory.setWorkMin(jobMctHistory.getWorkMin());
								insertJobMctHistory.setOrderType(jobMctHistory.getOrderType());
								insertJobMctHistory.setOrderNoBase(selectOrderNoBase);
								insertJobMctHistory.setOrderNoExtra(selectOrderNoExtra);
								insertJobMctHistory.setDrawingNo(jobMctHistory.getDrawingNo());
								insertJobMctHistory.setWorkQuantity(quantity[i]);
								insertJobMctHistory.setFinishStatus(jobMctHistory.getFinishStatus());
								insertJobMctHistory.setHoldReason(jobMctHistory.getHoldReason());
								
								processService.addJobMctHistoryFromMove(insertJobMctHistory);
								
								processService.deleteJobMctHistoryFromMove(jobMctHistory.getId());


							}
							
							else {
								
								Long grandWorkMin = jobMctHistory.getWorkMin();
								
								Long oneValue = grandWorkMin / jobMctHistory.getWorkQuantity();
								
								Long result = oneValue * quantity[i] ;
								
								if(jobMctHistory.getWorkQuantity()>quantity[i]) {
									
									JobMctHistory insertJobMctHistory = new JobMctHistory();
									
									insertJobMctHistory.setMctId(jobMctHistory.getMctId());
									insertJobMctHistory.setUserId(jobMctHistory.getUserId());
									insertJobMctHistory.setDrawingIdmct(nodeId);
									insertJobMctHistory.setStartDatetime(jobMctHistory.getStartDatetime());
									insertJobMctHistory.setEndDatetime(jobMctHistory.getEndDatetime());
									insertJobMctHistory.setWorkMin(result);
									insertJobMctHistory.setOrderType(jobMctHistory.getOrderType());
									insertJobMctHistory.setOrderNoBase(selectOrderNoBase);
									insertJobMctHistory.setOrderNoExtra(selectOrderNoExtra);
									insertJobMctHistory.setDrawingNo(jobMctHistory.getDrawingNo());
									insertJobMctHistory.setWorkQuantity(quantity[i]);
									insertJobMctHistory.setFinishStatus(jobMctHistory.getFinishStatus());
									insertJobMctHistory.setHoldReason(jobMctHistory.getHoldReason());
									
									processService.addJobMctHistoryFromMove(insertJobMctHistory);
									
									int workQuantity = jobMctHistory.getWorkQuantity() - quantity[i];
									
									processService.updateJobMctHistoryFromMove(jobMctHistory.getId(), workQuantity);
									
									
								}else if(jobMctHistory.getWorkQuantity()<quantity[i]) {
									
									JobMctHistory insertJobMctHistory = new JobMctHistory();
									
									insertJobMctHistory.setMctId(jobMctHistory.getMctId());
									insertJobMctHistory.setUserId(jobMctHistory.getUserId());
									insertJobMctHistory.setDrawingIdmct(nodeId);
									insertJobMctHistory.setStartDatetime(jobMctHistory.getStartDatetime());
									insertJobMctHistory.setEndDatetime(jobMctHistory.getEndDatetime());
									insertJobMctHistory.setWorkMin(result);
									insertJobMctHistory.setOrderType(jobMctHistory.getOrderType());
									insertJobMctHistory.setOrderNoBase(selectOrderNoBase);
									insertJobMctHistory.setOrderNoExtra(selectOrderNoExtra);
									insertJobMctHistory.setDrawingNo(jobMctHistory.getDrawingNo());
									insertJobMctHistory.setWorkQuantity(quantity[i]);
									insertJobMctHistory.setFinishStatus(jobMctHistory.getFinishStatus());
									insertJobMctHistory.setHoldReason(jobMctHistory.getHoldReason());
									
									processService.addJobMctHistoryFromMove(insertJobMctHistory);
									
									Long[] grandMctId = null;
									int caculatorQuantity = 0;
									int j = 0;
									
									List<JobMctHistory> jobMctHistoryData2 = processService.JobHistroyMctFromMoveAll(id[i]);
									
									
									for(JobMctHistory jobMctHistory2 : jobMctHistoryData2) {
										
										caculatorQuantity = caculatorQuantity + jobMctHistory2.getWorkQuantity();
										
										grandMctId[j] = jobMctHistory2.getId();
										
										if(caculatorQuantity == quantity[i]) {
											break;
										}
										else if(caculatorQuantity > quantity[i]) {
											
											processService.updateJobMctHistoryFromMove(jobMctHistory2.getId(), caculatorQuantity-quantity[i]);
											
											grandMctId[j] = 0L;
											
										}
										
										j++;
									}
									
									for(int a=0;a<grandMctId.length;a++) {
										if(grandMctId[a]==0L) {
											break;
										}
										
										processService.deleteJobMctHistoryFromMove(grandMctId[a]);
										
									}
								}					
								
							}	
						}
					}
					
					List<JobInspect> jobInspectData = new ArrayList<>();
					
					List<JobDesignDrawing> designData = processService.DrawingFromMoveSelectIdForInspect(grandOrderId);
					
					Long[] jobDrawingId = new Long[designData.size()];
					int q =0;
					
					for(JobDesignDrawing design : designData) {
						
						jobDrawingId[q] = design.getId();
						q++;
					}
					
	
					jobInspectData = processService.jobInspectFromMove(jobDrawingId);
					
					
					if(!(jobInspectData.isEmpty())) {
						
						for(JobInspect jobInspect : jobInspectData) {
							
							jobInspect.setId(0L);
							jobInspect.setJobDrawingId(nodeId);
							
							processService.addFromMoveForJobInspect(jobInspect);
							
						}
					}
					
					
					if((processService.nodeOrderDesignqtyCount(nodeId)) > quantity[i]) {
						
						//New 이관 받을 Order DesignDrawing quantity 만 변경 상태 그대로..
						
						processService.nodeOrderDesignDrawingSet(nodeId, quantity[i]);
						
						processService.grandOrderDesignDrawingSetWorkQty(id[i], quantity[i]);
						
						
						int grandqty = processService.grandOrderDesignSelectQtyFromMove(id[i]);
						int grandworkqty = processService.grandOrderDesignSelectWorkQtyFromMove(id[i]);
						
						
						
						if(grandqty <= 0 || grandworkqty<=0) {
							processService.grandOrderDesignDrawingSetCureent(id[i]);
						}
						
						
						

						
					}else if((processService.nodeOrderDesignqtyCount(nodeId)) == quantity[i]) {
						
						//New 이관 받을 Order DesignDrawing 이관 상태 완료 변경
						
						JobDesignDrawing grandDesign = processService.oneDesignSelectGrandid(id[i]);
						
						JobDesignDrawing nodeDesign = processService.oneDesignSelectNodeid(nodeId);
						
						
						nodeDesign.setWorkPosition(grandDesign.getWorkPosition());
						nodeDesign.setProcessCompleted("Y");
						nodeDesign.setProcessFinishDate(grandDesign.getProcessFinishDate());
						nodeDesign.setProcessFinishPlanDate(grandDesign.getProcessFinishDate());
						nodeDesign.setCoatingDate(grandDesign.getCoatingDate());
						nodeDesign.setCoatingPartnerId(grandDesign.getCoatingPartnerId());
						nodeDesign.setCoatingRequestId(grandDesign.getCoatingRequestId());
						nodeDesign.setCoatingStage(grandDesign.getCoatingStage());
						nodeDesign.setCoatingRequested(grandDesign.getCoatingRequested());
						nodeDesign.setChecking(grandDesign.getChecking());
						nodeDesign.setInspectId(grandDesign.getInspectId());
						nodeDesign.setInspectDate(grandDesign.getInspectDate());
						nodeDesign.setInspectPlanDate(grandDesign.getInspectPlanDate());
						nodeDesign.setHoldReason(grandDesign.getHoldReason());
						
						processService.nodeOrderDesignDrawingSetAll(nodeDesign);
						
						
						processService.grandOrderDesignDrawingSetWorkQty(id[i], quantity[i]);
						
						
						int grandqty = processService.grandOrderDesignSelectQtyFromMove(id[i]);
						int grandworkqty = processService.grandOrderDesignSelectWorkQtyFromMove(id[i]);
						
						
						
						if(grandqty <= 0 || grandworkqty<=0) {
							processService.grandOrderDesignDrawingSetCureent(id[i]);
						}
						

						
					}
				
					

					
					
				}else if(grandQuantity[i] == quantity[i]) {
					
					
					List<JobMctHistory> jobMctHistoryData = processService.JobHistroyMctFromMoveAll(id[i]);
					
					
					for(JobMctHistory jobMctHistory : jobMctHistoryData) {
						
						
						JobMctHistory insertJobMctHistory = new JobMctHistory();
						
						insertJobMctHistory.setMctId(jobMctHistory.getMctId());
						insertJobMctHistory.setUserId(jobMctHistory.getUserId());
						insertJobMctHistory.setDrawingIdmct(nodeId);
						insertJobMctHistory.setStartDatetime(jobMctHistory.getStartDatetime());
						insertJobMctHistory.setEndDatetime(jobMctHistory.getEndDatetime());
						insertJobMctHistory.setWorkMin(jobMctHistory.getWorkMin());
						insertJobMctHistory.setOrderType(jobMctHistory.getOrderType());
						insertJobMctHistory.setOrderNoBase(selectOrderNoBase);
						insertJobMctHistory.setOrderNoExtra(selectOrderNoExtra);
						insertJobMctHistory.setDrawingNo(jobMctHistory.getDrawingNo());
						insertJobMctHistory.setWorkQuantity(quantity[i]);
						insertJobMctHistory.setFinishStatus(jobMctHistory.getFinishStatus());
						insertJobMctHistory.setHoldReason(jobMctHistory.getHoldReason());
						
						processService.addJobMctHistoryFromMove(insertJobMctHistory);
						
						processService.deleteJobMctHistoryFromMove(jobMctHistory.getId());
						
					}
					
					List<JobInspect> jobInspectData = new ArrayList<>();
					
					List<JobDesignDrawing> designData = processService.DrawingFromMoveSelectIdForInspect(grandOrderId);
					
					Long[] jobDrawingId = new Long[designData.size()];
					
				
					int q =0;
					
					for(JobDesignDrawing design : designData) {
						
						jobDrawingId[q] = design.getId();
						q++;
					}
					
	
					jobInspectData = processService.jobInspectFromMove(jobDrawingId);
					
					
					if(!(jobInspectData.isEmpty())) {
						
						for(JobInspect jobInspect : jobInspectData) {
							
							jobInspect.setId(0L);
							jobInspect.setJobDrawingId(nodeId);
							
							processService.addFromMoveForJobInspect(jobInspect);
							
						}
					}
					
					
					
					if((processService.nodeOrderDesignqtyCount(nodeId)) > quantity[i]) {
						
						//New 이관 받을 Order DesignDrawing quantity 만 변경 상태 그대로..
						
						processService.nodeOrderDesignDrawingSet(nodeId, quantity[i]);
						
						
						processService.grandOrderDesignDrawingSetWorkQty(id[i], quantity[i]);
						
						
						int grandqty = processService.grandOrderDesignSelectQtyFromMove(id[i]);
						int grandworkqty = processService.grandOrderDesignSelectWorkQtyFromMove(id[i]);
						
						
						
						if(grandqty <= 0 || grandworkqty<=0) {
							processService.grandOrderDesignDrawingSetCureent(id[i]);
						}
						
						
						

						
					}else if((processService.nodeOrderDesignqtyCount(nodeId)) == quantity[i]) {
						
						//New 이관 받을 Order DesignDrawing 이관 상태 완료 변경
						
						JobDesignDrawing grandDesign = processService.oneDesignSelectGrandid(id[i]);
						
						JobDesignDrawing nodeDesign = processService.oneDesignSelectNodeid(nodeId);
						
						
						nodeDesign.setWorkPosition(grandDesign.getWorkPosition());
						nodeDesign.setProcessCompleted("Y");
						nodeDesign.setProcessFinishDate(grandDesign.getProcessFinishDate());
						nodeDesign.setProcessFinishPlanDate(grandDesign.getProcessFinishDate());
						nodeDesign.setCoatingDate(grandDesign.getCoatingDate());
						nodeDesign.setCoatingPartnerId(grandDesign.getCoatingPartnerId());
						nodeDesign.setCoatingRequestId(grandDesign.getCoatingRequestId());
						nodeDesign.setCoatingStage(grandDesign.getCoatingStage());
						nodeDesign.setCoatingRequested(grandDesign.getCoatingRequested());
						nodeDesign.setChecking(grandDesign.getChecking());
						nodeDesign.setInspectId(grandDesign.getInspectId());
						nodeDesign.setInspectDate(grandDesign.getInspectDate());
						nodeDesign.setInspectPlanDate(grandDesign.getInspectPlanDate());
						nodeDesign.setHoldReason(grandDesign.getHoldReason());
						
						processService.nodeOrderDesignDrawingSetAll(nodeDesign);
						
						
						processService.grandOrderDesignDrawingSetWorkQty(id[i], quantity[i]);
						
						
						int grandqty = processService.grandOrderDesignSelectQtyFromMove(id[i]);
						int grandworkqty = processService.grandOrderDesignSelectWorkQtyFromMove(id[i]);
						
						
						
						if(grandqty <= 0 || grandworkqty<=0) {
							processService.grandOrderDesignDrawingSetCureent(id[i]);
						}
						
						

						
					}

					
				}
				
				
				
				
			}
		}catch(XXXException e) {
			
			System.out.println("이관 에러 = " + e);
			
			String pre = e.toString(); 
			
			pre.trim();
			
			String result = pre.replaceAll("[^0-9]", "");
			
			result.trim();
			
			
			return "redirect:/ps/carry/moveOrder/erro/"+result;
		}
		
	
		

		return "redirect:/ps/carry/moveOrder";
	}
	
	//이관 에러 page
	@RequestMapping("/moveOrder/erro/{result}")
	public ModelAndView moveOrder_erro(@PathVariable("result") String result){
		
		
		ModelAndView mv = new ModelAndView("production_schedule/carry/moveErroPage");
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	// "조립/검사 인계" 에 표시할 데이터
	// 후처리 완료 입고된 도면 + 완품 입고된 도면 + (후처리 스킵 + 가공 완료)  
	@RequestMapping("/data/assembly_check")
	@ResponseBody
	public DataTableResponse dataAssemblyCheck(Integer draw, Integer start, Integer length,
												String orderNoBase, String orderNoExtra, String partnerId,
												@DateTimeFormat(pattern="yyyy-MM-dd") Date orderDate1, @DateTimeFormat(pattern="yyyy-MM-dd") Date orderDate2, 
												boolean processInsourcing, boolean processOutsourcing, boolean postprocessFinished, boolean checkFinished, boolean checkUnfinished, boolean checkNg) {
		DataTableResponse res = new DataTableResponse();
		try {			
			String workPositionFilter = (processInsourcing && !processOutsourcing) ? "I" :
										(!processInsourcing && processOutsourcing) ? "O" : null;
			
			if(StringUtils.isEmpty(partnerId))
				partnerId = null;
			
			final FinishStatusEnum finishStatus = checkFinished && !checkUnfinished ? FinishStatusEnum.FINISHED :
											!checkFinished && checkUnfinished ? FinishStatusEnum.UNFINISHED :
												FinishStatusEnum.BOTH;
			
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  = "+checkNg);
			
			log.info("조립검사인계_목록조회 :: finishStatus = " + finishStatus + ", workPositionFilter = " + workPositionFilter);
			res = processService.getAssembleCheckTargetList(start, length, orderNoBase, orderNoExtra, partnerId, orderDate1, orderDate2, workPositionFilter, postprocessFinished, finishStatus, checkNg);
			res.setDraw(draw);
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			log.error("후처리완료입고 목록 조회 중 오류", e);
		}catch(Exception e) {
			e.printStackTrace();
			res.setError("목록 조회 중 오류가 발생하였습니다");
			log.error("후처리완료입고 목록 조회 중 오류", e); 
		}
		
		return res;
	}
	
	// 조립/검사 인계 -> 검사부/조립부 인계 팝업
	@RequestMapping("/popup/carry/{dept}")
	public ModelAndView popupCarry(@RequestParam("id") String designDrawingIdList, @PathVariable("dept") CarryToEnum dept ) {
			
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_carry");
		mv.addObject("id", designDrawingIdList);
		mv.addObject("dept", dept);
		mv.addObject("deptName", dept.getLabel());
		
		return mv;		
	}
	
	//NG 재가공 popup
	@RequestMapping("/popup/carryNG")
	public ModelAndView popupCarryNG(@RequestParam("id") String id) {
		
		
		
		if(StringUtils.isEmpty(id))
			return null;
		
		
		int countDesignId = getCharNumber(id,',') + 1;
		String[] DesignIdList = id.split(",",countDesignId);
		List<Long> realIds = new ArrayList<>();
		
		for(String rid : DesignIdList) {
			
			String rrid = rid.replace(",","");
			
			Long realId = Long.parseLong(rrid.trim());

			realIds.add(realId);

		}
		
		
		try {
			
			for(Long real : realIds) {
				
				if((processService.selectInsepectResult(real)).equals("PASS"))
					throw new Exception();
				if((processService.selectDrawingKindworkPositon(real)).equals("O"))
					throw new Exception();
	
			}
			
			
		}catch(Exception e) {
			
			
			ModelAndView mv = new ModelAndView("production_schedule/carry/popup_carrNgErro");
			mv.addObject("text", "사외가공품 혹은 검사가 완료 되지 않은 가공품 은 NG로 인한 재가공을 할 수 없습니다.");
			return mv;
			
			
		}
		
		
		List<NGcarry> ngcarry = new ArrayList<>();
		
		for(Long real : realIds) {

			ngcarry.add(processService.selectNGcarry(real));

		}
		
		int pageNo = 0;
		ModelAndView mv = new ModelAndView("production_schedule/carry/popup_carryNG");
		mv.addObject("item", ngcarry);
		mv.addObject("pageNo", pageNo);
		
		return mv;		
	}
	
	//NG 재가공 처리
	@RequestMapping("/carryNG/do")
	public String carryNGDo(@RequestParam("drawingId")Long[] drawingId,
							@RequestParam("rqty")int[] rqty) {
		
		for(int i=0;i<drawingId.length;i++) {
			
			System.out.println("!!DrawingId = " + drawingId[i]);
			System.out.println("!!rqty = " + rqty[i]);
			
			processService.insDesignHistry(drawingId[i], "F", loginDongleService.getLoginId(), "NG 처리로 인하여 "+rqty[i]+"개의 수량을 사내 재가공 요청했습니다.");
			processService.updateDesignfromNGcarry(drawingId[i], rqty[i]);
			
		}
		
		
	

		
		return "redirect:/ps/carry/assembly_check";
	}
	
	
	// 인계 page 검사 항목 Y 변경
	@RequestMapping("/checkEditAssembly")
	public String checkEditAssembly(@RequestParam("id") String id) {
		if(StringUtils.isEmpty(id))
			return null;
		
		
		int countDesignId = getCharNumber(id,',') + 1;
		String[] DesignIdList = id.split(",",countDesignId);
		
		for(String rid : DesignIdList) {
			
			String rrid = rid.replace(",","");
			
			Long realId = Long.parseLong(rrid.trim());
			
			processService.updateCheckTrueFromCarryIn(realId);
			
			processService.insDesignHistry(realId, "B", loginDongleService.getLoginId(), "도면이 생산관리부서에서 검사필요 항목으로 변경 되었습니다.");
			
			
		}
	

		
		return "redirect:/ps/carry/assembly_check";
	}
	
	// 검사부/조립부 인계 처리
	@RequestMapping("/update/carry")
	@ResponseBody
	public StandardResponse updateCarry(@RequestParam("id") String designDrawingIdList, String from, CarryToEnum dept) {
		StandardResponse res = new StandardResponse();
		
		try {
			if(StringUtils.isEmpty(from))
				throw new RuntimeException("인계자를 지정해 주세요");
			if(dept == null)
				throw new RuntimeException("대상 부서 정보를 찾을 수 없습니다");

			long[] designDrawingId = Stream.of (designDrawingIdList.split("[,/]")).mapToLong(a -> Long.parseLong(a)).toArray();
			log.info("도면 목록 " + Arrays.toString(designDrawingId) + " ==> 목적지 부서 : " + dept + ", from=" + from );

			if(dept == CarryToEnum.INSPECT) {
				processService.carryToCheckDept(designDrawingId, from);
				
				for(int i=0; i<designDrawingId.length;i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 검사부서로 인계 되었습니다.");
				}
			}
			else if(dept == CarryToEnum.MC){
				processService.carryToMC(designDrawingId, from);
				
				for(int i=0; i<designDrawingId.length;i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 가공부서로 인계 되었습니다.");
				}
			}
			else if(dept == CarryToEnum.TS) {
				processService.carryToTS(designDrawingId, from);
				
				for(int i=0; i<designDrawingId.length;i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 엽업부서로 인계 되었습니다.");
				}
			}
			else if(dept == CarryToEnum.T1) {
				processService.carryToT1(designDrawingId, from);
				
				for(int i=0; i<designDrawingId.length;i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 설계부서로 인계 되었습니다.");
				}
			}
			
			else if(dept == CarryToEnum.PQ) {
				processService.carryToPQ(designDrawingId, from);
				
				for(int i=0; i<designDrawingId.length;i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 구매부서로 인계 되었습니다.");
				}
			}
			
			else {
				processService.carryToAssemblyDept(designDrawingId, from);
				
				for(int i=0; i<designDrawingId.length;i++) {
					processService.insDesignHistry(designDrawingId[i], "B", loginDongleService.getLoginId(), "도면이 조립부서로 인계 되었습니다.");
				}
			}
			
			res.setResult("OK");
			
		}catch(RuntimeException e) {
			res = StandardResponse.createResponse("ERROR", e.getMessage());
			e.printStackTrace();
			
		}catch(Exception e) {
			res = StandardResponse.createResponse("ERROR", "서버 오류로 처리 할 수 없습니다");
			e.printStackTrace();
		}
		
		return res;
	}
	

	@RequestMapping("/entering_list")
	public ModelAndView enteringList(Long key, String drawingNo, String outPartnerId)
	{
		Long adjustedKey = (key==null) ? 0:key;
		String adjustedDrawingNo = !StringUtils.isEmpty(drawingNo) ? drawingNo.trim() : null;
		String adjustedOutPartnerId = !StringUtils.isEmpty(outPartnerId) ? outPartnerId.trim() : null;
		if (adjustedDrawingNo!=null) {
			if (adjustedDrawingNo=="" || adjustedDrawingNo.length() <=0)
				adjustedDrawingNo = null;
		}
		if (adjustedOutPartnerId!=null) {
			if (adjustedOutPartnerId=="" || adjustedOutPartnerId.length() <=0)
				adjustedOutPartnerId = null;
		}

		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "40", "03", "01");

		ModelAndView mv = new ModelAndView("production_schedule/carry/entering_list");
		mv.addObject("items", processService.getEnteringList(adjustedKey, adjustedDrawingNo, adjustedOutPartnerId));
		//mv.addObject("order", jobOrderService.searchJob(adjustedKey));
		mv.addObject("isupdate", isupdate);
		return mv;
	}
	
	
}
