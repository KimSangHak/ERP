package com.yuhannci.erp.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.CarryInOutMapper;
import com.yuhannci.erp.mapper.InspectMapper;
import com.yuhannci.erp.mapper.JobDesignDrawingEstimateMapper;
import com.yuhannci.erp.mapper.JobDesignDrawingMapper;
import com.yuhannci.erp.mapper.ProcessManageMapper;
import com.yuhannci.erp.mapper.TransitionMapper;
import com.yuhannci.erp.mapper.QcViewDataMapper;
import com.yuhannci.erp.mapper.DrawingRecordMapper;
import com.yuhannci.erp.model.CarryInEntry;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.DesignHistorywhere;
import com.yuhannci.erp.model.DrawingInspect;
import com.yuhannci.erp.model.FinishStatusEnum;
import com.yuhannci.erp.model.OutsourcingOrderTypeEnum;
import com.yuhannci.erp.model.PostprocessFinishedEntry;

import com.yuhannci.erp.model.ProcessChangeDataFormEntry;
import com.yuhannci.erp.model.ProcessHisSearchForm;
import com.yuhannci.erp.model.ProcessingAndCheckListEntry;
import com.yuhannci.erp.model.QcFinishListData;
import com.yuhannci.erp.model.db.DesignDrawingHistory;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobInspect;
import com.yuhannci.erp.model.db.JobMctHistory;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.db.TransitionProcessing;
import com.yuhannci.erp.model.JobProcessList;
import com.yuhannci.erp.model.NGcarry;

import lombok.extern.slf4j.Slf4j;

// 가공 관련된 서비스

@Service
@Slf4j
public class ProcessService {
	
	@Autowired ProcessManageMapper processManageMapper;
	@Autowired JobDesignDrawingMapper jobDesignDrawingMapper;
	@Autowired JobDesignDrawingEstimateMapper jobDesignDrawingEstimateMapper; 
	@Autowired CarryInOutMapper carryInOutMapper;
	@Autowired InspectMapper inspectMapper;
	@Autowired DrawingRecordMapper drawingRecordMapper;
	@Autowired TransitionMapper TransitionMapper;
	@Autowired ProcessService processService;
	@Autowired	LoginDongleService loginDongleService;
	
	// 가공 내역 리스트
	public DataTableResponse getProcessReadyDetailList(Integer draw, Integer start, Integer length, 
															String selectedCustomer, String device, String orderNoBase, String orderNoExtra, String drawingNo){
		DataTableResponse res = new DataTableResponse();
		
		try{
			orderNoBase = StringUtils.isEmpty(orderNoBase) ? null : orderNoBase;
			orderNoExtra = StringUtils.isEmpty(orderNoExtra) ? null : orderNoExtra;
			drawingNo = StringUtils.isEmpty(drawingNo) ? null : drawingNo;
			
			// 도면 번호에 xxxxx-yyyy 형태로 입력된 경우, yyyy 를 도면순번으로 바꿔서 검색
			if( !StringUtils.isEmpty(orderNoExtra) && StringUtils.isEmpty(drawingNo)){
				drawingNo = orderNoExtra;
				orderNoExtra = null;
			}
			
			device = StringUtils.isEmpty(device) ? null : device;
			selectedCustomer = StringUtils.isEmpty(selectedCustomer) ? null : selectedCustomer;
			
			log.info("검색중인 가공 내역 관리. 도면번호=" + orderNoBase + "/" + orderNoExtra + "/" + drawingNo + ", device = " + device + ", selectedCustomer = " + selectedCustomer);
			
			res.setData( processManageMapper.selectProcessReadyDetailList(start, length, selectedCustomer, device, orderNoBase, orderNoExtra, drawingNo) );
			res.setRecordsTotal( processManageMapper.selectProcessReadyDetailListCount(selectedCustomer, device, orderNoBase, orderNoExtra, drawingNo) );
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			res.setErrorResponse(e);
			
		} catch(Exception e){
			log.error("내역 검색 중 오류", e);
			res.setErrorResponse();
		}		
				
		return res;		
	}
	
	@Transactional
	public void prepareProcess(ProcessChangeDataFormEntry[] data) {
		
		for(ProcessChangeDataFormEntry entry : data) {
		
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(entry.getId());
			if(item == null)
				throw new RuntimeException("지정된 설계도 정보[" + entry.getId() + "] 를 찾을 수 없습니다");
			
			boolean ready = item.getCurrentStage().equals("B") && item.getWorkStatus().equalsIgnoreCase("R");
			if(!ready)
				throw new RuntimeException("이미 상태가 변경된 도면입니다");
			
			if("I".equals(entry.getWorkPosition())) {
				// 사내 가공
				processManageMapper.assignToInnerProcess( entry.getId(), entry.getFinishDate(), entry.getCoatingDate(), entry.getInspectDate() );
				processService.insDesignHistry(entry.getId(), "B", loginDongleService.getLoginId(), "도면이 사내가공 등록 되었습니다.");
				
			}else if("O".equals(entry.getWorkPosition())) {
				// 외주 가공 예정
				
				if(StringUtils.isEmpty(entry.getOutsourcingPartnerId()))
					throw new RuntimeException("외주 가공 업체를 선택해 주세요");
				
				processManageMapper.assignToPartnerProcess(entry.getId(), entry.getOutsourcingPartnerId(), entry.getFinishDate(), entry.getCoatingDate(), entry.getInspectDate());
				processService.insDesignHistry(entry.getId(), "B", loginDongleService.getLoginId(), "도면이 사외가공 등록 되었습니다.");
				
			}else if("S".equals(entry.getWorkPosition())){
				// 표준품
				processManageMapper.assignToStandardProduct(entry.getId(), entry.getFinishDate(), entry.getCoatingDate(), entry.getInspectDate());
				processService.insDesignHistry(entry.getId(), "B", loginDongleService.getLoginId(), "도면이 표준품 등록 되었습니다.");
			} else if("D".equals(entry.getWorkPosition())){
				// 표준품 가공
				processManageMapper.assignToStandardProductWithProcess(entry.getId(), entry.getFinishDate(), entry.getCoatingDate(), entry.getInspectDate());
				processService.insDesignHistry(entry.getId(), "B", loginDongleService.getLoginId(), "도면이 표준품가공 등록 되었습니다.");
			}else {
				throw new RuntimeException("잘못된 작업 위치 상태입니다 [" + entry.getWorkPosition() + "]");
			}
		}		
	}
	
