package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.db.TransitionPurchase;
import com.yuhannci.erp.model.TransitionAdmin;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.TransitionProcessing;




@Mapper
public interface TransitionMapper {
	
	public String serchuserDeptCode(String id);
	
	public List<TransitionPurchase> serachdata(String deptCode);
	
	public List<TransitionPurchase> serachdataList(String deptCode);
	
	public List<TransitionPurchase> serachdataPass(String usrId);
	
	public List<TransitionPurchase> serachdata3(String deptCode);
	
	public List<TransitionPurchase> serachdataList3(String deptCode);
	
	public List<TransitionPurchase> serachdataPass3(String usrId);
	
	public List<TransitionProcessing> serachdata2(String deptCode);
	
	public List<TransitionProcessing> serachdataList2(String deptCode);
	
	public List<TransitionProcessing> serachdataPass2(String usrId);
	
	public List<TransitionAdmin> serachdataAdmin();
	
	public List<TransitionAdmin> serachdataAdminList();
	
	public List<TransitionAdmin> serachdataAdmin2();
	
	public List<TransitionAdmin> serachdataAdminList2();

	//구매품
	public List<TransitionPurchase> popserachdataPurchase(@Param("jobOrderId") Long jobOrderId, @Param("receiveDept") String receiveDept);
	
	public List<TransitionPurchase> popserachdataPurchaseList(@Param("jobOrderId") Long jobOrderId, @Param("receiveDept") String receiveDept);
	
	//구매품 pass
	public List<TransitionPurchase> popserachdataPurchasePass(@Param("jobOrderId") Long jobOrderId, @Param("passUsr") String passUsr);
	
	
	//재고품
	public List<TransitionPurchase> popserachdataStock(@Param("jobOrderId") Long jobOrderId, @Param("receiveDept") String receiveDept);
	
	public List<TransitionPurchase> popserachdataStockList(@Param("jobOrderId") Long jobOrderId, @Param("receiveDept") String receiveDept);
	
	//재고품 Pass
	public List<TransitionPurchase> popserachdataStockPass(@Param("jobOrderId") Long jobOrderId, @Param("passUsr") String passUsr);
	
	
	//가공품
	public List<TransitionProcessing> popserachdataMm(@Param("jobOrderId") Long jobOrderId, @Param("receiveDept") String receiveDept);
	
	public List<TransitionProcessing> popserachdataMmList(@Param("jobOrderId") Long jobOrderId, @Param("receiveDept") String receiveDept);
	
	//가공품 Pass
	public List<TransitionProcessing> popserachdataPartListPass(@Param("jobOrderId") Long jobOrderId, @Param("passUsr") String passUsr);
	
	
	public List<TransitionPurchase> popserachdataPurchaseAdmin(String receiveDept);
	
	public List<TransitionPurchase> popserachdataPurchaseAdminList(String receiveDept);
	
	public List<TransitionPurchase> popserachdataStockAdmin(String receiveDept);
	
	public List<TransitionPurchase> popserachdataStockAdminList(String receiveDept);
	
	public void updateTration(@Param("id") Long id, @Param("receiverUsr") String receiverUsr);
	
	public void updateTrationProccesing(@Param("id") Long id, @Param("receiverUsr") String receiverUsr);
	
	public void updateJobPurchase(@Param("jobPurchaseId") Long jobPurchaseId, @Param("receiverUsr") String receiverUsr);
	
	public void updateStockOut(@Param("stockHistoryId") Long stockHistoryId, @Param("receiverUsr") String receiverUsr);
	
	public void updateJobPurchaseAdmin(Long jobPurchaseId);
	
	public void deleteTrantion(Long id);
	
	public void deleteTrantionPart(Long id);
	
	public int selectOutqty(Long stockHistoryId);
	
	public int selectStockqty(Long stockId);
	
	public void updateStock(@Param("stockId") Long stockId, @Param("outQty") int outQty);
	
	public void updateOutStock(@Param("stockId") Long stockId, @Param("outQty") int outQty);
	
	public String abstockCheck(Long id);
	
	public void stockHistoryDelete(Long stockHistoryId);
	
	public String deleteCheck(Long id);
	
	public String deleteCheckPart(Long id);
	
	public List<TransitionPurchase> rePassPurcahse(Long id);
	
	public List<TransitionPurchase> rePassPurcahseStock(Long id);
	
	public List<TransitionProcessing> rePassPartList(Long id);
	
	public void rePassPurchaseDo(@Param("rquantity") int rquantity, @Param("id") Long id, @Param("kind") String kind, @Param("outReason") String outReason);
	
	public void rePassPurchaseDoPart(@Param("rquantity") int rquantity, @Param("id") Long id, @Param("kind") String kind, @Param("outReason") String outReason);
	
	public void rePassETCDo(@Param("rquantity") int rquantity, @Param("id") Long id, @Param("kind") String kind);
	
	public void rePassETCDoPart(@Param("rquantity") int rquantity, @Param("id") Long id, @Param("kind") String kind);
	
	public TransitionPurchase idSerachforPurchaseTratison(Long id);
	
	public TransitionProcessing idSerachforPurchaseTratisonPart(Long id);
	
	public List<DeptData> selectDeptAllData();
	
	public Long searchPreIdMapper(Long id);
	
	public Long searchPreIdMapperPart(Long id);
	
	public void updatePassReturn(Long id);
	
	public void updatePassReturnPart(Long id);
	
	public void updatePassReturnEL(@Param("id") Long id, @Param("qty") int qty);
	
	public void updatePassReturnELPart(@Param("id") Long id, @Param("qty") int qty);
	
	public int rqtySearch(Long id);
	
	public int rqtySearchPart(Long id);
	
	public int qtySearch(Long id);
	
	public int qtySearchPart(Long id);
	
	public String kindRepass(Long id);
	
	public Long purchaseIdfromTransition(Long id);
	
	public String getModel(Long id);
	
	public String getDes(Long id);
	
	public String getMak(Long id);
	
	public int getQtyfromTrasiton(Long id);
	
	public int getQtyfromTrasitonProccesing(Long id);
	
	public String getReasonfromTransition(Long id);
	
	public String getReasonfromTransitionProccesing(Long id);
	
	public String getPassUfromTransition(Long id);
	
	public void insertProccessingTrantion(TransitionProcessing TransitionProcessing);
	
	public void insPTfromQcComplet(TransitionProcessing TransitionProcessing);
	
	public String getDesForDrawing(Long id);
	
	public String getDemForDrawing(Long id);
	
	public String getPassUsrFromTrasitionProccesing(Long id);
	
	public JobDesignDrawing getJobDesign(Long id);
	
	public void setDesignPassStayY(Long id);
	public void setDesignPassStayN(Long id);
}
