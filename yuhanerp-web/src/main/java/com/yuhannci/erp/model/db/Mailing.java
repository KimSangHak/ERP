package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class Mailing {

	Long id;
	String subject;
	
	String body;
	
	String fromUserId;
	String senderEmail;
	
	Date registeredDate;
	
	Date sentDate;
	String sendStatus;
	
	String hint;
}