	public void markAsProcessCancelled(ProcessChangeDataFormEntry[] data, String reason){
		
		long[] designDrawingIds = Stream.of(data).mapToLong(a -> a.getId()).toArray();
		
		markAsProcessCancelled(designDrawingIds, reason);
	}
	
	public void markAsProcessCancelled2(ProcessChangeDataFormEntry[] data, String reason){
		
		long[] designDrawingIds = Stream.of(data).mapToLong(a -> a.getId()).toArray();
		
		markAsProcessCancelled2(designDrawingIds, reason);
		
		
	}

	@Transactional
	public void markAsProcessCancelled(long[] data, String reason){
		
		for(long entry : data) {
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(entry);
			if(item == null)
				throw new RuntimeException("지정된 설계도 정보[" + entry + "] 를 찾을 수 없습니다");
			 
			if(!(item.getCurrentStage().equalsIgnoreCase("C")))
				throw new RuntimeException("가공중인 것만 취소할 수 있습니다.");
			
			if(/*item.getCurrentStage().equalsIgnoreCase("C") && */ item.getWorkStatus().equalsIgnoreCase("C"))
				throw new RuntimeException("이미 취소된 건입니다");
					
			processManageMapper.cancelProcessRequest( entry, reason);
			
			processService.insDesignHistry(entry, "B", loginDongleService.getLoginId(), "도면이 가공취소 되었습니다.");
			
			Long uid = processManageMapper.selectedUIDFromOutsourcing(entry);
			
			processManageMapper.deletedOutsourcingFromUID(uid);
			
			log.info("도면 [" + item.getId() + "] 취소 처리 완료");			
		}
	}
	
	@Transactional
	public void markAsProcessCancelled2(long[] data, String reason){
		
		for(long entry : data) {
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(entry);
			if(item == null)
				throw new RuntimeException("지정된 설계도 정보[" + entry + "] 를 찾을 수 없습니다");
			 
			if(item.getCurrentStage().equalsIgnoreCase("C"))
				throw new RuntimeException("가공중인 건은  취소 할 수 없습니다");
			
			if(/*item.getCurrentStage().equalsIgnoreCase("C") && */ item.getWorkStatus().equalsIgnoreCase("C"))
				throw new RuntimeException("이미 취소된 건입니다");
					
			processManageMapper.cancelProcessRequest2( entry, reason);
			
			Long uid = processManageMapper.selectedUIDFromOutsourcing(entry);
			
			processManageMapper.deletedOutsourcingFromUID(uid);
			
			log.info("도면 [" + item.getId() + "] 취소 처리 완료");			
		}
	}
	/*
	 * 완품 가공 입고 처리
	 */
	@Transactional
	public void MarkAsFinishedProductIn(long[] designDrawingId, String loginUserId) {
		
		final Date now = new Date();
		
		for (Long id : designDrawingId) {
			
			// 완품 가공 입고 처리 할 수 있는지 판단
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(item == null)
				throw new RuntimeException("지정된 순번(" + id + ")의 도면을 찾을 수 없습니다");
			if(!"C".equals(item.getCurrentStage()))
				throw new RuntimeException("가공 단계에 있는 도면만 처리 할 수 있습니다");
			
			// 외주 가공 여부
			boolean outsourcingProcessed = item.getWorkPosition().equalsIgnoreCase("O");
			
			// 후처리 요청 여부
			boolean postprocessingRequested = "Y".equalsIgnoreCase(item.getCoatingRequested());
			
			System.out.println("!!!!!!!!!! REQUEST = "+postprocessingRequested);
			System.out.println("!!!!!!!!!! CoatingRequest = "+item.getCoatingRequested());
			
			// 후처리 필요
			boolean needPostprocessing = !StringUtils.isEmpty( item.getPostprocessing() );
			
			log.info("도면[" + item.getId() + "] ==> outsourcingProcessed = " + outsourcingProcessed + ", postprocessingRequested = " + postprocessingRequested + ", needPostprocessing = " + needPostprocessing );
			
			if(outsourcingProcessed) {
				
				if(StringUtils.isEmpty(item.getOutsourcingPartnerId()))
					throw new RuntimeException("외주 가공사 정보가 없습니다");
				if(StringUtils.isEmpty(item.getOutsourcingRequestId()))
					throw new RuntimeException("외주 가공 발주 식별자가 없습니다");
				
				if(postprocessingRequested) {
					
					// current_stage = 'B', work_status = 'R', coating_date = now(), finish_date = now(), coating_stage = 'Y' , process_completed = 'Y'
					carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", true, now);
					
					System.out.println("TRUE?TRUE?TRUE?TRUE?TRUE?TRUE?TRUE?");
				}else {
				
					if(needPostprocessing) {
						
						// current_stage = 'B',  process_completed = 'Y', finish_Date = now(), coating_stage = 'S', work_status = 'R'						
						carryInOutMapper.updateProcessStatus(id, "B", "R", "S", "Y", true, now);
						System.out.println("FALSE?FALSE?FALSE?FALSE?FALSE?FALSE?FALSE?");
					}else {
						
						// current_stage = 'B', process_completed = 'Y', finish_date = now(), coating_stage = 'Y', work_status = 'R'						
						carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", true, now);
					}					
				}	
				
				log.info("외주 가공 요청 원장 변경. 도면=" + item.getId() + ", 요청ID=" + item.getOutsourcingRequestId() + ", 파트너ID=" + item.getOutsourcingPartnerId());
				
				carryInOutMapper.updatePartnerOutsourcingOrderFinished(now, item.getOutsourcingRequestId(), item.getId(), item.getOutsourcingPartnerId());

			}else {
				
				if(needPostprocessing) {
					
					// current_stage = 'B', process_completed = 'Y', coating_stage = 'S', work_status = 'R'					
					carryInOutMapper.updateProcessStatus(id, "B", "R", "S", "Y", false, now);
					
				}else {
					
					// current_stage = 'B', process_completed = 'Y', coating_stage = 'Y', work_status = 'R'
					carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", false, now);
				}				
			}			
		}		
	}
	
