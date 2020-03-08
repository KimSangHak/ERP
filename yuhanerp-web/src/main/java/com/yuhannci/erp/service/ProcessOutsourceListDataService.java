package com.yuhannci.erp.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.JobDesignDrawingMapper;
import com.yuhannci.erp.mapper.ProcessOutsourceListDataMapper;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.OutsourcingRequestEntry;
import com.yuhannci.erp.model.ProcessOrderListEntry;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobPartnerOutsourcingOrder;

import lombok.extern.slf4j.Slf4j;

// 가공 관련된 서비스

@Service
@Slf4j
public class ProcessOutsourceListDataService {
	
	@Autowired ProcessOutsourceListDataMapper processOutsourceListDataMapper;
	@Autowired JobDesignDrawingMapper jobDesignDrawingMapper;
	@Autowired DrawingService drawingService;
	
	// 가공 발주 리스트
	public DataTableResponse getProcessOutsourceListData(Integer start, Integer length, String orderNoBase, String orderNoExtra, String drawingNo,
												String outsourcingPartnerId, Date orderDateFrom, Date orderDateTo, long[] specificDrawingIdList){
		
		DataTableResponse res = new DataTableResponse();
		
		try{
			orderNoBase = StringUtils.isEmpty(orderNoBase) ? null : orderNoBase;
			orderNoExtra = StringUtils.isEmpty(orderNoExtra) ? null : orderNoExtra;
			drawingNo = StringUtils.isEmpty(drawingNo) ? null : drawingNo;
			outsourcingPartnerId = StringUtils.isEmpty(outsourcingPartnerId) ? null : outsourcingPartnerId;
			orderDateFrom = StringUtils.isEmpty(orderDateFrom) ? null : orderDateFrom;
			orderDateTo = StringUtils.isEmpty(orderDateTo) ? null : orderDateTo;
			
			List<ProcessOrderListEntry> data = processOutsourceListDataMapper.selectProcessOutsourceListData(start, length, 
																					orderNoBase, orderNoExtra, drawingNo, 
																					outsourcingPartnerId, orderDateFrom, orderDateTo, specificDrawingIdList);
			
			res.setData(data);
			
			if(specificDrawingIdList == null)
				res.setTotalRecords(processOutsourceListDataMapper.selectProcessOutsourceListDataCount(orderNoBase, orderNoExtra, drawingNo, outsourcingPartnerId, orderDateFrom, orderDateTo));
			
		}catch(Exception e){
			log.error("가공 발주 리스트 검색 중 오류", e);
			res.setErrorResponse(e);
		}		
		
		return res;		
	}

	public List<ProcessOrderListEntry> getSpecificRequests(long[] designDrawingIdArray){
		
		return processOutsourceListDataMapper.selectProcessOutsourceListData(null, null, null, null, null, null, null, null, designDrawingIdArray);		
	}
	
	public JobPartnerOutsourcingOrder getSingleOutsourcingList(Long uid){
		
		return processOutsourceListDataMapper.selectProcessOutsourceSingleData(uid);		
	}
	
	@Transactional
	public void updateOutsourcingOrder(Long[] uid, Date[] dates, Long[] price ) {
		
		if(uid.length != dates.length || uid.length != price.length) {
			log.info("UID = " + uid.length + ", dates = " + dates.length + ", price = " + price.length);
			throw new RuntimeException("데이터가 부족합니다.");
		}
		
		for(int i=0;i<uid.length;i++) {
			
			log.info("요청[" + uid[i] + "] ==> 납기일 [" + dates[i] + "], 단가=[" + price[i] + "]");
			
			processOutsourceListDataMapper.updateOutsourcingOrder(uid[i], price[i], dates[i]);			
			processOutsourceListDataMapper.updateOutsourcingOrderMaster(uid[i], price[i], dates[i]);			
		}
	}
	
	/**
	 * 견적/발주 요청한 항목을 추출한다 (메일 본문 구성용)
	 * @param isOrder 발주(True), 견적(False)
	 * @param requestId 요청ID
	 * @return
	 * @throws Exception
	 */
	public List<OutsourcingRequestEntry> getOutsourcingRequestDetailList(boolean isOrder, String requestId) throws Exception{
		
		if(StringUtils.isEmpty(requestId))
			throw new Exception("잘못된 견적 요청 ID");
		
		List<JobPartnerOutsourcingOrder> requests = processOutsourceListDataMapper.selectPartnerOutsourcingRequestList(isOrder ? "P" : "E", requestId, null);
		Long[] jobDesignDrawingIDs = requests.stream().map(a -> a.getJobDesignDrawingId()).toArray(Long[]::new);
		
		List<JobDesignDrawing> records = jobDesignDrawingMapper.selectJobDesignDrawingArray( jobDesignDrawingIDs );
		if(records == null || records.size() == 0 || requests == null || requests.size() == 0)
			throw new Exception("견적 요청ID[" + requestId + "]로 검색된 대상이 없습니다");
		
		List<OutsourcingRequestEntry> result = new LinkedList<>();
		
		// 작업 지시별로 분리한다
		Map<Long, List<JobDesignDrawing>> temp = records.stream().collect(Collectors.groupingBy(JobDesignDrawing::getOrderId));
		
		// 각 작업지시건 별로 분류하여 전달
		for(Long id : temp.keySet()){
			OutsourcingRequestEntry entry = new OutsourcingRequestEntry();
			
			List<JobDesignDrawing> all = temp.get(id);
			
			entry.setDevice( all.get(0).getDevice() );
			entry.setDrawings(all);
			entry.setJobOrderId(id);;
			entry.setRequestedDate(all.get(0).getOutsourcingEstimateRequestDate());
			entry.setJobOrderNo(all.get(0).getJobOrderNo());
			
			entry.setDueDate( requests.get(0).getDeliveryPlanDate());	// 납입 기한
			entry.setWithPostProcessing(requests.get(0).getWithPostprocessing().equals("Y"));		// 후처리 여부
			
			result.add(entry);
		}
				
		return result;
	}
	
	public String getAssociatedPartnerId(boolean isOrder, String outsourcingOrder){
		List<JobPartnerOutsourcingOrder> requests = processOutsourceListDataMapper.selectPartnerOutsourcingRequestList(isOrder ? "P" : "E", outsourcingOrder, null);
		return requests.size() > 0 ? requests.get(0).getPartnerId() : "";
	}
}
