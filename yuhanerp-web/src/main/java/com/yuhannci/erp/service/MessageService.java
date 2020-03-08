package com.yuhannci.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhannci.erp.mapper.MessageMapper;
import com.yuhannci.erp.model.db.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageService {

	@Autowired MessageMapper messageMapper;
	
	public List<Message> getRecentMessage(String userId){
		return messageMapper.selectRecentMessages(userId, 4);
	}
	
	public int getUnreadMessageCount(String userId){
		return messageMapper.selectUnreadMessageCount(userId);
	}
}