	/*
	 * 가공 입고 처리
	 */
	@Transactional
	public void MarkAsProcessedProductIn(long[] designDrawingId, String loginUserId) {
		
		final Date now = new Date();
		
		for (Long id : designDrawingId) {
			
			// 완품 가공 입고 처리 할 수 있는지 판단
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(item == null)
				throw new RuntimeException("지정된 순번(" + id + ")의 도면을 찾을 수 없습니다");
			if(!"C".equals(item.getCurrentStage()))
				throw new RuntimeException("가공 단계에 있는 도면만 처리 할 수 있습니다");
			
			// 외주 가공 여부
			boolean outsourcingProcessed = item.getWorkPosition().equalsIgnoreCase("O");
			
			// 후처리 요청 여부
			boolean postprocessingRequested = "Y".equalsIgnoreCase(item.getCoatingRequested());
			
			// 후처리 필요
			boolean needPostprocessing = !StringUtils.isEmpty( item.getPostprocessing() );
			
			log.info("도면[" + item.getId() + "] ==> outsourcingProcessed = " + outsourcingProcessed + ", postprocessingRequested = " + postprocessingRequested + ", needPostprocessing = " + needPostprocessing );
			
			if(outsourcingProcessed) {
							
				if(StringUtils.isEmpty(item.getOutsourcingPartnerId()))
					throw new RuntimeException("외주 가공사 정보가 없습니다");
				if(StringUtils.isEmpty(item.getOutsourcingRequestId()))
					throw new RuntimeException("외주 가공 발주 식별자가 없습니다");
								
				if(postprocessingRequested) {

					throw new RuntimeException("도면번호[" + item.getDesignDrawingNo() + "]는 완품으로 입고처리해 주세요");
					
				}else {
				
					if(needPostprocessing) {
						
						// current_stage = 'B',  process_completed = 'Y', finish_Date = now(), coating_stage = 'S', work_status = 'R'						
						carryInOutMapper.updateProcessStatus(id, "B", "R", null, "Y", true, now);
					}else {
						
						// current_stage = 'B', process_completed = 'Y', finish_date = now(), coating_stage = 'Y', work_status = 'R'						
						carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", true, now);
					}
					
				}								
				

				log.info("외주 가공 요청 원장 변경. 도면=" + item.getId() + ", 요청ID=" + item.getOutsourcingRequestId() + ", 파트너ID=" + item.getOutsourcingPartnerId());
				
				carryInOutMapper.updatePartnerOutsourcingOrderFinished(now, item.getOutsourcingRequestId(), item.getId(), item.getOutsourcingPartnerId());
			}else {
				
				if(needPostprocessing) {
					
					// current_stage = 'B', process_completed = 'Y', coating_stage = 'S', work_status = 'R'					
					carryInOutMapper.updateProcessStatus(id, "B", "R", null, "Y", false, now);
					
				}else {
					
					// current_stage = 'B', process_completed = 'Y', coating_stage = 'Y', work_status = 'R'
					carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", false, now);
				}				
			}			
		}		
	}	
	
	// 가공입고 + 후처리발주
	public String MarkAsPostprocessingProductIn(long[] designDrawingId, String loginId, String partnerId, Date[] coatingFinishPlanDates ) {
		
		if(designDrawingId.length != coatingFinishPlanDates.length)
			throw new RuntimeException("납기 예정일을 모두 입력해 주세요");
		
		final Date now = new Date();
		String generatedPostprocessRequestNo = null;
		
		HashMap<Long, Date> pp = new HashMap<>();
		
		for (int i=0; i<designDrawingId.length;i++) {
			
			Long id = designDrawingId[i];
			
			// 완품 가공 입고 처리 할 수 있는지 판단
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(item == null)
				throw new RuntimeException("지정된 순번(" + id + ")의 도면을 찾을 수 없습니다");
			if(!"C".equals(item.getCurrentStage()))
				throw new RuntimeException("가공 단계에 있는 도면만 처리 할 수 있습니다");
			
			// 외주 가공 여부
			boolean outsourcingProcessed = item.getWorkPosition().equalsIgnoreCase("O");
			
			// 후처리 요청 여부
			boolean postprocessingRequested = "Y".equalsIgnoreCase(item.getCoatingRequested());
			
			// 후처리 필요
			boolean needPostprocessing = !StringUtils.isEmpty( item.getPostprocessing() );
			
			if(outsourcingProcessed) {
				// 사외
				
				if(postprocessingRequested)
					throw new RuntimeException("도면번호[" + item.getDesignDrawingNo() + "]는 완품으로 입고처리해 주세요");
				
				if(needPostprocessing) {
					carryInOutMapper.updateProcessStatus(id, "G", "I", null, "Y", true, now);
					
					pp.put(id, coatingFinishPlanDates[i]);
				}else {
					carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", true, now);
				}
				//가공완료 처리시, 가공완료 수량 업데이트하기
				carryInOutMapper.updateProcessWorkQuantity(id);
				
				// 가공 원장 갱신
				carryInOutMapper.updatePartnerOutsourcingOrderFinished(now, item.getOutsourcingRequestId(), item.getId(), item.getOutsourcingPartnerId());
			}else {
				// 사내가공
				if(needPostprocessing) {
					carryInOutMapper.updateProcessStatus(id, "G", "I", null, "Y", true, now);
					pp.put(id, coatingFinishPlanDates[i]);
				}else {
					carryInOutMapper.updateProcessStatus(id, "B", "R", "Y", "Y", true, now);
				}
			}
		}
		
		if(pp.size() > 0)
		{
			// 후처리 가공 발주
			generatedPostprocessRequestNo = makePostprocessingOrder(partnerId, 
										pp.keySet().stream().toArray(Long[]::new),
										pp.values().stream().toArray(Date[]::new));
			
			log.info("도면[" + Arrays.toString(pp.keySet().stream().toArray(Long[]::new)) + "] 를 후처리 요청함 [" + generatedPostprocessRequestNo + "]" );
		}
		
		return generatedPostprocessRequestNo;
	}
	
