package com.yuhannci.erp.model;

import java.util.Date;
import java.util.List;

import com.yuhannci.erp.controller.MainController;
import com.yuhannci.erp.model.db.DayOff;

import lombok.Data;

@Data
public class CalendarEntry{
	Date date;
	int day;	// 그냥 귀차니즘..
	
	boolean prevMonth, nextMonth, sunday;
	// 연차
	List<DayOff> Offs;
	// 작업 진행
	List<CalendarDayJob> jobs;
	// 납품 
	String[] deliveryOrderNumbers;
	
	// 관리자가 별도로 입력한 레이블(calendar_data 테이블 참조)
	String calendarLabel;
	
	// 직원 연차, 회사 공지 일정 등 표시용도
	public String getShortDayOffString(){
		if(Offs != null && Offs.size() > 0){
			return String.join(", ", Offs.stream().map(a -> a.getDayOffLabel()).toArray(String[]::new));
		}
		return null;
	}
	
	// 납품 
	public String getDeliveryString(){
		if(deliveryOrderNumbers != null && deliveryOrderNumbers.length > 0){
			return  String.join(", ", deliveryOrderNumbers);
		}
		return null;
	}
	
	// 작업 진행 업무
	public String getProgressString(){
		if(jobs != null && jobs.size() > 0)
			return String.join(", ", jobs.stream().map(a -> a.getOrderNo()).toArray(String[]::new));
		return null;
	}
}