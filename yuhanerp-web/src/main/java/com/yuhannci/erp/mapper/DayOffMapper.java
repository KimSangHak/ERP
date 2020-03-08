package com.yuhannci.erp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.CommonScheduleEntry;
import com.yuhannci.erp.model.db.CalendarData;
import com.yuhannci.erp.model.db.DayOff;

@Mapper
public interface DayOffMapper {
	
	// 지정된 날짜에서 +42일까지 연차 내역 검색
	public List<DayOff> selectDayOff(@Param("userId") String userId, @Param("searchInitialDate") Date searchInitialDate, @Param("specificDate") Date specificDate);
	
	// 납품건 검색
	public List<CommonScheduleEntry> selectInstallSchedule(@Param("searchInitialDate") Date searchInitialDate, @Param("specificDate") Date specificDate);
	
	public List<CalendarData> selectCalendarData(@Param("searchInitialDate") Date searchInitialDate, @Param("specificDate") Date specificDate);
}