	// 가공입고 + 가공추가
	public String MarkAsAddprocessingProductIn(String outsourcingPartnerId, Long[] drawingJobId, Date[] deliveryDate, String[] drawingUrl, Integer unitPrice[], Long[] drawingJobOutUid) {
		
		if(drawingJobId.length != deliveryDate.length)
			throw new RuntimeException("납기 예정일을 모두 입력해 주세요");
		
		final Date now = new Date();

		if(unitPrice == null)
			throw new RuntimeException("단가 정보가 없습니다");
		
		for(Long id : drawingJobId){
			
			// 완품 가공 입고 처리 할 수 있는지 판단
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(item == null)
				throw new RuntimeException("지정된 순번(" + id + ")의 도면을 찾을 수 없습니다");

			if(!( ("C".equals(item.getCurrentStage())) || ("G".equals(item.getCurrentStage()))))
				throw new RuntimeException("가공/후처리 단계에 있는 도면만 처리 할 수 있습니다");

		}
		
		// 사용할 견적 요청 ID 
		String newOrderId = getNextEstimateId(OutsourcingOrderTypeEnum.PROCESS_ORDER, outsourcingPartnerId, now);
		log.debug("발주ID = " + outsourcingPartnerId + "-" + newOrderId);

		for(int i=0;i<drawingJobId.length;i++){
			Long id = drawingJobId[i];
			log.debug("drawingJobId = " + id);
			Long uid = drawingJobOutUid[i];
			log.debug("uid=" + uid);
			
			String drawingUrl_val = null;
			if (drawingUrl.length>0) {
				drawingUrl_val = StringUtils.isEmpty(drawingUrl[i]) ? null : drawingUrl[i];
			}
			log.debug("drawingJobId = " + id + ", uid=" + uid);
			
			JobDesignDrawing item = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
		
			// 외주 가공 여부
			boolean outsourcingProcessed = item.getWorkPosition().equalsIgnoreCase("O");
			
			// 현재 후처리에서 추가 가공 발주 인지 여부
			boolean postprocessingRequested = "G".equals(item.getCurrentStage());
			
			if(postprocessingRequested) {
				//후처리 완료 등록
				carryInOutMapper.updatePostprocessFinished(id);
				//추가 가공으로 가공상태 변경
				carryInOutMapper.updateProcessStatus(id, "C", "I", null, "N", false, now);

			} else {

				//추가 가공으로 가공상태 변경
				carryInOutMapper.updateProcessStatus(id, "C", "I", null, "N", false, now);
				if(outsourcingProcessed) {
					// 사외
					carryInOutMapper.updatePartnerOutsourcingAddOrderFinished(now, uid);
				}
			}
			
			log.debug("%%%%%%%%%%%%");
			log.debug("drawingJobId:" + drawingJobId[i]);
			log.debug("deliveryDate:" + deliveryDate[i]);
			log.debug("outsourcingPartnerId:" + outsourcingPartnerId);
			log.debug("newOrderId:" + newOrderId);
			
			// 외주 가공 발주 상태로 변경
			processManageMapper.markToAddOutsourcingOrdered(drawingJobId[i], deliveryDate[i], outsourcingPartnerId, newOrderId);

			log.debug("%%%%%%%%%%%%");
			log.debug("outsourcingPartnerId:" + outsourcingPartnerId);
			log.debug("newOrderId:" + newOrderId);
			log.debug("drawingJobId:" + drawingJobId[i]);
			log.debug("now:" + now);
			log.debug("deliveryDate:" + deliveryDate[i]);
			if (drawingUrl_val!=null) {
				log.debug("drawingUrl:" + drawingUrl[i]);
			}
			log.debug("unitPrice:" + unitPrice[i]);
			log.debug("%%%%%%%%%%%%");

			// 발주 기록 남기고
			jobDesignDrawingEstimateMapper.insertProcessOrder(outsourcingPartnerId, newOrderId, drawingJobId[i], now, deliveryDate[i], drawingUrl_val, "N", unitPrice[i]);
		}
		return newOrderId;
	}
	
	// 오주 가공 등록된 내역 리스트
	public DataTableResponse getOutsourcingProcessDetailList(Integer start, Integer length,
								String selectedPartner, String selectedCustomer, String device, String orderNoBase, String orderNoExtra, String drawingNo){
		
		DataTableResponse res = new DataTableResponse();
		
		try{
			orderNoBase = StringUtils.isEmpty(orderNoBase) ? null : orderNoBase;
			orderNoExtra = StringUtils.isEmpty(orderNoExtra) ? null : orderNoExtra;
			drawingNo = StringUtils.isEmpty(drawingNo) ? null : drawingNo;
			device = StringUtils.isEmpty(device) ? null : device;
			selectedPartner = StringUtils.isEmpty(selectedPartner) ? null : selectedPartner;
			selectedCustomer = StringUtils.isEmpty(selectedCustomer) ? null : selectedCustomer;
			
			// 도면 번호에 xxxxx-yyyy 형태로 입력된 경우, yyyy 를 도면순번으로 바꿔서 검색
			if( !StringUtils.isEmpty(orderNoExtra) && StringUtils.isEmpty(drawingNo)){
				drawingNo = orderNoExtra;
				orderNoExtra = null;
			}
			
			res.setData(processManageMapper.selectOutsourcingList(start, length, selectedPartner, selectedCustomer, device, orderNoBase, orderNoExtra, drawingNo));
			res.setTotalRecords( processManageMapper.selectOutsourcingListCount(selectedPartner, selectedCustomer, device, orderNoBase, orderNoExtra, drawingNo));
			
		}catch(RuntimeException e){
			e.printStackTrace();			
			res.setErrorResponse(e);
		}catch(Exception e){
			e.printStackTrace();			
			res.setErrorResponse();
		}			
		
		return res;
		
	}
	

	// 외주 요청할 때 사용하는 주문ID 를 만들어낸다
	public String getNextEstimateId(OutsourcingOrderTypeEnum code, String partnerId, Date when){
		String org = new SimpleDateFormat("yyMMdd").format(when);
		Long N = jobDesignDrawingEstimateMapper.getOrderIdCount(code.toString(), partnerId, org);
		String made = org + "-" + String.format("%04d", N);
		log.info("신규 ID. org=" + org + ", partner=" + partnerId + ", count=" + N + " ==> " + made );
		return made;
	}
	
