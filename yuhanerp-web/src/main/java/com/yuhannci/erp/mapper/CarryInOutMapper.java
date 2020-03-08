package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.CarryInEntry;
import com.yuhannci.erp.model.PostprocessFinishedEntry;

@Mapper
public interface CarryInOutMapper {

	// 완품 가공 입고 리스트 --------------------------------------------------------
	
	Integer selectCarryInOutListCount(@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("insourcingProcess") Boolean insourcingProcess,
			@Param("outsourcingProcess") Boolean outsourcingProcess,
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("orderDateFrom") Date orderDateFrom, 
			@Param("orderDateTo") Date orderDateTo,
			@Param("drawings") long[] drawings);
	
	List<CarryInEntry> selectCarryInOutList(@Param("start") Integer start, @Param("length") Integer length, 
			@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("insourcingProcess") Boolean insourcingProcess,
			@Param("outsourcingProcess") Boolean outsourcingProcess,
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("orderDateFrom") Date orderDateFrom, 
			@Param("orderDateTo") Date orderDateTo,
			@Param("drawings") long[] drawings);
	
	// 상태 변경 (완품 등록 등)
	void updateProcessStatus(@Param("id") long id, 
			@Param("currentStage") String currentStage,
			@Param("workStatus") String workStatus,
			@Param("coatingStage") String coatingStage,
			@Param("processCompleted") String processCompleted,
			@Param("updateFinishDate") boolean updateFinishDate,
			@Param("now") Date now
			);

	// 상태 변경(후처리용)
	void updatePostprocessStatus(@Param("id") long id,
			@Param("now") Date now,
			@Param("coatingStage") String coatingStage,
			@Param("updateCoatingFinishDate") boolean updateCoatingFinishDate,
			@Param("coatingRequestId") String coatingRequestId,
			@Param("coatingPartnerId") String coatingPartnerId);
	
	// 상태 변경 (외주 가공 원장 변경, job_partner_outsourcing_order 변경)
	void updatePartnerOutsourcingOrderFinished(
			@Param("now") Date now,
			@Param("outsourcingRequestId") String outsourcingRequestId,
			@Param("designDrawingId") Long designDrawingId,
			@Param("outsourcingPartnerId") String outsourcingPartnerId
			);
	
	// 상태 변경 (외주 가공 원장 변경, job_partner_outsourcing_order 변경)
	void updatePartnerOutsourcingAddOrderFinished(
			@Param("now") Date now,
			@Param("uid") Long uid);
	
	
	void updateProcessWorkQuantity(@Param("id") long id);
	
	
	// 후처리 완료 입고 리스트 ----------------------------------------------------------
	List<PostprocessFinishedEntry> selectPostprocessFinishedList(@Param("start") Integer start, @Param("length") Integer length, 
			@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("insourcingProcess") Boolean insourcingProcess,
			@Param("outsourcingProcess") Boolean outsourcingProcess,
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("orderDateFrom") Date orderDateFrom, 
			@Param("orderDateTo") Date orderDateTo);
	
	Integer selectPostprocessFinishedListCount(@Param("outsourcingPartnerId") String outsourcingPartnerId,
			@Param("insourcingProcess") Boolean insourcingProcess,
			@Param("outsourcingProcess") Boolean outsourcingProcess,
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("orderDateFrom") Date orderDateFrom, 
			@Param("orderDateTo") Date orderDateTo);
	
	void updatePostprocessFinished(@Param("id") long id);
	
	
	
	// 조립/검사 인계 - 리스트 ------------------------------------------------------------------------------------------------------
	List<com.yuhannci.erp.model.CarryToEntry> selectAssembleCheckTargetList(@Param("start") Integer start, @Param("length") Integer length, 
																			@Param("workPositionFilter") String workPositionFilter,
																			@Param("postprocessFinished") boolean postprocessFinished,
																			@Param("outsourcingPartnerId") String outsourcingPartnerId,
																			@Param("checkFinishStatus") String checkFinishStatus,
																			@Param("orderNoBase") String orderNoBase, 
																			@Param("orderNoExtra") String orderNoExtra,
																			@Param("drawingNo") String drawingNo,
																			@Param("orderDateFrom") Date orderDateFrom, 
																			@Param("orderDateTo") Date orderDateTo,
																			@Param("checkNg") boolean checkNg);
	Integer selectAssembleCheckTargetListCount(@Param("workPositionFilter") String workPositionFilter,
											@Param("postprocessFinished") boolean postprocessFinished,
											@Param("outsourcingPartnerId") String outsourcingPartnerId,
											@Param("checkFinishStatus") String checkFinishStatus,
											@Param("orderNoBase") String orderNoBase, 
											@Param("orderNoExtra") String orderNoExtra,
											@Param("drawingNo") String drawingNo,
											@Param("orderDateFrom") Date orderDateFrom, 
											@Param("orderDateTo") Date orderDateTo,
											@Param("checkNg") boolean checkNg);

	void carryToCheckDept(@Param("id") long id, @Param("inspectId") Long inspectId);	
	
	void carryToAssemblyDept(@Param("id") long id, 
								@Param("transferFrom") String transferFrom,
								@Param("transferTo") String transferTo,
								@Param("transferDate") Date transferDate);	
	
	
	
}
