package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yuhannci.erp.model.JobSetupList;
import com.yuhannci.erp.model.db.JobSetup;

@Mapper
public interface JobSetupMapper {

	public List<JobSetupList> selectViewList();
	
	public void insertJobSetup(JobSetup inData);
	
	public void updateJobDesignDrawingFinish(Long id);
	
}
