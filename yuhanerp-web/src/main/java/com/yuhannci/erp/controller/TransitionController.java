package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.mapper.CarryInOutMapper;
import com.yuhannci.erp.mapper.InspectMapper;
import com.yuhannci.erp.mapper.TransitionMapper;
import com.yuhannci.erp.model.TransitionAdmin;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.JobInspect;
import com.yuhannci.erp.model.db.JobPurchaseDB;
import com.yuhannci.erp.model.db.JobDesignDrawing;
import com.yuhannci.erp.model.db.TransitionProcessing;
import com.yuhannci.erp.model.db.TransitionPurchase;
import com.yuhannci.erp.model.db.stock;
import com.yuhannci.erp.model.db.stockInHistroy;
import com.yuhannci.erp.service.DrawingService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.TransitionService;
import com.yuhannci.erp.service.PurchaseService;

import ch.qos.logback.core.net.SyslogOutputStream;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/transition")
public class TransitionController {
	
	@Autowired TransitionService TransitionService;
	@Autowired TransitionMapper TransitionMapper;
	@Autowired CarryInOutMapper CarryInOutMapper;
	@Autowired LoginDongleService loginDongleService;
	@Autowired PurchaseService PurchaseService;
	@Autowired InspectMapper inspectMapper;
	@Autowired DrawingService drawingService;
	
	//인수인계 현황 Main Page
	@RequestMapping("/main")
	public ModelAndView transition(){
		
		String usrId = loginDongleService.getLoginId();
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
	
			
		List<TransitionPurchase> data = TransitionService.serachdata(deptCode);
		List<TransitionProcessing> data2 = TransitionService.serachdata2(deptCode);
		List<TransitionPurchase> data3 = TransitionService.serachdata3(deptCode);
			
		ModelAndView mv = new ModelAndView("transition/transitionPage");
		mv.addObject("data", data);
		mv.addObject("data2", data2);
		mv.addObject("data3", data3);
			
		return mv;
		
		
		
	}
	
	//인계 대기 LIST Page
	@RequestMapping("/passStay")
	public ModelAndView transitionPassStay(){
		
			String usrId = loginDongleService.getLoginId();
			
		

			
			List<TransitionPurchase> data = TransitionService.serachdataPass(usrId);
			List<TransitionProcessing> data2 = TransitionService.serachdataPass2(usrId);
			List<TransitionPurchase> data3 = TransitionService.serachdataPass3(usrId);
			
			ModelAndView mv = new ModelAndView("transition/transitionPagePass");
			mv.addObject("data", data);
			mv.addObject("data2", data2);
			mv.addObject("data3", data3);
			
			return mv;

		
		
	}
	
	//인계대기 현황 취소 팝업(구매품)
	@RequestMapping("/popPurchasePass/{jobOrderId}/{passUsr}")
	public ModelAndView transitionpop_purchasePass(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("passUsr") String passUsr)throws Exception {
			

		
		List<TransitionPurchase> data = TransitionService.popserachdataPurchasePass(jobOrderId, passUsr);

		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopPass");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		

		return mv;
									
	}
	
	//인계대기 현황 취소 팝업(재고품)
	@RequestMapping("/popStockPass/{jobOrderId}/{passUsr}")
	public ModelAndView transitionpop_stockPass(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("passUsr") String passUsr)throws Exception {
			

		
		List<TransitionPurchase> data = TransitionService.popserachdataStockPass(jobOrderId, passUsr);

		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("transition/popup/transitionStockPopPass");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		

		return mv;
									
	}
	
	//인계대기 현황 취소 팝업(가공품)
	@RequestMapping("/popPartListPass/{jobOrderId}/{passUsr}")
	public ModelAndView transitionpop_partPass(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("passUsr") String passUsr)throws Exception {
			

		
		List<TransitionProcessing> data = TransitionService.popserachdataPartListPass(jobOrderId, passUsr);

		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopPassPart");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		

		return mv;
									
	}
	
