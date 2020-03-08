package com.yuhannci.erp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yuhannci.erp.model.db.JobInspect;

@Mapper
public interface InspectMapper {
	void registerOrder(JobInspect data);
}
