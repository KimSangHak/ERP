package com.yuhannci.erp.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.JobDeliveryMapper;
import com.yuhannci.erp.mapper.JobOrderMapper;
import com.yuhannci.erp.model.db.JobDelivery;

@Service
public class JobDeliveryService {

	@Autowired JobDeliveryMapper jobDeliveryMapper;
	@Autowired JobOrderService jobOrderService;
	
	@Transactional
	public void registerDelivery(JobDelivery data, String lastModifiedUserId) throws Exception{
		
		if(data.getOrderNoBase() == null || data.getOrderNoExtra() == null)
			throw new IllegalArgumentException("Order-No 가 없습니다");
		
		jobDeliveryMapper.insertDelivery(data);
		jobOrderService.makeJobDelivered(data.getJobOrderId(), lastModifiedUserId);
	}
	
	public Map<Long, List<String>> getAttachedImages(Long[] jobOrderId){
		
		List<JobDelivery > deliveries = jobDeliveryMapper.selectDelivery( jobOrderId );
		Map<Long, List<String>> arr = new HashMap<>();
		for(Long id : jobOrderId)
			arr.put(id, new LinkedList<>());
		
		deliveries.stream().forEach(a -> arr.get(a.getJobOrderId()).addAll(a.getImageFileHashes()) );
		return arr;
	}
	
}
