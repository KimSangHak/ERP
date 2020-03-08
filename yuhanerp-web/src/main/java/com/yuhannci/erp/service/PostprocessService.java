package com.yuhannci.erp.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.JobDesignDrawingEstimateMapper;
import com.yuhannci.erp.mapper.JobDesignDrawingMapper;
import com.yuhannci.erp.mapper.PostprocessMapper;
import com.yuhannci.erp.mapper.ProcessManageMapper;
import com.yuhannci.erp.model.CoatingAbortTypeEnum;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.OutsourcingOrderTypeEnum;
import com.yuhannci.erp.model.OutsourcingRequestEntry;
import com.yuhannci.erp.model.PostprocessRequestInfo;
import com.yuhannci.erp.model.db.JobDesignDrawing;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostprocessService {
	
	@Autowired JobDesignDrawingMapper jobDesignDrawingMapper;
	@Autowired PostprocessMapper postprocessMapper;
	@Autowired ProcessService processService;
	@Autowired ProcessManageMapper processManageMapper;
	@Autowired JobDesignDrawingEstimateMapper jobDesignDrawingEstimateMapper;

	// 후처리 등록할 대상 목록
	public DataTableResponse getPostprocessingList(Integer start, Integer length, long[] specificDrawings, 
													Date orderDateFrom, Date orderDateTo, 
													String device, String orderNoBase, String orderNoExtra, 
													String outsourcingPartnerId, String processSourcing){
		
		log.info("후처리 등록 대상 조회. 세부 항목 = " + Arrays.toString(specificDrawings));
		
		DataTableResponse res = new DataTableResponse();
		res.setData(postprocessMapper.selectPostprocessList(start, length, specificDrawings, orderDateFrom, orderDateTo, device, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing));
		res.setTotalRecords( postprocessMapper.selectPostprocessListCount(start, length, specificDrawings, orderDateFrom, orderDateTo, device, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing) );
		
		return res;
	}
	
	// 후처리 스킵 등록
	@Transactional
	public void markPostprocessSkip(long[] specificDrawingId, boolean withFinish) {
		log.info("후처리 등록 = " + Arrays.toString(specificDrawingId) + ", 완품등록 = " + withFinish );
		
		postprocessMapper.updatePostprocessSkip(specificDrawingId, withFinish);
	}
	
	// 후처리 스킵 리스트
	public DataTableResponse getSkipList(Integer start, Integer length, long[] specificDrawings, 
			Date orderDateFrom, Date orderDateTo, 
			String device, String orderNoBase, String orderNoExtra, 
			String outsourcingPartnerId, String processSourcing) {
		
		DataTableResponse res = new DataTableResponse();

		res.setData( postprocessMapper.selectSkipList(start, length, specificDrawings, orderDateFrom, orderDateTo, device, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing) );
		res.setTotalRecords(postprocessMapper.selectSkipListCount(start, length, specificDrawings, orderDateFrom, orderDateTo, device, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing));
		
		return res;
	}
	
	// 후처리 전환 발주
	@Transactional
	public String makePostprocessSkipOrder(Long[] specificDrawingId, Date[] deliveryDate, String partnerId) {
		
		String postprocessOrderId = processService.getNextEstimateId(OutsourcingOrderTypeEnum.POSTPROCESS_ORDER, partnerId, new Date());
		final Date now = new Date();
		log.info("후처리 전환. 사용 발주 ID=" + postprocessOrderId + ", 기준 시각 = " + now + ", 대상 도면번호 = " + Arrays.toString(specificDrawingId) + ", 파트너ID=" + partnerId);
		
		for(int i=0;i<specificDrawingId.length;i++) {
			
			// 도면 상태 변경
			processManageMapper.updateSkipToPostprocessOrderRequested( specificDrawingId[i], partnerId, postprocessOrderId );
			
			// 발주 기록 추가
			jobDesignDrawingEstimateMapper.insertPostprocessOrder(partnerId, postprocessOrderId, specificDrawingId[i], now, deliveryDate[i]);
		}
		
		return postprocessOrderId;
	}
	
	// 후처리 발주 리스트
	public DataTableResponse getOrderList(Integer start, Integer length, long[] specificDrawings, 
			Date orderDateFrom, Date orderDateTo, 
			String device, String orderNoBase, String orderNoExtra, 
			String outsourcingPartnerId, String processSourcing) {
		
		DataTableResponse res = new DataTableResponse();

		res.setData( postprocessMapper.selectOrderList(start, length, specificDrawings, orderDateFrom, orderDateTo, device, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing) );
		res.setTotalRecords(postprocessMapper.selectOrderListCount(start, length, specificDrawings, orderDateFrom, orderDateTo, device, orderNoBase, orderNoExtra, outsourcingPartnerId, processSourcing));
		
		return res;
	}	
	
	// 발주 취소
	@Transactional
	public void abortCoatingOrder(CoatingAbortTypeEnum coatingAbortType, long[] drawingIdList, String reason) {
		
		// TODO :: 상태 확인
		for(Long id : drawingIdList) {
			
			JobDesignDrawing drawing = jobDesignDrawingMapper.selectSingleDesignDrawing(id);
			
			switch(coatingAbortType) {
			case AbortCoatingOrderOnly:
				// 후처리 발주만 취소
				break;
			
			default:
				throw new RuntimeException("사용할 수 없는 기능입니다");
			
			}			
		}
		
		// 별 다른 문제 없으면 일괄 변경
		postprocessMapper.updateCancelOrder(drawingIdList, reason);
		postprocessMapper.updateCancelOrderMaster(drawingIdList);
		
	}
	
	// 지정된 후처리요청ID 에 관련된 항목 검색한다
	public PostprocessRequestInfo getDetailRequestList(String partnerId, String postprocessRequestId){
		if(partnerId == null)
			throw new RuntimeException("파트너ID 가 없습니다");
		if(postprocessRequestId == null)
			throw new RuntimeException("후처리요청ID가 없습니다");
		
		PostprocessRequestInfo result = postprocessMapper.selectDetailRequestInfo(postprocessRequestId, partnerId);
		if(result != null) {
			result.setDrawings( postprocessMapper.selectDetailRequestList(postprocessRequestId, partnerId) );
			log.info("total " + result.getDrawings().size() + " drawings");
		}
		
		return result;
	}
}