	@Transactional
	public String makeEstimateRequest(boolean withPostprocessing, Long[] drawingJobId, Date[] deliveryDate, String[] drawingUrl){
		Date now = new Date();
		
		// 정보 확인
		String outsourcingPartnerId = null;
		// 다른 외주 업체가 선택돼 있는지 확인
		for(Long id : drawingJobId){
			
			JobDesignDrawing entry = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(entry.getOutsourcingPartnerId() == null)
				throw new RuntimeException("도면[ID:" + id + "]에 외주 가공 업체 정보가 없습니다");
			
			if(outsourcingPartnerId != null && !outsourcingPartnerId.equalsIgnoreCase(entry.getOutsourcingPartnerId()))
				throw new RuntimeException("혼합된 외주 업체 견적은 작성할 수 없습니다");
			
			outsourcingPartnerId = entry.getOutsourcingPartnerId();			
		}
		
		// 사용할 견적 요청 ID 
		String newEstimateId = getNextEstimateId(OutsourcingOrderTypeEnum.ESTIMATE, outsourcingPartnerId, now);
		log.debug("견적서ID = " + outsourcingPartnerId + "-" + newEstimateId);
		String setDrawingUrl = "";
		// 견적 요청 데이터 남기고
		for(int i=0;i<drawingJobId.length;i++){			
			processManageMapper.markAsEstimateRequested(drawingJobId[i], newEstimateId);
			if (drawingUrl==null) {
				setDrawingUrl = "";
			} else {
				if (drawingUrl.length > 0)
				{
					setDrawingUrl = drawingUrl[i];
				}
			}
			jobDesignDrawingEstimateMapper.insertEstimate(outsourcingPartnerId, newEstimateId, drawingJobId[i], now, deliveryDate[i], setDrawingUrl, withPostprocessing ? "Y" : "N" );
		}
		
		return newEstimateId;
	}
	
	public void setOutsourcingPartnerIdAndWorkPosition(JobDesignDrawing drawing) {
		processManageMapper.setOutsourcingPartnerIdAndWorkPosition(drawing);
	}
	
	// 
	@Transactional
	public String makeProcessOrderRequest(boolean withPostprocessing, Long[] drawingJobId, Date[] deliveryDate, String[] drawingUrl, Integer unitPrice[]){
		
		// 발주 시각 기준 시각
		final Date now = new Date();
		
		if(unitPrice == null)
			throw new RuntimeException("단가 정보가 없습니다");
		
		// 정보 확인
		String outsourcingPartnerId = null;
		for(Long id : drawingJobId){
			
			JobDesignDrawing entry = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(entry.getOutsourcingPartnerId() == null)
				throw new RuntimeException("도면[ID:" + id + "]에 외주 가공 업체 정보가 없습니다");
			
			if(outsourcingPartnerId != null && !outsourcingPartnerId.equalsIgnoreCase(entry.getOutsourcingPartnerId()))
				throw new RuntimeException("한번에 한 업체에만 발주를 할 수 있습니다.");
			
			outsourcingPartnerId = entry.getOutsourcingPartnerId();
		}
		// 사용할 견석 요청 ID 
		String newOrderId = getNextEstimateId(OutsourcingOrderTypeEnum.PROCESS_ORDER, outsourcingPartnerId, now);
		log.debug("발주ID = " + outsourcingPartnerId + "-" + newOrderId);
		String setDrawingUrl = "";
		for(int i=0;i<drawingJobId.length;i++){
			
			// 외주 가공 발주 상태로 변경
			processManageMapper.markToOutsourcingOrdered(drawingJobId[i], withPostprocessing, newOrderId);
			
			if (drawingUrl==null) {
				setDrawingUrl = " ";
			} else {
				if (drawingUrl.length > 0)
				{
					setDrawingUrl = drawingUrl[i];
				}
			}
			// 발주 기록 남기고
			jobDesignDrawingEstimateMapper.insertProcessOrder(outsourcingPartnerId, newOrderId, drawingJobId[i], now, deliveryDate[i], setDrawingUrl, withPostprocessing ? "Y" :"N", unitPrice[i]);
		}
		
		return newOrderId;
	}
	
	// 가공 및 검사 현황 목록 반환

	public DataTableResponse getProcessStatusList(String orderNoBase, String orderNoExtra, String drawingNo, 
													String outsourcingPartnerId, Date orderDateFrom, Date orderDateTo, Integer draw, Integer start, Integer length, String inProcessYN){
		DataTableResponse res = new DataTableResponse();
		
		String workPosition = null;
		String statusR = null;
		
		if(StringUtils.isEmpty(orderNoBase))
			orderNoBase = null;
		if(StringUtils.isEmpty(orderNoExtra))
			orderNoExtra = null;
		if(StringUtils.isEmpty(drawingNo))
			drawingNo = null;
		if(StringUtils.isEmpty(outsourcingPartnerId))
			outsourcingPartnerId = null;
		if(StringUtils.isEmpty(inProcessYN)) {
			workPosition = null;
			statusR = null;
		}
		if(inProcessYN.equals("Y")) {
			workPosition = "I";
			statusR = null;
		}
		
		if(inProcessYN.equals("O")) {
			workPosition = "O";
			statusR = null;
		}
		if(inProcessYN.equals("N")) {
			workPosition = null;
			statusR = null;
		}
		
		if(inProcessYN.equals("R")) {
			workPosition = "I";
			statusR = "Y";
		}
		
		System.out.println("&&&&&&&&&&& inProcessYN =  "+inProcessYN);
		System.out.println("^^^^^^^^^^^ workPosition =  "+workPosition );
		
		
		List<ProcessingAndCheckListEntry> result = processManageMapper.selectprocessingAndCheckList(outsourcingPartnerId, orderNoBase, orderNoExtra, drawingNo, orderDateFrom, orderDateTo, start, length, workPosition, statusR);
		res.setData(result);
		res.setTotalRecords( processManageMapper.selectprocessingAndCheckListCount(orderNoBase, orderNoExtra, drawingNo, outsourcingPartnerId, orderDateFrom, orderDateTo, workPosition, statusR));
		
		log.debug("전체 " + res.getRecordsTotal() + " ==> " + result.size());
		return res;
	}
	
