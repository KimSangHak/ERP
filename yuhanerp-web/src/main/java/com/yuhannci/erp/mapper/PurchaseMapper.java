package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.Choice_Estimate;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_Issue;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_histroy;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_issueHistory;
import com.yuhannci.erp.model.InnerStockModel;
import com.yuhannci.erp.model.innerstockListModel;
import com.yuhannci.erp.model.innerStockpop;
import com.yuhannci.erp.model.IssueMailText;
import com.yuhannci.erp.model.MailSendIssue;
import com.yuhannci.erp.model.MailSendIssueNew;
import com.yuhannci.erp.model.ModelInfo;
import com.yuhannci.erp.model.PreEstimateRequest;
import com.yuhannci.erp.model.PurchasePartner;
import com.yuhannci.erp.model.PurchaseSelectHis;
import com.yuhannci.erp.model.SearchCountOrderState;
import com.yuhannci.erp.model.SearchCountPartnerState;
import com.yuhannci.erp.model.SearchOrderState;
import com.yuhannci.erp.model.StatementDetail;
import com.yuhannci.erp.model.StatementIns;
import com.yuhannci.erp.model.StatementInsList;
import com.yuhannci.erp.model.statementList;
import com.yuhannci.erp.model.StatementInsPop;
import com.yuhannci.erp.model.StatementListPartner;
import com.yuhannci.erp.model.StatementListPop;
import com.yuhannci.erp.model.StatementPartner;
import com.yuhannci.erp.model.StatementUpdate;
import com.yuhannci.erp.model.StockHIsIns;
import com.yuhannci.erp.model.StockIns;
import com.yuhannci.erp.model.StockListOrderNo;
import com.yuhannci.erp.model.Warehousing;
import com.yuhannci.erp.model.Warehousing_pop;
import com.yuhannci.erp.model.billingDay;
import com.yuhannci.erp.model.billingMonth;
import com.yuhannci.erp.model.innerAndoutModel;
import com.yuhannci.erp.model.jobpurchaseEstimateHistory;
import com.yuhannci.erp.model.stockRealListModel;
import com.yuhannci.erp.model.stockRealOutModel;
import com.yuhannci.erp.model.Excel.PurchaseLine;
import com.yuhannci.erp.model.EstimateListUpdated;
import com.yuhannci.erp.model.SerachPartnerState;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobPartnerTypeNew;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.db.JobPurchaseDB;
import com.yuhannci.erp.model.db.JobPurchaseHistory;
import com.yuhannci.erp.model.db.JobPurchaseNew;
import com.yuhannci.erp.model.db.JobPurchaseNewList;
import com.yuhannci.erp.model.db.JobPurchaseNew_history;
import com.yuhannci.erp.model.db.JobPurchaseNew_history_null;
import com.yuhannci.erp.model.db.PurchaseHistorySelectDetail;
import com.yuhannci.erp.model.db.Purchase_E_ab;
import com.yuhannci.erp.model.db.Purchase_I_ab;
import com.yuhannci.erp.model.db.SelectJobPurchaseNew;
import com.yuhannci.erp.model.db.TransitionPurchase;
import com.yuhannci.erp.model.db.warehousing_list;
import com.yuhannci.erp.model.SumPriceState;
import com.yuhannci.erp.model.db.SurchAllOrder;
import com.yuhannci.erp.model.db.SurchAllPartner;
import com.yuhannci.erp.model.db.UpdateJobPurchaseNew;
import com.yuhannci.erp.model.db.stock;
import com.yuhannci.erp.model.db.stockInHistroy;
import com.yuhannci.erp.model.db.stockOutHistory;
import com.yuhannci.erp.model.db.JobPurchaseHistory;

@Mapper
public interface PurchaseMapper {
	
	public JobPurchase selectPurchaseEntry(@Param("jobPurchaseId") Long jobPurchaseId, @Param("partnerId") String partnerId, @Param("jobOrderId") Long jobOrderId  );
	public List<JobPurchase> selectPurchaseEntries(@Param("jobPurchaseId") Long jobPurchaseId, @Param("partnerId") String partnerId, @Param("jobOrderId") Long jobOrderId  );
	
	
	public List<GroupedJobPurchaseEntry> selectPurchaseListForEstimate(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("roundRobinYN") String roundRobinYN);
	
	public List<Purchase_E_ab> selectPurchaseListForEstimate_request(@Param("desc") String desc,
			@Param("maker") String maker, @Param("partnerId") String partnerId, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd
			,@Param("kind") String kind, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra);
	public List<Purchase_E_ab> getPurchaseListForEstimate_ListPOP(Choice_Estimate Choice_Estimate);
	public List<Purchase_I_ab> getPurchaseListForIssue_ListPOP(Choice_Estimate Choice_Estimate);
	
