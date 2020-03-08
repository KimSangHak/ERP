package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.CarryInEntry;
import com.yuhannci.erp.model.DesignHistorywhere;
import com.yuhannci.erp.model.NGcarry;
import com.yuhannci.erp.model.ProcessHisSearchForm;
import com.yuhannci.erp.model.db.DesignDrawingHistory;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobInspect;
import com.yuhannci.erp.model.db.JobMctHistory;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.db.ProcessHistoryDetail;

@Mapper
public interface ProcessManageMapper {

	// 가공 대기 내역
	List<JobDesignDrawing> selectProcessReadyDetailList(	@Param("start") Integer start,
			@Param("length") Integer length,
			@Param("selectedCustomer") String selectedCustomer, 
			@Param("device") String device, 
			@Param("orderNoBase") String orderNoBase,
			@Param("orderNoExtra") String orderNoExtra, 
			@Param("drawingNo") String drawingNo
			);
	
	Integer selectProcessReadyDetailListCount(	@Param("selectedCustomer") String selectedCustomer, 
			@Param("device") String device, 
			@Param("orderNoBase") String orderNoBase,
			@Param("orderNoExtra") String orderNoExtra, 
			@Param("drawingNo") String drawingNo );
	
	/*
	 * 사내가공
	 */
	int assignToInnerProcess(@Param("id") Long id
			, @Param("processFinishPlanDate") Date processFinishPlanDate
			, @Param("coatingFinishPlanDate") Date coatingFinishPlanDate
			, @Param("inspectPlanDate") Date inspectPlanDate
									);
	/*
	 * 외주 가공
	 */
	int assignToPartnerProcess(@Param("id") Long id, @Param("outsourcingPartnerId") String outsourcingPartnerId
			, @Param("processFinishPlanDate") Date processFinishPlanDate
			, @Param("coatingFinishPlanDate") Date coatingFinishPlanDate
			, @Param("inspectPlanDate") Date inspectPlanDate
			);
	/*
	 * 가공취소
	 */
	int cancelProcessRequest(@Param("id") Long id, @Param("holdReason") String holdReason);
	
	int cancelProcessRequest2(@Param("id") Long id, @Param("holdReason") String holdReason);
	/* 
	 * 표준품 
	 */
	int assignToStandardProduct(@Param("id") Long id
			, @Param("processFinishPlanDate") Date processFinishPlanDate
			, @Param("coatingFinishPlanDate") Date coatingFinishPlanDate
			, @Param("inspectPlanDate") Date inspectPlanDate
			);
	/*
	 * 표준품 가공
	 */
	int assignToStandardProductWithProcess(@Param("id") Long id
			, @Param("processFinishPlanDate") Date processFinishPlanDate
			, @Param("coatingFinishPlanDate") Date coatingFinishPlanDate
			, @Param("inspectPlanDate") Date inspectPlanDate
			);
		
	// 가공 내역 관리에서, 외주가공으로 등록된 리스트 
	List<JobDesignDrawing> selectOutsourcingList(@Param("start") Integer start,
			@Param("length") Integer length,
			@Param("selectedPartnerId") String selectedPartnerId,
			@Param("selectedCustomer") String selectedCustomer,
			@Param("device") String device, 
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("drawingNo") String drawingNo);
	
	Integer selectOutsourcingListCount(@Param("selectedPartnerId") String selectedPartnerId,
			@Param("selectedCustomer") String selectedCustomer,
			@Param("device") String device, 
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("drawingNo") String drawingNo);
	
	void markAsEstimateRequested(@Param("id") Long id, @Param("estimateRequestId") String estimateRequestId);
	void markToOutsourcingOrdered(@Param("id") Long id, @Param("withPostprocessing") boolean withPostprocessing, @Param("outsourcingRequestId") String outsourcingRequestId );
	void markToAddOutsourcingOrdered(@Param("id") Long id,
			@Param("processFinishPlanDate") Date processFinishPlanDate,
			@Param("outsourcingPartnerId") String outsourcingPartnerId, 
			@Param("outsourcingRequestId") String outsourcingRequestId );

	void markAsPostprocessOrderRequested(@Param("id") Long id, @Param("partnerId") String partnerId, @Param("orderId") String orderId);
	
	// SKIP --> 후처리 발주 전환
	void updateSkipToPostprocessOrderRequested(@Param("id") Long id, @Param("partnerId") String partnerId, @Param("orderId") String orderId);
	
