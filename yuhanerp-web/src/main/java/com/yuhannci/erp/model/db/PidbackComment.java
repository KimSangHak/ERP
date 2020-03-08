package com.yuhannci.erp.model.db;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class PidbackComment {
	
	 Long id;
	 Long pidBackId;
	 String smallBody;
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 Date whenCreated;
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 Date whenUpdated;
	 String userId;
	 String deleted;
	 
	 String usrName;

}