	public List<GroupedJobPurchaseEntry_Issue> selectPurchaseListForIssue(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("roundRobinYN") String roundRobinYN);
	public List<Purchase_I_ab> selectPurchaseListForIssue_request(@Param("desc") String desc,
			@Param("maker") String maker, @Param("partnerId") String partnerId, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd,
			@Param("kind") String kind, @Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra);
	
	//발주 List history
	public List<GroupedJobPurchaseEntry_issueHistory> selectPurchaseList_History(@Param("job_purchase_id") Long job_purchase_id
			, @Param("request_id") String request_id);
	
	//입고품 등록(조회)
	public List<Warehousing> selectWarehousing();
	
	//입고품 등록 팝업
	public List<Warehousing_pop> selectWarehousing_pop(String BuyNum);
	
	//입고품 등록
	public void insertWarehousing(warehousing_list warehousing_list);
	
	//입고품 등록 stage 변경
	public void updateStage(String BuyNum);
	
	//견적리스트 발송전 popup
	public List<PreEstimateRequest> preEstimateRequestTargets(@Param("partnerId") String partnerId,
			@Param("jobOrderId") Long[] jobOrderId);
	
	//신규구매리스트 발송전 popup
	public List<Purchase_I_ab> preIssueRequestTargets(@Param("partnerId") String partnerId,
			@Param("jobOrderId") Long[] jobOrderId);
	
	// 견적요청 등록
	public void markAsEstimateRequested(@Param("partnerId") String partnerId,
										@Param("jobOrderId") String jobOrderId,
										@Param("estimateRequestId") String estimateRequestId,
										@Param("estimateRequestUserId") String estimateRequestUserId );
	
	//파트너 이름 출력
	public List<String> selectPartnerName();
	
	public List<PurchasePartner> selectPartnerIdName();
	
	//파트너 ID-->name
	public String IdforNamePartner(String id);
	

	//파트너 name-->ID
	public String NameforIdPartner(String name);
	
	//견적 가상 DB insert
	public void insertEstimateAB(Purchase_E_ab Purchase_E_ab);
	
	//견적 발주 가상 DB insert
	public void insertIssueAB(Purchase_I_ab Purchase_I_ab);
	
	//견적 List deleted
	public void EstimateListDeleted(@Param("jobPurchaseId") Long jobPurchaseId, @Param("estimateRequestId") String estimateRequestId, @Param("jobOrderId") Long jobOrderId);
	
	//견적 List Update
	public void EstimateListUpdated(EstimateListUpdated EstimateListUpdated);
	
	//구매 history select 
	public JobPurchaseHistory selectHistory(@Param("requestId") String requestId, @Param("jobPurchaseId") Long jobPurchaseId);
	
	//구매 가상 history insert
	public void insertHistoryAB(JobPurchaseHistory JobPurchaseHistory);
	
	//stock select where modelNo
	public List<stock> selectstock(String modelNo);
	
	//구매 가상 history select
	public List<JobPurchaseHistory> selectJobPurchaseHistoryAB();
	
	//견적 가상 DB select
	public List<Purchase_E_ab> selectEstimateAB();
	
	//견적 가상 DB select
	public List<Purchase_I_ab> selectIssueAB();
	
	//구매 발주 history 실 DB insert
	public void insertJobPurchaseHistoryReal(JobPurchaseHistory JobPurchaseHistory);
	
	//견적 실 DB insert
	public void insertEstimateReal(Purchase_E_ab Purchase_E_ab);
	
	//견적 발주 실 DB insert
	public void insertIssueReal(Purchase_I_ab Purchase_I_ab);
	
	//구매 발주 구매원장 stage 변경
	public void UpdateJobPurchaseHistoryStage(@Param("id") Long id, @Param("orderRequestId") String orderRequestId, @Param("orderRequestUserId") String orderRequestUserId);
	
	//구매원장 stage E 변경
	public void UpdateStageE(Long id);
	
	//구매원장 stage P 변경
	public void UpdateStageP(@Param("id") Long id,@Param("IssueRequestId") String IssueRequestId,@Param("orderRequestUserId") String orderRequestUserId,@Param("modelNo") String modelNo,
			@Param("partnerId") String partnerId, @Param("maker") String maker);
	
	//가상 history delete
	public void deleteABHistory();
	
	//발주취소
	public void issueCancle(@Param("jobOrderId") Long jobOrderId, @Param("jobPurchaseId") Long jobPurchaseId, @Param("issueRequestId") String issueRequestId, @Param("cancleReason") String cancleReason);
	