	//구매품 인계 취소 동작
	@RequestMapping("/purchaseDeleteAct")
	public String purchaseDeleteAct(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
							   @RequestParam("id")Long[] id) {
		
		
		
		
		for(int i=0;i<id.length;i++) {
			
			
			if(TransitionService.searchPreId(id[i])) {
				
				String complet = TransitionService.deleteCheck(id[i]);
				
				if(complet.equals("N")) {
					
					
					Long preId = TransitionService.getPreId(id[i]);
					
					int rqty = TransitionService.rqtySearch(preId);
					
					int qty = TransitionService.qtySearch(id[i]);
					
					
					if(rqty == qty) {
					
						TransitionService.deleteTrantion(id[i]);
					
						TransitionService.updatePassReturn(preId);
					}
					else {
						
						int param = rqty - qty ;
						
						TransitionService.deleteTrantion(id[i]);
						
						TransitionService.updatePassReturnEL(preId, param);
						
						
					}

					
				}
				
			}
			
			
			else {
				
				String complet = TransitionService.deleteCheck(id[i]);
			
				if(complet.equals("N")) {

					TransitionService.updateJobPurchaseAdmin(jobPurchaseId[i]);
					TransitionService.deleteTrantion(id[i]);
				}
			}
			
		}
					
			
		return "redirect:/transition/passStay";	
	}
	
	
	//재고품 인계 취소 동작
	@RequestMapping("/stockDeleteAct")
	public String stockDeleteAct(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
								 @RequestParam("id")Long[] id,
								 @RequestParam("stockId")Long[] stockId,
								 @RequestParam("stockHistoryId")Long[] stockHistoryId) {
		
		
		for(int i=0;i<id.length;i++) {
			
			
			if(TransitionService.searchPreId(id[i])) {
				
				String complet = TransitionService.deleteCheck(id[i]);
				
				if(complet.equals("N")) {
					
					
					Long preId = TransitionService.getPreId(id[i]);
					
					int rqty = TransitionService.rqtySearch(preId);
					
					int qty = TransitionService.qtySearch(id[i]);
					
					
					if(rqty == qty) {
					
						TransitionService.deleteTrantion(id[i]);
					
						TransitionService.updatePassReturn(preId);
					}
					else {
						
						int param = rqty - qty ;
						
						TransitionService.deleteTrantion(id[i]);
						
						TransitionService.updatePassReturnEL(preId, param);
						
						
					}

					
				}
				
				
				
				
			}
			
			
			else {
			
				String complet = TransitionService.deleteCheck(id[i]);
			
				if(complet.equals("N")) {
				
					int outQty = TransitionService.selectOutqty(stockHistoryId[i]);

			
					String abYn = TransitionService.abstockCheck(id[i]);
			
					if(abYn.equals("Y")) {
				
						TransitionService.updateStock(stockId[i], outQty);
						TransitionService.stockHistoryDelete(stockHistoryId[i]);
						TransitionService.deleteTrantion(id[i]);
				
					}
			
					else {
				
						TransitionService.updateOutStock(stockId[i], outQty);
						TransitionService.stockHistoryDelete(stockHistoryId[i]);
						TransitionService.deleteTrantion(id[i]);
					}
				}
			}

			
		}
					
			
		return "redirect:/transition/passStay";	
	}
	
	//가공품 인계 취소 동작
	@RequestMapping("/partListDeletedAct")
	public String partListDeleteAct(@RequestParam("jobDesignDrawingId")Long[] jobDesignDrawingId,
							   @RequestParam("id")Long[] id) {
		
		
		
		
		for(int i=0;i<id.length;i++) {
			
			
			if(TransitionService.searchPreIdPart(id[i])) {
				
				String complet = TransitionService.deleteCheckPart(id[i]);
				
				if(complet.equals("N")) {
					
					
					Long preId = TransitionService.getPreIdPart(id[i]);
				
					int rqty = TransitionService.rqtySearchPart(preId);
					
					int qty = TransitionService.qtySearchPart(id[i]);
					
					
					if(rqty == qty) {
					
						TransitionService.deleteTrantionPart(id[i]);
					
						TransitionService.updatePassReturnPart(preId);
					}
					else {
						
						int param = rqty - qty ;
						
						TransitionService.deleteTrantionPart(id[i]);
						
						TransitionService.updatePassReturnELPart(preId, param);
						
						
					}

					
				}
				
			}
			
			
			else {
				
				String complet = TransitionService.deleteCheckPart(id[i]);
			
				if(complet.equals("N")) {

					TransitionService.deleteTrantionPart(id[i]);
					TransitionMapper.setDesignPassStayN(jobDesignDrawingId[i]);
				}
			}
			
		}
					
			
		return "redirect:/transition/passStay";	
	}
	
	
	//인수인계 현황 구매품 인수확인 팝업
	@RequestMapping("/popPurchase/{jobOrderId}/{receiveDept}")
	public ModelAndView transitionpop_purchase(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("receiveDept") String receiveDept)throws Exception {
		
			String usrId = loginDongleService.getLoginId();
			
			
			String deptCode = TransitionService.serchuserDeptCode(usrId);
			
			
			if(deptCode.equals("PQ")) {
				
				List<TransitionPurchase> data = TransitionService.popserachdataPurchase(jobOrderId, receiveDept);
				List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
				int pageNo = 0;
		
				ModelAndView mv = new ModelAndView("transition/popup/receivePApop");
				mv.addObject("data", data);
				mv.addObject("userName", userName);
				mv.addObject("pageNo", pageNo);
				
				return mv;
				
				
			}
			else {
				List<TransitionPurchase> data = TransitionService.popserachdataPurchase(jobOrderId, receiveDept);
				List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
				int pageNo = 0;
		
				ModelAndView mv = new ModelAndView("transition/popup/transitionPop");
				mv.addObject("data", data);
				mv.addObject("userName", userName);
				mv.addObject("pageNo", pageNo);
		
		
		

				return mv;
			
			}
	
									
	}
	
	//인수인계 구매품 Admin 팝업
	@RequestMapping("/popPurchaseAdmin/{receiveDept}")
	public ModelAndView transitionpop_purchaseAdmin(@PathVariable("receiveDept") String receiveDept)throws Exception {
			

		
		List<TransitionPurchase> data = TransitionService.popserachdataPurchaseAdmin(receiveDept);

		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopAdmin");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		

		return mv;
									
	}
	
	
	//인수인계 현황 재고품 인수확인 팝업
	@RequestMapping("/popStock/{jobOrderId}/{receiveDept}")
	public ModelAndView transitionpop_stock(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("receiveDept") String receiveDept)throws Exception {
		
		
		String usrId = loginDongleService.getLoginId();
		
		
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
		
		if(deptCode.equals("PQ")) {
			
			List<TransitionPurchase> data = TransitionService.popserachdataStock(jobOrderId, receiveDept);
			List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
			int pageNo = 0;
	
			ModelAndView mv = new ModelAndView("transition/popup/receivePSpop");
			mv.addObject("data", data);
			mv.addObject("userName", userName);
			mv.addObject("pageNo", pageNo);
			
			return mv;
			
			
		}
		else {
		
			List<TransitionPurchase> data = TransitionService.popserachdataStock(jobOrderId, receiveDept);
			List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
			int pageNo = 0;
		
			ModelAndView mv = new ModelAndView("transition/popup/transitionStockPop");
			mv.addObject("data", data);
			mv.addObject("userName", userName);
			mv.addObject("pageNo", pageNo);
		

			return mv;
		}
									
	}
	
