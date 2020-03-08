package com.yuhannci.erp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.ProcessBillMapper;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.PsBillSearchForm;
import com.yuhannci.erp.model.db.StatementAccountDetailEntry;
import com.yuhannci.erp.model.db.StatementAccountEntry;
import com.yuhannci.erp.model.db.StatementRegEntry;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatementAccountService {

	@Autowired ProcessBillMapper processBillMapper;
	
	public DataTableResponse getUnpublishedStatementList(PsBillSearchForm form) {
		
		DataTableResponse res = new DataTableResponse();
		res.setData( processBillMapper.selectUnpublishedStatement(form ));
		res.setTotalRecords( processBillMapper.selectUnpublishedStatementCount(form));
		res.setDraw(form.getDraw());
		return res;		
	}
	
	public DataTableResponse getPublishedStatementList(PsBillSearchForm form) {
		log.info("form is " + form);
		
		DataTableResponse res = new DataTableResponse();
		res.setData( processBillMapper.selectPublishedStatement(form ));
		res.setTotalRecords( processBillMapper.selectPublishedStatementCount(form));
		res.setDraw(form.getDraw());
		return res;
	}
	
	// 거래명세표 등록할 대상 도면 목록 
	public DataTableResponse getUnregisteredDrawingEntry(Integer draw, String id, String partnerId, String requestType, Long orderId) {
		
		List<StatementRegEntry> data = processBillMapper.selectRegPrepareList(id, partnerId, requestType, orderId);
		
		DataTableResponse res = new DataTableResponse();		
		res.setData( data );
		res.setTotalRecords( data.size() );
		res.setDraw(draw);
		
		return res;
	}
	
	// 거래명세표 등록
	@Transactional
	public void registerStatement(String partnerId, String requestType, String outsourcingOrderId, Long negoPrice, Date issueDate, List<StatementRegEntry> targetEntries) {
		
		List<Long> jobOrderIds = processBillMapper.lookupJobOrderId(outsourcingOrderId, requestType, partnerId);
		
		for(Long jobOrderId : jobOrderIds) {
		
			if(jobOrderId == null)
				throw new RuntimeException("작업 지시ID 를 찾을 수 없습니다.");
		
			// 거래명세서 원장 등록
			StatementAccountEntry master = new StatementAccountEntry();
			master.setIssueDate(issueDate);
			master.setPartnerId(partnerId);
			master.setRequestId( outsourcingOrderId );
			master.setJobOrderId(jobOrderId);
			master.setSumPrice(0L);	// 맨 마지막에 일괄 업데이트
			master.setNegoPrice(negoPrice);
			processBillMapper.insertStatementMaster(master);
			if(master.getId() == null)
				throw new RuntimeException("내부 오류 :: 발행ID 를 확인할 수 없습니다");
		
			final Long issueId = master.getId();
		
			log.info("이번에 사용할 발행ID = " + issueId);
			for(StatementRegEntry entry : targetEntries) {
				System.out.println("!!!!DrawingNo = "+entry.getDesignDrawingNo());
				// 거래명세서 상세 등록
				if((processBillMapper.orderIdFromstatementProcess(entry.getDesignDrawingNo()) == jobOrderId)) {
				
					StatementAccountDetailEntry detail = new StatementAccountDetailEntry();
					detail.setIssueId(issueId);
					detail.setJobOutsourcingOrderUid(entry.getUid());			
					detail.setIssuedItemName(entry.getItemName());
					detail.setIssuedQuantity(entry.getTotalQuantity());
					detail.setIssuedUnitPrice(entry.getUnitPrice());
					processBillMapper.insertStatementDetail(detail);
			
					// 외주 가공 내역에 거래명세상세정보연결
					processBillMapper.markStatementIssued(entry.getUid(), detail.getId());
				}
			}
		
		
		// 실제 명세서 금액 업데이트
			processBillMapper.updateStatementMasterSumPrice(issueId);
		}
	}
	
	public StatementAccountEntry getStatementInformation(Long statementId) {
		
		return processBillMapper.selectSingleStatement(statementId);
	}
	
	public DataTableResponse loadStatementInformation(Integer draw, Long statementId) {

		DataTableResponse res = new DataTableResponse();
		res.setData( processBillMapper.selectStatementDetail(statementId) );
		res.setDraw(draw);
		
		return res;
	}
	
	public Long orderNoFNfromstatement(String orderNo) {
		return processBillMapper.orderNoFNfromstatement(orderNo);
	}
}
