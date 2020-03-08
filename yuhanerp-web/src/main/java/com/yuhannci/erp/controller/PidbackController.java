package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.PibackPurchase;
import com.yuhannci.erp.model.PidbackDrawing;
import com.yuhannci.erp.model.PidbackInsForm;
import com.yuhannci.erp.model.PidbackSearchForm;
import com.yuhannci.erp.model.StockListOrderNo;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.PidbackComment;
import com.yuhannci.erp.model.db.PidbackEntry;
import com.yuhannci.erp.model.db.TransitionPurchase;
import com.yuhannci.erp.model.db.UserData;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.PidbackService;
import com.yuhannci.erp.service.PurchaseService;
import com.yuhannci.erp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pidback")
@Slf4j
public class PidbackController {
  	@Autowired UserService userService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired PidbackService pidbackService;
	@Autowired PurchaseService purchaseService;
	
	
	
	@RequestMapping("/main")
	public ModelAndView pidbackMain(String kind, String usrName, String title, String orderNoBase, String orderNoExtra, String drawingNo, String seq, Date DateBegin, Date DateEnd) {
		
		
		
		ModelAndView mv = new ModelAndView("pidback/main");
		
		return mv;
	}
	
	@RequestMapping("/data/main")
	@ResponseBody
	public DataTableResponse pidbackMainData(PidbackSearchForm form) {
		
		
		
		DataTableResponse res = new DataTableResponse();
		
		try {
			
			System.out.println("여기 찍힘!?!?!?!??!?!?!?!?!??!??!");
			
			
			res = pidbackService.getPidbackEntry(form);
		
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		
		
		return res;
		
	}
	
	//피드백 등록 popup
	@RequestMapping("/pidbackIns/popup")
	public ModelAndView pidback_insertPop(){
		
		
		List<UserData> data = userService.getUserData(loginDongleService.getLoginId());
		List<StockListOrderNo> orderNodata = purchaseService.searchOrderNo();

		
		ModelAndView mv = new ModelAndView("pidback/popup/insertPidback");
		
		mv.addObject("data", data);
		mv.addObject("orderNodata", orderNodata);


		return mv;
	}
	
	//피드백 구매품 검색 팝업
	@RequestMapping("/pidbackInsP/repopup/{modelNo}/{description}")
	public ModelAndView pidbackP_ReinsertPop(@PathVariable("modelNo") String modelNo, @PathVariable("description") String description){
		
		if((StringUtils.isEmpty(modelNo) && StringUtils.isEmpty(description))) {
			modelNo = "---";
			description = "---";
		}
		
		if(!(modelNo.equals("---"))&&description.equals("---")) {
			
			description = "";
		}
		
		if(modelNo.equals("---")&&!(description.equals("---"))) {
			
			modelNo = "";
		}

		
		List<PibackPurchase> data = pidbackService.getPidbackPurchase(modelNo, description);
		
		
	
		
		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("pidback/popup/pidbackPurchase");
		
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);

		return mv;
	}
	
	//피드백 도면 검색 팝업
	@RequestMapping("/pidbackInsD/repopup/{orderNoBase}/{orderNoExtra}/{drawingNo}")
	public ModelAndView pidbackD_ReinsertPop(@PathVariable("orderNoBase") String orderNoBase, @PathVariable("orderNoExtra") String orderNoExtra, @PathVariable("drawingNo") String drawingNo){
		
		
		if(!(orderNoBase.equals("Z"))&&orderNoExtra.equals("Z")&&drawingNo.equals("Z")) {
			
			orderNoExtra = "";
			drawingNo = "";
		}
		
		if(!(orderNoBase.equals("Z"))&&!(orderNoExtra.equals("Z"))&&drawingNo.equals("Z")) {
			
			drawingNo = "";
		}
		
		if(!(orderNoBase.equals("Z"))&&orderNoExtra.equals("Z")&&!(drawingNo.equals("Z"))) {
			
			orderNoExtra = "";
		}
		
		
		
		if(orderNoBase.equals("Z")&&!(orderNoExtra.equals("Z"))&&drawingNo.equals("Z")) {
			
			orderNoBase = "";
			drawingNo = "";
		}
		
		if(orderNoBase.equals("Z")&&!(orderNoExtra.equals("Z"))&&!(drawingNo.equals("Z"))) {
			
			
			orderNoBase = "";
		}
		
		if(orderNoBase.equals("Z")&&orderNoExtra.equals("Z")&&!(drawingNo.equals("Z"))) {
			
			orderNoBase = "";
			orderNoExtra = "";
		}
		


		List<PidbackDrawing> data = pidbackService.getPidbackDrawing(orderNoBase, orderNoExtra, drawingNo);

		int pageNo = 0;
		
		ModelAndView mv = new ModelAndView("pidback/popup/pidbackDrawing");
		
		mv.addObject("data", data);
		mv.addObject("pageNo", pageNo);


		return mv;
	}
	
