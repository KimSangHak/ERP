package com.yuhannci.erp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhannci.erp.mapper.QcViewDataMapper;
import com.yuhannci.erp.mapper.TransitionMapper;
import com.yuhannci.erp.model.QcViewData;
import com.yuhannci.erp.model.db.TransitionProcessing;
import com.yuhannci.erp.model.QcFinishListData;

@Service
public class QcViewDataService {

	@Autowired QcViewDataMapper qcViewDataMapper;
	@Autowired TransitionMapper TransitionMapper;
	
	public List<QcViewData> getQcViewList(){
		
		return qcViewDataMapper.selectList();
	}
	
	public List<QcFinishListData> getQcFinishList(Integer start, Integer length, String orderNoBase, 
			String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo){
		
		return qcViewDataMapper.selectFinishList(start, length, orderNoBase, orderNoExtra, drawingNo, 
									mctDateFrom, mctDateTo);
	}

	@Transactional
	public void makeQcPlay(Long id){
		QcFinishListData item = qcViewDataMapper.selectCheck(id);
		if(item == null)
			throw new RuntimeException("지정된 설계도 정보[" + id + "] 를 찾을 수 없습니다");
		 
		if(item.getWorkStatus().equalsIgnoreCase("I"))
			throw new RuntimeException("이미 진행중인 건입니다");
		
		qcViewDataMapper.qcPlayStart(id);
	}
	
	@Transactional
	public void makeQcHold(Long id, String atype, String reason){
		QcFinishListData item = qcViewDataMapper.selectCheck(id);
		if(item == null)
			throw new RuntimeException("지정된 설계도 정보[" + id + "] 를 찾을 수 없습니다");
		 
		if(item.getWorkStatus().equalsIgnoreCase(atype))
			throw new RuntimeException("이미 설정 중입니다.");
		
		qcViewDataMapper.qcHoldSet(id, atype, reason);
	}
	
	@Transactional
	public void makeQcComplete(Long id, String resultok, int okcnt, int failcnt){
		QcFinishListData item = qcViewDataMapper.selectCheck(id);
		if(item == null)
			throw new RuntimeException("지정된 설계도 정보[" + id + "] 를 찾을 수 없습니다");
		 
		qcViewDataMapper.qcCompleteSet(id, resultok, okcnt, failcnt);
	}
	
	@Transactional
	public void makeQcDelivery(Long id, String fromid, String toid){
		QcFinishListData item = qcViewDataMapper.selectCheck(id);
		if(item == null)
			throw new RuntimeException("지정된 설계도 정보[" + id + "] 를 찾을 수 없습니다");
		 
		qcViewDataMapper.qcDeliverySet(id, fromid, toid);
	}
	
	@Transactional
	public void makeQcDrawingStatusSet(Long dwid, String atype){

		qcViewDataMapper.qcDrawingStatusSet(dwid, atype);
	}
	
	@Transactional
	public void makeQcCompleteDrawingSet(Long dwid, int okcnt, int failcnt){

		qcViewDataMapper.qcCompleteDrawingSet(dwid, okcnt, failcnt);
	}
	
	@Transactional
	public void makeQcDeliveryDrawingSet(Long dwid){

		qcViewDataMapper.qcDeliveryDrawingSet(dwid);
	}
	
	public void makesetTransitionps(Long did, String fromid, String toid) {
		
		
		TransitionProcessing TransitionProcessing = new TransitionProcessing();
		TransitionProcessing.setPreId(0L);
		TransitionProcessing.setJobOrderId(qcViewDataMapper.qcDeliveryGetOrderId(did));
		TransitionProcessing.setJobDesignDrawingId(did);
		TransitionProcessing.setPassUsr(fromid);
		TransitionProcessing.setReceiverUsr(toid);
		TransitionProcessing.setReceiveDept("MM");
		TransitionProcessing.setQuantity(qcViewDataMapper.qcDeliveryGetQtywithSpare(did));
		TransitionProcessing.setComplet("Y");
		
		TransitionMapper.insPTfromQcComplet(TransitionProcessing);
		
	}
}
