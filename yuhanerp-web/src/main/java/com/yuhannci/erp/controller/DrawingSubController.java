package com.yuhannci.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.service.DrawingService;
import com.yuhannci.erp.service.JobOrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/drawing/internal")
public class DrawingSubController {

	//@Autowired DrawingService drawingService;
	@Autowired JobOrderService jobOrderService;

	/**
	 * 도면출도리스트 -> 설비 진행 
	 * @param editMode
	 * @return
	 */
	
	public int getCharNumber2(String str, char c) {
		 int count = 0;
	        for(int i=0;i<str.length();i++)
	        {
	            if(str.charAt(i) == c)
	                count++;
	        }
	        return count;
	}
	@RequestMapping("/issue/job/progress")
	public ModelAndView job(Boolean editMode,  @RequestParam String orderNoBase, @RequestParam String orderNoExtra) {
		
		String baseTokens = null;
		String extraTokens = null;
		
		if(!(orderNoBase.isEmpty()||orderNoBase.equals(""))) {
			baseTokens = orderNoBase.substring(orderNoBase.lastIndexOf(",")+1);
		}
		
		if(!(orderNoExtra.isEmpty()||orderNoExtra.equals(""))) {
			extraTokens = orderNoExtra.substring(orderNoExtra.lastIndexOf(",")+1);
		}

		
		// 설비 진행 리스트
		ModelAndView mv = new ModelAndView("drawing/issue/sub/job");
		mv.addObject("drawing", jobOrderService.getUnfinishedJob(OrderTypeEnum.JOB, null, baseTokens, extraTokens, null, null, null, null, null, null)
									/*drawingService.selectDesignDrawingList(OrderTypeEnum.JOB)*/ );
		
		System.out.println("%%%param : "+orderNoBase);
		System.out.println("%%%param EX: "+orderNoExtra);
		
		System.out.println("%%%param T: "+baseTokens);
		System.out.println("%%%param T EX: "+extraTokens);
		return mv;
	}
	
	/**
	 * 도면출도리스트 -> 치공구 진행 
	 * @param editMode
	 * @return
	 */
	@RequestMapping("issue/jig/progress")
	public ModelAndView jig(boolean editMode,@RequestParam String orderNoBase,@RequestParam String orderNoExtra) {
		
		String baseTokens = null;
		String extraTokens = null;
		
		if(!(orderNoBase.isEmpty()||orderNoBase.equals(""))) {
			baseTokens = orderNoBase.substring(orderNoBase.lastIndexOf(",")+1);
		}
		
		if(!(orderNoExtra.isEmpty()||orderNoExtra.equals(""))) {
			extraTokens = orderNoExtra.substring(orderNoExtra.lastIndexOf(",")+1);
		}
		
		
		ModelAndView mv = new ModelAndView("drawing/issue/sub/jig");
		mv.addObject("drawing", jobOrderService.getUnfinishedJob(OrderTypeEnum.JIG, null, baseTokens, extraTokens, null, null, null, null, null, null)
				/*drawingService.selectDesignDrawingList(OrderTypeEnum.JIG)*/);
		return mv;
	}
	
}
