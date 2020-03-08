package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.PsBillSearchForm;
import com.yuhannci.erp.model.db.StatementAccountDetailEntry;
import com.yuhannci.erp.model.db.StatementAccountEntry;
import com.yuhannci.erp.model.db.StatementEntry;
import com.yuhannci.erp.model.db.StatementRegEntry;

@Mapper
public interface ProcessBillMapper {
	
	// 거래 명세표 등록 대상 조회
	Integer selectUnpublishedStatementCount(PsBillSearchForm form);
	List<StatementEntry> selectUnpublishedStatement( PsBillSearchForm form);
	
	List<StatementRegEntry> selectRegPrepareList(@Param("id") String id, @Param("partnerId") String partnerId, @Param("requestType") String requestType, @Param("orderId") Long orderId);
	
	// 가공용 거래명세표 원장 등록
	void insertStatementMaster(StatementAccountEntry entry);
	// 거래명세표 상세 내역 등록
	void insertStatementDetail(com.yuhannci.erp.model.db.StatementAccountDetailEntry entry);
	// 외주 가공건에 대해 거래명세표가 발행됐음을 표기
	void markStatementIssued(@Param("uid") Long job_partner_outsourcing_order_uid, @Param("issueDetailId") Long issueDetailId);
	
	// 거래명세표 원장의 합계 금액을 업데이트한다
	void updateStatementMasterSumPrice(@Param("issueId") Long issueId);
	
	List<Long> lookupJobOrderId(@Param("id") String id, @Param("requestType") String requestType, @Param("partnerId") String partnerId);
	
	// 거래 명세표 조회
	Integer selectPublishedStatementCount(PsBillSearchForm form);
	List<com.yuhannci.erp.model.db.StatementListEntry> selectPublishedStatement(PsBillSearchForm form);
	
	StatementAccountEntry selectSingleStatement(@Param("statementId") Long statementId);
	List<StatementAccountDetailEntry> selectStatementDetail(@Param("statementId") Long statementId);
	
	public Long orderIdFromstatementProcess(String no);
	
	public Long orderNoFNfromstatement(String orderNo);
}
