package com.yuhannci.erp.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.common.base.Strings;
import com.yuhannci.erp.mapper.CommonMapper;
import com.yuhannci.erp.mapper.PartnerMapper;
import com.yuhannci.erp.mapper.PurchaseHistoryMapper;
import com.yuhannci.erp.mapper.PurchaseMapper;
import com.yuhannci.erp.model.db.Purchase_E_ab;
import com.yuhannci.erp.model.db.Purchase_I_ab;
import com.yuhannci.erp.model.Choice_Estimate;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.EstimateListUpdated;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_Issue;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_histroy;
import com.yuhannci.erp.model.GroupedJobPurchaseEntry_issueHistory;
import com.yuhannci.erp.model.InnerStockModel;
import com.yuhannci.erp.model.IssueMailText;
import com.yuhannci.erp.model.MailSendIssue;
import com.yuhannci.erp.model.MailSendIssueNew;
import com.yuhannci.erp.model.ModelInfo;
import com.yuhannci.erp.model.PreEstimateRequest;
import com.yuhannci.erp.model.ProcessHisSearchForm;
import com.yuhannci.erp.model.PurchasePartner;
import com.yuhannci.erp.model.PurchaseSelectHis;
import com.yuhannci.erp.model.SearchCountOrderState;
import com.yuhannci.erp.model.SearchCountPartnerState;
import com.yuhannci.erp.model.SearchOrderState;
import com.yuhannci.erp.model.SerachPartnerState;
import com.yuhannci.erp.model.StatementDetail;
import com.yuhannci.erp.model.StatementIns;
import com.yuhannci.erp.model.StatementInsList;
import com.yuhannci.erp.model.StatementInsPop;
import com.yuhannci.erp.model.StatementListPartner;
import com.yuhannci.erp.model.StatementListPop;
import com.yuhannci.erp.model.StatementPartner;
import com.yuhannci.erp.model.StatementUpdate;
import com.yuhannci.erp.model.StockHIsIns;
import com.yuhannci.erp.model.StockIns;
import com.yuhannci.erp.model.StockListOrderNo;
import com.yuhannci.erp.model.SumPriceState;
import com.yuhannci.erp.model.Warehousing;
import com.yuhannci.erp.model.Warehousing_pop;
import com.yuhannci.erp.model.billingDay;
import com.yuhannci.erp.model.billingMonth;
import com.yuhannci.erp.model.innerAndoutModel;
import com.yuhannci.erp.model.innerStockpop;
import com.yuhannci.erp.model.innerstockListModel;
import com.yuhannci.erp.model.jobpurchaseEstimateHistory;
import com.yuhannci.erp.model.statementList;
import com.yuhannci.erp.model.stockRealListModel;
import com.yuhannci.erp.model.stockRealOutModel;
import com.yuhannci.erp.model.Excel.PurchaseLine;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.db.JobPartner;
import com.yuhannci.erp.model.db.JobPartnerType;
import com.yuhannci.erp.model.db.JobPartnerTypeNew;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.db.JobPurchaseDB;
import com.yuhannci.erp.model.db.JobPurchaseHistory;
import com.yuhannci.erp.model.db.JobPurchaseNew;
import com.yuhannci.erp.model.db.JobPurchaseNewList;
import com.yuhannci.erp.model.db.JobPurchaseNew_history;
import com.yuhannci.erp.model.db.JobPurchaseNew_history_null;
import com.yuhannci.erp.model.db.SelectJobPurchaseNew;
import com.yuhannci.erp.model.db.SurchAllOrder;
import com.yuhannci.erp.model.db.SurchAllPartner;
import com.yuhannci.erp.model.db.TransitionPurchase;
import com.yuhannci.erp.model.db.warehousing_list;
import com.yuhannci.erp.model.db.UpdateJobPurchaseNew;
import com.yuhannci.erp.model.db.stock;
import com.yuhannci.erp.model.db.stockInHistroy;
import com.yuhannci.erp.model.db.stockOutHistory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseService {

	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	PartnerMapper partnerMapper;
	@Autowired
	PurchaseHistoryMapper purchaseHistoryMapper;
	@Autowired
	UtilityService utilityService;

	public List<GroupedJobPurchaseEntry> getPurchaseListForEstimate(String orderNoBase,
			String orderNoExtra, String roundRobinYN) {
		

		return purchaseMapper.selectPurchaseListForEstimate(orderNoBase, orderNoExtra, roundRobinYN);

	}
	
	public List<Purchase_E_ab> getPurchaseListForEstimate_request(String desc,
		 String maker, String partnerId, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind, String orderNoBase, String orderNoExtra) {
		

		return purchaseMapper.selectPurchaseListForEstimate_request(desc, maker, partnerId, convertedDesignDateBegin, convertedDesignDateEnd, kind, orderNoBase, orderNoExtra);

	}
	
	public List<Purchase_E_ab> getPurchaseListForEstimate_ListPOP(Choice_Estimate Choice_Estimate) {
		

		return purchaseMapper.getPurchaseListForEstimate_ListPOP(Choice_Estimate);

	}
	
	public List<Purchase_I_ab> getPurchaseListForIssue_ListPOP(Choice_Estimate Choice_Estimate) {
		

		return purchaseMapper.getPurchaseListForIssue_ListPOP(Choice_Estimate);

	}

	public List<GroupedJobPurchaseEntry_Issue> getPurchaseListForIssue(String orderNoBase,
			String orderNoExtra, String roundRobinYN) {
		
		return purchaseMapper.selectPurchaseListForIssue(orderNoBase, orderNoExtra, roundRobinYN);

	}
	
	public List<Purchase_I_ab> getPurchaseListForIssue_request( String desc,
			String maker, String partnerId, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String kind, String orderNoBase, String orderNoExtra) {
		
		return purchaseMapper.selectPurchaseListForIssue_request(desc, maker, partnerId, convertedDesignDateBegin, convertedDesignDateEnd, kind, orderNoBase, orderNoExtra);

	}
	
	//발주 List history
	public List<GroupedJobPurchaseEntry_issueHistory> selectPurchaseList_History(Long job_purchase_id, String request_id){
		return purchaseMapper.selectPurchaseList_History(job_purchase_id, request_id);
	}
	
	
	//입고품 등록(조회)
	public List<Warehousing> getWarehousing(){
		return purchaseMapper.selectWarehousing();
	}
	
	//입고품 등록 팝업
	public List<Warehousing_pop> getWarehousing_pop(String BuyNum){
		return purchaseMapper.selectWarehousing_pop(BuyNum);
	}
	
	//입고품 입고 동록
	public void insertWarehousing(warehousing_list warehousing_list) {
		purchaseMapper.insertWarehousing(warehousing_list);
	}
	//입고품 stage 변경
	public void updateStage(String BuyNum) {
		purchaseMapper.updateStage(BuyNum);
	}
	
	//구매/견적 List 등록 시 JobpurchaseNew select
	public List<JobPurchaseNew> getJobpurchaseNew(SelectJobPurchaseNew SelectJobPurchaseNew){

		return purchaseMapper.selectJobpurchaseNew(SelectJobPurchaseNew);
	}
	
	//구매 List 견적 요청 Update
	public void UpdateJobPurchaseNew(UpdateJobPurchaseNew UpdateJobPurchaseNew) {
		purchaseMapper.UpdateJobPurchaseNew(UpdateJobPurchaseNew);
	}
	
	//
	public void InsertJobPurchaseNew_history(JobPurchaseNew_history JobPurchaseNew_history) {
		purchaseMapper.InsertJobPurchaseNew_history(JobPurchaseNew_history);
	}
	
	//발주 삭제시 입고완료확인
	public String IsStage_F(Long id) {
		return purchaseMapper.IsStage_F(id);
	}
	
	//발주 삭제 동작
	public void deleteIssueList(Long id, String deleteUserId, String deleteReason) {
		purchaseMapper.deleteIssueList(id, deleteUserId, deleteReason);
	}
	
	//수정위해 선택 한 견적LIST selct
	public List<JobPurchaseNew_history_null> selectChoiseEdit_Estimate(Choice_Estimate Choice_Estimate){
		return purchaseMapper.selectChoiseEdit_Estimate(Choice_Estimate);
	}
	
	public List<jobpurchaseEstimateHistory> EstimateHistory(Long job_purchase_id, String model_no){
		return purchaseMapper.EstimateHistory(job_purchase_id, model_no);
	}
	
	//견적 LIST deleted
	public void EstimateListDeleted(Long jobPurchaseId, String estimateRequestId, Long jobOrderId) {
		purchaseMapper.EstimateListDeleted(jobPurchaseId, estimateRequestId, jobOrderId);
	}
	
	//견적 LIST updated
	public void EstimateListUpdated(EstimateListUpdated EstimateListUpdated) {
		purchaseMapper.EstimateListUpdated(EstimateListUpdated);
	}
	
	//구매 history select
	public JobPurchaseHistory selectHistory(String requestId, Long jobPurchaseId) {
		return purchaseMapper.selectHistory(requestId, jobPurchaseId);
	}
	//구매 가상 history insert
	public void insertHistoryAB(JobPurchaseHistory JobPurchaseHistory) {
		purchaseMapper.insertHistoryAB(JobPurchaseHistory);
	}
	
	//구매 가상 history select
	public List<JobPurchaseHistory> selectJobPurchaseHistoryAB(){
		return 	purchaseMapper.selectJobPurchaseHistoryAB();
	}
	
	//구매 발주 history 실 DB insert
	public void insertJobPurchaseHistoryReal(JobPurchaseHistory JobPurchaseHistory) {
		purchaseMapper.insertJobPurchaseHistoryReal(JobPurchaseHistory);
	}
	
	
	//견적 실 DB insert
	public void insertEstimateReal(Purchase_E_ab Purchase_E_ab) {
		purchaseMapper.insertEstimateReal(Purchase_E_ab);
	}
	
	//견적 발주 실 DB insert
	public void insertIssueReal(Purchase_I_ab Purchase_I_ab) {
		purchaseMapper.insertIssueReal(Purchase_I_ab);
	}
	
	//구매 발주 구매원장 stage 변경
	public void UpdateJobPurchaseHistoryStage(Long id, String orderRequestId, String orderRequestUserId) {
		purchaseMapper.UpdateJobPurchaseHistoryStage(id, orderRequestId, orderRequestUserId);
	}
	
	//가상 history delete
	public void deleteABHistory() {
		purchaseMapper.deleteABHistory();
	}
	
	//재고 불출 대기 사용 수량 Update
	public void UpdateStockabQty(Long stockId, int stockUseQuantity) {
		purchaseMapper.UpdateStockabQty(stockId, stockUseQuantity);
	}
	
	//입고품 등록 List
	public List<InnerStockModel> innerStockList(String partnerId, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String roundRobinYN){
		return purchaseMapper.innerStockList(partnerId, convertedDesignDateBegin, convertedDesignDateEnd, roundRobinYN);
	}
	
	//입고처리
	public List<InnerStockModel> innerStockpop(String issueRequestId){
		return purchaseMapper.innerStockpop(issueRequestId);
	}
	
	public List<InnerStockModel> innerListStockpop(String issueRequestId, String stage){
		return purchaseMapper.innerListStockpop(issueRequestId, stage);
	}
	
	//입고품 반품 처리
	public List<InnerStockModel> outStockpop(Long jobPurchaseId, String issueRequestId, Long jobOrderId){
		return purchaseMapper.outStockpop(jobPurchaseId, issueRequestId, jobOrderId);
	}
	
	public JobPurchaseDB selectOneJobPurchase(Long jobPurchaseId) {
		return purchaseMapper.selectOneJobPurchase(jobPurchaseId);
	}
	
	public void updateJobPurchase(Long jobPurchaseId, int outQuantity, String outUser, String reason) {
		purchaseMapper.updateJobPurchase(jobPurchaseId, outQuantity, outUser, reason);
	}
	public void reBuyreg(JobPurchaseDB JobPurchaseDB){
		
		purchaseMapper.reBuyreg(JobPurchaseDB);
	}
	
	//구매원장 ID select
	public Long jobpurchaseIDselect(String requestId) {
		return purchaseMapper.jobpurchaseIDselect(requestId);
	}
	
	//입고 및 반품 처리 update
	public void innerAndOutUpdate(innerAndoutModel innerAndoutModel) {
		purchaseMapper.innerAndOutUpdate(innerAndoutModel);
	}
	
	public void innerAndOutUpdateO(innerAndoutModel innerAndoutModel) {
		purchaseMapper.innerAndOutUpdateO(innerAndoutModel);
	}
	
	public void innerUpdateList(Long jobPurchaseId, String receiverUsr, String passUsr) {
		purchaseMapper.innerUpdateList(jobPurchaseId, receiverUsr, passUsr);
	}
	
	public void innerAndOutUpdateplus(innerAndoutModel innerAndoutModel) {
		purchaseMapper.innerAndOutUpdateplus(innerAndoutModel);
	}
	
	//입고품 LIST select
	public List<InnerStockModel> innerstockListModel(  String partnerId, Date convertedDesignDateBegin, Date convertedDesignDateEnd, String stage){
		return purchaseMapper.innerstockListModel(partnerId, convertedDesignDateBegin, convertedDesignDateEnd, stage);
	}
	
	
	public DataTableResponse getPurchaseSelectPageList(PurchaseSelectHis form) {
		
		DataTableResponse res = new DataTableResponse();
		
		res.setData( purchaseMapper.purcahseSelectHisDetailList(form));
		res.setTotalRecords( purchaseMapper.purcahseSelectHisDetailListCount(form));
		res.setDraw(form.getDraw());
		
		return res;	
		
	}
	
	
	
	//입고품 재고등록 page
	public List<innerstockListModel> viewInsertstock(Long jobPurchaseId, String requestId){
		return purchaseMapper.viewInsertstock(jobPurchaseId, requestId);
	}
	
	//재고List null 확인
	public stock stockIsnull(String modelNo){
		return purchaseMapper.stockIsnull(modelNo);
	}
	
	//재고 수량 select
	public int selectStockQt(String modelNo) {
		return purchaseMapper.selectStockQt(modelNo);	
	}
	
	//재고 수량 select
	public int selectStockQtfromID(Long stockID) {
		return purchaseMapper.selectStockQtfromID(stockID);	
	}
	
	//신재고 insert
	public void insertStockList(stock stock) {
		purchaseMapper.insertStockList(stock);
	}
	
	
	//stock Id select
	public Long stockIdSelect(String modelNo) {
		return purchaseMapper.stockIdSelect(modelNo);
	}
	
	//stock in History insert
	public void StockInHistoryIns(stockInHistroy stockInHistroy) {
		purchaseMapper.StockInHistoryIns(stockInHistroy);
	}
	
	//stock Update
	public void UpdateStock(String modelNo, int quantity) {
		purchaseMapper.UpdateStock(modelNo, quantity);
	}
	
	public void UpdateStockfromID(Long stockID, int quantity) {
		purchaseMapper.UpdateStockfromID(stockID, quantity);
	}
	
	//purchase update Stage 'O'
	public void UpdateStagePurchaseO(Long jobPurchaseId) {
		purchaseMapper.UpdateStagePurchaseO(jobPurchaseId);
	}

	
	//재고 List
	public List<stockRealListModel> stockRealListSelect(String kind ,String CodeNO,String modelNo, String maker, String desc, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.stockRealListSelect(kind, CodeNO, modelNo, maker, desc, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	//재고 List id select
	public List<stockRealListModel> stockRealListSelectId(Long id) {
		return purchaseMapper.stockRealListSelectId(id);
	}
	
	//재고 불출
	@Transactional
	public Long addStockHistory(stockOutHistory stockOutHistory) {
		purchaseMapper.outStock(stockOutHistory);
		
		Long stockId = stockOutHistory.getId();
		
		log.debug("생성된  ID = "  + stockId);
		return stockId;
		
	}
	
	public List<stockRealOutModel> listoutpopdetail(Long id) {
		return purchaseMapper.listoutpopdetail(id);
	}
	//재고 List stock불출 update
	public void stockOutUpdate(Long id, int outQuantity) {
		purchaseMapper.stockOutUpdate(id, outQuantity);
	}
	
	public void stockOutUpdateAB(Long id, int outQuantity) {
		purchaseMapper.stockOutUpdateAB(id, outQuantity);
	}
	
	@Transactional
	public Long addStatement(StatementIns StatementIns){
	
		purchaseMapper.insertStatement(StatementIns);
		
		
		Long StatementId = StatementIns.getId();
		log.debug("생성된 작업 지시 ID = "  + StatementId);
		return StatementId;
	}
	
	public void insertStatementDetail(StatementDetail StatementDetail) {
		purchaseMapper.insertStatementDetail(StatementDetail);
	}
	
	
	//재고 불출 List
	public List<stockRealOutModel> selectStockOutRealList(String orderNoBase, String orderNoExtra,String CodeNO,String modelNo, String maker, String desc, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.selectStockOutRealList(orderNoBase, orderNoExtra, CodeNO, modelNo, maker, desc, convertedDesignDateBegin, convertedDesignDateEnd);
	}	
	
	//파트너 이름 출력
	public List<String> selectPartnerName(){
		return purchaseMapper.selectPartnerName();
	}
	
	public List<PurchasePartner> selectPartnerIdName(){
		return purchaseMapper.selectPartnerIdName();
	}
	
	//파트너 ID-->name
	public String IdforNamePartner(String id) {
		return purchaseMapper.IdforNamePartner(id);
	}
	
	//파트너 name --> ID
	public String NameforIdPartner(String name) {
		return purchaseMapper.NameforIdPartner(name);
	}
	
	
	//견적 가상 DB insert
	public void insertEstimateAB(Purchase_E_ab Purchase_E_ab) {
		purchaseMapper.insertEstimateAB(Purchase_E_ab);
	}
	
	//견적 발주 DB insert
	public void insertIssueAB(Purchase_I_ab Purchase_I_ab) {
		purchaseMapper.insertIssueAB(Purchase_I_ab);
	}

	
	

	// 메일 발송시 사용
	// 구매 발주/견적 요청한 특정 업체 작업 지시건을 검색
	public Collection<List<JobPurchase>> getEstimateTargets(String partnerId, Long[] jobOrderIds) {

		List<JobPurchase> all = purchaseMapper.selectEstimateRequestTargets(partnerId, jobOrderIds);
		Map<Long, List<JobPurchase>> distinguished = all.stream()
				.collect(Collectors.groupingBy(JobPurchase::getJobOrderId));
		return distinguished.values();
	}
	
	public Collection<List<MailSendIssue>> getIssueTargets(String partnerId, Long[] jobOrderIds) {

		List<MailSendIssue> all = purchaseMapper.selectIssueRequestTargets(partnerId, jobOrderIds);
		Map<Long, List<MailSendIssue>> distinguished = all.stream()
				.collect(Collectors.groupingBy(MailSendIssue::getJobOrderId));
		return distinguished.values();
	}
	
	//견적리스트 발송전 popup
	public Collection<List<PreEstimateRequest>> preEstimateRequestTargets(String partnerId,
		Long[] jobOrderIds){

		List<PreEstimateRequest> all = purchaseMapper.preEstimateRequestTargets(partnerId, jobOrderIds);
		Map<Long, List<PreEstimateRequest>> distinguished = all.stream()
				.collect(Collectors.groupingBy(PreEstimateRequest::getJobOrderId));
		return distinguished.values();
	}
	
	//신규구매리스트 발송전 popup
	public Collection<List<Purchase_I_ab>> preIssueRequestTargets(String partnerId,
		Long[] jobOrderIds){

		List<Purchase_I_ab> all = purchaseMapper.preIssueRequestTargets(partnerId, jobOrderIds);
		Map<Long, List<Purchase_I_ab>> distinguished = all.stream()
				.collect(Collectors.groupingBy(Purchase_I_ab::getJobOrderId));
		return distinguished.values();
	}
	
	
	public Collection<List<MailSendIssueNew>> getIssueTargetsNew(String partnerId, Long[] jobOrderIds) {

		List<MailSendIssueNew> all = purchaseMapper.selectIssueRequestTargetsNew(partnerId, jobOrderIds);
		Map<Long, List<MailSendIssueNew>> distinguished = all.stream()
				.collect(Collectors.groupingBy(MailSendIssueNew::getJobOrderId));
		return distinguished.values();
	}
	
	
	public Collection<List<Purchase_I_ab>> selectIssueMailText(String partnerId, Long[] jobOrderIds, String[] pks){
		
		List<Purchase_I_ab> all = purchaseMapper.selectIssueMailText(partnerId, jobOrderIds, pks);
		Map<Long, List<Purchase_I_ab>> distinguished = all.stream()
				.collect(Collectors.groupingBy(Purchase_I_ab::getJobOrderId));
		
		return distinguished.values();
	}
	
	//견적 가상 DB 삭제
	public void deletePuchaseEstimateAB() {
		purchaseMapper.deletePuchaseEstimateAB();
	}
	
	public String sspartnerId(String estimateRequestId) {
		return purchaseMapper.sspartnerId(estimateRequestId);
	}
	
	//발주 가상 DB 삭제
	public void deltePurchaseIssueAB() {
		purchaseMapper.deltePurchaseIssueAB();
	}
	
	
	//구매원장 stage E 변경
	public void UpdateStageE(Long id) {
		purchaseMapper.UpdateStageE(id);
	}
	
	//구매원장 stage P 변경
	public void UpdateStageP(Long id, String IssueRequestId, String orderRequestUserId, String modelNo, String partnerId, String maker) {
		purchaseMapper.UpdateStageP(id, IssueRequestId, orderRequestUserId, modelNo, partnerId, maker);
	}
	
	//견적 가상 DB select
	public List<Purchase_E_ab> selectEstimateAB(){
		return purchaseMapper.selectEstimateAB();
	}
	
	//견적 발주 가상 DB select
	public List<Purchase_I_ab> selectIssueAB(){
		return purchaseMapper.selectIssueAB();
	}
	
	//발주 취소
	public void issueCancle(Long jobOrderId, Long jobPurchaseId, String issueRequestId, String cancleReason) {
		purchaseMapper.issueCancle(jobOrderId, jobPurchaseId, issueRequestId, cancleReason);
	}
	
	
	public Collection<List<Purchase_E_ab>> getEstimateMailText(String partnerId, Long[] jobOrderIds, String[] pks){
		
		List<Purchase_E_ab> all = purchaseMapper.getEstimateMailText(partnerId, jobOrderIds, pks);
		Map<Long, List<Purchase_E_ab>> distinguished = all.stream()
				.collect(Collectors.groupingBy(Purchase_E_ab::getJobOrderId));
		
		return distinguished.values();
	}
	
	//stock select where modelNo
	public List<stock> selectstock(String modelNo) {
		return purchaseMapper.selectstock(modelNo);
	}
	

	// 구매 리스트 저장
	@Transactional
	public void addPurchaseList(Long jobOrderId, PurchaseLine[] data) throws Exception {

		Integer seqNo = 1;
		for (PurchaseLine line : data) {

			if (line.getPartnerId() == null)
				throw new Exception("partnerId is null");

			String seq = Strings.padStart(seqNo.toString(), 3, '0'); // seq 는
			// 그때그때 만든다
			System.out.println("******"+seq);
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			
			map.put("jobOrderId", jobOrderId);
			map.put("seq", seq);
			map.put("unitNo", line.getUnitNo());
			map.put("description", line.getDescription());
			map.put("modelNo", line.getModelNo());
			map.put("maker", line.getMaker());
			map.put("partnerId", line.getPartnerId());
			map.put("quantity", line.getQuantity());
			
			System.out.println("jobOrderId = " + jobOrderId);
			System.out.println("seq = " + seq);
			System.out.println("unitNo = " + line.getUnitNo());
			System.out.println("description = " + line.getDescription());
			System.out.println("modelNo = " + line.getModelNo());
			System.out.println("maker = " + line.getMaker());
			System.out.println("partnerId = " + line.getPartnerId());
			System.out.println("quantity = " + line.getQuantity());
			
			if(line.getSpareQuantity().isEmpty()) {
				map.put("spareQuantity", null);
			}else {
				map.put("spareQuantity", line.getSpareQuantity());
			}
			
			if(line.getCode().isEmpty()) {
				map.put("code", null);
			}else {
				map.put("code", line.getCode());
			}
			
			if(line.getComment().isEmpty()) {
				map.put("comment", null);
			}else {
				map.put("comment", line.getComment());
			}
			
			if(line.getRemark().isEmpty()) {
				map.put("remark", null);
			}else {
				map.put("remark", line.getRemark());
			}
			
			
			if(line.getDesignFileNo().isEmpty()) {
				map.put("designFileNo", null);
			}
			else {
				map.put("designFileNo", line.getDesignFileNo());
			}
			
			
			purchaseMapper.insertPurchaseList(map);
		}
	}

	// 특정 구매리스트 항목 불러오기
	public JobPurchase getPurchaseListEntry(Long jobPurchaseId) {
		return purchaseMapper.selectPurchaseEntry(jobPurchaseId, null, null);
	}

	// 구매 리스트 조회
	
	public List<JobPurchase> getPurchaseList(String partnerId, String maker, String desc, String modelNo,  String unitNo) {

		return purchaseMapper.selectPurchaseList(partnerId, maker, desc, modelNo, unitNo);

	}

	// 모델 검색
	public List<ModelInfo> searchModel(String keyword, String category) {
		if (StringUtils.isEmpty(keyword)) {
			category = null;
			keyword = null;
		}

		return purchaseMapper.selectModelList(category, keyword);
	}

	public List<JobPartner> searchPartner(String keyword) {
		return partnerMapper.selectPartnerList(StringUtils.isEmpty(keyword) ? null : keyword, null, null, null, null);
	}
	
	public List<JobPartner> searchPartner2(String partnerName, String Code) {
		return partnerMapper.selectPartnerList2(partnerName, Code);
	}
	
	public void insertPartner(JobPartner JobPartner) {
		partnerMapper.insertPartner(JobPartner);
	}
	
	public void EditPartner(JobPartner JobPartner) {
		partnerMapper.EditPartner(JobPartner);
	}
	public int autoPartnerCount(String PartnerType) {
		return partnerMapper.autoPartnerCount(PartnerType);
	}
	
	public int jobPartnerTypeCount(JobPartnerType JobPartnerType) {
		 return partnerMapper.jobPartnerTypeCount(JobPartnerType);
	 }
	public void jobPartnerTypeCount_ins(JobPartnerType JobPartnerType) {
		partnerMapper.jobPartnerTypeCount_ins(JobPartnerType);
	}
	public void jobPartnerTypeUP(JobPartnerType JobPartnerType) {
		partnerMapper.jobPartnerTypeUP(JobPartnerType);
	}
	public int jobPartnerTypeNextValue(JobPartnerType JobPartnerType) {
		return partnerMapper.jobPartnerTypeNextValue(JobPartnerType);
	}
	
	 
	public List<JobPartner> selectOnePartnerList(String id){
		return partnerMapper.selectOnePartnerList(id);
	}

	public void updatePurchaseListEntry(JobPurchase newData) {
		if (newData.getId() == null)
			throw new IllegalArgumentException("ID 가 null 입니다");
		
		if(newData.getSpare() == null)
			newData.setSpare(0);

		purchaseMapper.updatePurchaseEntry(newData);
	}

	public void deletePurchaseEntry(Long id, String userId, String reason) {
		purchaseMapper.updatePurchaseEntryDeleted(id, reason, userId);
	}

	public List<JobPurchase> getAssignedList(Long jobOrderId, String partnerId) throws Exception {

		if (jobOrderId == null || partnerId == null)
			throw new IllegalArgumentException("작업지시ID, 업체ID 는 NOT-NULL 입니다");

		return purchaseMapper.selectPurchaseEntries(null, partnerId, jobOrderId);
	}
	
	@Transactional
	public void addRequestHistory(String requestType, String partnerId, List<Long> jobOrderIdList){

		String requestId = utilityService.generateId("P", partnerId);
		log.info("사용될 견적 요청 ID = " + requestId);
		
		purchaseHistoryMapper.insertPurchaseHistory(requestId, requestType, partnerId, jobOrderIdList);
				
	}
	
	//JobPurchase partner_id select
	public String selectPartnerId(String customer) {
		return purchaseMapper.selectPartnerId(customer);
	}
	
	//업체 대분류 select
	public List<JobPartnerTypeNew> selectTypeCode(){
		return purchaseMapper.selectTypeCode();
	}
	
	//업체 소분류 select
	public List<JobPartnerTypeNew> selectTypeKind(String param){
		return purchaseMapper.selectTypeKind(param);
	}
	
	//몇개월 업체 기준
	public List<billingMonth> selectBillingAfter(){
		return purchaseMapper.selectBillingAfter();
	}
	
	//몇일 업체기준
	public List<billingDay> selectBillingDay(){
		return purchaseMapper.selectBillingDay();
	}
	
	//구매 거래 명세표 입고시 insert
	public void insertStatement(StatementIns StatementIns) {
		purchaseMapper.insertStatement(StatementIns);
	}
	
	//거래명세표등록 Page
	public List<StatementInsList> statementInsPage(String PartnerCode, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.statementInsPage(PartnerCode, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	//파트너 이름 --> 코드 검색
	public String surchPartnerCode(String partnerName) {
		return purchaseMapper.surchPartnerCode(partnerName);
	}
	
	//구매거래명세표등록pops
	public List<StatementInsPop> statementInspop(Long id){
		return purchaseMapper.statementInspop(id);
	}
	
	public void StatementofUpdate(Long issueId,String buyKind, int sumPrice, int negoPrice, Date issueDateR) {
		purchaseMapper.StatementofUpdate(issueId, buyKind, sumPrice, negoPrice, issueDateR);
	}
	
	public void StatementofDetailUpdate(Long id, String issuedItemName, int issuedQuantity, int issuedUnitPrice) {
		purchaseMapper.StatementofDetailUpdate(id, issuedItemName, issuedQuantity, issuedUnitPrice);
	}
	
	public String getRequestIdfromstatement(Long issueId) {
		return purchaseMapper.getRequestIdfromstatement(issueId);
	}
	
	
	public Long getJobOrderIdfromstatement(Long issueId) {
		return purchaseMapper.getJobOrderIdfromstatement(issueId);
	}
	
	public Long getPurchaseIdfromstatementDetail(Long id) {
		return purchaseMapper.getPurchaseIdfromstatementDetail(id);
	}
	
	public void upDatePurchaseStatementA(Long jobPurchaseId) {
		purchaseMapper.upDatePurchaseStatementA(jobPurchaseId);
	}
	public void upDatePurchaseIssueStatementY(String requestId ,Long jobPurchaseId, Long jobOrderId) {
		purchaseMapper.upDatePurchaseIssueStatementY(requestId, jobPurchaseId, jobOrderId);
	}
	
	//거래 명세표 updtae
	public void statementUpdate(StatementUpdate StatementUpdate) {
		purchaseMapper.statementUpdate(StatementUpdate);
	}
	
	//거래명세표리스트 Page
	public List<statementList> statementListPage(String PartnerCode, Date convertedDesignDateBegin, Date convertedDesignDateEnd,
			String orderNoBase, String orderNoExtra){
		return purchaseMapper.statementListPage(PartnerCode, convertedDesignDateBegin, convertedDesignDateEnd,
				orderNoBase,orderNoExtra);
	}
	
	//user 검색
	public List<String> userSurch(){
		return purchaseMapper.userSurch();
	}
	
	//user 부서 조건 검색
	public List<String> userSurchwhereDept(String receiveDept){
		return purchaseMapper.userSurchwhereDept(receiveDept);
	}
	
	public List<String> deptSurch(){
		return purchaseMapper.deptSurch();
	}
	
	public List<DeptData> deptSurch2(){
		return purchaseMapper.deptSurch2();
	}
	
	public String deptCode(String name) {
		return purchaseMapper.deptCode(name);
	}
	
	//user name ----> user code
	public String userCodeSurch(String name) {
		return purchaseMapper.userCodeSurch(name);
	}
	
	public void insertTransitionpurchase(TransitionPurchase TransitionPurchase) {
		purchaseMapper.insertTransitionpurchase(TransitionPurchase);
	}
	
	//user code ----> user name
	public String userNameSurch(String id) {
		return purchaseMapper.userNameSurch(id);
	}
	
	public StatementPartner selectDatePID(Long id) {
		return purchaseMapper.selectDatePID(id);
	}
	
	public StatementListPartner selectPartner(String id) {
		return purchaseMapper.selectPartner(id);
	}
	
	public List<StatementListPop> statementListPop(Long id){
		return purchaseMapper.statementListPop(id);
	}
	
	public Long statementListSum(Long id){
		return purchaseMapper.statementListSum(id);
	}
	
	public Long statementListNego(Long id){
		return purchaseMapper.statementListNego(id);
	}
	
	public Long selectOrderIdFrompurchase(Long id) {
		return purchaseMapper.selectOrderIdFrompurchase(id);
	}
	
	public Long selectPurchaseIdFromstock(Long id) {
		return purchaseMapper.selectPurchaseIdFromstock(id);
	}
	
	public String deptNameSurch(String id) {
		return purchaseMapper.deptNameSurch(id);
	}
	
	public List<StockListOrderNo> searchOrderNo(){
		return purchaseMapper.searchOrderNo();
	}
	
	//매입업체별 기준 매입업체 select
	public List<SerachPartnerState> serachPartnerState(Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.serachPartnerState(convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	//매입업체별 파트너 카운트
	public List<SearchCountPartnerState> serachCountPartnerState(Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.serachCountPartnerState(convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	//OrderNo별 기준 OrderNo select
	public List<SearchOrderState> serachOrderState(String orderNoBase, String orderNoExtra, Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.serachOrderState(orderNoBase, orderNoExtra, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	//OrderNo별 OrderNo 카운트
	public List<SearchCountOrderState> serachCountOrderState(String orderNoBase, String orderNoExtra ,Date convertedDesignDateBegin, Date convertedDesignDateEnd){
		return purchaseMapper.serachCountOrderState(orderNoBase, orderNoExtra, convertedDesignDateBegin, convertedDesignDateEnd);
	}
	
	//월별합계 전체  Partner 검색
	public List<SurchAllPartner> surchAllPartner(){
		return purchaseMapper.surchAllPartner();
	}
	
	//월별합계 전체  Order 검색
	public List<SurchAllOrder> surchAllOrder(){
		return purchaseMapper.surchAllOrder();
	}
	
	//월별합계 1월
	public List<SumPriceState> janSum(){
		return purchaseMapper.janSum();
	}
	
	public List<SumPriceState> febSum(){
		return purchaseMapper.febSum();
	}
	
	public List<SumPriceState> marSum(){
		return purchaseMapper.marSum();
	}
	
	public List<SumPriceState> aprSum(){
		return purchaseMapper.aprSum();
	}
	
	public List<SumPriceState> maySum(){
		return purchaseMapper.maySum();
	}
	
	public List<SumPriceState> junSum(){
		return purchaseMapper.junSum();
	}
	
	public List<SumPriceState> julSum(){
		return purchaseMapper.julSum();
	}
	
	public List<SumPriceState> augSum(){
		return purchaseMapper.augSum();
	}
	
	public List<SumPriceState> sepSum(){
		return purchaseMapper.sepSum();
	}
	
	public List<SumPriceState> octSum(){
		return purchaseMapper.octSum();
	}
	
	public List<SumPriceState> novSum(){
		return purchaseMapper.novSum();
	}
	
	public List<SumPriceState> decSum(){
		return purchaseMapper.decSum();
	}
	
	public List<SumPriceState> janSumOrder(){
		return purchaseMapper.janSumOrder();
	}
	
	public List<SumPriceState> febSumOrder(){
		return purchaseMapper.febSumOrder();
	}
	
	public List<SumPriceState> marSumOrder(){
		return purchaseMapper.marSumOrder();
	}
	
	public List<SumPriceState> aprSumOrder(){
		return purchaseMapper.aprSumOrder();
	}
	
	public List<SumPriceState> maySumOrder(){
		return purchaseMapper.maySumOrder();
	}
	
	public List<SumPriceState> junSumOrder(){
		return purchaseMapper.junSumOrder();
	}
	
	public List<SumPriceState> julSumOrder(){
		return purchaseMapper.julSumOrder();
	}
	
	public List<SumPriceState> augSumOrder(){
		return purchaseMapper.augSumOrder();
	}
	
	public List<SumPriceState> sepSumOrder(){
		return purchaseMapper.sepSumOrder();
	}
	
	public List<SumPriceState> octSumOrder(){
		return purchaseMapper.octSumOrder();
	}
	
	public List<SumPriceState> novSumOrder(){
		return purchaseMapper.novSumOrder();
	}
	
	public List<SumPriceState> decSumOrder(){
		return purchaseMapper.decSumOrder();
	}
	
	//월별 OrderNo 갯수
	public int janCountOrder() {
		return purchaseMapper.janCountOrder();
	}
	
	public int febCountOrder() {
		return purchaseMapper.febCountOrder();
	}
	
	public int marCountOrder() {
		return purchaseMapper.marCountOrder();
	}
	
	public int aprCountOrder() {
		return purchaseMapper.aprCountOrder();
	}
	
	public int mayCountOrder() {
		return purchaseMapper.mayCountOrder();
	}
	
	public int junCountOrder() {
		return purchaseMapper.junCountOrder();
	}
	
	public int julCountOrder() {
		return purchaseMapper.julCountOrder();
	}
	
	public int augCountOrder() {
		return purchaseMapper.augCountOrder();
	}
	
	public int sepCountOrder() {
		return purchaseMapper.sepCountOrder();
	}
	
	public int octCountOrder() {
		return purchaseMapper.octCountOrder();
	}
	
	public int novCountOrder() {
		return purchaseMapper.novCountOrder();
	}
	
	public int decCountOrder() {
		return purchaseMapper.decCountOrder();
	}
	
	//월별 Partner 갯수
	public int janCountPartner() {
		return purchaseMapper.janCountPartner();
	}
	
	public int febCountPartner() {
		return purchaseMapper.febCountPartner();
	}
	
	public int marCountPartner() {
		return purchaseMapper.marCountPartner();
	}
	
	public int aprCountPartner() {
		return purchaseMapper.aprCountPartner();
	}
	
	public int mayCountPartner() {
		return purchaseMapper.mayCountPartner();
	}
	
	public int junCountPartner() {
		return purchaseMapper.junCountPartner();
	}
	
	public int julCountPartner() {
		return purchaseMapper.julCountPartner();
	}
	
	public int augCountPartner() {
		return purchaseMapper.augCountPartner();
	}
	
	public int sepCountPartner() {
		return purchaseMapper.sepCountPartner();
	}
	
	public int octCountPartner() {
		return purchaseMapper.octCountPartner();
	}
	
	public int novCountPartner() {
		return purchaseMapper.novCountPartner();
	}
	
	public int decCountPartner() {
		return purchaseMapper.decCountPartner();
	}
	
	public void insertJobPurchasefromRobin(JobPurchaseDB JobPurchaseDB) {
		purchaseMapper.insertJobPurchasefromRobin(JobPurchaseDB);
	}
	
	public void deletedTrashData() {
		purchaseMapper.deletedTrashData();
		
	}
	
	public void deletedTrashData2() {
		purchaseMapper.deletedTrashData2();
		
	}
	
	//재고삭제
	public void deletedStockYN(Long id) {
		purchaseMapper.deletedStockYN(id);
	}
	
	//재고등록
	@Transactional
	public Long addInsStockfromPurchase(StockIns stockIns) {
		purchaseMapper.addInsStockfromPurchase(stockIns);
		
		Long stockID = stockIns.getId();
		
		return stockID;
	}
	
	public Long sumStockAllList() {
		return purchaseMapper.sumStockAllList();
	}
	
	public void setStockListUdate(StockIns stockIns) {
		purchaseMapper.setStockListUdate(stockIns);
	}
	
	public void stockHisInsAct(StockHIsIns stockHIsIns) {
		purchaseMapper.stockHisInsAct(stockHIsIns);
	}
	
	public void nonePurchaseUpdate(Long jobOrderId) {
		purchaseMapper.nonePurchaseUpdate(jobOrderId);
	}
	
	public void noneNPurchseUpdate(Long jobOrderId) {
		purchaseMapper.noneNPurchseUpdate(jobOrderId);
	}
	
}
