package com.yuhannci.erp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yuhannci.erp.model.db.NoticeMessage;

@Mapper
public interface NoticeMessageMapper {

	public void insertNewNoticeMessage(NoticeMessage body);
	public String getCustomerNameToNotice(Long id);
	public String getDeviceToNotice(Long id);
	
	public void addOverWorkLeaveNotice(NoticeMessage body);
	
	public void addRoundRobinNotice(NoticeMessage body);
	
	public void addInnerStockNoticeMsg(NoticeMessage body);
	
	public NoticeMessage selectConformToNoticeFromLeave(Long leaveOverworkId);
	
	public NoticeMessage selectConformToNoticeFromOverWork(Long leaveOverworkId);
	
	public NoticeMessage selectConformToNoticeFromRoundRobin(String roundNo);
}
