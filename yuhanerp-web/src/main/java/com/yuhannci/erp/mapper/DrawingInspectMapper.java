package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.DrawingInspect;

@Mapper
public interface DrawingInspectMapper {
	// 가공 및 검사 현황
	public List<DrawingInspect> selectDrawingInspectStatusList(@Param("orderNoBase") String orderNoBase,  
			@Param("orderNoExtra") String orderNoExtra, 
			@Param("drawingNo") String drawingNo, 
			@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("orderDateFrom") Date orderDateFrom,
			@Param("orderDateTo") Date orderDateTo,
			@Param("start") Integer start,
			@Param("length") Integer length);
	
	public Integer selectDrawingInspectStatusListCount(@Param("orderNoBase") String orderNoBase,  
			@Param("orderNoExtra") String orderNoExtra, 
			@Param("drawingNo") String drawingNo, 
			@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("orderDateFrom") Date orderDateFrom,
			@Param("orderDateTo") Date orderDateTo);
	
}
