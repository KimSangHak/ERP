package com.yuhannci.erp.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphbuilder.struc.LinkedList;
import com.yuhannci.erp.mapper.JobOrderMapper;
import com.yuhannci.erp.model.DesignDrawingNoEntry;
import com.yuhannci.erp.model.db.JobOrder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderNoService {
	
	@Autowired JobOrderMapper jobOrderMapper;
	
	final int minimumOrderNoLength = 5;		// orderNoBase 의 길이
	final int minimumDrawingNoLength = 2; 	//DrawingNo
	
	// 합쳐진 order-no 를 분해해서 order-no-base 와 order-no-extra 로 반환한다
	public SimpleEntry<String, String> parseOrderNo(String orderNo){
		
		if(orderNo == null)
			return new SimpleEntry<String, String>(null, null);
		
		if(orderNo.length() < minimumOrderNoLength)
			throw new IllegalArgumentException("잘못된 OrderNo [" + orderNo + "] 입니다");
				
		// aabbb
		// aa-bbb
		// aabbb-ccc
		// aabbb-cc-as-1
		
		String[] tokens = orderNo.split("-");
		log.debug("분리된 토큰 = " + tokens.length);
		
		List<String> extraTokens = new java.util.LinkedList<>();
		StringBuilder base = new StringBuilder(); // , extra = new StringBuilder();
		for(String token : tokens){
			if(base.length() >= minimumOrderNoLength){
				extraTokens.add(token);
//				extra.append(token);
//				extra.append("-");
			}
			else
				base.append(token);
		}
//		while(extra.length() > 0 && extra.charAt(extra.length() - 1) == '-')
//			extra.deleteCharAt(0);
		String extra = String.join("-", extraTokens);
		
		log.debug("[" + orderNo + "] 를 base=[" + base + "], extra=[" + extra + "] 로 분리");
		return new SimpleEntry<String, String>(base.toString(), extra);
	}
	
	public SimpleEntry<String, String> getOrderNoFromJobOrderId(Long jobOrderId){
		
		JobOrder order = jobOrderMapper.selectSingleOrder(null, null, jobOrderId);
		if(order == null)
			return null;
		
		
		
		return new SimpleEntry<String, String>(order.getOrderNoBase(), order.getOrderNoExtra());
	}
	
	public String concatOrderNo(String base, String extra){
		final String trimmedBase = base.trim();
		final String trimmedExtra = extra.trim();
		
		StringBuilder sb = new StringBuilder();
		sb.append(base.trim());
		if(!trimmedExtra.startsWith("-"))
			sb.append('-');
		sb.append(trimmedExtra);
		
		return sb.toString();
	}
	
	public DesignDrawingNoEntry parseDesignDrawingNo(String drawingNo){
		String[] tokens = drawingNo.split("-");
		if(tokens.length < 1)
			throw new IllegalArgumentException("설계도면번호[" + drawingNo + "]가 잘못된 형식을 가지고 있습니다");
						
		DesignDrawingNoEntry result = new DesignDrawingNoEntry();
		result.setDrawingNo(tokens[ tokens.length - 1 ]);
		
		StringBuilder base = new StringBuilder(), extra = new StringBuilder();		
		for(int i=0;i<tokens.length - 1;i++){
			if(base.length() < minimumOrderNoLength)
				base.append(tokens[i]);
			else {
				
				if(i==1) {
					extra.append(tokens[i]);
				}
				else {
					extra.append("-"+tokens[i]);
					
				}
			}
			
		}			
		
		if(base.length() < minimumOrderNoLength)
			throw new IllegalArgumentException("작업 지시 번호의 base 가 너무 짧습니다");
		
		result.setOrderNoBase(base.toString());
		result.setOrderNoExtra(extra.toString());
		
		
		return result;
	}

		
	/**
	 * '-' 가 2개인 경우 base만 리턴
	 * '-' 가 3개인 경우 base, extra 리턴
	 * 
	 * @param drawingId
	 * @return
	 */
	public SimpleEntry<String, String> parseDrawingNo(String drawingId) {
		if(drawingId == null)
			return new SimpleEntry<String, String>(null, null);
		
		if(drawingId.length() < minimumOrderNoLength)
			throw new IllegalArgumentException("잘못된 drawingId [" + drawingId + "] 입니다");
				
		// aabbb
		// aa-bbb
		// aabbb-ccc
		// aabbb-cc-as-1
		
		String[] tokens = drawingId.split("-");
		log.debug("분리된 토큰 = " + tokens.length);
		
		StringBuilder base = new StringBuilder(), extra = new StringBuilder();
		
		for(String token : tokens){
			
			if(tokens.length <= 2) {
				
				if(base.length() < minimumOrderNoLength) {
					base.append(token);
					log.debug("Base : " + base.toString());
				}
			} else {
			
				if(base.length() >= minimumOrderNoLength){
					extra.append(token);
					extra.append("-");
					log.debug("Base : " + base.toString() + "  extra : " + extra.toString());
				}
				else
					base.append(token);
			}
			
		}
		while(extra.length() > 0 && extra.indexOf("-") > -1)
			extra.deleteCharAt(extra.length() - 1);
		
		log.debug("[" + drawingId + "] 를 base=[" + base + "], extra=[" + extra + "] 로 분리");
		return new SimpleEntry<String, String>(base.toString(), extra.toString());
	}
	
	/**
	 * 치공구 인 경우 2자리 
	 * 설비 인 경우 4자리
	 * 
	 * @param drawingId
	 * @return
	 */
	public String getDrawingNo(String drawingId){
		if(drawingId == null)
			return null;
		
		int start = drawingId.lastIndexOf('-') + 1;
		String result = drawingId.substring(start);
		
		StringBuilder sb = new StringBuilder();
		sb.append(result);
		
		if(sb.length() < minimumDrawingNoLength)
			throw new IllegalArgumentException("잘못된 drawingId [" + drawingId + "] 입니다");
		return sb.toString();
	}
		
}