	//피드백 insert 동작
	@RequestMapping("/insertDo")
	public String PidbackInsertDo(@RequestParam("orderId") Long orderId,
								  @RequestParam("pid") Long pid,
								  @RequestParam("userId") String userId,
								  @RequestParam("deptCode") String deptCode,
								  @RequestParam("title") String title,
								  @RequestParam("body") String body,
								  @RequestParam("kind") String kind
								  
												) throws Exception{
		
		
		PidbackInsForm pidbackInsForm = new PidbackInsForm();
		
		pidbackInsForm.setOrderId(orderId);
		pidbackInsForm.setPid(pid);
		pidbackInsForm.setUserId(userId);
		pidbackInsForm.setDeptCode(deptCode);
		pidbackInsForm.setTitle(title);
		pidbackInsForm.setBody(body);
		pidbackInsForm.setKind(kind);
		
		
		if(kind.equals("D")) {
			
			pidbackService.insPidbackDrawing(pidbackInsForm);

		}else if(kind.equals("P")) {
			
			pidbackService.insPidbackPurchase(pidbackInsForm);

		}

				
		return "redirect:/pidback/main";
								
	}
	
	//피드백 게시글 상세 내용
	@RequestMapping("/detail/view")
	public ModelAndView PidbackInsertDo(@RequestParam("id") Long id) throws Exception{
		
		
		

		
		PidbackEntry data = pidbackService.getDetailPidback(id);
				
		List<PidbackComment> data2 = pidbackService.getCommentPidback(id);
		
		
		
		
		pidbackService.plusViewUpdate(id);
		
		ModelAndView mv = new ModelAndView("pidback/detailOne");
		
		int pageNo = 0;
		
		mv.addObject("data", data);
		mv.addObject("data2", data2);
		mv.addObject("loginUsr", loginDongleService.getLoginId());
		mv.addObject("pageNo", pageNo);
		
		return mv;
		
								
	}
	
	//댓글 등록
	@RequestMapping("/insComment/do")
	public String CommentInsertDo(@RequestParam("id") Long id,
								  @RequestParam("sbody") String sbody) throws Exception{
		
		
			
		String value = Long.toString(id);
		
		
		pidbackService.addComment(id, sbody, loginDongleService.getLoginId());
		

		return "redirect:/pidback/detail/view?id=" + value;
		
								
	}
	
	//댓글 수정
	@RequestMapping("/configComment/do/{id}/{sid}/{stitle}")
	public String CommentUpdateDo(@PathVariable("id") Long id,
								  @PathVariable("sid") Long sid,
								  @PathVariable("stitle") String stitle) throws Exception{
		
		
		System.out.println("&&&&&&&&&&&&&&&&&&&& ==== "+ sid);
			
		String value = Long.toString(id);

		pidbackService.configComment(sid, stitle);
		

		return "redirect:/pidback/detail/view?id=" + value;
		
								
	}
	
	//댓글 삭제
	@RequestMapping("/deleteComment/do/{id}/{sid}")
	public String deleteCommentDo(@PathVariable("id") Long id,
								  @PathVariable("sid") Long sid) throws Exception{
		
		
		System.out.println("&&&&&&&&&&&&&&&&&&&& ==== "+ sid);
			
		String value = Long.toString(id);

		pidbackService.delteComment(sid);
		

		return "redirect:/pidback/detail/view?id=" + value;
		
								
	}
	
	//피드백 수정
	@RequestMapping("/configPidback/do")
	public String ConfigPidbackDo(@RequestParam("id") Long id,
								  @RequestParam("body") String body,
								  @RequestParam("title") String title) throws Exception{
		
		
			
		String value = Long.toString(id);
		
		
		pidbackService.configPidback(id, body, title);
		

		return "redirect:/pidback/detail/view?id=" + value;
		
								
	}
	
	//피드백 삭제
	@RequestMapping("/deletePidback/do")
	public String deletePidbackDo(@RequestParam("id") Long id) throws Exception{
		

		pidbackService.deletePidback(id);

		return "redirect:/pidback/main";
		
								
	}
	
	
	

}
