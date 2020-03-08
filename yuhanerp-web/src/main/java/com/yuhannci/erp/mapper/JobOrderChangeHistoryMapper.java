package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.JobOrderModificationEntry;
import com.yuhannci.erp.model.db.JobOrderModificationHistory;

@Mapper
public interface JobOrderChangeHistoryMapper {
	public void insertHistory(JobOrderModificationHistory entry);
	
	public void insertHistory(@Param("jobOrderId") Long jobId, 
							@Param("orderType") String orderType, 
							@Param("sourceCategory") String sourceCategory,
							@Param("changeType") String changeType, 
										@Param("userId") String userId, @Param("ret") JobOrderModificationHistory ret);
	
	public void insertValueChangeHistory(@Param("jobOrderChangeId") Long jobOrderChangeId, 									
										@Param("fieldName") String fieldName, 
										@Param("before") String original, 
										@Param("after") String newValue);	
	
	public List<JobOrderModificationEntry> selectChangeHistory(@Param("jobOrderId") Long id);
}
