package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.JobPartnerOutsourcingOrder;

@Mapper
public interface JobDesignDrawingEstimateMapper {

	Long getOrderIdCount(@Param("requestType") String requestType
						, @Param("outsourcingPartnerId") String outsourcingPartnerId
						, @Param("estimateIdInitial") String estimateIdInitial);
	
	void insertEstimate(@Param("outsourcingPartnerId") String outsourcingPartnerId
						, @Param("estimateId") String estimateId
						, @Param("drawingId") Long drawingId
						, @Param("requested") Date when
						, @Param("deliveryDate") Date deliveryDate
						, @Param("drawingUrl") String drawingUrl
						, @Param("withPostprocessing") String withPP
						);
	void insertProcessOrder(@Param("outsourcingPartnerId") String outsourcingPartnerId
			, @Param("estimateId") String estimateId
			, @Param("drawingId") Long drawingId, @Param("requested") Date when
			, @Param("deliveryDate") Date deliveryDate, @Param("drawingUrl") String drawingUrl
			, @Param("withPostprocessing") String withPP
			, @Param("unitPrice") Integer unitPrice
			);	
	
	void insertPostprocessOrder(@Param("outsourcingPartnerId") String outsourcingPartnerId
								, @Param("outsourcingId") String outsourcingId
								, @Param("drawingId") Long drawingId
								, @Param("requested") Date requestedWhen
								, @Param("deliveryDate") Date deliveryDate
								);
	
	List<JobPartnerOutsourcingOrder> selectOutsourcingOrder(@Param("id") String id, @Param("jobDrawingId") String jobDrawingId, 
														@Param("requestType") String requestType, @Param("partnerId") String partnerId  );
	
	List<Long> selectOutsourcingOrderDevices(@Param("id") String id, @Param("jobDrawingId") String jobDrawingId, 
			@Param("requestType") String requestType, @Param("partnerId") String partnerId  );	
}
