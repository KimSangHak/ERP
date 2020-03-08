package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.DrawingRecord;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.JobProcessList;

@Mapper
public interface DrawingRecordMapper {
	// 가공 내역 리스트	
	public List<DrawingRecord> selectDrawingReport(@Param("customerId") String customerId, 
												@Param("orderNoBase") String orderNoBase, 
												@Param("orderNoExtra") String orderNoExtra, 
												@Param("keyword") String keyword,
												@Param("drawingDateFrom") Date orderDateFrom,
												@Param("drawingDateTo") Date orderDateTo);
	

	
	public DrawingRecord selectSingleDrawingReport(@Param("orderId") Long orderId);

	public List<JobProcessList> selectJobProcessList(@Param("orderId") Long orderId, @Param("searchType") Integer searchType);
	
	public List<JobProcessList> selectDrawingEnteringList(@Param("orderId") Long orderId,
													@Param("drawingNo") String drawingNo,
													@Param("outPartnerId") String outPartnerId);

}
