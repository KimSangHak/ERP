package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.ProcessOrderListEntry;
import com.yuhannci.erp.model.ProcessOutsourceListData;
import com.yuhannci.erp.model.db.JobPartnerOutsourcingOrder;

@Mapper
public interface ProcessOutsourceListDataMapper {
	// 생산일정관리 > 가공발주 리스트 테이블 항목 맵핑
	
	public List<ProcessOrderListEntry> selectProcessOutsourceListData(@Param("start") Integer start, @Param("length") Integer length,
			@Param("orderNoBase") String orderNoBase,  
			@Param("orderNoExtra") String orderNoExtra,
			@Param("drawingNo") String drawingNo,
			@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("orderDateFrom") Date orderDateFrom,
			@Param("orderDateTo") Date orderDateTo,
			@Param("designDrawingIdArray") long[] designDrawingIdArray
			);
	
	public JobPartnerOutsourcingOrder selectProcessOutsourceSingleData(@Param("uid") Long uid);
	
	public Integer selectProcessOutsourceListDataCount(
			@Param("orderNoBase") String orderNoBase,  
			@Param("orderNoExtra") String orderNoExtra,
			@Param("drawingNo") String drawingNo,
			@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("orderDateFrom") Date orderDateFrom,
			@Param("orderDateTo") Date orderDateTo);
	
	
	public List<JobPartnerOutsourcingOrder> selectPartnerOutsourcingRequestList(@Param("requestType") String requestType, 
																				@Param("id") String id, 
																				@Param("jobDesignDrawingId") Long jobDesignDrawingId);
	
	public void updateOutsourcingOrderMaster(@Param("uid") Long uid, @Param("unitPrice") Long unitPrice, @Param("deliveryPlanDate") Date deliveryPlanDate);
	public void updateOutsourcingOrder(@Param("uid") Long uid, @Param("unitPrice") Long unitPrice, @Param("deliveryPlanDate") Date deliveryPlanDate);
}
