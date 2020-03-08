package com.yuhannci.erp.model.db;

import java.util.Date;

import lombok.Data;

@Data
public class JobOrderChangeHistory {
	Long id;
	
	String orderType;
	Long jobOrderId;
	String changeType;			// MODIFY_STATUS = 상태변경, MODIFY_FIELD = 값 변경, DELETE = 컨셉삭제
	String fieldName;
	String original;
	String changed;
	
	String userId;
	String userName;			// join 으로 가져올 수정한 사람의 이름
	
	Date whenModified;
}
