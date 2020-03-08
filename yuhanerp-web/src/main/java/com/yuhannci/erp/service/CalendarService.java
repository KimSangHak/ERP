package com.yuhannci.erp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.DayOffMapper;
import com.yuhannci.erp.mapper.JobOrderMapper;
import com.yuhannci.erp.model.CalendarDayJob;
import com.yuhannci.erp.model.CommonScheduleEntry;
import com.yuhannci.erp.model.db.CalendarData;
import com.yuhannci.erp.model.db.DayOff;

@Service
public class CalendarService {
	@Autowired DayOffMapper dayOffMapper;
	@Autowired JobOrderMapper jobOrderMapper;
	
	// 지정한 날부터 42 일내의 휴가 데이터 수집 
	public List<DayOff> getUserDayoffData(Date searchInitialDate){
		List<DayOff> d1 = dayOffMapper.selectDayOff(null, searchInitialDate, null);		
		return d1;
	}
	
	public List<CalendarDayJob> getJobData(Date searchInitialDate){
		return jobOrderMapper.selectCalendarData(searchInitialDate);
	}
	
	// 특정 날짜의 연차 자료 조회
	public List<DayOff> getSpecificDateUserDayoffData(Date specificDate){
		return dayOffMapper.selectDayOff(null, null, specificDate);		
	}
	
	// 지정한 날부터 42 일내의 납품(설치)건 조회
	public List<CommonScheduleEntry> getInstallData(Date searchInitialDate){
		return dayOffMapper.selectInstallSchedule(searchInitialDate, null);
	}
	public List<CommonScheduleEntry> getSpecificDateInstallData(Date specificDate){
		return dayOffMapper.selectInstallSchedule(null, specificDate);
	}
	
	// 수동으로 입력한 달력 정보
	public List<CalendarData> getCalendarData(Date searchInitialDate, Date specificDate ) {
		if(searchInitialDate == null && specificDate == null)
			return null;
		
		if(searchInitialDate != null)
			return dayOffMapper.selectCalendarData(searchInitialDate, null);
		else
			return dayOffMapper.selectCalendarData(null, specificDate);
	}
}
