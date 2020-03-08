package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.GroupedJobPurchaseEntry;
import com.yuhannci.erp.model.ModelInfo;
import com.yuhannci.erp.model.Excel.PurchaseLine;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.JobPurchaseAssembleList;

@Mapper
public interface PurchaseHistoryMapper {
	void insertPurchaseHistory(@Param("requestId") String requestId, 
								@Param("requestType") String requestType, 
								@Param("partnerId") String partnerId, 
								@Param("jobOrderIdList") List<Long> jobOrderIdList);
	
	//조립관리화면 > 구매품 리스트 팝업
	public List<JobPurchaseAssembleList> selectJobPurchaseAssembleList(
					@Param("orderId") Long orderId, @Param("searchType") Integer searchType);
}
