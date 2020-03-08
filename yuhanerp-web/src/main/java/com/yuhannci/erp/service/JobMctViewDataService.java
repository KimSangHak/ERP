package com.yuhannci.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.JobMctViewDataMapper;
import com.yuhannci.erp.model.JobMctViewData;

@Service
public class JobMctViewDataService {

	@Autowired JobMctViewDataMapper jobMctViewDataMapper;
	
	public List<JobMctViewData> getMctViewList(){
		
		return jobMctViewDataMapper.selectList();
		
	}

}
