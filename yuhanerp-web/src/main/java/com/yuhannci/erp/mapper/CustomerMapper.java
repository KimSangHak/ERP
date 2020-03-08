package com.yuhannci.erp.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.IdNameEntry;
import com.yuhannci.erp.model.BillingPartner;
import com.yuhannci.erp.model.db.JobPartner;

@Mapper
public interface CustomerMapper {
	public List<IdNameEntry> selectSimpleCustomerList();
	
	public String getNextCustomerSequenceNo(@Param("isConcept") String isConcept, @Param("customerId") String customerId);
	
	public List<BillingPartner> selectPartnerList(@Param("offset") Integer pageOffset, @Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy, @Param("sortOrder") String sortOrder);
	public Integer selectPartnerListCount();
	
	public List<JobPartner> selectBuyPartner(@Param("category") String category, @Param("keyword") String keyword);
	
	// 아래 함수는 PartnerMapper 로 이동
	// public String selectBuyPartnerName(@Param("partnerId") String id);	
	// public JobPartner selectPartner(@Param("partnerId") String id);
}
