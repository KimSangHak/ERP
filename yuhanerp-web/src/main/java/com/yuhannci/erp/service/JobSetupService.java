package com.yuhannci.erp.service;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.JobOrderStatusMapper;
import com.yuhannci.erp.mapper.JobSetupMapper;
import com.yuhannci.erp.model.QcViewData;
import com.yuhannci.erp.model.JobSetupList;
import com.yuhannci.erp.model.db.JobSetup;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobSetupService {

	@Autowired JobSetupMapper jobSetupMapper;
	@Autowired JobOrderStatusMapper jobStatusMapper;
	
	public List<JobSetupList> getQcViewList(){
		
		return jobSetupMapper.selectViewList();
	}
	
	@Transactional
	public Long makeJobSetup(JobSetup inData, String updateuser){
		jobSetupMapper.insertJobSetup(inData);
		
		Long setupId = inData.getId();
		Long jobOrderId = inData.getOrderId();
		log.debug("생성된 Setup ID = "  + setupId.toString() + ", jobOrderId=" + jobOrderId.toString());

		//도면리스트 종료 처리
		jobSetupMapper.updateJobDesignDrawingFinish(jobOrderId);
		
		//구매품리스트 종료 처리 추가 필요
		
		jobStatusMapper.setupStart(jobOrderId, setupId, updateuser);
		return setupId;
	}

}