	//재고 불출 대기 사용 수량 Update
	public void UpdateStockabQty(@Param("stockId") Long stockId, @Param("stockUseQuantity") int stockUseQuantity);
	
	//발주 삭제시 입고완료확인
	public String IsStage_F(Long id);
	
	//발주 삭제 동작
	public void deleteIssueList(@Param("id") Long id, @Param("deleteUserId") String deleteUserId, @Param("deleteReason") String deleteReason);
	
	//입고품 등록 List
	public List<InnerStockModel> innerStockList( @Param("partnerId") String partnerId, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd, @Param("roundRobinYN") String roundRobinYN);
	
	//입고품 입고 처리
	public List<InnerStockModel> innerStockpop(String issueRequestId);
	
	public List<InnerStockModel> innerListStockpop(@Param("issueRequestId") String issueRequestId, @Param("stage") String stage);
	
	//입고품 반품 처리
	public List<InnerStockModel> outStockpop(@Param("jobPurchaseId") Long jobPurchaseId, @Param("issueRequestId") String issueRequestId, @Param("jobOrderId") Long jobOrderId);
	
	public JobPurchaseDB selectOneJobPurchase(Long jobPurchaseId);
	
	public void updateJobPurchase(@Param("jobPurchaseId") Long jobPurchaseId,
								  @Param("outQuantity") int outQuantity,
								  @Param("outUser") String outUser,
								  @Param("reason") String reason);
	
	public void reBuyreg(JobPurchaseDB JobPurchaseDB);
	
	public void innerAndOutUpdateplus(innerAndoutModel innerAndoutModel);
	
	
	public void innerUpdateList(@Param("jobPurchaseId") Long jobPurchaseId, @Param("receiverUsr") String receiverUsr, @Param("passUsr") String passUsr);
	
	//구매원장 ID select
	public Long jobpurchaseIDselect(String requestId);
	
	//입고 및 반품 처리 update
	public void innerAndOutUpdate(innerAndoutModel innerAndoutModel);
	
	public void innerAndOutUpdateO(innerAndoutModel innerAndoutModel);
	
	//입고품 LIST select
	public List<InnerStockModel> innerstockListModel(
			 @Param("partnerId") String partnerId, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd,
			 @Param("stage") String stage);
	
	//입고품 재고등록 page
	public List<innerstockListModel> viewInsertstock(@Param("jobPurchaseId") Long jobPurchaseId, @Param("requestId") String requestId);
	
	//재고List null 확인
	public stock stockIsnull(String modelNo);
	
	//재고 수량 select
	public int selectStockQt(String modelNo);
	
	public int selectStockQtfromID(Long stockID);
	
	//신재고 insert
	public void insertStockList(stock stock);
	
	//stock Id select
	public Long stockIdSelect(String modelNo);
	
	//stock in History insert
	public void StockInHistoryIns(stockInHistroy stockInHistroy);
	
	//stock Update
	public void UpdateStock(@Param("modelNo") String modelNo,
			@Param("quantity") int quantity);
	
	public void UpdateStockfromID(@Param("stockID") Long stockID,
			@Param("quantity") int quantity);
	//purchase update Stage 'O'
	public void UpdateStagePurchaseO(Long jobPurchaseId);
	
	
	
