package com.yuhannci.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.NoticeMessageMapper;
import com.yuhannci.erp.model.db.NoticeMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeMessageService {

	@Autowired NoticeMessageMapper noticeMessageMapper;
	
	/*
	 * 도면 출도시 알람 등록
	 */
	public void addDesignDrawingRegisteredNotice(Long jobOrderId, String device, String customer){
		
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAlarmType("G");
		noticeMessage.setDeptCode(null);
		noticeMessage.setDevice(device);
		noticeMessage.setMessageType("도면출도");
		noticeMessage.setMessage("도면이 출도 되었습니다");
		noticeMessage.setJobOrderId(jobOrderId);
		noticeMessage.setCustomer(customer);
		
		noticeMessageMapper.insertNewNoticeMessage(noticeMessage);
		
		log.info("등록된 알람메세지 ID = " + noticeMessage.getId());
	}

	public void addRegisteredNotice(Long jobOrderId, String alarmType, String msgType, String msgText, String device, String customer){
		
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAlarmType(alarmType);
		noticeMessage.setDeptCode(null);
		noticeMessage.setDevice(device);
		noticeMessage.setMessageType(msgType);
		noticeMessage.setMessage(msgText);
		noticeMessage.setJobOrderId(jobOrderId);
		noticeMessage.setCustomer(customer);
		
		noticeMessageMapper.insertNewNoticeMessage(noticeMessage);
		
		log.info("등록된 알람메세지 ID = " + noticeMessage.getId());
	}
	
	public void addRoundRobinNotice() {
		
		
	}
	
	
	public void addOverWorkLeaveNotice(String alarmType, String alarmKind, String deptCode, String kind, String useDate, String requestDate, Long leaveOverworkId, String conformStage, String message) {
		
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAlarmType(alarmType);
		noticeMessage.setAlarmKind(alarmKind);
		noticeMessage.setDeptCode(deptCode);
		noticeMessage.setKind(kind);
		noticeMessage.setUseDate(useDate);
		noticeMessage.setRequestDate(requestDate);
		noticeMessage.setLeaveOverworkId(leaveOverworkId);
		noticeMessage.setConformStage(conformStage);
		noticeMessage.setMessage(message);
		
		noticeMessageMapper.addOverWorkLeaveNotice(noticeMessage);
		
	}
	
	public String getDeviceToNotice(Long id) {
		return noticeMessageMapper.getDeviceToNotice(id);
	}
	
	public String getCustomerNameToNotice(Long id) {
		return noticeMessageMapper.getCustomerNameToNotice(id);
	}
	
	public NoticeMessage selectConformToNoticeFromLeave(Long leaveOverworkId) {
		return noticeMessageMapper.selectConformToNoticeFromLeave(leaveOverworkId);
	}
	
	public NoticeMessage selectConformToNoticeFromOverWork(Long leaveOverworkId) {
		return noticeMessageMapper.selectConformToNoticeFromOverWork(leaveOverworkId);
	}
	
	public void addOverWorkLeaveNoticeFromNoticeClass(NoticeMessage noticeMessage) {
		
		noticeMessageMapper.addOverWorkLeaveNotice(noticeMessage);
		
	}
	
	public void addRoundRobinNotice(String alarmType, String alarmKind, String deptCode, Long jobOrderId, String conformStage, String device, String message, int sumprice, String roundNo) {
		
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAlarmType(alarmType);
		noticeMessage.setAlarmKind(alarmKind);
		noticeMessage.setDeptCode(deptCode);
		noticeMessage.setJobOrderId(jobOrderId);;
		noticeMessage.setConformStage(conformStage);
		noticeMessage.setMessage(message);
		noticeMessage.setSumprice(sumprice);
		noticeMessage.setRoundNo(roundNo);
		
		noticeMessageMapper.addRoundRobinNotice(noticeMessage);
		
	}
	
	public NoticeMessage selectConformToNoticeFromRoundRobin(String roundNo) {
		return noticeMessageMapper.selectConformToNoticeFromRoundRobin(roundNo);
	}
	
	public void addRoundRobinNoticeFromNoticeClass(NoticeMessage noticeMessage) {
		noticeMessageMapper.addRoundRobinNotice(noticeMessage);
	}
	
	public void addInnerStockNoticeMsg(String alarmType, String alarmKind, Long jobOrderId, String message, String device) {
		
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAlarmType(alarmType);
		noticeMessage.setAlarmKind(alarmKind);
		noticeMessage.setJobOrderId(jobOrderId);
		noticeMessage.setMessage(message);
		noticeMessage.setDevice(device);
		
		noticeMessageMapper.addInnerStockNoticeMsg(noticeMessage);
		
	}
}