	// 후처리 완료 입고 목록 조회
	public DataTableResponse getPostprocessFinishedList(Integer draw, Integer start, Integer length, String outsourcingPartnerId, String orderNoBase, String orderNoExtra, String drawingNo, Date orderDateFrom, Date orderDateTo, boolean insourcingProcess, boolean outsourcingProcess){
		
		if(StringUtils.isEmpty(outsourcingPartnerId))
			outsourcingPartnerId = null;
		if(StringUtils.isEmpty(orderNoBase))
			orderNoBase = null;
		if(StringUtils.isEmpty(orderNoExtra))
			orderNoExtra = null;
		if(StringUtils.isEmpty(drawingNo))
			drawingNo = null;
		
		List<PostprocessFinishedEntry> data = carryInOutMapper.selectPostprocessFinishedList(start, length, outsourcingPartnerId, insourcingProcess, outsourcingProcess, orderNoBase, orderNoExtra, orderDateFrom, orderDateTo);
		
		DataTableResponse res = new DataTableResponse();
		res.setTotalRecords( carryInOutMapper.selectPostprocessFinishedListCount(outsourcingPartnerId, insourcingProcess, outsourcingProcess, orderNoBase, orderNoExtra, orderDateFrom, orderDateTo)  );
		res.setData(data);
		res.setDraw(draw);
		
		return res;
	}
	
	@Transactional
	public void updateCoatingFinished(long[] drawings, String loginId) {
		
		for(long id : drawings) {
		
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			if(drawing == null) {
				log.info("도면[" + id + "]을 찾을 수 없음");
				throw new RuntimeException("지정된 도면을 찾을 수 없습니다");
			}
			
			if( !"G".equals(drawing.getCurrentStage()) ) {
				log.info("도면[" + id + "]의 현재 상태는 [" + drawing.getCurrentStage() + "]");
				throw new RuntimeException("후처리 단계의 도면이 아닙니다");
			}
						
			carryInOutMapper.updatePostprocessFinished(id);
		}		
	}
	
	public DataTableResponse getCarryInList(Integer draw, Integer start, Integer length 
												, String outsourcingPartnerId
												, String orderNoBase, String orderNoExtra, String drawingNo
												, Date orderDateFrom, Date orderDateTo
												, boolean insourcingProcess, boolean outsourcingProcess, long[] specificDrawingNo){
		
		if(StringUtils.isEmpty(outsourcingPartnerId))
			outsourcingPartnerId = null;
		if(StringUtils.isEmpty(orderNoBase))
			orderNoBase = null;
		if(StringUtils.isEmpty(orderNoExtra))
			orderNoExtra = null;
		if(StringUtils.isEmpty(drawingNo))
			drawingNo = null;
		
		DataTableResponse res = new DataTableResponse();
		
		res.setDraw(draw);
		res.setTotalRecords(carryInOutMapper.selectCarryInOutListCount(outsourcingPartnerId
				, insourcingProcess, outsourcingProcess
				, orderNoBase, orderNoExtra
				, orderDateFrom, orderDateTo, specificDrawingNo) );				
		res.setData( carryInOutMapper.selectCarryInOutList(start, length
															,outsourcingPartnerId
															, insourcingProcess, outsourcingProcess
															, orderNoBase, orderNoExtra
															, orderDateFrom, orderDateTo, specificDrawingNo) );
		
		return res;
	}
	
	// 조립/검사 인계 리스트
	public DataTableResponse getAssembleCheckTargetList(Integer start, Integer length,
			String orderNoBase, String orderNoExtra, String partnerId,
			Date orderDate1, Date orderDate2, 
			String workPositionFilter, boolean postprocessFinished, FinishStatusEnum checkFinishStatus, boolean checkNg) {
		
		String checkFinishStatusValue = checkFinishStatus.getCode();
		
		DataTableResponse res = new DataTableResponse();
		res.setTotalRecords(carryInOutMapper.selectAssembleCheckTargetListCount(workPositionFilter, postprocessFinished, partnerId, checkFinishStatusValue, orderNoBase, orderNoExtra, null, orderDate1, orderDate2, checkNg));
		res.setData( carryInOutMapper.selectAssembleCheckTargetList(start, length, workPositionFilter, postprocessFinished, partnerId, checkFinishStatusValue, orderNoBase, orderNoExtra, null, orderDate1, orderDate2, checkNg));
		
		return res;
		
	}
	
	// 조립관리-가공품 입고리스트
	public List<JobProcessList> getAssembleProcessList(Long orderId, Integer searchType) {
		log.info("조립관리-가공품리스트 ::key=" + orderId + ",searchType=" + searchType);
		
		return drawingRecordMapper.selectJobProcessList(orderId, searchType);
	}
	
	// 가공관리-입고리스트
	public List<JobProcessList> getEnteringList(Long orderId, String drawingNo, String outPartnerId) {
		log.info("가공관리-입고리스트::key=" + orderId + ",drawingNo=" + drawingNo + ",outPartnerId=" + outPartnerId);
		
		return drawingRecordMapper.selectDrawingEnteringList(orderId, drawingNo, outPartnerId);
	}

	// 후처리 외주가공 발주
	@Transactional
	public String makePostprocessingOrder(String partnerId, Long[] designDrawingId, Date[] deliveryDate) {
		String postprocessOrderId = getNextEstimateId(OutsourcingOrderTypeEnum.POSTPROCESS_ORDER, partnerId, new Date());
		final Date now = new Date();
		log.info("후처리 발주 ID=" + postprocessOrderId + ", 기준 시각 = " + now + ", 대상 도면번호 = " + Arrays.toString(designDrawingId));
		
		for(int i=0;i<designDrawingId.length;i++) {
			
			// 도면 상태 변경
			processManageMapper.markAsPostprocessOrderRequested( designDrawingId[i], partnerId, postprocessOrderId );
			
			// 발주 기록 추가
			jobDesignDrawingEstimateMapper.insertPostprocessOrder(partnerId, postprocessOrderId, designDrawingId[i], now, deliveryDate[i]);
		}
		
		return postprocessOrderId;
	}
	
