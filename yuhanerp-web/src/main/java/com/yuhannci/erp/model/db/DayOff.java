package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class DayOff {
	Long id;			// 내부 ID
	
	String userId;			// ID
	String userName; 		// 이름
	String deptName;		// 소속 부서 이름
	String position;		// 직위
	String dayOffName;		// dayOffType 이 'NOTICE' 일 경우 일정 이름
	String dayOffType; 		// 'AM','PM','OFF','DAY','HOLIDAY', 'NOTICE'
	Date whenDayOff;		// 날짜

	public String getDayOffLabel(){
		if("AM".equals(dayOffType) || "PM".equals(dayOffType))
			return userName + " 반차";
		else if("DAY".equals(dayOffType))
			return userName + " 휴가";
		else if("NOTICE".equals(dayOffType))
			return dayOffName;
		else if("OFF".equals(dayOffType))
			return "휴무";
		
		return "(" + dayOffType + ")";
	}
}
