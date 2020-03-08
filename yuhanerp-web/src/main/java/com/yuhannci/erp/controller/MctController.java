package com.yuhannci.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import com.yuhannci.erp.service.JobMctViewDataService;
import com.yuhannci.erp.service.JobMctHistoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mct")
public class MctController {
	@Autowired JobMctViewDataService mctViewService;
	@Autowired JobMctHistoryService mctHistoryService;

	@RequestMapping("/list")
	public ModelAndView mctList()
	{
		ModelAndView mv = new ModelAndView("mct/list");
		mv.addObject("data", mctViewService.getMctViewList());
		return mv;
	}
	@RequestMapping("/history")
	public ModelAndView mctHistory(String orderBy, String orderNoBase, String orderNoExtra, String drawingNo, String mctDateFrom, String mctDateTo)
	{
		String adjustedorderBy = !StringUtils.isEmpty(orderBy) ? orderBy.trim() : "All";
		String adjustedorderNoBase = !StringUtils.isEmpty(orderNoBase) ? orderNoBase.trim() : null;
		String adjustedorderNoExtra = !StringUtils.isEmpty(orderNoExtra) ? orderNoExtra.trim() : null;
		String adjusteddrawingNo = !StringUtils.isEmpty(drawingNo) ? drawingNo.trim() : null;
		
		String adjustedMctDateFrom = !StringUtils.isEmpty(mctDateFrom) ? mctDateFrom.trim() : null;
		String adjustedMctDateTo = !StringUtils.isEmpty(mctDateTo) ? mctDateTo.trim() : null;

		SimpleDateFormat  DateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DATE,  -7);
		String adjustedWeekday = DateFormatDay.format(cal.getTime());
		String adjustedToday = DateFormatDay.format(new Date());
		if (adjustedMctDateFrom==null)
		{
			adjustedMctDateFrom = adjustedWeekday;
		}
		if (adjustedMctDateTo==null)
		{
			adjustedMctDateTo = adjustedToday;
		}

		log.info("orderNoBase = [" + adjustedorderNoBase + "], orderNoExtra = " + adjustedorderNoExtra);
		
		ModelAndView mv = new ModelAndView("mct/history");
		mv.addObject("orderBy", adjustedorderBy);
		mv.addObject("orderNoBase", adjustedorderNoBase);
		mv.addObject("orderNoExtra", adjustedorderNoExtra);
		mv.addObject("drawingNo", adjusteddrawingNo);

		mv.addObject("mctDateFrom", adjustedMctDateFrom);
		mv.addObject("mctDateTo", adjustedMctDateTo);
		if (adjustedorderBy.equals("All"))
		{
			//mv.addObject("test", adjustedorderBy);
			mv.addObject("data", mctHistoryService.getMctHistoryAll(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		else if (adjustedorderBy.equals("DW_USER"))
		{
			mv.addObject("data", mctHistoryService.getMctHistoryDwUser(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		else if (adjustedorderBy.equals("DW_MCT"))
		{
			mv.addObject("data", mctHistoryService.getMctHistoryDwMct(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		else if (adjustedorderBy.equals("OR"))
		{
			mv.addObject("data", mctHistoryService.getMctHistoryOrder(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		else if (adjustedorderBy.equals("DW"))
		{
			mv.addObject("data", mctHistoryService.getMctHistoryDrawing(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		else if (adjustedorderBy.equals("USER"))
		{
			mv.addObject("data", mctHistoryService.getMctHistoryUser(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		else if (adjustedorderBy.equals("MCT"))
		{
			mv.addObject("data", mctHistoryService.getMctHistoryMct(adjustedorderBy, adjustedorderNoBase, adjustedorderNoExtra, adjusteddrawingNo, adjustedMctDateFrom, adjustedMctDateTo));
		}
		return mv;
	}
}
