package com.yuhannci.erp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import com.yuhannci.erp.mapper.TransitionMapper;
import com.yuhannci.erp.model.TransitionAdmin;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.TransitionProcessing;
import com.yuhannci.erp.model.db.TransitionPurchase;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TransitionService {
	
	@Autowired
	TransitionMapper TransitionMapper;
	
	public String serchuserDeptCode(String id) {
		return TransitionMapper.serchuserDeptCode(id);
	}
	
	public List<TransitionPurchase> serachdata(String deptCode){
		return TransitionMapper.serachdata(deptCode);
	}
	
	public List<TransitionPurchase> serachdataList(String deptCode){
		return TransitionMapper.serachdataList(deptCode);
	}
	
	//Pass1
	public List<TransitionPurchase> serachdataPass(String usrId){
		return TransitionMapper.serachdataPass(usrId);
	}
	
	public List<TransitionPurchase> serachdata3(String deptCode){
		return TransitionMapper.serachdata3(deptCode);
	}
	
	public List<TransitionPurchase> serachdataList3(String deptCode){
		return TransitionMapper.serachdataList3(deptCode);
	}
	
	//Pass3
	public List<TransitionPurchase> serachdataPass3(String usrId){
		return TransitionMapper.serachdataPass3(usrId);
	}
	
	public List<TransitionProcessing> serachdata2(String deptCode){
		return TransitionMapper.serachdata2(deptCode);
	}
	
	public List<TransitionProcessing> serachdataList2(String deptCode){
		return TransitionMapper.serachdataList2(deptCode);
	}
	
	//Pass2
	public List<TransitionProcessing> serachdataPass2(String usrId){
		return TransitionMapper.serachdataPass2(usrId);
	}
	
	
	public List<TransitionAdmin> serachdataAdmin(){
		return TransitionMapper.serachdataAdmin();
	}
	
	public List<TransitionAdmin> serachdataAdminList(){
		return TransitionMapper.serachdataAdminList();
	}
	
	public List<TransitionAdmin> serachdataAdmin2(){
		return TransitionMapper.serachdataAdmin2();
	}
	
	public List<TransitionAdmin> serachdataAdminList2(){
		return TransitionMapper.serachdataAdminList2();
	}
	
	//구매품
	public List<TransitionPurchase> popserachdataPurchase(Long jobOrderId, String receiveDept){
		return TransitionMapper.popserachdataPurchase(jobOrderId, receiveDept);
	}
	public List<TransitionPurchase> popserachdataPurchaseList(Long jobOrderId, String receiveDept){
		return TransitionMapper.popserachdataPurchaseList(jobOrderId, receiveDept);
	}
	
	//pass 구매품
	public List<TransitionPurchase> popserachdataPurchasePass(Long jobOrderId, String passUsr){
		return TransitionMapper.popserachdataPurchasePass(jobOrderId, passUsr);
	}
	
	
	
	//재고품
	public List<TransitionPurchase> popserachdataStock(Long jobOrderId, String receiveDept){
		return TransitionMapper.popserachdataStock(jobOrderId, receiveDept);
	}
	
	public List<TransitionPurchase> popserachdataStockList(Long jobOrderId, String receiveDept){
		return TransitionMapper.popserachdataStockList(jobOrderId, receiveDept);
	}
	
	//Pass 재고품
	public List<TransitionPurchase> popserachdataStockPass(Long jobOrderId, String passUsr){
		return TransitionMapper.popserachdataStockPass(jobOrderId, passUsr);
	}
	
	//가공품
	public List<TransitionProcessing> popserachdataMm(Long jobOrderId, String receiveDept){
		return TransitionMapper.popserachdataMm(jobOrderId, receiveDept);
	}
	
	public List<TransitionProcessing> popserachdataMmList(Long jobOrderId, String receiveDept){
		return TransitionMapper.popserachdataMmList(jobOrderId, receiveDept);
	}
	
	//Pass 가공품
	public List<TransitionProcessing> popserachdataPartListPass(Long jobOrderId, String passUsr){
		return TransitionMapper.popserachdataPartListPass(jobOrderId, passUsr);
	}
	
	
	
	public List<TransitionPurchase> popserachdataPurchaseAdmin(String receiveDept){
		return TransitionMapper.popserachdataPurchaseAdmin(receiveDept);
	}
	
	public List<TransitionPurchase> popserachdataPurchaseAdminList(String receiveDept){
		return TransitionMapper.popserachdataPurchaseAdminList(receiveDept);
	}
	
	public List<TransitionPurchase> popserachdataStockAdmin(String receiveDept){
		return TransitionMapper.popserachdataStockAdmin(receiveDept);
	}
	
	public List<TransitionPurchase> popserachdataStockAdminList(String receiveDept){
		return TransitionMapper.popserachdataStockAdminList(receiveDept);
	}
	
	public void updateTration(Long id, String receiverUsr) {
		TransitionMapper.updateTration(id, receiverUsr);
	}
	
	public void updateTrationProccesing(Long id, String receiverUsr) {
		TransitionMapper.updateTrationProccesing(id, receiverUsr);
	}
	
	public void updateJobPurchase(Long jobPurchaseId, String receiverUsr) {
		TransitionMapper.updateJobPurchase(jobPurchaseId, receiverUsr);
	}
	
	public void updateStockOut(Long stockHistoryId, String receiverUsr) {
		TransitionMapper.updateStockOut(stockHistoryId, receiverUsr);
	}
	
	public void updateJobPurchaseAdmin(Long jobPurchaseId) {
		
		TransitionMapper.updateJobPurchaseAdmin(jobPurchaseId);
		
	}
	
	public void deleteTrantion(Long id) {
		TransitionMapper.deleteTrantion(id);
	}
	
	public void deleteTrantionPart(Long id) {
		TransitionMapper.deleteTrantionPart(id);
	}
	
	public int selectOutqty(Long stockHistoryId) {
		return TransitionMapper.selectOutqty(stockHistoryId);
	}
	
	public int selectStockqty(Long stockId) {
		return TransitionMapper.selectStockqty(stockId);
	}
	
	public void updateStock(Long stockId, int outQty) {
		TransitionMapper.updateStock(stockId, outQty);
	}
	
	public void updateOutStock(Long stockId, int outQty) {
		TransitionMapper.updateOutStock(stockId, outQty);
	}
	
	public String abstockCheck(Long id) {
		return TransitionMapper.abstockCheck(id);
	}
	
	public void stockHistoryDelete(Long stockHistoryId) {
		TransitionMapper.stockHistoryDelete(stockHistoryId);
	}
	
	public String deleteCheck(Long id) {
		return TransitionMapper.deleteCheck(id);
	}
	
	public String deleteCheckPart(Long id) {
		return TransitionMapper.deleteCheckPart(id);
	}
	
	public List<TransitionPurchase> rePassPurcahse(Long id){
		return TransitionMapper.rePassPurcahse(id);
	}
	
	public List<TransitionPurchase> rePassPurcahseStock(Long id){
		return TransitionMapper.rePassPurcahseStock(id);
	}
	
	public List<TransitionProcessing> rePassPartList(Long id){
		return TransitionMapper.rePassPartList(id);
	}
	
	public void rePassPurchaseDo(int rquantity, Long id, String kind, String outReason){
		TransitionMapper.rePassPurchaseDo(rquantity, id, kind, outReason);
	}
	
	public void rePassPurchaseDoPart(int rquantity, Long id, String kind, String outReason){
		TransitionMapper.rePassPurchaseDoPart(rquantity, id, kind, outReason);
	}
	
	public void rePassETCDo(int rquantity, Long id, String kind){
		TransitionMapper.rePassETCDo(rquantity, id, kind);
	}
	
	public void rePassETCDoPart(int rquantity, Long id, String kind){
		TransitionMapper.rePassETCDoPart(rquantity, id, kind);
	}
	
	public TransitionPurchase idSerachforPurchaseTratison(Long id) {
		return TransitionMapper.idSerachforPurchaseTratison(id);
	}
	
	public TransitionProcessing idSerachforPurchaseTratisonPart(Long id) {
		return TransitionMapper.idSerachforPurchaseTratisonPart(id);
	}
	
	public List<DeptData> selectDeptAllData(){
		return TransitionMapper.selectDeptAllData();
	}
	
	public boolean searchPreId(Long id) {
		
		return (TransitionMapper.searchPreIdMapper(id) != 0 ? true:false);
	}
	
	public boolean searchPreIdPart(Long id) {
		
		return (TransitionMapper.searchPreIdMapperPart(id) != 0 ? true:false);
	}
	
	public Long getPreId(Long id) {
		return TransitionMapper.searchPreIdMapper(id);
	}
	
	public Long getPreIdPart(Long id) {
		return TransitionMapper.searchPreIdMapperPart(id);
	}
	
	public void updatePassReturn(Long id) {
		TransitionMapper.updatePassReturn(id);
	}
	
	public void updatePassReturnPart(Long id) {
		TransitionMapper.updatePassReturnPart(id);
	}
	
	public void updatePassReturnEL(Long id, int qty) {
		TransitionMapper.updatePassReturnEL(id, qty);
	}
	
	public void updatePassReturnELPart(Long id, int qty) {
		TransitionMapper.updatePassReturnELPart(id, qty);
	}
	
	public int rqtySearch(Long id) {
		return TransitionMapper.rqtySearch(id);
	}
	
	public int rqtySearchPart(Long id) {
		return TransitionMapper.rqtySearchPart(id);
	}
	
	public int qtySearch(Long id){
		return TransitionMapper.qtySearch(id);
	}
	
	public int qtySearchPart(Long id){
		return TransitionMapper.qtySearchPart(id);
	}
	
	public String kindRepass(Long id) {
		return TransitionMapper.kindRepass(id);
	}
	
	public Long purchaseIdfromTransition(Long id) {
		return TransitionMapper.purchaseIdfromTransition(id);
	}
	
	public String getModel(Long id) {
		return TransitionMapper.getModel(id);
	}
	
	public String getDesForDrawing(Long id) {
		return TransitionMapper.getDesForDrawing(id);
	}
	
	public String getDemForDrawing(Long id) {
		return TransitionMapper.getDemForDrawing(id);
	}
	
	public String getDes(Long id) {
		return TransitionMapper.getDes(id);
	}
	
	public String getMak(Long id) {
		return TransitionMapper.getMak(id);
	}
	
	public String getPassUsrFromTrasitionProccesing(Long id) {
		return TransitionMapper.getPassUsrFromTrasitionProccesing(id);
	}
	
	public int getQtyfromTrasiton(Long id) {
		return TransitionMapper.getQtyfromTrasiton(id);
	}
	
	public int getQtyfromTrasitonProccesing(Long id) {
		return TransitionMapper.getQtyfromTrasitonProccesing(id);
	}
	
	public String getReasonfromTransition(Long id) {
		return TransitionMapper.getReasonfromTransition(id);
	}
	
	public String getReasonfromTransitionProccesing(Long id) {
		return TransitionMapper.getReasonfromTransitionProccesing(id);
	}
	
	public String getPassUfromTransition(Long id) {
		return TransitionMapper.getPassUfromTransition(id);
				
	}
	
	public JobDesignDrawing getJobDesign(Long id) {
		return TransitionMapper.getJobDesign(id);
	}
	
	public void setUpdatePassStayQc(Long id) {
		TransitionMapper.setDesignPassStayN(id);
	}
	



}