	// 검사부로 인계
	@Transactional
	public void carryToCheckDept(long[] designDrawingId, String from) {
		
		final Date now = new Date();
		
		for(long id : designDrawingId) {
			
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			
		
			
			//인수인계 processing 내역 추가
			TransitionProcessing TransitionProcessing = new TransitionProcessing();
			TransitionProcessing.setPreId(0L);
			TransitionProcessing.setJobOrderId(drawing.getOrderId());
			TransitionProcessing.setJobDesignDrawingId(id);
			TransitionProcessing.setPassUsr(from);
			TransitionProcessing.setReceiveDept("PQ");
			TransitionProcessing.setQuantity(drawing.getQuantity());
			
			TransitionMapper.insertProccessingTrantion(TransitionProcessing);
			TransitionMapper.setDesignPassStayY(id);
			
			
			
		
		}		
	}
	
	//가공부로 인계
	@Transactional
	public void carryToMC(long[] designDrawingId, String from) {
		
		final Date now = new Date();
		
		for(long id : designDrawingId) {
			
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			
			
			//인수인계 processing 내역 추가
			TransitionProcessing TransitionProcessing = new TransitionProcessing();
			TransitionProcessing.setPreId(0L);
			TransitionProcessing.setJobOrderId(drawing.getOrderId());
			TransitionProcessing.setJobDesignDrawingId(id);
			TransitionProcessing.setPassUsr(from);
			TransitionProcessing.setReceiveDept("MC");
			TransitionProcessing.setQuantity(drawing.getQuantity());
			
			TransitionMapper.insertProccessingTrantion(TransitionProcessing);
			TransitionMapper.setDesignPassStayY(id);
			
			
		}		
	}
	
	//영업부로 인계
	@Transactional
	public void carryToTS(long[] designDrawingId, String from) {
		
		final Date now = new Date();
		
		for(long id : designDrawingId) {
			
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			
			
			//인수인계 processing 내역 추가
			TransitionProcessing TransitionProcessing = new TransitionProcessing();
			TransitionProcessing.setPreId(0L);
			TransitionProcessing.setJobOrderId(drawing.getOrderId());
			TransitionProcessing.setJobDesignDrawingId(id);
			TransitionProcessing.setPassUsr(from);
			TransitionProcessing.setReceiveDept("TS");
			TransitionProcessing.setQuantity(drawing.getQuantity());
			
			TransitionMapper.insertProccessingTrantion(TransitionProcessing);
			TransitionMapper.setDesignPassStayY(id);
			
			
		}		
	}
	
	//구매부로 인계
		@Transactional
		public void carryToPQ(long[] designDrawingId, String from) {
			
			final Date now = new Date();
			
			for(long id : designDrawingId) {
				
				JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
				
				
				//인수인계 processing 내역 추가
				TransitionProcessing TransitionProcessing = new TransitionProcessing();
				TransitionProcessing.setPreId(0L);
				TransitionProcessing.setJobOrderId(drawing.getOrderId());
				TransitionProcessing.setJobDesignDrawingId(id);
				TransitionProcessing.setPassUsr(from);
				TransitionProcessing.setReceiveDept("PQ");
				TransitionProcessing.setQuantity(drawing.getQuantity());
				
				TransitionMapper.insertProccessingTrantion(TransitionProcessing);
				TransitionMapper.setDesignPassStayY(id);
				
				
			}		
		}
	
	//설계부로 인계
	@Transactional
	public void carryToT1(long[] designDrawingId, String from) {
		
		final Date now = new Date();
		
		for(long id : designDrawingId) {
			
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			
			
			//인수인계 processing 내역 추가
			TransitionProcessing TransitionProcessing = new TransitionProcessing();
			TransitionProcessing.setPreId(0L);
			TransitionProcessing.setJobOrderId(drawing.getOrderId());
			TransitionProcessing.setJobDesignDrawingId(id);
			TransitionProcessing.setPassUsr(from);
			TransitionProcessing.setReceiveDept("T1");
			TransitionProcessing.setQuantity(drawing.getQuantity());
			
			TransitionMapper.insertProccessingTrantion(TransitionProcessing);
			TransitionMapper.setDesignPassStayY(id);
			
			
		}		
	}
	
	// 조립부로 인계
	@Transactional
	public void carryToAssemblyDept(long[] designDrawingId, String from) {
		
		final Date now = new Date();
		
		for(long id : designDrawingId) {
			
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			
			
			/*
			if("E".equals(drawing.getCurrentStage()))
				throw new RuntimeException(drawing.getDesignDrawingNo() + " 이미 조립부로 인계된 도면입니다");
			
			if(!"B".equals(drawing.getCurrentStage()))
				throw new RuntimeException(drawing.getDesignDrawingNo() + " 도면의 위치가 생관이 아닙니다");
			
			if("Y".equals(drawing.getChecking()) && drawing.getInspectId() == null)
				throw new RuntimeException(drawing.getDesignDrawingNo() + " 는 검사를 먼저 진행해야 합니다");
				*/
			
			//인수인계 processing 내역 추가
			TransitionProcessing TransitionProcessing = new TransitionProcessing();
			TransitionProcessing.setPreId(0L);
			TransitionProcessing.setJobOrderId(drawing.getOrderId());
			TransitionProcessing.setJobDesignDrawingId(id);
			TransitionProcessing.setPassUsr(from);
			TransitionProcessing.setReceiveDept("AD");
			TransitionProcessing.setQuantity(drawing.getQuantity());
			
			TransitionMapper.insertProccessingTrantion(TransitionProcessing);
			TransitionMapper.setDesignPassStayY(id);
			
			// 도면 수정 : 조립부 인계 됐음
			//carryInOutMapper.carryToAssemblyDept(id, from, to, now);
			
			//log.info("도면 [" + id + "] 조립부로 인계. 인계자=" + from + ", 인수자=" + to + ", 기준 날짜 = " + now );
		}		
	}
	
	//이관 가능 order
	
	public List<JobOrder> selectMovePossibleOrder(){
		
		return processManageMapper.selectMovePossibleOrder();
		
	}
	
	public List<JobOrder> selectMovePossibleOrder2(){
		
		return processManageMapper.selectMovePossibleOrder2();
		
	}
	
	public List<JobDesignDrawing> MoveDesignDrawingPopup(Long id){
		
		return processManageMapper.MoveDesignDrawingPopup(id);
		
	}
	
	public String OrderIdToOrderNoFromMoverOrder(Long id) {
		
		return processManageMapper.OrderIdToOrderNoFromMoverOrder(id);
	}
	
