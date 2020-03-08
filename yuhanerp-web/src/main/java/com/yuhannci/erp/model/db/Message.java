package com.yuhannci.erp.model.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Message {
	Long id;
	
	String senderUserId;
	String receiverUserId;
	
	String title;
	String body;
	
	Date whenReceived, whenRead, whenDeleted;
	
	public boolean isRead(){
		return whenReceived != null;
	}
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public String getReceivedDate(){
		return sdf.format(whenReceived);
	}
	
	public String getReadDate(){
		return whenRead != null ? sdf.format(whenRead) : "";
	}
}
