package com.yuhannci.erp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.db.ConceptJobOrder;
import com.yuhannci.erp.model.db.JobOrder;
import com.yuhannci.erp.model.db.JobOrderModificationHistory;
import com.yuhannci.erp.service.JobDeliveryService;
import com.yuhannci.erp.service.JobOrderChangeHistoryService;
import com.yuhannci.erp.service.JobOrderService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/internal/dailyreport")
public class DailyReportSubPageController {

	@Autowired UserService userService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired JobOrderService jobOrderService;
	@Autowired JobOrderChangeHistoryService jobOrderChangeHistoryService;
	@Autowired JobDeliveryService jobDeliveryService;
	@Autowired MenuService menuService;
	
	// 영업일보 리스트 ============================================================================================================
	
	// 설비 진행 리스트
	@RequestMapping("/list/job/progress")
	public ModelAndView job(String keyword, String orderNoBase, String orderNoExtra)
	{
		log.info("job-progress :: keyword = [" + keyword + "], orderNoBase = [" + orderNoBase + "], orderNoExtra = " + orderNoExtra);
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "10", "01", "01");
		ModelAndView mv = new ModelAndView("daily_report/list_sub/job");
		mv.addObject("jobs", jobOrderService.getUnfinishedJob(OrderTypeEnum.JOB, keyword, orderNoBase, orderNoExtra, null, null, null, null, null, null));						
		mv.addObject("isBusinessUser", loginDongleService.isAdministrator() || userService.isBusinessDeptUser( loginDongleService.getLoginId() ) );
		mv.addObject("isupdate", isupdate);
		return mv;
	}
	// 설비 컨셉 완료
	@RequestMapping("/list/job/concept/finished")
	public ModelAndView finishedConcept(Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/list_sub/job_concept_finished");
		mv.addObject("concept", jobOrderService.getFinishedConcept(OrderTypeEnum.JOB, true, keyword, orderNoBase, orderNoExtra, null, null));					
		
		return mv;
	}
	
	// 설비 컨셉 진행 및 예정
	@RequestMapping("/list/job/concept")
	public ModelAndView concept(Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/list_sub/job_concept");
		mv.addObject("concept", jobOrderService.getUnfinishedConcept(OrderTypeEnum.JOB, true, keyword, orderNoBase, orderNoExtra, null, null));					
		
		return mv;
	}
	
	
	// 치공구 진행 리스트
	@RequestMapping("/list/jig/progress")
	public ModelAndView jigjob(Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "10", "01", "01");
		ModelAndView mv = new ModelAndView("daily_report/list_sub/jig");
		mv.addObject("jobs", jobOrderService.getUnfinishedJob(OrderTypeEnum.JIG, keyword, orderNoBase, orderNoExtra, null, null, null, null, null, null));							
		mv.addObject("isBusinessUser", loginDongleService.isAdministrator() || userService.isBusinessDeptUser( loginDongleService.getLoginId() ) );
		mv.addObject("isupdate", isupdate);
		return mv;
	}
	// 치공구 컨셉 완료
	@RequestMapping("/list/jig/concept/finished")
	public ModelAndView jigConceptFinished(Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/list_sub/jig_concept_finished");
		mv.addObject("concept", jobOrderService.getFinishedConcept(OrderTypeEnum.JIG, true, keyword, orderNoBase, orderNoExtra, null, null));					
		
		return mv;
	}
	
	// 치공구 컨셉 진행 및 예정
	@RequestMapping("/list/jig/concept")
	public ModelAndView jigConcept(Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/list_sub/jig_concept");
		mv.addObject("concept", jobOrderService.getUnfinishedConcept(OrderTypeEnum.JIG, true, keyword, orderNoBase, orderNoExtra, null, null));					
		
		return mv;
	}	
	
	// 지난 영업 일보 서브 페이지 =================================================================================================
	
	// 지난 영업 일보 - 설비 컨셉 완료
	@RequestMapping("/archive/{jobType}/concept/finished")
	public ModelAndView archiveJobConceptFinished(@PathVariable("jobType") String jobType
														, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deliveryDateFrom
														, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deliveryDateTo
														, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateFrom
														, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateTo, Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		// 컨셉 발송일(배송일) 검색할 때 컨셉은 검색 안함
		if(deliveryDateFrom != null || deliveryDateTo != null)
			return null;
		
		List<ConceptJobOrder> data = jobOrderService.getFinishedConcept(jobType.equalsIgnoreCase("job") ? OrderTypeEnum.JOB : OrderTypeEnum.JIG
				, false
				, keyword
				, orderNoBase
				, orderNoExtra
				, orderDateFrom, orderDateTo);
		
		long[] allIds = data.stream().mapToLong(a -> a.getId()).toArray();
		//HashMap<Long, List<JobOrderModificationHistory> > changes = jobOrderChangeHistoryService.loadChangeHistory(allIds);
				
		ModelAndView mv = new ModelAndView("daily_report/archive_sub/jobjig_concept_finished");					
		mv.addObject("data", data);				
		//mv.addObject("change", changes);				
		
		mv.addObject("mode", jobType);		
				
		return mv;
	}

	// 지난 영업 일보 - 설비컨셉/치공구컨셉 진행
	@RequestMapping("/archive/{jobType}/concept")
	public ModelAndView archiveJigJobConcept(@PathVariable("jobType") String jobType
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deliveryDateFrom
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deliveryDateTo
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateFrom
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateTo, Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		if(jobType == null || !(jobType.equals("job") || jobType.equals("jig")) )
			log.error("작업 유형(job-type)이 잘못되었습니다");
		
		ModelAndView mv = new ModelAndView("daily_report/archive_sub/jobjig_concept");					
		mv.addObject("data", jobOrderService.getFinishedConcept(jobType.equalsIgnoreCase("job") ? OrderTypeEnum.JOB : OrderTypeEnum.JIG, false, keyword, orderNoBase, orderNoExtra, orderDateFrom, orderDateTo));					
		mv.addObject("mode", jobType);		
		return mv;
	}
	
	// 지난 영업 일보 - 설비/치공구
	@RequestMapping("/archive/{jobType}")
	public ModelAndView archiveJob(@PathVariable("jobType") String jobType 
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deliveryDateFrom
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deliveryDateTo
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateFrom
			, @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateTo
			, Boolean editMode, String keyword, String orderNoBase, String orderNoExtra)
	{
		List<JobOrder> orders = jobOrderService.getFinishedJob(jobType.equalsIgnoreCase("job") ? OrderTypeEnum.JOB : OrderTypeEnum.JIG, 
				keyword, 
				orderNoBase, 
				orderNoExtra,
				orderDateFrom, orderDateTo,
				deliveryDateFrom, deliveryDateTo);
		
		Map<Long, List<String>> images;
		if (!orders.isEmpty()) {
			images = jobDeliveryService.getAttachedImages(orders.stream().map(a -> a.getId()).toArray(Long[]::new)  );
		} else {
			images = null; 
		}

		ModelAndView mv = new ModelAndView("daily_report/archive_sub/jobjig");					
		mv.addObject("data", orders);		
		mv.addObject("images", images);
		mv.addObject("mode", jobType);
		return mv;
	}	
	
	// 영업일보 등록&수정 서브 페이지 ==========================================================================================	
	// 설비 진행
	@RequestMapping("/write/job/progress")
	public ModelAndView writeJob(String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/write_sub/job");
		mv.addObject("items",  jobOrderService.getUnfinishedJob(OrderTypeEnum.JOB, keyword, orderNoBase, orderNoExtra, null, null, null, null, null, null));
		return mv;
	}	
	
	// 설비 컨셉 완료
	@RequestMapping("/write/job/concept/finished")
	public ModelAndView writeJobConceptFinished(String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/write_sub/job_concept_finished");
		mv.addObject("data", jobOrderService.getFinishedConcept(OrderTypeEnum.JOB, true, keyword, orderNoBase, orderNoExtra, null, null));					
		return mv;
	}	
	// 설비 컨셉 진행
	@RequestMapping("/write/job/concept")
	public ModelAndView writeConcept(String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/write_sub/job_concept");
		mv.addObject("data", jobOrderService.getUnfinishedConcept(OrderTypeEnum.JOB, true, keyword, orderNoBase, orderNoExtra, null, null));					
		return mv;
	}		
	// 치공구 진행
	@RequestMapping("/write/jig/progress")
	public ModelAndView writeJig(String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/write_sub/jig");
		mv.addObject("items", jobOrderService.getUnfinishedJob(OrderTypeEnum.JIG, keyword, orderNoBase, orderNoExtra, null, null, null, null, null, null));					
		return mv;
	}
	// 치공구 컨셉 완료
	@RequestMapping("/write/jig/concept/finished")
	public ModelAndView writeJigConceptFinished(String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/write_sub/jig_concept_finished");
		mv.addObject("data", jobOrderService.getFinishedConcept(OrderTypeEnum.JIG, true, keyword, orderNoBase, orderNoExtra, null, null));					
		return mv;
	}	
	// 치공구 컨셉 진행
	@RequestMapping("/write/jig/concept")
	public ModelAndView writeJigConcept(String keyword, String orderNoBase, String orderNoExtra)
	{
		ModelAndView mv = new ModelAndView("daily_report/write_sub/jig_concept");
		mv.addObject("data", jobOrderService.getUnfinishedConcept(OrderTypeEnum.JIG, true, keyword, orderNoBase, orderNoExtra, null, null));					
		return mv;
	}	
	
	
}
