package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.CalendarDayJob;
import com.yuhannci.erp.model.db.ConceptJobOrder;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.JobOrderProgress;
import com.yuhannci.erp.model.KeyValueEntry;

@Mapper
public interface JobOrderMapper {
	
	public void insertJob(JobOrder order);
	
	public void reUpdateJob(JobOrder order);
	
	// 주문 번호로 설비 작업 진행 정보 조회
	public List<JobOrder> selectJobOrder(	@Param("orderType") String jobType, 
											@Param("orderNoBase") String orderNoBase, 
											@Param("orderNoExtra") String orderNoExtra, 
											@Param("finishStatus") String finishStatus,
											@Param("keyword") String keyword,
											@Param("orderDateFrom") Date orderDateFrom,
											@Param("orderDateTo") Date orderDateTo,
											@Param("deliveryDateFrom") Date deliveryDateFrom,
											@Param("deliveryDateTo") Date deliveryDateTo,
											@Param("designDateFrom") Date designDateFrom,
											@Param("designDateTo") Date designDateTo);
	
	public List<JobOrderProgress> selectJobOrderProgress(@Param("orderType") String orderType, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra);
	public JobOrderProgress selectJobOrderProgressSingle(@Param("key") Long key);
	public List<JobOrderProgress> selectJobOrderProgressPage(@Param("orderType") String orderType, @Param("psize") Integer psize, @Param("page") Integer page);
	
	public List<KeyValueEntry> selectIngOrderList();
	public Long selectIngOrderListFirst();
	
	public JobOrder selectSingleOrder(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("id") Long id);
	public Long selectJobOrderExists(@Param("orderType") String jobType,  @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra);
	
	public List<CalendarDayJob> selectCalendarData(@Param("searchInitialDate") Date searchInitialDate);
	
	// 견적 금액 갱신
	public void updateEstimation(JobOrder order);
	
	// 삭제
	public void deleteJob(@Param("id") Long id, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("lastModifiedUserId") String lastModifiedUserId);
	
	// 완료된걸로 변경
	public void markFinishedJob(@Param("id") Long id, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("lastModifiedUserId") String lastModifiedUserId);
	// 배송 상태로 변경
	public void markJobDelivered(@Param("id") Long id, @Param("lastModifiedUserId") String userId);
	// 셋업 상태로 변경
	public void markJobSetup(@Param("id") Long id, @Param("lastModifiedUserId") String userId);
		
	// 수정
	public void updateJob(JobOrder data);
	// 도면 작업 진행률 변경
	public void updateDesignProgress(JobOrder data);
	
	public String selectJobOrderFromPush(Long id);
}
