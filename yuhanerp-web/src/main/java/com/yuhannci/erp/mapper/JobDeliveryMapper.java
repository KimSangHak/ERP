package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.JobDelivery;

@Mapper
public interface JobDeliveryMapper {

	public void insertDelivery(JobDelivery data);
	public List<JobDelivery> selectDelivery(@Param("jobOrderIds") Long[] jobOrderIds);
}