	public String OrderIdToOrderNoBaseFromMoverOrder(Long id) {
		
		return processManageMapper.OrderIdToOrderNoBaseFromMoverOrder(id);
	}
	
	public String OrderIdToOrderNoExtraFromMoverOrder(Long id) {
		
		return processManageMapper.OrderIdToOrderNoExtraFromMoverOrder(id);
	}
	
	public String selectDrawingNoFromMoveOrder(Long id) {
		return processManageMapper.selectDrawingNoFromMoveOrder(id);
	}
	
	public int countIsDrawingNoEqauls(Long selectOrderId, String grandDrawingNo) {
		return processManageMapper.countIsDrawingNoEqauls(selectOrderId, grandDrawingNo);
	}
	
	public void processSetWorkMinAll(Long id) {
		processManageMapper.processSetWorkMinAll(id);
	}
	
	public List<JobMctHistory> JobHistroyMctFromMoveAll(Long id){
		
		return processManageMapper.JobHistroyMctFromMoveAll(id);
		
	}
	
	public Long jobDesignIdFromMove(Long selectOrderId, String grandDrawingNo) {
		return processManageMapper.jobDesignIdFromMove(selectOrderId, grandDrawingNo);
	}
	
	public void addJobMctHistoryFromMove(JobMctHistory jobMctHistory) {
		
		processManageMapper.addJobMctHistoryFromMove(jobMctHistory);
		
	}
	
	public void deleteJobMctHistoryFromMove(Long id) {
		processManageMapper.deleteJobMctHistoryFromMove(id);
	}
	
	public void updateJobMctHistoryFromMove(Long id, int workQuantity) {
		processManageMapper.updateJobMctHistoryFromMove(id, workQuantity);
	}
	
	public List<JobInspect> jobInspectFromMove(Long[] jobDrawingId){
		return processManageMapper.jobInspectFromMove(jobDrawingId);
	}
	
	public List<JobDesignDrawing> DrawingFromMoveSelectIdForInspect(Long grandOrderId){
		return processManageMapper.DrawingFromMoveSelectIdForInspect(grandOrderId);
	}
	
	public void addFromMoveForJobInspect(JobInspect JobInspect) {
		processManageMapper.addFromMoveForJobInspect(JobInspect);
		
	}
	
	public void updateFromMoveForDesignQuantity(Long id, int qty) {
		processManageMapper.updateFromMoveForDesignQuantity(id, qty);
	}
	
	public JobDesignDrawing oneDesignSelectGrandid(Long id) {
		return processManageMapper.oneDesignSelectGrandid(id);
	}
	
	public int nodeOrderDesignqtyCount(Long id) {
		return processManageMapper.nodeOrderDesignqtyCount(id);
	}
	
	public void nodeOrderDesignDrawingSet(Long id, int qty) {
		processManageMapper.nodeOrderDesignDrawingSet(id, qty);
	}
	
	public void grandOrderDesignDrawingSetCureent(Long id) {
		processManageMapper.grandOrderDesignDrawingSetCureent(id);
	}
	
	public JobDesignDrawing oneDesignSelectNodeid(Long id) {
		return processManageMapper.oneDesignSelectNodeid(id);
	}
	
	public void nodeOrderDesignDrawingSetAll(JobDesignDrawing jobDesignDrawing) {
		processManageMapper.nodeOrderDesignDrawingSetAll(jobDesignDrawing);
	}
	
	public void grandOrderDesignDrawingSetWorkQty(Long id, int qty) {
		processManageMapper.grandOrderDesignDrawingSetWorkQty(id, qty);
	}
	
	public int grandOrderDesignSelectQtyFromMove(Long id) {
		return processManageMapper.grandOrderDesignSelectQtyFromMove(id);
	}
	
	public int grandOrderDesignSelectWorkQtyFromMove(Long id) {
		return processManageMapper.grandOrderDesignSelectWorkQtyFromMove(id);
	}
	
	public void moveOrderCompletFromMM(Long id) {
		processManageMapper.moveOrderCompletFromMM(id);
	}
	
	public String whatKindOfProcess(Long id) {
		return processManageMapper.whatKindOfProcess(id);
		
	}
	
	public String whatProcessStatus(Long id) {
		return processManageMapper.whatProcessStatus(id);
	}
	
	public void updateDesigToProcessFirst(Long id) {
		processManageMapper.updateDesigToProcessFirst(id);
	}
	
	public void updateDesignOToProcessFirst(Long id) {
		processManageMapper.updateDesignOToProcessFirst(id);
	}
	
	public void updateCheckTrueFromCarryIn(Long id) {
		processManageMapper.updateCheckTrueFromCarryIn(id);
	}
	
	public void updateReProcessOutToIn(Long id) {
		
		processManageMapper.updateReProcessOutToIn(id);
	}
	
	public String selectInsepectResult(Long jobDrawingId) {
		return processManageMapper.selectInsepectResult(jobDrawingId);
	}
	
	public String selectDrawingKindworkPositon(Long id) {
		return processManageMapper.selectDrawingKindworkPositon(id);
	}
	
	public void insDesignHistry(Long drawingId, String kind, String regUsr, String description) {
		processManageMapper.insDesignHistry(drawingId, kind, regUsr, description);
	}
	
	public NGcarry selectNGcarry(Long id) {
		return processManageMapper.selectNGcarry(id);
	}
	
	public void updateDesignfromNGcarry(Long id, int rqty) {
		
		processManageMapper.updateDesignfromNGcarry(id, rqty);
		
	}
	
	public DataTableResponse getProcessHistoryPageList(ProcessHisSearchForm form) {
		
		DataTableResponse res = new DataTableResponse();
		
		res.setData( processManageMapper.selectProcessHistoryPageList(form ));
		res.setTotalRecords( processManageMapper.selectProcessHistoryPageListCount(form));
		res.setDraw(form.getDraw());
		
		return res;	
		
	}
	
	public List<DesignDrawingHistory> detailDesignDrawingOnehistory(Long id){
		return processManageMapper.detailDesignDrawingOnehistory(id);
	}
	
	public DesignHistorywhere nowDrawinghistoryWhere(Long id) {
		return processManageMapper.nowDrawinghistoryWhere(id);
	}
	
	public String drawHisFullNo(Long id) {
		return processManageMapper.drawHisFullNo(id);
	}
	
}
