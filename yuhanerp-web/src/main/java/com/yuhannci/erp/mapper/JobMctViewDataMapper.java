package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.JobMctViewData;

@Mapper
public interface JobMctViewDataMapper {

	public List<JobMctViewData> selectList();
}
