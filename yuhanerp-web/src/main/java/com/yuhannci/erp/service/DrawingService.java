package com.yuhannci.erp.service;

import static org.hamcrest.CoreMatchers.allOf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.DesignDrawingMapper;
import com.yuhannci.erp.mapper.JobDesignDrawingMapper;
import com.yuhannci.erp.model.DesignDrawingNoEntry;
import com.yuhannci.erp.model.DesignProgressFormEntry;
import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.JobDesignDrawingOutSource;

import lombok.extern.slf4j.Slf4j;

/**
 * 도면 관리 
 * 
 * @author core
 *
 */
@Service
@Slf4j
public class DrawingService {

	@Autowired DesignDrawingMapper 		designDrawingMapper;
	@Autowired JobDesignDrawingMapper 	jobDesignDrawingMapper;
	@Autowired	OrderNoService orderNoService;
	
	public List<JobDesignDrawing> selectDesignDrawingList(OrderTypeEnum orderType) {
		log.info("orderType : " + orderType);
		//List<JobDesignDrawing> drawing = designDrawingMapper.selectList(orderType);
		return null; // drawing;
	}
	
	public List<JobDesignDrawing> selectDesignDrawingList(Long[] id) {
		List<JobDesignDrawing> drawing = jobDesignDrawingMapper.selectSpecificDrawings( Stream.of(id).mapToLong(a -> a).toArray() );
		return drawing;
	}	
	
	public List<JobDesignDrawingOutSource> selectDesignDrawingOutList(Long[] id) {
		List<JobDesignDrawingOutSource> drawing = jobDesignDrawingMapper.selectSpecificDrawingOuts( Stream.of(id).mapToLong(a -> a).toArray() );
		return drawing;
	}	

	public JobDesignDrawing selectSingle(Long id){
		return jobDesignDrawingMapper.selectSingleDesignDrawing(id);
	}
	
	public List<JobDesignDrawing> selectJobDrawingRecord(Long id
														, String classification, String reason){
		return jobDesignDrawingMapper.selectJobDrawingRecord(id, classification, reason, null, null);
	}
	
	// 견적요청한 대상 도면 목록
	public List<JobDesignDrawing> getOutsourcingEstimateRequestedDrawing(String estimateRequestId){		
		return jobDesignDrawingMapper.selectJobDrawingRecord(null, null, null, estimateRequestId, null);		
	}
	
	// 외주 가공 요청한 도면 목록
	public List<JobDesignDrawing> getOutsourcingRequestedDrawing(String outsourcingRequestId){		
		return jobDesignDrawingMapper.selectJobDrawingRecord(null, null, null, null, outsourcingRequestId);		
	} 
	
	// 지정된 도면이 등록돼 있는지 확인
	public boolean checkExistDesignDrawingNo(DesignDrawingNoEntry entry){
		Integer val = designDrawingMapper.countDrawing(entry.getOrderNoBase(), entry.getOrderNoExtra(), entry.getDrawingNo());
		return val > 0;
	}

	@Transactional
	public List<Long> registerDrawings(List<JobDesignDrawing> arr){
		log.info("도면 [" + arr.size() + "]개 등록 시작");
		
		List<Long> drawingIds =  new ArrayList<>();
		for(JobDesignDrawing entry : arr) {
			designDrawingMapper.insertDrawing(entry);
			
			Long drawingId = entry.getId();
			
			drawingIds.add(drawingId);
		}
		log.info("도면 등록 끝");
		return drawingIds;
	}
	
	public void registerDarwingsOne(JobDesignDrawing jobDesignDrawing) {
		designDrawingMapper.insertDrawing(jobDesignDrawing);
	}
	
	public String outScourcingPTNamefromHistory(String id) {
		return designDrawingMapper.outScourcingPTNamefromHistory(id);
	}
}