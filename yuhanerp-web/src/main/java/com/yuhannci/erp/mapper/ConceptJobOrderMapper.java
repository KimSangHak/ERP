package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.ConceptJobOrder;
import com.yuhannci.erp.model.db.JobOrder;

@Mapper
public interface ConceptJobOrderMapper {
	// 장비 컨셉, 치공구 일정 협의
	public List<ConceptJobOrder> selectConcept(@Param("orderType") String jobType 
												, @Param("orderNoBase") String orderNoBase
												, @Param("orderNoExtra") String orderNoExtra
												, @Param("conceptBuilt") boolean conceptBuilt		// 컨셉 제작 완료 여부.  true : 컨셉 완료, false : 컨셉 진행중
												, @Param("activeList") boolean activeList			// true : 영업일보리스트 등, false : 지난 영업 일보
												, @Param("includeDeleted") boolean includeDeleted
												, @Param("keyword") String keyword
												, @Param("orderDateFrom") Date orderDateFrom
												, @Param("orderDateTo") Date orderDateTo );
	
	public ConceptJobOrder findConcept(@Param("id") Long id
										, @Param("orderType") String orderType
										, @Param("orderNoBase") String orderNoBase
										, @Param("orderNoExtra") String orderNoExtra );
	
	// 컨셉 작업 저장
	public void insertConceptJob(ConceptJobOrder data);
	
	// 컨셉 데이터 수정
	public void updateConceptJob(ConceptJobOrder data);

	// 설비 컨셉을 삭제한 걸로 표기
	public void markConceptJobDeleted(@Param("id") Long id,  
										@Param("lastModifiedUserId") String lastModifiedUserId);
	
	// 설비 컨셉 완료
	public void markConceptJobFinished(@Param("id") Long id,  
										@Param("lastModifiedUserId") String lastModifiedUserId, 
										@Param("conceptFinishDate") Date conceptFinishDate);
	
	// 작업 지시로 변경
	public void markConceptJobIsOrdered(@Param("id") Long id,
										@Param("jobOrderId") Long jobOrderId, 
										@Param("lastModifiedUserId") String lastModifiedUserId
										);
}