	// 가공 검사 현황 리스트
	List<com.yuhannci.erp.model.ProcessingAndCheckListEntry> selectprocessingAndCheckList(	@Param("outsourcingPartnerId") String outsourcingPartnerId, 
															@Param("orderNoBase") String orderNoBase, 
															@Param("orderNoExtra") String orderNoExtra,
															@Param("drawingNo") String drawingNo,
															@Param("orderDateFrom") Date orderDateFrom, 
															@Param("orderDateTo") Date orderDateTo, 
															@Param("start") Integer start,
															@Param("length") Integer length,
															@Param("workPosition") String workPosition,
															@Param("statusR") String statusR);
	Integer selectprocessingAndCheckListCount(	@Param("outsourcingPartnerId") String outsourcingPartnerId, 
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("drawingNo") String drawingNo,
			@Param("orderDateFrom") Date orderDateFrom, 
			@Param("orderDateTo") Date orderDateTo,
			@Param("workPosition") String workPosition,
			@Param("statusR") String statusR);
	
	
	// 완품/가공 입고 - 리스트
	/*
	List<CarryInEntry> selectWarehousingOfFinishedOrProcessingList(	@Param("outsourcingPartnerId") String outsourcingPartnerId, 
			@Param("workPosition") String workPosition,
			@Param("device") String device, 
			@Param("orderNoBase") String orderNoBase, 
			@Param("orderNoExtra") String orderNoExtra,
			@Param("orderDateFrom") Date orderDateFrom, 
			@Param("orderDateTo") Date orderDateTo);
	*/
	void MarkAsFinishedProductIn(@Param("id") Long designDrawingId, @Param("coatingStage") String newCoatingStage);
	
	public void setOutsourcingPartnerIdAndWorkPosition(JobDesignDrawing drawing);
	
	public List<JobOrder> selectMovePossibleOrder();
	
	public List<JobOrder> selectMovePossibleOrder2();
	
	public List<JobDesignDrawing> MoveDesignDrawingPopup(Long id);
	
	public String OrderIdToOrderNoFromMoverOrder(Long id);
	
	public String selectDrawingNoFromMoveOrder(Long id);
	
	public int countIsDrawingNoEqauls(@Param("selectOrderId") Long selectOrderId, @Param("grandDrawingNo") String grandDrawingNo);
	
	public void processSetWorkMinAll(Long id);
	
	public List<JobMctHistory> JobHistroyMctFromMoveAll(Long id);
	
	public Long jobDesignIdFromMove(@Param("selectOrderId") Long selectOrderId,  @Param("grandDrawingNo") String grandDrawingNo);
	
	public String OrderIdToOrderNoBaseFromMoverOrder(Long id);
	
	public String OrderIdToOrderNoExtraFromMoverOrder(Long id);
	
	public void addJobMctHistoryFromMove(JobMctHistory jobMctHistory);
	
	public void deleteJobMctHistoryFromMove(Long id);
	
	public void updateJobMctHistoryFromMove(@Param("id") Long id, @Param("workQuantity") int workQuantity);
	
	public List<JobInspect> jobInspectFromMove(@Param("jobDrawingId") Long[] jobDrawingId);
	
	public List<JobDesignDrawing> DrawingFromMoveSelectIdForInspect(Long grandOrderId);
	
	public void addFromMoveForJobInspect(JobInspect JobInspect);
	
	public void updateFromMoveForDesignQuantity(@Param("id") Long id, @Param("qty") int qty);
	
	public JobDesignDrawing oneDesignSelectGrandid(Long id);
	
	public int nodeOrderDesignqtyCount(Long id);
	
	public void nodeOrderDesignDrawingSet(@Param("id") Long id, @Param("qty") int qty);
	
	public void grandOrderDesignDrawingSetCureent(Long id);
	
	public JobDesignDrawing oneDesignSelectNodeid(Long id);
	
	public void nodeOrderDesignDrawingSetAll(JobDesignDrawing jobDesignDrawing);
	
	public void grandOrderDesignDrawingSetWorkQty(@Param("id") Long id, @Param("qty") int qty);
	
	public int grandOrderDesignSelectQtyFromMove(Long id);
	
	public int grandOrderDesignSelectWorkQtyFromMove(Long id);
	
	public void moveOrderCompletFromMM(Long id);
	
	public String whatKindOfProcess(Long id);
	
	public String whatProcessStatus(Long id);
	
	public void updateDesigToProcessFirst(Long id);
	
	public void updateDesignOToProcessFirst(Long id);
	
	public Long selectedUIDFromOutsourcing(Long drawingId);
	
	public void deletedOutsourcingFromUID(Long uid);
	
	public void updateCheckTrueFromCarryIn(Long id);
	
	public void updateReProcessOutToIn(Long id);
	
	public String selectInsepectResult(Long jobDrawingId);
	
	public String selectDrawingKindworkPositon(Long id);
	
	public void insDesignHistry(@Param("drawingId") Long drawingId, @Param("kind") String kind, @Param("regUsr") String regUsr,  @Param("description") String description);
	
	public NGcarry selectNGcarry(Long id);
	
	public void updateDesignfromNGcarry(@Param("id") Long id, @Param("rqty") int rqty);
	
	public List<ProcessHistoryDetail> selectProcessHistoryPageList(ProcessHisSearchForm form);

	public Integer selectProcessHistoryPageListCount(ProcessHisSearchForm form);
	
	public List<DesignDrawingHistory> detailDesignDrawingOnehistory(Long id);
	
	public DesignHistorywhere nowDrawinghistoryWhere(Long id);
	
	public String drawHisFullNo(Long id);
}
