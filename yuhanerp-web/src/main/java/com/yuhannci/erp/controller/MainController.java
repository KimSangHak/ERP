package com.yuhannci.erp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.CalendarDayJob;
import com.yuhannci.erp.model.CalendarEntry;
import com.yuhannci.erp.model.CommonScheduleEntry;
import com.yuhannci.erp.model.db.CalendarData;
import com.yuhannci.erp.model.db.DayOff;
import com.yuhannci.erp.service.BulletinService;
import com.yuhannci.erp.service.CalendarService;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MessageService;
import com.yuhannci.erp.service.UserService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired LoginDongleService loginDongleService;
	@Autowired MessageService messageService;
	@Autowired UserService userService;
	@Autowired CalendarService calendarService;
	@Autowired BulletinService bulletinService;
	
	@RequestMapping("/main")
	public ModelAndView mainPage(){
		final String userId = loginDongleService.getLoginId();
		
		final Date today = new Date();
		
		ModelAndView mv = new ModelAndView("main/main");
		mv.addObject("recentMessage", messageService.getRecentMessage(userId));					// 최근 메세지
		mv.addObject("totalUnreadMessageCount", messageService.getUnreadMessageCount(userId));	// 아직 읽지 않은 메세지
		mv.addObject("initialYM", new SimpleDateFormat("yyyy.MM").format(today));				// 캘린더 초기 연, 월 표시
		mv.addObject("recentBulletin", bulletinService.getRecentBulletin());					// 사내 공지사항 내용
		mv.addObject("todayIssueDayoff", calendarService.getSpecificDateUserDayoffData(today));	// Today issue "연차"
		String install = String.join(", ", calendarService.getSpecificDateInstallData(today).stream().map(a -> a.getLabel()).toArray(String[]::new));
		mv.addObject("todayIssueInstall", StringUtils.isEmpty(install) ? null : install );	// Today issue "납품"
		String calendarData = String.join(",", calendarService.getCalendarData(null, today).stream().map(a -> a.getLabel()).toArray(String[]::new));
		mv.addObject("todayIssueCalendarData", calendarData);	// Today issue "달력"
		
		return mv;
	}
	
final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
	
	@RequestMapping(value="/main/calendar", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView calendar(Integer year, Integer month){
		final String userId = loginDongleService.getLoginId();
		
		// 요청한 월(y-m)의 1일
		Calendar cal = Calendar.getInstance();		
		if(year != null)
			cal.set(Calendar.YEAR, year);
		if(month != null)
			cal.set(Calendar.MONTH, month - 1);		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		int thisMonth = cal.get(Calendar.MONTH);
		int offset = cal.get(Calendar.DAY_OF_WEEK);		// SUN=1, MON=2 ... 
		Date searchInitialDate = cal.getTime();	
		
		// 1일이 아닌 1일이 시작하는 주의 일요일~
		// 2018-05-01의 경우 화요일이기 때문에, 그 주 일요일인 2018-04-29 를 가리키도록 offset 이동
		cal.add(Calendar.DAY_OF_MONTH, -offset + 1);
		log.debug("검색 요청 = " + year + "/" + month + " ==> Calendar 표기 시작 날짜 = " + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));		
		
		// 검색할 캘린더의 첫 날짜
		final String thisMonthLabel = sdf.format(searchInitialDate);	// yyyy.MM
		
		// 직원 휴가 연차 검색
		List<DayOff> holidays = calendarService.getUserDayoffData(searchInitialDate);
		log.debug("#1 휴가 항목 = " + holidays.size() + " 개 발견");
		// 진행 작업
		List<CalendarDayJob> allJobs = calendarService.getJobData(searchInitialDate);
		log.debug("#2 진행 항목 = " + allJobs.size() + " 개 발견. 검색시작날짜(searchInitialDate) = " + new SimpleDateFormat("yyyy-MM-dd").format(searchInitialDate));
		// 납품
		List<CommonScheduleEntry> allInstalls = calendarService.getInstallData(searchInitialDate);
		log.debug("#3 납품 항목 = " + allInstalls.size() + " ==> " + allInstalls);
		// 캘린더 표시 내용
		List<CalendarData> allCalendarData = calendarService.getCalendarData(searchInitialDate, null);
		
		// 이전달
		Calendar prevCal = Calendar.getInstance();
		prevCal.setTime(searchInitialDate);
		prevCal.add(Calendar.MONTH, -1);
		
		// 다음달
		Calendar nextCal = Calendar.getInstance();
		nextCal.setTime(searchInitialDate);
		nextCal.add(Calendar.MONTH, 1);		
		
		// 캘린더 데이터 (6주치 데이터 추출)
		List<CalendarEntry> entries = new ArrayList<>(6 * 7);
		String v;
		for(int i=0;i<6*7;i++){
			Date day = cal.getTime();
			
			CalendarEntry entry = new CalendarEntry();
			entry.setDay(day.getDate());
			entry.setDate(day);
			entry.setPrevMonth ( day.getMonth() < thisMonth );
			entry.setNextMonth ( day.getMonth() > thisMonth );
			entry.setSunday(day.getDay() == 0);
			entry.setOffs(holidays.stream().filter(a -> a.getWhenDayOff().getTime() == day.getTime()).collect(Collectors.toList()) );
			entry.setJobs(allJobs.stream().filter(a -> a.getScheduleDate().getTime() == day.getTime()).collect(Collectors.toList()));
			entry.setDeliveryOrderNumbers(allInstalls.stream().filter(a -> a.getWhen().getTime() == day.getTime()).map(a -> a.getLabel()).toArray(String[]::new));
			v = String.join(",", allCalendarData.stream().filter(a -> a.getWhen().getTime() == day.getTime()).map(a -> a.getLabel()).toArray(String[]::new));
			entry.setCalendarLabel( StringUtils.isEmpty(v) ? null : v );
			entries.add(entry);
			
			cal.add(Calendar.DAY_OF_MONTH, 1);	// 다음 날짜(=내일)
		}
				
		ModelAndView mv = new ModelAndView("main/cal");
		mv.addObject("data", entries);
		mv.addObject("title", thisMonthLabel);
		mv.addObject("prevY", prevCal.get(Calendar.YEAR));
		mv.addObject("nextY", nextCal.get(Calendar.YEAR));
		mv.addObject("prevM", prevCal.get(Calendar.MONTH) + 1);
		mv.addObject("nextM", nextCal.get(Calendar.MONTH) + 1);
		
		log.debug("캘린더 정보 ==> 이달 = " + year + "/" + month + ", 이전달 = " + prevCal.get(Calendar.YEAR) + "/" + (prevCal.get(Calendar.MONTH) + 1) + ", 다음달 = " + nextCal.get(Calendar.YEAR) + "/" + (nextCal.get(Calendar.MONTH) + 1));
		return mv;
	}	

}