	//인수인계 재고품 Admin 팝업
	@RequestMapping("/popStockAdmin/{receiveDept}")
	public ModelAndView transitionpop_stockAdmin(@PathVariable("receiveDept") String receiveDept)throws Exception {
			

		
		List<TransitionPurchase> data = TransitionService.popserachdataStockAdmin(receiveDept);

		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("transition/popup/transitionStockPopAdmin");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);
		

		return mv;
									
	}
	
	
	//가공품 인수 확인 팝업
	@RequestMapping("/popMm/{jobOrderId}/{receiveDept}")
	public ModelAndView transitionpop_mm(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("receiveDept") String receiveDept)throws Exception {
		
		
		String usrId = loginDongleService.getLoginId();
		
		
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
		
		if(deptCode.equals("PQ")) {
			
			List<TransitionProcessing> data = TransitionService.popserachdataMm(jobOrderId, receiveDept);
			List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
			int pageNo = 0;
	
			ModelAndView mv = new ModelAndView("transition/popup/receivePSpopM");
			mv.addObject("data", data);
			mv.addObject("userName", userName);
			mv.addObject("pageNo", pageNo);
			
			return mv;
			
			
		}
		else if(deptCode.equals("MM")) {
			
			List<TransitionProcessing> data = TransitionService.popserachdataMm(jobOrderId, receiveDept);
			List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
			int pageNo = 0;
			
			ModelAndView mv = new ModelAndView("transition/popup/receiveMMpopM");
			mv.addObject("data", data);
			mv.addObject("userName", userName);
			mv.addObject("pageNo", pageNo);
		
			return mv;
			
		}
	
			
		else {
		
			List<TransitionProcessing> data = TransitionService.popserachdataMm(jobOrderId, receiveDept);
			List<String> userName = PurchaseService.userSurchwhereDept(receiveDept);
			int pageNo = 0;
		
			ModelAndView mv = new ModelAndView("transition/popup/transitionMmPop");
			mv.addObject("data", data);
			mv.addObject("userName", userName);
			mv.addObject("pageNo", pageNo);
		

			return mv;
		}
									
	}
	
	//인수인계 구매품 인수 동작
	@RequestMapping("/purchaseReciveAct")
	public String purchaseReciveAct(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
							   @RequestParam("id")Long[] id,
							   @RequestParam("selectUserName")String selectUserName,
							   @RequestParam(value="selectReceive", defaultValue="")String[] selectReceive) {
		
		String usrId = loginDongleService.getLoginId();
		
		
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
		if(deptCode.equals("PQ")) {
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
			
			for(int i=0;i<id.length;i++) {
			
				
				
				
				
				if(selectReceive[i].equals("A")) {
					
					Long pId = TransitionService.purchaseIdfromTransition(id[i]);
					
					String modelNo = TransitionService.getModel(pId);
					
					stock stockIsnull = null;
					stockIsnull = PurchaseService.stockIsnull(modelNo);
					
					if(stockIsnull == null) {
						
						stock stock = new stock();
						stock.setJobPurchaseId(pId);
						stock.setModelNo(modelNo);
						stock.setMaker(TransitionService.getMak(pId));
						stock.setDescription(TransitionService.getDes(pId));
						stock.setQuantity(TransitionService.getQtyfromTrasiton(id[i]));
						stock.setAbQuantity(0);
						stock.setOutQuantity(0);
						stock.setDeleted("N");
						
						PurchaseService.insertStockList(stock);
					
						stockInHistroy stockInHistroy = new stockInHistroy();
						stockInHistroy.setStockID(PurchaseService.stockIdSelect(stock.getModelNo()));
						stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
						stockInHistroy.setRegistrationReason(TransitionService.getReasonfromTransition(id[i]));
						stockInHistroy.setInQuantity(stock.getQuantity());
						PurchaseService.StockInHistoryIns(stockInHistroy);
					
					
					
					}else {
					
						stockInHistroy stockInHistroy = new stockInHistroy();
						stockInHistroy.setStockID(PurchaseService.stockIdSelect(modelNo));
						stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
						stockInHistroy.setRegistrationReason(TransitionService.getReasonfromTransition(id[i]));
						stockInHistroy.setInQuantity(TransitionService.getQtyfromTrasiton(id[i]));
						PurchaseService.StockInHistoryIns(stockInHistroy);
					
						int remindQt = PurchaseService.selectStockQt(modelNo);
						int quantity = remindQt + stockInHistroy.getInQuantity();
						PurchaseService.UpdateStock(modelNo, quantity);
					
					
					}

					TransitionService.updateTration(id[i], receiverUsr);
					TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);
		
					
				}
				else if(selectReceive[i].equals("P")) {
					
					Long pId = TransitionService.purchaseIdfromTransition(id[i]);
					
					JobPurchaseDB JobPurchaseDB = new JobPurchaseDB();
					
					JobPurchaseDB = PurchaseService.selectOneJobPurchase(pId);
					JobPurchaseDB.setQuantity(TransitionService.getQtyfromTrasiton(id[i]));
					JobPurchaseDB.setSpare(0);
					
				
					
					PurchaseService.updateJobPurchase(pId, TransitionService.getQtyfromTrasiton(id[i]), TransitionService.getPassUfromTransition(id[i]), TransitionService.getReasonfromTransition(id[i]));
					
					System.out.println("#############업데이트 jobPurchase 완료!!!");
					
					PurchaseService.reBuyreg(JobPurchaseDB);
					
					
					TransitionService.updateTration(id[i], receiverUsr);
					TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);
					
				}
				else {
				
						TransitionService.updateTration(id[i], receiverUsr);
						TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);	
					
				}
			
			}
			
			return "redirect:/transition/main";	
			
		}
		
		else if(deptCode.equals("MM")) {
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
			
			for(int i=0;i<id.length;i++) {
			
				TransitionService.updateTration(id[i], receiverUsr);
				TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);
			
			}
			
			
			return "redirect:/transition/main";	
			
		}
			
		else {
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
		
			for(int i=0;i<id.length;i++) {
			
				TransitionService.updateTration(id[i], receiverUsr);
				TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);
			
			}
					
			
			return "redirect:/transition/main";
		}
	}
	
	
	//인수인계 재고품(일반부서) 인수 동작
	@RequestMapping("/stockReciveAct")
	public String stockReciveAct(@RequestParam("jobPurchaseId")Long[] jobPurchaseId,
							   @RequestParam("id")Long[] id,
							   @RequestParam("selectUserName")String selectUserName,
							   @RequestParam("stockId")Long[] stockId,
							   @RequestParam("stockHistoryId")Long[] stockHistoryId,
							   @RequestParam(value="selectReceive", defaultValue="")String[] selectReceive) {
		
		String usrId = loginDongleService.getLoginId();
		
		
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
		if(deptCode.equals("PQ")) {
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);

			
			for(int i=0;i<id.length;i++) {
			
				
				
				
				
				if(selectReceive[i].equals("A")) {
					
					Long pId = TransitionService.purchaseIdfromTransition(id[i]);
					
					String modelNo = TransitionService.getModel(pId);
					
					stock stockIsnull = null;
					stockIsnull = PurchaseService.stockIsnull(modelNo);
					
					if(stockIsnull == null) {
						
						stock stock = new stock();
						stock.setJobPurchaseId(pId);
						stock.setModelNo(modelNo);
						stock.setMaker(TransitionService.getMak(pId));
						stock.setDescription(TransitionService.getDes(pId));
						stock.setQuantity(TransitionService.getQtyfromTrasiton(id[i]));
						stock.setAbQuantity(0);
						stock.setOutQuantity(0);
						stock.setDeleted("N");
						
						PurchaseService.insertStockList(stock);
					
						stockInHistroy stockInHistroy = new stockInHistroy();
						stockInHistroy.setStockID(PurchaseService.stockIdSelect(stock.getModelNo()));
						stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
						stockInHistroy.setRegistrationReason(TransitionService.getReasonfromTransition(id[i]));
						stockInHistroy.setInQuantity(stock.getQuantity());
						PurchaseService.StockInHistoryIns(stockInHistroy);
					
					
					
					}else {
					
						stockInHistroy stockInHistroy = new stockInHistroy();
						stockInHistroy.setStockID(PurchaseService.stockIdSelect(modelNo));
						stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
						stockInHistroy.setRegistrationReason(TransitionService.getReasonfromTransition(id[i]));
						stockInHistroy.setInQuantity(TransitionService.getQtyfromTrasiton(id[i]));
						PurchaseService.StockInHistoryIns(stockInHistroy);
					
						int remindQt = PurchaseService.selectStockQt(modelNo);
						int quantity = remindQt + stockInHistroy.getInQuantity();
						PurchaseService.UpdateStock(modelNo, quantity);
					
					
					}

					TransitionService.updateTration(id[i], receiverUsr);
					TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);
		
					
				}
				else if(selectReceive[i].equals("P")) {
					
					Long pId = TransitionService.purchaseIdfromTransition(id[i]);
					
					JobPurchaseDB JobPurchaseDB = new JobPurchaseDB();
					
					JobPurchaseDB = PurchaseService.selectOneJobPurchase(pId);
					JobPurchaseDB.setQuantity(TransitionService.getQtyfromTrasiton(id[i]));
					JobPurchaseDB.setSpare(0);
					
				
					
					PurchaseService.updateJobPurchase(pId, TransitionService.getQtyfromTrasiton(id[i]), TransitionService.getPassUfromTransition(id[i]), TransitionService.getReasonfromTransition(id[i]));
					
					System.out.println("#############업데이트 jobPurchase 완료!!!");
					
					PurchaseService.reBuyreg(JobPurchaseDB);
					
					
					TransitionService.updateTration(id[i], receiverUsr);
					TransitionService.updateJobPurchase(jobPurchaseId[i], receiverUsr);
					
				}
				
				else {
					
					TransitionService.updateTration(id[i], receiverUsr);
					TransitionService.updateStockOut(stockHistoryId[i], receiverUsr);
					
					
				}
			
			}
			
			return "redirect:/transition/main";	
			
		
			
			
			
		}
		
		else if(deptCode.equals("MM")) {
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
			
			
			for(int i=0;i<id.length;i++) {
			
				TransitionService.updateTration(id[i], receiverUsr);
				TransitionService.updateStockOut(stockHistoryId[i], receiverUsr);
			
			}
					
			
			return "redirect:/transition/main";	
			
		}
		
		
		else {
		
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
		
		
			for(int i=0;i<id.length;i++) {
			
				TransitionService.updateTration(id[i], receiverUsr);
				TransitionService.updateStockOut(stockHistoryId[i], receiverUsr);
			
			}
					
			
			return "redirect:/transition/main";
		}
	}
	
	//인수인계 가공품 인수 동작
	@RequestMapping("/mmReciveAct")
	public String mmReciveAct(@RequestParam("jobDesignDrawingId")Long[] jobDesignDrawingId,
							   @RequestParam("id")Long[] id,
							   @RequestParam("selectUserName")String selectUserName,
							   @RequestParam(value="selectReceive", defaultValue="")String[] selectReceive) {
		
		String usrId = loginDongleService.getLoginId();
		
		
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
		final Date now = new Date();
		
		if(deptCode.equals("PQ")) {
			
			
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
			
			for(int i=0;i<id.length;i++) {
			
				
				
				
				
				if(selectReceive[i].equals("A")) {
					
					
					String modelNo = TransitionService.getDesForDrawing(jobDesignDrawingId[i]);
					
					stock stockIsnull = null;
					stockIsnull = PurchaseService.stockIsnull(modelNo);
					
					if(stockIsnull == null) {
						
						stock stock = new stock();
						stock.setModelNo(modelNo);
						stock.setMaker("가공품");
						stock.setDescription(TransitionService.getDemForDrawing(jobDesignDrawingId[i]));
						stock.setQuantity(TransitionService.getQtyfromTrasitonProccesing(id[i]));
						stock.setAbQuantity(0);
						stock.setOutQuantity(0);
						stock.setDeleted("N");
						
						PurchaseService.insertStockList(stock);
					
						stockInHistroy stockInHistroy = new stockInHistroy();
						stockInHistroy.setStockID(PurchaseService.stockIdSelect(stock.getModelNo()));
						stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
						stockInHistroy.setRegistrationReason(TransitionService.getReasonfromTransitionProccesing(id[i]));
						stockInHistroy.setInQuantity(stock.getQuantity());
						PurchaseService.StockInHistoryIns(stockInHistroy);
					
					
					
					}else {
					
						stockInHistroy stockInHistroy = new stockInHistroy();
						stockInHistroy.setStockID(PurchaseService.stockIdSelect(modelNo));
						stockInHistroy.setRegistrationUser(loginDongleService.getLoginId());
						stockInHistroy.setRegistrationReason(TransitionService.getReasonfromTransitionProccesing(id[i]));
						stockInHistroy.setInQuantity(TransitionService.getQtyfromTrasitonProccesing(id[i]));
						PurchaseService.StockInHistoryIns(stockInHistroy);
					
						int remindQt = PurchaseService.selectStockQt(modelNo);
						int quantity = remindQt + stockInHistroy.getInQuantity();
						PurchaseService.UpdateStock(modelNo, quantity);
					
					
					}

					TransitionService.updateTrationProccesing(id[i], receiverUsr);
		
					
				}
				
				
				else if(selectReceive[i].equals("B")) {

						TransitionService.updateTrationProccesing(id[i], receiverUsr);
	
				}
				
				else {
					
						
					
						JobInspect data = new JobInspect();
						data.setJobDrawingId(jobDesignDrawingId[i]);
						data.setGetDate(now);
						data.setGetFromId(TransitionService.getPassUsrFromTrasitionProccesing(id[i]));
						data.setGetToId(receiverUsr);
						inspectMapper.registerOrder(data);
						
						if(data.getId() == null)
							throw new RuntimeException("검사 원장 생성 중 오류가 발생하였습니다");
				
						TransitionService.updateTrationProccesing(id[i], receiverUsr);
						
						CarryInOutMapper.carryToCheckDept(jobDesignDrawingId[i], data.getId());
						TransitionService.setUpdatePassStayQc(jobDesignDrawingId[i]);
						
					
				}
			
			}
			
			return "redirect:/transition/main";	
			
		}
		
		else if(deptCode.equals("MM")) {
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
			
			for(int i=0;i<id.length;i++) {
				
				
				
				if(selectReceive[i].equals("P")) {
					
					JobDesignDrawing pre = TransitionService.getJobDesign(jobDesignDrawingId[i]);
					
					pre.setWorkStatus("R");
					pre.setQuantity(TransitionService.getQtyfromTrasitonProccesing(id[i]));
					pre.setSpare(0);
					pre.setClassification("재가공");
					pre.setReason(TransitionService.getReasonfromTransitionProccesing(id[i]));
					pre.setNote(" ");
					
					drawingService.registerDarwingsOne(pre);
					
					
					
				}
				
				
				
				TransitionService.updateTrationProccesing(id[i], receiverUsr);
			
			}
			
			
			return "redirect:/transition/main";	
			
		}
		
		else if(deptCode.equals("AD")) {
			
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
			
			for(int i=0;i<id.length;i++) {
			
				TransitionService.updateTrationProccesing(id[i], receiverUsr);
				
				CarryInOutMapper.carryToAssemblyDept(jobDesignDrawingId[i], TransitionService.getPassUsrFromTrasitionProccesing(id[i]), receiverUsr, now);
			
			}
			
			return "redirect:/transition/main";
			
			
			
		}
			
		else {
			String receiverUsr = PurchaseService.userCodeSurch(selectUserName);
		
			for(int i=0;i<id.length;i++) {
			
				TransitionService.updateTrationProccesing(id[i], receiverUsr);
			
			}
					
			
			return "redirect:/transition/main";
		}
	}
	
	
	
	
	//인수 LIST 재인계
	@RequestMapping("/list")
	public ModelAndView transitionList(){
		
		String usrId = loginDongleService.getLoginId();
		String deptCode = TransitionService.serchuserDeptCode(usrId);
		
		

		
			List<TransitionPurchase> data = TransitionService.serachdataList(deptCode);
			List<TransitionProcessing> data2 = TransitionService.serachdataList2(deptCode);
			List<TransitionPurchase> data3 = TransitionService.serachdataList3(deptCode);
			
			
			ModelAndView mv = new ModelAndView("transition/transitionList");
			
			mv.addObject("data", data);
			mv.addObject("data2", data2);
			mv.addObject("data3", data3);
			
		
			
			return mv;

		
		
	}
	
	//구매품 인수LIST 상세 팝업
	@RequestMapping("/popPurchaseList/{jobOrderId}/{receiveDept}")
	public ModelAndView transitionpop_purchaseList(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("receiveDept") String receiveDept)throws Exception {

		List<TransitionPurchase> data = TransitionService.popserachdataPurchaseList(jobOrderId, receiveDept);
		
		
		Integer pageNo=0; 
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopList");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);

		return mv;
									
	}
	
	//가공품 인수 LIST 상세 팝업
	@RequestMapping("/popPartList/{jobOrderId}/{receiveDept}")
	public ModelAndView transitionpop_popPartList(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("receiveDept") String receiveDept)throws Exception {

		List<TransitionProcessing> data = TransitionService.popserachdataMmList(jobOrderId, receiveDept);
		
		
		Integer pageNo=0; 
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopPartList");
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);

		return mv;
									
	}
	
	//타부서 재인계 팝업(구매/재고품)
	@RequestMapping("/eReIns/{id}/{kindPurchase}")
	public ModelAndView transitionpop_eReIns(@PathVariable("id") Long id, @PathVariable("kindPurchase") String kindPurchase)throws Exception {

		
		if(kindPurchase.equals("P")) {
			
			List<TransitionPurchase> data = TransitionService.rePassPurcahse(id);
			List<DeptData> dept = TransitionService.selectDeptAllData();
			ModelAndView mv = new ModelAndView("transition/popup/transitionReInsPop");
			mv.addObject("data", data);
			mv.addObject("dept", dept);
			return mv;
		
		}
		else {
			List<TransitionPurchase> data = TransitionService.rePassPurcahseStock(id);
			List<DeptData> dept = TransitionService.selectDeptAllData();
			ModelAndView mv = new ModelAndView("transition/popup/transitionReInsPop");
			mv.addObject("data", data);
			mv.addObject("dept", dept);
			return mv;
			
		}
		
									
	}
	
	//타부서 재인계 팝업(가공품)
	@RequestMapping("/eReInsPart/{id}")
	public ModelAndView transitionpop_eReInsPart(@PathVariable("id") Long id)throws Exception {

		
		
		List<TransitionProcessing> data = TransitionService.rePassPartList(id);
		List<DeptData> dept = TransitionService.selectDeptAllData();
		ModelAndView mv = new ModelAndView("transition/popup/transitionReInsPopPart");
		mv.addObject("data", data);
		mv.addObject("dept", dept);
		return mv;
		
	
									
	}
	
	//타부서 재인계 동작(구매/재고품)
	@RequestMapping("/eReInsPart/do")
	public String pReInsDoPart(@RequestParam("rquantity")int rquantity,
						   @RequestParam("id")Long id,
						   @RequestParam("deptcode")String deptcode
						   )throws Exception {

		
		TransitionService.rePassETCDo(rquantity, id, "A");
		
		
		
		TransitionPurchase TransitionPurchasePre = TransitionService.idSerachforPurchaseTratison(id);
		
		TransitionPurchase TransitionPurchase = new TransitionPurchase();
		
		
		TransitionPurchase.setPreId(TransitionPurchasePre.getId());
		TransitionPurchase.setJobOrderId(TransitionPurchasePre.getJobOrderId());
		TransitionPurchase.setJobPurchaseId(TransitionPurchasePre.getJobPurchaseId());
		TransitionPurchase.setKindPurchase(TransitionPurchasePre.getKindPurchase());
		TransitionPurchase.setQuantity(rquantity);
		TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
		TransitionPurchase.setReceiveDept(deptcode);
		TransitionPurchase.setStockId(TransitionPurchasePre.getStockId());
		TransitionPurchase.setStockHistoryId(TransitionPurchasePre.getStockHistoryId());
		TransitionPurchase.setKindRepass("A");
		
		
		PurchaseService.insertTransitionpurchase(TransitionPurchase);
	
		
		

		return "redirect:/transition/list";
									
	}
	
	//타부서 재인계 동작(가공품)
	@RequestMapping("/eReIns/do")
	public String pReInsDo(@RequestParam("rquantity")int rquantity,
						   @RequestParam("id")Long id,
						   @RequestParam("deptcode")String deptcode
						   )throws Exception {

		
		TransitionService.rePassETCDoPart(rquantity, id, "A");
		
		
		
		TransitionProcessing TransitionProcessingPre = TransitionService.idSerachforPurchaseTratisonPart(id);
		
		TransitionProcessing TransitionProcessing = new TransitionProcessing();
		
		
		TransitionProcessing.setPreId(TransitionProcessingPre.getId());
		TransitionProcessing.setJobOrderId(TransitionProcessingPre.getJobOrderId());
		TransitionProcessing.setJobDesignDrawingId(TransitionProcessingPre.getJobDesignDrawingId());
		TransitionProcessing.setQuantity(rquantity);
		TransitionProcessing.setPassUsr(loginDongleService.getLoginId());
		TransitionProcessing.setReceiveDept(deptcode);
		TransitionProcessing.setKindRepass("A");
		
		TransitionMapper.insertProccessingTrantion(TransitionProcessing);
		
	
		
		

		return "redirect:/transition/list";
									
	}
	
	
	//구매부서 재인계 팝업(구매/재고품)
	@RequestMapping("/pReIns/{id}/{kindPurchase}")
	public ModelAndView transitionpop_pReIns(@PathVariable("id") Long id, @PathVariable("kindPurchase") String kindPurchase)throws Exception {

		
		
		if(kindPurchase.equals("P")) {
		
			List<TransitionPurchase> data = TransitionService.rePassPurcahse(id);
			ModelAndView mv = new ModelAndView("transition/popup/transitionRePurchaseIns");
			mv.addObject("data", data);
			return mv;
		
		}
		else {
			List<TransitionPurchase> data = TransitionService.rePassPurcahseStock(id);
			ModelAndView mv = new ModelAndView("transition/popup/transitionRePurchaseIns");
			mv.addObject("data", data);
			return mv;
			
		}
		

		
									
	}
	
	//구매부서 재인계 팝업(가공품)
	@RequestMapping("/pReInsPart/{id}")
	public ModelAndView transitionpop_pReInsPart(@PathVariable("id") Long id)throws Exception {

		
		
		List<TransitionProcessing> data = TransitionService.rePassPartList(id);
		ModelAndView mv = new ModelAndView("transition/popup/transitionRePurchaseInsPart");
		mv.addObject("data", data);
		return mv;
		

									
	}
	//구매부 재인계 동작(구매/재고품)
	@RequestMapping("/pReIns/do")
	public String pReInsDo(@RequestParam("rquantity")int rquantity,
						   @RequestParam("id")Long id,
						   @RequestParam("kind")String kind,
						   @RequestParam("outReason")String outReason,
						   @RequestParam("kindPurchase")String kindPurchase)throws Exception {

		
		TransitionService.rePassPurchaseDo(rquantity, id, kind, outReason);
		
		
		
		TransitionPurchase TransitionPurchasePre = TransitionService.idSerachforPurchaseTratison(id);
		
		TransitionPurchase TransitionPurchase = new TransitionPurchase();
		
		
		TransitionPurchase.setPreId(TransitionPurchasePre.getId());
		TransitionPurchase.setJobOrderId(TransitionPurchasePre.getJobOrderId());
		TransitionPurchase.setJobPurchaseId(TransitionPurchasePre.getJobPurchaseId());
		TransitionPurchase.setKindPurchase(TransitionPurchasePre.getKindPurchase());
		TransitionPurchase.setQuantity(rquantity);
		TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
		TransitionPurchase.setReceiveDept("PQ");
		TransitionPurchase.setStockId(TransitionPurchasePre.getStockId());
		TransitionPurchase.setStockHistoryId(TransitionPurchasePre.getStockHistoryId());
		TransitionPurchase.setKindRepass(kind);
		TransitionPurchase.setReReason(outReason);
		
		
		PurchaseService.insertTransitionpurchase(TransitionPurchase);
	
		
		

		return "redirect:/transition/list";
									
	}
	
	//구매부서 재인계 동작(가공품)
	@RequestMapping("/pReInsPart/do")
	public String pReInsDoPart(@RequestParam("rquantity")int rquantity,
						   @RequestParam("id")Long id,
						   @RequestParam("kind")String kind,
						   @RequestParam("outReason")String outReason)throws Exception {

		
		TransitionService.rePassPurchaseDoPart(rquantity, id, kind, outReason);
		
		
		
		TransitionProcessing TransitionProcessingPre = TransitionService.idSerachforPurchaseTratisonPart(id);
		
		TransitionProcessing TransitionProcessing = new TransitionProcessing();
		
		
		TransitionProcessing.setPreId(TransitionProcessingPre.getId());
		TransitionProcessing.setJobOrderId(TransitionProcessingPre.getJobOrderId());
		TransitionProcessing.setJobDesignDrawingId(TransitionProcessingPre.getJobDesignDrawingId());
		TransitionProcessing.setQuantity(rquantity);
		TransitionProcessing.setPassUsr(loginDongleService.getLoginId());
		TransitionProcessing.setReceiveDept("PQ");
		TransitionProcessing.setKindRepass(kind);
		TransitionProcessing.setReReason(outReason);
		
		TransitionMapper.insertProccessingTrantion(TransitionProcessing);
		
		

		return "redirect:/transition/list";
									
	}
	
	//가공부서 재인계 팝업(구매/재고품)
	@RequestMapping("/mReIns/{id}/{kindPurchase}")
	public ModelAndView transitionpop_mReIns(@PathVariable("id") Long id, @PathVariable("kindPurchase") String kindPurchase)throws Exception {

		
		
		if(kindPurchase.equals("P")) {
			
			List<TransitionPurchase> data = TransitionService.rePassPurcahse(id);
			ModelAndView mv = new ModelAndView("transition/popup/transitionReMfromP");
			mv.addObject("data", data);
			return mv;
		
		}
		else {
			List<TransitionPurchase> data = TransitionService.rePassPurcahseStock(id);
			ModelAndView mv = new ModelAndView("transition/popup/transitionReMfromP");
			mv.addObject("data", data);
			return mv;
			
		}
									
	}
	
	//가공부서 재인계 팝업(가공품)
	@RequestMapping("/mReInsPart/{id}")
	public ModelAndView transitionpop_mReInsPart(@PathVariable("id") Long id)throws Exception {


		List<TransitionProcessing> data = TransitionService.rePassPartList(id);
		ModelAndView mv = new ModelAndView("transition/popup/transitionReMfromPpart");
		mv.addObject("data", data);
		return mv;
					
	}
	
	//가공부서 재인계 동작(구매/재고품)
	@RequestMapping("/mReIns/do")
	public String mReInsDo(@RequestParam("rquantity")int rquantity,
						   @RequestParam("id")Long id)throws Exception {

		
		TransitionService.rePassETCDo(rquantity, id, "A");
		
		
		
		TransitionPurchase TransitionPurchasePre = TransitionService.idSerachforPurchaseTratison(id);
		
		TransitionPurchase TransitionPurchase = new TransitionPurchase();
		
		
		TransitionPurchase.setPreId(TransitionPurchasePre.getId());
		TransitionPurchase.setJobOrderId(TransitionPurchasePre.getJobOrderId());
		TransitionPurchase.setJobPurchaseId(TransitionPurchasePre.getJobPurchaseId());
		TransitionPurchase.setKindPurchase(TransitionPurchasePre.getKindPurchase());
		TransitionPurchase.setQuantity(rquantity);
		TransitionPurchase.setPassUsr(loginDongleService.getLoginId());
		TransitionPurchase.setReceiveDept("MM");
		TransitionPurchase.setStockId(TransitionPurchasePre.getStockId());
		TransitionPurchase.setStockHistoryId(TransitionPurchasePre.getStockHistoryId());
		TransitionPurchase.setKindRepass("A");
		
		
		PurchaseService.insertTransitionpurchase(TransitionPurchase);
	
		
		

		return "redirect:/transition/list";
									
	}
	
	//가공부서 재인계동작(가공품)
	@RequestMapping("/mReInsPart/do")
	public String mReInsDoPart(@RequestParam("rquantity")int rquantity,
						       @RequestParam("id")Long id,
						       @RequestParam("kind")String kind,
						       @RequestParam("outReason")String outReason)throws Exception {

		
		TransitionService.rePassPurchaseDoPart(rquantity, id, kind, outReason);
		
		
		
		TransitionProcessing TransitionProcessingPre = TransitionService.idSerachforPurchaseTratisonPart(id);
		
		TransitionProcessing TransitionProcessing = new TransitionProcessing();
		
		
		TransitionProcessing.setPreId(TransitionProcessingPre.getId());
		TransitionProcessing.setJobOrderId(TransitionProcessingPre.getJobOrderId());
		TransitionProcessing.setJobDesignDrawingId(TransitionProcessingPre.getJobDesignDrawingId());
		TransitionProcessing.setQuantity(rquantity);
		TransitionProcessing.setPassUsr(loginDongleService.getLoginId());
		TransitionProcessing.setReceiveDept("MM");
		TransitionProcessing.setKindRepass(kind);
		TransitionProcessing.setReReason(outReason);
		
		TransitionMapper.insertProccessingTrantion(TransitionProcessing);
		
		

		return "redirect:/transition/list";
									
	}
	
	//재고품 인수 LIST 상세 팝업(일반부서)
	@RequestMapping("/popStockList/{jobOrderId}/{receiveDept}")
	public ModelAndView transitionpop_StockList(@PathVariable("jobOrderId") Long jobOrderId,
								@PathVariable("receiveDept") String receiveDept)throws Exception {

		List<TransitionPurchase> data = TransitionService.popserachdataStockList(jobOrderId, receiveDept);
	
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopList");
		mv.addObject("data", data);

		return mv;
									
	}
	
	//구매품 인수 LIST 상세팝업(Admin)
	@RequestMapping("/popPurchaseAdminList/{receiveDept}")
	public ModelAndView transitionpop_purchaseAdminList(@PathVariable("receiveDept") String receiveDept)throws Exception {
			

		
		List<TransitionPurchase> data = TransitionService.popserachdataPurchaseAdminList(receiveDept);

		
		ModelAndView mv = new ModelAndView("transition/popup/transitionPopAdminList");
		mv.addObject("data", data);
		

		return mv;
									
	}
	
	//재고품 인수 LIST 상세팝업(Admin)
	@RequestMapping("/popStockAdminList/{receiveDept}")
	public ModelAndView transitionpop_stockAdminList(@PathVariable("receiveDept") String receiveDept)throws Exception {
			

		
		List<TransitionPurchase> data = TransitionService.popserachdataStockAdminList(receiveDept);

		
		ModelAndView mv = new ModelAndView("transition/popup/transitionStockPopAdminList");
		mv.addObject("data", data);

		
		return mv;
									
	}

	
	

}