	//재고 List
	public List<stockRealListModel> stockRealListSelect(@Param("kind") String kind ,@Param("CodeNO") String CodeNO, @Param("modelNo") String modelNo,
			@Param("maker") String maker, @Param("desc") String desc, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	//재고 List id select
	public List<stockRealListModel> stockRealListSelectId(Long id);
	
	//재고 불출
	public void outStock(stockOutHistory stockOutHistory);
	
	//재고 List stock불출 update
	public void stockOutUpdate(@Param("id") Long id, @Param("outQuantity") int outQuantity );
	
	public void stockOutUpdateAB(@Param("id") Long id, @Param("outQuantity") int outQuantity );
	
	public List<stockRealOutModel> listoutpopdetail(Long id);
	
	public void insertStatementDetail(StatementDetail StatementDetail);
	
	//재고 불출 List
	public List<stockRealOutModel> selectStockOutRealList(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("CodeNO") String CodeNO, @Param("modelNo") String modelNo,
			@Param("maker") String maker, @Param("desc") String desc, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);	
	
	public List<JobPurchase> selectEstimateRequestTargets(@Param("partnerId") String partnerId,
										@Param("jobOrderId") Long[] jobOrderId);
	
	public List<MailSendIssue> selectIssueRequestTargets(@Param("partnerId") String partnerId,
			@Param("jobOrderId") Long[] jobOrderId);
	
	public List<MailSendIssueNew> selectIssueRequestTargetsNew(@Param("partnerId") String partnerId,
			@Param("jobOrderId") Long[] jobOrderId);
	
	public List<Purchase_I_ab> selectIssueMailText(@Param("partnerId") String partnerId,
			@Param("jobOrderId") Long[] jobOrderId, @Param("pk") String[] pk);
	
	public List<Purchase_E_ab> getEstimateMailText(@Param("partnerId") String partnerId,
			@Param("jobOrderId") Long[] jobOrderId, @Param("pk") String[] pk);
	
	public String sspartnerId(String estimateRequestId);
	
	//견적 가상 DB 삭제
	public void deletePuchaseEstimateAB();
	
	//발주 가상 DB 삭제
	public void deltePurchaseIssueAB();
	
	public List<JobPurchaseNew> selectJobpurchaseNew(SelectJobPurchaseNew SelectJobPurchaseNew);
	
	public List<JobPurchaseNew_history_null> selectChoiseEdit_Estimate(Choice_Estimate Choice_Estimate);
	
	public List<jobpurchaseEstimateHistory> EstimateHistory(@Param("job_purchase_id") Long job_purchase_id, @Param("model_no") String model_no);
	
	public void UpdateJobPurchaseNew(UpdateJobPurchaseNew UpdateJobPurchaseNew);
	
	public void InsertJobPurchaseNew_history(JobPurchaseNew_history JobPurchaseNew_history);
	
	public void insertPurchaseList(HashMap<String,Object> map);
	
	public List<JobPurchase> selectPurchaseList(@Param("partnerId") String partnerId,@Param("maker") String maker,
			@Param("desc") String desc, @Param("modelNo") String modelNo, @Param("unitNo") String unitNo);
	public void updatePurchaseEntry(JobPurchase data);
	public void updatePurchaseEntryDeleted(@Param("jobPurchaseId") Long jobPurchaseId, @Param("deleteReason") String deleteReason, @Param("deleteUserId") String deleteUserId );
	
	public List<ModelInfo> selectModelList( @Param("category") String category, @Param("keyword") String keyword );	
	
	//JobPurchase partner_id select
	public String selectPartnerId(String customer);
	
	//업체 대분류 select
	public List<JobPartnerTypeNew> selectTypeCode();
	
	//업체 소분류 select
	public List<JobPartnerTypeNew> selectTypeKind(String param);
	
	//몇개월 업체 기준
	public List<billingMonth> selectBillingAfter();
	
	//몇일 업체기준
	public List<billingDay> selectBillingDay();
	
	//구매 거래 명세표 입고시 insert
	public void insertStatement(StatementIns StatementIns);
	
	//거래명세표등록 Page
	public List<StatementInsList> statementInsPage(@Param("PartnerCode") String PartnerCode, 
												   @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, 
												   @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	//파트너 이름 --> 코드 검색
	public String surchPartnerCode(String partnerName);
	
	public String getRequestIdfromstatement(Long issueId);
	
	public Long getJobOrderIdfromstatement(Long issueId);
	
	public Long getPurchaseIdfromstatementDetail(Long id);
	
	public void upDatePurchaseStatementA(Long jobPurchaseId);
	
	public void upDatePurchaseIssueStatementY(@Param("requestId") String requestId ,@Param("jobPurchaseId") Long jobPurchaseId,@Param("jobOrderId") Long jobOrderId);
	
	public void StatementofDetailUpdate(@Param("id") Long id,@Param("issuedItemName") String issuedItemName,@Param("issuedQuantity") int issuedQuantity,@Param("issuedUnitPrice") int issuedUnitPrice);
	
	//구매거래명세표등록pop
	public List<StatementInsPop> statementInspop(Long id);
	
	public void StatementofUpdate(@Param("issueId") Long issueId, @Param("buyKind") String buyKind, @Param("sumPrice") int sumPrice, @Param("negoPrice") int negoPrice, @Param("issueDateR") Date issueDateR);
	
	//거래 명세표 updtae
	public void statementUpdate(StatementUpdate StatementUpdate);
	
	//거래명세표리스트 Page
	public List<statementList> statementListPage(@Param("PartnerCode") String PartnerCode, 
												 @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, 
												 @Param("convertedDesignDateEnd") Date convertedDesignDateEnd,
												 @Param("orderNoBase") String orderNoBase,
												 @Param("orderNoExtra") String orderNoExtra);
	
	//user 검색
	public List<String> userSurch();
	
	public List<String> userSurchwhereDept(String receiveDept);
	
	//user name ----> user code
	public String userCodeSurch(String name);
	
	//user code ----> user name
	public String userNameSurch(String id);
	
	public StatementPartner selectDatePID(Long id);
	
	public StatementListPartner selectPartner(String id);
	
	public List<StatementListPop> statementListPop(Long id);
	
	public Long statementListSum(Long id);
	
	public Long statementListNego(Long id);
	
	public List<String> deptSurch();
	
	public List<DeptData> deptSurch2();
	
	public String deptCode(String name);
	
	public void insertTransitionpurchase(TransitionPurchase TransitionPurchase);
	
	public Long selectOrderIdFrompurchase(Long id);
	
	public Long selectPurchaseIdFromstock(Long id);
	
	public String deptNameSurch(String id);
	
	public List<StockListOrderNo> searchOrderNo();
	
	//매입업체별 기준 매입업체 selelct
	public List<SerachPartnerState> serachPartnerState(@Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	//매입업체별 파트너 카운트
	public List<SearchCountPartnerState> serachCountPartnerState(@Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	//OrderNo별 기준 OrderNo select
	public List<SearchOrderState> serachOrderState(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra ,@Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	//OrderNo별 OrderNo 카운트
	public List<SearchCountOrderState> serachCountOrderState(@Param("orderNoBase") String orderNoBase, @Param("orderNoExtra") String orderNoExtra, @Param("convertedDesignDateBegin") Date convertedDesignDateBegin, @Param("convertedDesignDateEnd") Date convertedDesignDateEnd);
	
	//월별합계 전체  Partner 검색
	public List<SurchAllPartner> surchAllPartner();
	
	//월별합계 전체  Order 검색
	public List<SurchAllOrder> surchAllOrder();
	
	//월별합계 1월
	public List<SumPriceState> janSum();
	
	public List<SumPriceState> febSum();
	
	public List<SumPriceState> marSum();
	
	public List<SumPriceState> aprSum();
	
	public List<SumPriceState> maySum();
	
	public List<SumPriceState> junSum();
	
	public List<SumPriceState> julSum();
	
	public List<SumPriceState> augSum();
	
	public List<SumPriceState> sepSum();
	
	public List<SumPriceState> octSum();
	
	public List<SumPriceState> novSum();
	
	public List<SumPriceState> decSum();
	
	public List<SumPriceState> janSumOrder();
	
	public List<SumPriceState> febSumOrder();
	
	public List<SumPriceState> marSumOrder();
	
	public List<SumPriceState> aprSumOrder();
	
	public List<SumPriceState> maySumOrder();
	
	public List<SumPriceState> junSumOrder();
	
	public List<SumPriceState> julSumOrder();
	
	public List<SumPriceState> augSumOrder();
	
	public List<SumPriceState> sepSumOrder();
	
	public List<SumPriceState> octSumOrder();
	
	public List<SumPriceState> novSumOrder();
	
	public List<SumPriceState> decSumOrder();
	
	//월별 OrderNo 갯수
	public int janCountOrder();
	
	public int febCountOrder();
	
	public int marCountOrder();
	
	public int aprCountOrder();
	
	public int mayCountOrder();
	
	public int junCountOrder();
	
	public int julCountOrder();
	
	public int augCountOrder();
	
	public int sepCountOrder();
	
	public int octCountOrder();
	
	public int novCountOrder();
	
	public int decCountOrder();
	
	//월별 파트너 갯수
	public int janCountPartner();
	
	public int febCountPartner();
	
	public int marCountPartner();
	
	public int aprCountPartner();
	
	public int mayCountPartner();
	
	public int junCountPartner();
	
	public int julCountPartner();
	
	public int augCountPartner();
	
	public int sepCountPartner();
	
	public int octCountPartner();
	
	public int novCountPartner();
	
	public int decCountPartner();
	
	public void insertJobPurchasefromRobin(JobPurchaseDB JobPurchaseDB);
	
	public void deletedTrashData();
	
	public void deletedTrashData2();
	
	public void deletedStockYN(Long id);
	
	public void addInsStockfromPurchase(StockIns stockIns);
	
	public Long sumStockAllList();
	
	public void setStockListUdate(StockIns stockIns);
	
	public List<PurchaseHistorySelectDetail> purcahseSelectHisDetailList(PurchaseSelectHis form);
	
	public Integer purcahseSelectHisDetailListCount(PurchaseSelectHis form);
	
	public void stockHisInsAct(StockHIsIns stockHIsIns);
	
	public void nonePurchaseUpdate(Long jobOrderId);
	
	public void noneNPurchseUpdate(Long jobOrderId);
	
	
}

