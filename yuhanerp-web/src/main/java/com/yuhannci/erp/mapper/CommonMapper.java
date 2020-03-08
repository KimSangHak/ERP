package com.yuhannci.erp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonMapper {

	// 내부처리용 트랜젝션ID 생성하는 역할
	// sectionId :: 1자리 (NULL 가능), P:구매견적, R:발주요청
	// partnerId :: 5자리 (NULL 가능)
	public String selectTransactionId(@Param("sectionId") String sectionId, @Param("partnerId") String partnerId);
}
