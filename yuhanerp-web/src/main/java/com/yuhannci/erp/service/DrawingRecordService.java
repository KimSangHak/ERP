package com.yuhannci.erp.service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.DrawingRecordMapper;
import com.yuhannci.erp.model.DrawingRecord;

import lombok.extern.slf4j.Slf4j;

// 가공 관련된 서비스

@Service
@Slf4j
public class DrawingRecordService {
	
	@Autowired DrawingRecordMapper drawingRecordMapper;
	
	// 가공 내역 리스트
	public List<DrawingRecord> getDrawingRecordList(String partnerId, String orderNoBase, String orderNoExtra,
												String keyword, Date drawingDateFrom, Date drawingDateTo){
		
		try{
			partnerId = StringUtils.isEmpty(partnerId) ? null : partnerId;
			orderNoBase = StringUtils.isEmpty(orderNoBase) ? null : orderNoBase;
			orderNoExtra = StringUtils.isEmpty(orderNoExtra) ? null : orderNoExtra;
			keyword = StringUtils.isEmpty(keyword) ? null : keyword;
			drawingDateFrom = StringUtils.isEmpty(drawingDateFrom) ? null : drawingDateFrom;
			drawingDateTo = StringUtils.isEmpty(drawingDateTo) ? null : drawingDateTo;

			return drawingRecordMapper.selectDrawingReport(partnerId, orderNoBase, orderNoExtra, keyword, drawingDateFrom, drawingDateTo); 
		}catch(Exception e){
			log.error("내역 검색 중 오류", e);
		}		
		
		return null;		
	}

	// 가공 내역 리스트
	public DrawingRecord getDrawingRecordPopDetailList(Long orderId){
		
		try{
			return drawingRecordMapper.selectSingleDrawingReport(orderId); 
		}catch(Exception e){
			log.error("내역 검색2 중 오류", e);
		}		
		
		return null;		
	}
	
	
	
}
